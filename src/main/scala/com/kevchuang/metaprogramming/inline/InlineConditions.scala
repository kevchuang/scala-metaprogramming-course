package com.kevchuang.metaprogramming.inline

object InlineConditions:

  inline def condition(b: Boolean): String =
    inline if b then "yes" else "no"

  val positive = condition(true)

  transparent inline def conditionUnion(b: Boolean): String | Int =
    inline if b then "yes" else 0

  val aString: String = conditionUnion(true)

  inline def matcher(x: Int): String = inline x match
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case _ => "nothing"

  inline def matchOption(x: Option[Any]): String =
    inline x match
      case Some(value: String) => value
      case Some(value: Int)    => value.toString
      case None                => "nothing"
