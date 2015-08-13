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

import android.graphics.Canvas;
import android.view.View;

/**
 * Totally plain clipping transform to achieve a basic plain effect on the filling figure. This
 * mode would be the default one.
 *
 * @author jorge
 * @since 12/08/15
 */
public class PlainClippingTransform implements ClippingTransform {

  @Override public void transform(Canvas canvas, float currentFillPhase, View view) {
    canvas.clipRect(0, (view.getBottom() - view.getTop()) * (1f - currentFillPhase),
        view.getRight(), view.getBottom());
  }
}