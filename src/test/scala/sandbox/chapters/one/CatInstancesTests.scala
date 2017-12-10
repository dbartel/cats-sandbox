package sandbox.chapters.one

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import cats.syntax.eq._
import cats.instances.string._

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary._

// Testing these instances is a little silly, but it's "a fun exercise" in the context of this chapter

object CatGenerator {
  def genCat: Gen[Cat] = for {
    name <- arbitrary[String]
    age <- arbitrary[Int]
    color <- arbitrary[String]
  } yield Cat(name, age, color)

  implicit val arbitraryCat = Arbitrary(genCat)  

}

object CatEqTests extends Properties("CatEq") {
  import sandbox.chapters.one.CatInstances.catEq
  import sandbox.chapters.one.CatGenerator.arbitraryCat

  property("equality") = forAll { (a: Cat) =>
    a === a
  }

  property("non-equality") = forAll { (a: Cat) =>
    a =!= a.copy(name = (a.name + "-nonequal"))
  }

}

object PrintableCatTests extends Properties("CatPrintable") {
  import sandbox.chapters.one.Printable
  import sandbox.chapters.one.CatInstances.PrintableCat
  import sandbox.chapters.one.CatGenerator.arbitraryCat

  property("format") = forAll { (cat: Cat) =>
    Printable.format(cat) === s"${cat.name} is a ${cat.age} year-old ${cat.color} cat"
  }

}
