/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.optimizers;

import org.junit.jupiter.api.*;
import org.tensorflow.Graph;
import org.tensorflow.framework.utils.ND;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.tensorflow.framework.optimizers.AdaGrad.ACCUMULATOR;

/** Test cases for AdaGrad Optimizer */
public class AdaGradTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  public AdaGradTest() {}

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {}

  @AfterEach
  public void tearDown() {}

  @Test
  public void testBasic() {
    int numSteps = 3;
    float[] var0Init = {1.0F, 2.0F};
    float[] var1Init = {3.0F, 4.0F};
    float[] grads0Init = {0.1F, 0.1F};
    float[] grads1Init = {0.01F, 0.01F};
    float[] accum0 = {0.1f, 0.1f};
    float[] accum1 = {0.1f, 0.1f};

    FloatNdArray var0Np = NdArrays.vectorOf(var0Init);
    FloatNdArray var1Np = NdArrays.vectorOf(var1Init);
    FloatNdArray grads0Np = NdArrays.vectorOf(grads0Init);
    FloatNdArray grads1Np = NdArrays.vectorOf(grads1Init);
    FloatNdArray accum0Np = NdArrays.vectorOf(accum0);
    FloatNdArray accum1Np = NdArrays.vectorOf(accum1);

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Graph graph = session.getGraph();

      Shape shape0 = Shape.of(var0Init.length);
      Shape shape1 = Shape.of(var1Init.length);
      Variable<TFloat32> var0 = tf.withName("var0").variable(shape0, TFloat32.DTYPE);
      Variable<TFloat32> var1 = tf.withName("var1").variable(shape1, TFloat32.DTYPE);

      Assign<TFloat32> var0Initializer = tf.assign(var0, tf.constant(var0Init));
      Assign<TFloat32> var1Initializer = tf.assign(var1, tf.constant(var1Init));

      Constant<TFloat32> grads0 = tf.constant(grads0Init);
      Constant<TFloat32> grads1 = tf.constant(grads1Init);

      float learningRate = 3.0F;

      AdaGrad instance = new AdaGrad(graph, learningRate);

      /* build the GradsAnvVars */
      List<Optimizer.GradAndVar<? extends TType>> gradsAndVars = new ArrayList<>();
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads0.asOutput(), var0.asOutput()));
      gradsAndVars.add(new Optimizer.GradAndVar<>(grads1.asOutput(), var1.asOutput()));

      Op adaUpdate = instance.applyGradients(gradsAndVars, "AdGradTest");

      Variable<TFloat32>[] accumulatorSlots = new Variable[2];
      accumulatorSlots[0] = instance.getSlot(var0.asOutput(), ACCUMULATOR).get();
      assertEquals(accumulatorSlots[0].asOutput().shape(), var0.asOutput().shape());

      accumulatorSlots[1] = instance.getSlot(var1.asOutput(), ACCUMULATOR).get();
      assertEquals(accumulatorSlots[1].asOutput().shape(), var1.asOutput().shape());

      /* initialize the local variables */
      session.run(var0Initializer);
      session.run(var1Initializer);

      /* initialize the accumulators */
      session.run(tf.init());

      /* make sure the variables were initialized properly */
      session.evaluate(var0Init, var0);
      session.evaluate(var1Init, var1);

      for (int step = 0; step < numSteps; step++) {
        session.run(adaUpdate);

        accum0Np = caclulateAccum(accum0Np, grads0Np);
        var0Np = calculate(var0Np, accum0Np, grads0Np, learningRate);
        session.evaluate(var0Np, var0);

        accum1Np = caclulateAccum(accum1Np, grads1Np);
        var1Np = calculate(var1Np, accum1Np, grads1Np, learningRate);
        session.evaluate(var1Np, var1);
      }
    }
  }

  private FloatNdArray caclulateAccum(FloatNdArray accum, FloatNdArray grads) {
    // accum + gT * gT
    FloatNdArray squareG = ND.square(grads);
    return ND.add(accum, squareG);
  }

  private FloatNdArray calculate(
      FloatNdArray param, FloatNdArray accum, FloatNdArray grads, float learningRate) {
    // param - lr * gT / (np.sqrt(accumT) + epsilon)
    FloatNdArray divisor = ND.add(ND.sqrt(accum), 1e-07f);
    FloatNdArray dividend = ND.mul(learningRate, grads);
    FloatNdArray quotient = ND.div(dividend, divisor);
    return ND.sub(param, quotient);
  }
}