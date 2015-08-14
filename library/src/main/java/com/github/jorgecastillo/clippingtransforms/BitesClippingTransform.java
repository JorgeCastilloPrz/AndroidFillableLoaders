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
 * @author truizlop
 * @since 12/08/15
 */
public class BitesClippingTransform implements ClippingTransform {

  private float roundedEdgeHeight = 32f;
  private int waveCount = 8;
  private int width, height;

  public BitesClippingTransform() {
  }

  public BitesClippingTransform(float roundedEdgeHeight, int waveCount) {
    this.roundedEdgeHeight = roundedEdgeHeight;
    this.waveCount = waveCount;
  }

  @Override public void transform(Canvas canvas, float currentFillPhase, View view) {
    cacheDimensions(view.getWidth(), view.getHeight());
    Path path = buildClippingPath();
    path.offset(0, height * -currentFillPhase);
    canvas.clipPath(path, Region.Op.DIFFERENCE);
  }

  private void cacheDimensions(int width, int height) {
    if (this.width == 0 || this.height == 0) {
      this.width = width;
      this.height = height;
    }
  }

  protected Path buildClippingPath() {
    Path roundedPath = new Path();

    float widthDiff = width * 1f / (waveCount * 2);

    float startingHeight = height;
    roundedPath.moveTo(0, startingHeight);

    float nextCPX = widthDiff;
    float nextCPY = startingHeight + roundedEdgeHeight;
    float nextX = nextCPX + widthDiff;

    for (int i = 0; i < waveCount; i++) {
      roundedPath.quadTo(nextCPX, nextCPY, nextX, startingHeight);
      nextCPX = nextX + widthDiff;
      nextX = nextCPX + widthDiff;
    }

    roundedPath.lineTo(height + 100, startingHeight);
    roundedPath.lineTo(height + 100, 0);
    roundedPath.lineTo(0, 0);
    roundedPath.close();

    return roundedPath;
  }
}
