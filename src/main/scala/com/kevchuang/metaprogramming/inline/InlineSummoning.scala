package com.kevchuang.metaprogramming.inline

import scala.compiletime.summonInline
import scala.compiletime.summonFrom

object InlineSummoning:
  trait Semigroup[A]:
    def combine(a: A, b: A): A

  def doubleSimple[A: Semigroup](a: A): A =
    summon[Semigroup[A]].combine(a, a)

  inline def double[A](a: A): A =
    summonInline[Semigroup[A]].combine(a, a)

  given Semigroup[Int] = _ + _
  val four             = double(2)

  // condition summoning
  trait Messenger[A]:
    def message: String

  given Messenger[Int] with
    def message: String = "this is an int speaking"

  inline def produceMessage[A]: String =
    summonFrom:
      case m: Messenger[A] => "Found messenger: " + m.message
      case _               => "No messenger found for this type"

  val intMessage = produceMessage[Int]
  val stringMessage = produceMessage[String]