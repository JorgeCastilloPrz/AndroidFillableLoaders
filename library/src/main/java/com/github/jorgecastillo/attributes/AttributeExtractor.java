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
package com.github.jorgecastillo.attributes;

import com.github.jorgecastillo.clippingtransforms.ClippingTransform;

/**
 * Any attribute extractor must implement this contract.
 *
 * @author jorge
 * @since 11/08/15
 */
public interface AttributeExtractor {

  int getStrokeColor();

  int getFillColor();

  int getStrokeWidth();

  int getOriginalWidth();

  int getOriginalHeight();

  int getStrokeDrawingDuration();

  int getFillDuration();

  ClippingTransform getClippingTransform();

  void recycleAttributes();

  void release();
}
