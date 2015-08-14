/*
 * Copyright (C) 2015 Jorge Castillo Pérez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jorgecastillo.clippingtransforms;

/**
 * @author jorge
 * @since 12/08/15
 */
public class TransformFactoryImpl implements TransformAbstractFactory {

  @Override public ClippingTransform getClippingTransformFor(int value) {
    switch (value) {
      case FillMode.PLAIN:
        return new PlainClippingTransform();
      case FillMode.SPIKES:
        return new SpikesClippingTransform();
      case FillMode.ROUNDED:
        return new RoundedClippingTransform();
      case FillMode.SQUARES:
        return new SquareClippingTransform();
      case FillMode.BITES:
        return new BitesClippingTransform();
      default:
        return new WavesClippingTransform();
    }
  }
}
