package com.kevchuang.metaprogramming.inline

object CompileTimeErrors:
  inline def compileTimeError(x: Int): Int =
    compiletime.error("this should fail to compile")

// this fails with a custom error
//  val three = compileTimeError(3)

  inline def pmWithCTError(x: Option[Any]): String =
    inline x match
      case Some(v: Int) => v.toString
      case Some(v: String) => v
      case None => "nothing"
      case _ => compiletime.error("this value is not supported: only Option[Int] or Option[String]")

  inline def properCTError(x: String) =
    compiletime.error(s"error with " + x)
