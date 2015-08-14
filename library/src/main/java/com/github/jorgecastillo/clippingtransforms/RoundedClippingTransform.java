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
public class RoundedClippingTransform implements ClippingTransform {

  private int width, height;
  private Path roundedPath;
  private float roundedEdgeHeight = 8f;
  private int waveCount = 32;

  public RoundedClippingTransform() {
  }

  /**
   * If the user wants to set the transform by code he will be able to setup the edge height and
   * the number of waves to have.
   */
  public RoundedClippingTransform(float roundedEdgeHeight, int waveCount) {
    this.roundedEdgeHeight = roundedEdgeHeight;
    this.waveCount = waveCount;
  }

  @Override public void transform(Canvas canvas, float currentFillPhase, View view) {
    cacheDimensions(view.getWidth(), view.getHeight());
    buildClippingPath();
    roundedPath.offset(0, height * -currentFillPhase);
    canvas.clipPath(roundedPath, Region.Op.DIFFERENCE);
  }

  private void cacheDimensions(int width, int height) {
    if (this.width == 0 || this.height == 0) {
      this.width = width;
      this.height = height;
    }
  }

  private void buildClippingPath() {
    roundedPath = new Path();

    float widthDiff = width * 1f / (waveCount * 2);

    float startingHeight = height;
    roundedPath.moveTo(0, startingHeight);

    float nextCPX = widthDiff;
    float nextCPY = startingHeight + roundedEdgeHeight;
    float nextX = nextCPX + widthDiff;
    float nextY = startingHeight;

    for (int i = 0; i < waveCount; i++) {
      roundedPath.quadTo(nextCPX, nextCPY, nextX, nextY);
      nextCPX = nextX + widthDiff;
      nextCPY =
          (i % 2 != 0) ? startingHeight + roundedEdgeHeight : startingHeight - roundedEdgeHeight;
      nextX = nextCPX + widthDiff;
    }

    roundedPath.lineTo(width + 100, startingHeight);
    roundedPath.lineTo(width + 100, 0);
    roundedPath.lineTo(0, 0);
    roundedPath.close();
  }
}
