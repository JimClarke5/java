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

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Basic Stochastic gradient descent optimizer. GradientDescent updates the current weight using the
 * current gradient ?L/?w multiplied by the learning rate.
 */
public class GradientDescent extends Optimizer {

  public static final String DEFAULT_NAME = "GradientDescent";
  public static final float LEARNING_RATE_DEFAULT = 0.01f;

  /**
   * Creates a GradientDescent Optimizer using {@link #DEFAULT_NAME} for the Optimizer name and
   * {@link #LEARNING_RATE_DEFAULT} for the learning rate.
   *
   * @param graph the TensorFlow graph
   */
  public GradientDescent(Graph graph) {
    this(graph, LEARNING_RATE_DEFAULT);
  }

  /**
   * Creates a GradientDescent Optimizer using {@link #DEFAULT_NAME} for the Optimizer name.
   *
   * @param graph the TensorFlow graph
   * @param learningRate the learning rate.
   */
  public GradientDescent(Graph graph, float learningRate) {
    super(graph, null, learningRate);
  }

  /**
   * Creates a GradientDescent Optimizer using {@link #DEFAULT_NAME} for the Optimizer name.
   *
   * @param graph the TensorFlow graph
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   */
  public GradientDescent(Graph graph, Operand<TFloat32> learningRateOperand) {
    super(graph, null, learningRateOperand);
  }

  /**
   * Creates a GradientDescent Optimizer
   *
   * @param graph the TensorFlow graph
   * @param name the name for this Optimizer.
   * @param learningRate the learning rate.
   */
  public GradientDescent(Graph graph, String name, float learningRate) {
    super(graph, name, learningRate);
  }

  /**
   * Creates a GradientDescent Optimizer
   *
   * @param graph the TensorFlow graph
   * @param name the name for this Optimizer.
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   */
  public GradientDescent(Graph graph, String name, Operand<TFloat32> learningRateOperand) {
    super(graph, name, learningRateOperand);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    return tf.train.applyGradientDescent(
        variable, tf.dtypes.cast(getLearningRateOperand(), gradient.dataType()), gradient);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "GradientDescent{" + "learningRate=" + learningRate + '}';
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return DEFAULT_NAME;
  }
}
