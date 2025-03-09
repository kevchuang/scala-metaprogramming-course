package com.kevchuang.metaprogramming.inline

import scala.compiletime.ops.any.ToString

object CompileTimeOps:
  object Ints:
    import compiletime.ops.int.*

    val two: 1 + 1      = 2
    val four: 2 * 2     = 4
    val truth: <=[3, 4] = true

  object Booleans:
    import compiletime.ops.boolean.*

    val lie: ![true]               = false
    val combination: true && false = false

  object Strings:
    import compiletime.ops.string.*

    val aLiteral: "Scala"                        = "Scala"
    val aLength: Length["Scala"]                 = 5
    val regexMatching: Matches["Scala", ".*al*"] = true

  object Values:
    import compiletime.{constValue, constValueOpt}
    import compiletime.ops.int.+
    import compiletime.ops.string.{+ as _, *}
    val five       = constValue[2 + 3]
    val five_v2    = constValue[Length["Scala"]]
    val fiveOption = constValueOpt[2 + 3]
    val fiveNone   = constValueOpt[Int]

    inline def customErrorCode[N <: Int] =
      compiletime.error("Error number: " + constValue[ToString[N]])

    //customErrorCode[6]  fails with Error number: 6
