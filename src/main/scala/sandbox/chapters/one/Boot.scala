package sandbox.chapters.one

import sandbox.chapters.one.CatInstances._
import sandbox.chapters.one.PrintableSyntax._
import cats.syntax.show._
import cats.syntax.eq._
import cats.instances.option._

object Boot extends App {
  val cat = Cat("Ted", 62, "Brown")

  // Using Printable Library
  Printable.print(cat)

  // Using extension from PrintableSyntax

  cat.print()

  // Using cats show
  println(cat.show)

  // Eq comparison
  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println(cat1 === cat1)
  println(cat1 === cat2)

  println(optionCat1 === optionCat1)
  println(optionCat1 === optionCat2)



}

