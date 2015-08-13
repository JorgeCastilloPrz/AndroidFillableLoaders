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
import java.util.Random;

/**
 * @author jorge
 * @since 12/08/15
 */
public class WavesClippingTransform implements ClippingTransform {

  private int width, height;
  private Path wavesPath;
  private int currentWave = 0;

  @Override public void transform(Canvas canvas, float currentFillPhase, View view) {
    cacheDimensions(view.getWidth(), view.getHeight());
    buildClippingPath();
    wavesPath.offset(0, height * -currentFillPhase);
    canvas.clipPath(wavesPath, Region.Op.DIFFERENCE);
  }

  private void cacheDimensions(int width, int height) {
    if (this.width == 0 || this.height == 0) {
      this.width = width;
      this.height = height;
    }
  }

  private void buildClippingPath() {
    wavesPath = new Path();
    buildWaveAtIndex(currentWave++ % 128, 128);
  }

  private void buildWaveAtIndex(int index, int waveCount) {

    float startingHeight = height - 20;
    boolean initialOrLast = (index == 1 || index == waveCount);

    float xMovement = (width * 1f / waveCount) * index;
    float divisions = 8;
    float variation = 10;

    wavesPath.moveTo(-width, startingHeight);

    // First wave
    if (!initialOrLast) {
      variation = randomFloat();
    }

    wavesPath.quadTo(-width + width * 1f / divisions + xMovement, startingHeight + variation,
        -width + width * 1f / 4 + xMovement, startingHeight);

    if (!initialOrLast) {
      variation = randomFloat();
    }

    wavesPath.quadTo(-width + width * 1f / divisions * 3 + xMovement, startingHeight - variation,
        -width + width * 1f / 2 + xMovement, startingHeight);

    // Second wave
    if (!initialOrLast) {
      variation = randomFloat();
    }

    wavesPath.quadTo(-width + width * 1f / divisions * 5 + xMovement, startingHeight + variation,
        -width + width * 1f / 4 * 3 + xMovement, startingHeight);

    if (!initialOrLast) {
      variation = randomFloat();
    }

    wavesPath.quadTo(-width + width * 1f / divisions * 7 + xMovement, startingHeight - variation,
        -width + width + xMovement, startingHeight);

    // Third wave
    if (!initialOrLast) {
      variation = randomFloat();
    }

    wavesPath.quadTo(width * 1f / divisions + xMovement, startingHeight + variation,
        width * 1f / 4 + xMovement, startingHeight);

    if (!initialOrLast) {
      variation = randomFloat();
    }

    wavesPath.quadTo(width * 1f / divisions * 3 + xMovement, startingHeight - variation,
        width * 1f / 2 + xMovement, startingHeight);

    // Forth wave
    if (!initialOrLast) {
      variation = randomFloat();
    }

    wavesPath.quadTo(width * 1f / divisions * 5 + xMovement, startingHeight + variation,
        width * 1f / 4 * 3 + xMovement, startingHeight);

    if (!initialOrLast) {
      variation = randomFloat();
    }

    wavesPath.quadTo(width * 1f / divisions * 7 + xMovement, startingHeight - variation,
        width + xMovement, startingHeight);

    // Closing path
    wavesPath.lineTo(width + 100, startingHeight);
    wavesPath.lineTo(width + 100, 0);
    wavesPath.lineTo(0, 0);
    wavesPath.close();
  }

  private float randomFloat() {
    return nextFloat(10) + height * 1f / 25;
  }

  private float nextFloat(float upperBound) {
    Random random = new Random();
    return (Math.abs(random.nextFloat()) % (upperBound + 1));
  }
}
