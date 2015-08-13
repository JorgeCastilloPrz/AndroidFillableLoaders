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
package com.github.jorgecastillo;

import android.content.res.Resources;
import android.view.ViewGroup;
import com.github.jorgecastillo.clippingtransforms.ClippingTransform;
import com.github.jorgecastillo.clippingtransforms.PlainClippingTransform;
import com.github.jorgecastillo.library.R;

/**
 * Builder used to simplify code view construction. Normally i use to declare builders into
 * the classes that they are building, but this time i will make an exception to improve
 * readability.({@link FillableLoader} was getting a little bit big).
 */
public class FillableLoaderBuilder {

  private ViewGroup parent;
  private ViewGroup.LayoutParams params;

  private int strokeColor = -1;
  private int fillColor = -1;
  private int strokeWidth = -1;
  private int originalWidth = -1;
  private int originalHeight = -1;
  private int strokeDrawingDuration = -1;
  private int fillDuration = -1;
  private ClippingTransform clippingTransform;
  private String svgPath;

  public FillableLoaderBuilder parentView(ViewGroup parent) {
    this.parent = parent;
    return this;
  }

  public FillableLoaderBuilder layoutParams(ViewGroup.LayoutParams params) {
    this.params = params;
    return this;
  }

  public FillableLoaderBuilder strokeColor(int strokeColor) {
    this.strokeColor = strokeColor;
    return this;
  }

  public FillableLoaderBuilder fillColor(int fillColor) {
    this.fillColor = fillColor;
    return this;
  }

  public FillableLoaderBuilder strokeWidth(int strokeWidth) {
    this.strokeWidth = strokeWidth;
    return this;
  }

  public FillableLoaderBuilder originalDimensions(int originalWidth, int originalHeight) {
    this.originalWidth = originalWidth;
    this.originalHeight = originalHeight;
    return this;
  }

  public FillableLoaderBuilder strokeDrawingDuration(int strokeDrawingDuration) {
    this.strokeDrawingDuration = strokeDrawingDuration;
    return this;
  }

  public FillableLoaderBuilder fillDuration(int fillDuration) {
    this.fillDuration = fillDuration;
    return this;
  }

  public FillableLoaderBuilder clippingTransform(ClippingTransform transform) {
    this.clippingTransform = transform;
    return this;
  }

  public FillableLoaderBuilder svgPath(String svgPath) {
    this.svgPath = svgPath;
    return this;
  }

  public FillableLoader build() {
    Resources res = parent.getContext().getResources();
    strokeColor = strokeColor == -1 ? res.getColor(R.color.strokeColor) : strokeColor;
    fillColor = fillColor == -1 ? res.getColor(R.color.fillColor) : fillColor;
    strokeWidth = strokeWidth < 0 ? res.getDimensionPixelSize(R.dimen.strokeWidth) : strokeWidth;
    strokeDrawingDuration =
        strokeDrawingDuration < 0 ? res.getInteger(R.integer.strokeDrawingDuration)
            : strokeDrawingDuration;
    fillDuration = fillDuration < 0 ? res.getInteger(R.integer.fillDuration) : fillDuration;
    clippingTransform =
        clippingTransform == null ? new PlainClippingTransform() : clippingTransform;

    if (params == null) {
      throwArgumentException("layout params");
    }

    if (svgPath == null) {
      throwArgumentException("an svg path");
    }

    return new FillableLoader(parent, params, strokeColor, fillColor, strokeWidth, originalWidth,
        originalHeight, strokeDrawingDuration, fillDuration, clippingTransform, svgPath);
  }

  private void throwArgumentException(String neededStuff) {
    throw new IllegalArgumentException(
        "You must provide " + neededStuff + " in order to draw the view properly.");
  }
}
