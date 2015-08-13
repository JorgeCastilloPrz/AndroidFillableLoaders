package com.github.jorgecastillo.clippingtransforms;

import android.graphics.Path;

/**
 * @author truizlop
 * @since 13/08/15
 */
public class SquareClippingTransform extends BaseClippingTransform {

    private int squareSize = 24;

    public SquareClippingTransform() { }

    public SquareClippingTransform(int squareSize) {
        this.squareSize = squareSize;
    }

    protected Path buildClippingPath() {

        Path squaresPath = new Path();
        int numSquares = (int) Math.ceil(getWidth() / (2 * squareSize));
        int startingHeight = getHeight();
        int lowerHeight = startingHeight - squareSize;
        squaresPath.moveTo(0, startingHeight);

        for (int i = 0; i < numSquares; i++) {
            squaresPath.lineTo((2 * i + 1) * squareSize, startingHeight);
            squaresPath.lineTo((2 * i + 1) * squareSize, lowerHeight);
            squaresPath.lineTo(2 * (i + 1) * squareSize, lowerHeight);
            squaresPath.lineTo(2 * (i + 1) * squareSize, startingHeight);
        }

        squaresPath.lineTo(getWidth(), startingHeight);
        squaresPath.lineTo(getWidth(), 0);
        squaresPath.lineTo(0, 0);
        squaresPath.close();

        return squaresPath;
    }

}
