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

import android.graphics.Path;

/**
 * @author jorge
 * @since 12/08/15
 */
public class RoundedClippingTransform extends BaseClippingTransform {

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


  protected Path buildClippingPath() {
    Path roundedPath = new Path();

    float widthDiff = getWidth() * 1f / (waveCount * 2);

    float startingHeight = getHeight();
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

    roundedPath.lineTo(getWidth() + 100, startingHeight);
    roundedPath.lineTo(getWidth() + 100, 0);
    roundedPath.lineTo(0, 0);
    roundedPath.close();

    return roundedPath;
  }
}
