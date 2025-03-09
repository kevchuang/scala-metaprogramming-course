package com.kevchuang.metaprogramming.inline

import com.kevchuang.metaprogramming.tools.Show
import deriving.Mirror
import compiletime.*

case class Person(name: String, age: Int, programmer: Boolean) derives Show
// compiler will look for a method `derived` in the Show object such as it returns a Show[Person]

enum Permission:
  case READ, WRITE, EXECUTE

// automatically derive Show[A] where A can be any Sum type or Product type

object Mirrors:
  // mirror for a product type
  val personMirror = summon[Mirror.Of[Person]]
  // mirror contains all type information

  val daniel: Person                 = personMirror.fromTuple("Daniel", 99, true)
  val aTuple: (String, Int, Boolean) = Tuple.fromProductTyped(daniel)

  val className =
    constValue[
      personMirror.MirroredLabel
    ] // the name of the class, known at compile time
  val fieldsNames =
    constValueTuple[personMirror.MirroredElemLabels] // names of the fields

  // mirror of sum type
  val permissionMirror = summon[Mirror.Of[Permission]]

  val allCases = constValueTuple[personMirror.MirroredElemLabels]

  // auto-derivation for a serialization type class

  val masterYoda    = Person("Master Yoda", 800, false)
  val showPerson    = Show.derived[Person]
  val showPerson_v2 = summon[Show[Person]] // implicit call
  val showPerson_v3 = Person.derived$Show
  val yodaJson      = showPerson.show(masterYoda)

  def printThing[A: Show](thing: A) =
    println(summon[Show[A]].show(thing))

  @main
  def run: Unit =
    printThing(masterYoda)
