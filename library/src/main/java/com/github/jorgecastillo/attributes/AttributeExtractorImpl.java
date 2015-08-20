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

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.github.jorgecastillo.clippingtransforms.ClippingTransform;
import com.github.jorgecastillo.clippingtransforms.TransformAbstractFactory;
import com.github.jorgecastillo.clippingtransforms.TransformFactoryImpl;
import com.github.jorgecastillo.library.R;
import java.lang.ref.WeakReference;

/**
 * Attribute parser abstraction to avoid ugly nomenclature into the main view class.
 *
 * @author jorge
 * @since 11/08/15
 */
public class AttributeExtractorImpl implements AttributeExtractor {

  private WeakReference<Context> weakContext;
  private WeakReference<AttributeSet> weakAttrs;
  private WeakReference<TypedArray> weakAttributeArray;
  private TransformAbstractFactory transformFactory;

  private AttributeExtractorImpl(WeakReference<Context> weakContext,
      WeakReference<AttributeSet> weakAttrs) {

    this.weakContext = weakContext;
    this.weakAttrs = weakAttrs;
    transformFactory = new TransformFactoryImpl();
  }

  private Context context() {
    return weakContext.get();
  }

  private TypedArray attributeArray() {
    if (weakAttributeArray == null) {
      weakAttributeArray = new WeakReference<>(context().getTheme()
          .obtainStyledAttributes(weakAttrs.get(), R.styleable.FillableLoader, 0, 0));
    }

    return weakAttributeArray.get();
  }

  @Override public int getStrokeColor() {
    return attributeArray().getColor(R.styleable.FillableLoader_fl_strokeColor,
        context().getResources().getColor(R.color.strokeColor));
  }

  @Override public int getFillColor() {
    return attributeArray().getColor(R.styleable.FillableLoader_fl_fillColor,
        context().getResources().getColor(R.color.fillColor));
  }

  @Override public int getStrokeWidth() {
    return attributeArray().getDimensionPixelSize(R.styleable.FillableLoader_fl_strokeWidth, context().
        getResources().getDimensionPixelSize(R.dimen.strokeWidth));
  }

  @Override public int getOriginalWidth() {
    return attributeArray().getInteger(R.styleable.FillableLoader_fl_originalWidth, -1);
  }

  @Override public int getOriginalHeight() {
    return attributeArray().getInteger(R.styleable.FillableLoader_fl_originalHeight, -1);
  }

  @Override public int getStrokeDrawingDuration() {
    return attributeArray().getInteger(R.styleable.FillableLoader_fl_strokeDrawingDuration,
        context().getResources().getInteger(R.integer.strokeDrawingDuration));
  }

  @Override public int getFillDuration() {
    return attributeArray().getInteger(R.styleable.FillableLoader_fl_fillDuration,
        context().getResources().getInteger(R.integer.fillDuration));
  }

  @Override public ClippingTransform getClippingTransform() {
    int value = attributeArray().getInteger(R.styleable.FillableLoader_fl_clippingTransform, 0);
    return transformFactory.getClippingTransformFor(value);
  }

  @Override public void recycleAttributes() {
    if (weakAttributeArray != null) {
      weakAttributeArray.get().recycle();
    }
  }

  @Override public void release() {
    weakAttributeArray = null;
    weakContext = null;
    weakAttrs = null;
  }

  public static class Builder {

    private WeakReference<Context> weakContext;
    private WeakReference<AttributeSet> weakAttrs;

    public Builder with(Context context) {
      if (context == null) {
        throw new IllegalArgumentException("Context must not be null!");
      }
      weakContext = new WeakReference<Context>(context);
      return this;
    }

    public Builder with(AttributeSet attributeSet) {
      if (attributeSet == null) {
        throw new IllegalArgumentException("Attribute set must not be null!");
      }
      weakAttrs = new WeakReference<AttributeSet>(attributeSet);
      return this;
    }

    public AttributeExtractorImpl build() {
      return new AttributeExtractorImpl(weakContext, weakAttrs);
    }
  }
}