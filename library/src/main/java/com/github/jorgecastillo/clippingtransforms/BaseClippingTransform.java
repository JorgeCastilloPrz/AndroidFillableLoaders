package com.github.jorgecastillo.clippingtransforms;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.view.View;

/**
 * @author truizlop
 * @since 13/08/2015
 */
public abstract class BaseClippingTransform implements ClippingTransform{

    private int width, height;

    @Override
    public void transform(Canvas canvas, float currentFillPhase, View view) {
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

    protected abstract Path buildClippingPath();

    protected int getWidth(){
        return width;
    }

    protected int getHeight(){
        return height;
    }
}
