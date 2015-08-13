package com.github.jorgecastillo.utils;

/**
 * Constrains a value between a min and a maximum to be able to normalize it for coordinate
 * mapping.
 *
 * @author jorge
 * @since 7/08/15
 */
public class MathUtil {
  public static float constrain(float min, float max, float v) {
    return Math.max(min, Math.min(max, v));
  }
}
