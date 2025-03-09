package com.kevchuang.metaprogramming.inline

import com.kevchuang.metaprogramming.inline.TupleMatching.TupleMatching.showTuple
import com.kevchuang.metaprogramming.tools.Show

import scala.compiletime.summonInline

object TupleMatching:
  object TupleMatching:
    inline def showTuple[T <: Tuple](tuple: T): String =
      inline tuple match
        case EmptyTuple => ""
        case tup: (h *: t) =>
          val h *: t = tup
          summonInline[Show[h]].show(h) + " " + showTuple[t](t)

  val aTupleString = showTuple(("Scala", 2, true))
//  val thisWontCompile = showTuple(("Scala", List(1), 42))
