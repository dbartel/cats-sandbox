package sandbox.chapters.three

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object PrintableTest extends Properties("Printable") {
  import sandbox.chapters.three.PrintableInstances._
  import sandbox.chapters.three.PrintableSyntax._
  property("contramap") = forAll { (a: String, b: Int) =>
    implicit val printer: Printable[Int] = a.contramap(n => n.toString)
    b.format() == ("\"" + b.toString() + "\"")
  }
}

object BoxTest extends Properties("Box") {
  import sandbox.chapters.three.PrintableInstances._
  import sandbox.chapters.three.PrintableSyntax._
  import org.scalacheck.Gen
  import org.scalacheck.Arbitrary
  import org.scalacheck.Arbitrary._

  def genBoxedString : Gen[Box[String]] = for {
    value <- arbitrary[String]
  } yield Box(value)

  def genBoxedBool : Gen[Box[Boolean]] = for {
    value <- arbitrary[Boolean]
  } yield Box(value)

  implicit val arbitraryBoxedString = Arbitrary(genBoxedString)
  implicit val arbitraryBoxedBool = Arbitrary(genBoxedBool)


  property("printableBoxedString") = forAll  { (b: Box[String]) =>
    b.format() == ("\"" + b.value + "\"")
  }

  property("printableBoxedBoolean") = forAll { (b: Box[Boolean]) =>
    val result = if (b.value) "yes" else "no"
    b.format() == result
  }



}
