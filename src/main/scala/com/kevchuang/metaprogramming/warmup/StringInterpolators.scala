package com.kevchuang.metaprogramming.warmup

object StringInterpolators:
  final case class Person(name: String, age: Int)

  def stringToPerson(line: String): Person =
    val tokens = line.split(",")
    Person(tokens(0), tokens(1).toInt)

  extension (sc: StringContext)
    def person(args: Any*): Person =
      val concat = sc.s(args*)
      stringToPerson(concat)

  def main(args: Array[String]): Unit =
    val person: Person = person"Kevin,28"
    println(person)

