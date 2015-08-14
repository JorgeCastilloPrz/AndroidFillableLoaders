package com.github.jorgecastillo.clippingtransforms;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.view.View;

/**
 * @author truizlop
 * @since 13/08/15
 */
public class SquareClippingTransform implements ClippingTransform {

  private int width, height;
  private int squareSize = 24;

  public SquareClippingTransform() {
  }

  public SquareClippingTransform(int squareSize) {
    this.squareSize = squareSize;
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

    Path squaresPath = new Path();
    int numSquares = (int) Math.ceil(width / (2 * squareSize));
    int startingHeight = height;
    int lowerHeight = startingHeight - squareSize;
    squaresPath.moveTo(0, startingHeight);

    for (int i = 0; i < numSquares; i++) {
      squaresPath.lineTo((2 * i + 1) * squareSize, startingHeight);
      squaresPath.lineTo((2 * i + 1) * squareSize, lowerHeight);
      squaresPath.lineTo(2 * (i + 1) * squareSize, lowerHeight);
      squaresPath.lineTo(2 * (i + 1) * squareSize, startingHeight);
    }

    squaresPath.lineTo(width, startingHeight);
    squaresPath.lineTo(width, 0);
    squaresPath.lineTo(0, 0);
    squaresPath.close();

    return squaresPath;
  }
}
