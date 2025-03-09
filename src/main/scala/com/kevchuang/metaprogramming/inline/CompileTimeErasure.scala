package com.kevchuang.metaprogramming.inline

import compiletime.erasedValue
import compiletime.constValue
import scala.compiletime.ops.int.*

object CompileTimeErasure:
  inline def pmOnType[A] =
    inline erasedValue[A] match
      case _: String => "a string type"
      case _: Int    => "an integer type"
      case _         => "not supported type"

  val messageString = pmOnType[String]  // a string type
  val messageInt    = pmOnType[Int]     // an integer type
  val messageSmth   = pmOnType[Boolean] // not supported type

  // cannot use erasedValue[A] at runtime
  // only used for inline expressions
  inline def returnToRuntime[A] =
    inline erasedValue[A] match
      case s: String => s.length

  // val aStringLength = returnToRuntime[String]

  // example of compile-time calculations
  transparent inline def factorial[N <: Int]: Int =
    inline erasedValue[N] match
      case _: 0    => 1
      case _: S[n] => constValue[n + 1] * factorial[n]

  val fac: 24 = factorial[4]
