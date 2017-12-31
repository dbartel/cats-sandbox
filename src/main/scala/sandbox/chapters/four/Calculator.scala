package sandbox.chapters.four

import cats.data.State
import scala.util.Try
import cats.syntax.applicative._



object Calculator {

  type CalcState[A] = State[List[Int], A]
  type MathOperation = (Int, Int) => Int

  private def isNumber(x: String) = Try { x.toInt }.isSuccess


  private val add: PartialFunction[String, MathOperation] = { case "+" => (a, b) => a + b }
  private val subtract: PartialFunction[String, MathOperation] = { case "-" => (a, b) => a - b }
  private val multiply: PartialFunction[String, MathOperation] = { case "*" => (a,b) => a * b }
  private val divide: PartialFunction[String, MathOperation] = { case "/" => (a,b) => a / b }
  private val failOut: PartialFunction[String, MathOperation] = { case _ => throw new IllegalStateException("Bad operator") }

  val mathOperation: PartialFunction[String, MathOperation] = add orElse subtract orElse multiply orElse divide orElse failOut

  def evalOne(sym: String): CalcState[Int] = State[List[Int], Int] { oldStack =>
    val newStack = sym match {
      case n if isNumber(n) => n.toInt :: oldStack
      case n => mathOperation(n)(oldStack(1), oldStack(0)) :: oldStack.drop(2)
    }
    (newStack, newStack(0))
  }

  def evalAll(input: List[String]): CalcState[Int] = input.foldLeft(0.pure[CalcState]) { (a, b) => a.flatMap(_ => evalOne(b)) }


  def evalInput(input: String): Int = {
    val tokens = input.split(" ").toList
    evalAll(tokens).runA(Nil).value
  }

  

}
