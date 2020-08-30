/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.framework.optimizers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.train.ApplyMomentum;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * SGD plus momentum, either nesterov or traditional.
 * <p>
 * See the <a href="http://jmlr.org/proceedings/papers/v28/sutskever13.pdf">paper</a> for details of
 * nesterov momentum.
 */
public class Momentum extends Optimizer {

  public static final String MOMENTUM = "momentum";

  private float learningRate;
  private Tensor<TFloat32> learningRateTensor;
  private final Placeholder<TFloat32> learningRatePlaceholder;
  private Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict;

  private final float momentum;

  private final boolean useNesterov;

  public Momentum(Graph graph, float learningRate, float momentum, boolean useNesterov) {
    super(graph);
    this.learningRate = learningRate;
    this.learningRateTensor = TFloat32.scalarOf(this.learningRate);
    this.learningRatePlaceholder =
            tf.withSubScope(LEARNING_RATE).placeholder(TFloat32.DTYPE, Placeholder.shape(Shape.scalar()));
    this.feedDict = Collections.singletonMap(this.learningRatePlaceholder, this.learningRateTensor);
    this.momentum = momentum;
    this.useNesterov = useNesterov;
  }

  public Momentum(Graph graph, String name, float learningRate, float momentum, boolean useNesterov) {
    super(graph, name);
    this.learningRate = learningRate;
    this.learningRateTensor = TFloat32.scalarOf(this.learningRate);
    this.learningRatePlaceholder =
            tf.withSubScope(LEARNING_RATE).placeholder(TFloat32.DTYPE, Placeholder.shape(Shape.scalar()));
    this.feedDict = Collections.singletonMap(this.learningRatePlaceholder, this.learningRateTensor);
    this.momentum = momentum;
    this.useNesterov = useNesterov;
  }

  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createMomentumSlot(v);
    }
  }

  private <T extends TType> void createMomentumSlot(Output<T> v) {
    Operand<T> initializer = tf
        .fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), MOMENTUM, initializer);
  }

  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> slot = getSlot(variable, MOMENTUM).get();
    return tf.train
        .applyMomentum(variable, slot, tf.dtypes.cast(learningRatePlaceholder, gradient.dataType()),
            gradient,
            tf.dtypes.cast(tf.constant(momentum), gradient.dataType()),
            ApplyMomentum.useNesterov(useNesterov));
  }

  @Override
  public String toString() {
    return "Momentum{" +
        "learningRate=" + learningRate +
        ", momentum=" + momentum +
        ", useNesterov=" + useNesterov +
        '}';
  }

  @Override
  public String getOptimizerName() {
    return "Momentum";
  }

  /** {@inheritDoc} */
  @Override
  public float getLearningRate() {
    return this.learningRate;
  }

  /** {@inheritDoc} */
  @Override
  public final void setLearningRate(float learningRate) {
    this.learningRate = learningRate;
    if (this.learningRateTensor != null) {
      this.learningRateTensor.close();
    }
    this.learningRateTensor = TFloat32.scalarOf(this.learningRate);
    this.feedDict = Collections.singletonMap(this.learningRatePlaceholder, this.learningRateTensor);
  }

  /** {@inheritDoc} */
  public Map<Operand<? extends TType>, Tensor<? extends TType>> getFeedDict() {
    return this.feedDict;
  }

  /** {@inheritDoc} */
  @Override
  public void close() throws Exception {
    if (this.learningRateTensor != null) {
      this.learningRateTensor.close();
      this.learningRateTensor = null;
    }
  }
}
