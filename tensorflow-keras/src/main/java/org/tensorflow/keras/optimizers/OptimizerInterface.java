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
package org.tensorflow.keras.optimizers;

import org.tensorflow.Graph;
import org.tensorflow.op.Ops;

import java.util.Map;

/** The main Interface for Keras Optimizers */
public interface OptimizerInterface {

  /** The value for the name key in the Config object */
  String NAME_KEY = "name";

  /**
   * Get a TensorFlow Graph from the Ops.
   *
   * @param tf the TensorFlow Ops
   * @return the graph
   * @throws java.lang.IllegalArgumentException if the TensorFlow Ops does not represent Graph mode
   */
  static Graph assertGraph(Ops tf) {
    if (!tf.scope().env().isGraph()) {
      throw new IllegalArgumentException(
          "Invalid environment, Optimizers can only be used in Graph Mode");
    }
    return (Graph) tf.scope().env();
  }

  /**
   * Return the config object used to initialize the Optimizer
   *
   * @return the config object used to initialize the Optimizer
   */
  Map<String, Object> getConfig();
}
