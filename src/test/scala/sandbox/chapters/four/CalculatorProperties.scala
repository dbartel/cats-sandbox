package sandbox.chapters.four

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.forAllNoShrink
import org.scalacheck.Gen

object CalculatorProperties extends Properties("Calculator") {

  val posGen = Gen.choose(1, 100)

  property("add") = forAll { (a: Int, b: Int) =>
    val program = for {
      _ <- Calculator.evalOne(a.toString)
      _ <- Calculator.evalOne(b.toString)
      ans <- Calculator.evalOne("+")
    } yield ans

    program.runA(Nil).value == (a + b)
  }

  property("subtract") = forAll { (a: Int, b: Int) =>
    val program = for {
      _ <- Calculator.evalOne(a.toString)
      _ <- Calculator.evalOne(b.toString)
      ans <- Calculator.evalOne("-")
    } yield ans

    program.runA(Nil).value == (a - b)
  }

  property("multiply") = forAll { (a: Int, b: Int) =>
    val program = for {
      _ <- Calculator.evalOne(a.toString)
      _ <- Calculator.evalOne(b.toString)
      ans <- Calculator.evalOne("*")
    } yield ans

    program.runA(Nil).value == (a * b)
  }

  property("divide") = forAllNoShrink(posGen, posGen) { (a: Int, b: Int) =>
    val program = for {
      _ <- Calculator.evalOne(a.toString)
      _ <- Calculator.evalOne(b.toString)
      ans <- Calculator.evalOne("/")
    } yield ans

    program.runA(Nil).value == (a / b)
  }
}

