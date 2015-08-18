# CHANGELOG

## 1.02

* Two new `ClippingTransform` items have been added. `BitesClippingTransform` and `SquareClippingTransform`.
* A new **prefix** have been appended to custom attributes to avoid conflicts with other libraries. All
the attributes have now a `fl_` prefix, so the syntax is now like `fl_strokeWidth="2dp"`.
* I made the `State` class public in order to let users use it's values to compare `State` integer value
returned by the `OnStateChangeListener`.