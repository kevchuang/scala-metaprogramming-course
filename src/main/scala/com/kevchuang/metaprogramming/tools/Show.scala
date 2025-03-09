package com.kevchuang.metaprogramming.tools

import compiletime.*
import deriving.Mirror

trait Show[A]:
  def show(a: A): String

object Show:
  given Show[String]  = a => a
  given Show[Int]     = _.toString
  given Show[Boolean] = _.toString

  inline def showTuple[E <: Tuple, L <: Tuple](elements: E): List[String] =
    inline (elements, erasedValue[L]) match
      case (EmptyTuple, EmptyTuple) => List.empty
      case (el: (eh *: et), lab: (lh *: lt)) =>
        val (h *: t) = el
        val label    = constValue[lh]
        val value =
          summonInline[Show[eh]].show(h)
        ("" + label + ": " + value) :: showTuple[et, lt](t)

  /**
   * Necessary for type class derivation
   * Signature
   *  - requires no arg list of its own
   *  - must return a Show[A]
   */
  inline def derived[A <: Product](using m: Mirror.ProductOf[A]): Show[A] =
    (value: A) =>
      val valueTuple = Tuple.fromProductTyped(value)
      val fieldReprs =
        showTuple[m.MirroredElemTypes, m.MirroredElemLabels](valueTuple)
      fieldReprs.mkString("{", ",", "}")
