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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

import java.util.List;
import java.util.Optional;

/**
 * Optimizer that implements the Adam algorithm.
 *
 * <p>Adam optimization is a stochastic gradient descent method that is based on adaptive estimation
 * of first-order and second-order moments.
 *
 * <p>According to Kingma et al., 2014, the method is "computationally efficient, has little memory
 * requirement, invariant to diagonal rescaling of gradients, and is well suited for problems that
 * are large in terms of data/parameters".
 *
 * <p>@see <a href="http://arxiv.org/abs/1412.6980">Kingma et al., 2014, Adam: A Method for
 * Stochastic Optimization</a>.
 */
@Operator
public class Adam extends Optimizer {

  public static final String DEFAULT_NAME = "Adam";

  public static final String FIRST_MOMENT = "m";
  public static final String SECOND_MOMENT = "v";

  public static final float LEARNING_RATE_DEFAULT = 0.001f;
  public static final float EPSILON_DEFAULT = 1e-8f;
  public static final float BETA_ONE_DEFAULT = 0.9f;
  public static final float BETA_TWO_DEFAULT = 0.999f;

  private final float betaOne;

  private final float betaTwo;

  private final float epsilon;

  private Constant<TFloat32> epsilonConst;
  private Constant<TFloat32> betaOneConst;
  private Constant<TFloat32> betaTwoConst;
  private Variable<TFloat32> betaOnePower;
  private Variable<TFloat32> betaTwoPower;

  /**
   * Creates an Adam optimizer using {@link #DEFAULT_NAME} for the Optimizer name, {@link
   * #LEARNING_RATE_DEFAULT} for the learning rate, {@link #BETA_ONE_DEFAULT} for the betaOne value,
   * {@link #BETA_TWO_DEFAULT} for the betaTwo value, and {@link #EPSILON_DEFAULT} for the epsilon.
   *
   * @param graph the TensorFlow graph
   */
  public Adam(Graph graph) {
    this(graph, LEARNING_RATE_DEFAULT, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an Adam optimizer using {@link #DEFAULT_NAME} for the Optimizer name, {@link
   * #BETA_ONE_DEFAULT} for the betaOne value, {@link #BETA_TWO_DEFAULT} for the betaTwo value, and
   * {@link #EPSILON_DEFAULT} for the epsilon.
   *
   * @param graph the TensorFlow graph
   * @param learningRate the learning rate
   */
  public Adam(Graph graph, float learningRate) {
    this(graph, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }
  /**
   * Creates an Adam optimizer using {@link #DEFAULT_NAME} for the Optimizer name, {@link
   * #BETA_ONE_DEFAULT} for the betaOne value, {@link #BETA_TWO_DEFAULT} for the betaTwo value, and
   * {@link #EPSILON_DEFAULT} for the epsilon.
   *
   * @param graph the TensorFlow graph
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   */
  public Adam(Graph graph, Operand<TFloat32> learningRateOperand) {
    this(graph, learningRateOperand, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an Adam optimizer using {@link #DEFAULT_NAME} for the Optimizer name.
   *
   * @param graph the TensorFlow graph
   * @param learningRate the learning rate
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the 2nd moment estimates.
   * @param epsilon A small constant for numerical stability. This epsilon is "epsilon hat" in the
   *     Kingma and Ba paper (in the formula just before Section 2.1), not the epsilon in Algorithm
   *     1 of the paper..
   */
  public Adam(Graph graph, float learningRate, float betaOne, float betaTwo, float epsilon) {
    this(graph, null, learningRate, betaOne, betaTwo, epsilon);
  }

  /**
   * Creates an Adam optimizer using {@link #DEFAULT_NAME} for the Optimizer name.
   *
   * @param graph the TensorFlow graph
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the 2nd moment estimates.
   * @param epsilon A small constant for numerical stability. This epsilon is "epsilon hat" in the
   *     Kingma and Ba paper (in the formula just before Section 2.1), not the epsilon in Algorithm
   *     1 of the paper.
   */
  public Adam(
      Graph graph,
      Operand<TFloat32> learningRateOperand,
      float betaOne,
      float betaTwo,
      float epsilon) {
    this(graph, null, learningRateOperand, betaOne, betaTwo, epsilon);
  }

  /**
   * Creates an Adam optimizer using {@link #BETA_ONE_DEFAULT} for the betaOne value, {@link
   * #BETA_TWO_DEFAULT} for the betaTwo value, and {@link #EPSILON_DEFAULT} for the epsilon.
   *
   * @param graph the TensorFlow graph
   * @param name the Optimizer name.
   * @param learningRate the learning rate
   */
  public Adam(Graph graph, String name, float learningRate) {
    this(graph, name, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an Adam optimizer using {@link #BETA_ONE_DEFAULT} for the betaOne value, {@link
   * #BETA_TWO_DEFAULT} for the betaTwo value, and {@link #EPSILON_DEFAULT} for the epsilon.
   *
   * @param graph the TensorFlow graph
   * @param name the Optimizer name.
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   */
  public Adam(Graph graph, String name, Operand<TFloat32> learningRateOperand) {
    this(graph, name, learningRateOperand, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an Adam optimizer
   *
   * @param graph the TensorFlow graph
   * @param name the Optimizer name.
   * @param learningRate the learning rate
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the 2nd moment estimates.
   * @param epsilon A small constant for numerical stability. This epsilon is "epsilon hat" in the
   *     Kingma and Ba paper (in the formula just before Section 2.1), not the epsilon in Algorithm
   *     1 of the paper.
   */
  public Adam(
      Graph graph, String name, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(graph, name, learningRate);
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
  }

  /**
   * Creates an Adam optimizer
   *
   * @param graph the TensorFlow graph
   * @param name the Optimizer name.
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the 2nd moment estimates.
   * @param epsilon A small constant for numerical stability. This epsilon is "epsilon hat" in the
   *     Kingma and Ba paper (in the formula just before Section 2.1), not the epsilon in Algorithm
   *     1 of the paper.
   */
  public Adam(
      Graph graph,
      String name,
      Operand<TFloat32> learningRateOperand,
      float betaOne,
      float betaTwo,
      float epsilon) {
    super(graph, name, learningRateOperand);
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
  }

  /**
   * Creates the Operation that minimizes the loss
   *
   * @param scope the TensorFlow scope
   * @param loss the loss to minimize
   * @param learningRate the learning rate
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the 2nd moment estimates.
   * @param epsilon A small constant for numerical stability. This epsilon is "epsilon hat" in the
   *     Kingma and Ba paper (in the formula just before Section 2.1), not the epsilon in Algorithm
   *     1 of the paper.
   * @param options Optional Optimizer attributes
   * @param <T> the data type for the loss
   * @return the Operation that minimizes the loss
   * @throws java.lang.IllegalArgumentException if scope does not represent a Graph
   */
  @Endpoint(name = "adam_minimize")
  public static <T extends TType> Op createAdamMinimize(
      Scope scope,
      Operand<T> loss,
      float learningRate,
      float betaOne,
      float betaTwo,
      float epsilon,
      Optimizer.Options... options) {
    if (!(scope.env() instanceof Graph)) {
      throw new IllegalArgumentException("Optimizers are only supported on Graphs");
    }
    Adam adam = new Adam((Graph) scope.env(), learningRate, betaOne, betaTwo, epsilon);
    String name = null;
    for (Options o : options) {
      if (o.sharedName != null) {
        name = o.sharedName;
      }
    }
    if (name == null) {
      return adam.minimize(loss);
    } else {
      return adam.minimize(loss, name);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdamSlot(v.asOutput());
    }
    betaOnePower = tf.withName("beta1_power").variable(Shape.scalar(), TFloat32.DTYPE);
    Assign<TFloat32> betaOnePowerInit = tf.assign(betaOnePower, tf.constant(betaOne));
    graph.addInitializer(betaOnePowerInit);
    betaTwoPower = tf.withName("beta2_power").variable(Shape.scalar(), TFloat32.DTYPE);
    Assign<TFloat32> betaTwoPowerInit = tf.assign(betaTwoPower, tf.constant(betaTwo));
    graph.addInitializer(betaTwoPowerInit);
  }

  /** {@inheritDoc} */
  @Override
  protected Optional<Op> prepare(String scopeName) {
    betaOneConst = tf.constant(betaOne);
    betaTwoConst = tf.constant(betaTwo);
    epsilonConst = tf.constant(epsilon);
    return Optional.empty();
  }

  /**
   * Creates an Adam Slot
   *
   * @param v the variable to install in the slot
   * @param <T> the datatype of the variable.
   */
  private <T extends TType> void createAdamSlot(Output<T> v) {
    Operand<T> firstMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), FIRST_MOMENT, firstMomentInitializer);
    Operand<T> secondMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), SECOND_MOMENT, secondMomentInitializer);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> firstMomentSlot = getSlot(variable, FIRST_MOMENT).get();
    Variable<T> secondMomentSlot = getSlot(variable, SECOND_MOMENT).get();
    return tf.train.applyAdam(
        variable,
        firstMomentSlot,
        secondMomentSlot,
        tf.dtypes.cast(betaOnePower, gradient.dataType()),
        tf.dtypes.cast(betaTwoPower, gradient.dataType()),
        tf.dtypes.cast(getLearningRateOperand(), gradient.dataType()),
        tf.dtypes.cast(betaOneConst, gradient.dataType()),
        tf.dtypes.cast(betaTwoConst, gradient.dataType()),
        tf.dtypes.cast(epsilonConst, gradient.dataType()),
        gradient);
  }

  /**
   * Gathers up the update operations into a single op that can be used as a run target.
   *
   * <p>Adds the betaOne and betaTwo updates to the end of the updates list.
   *
   * @param updateOperations The update operations.
   * @param name The name of the run target.
   * @return A NoOp with a control dependency on each update operation.
   */
  @Override
  protected Op finish(List<Op> updateOperations, String name) {
    updateOperations.add(tf.assign(betaOnePower, tf.math.mul(betaOnePower, betaOneConst)));
    updateOperations.add(tf.assign(betaTwoPower, tf.math.mul(betaTwoPower, betaTwoConst)));
    return super.finish(updateOperations, name);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "Adam{"
        + "learningRate="
        + learningRate
        + ", betaOne="
        + betaOne
        + ", betaTwo="
        + betaTwo
        + ", epsilon="
        + epsilon
        + '}';
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return DEFAULT_NAME;
  }
}
