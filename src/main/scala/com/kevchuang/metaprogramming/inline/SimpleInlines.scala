package com.kevchuang.metaprogramming.inline

object SimpleInlines:
  inline def inc(x: Int): Int = x + 1

  val aNumber = 3
  val four    = inc(3) + 1

  inline def incia(inline x: Int): Int = x + 1

  transparent inline def wrap(x: Int): Option[Int] = Some(x)

  val anOption: Some[Int] = wrap(8)