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
import android.graphics.Path;
import android.graphics.Region;
import android.view.View;

/**
 * @author jorge
 * @since 12/08/15
 */
public class SpikesClippingTransform implements ClippingTransform {

  private int width, height;
  private Path spikesPath;

  @Override public void transform(Canvas canvas, float currentFillPhase, View view) {
    cacheDimensions(view.getWidth(), view.getHeight());
    buildClippingPath();
    spikesPath.offset(0, height * -currentFillPhase);
    canvas.clipPath(spikesPath, Region.Op.DIFFERENCE);
  }

  private void cacheDimensions(int width, int height) {
    if (this.width == 0 || this.height == 0) {
      this.width = width;
      this.height = height;
    }
  }

  private void buildClippingPath() {
    spikesPath = new Path();
    float heightDiff = width * 1f / 32;
    float widthDiff = width * 1f / 32;
    float startingHeight = height - heightDiff;

    spikesPath.moveTo(0, startingHeight);

    float nextX = widthDiff;
    float nextY = startingHeight + heightDiff;

    for (int i = 0; i < 32; i++) {
      spikesPath.lineTo(nextX, nextY);
      nextX += widthDiff;
      nextY += (i % 2 == 0) ? heightDiff : -heightDiff;
    }

    spikesPath.lineTo(width + 100, startingHeight);
    spikesPath.lineTo(width + 100, 0);
    spikesPath.lineTo(0, 0);
    spikesPath.close();
  }
}
