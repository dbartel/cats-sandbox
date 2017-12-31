
package sandbox.chapters.four

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

// import cats.data.Writer
// import cats.syntax.writer._
// import cats.implicits.writer._
// import cats.instances.writer._

import cats._
import cats.data._
import cats.implicits._

object Factorial {

  type Logg[A] = Writer[Vector[String], A]

  private def slowly[A](body: => A) = try body finally Thread.sleep(100)

  def factorial(n: Int) : Int = {
    val ans = if (n == 0) 1 else slowly((n * factorial(n - 1)))
    println(s"$n $ans")
    ans
  }

  def factorialW(n: Int): Logg[Int] = {
    for {
      ans <- if (n == 0) 1.pure[Logg] else slowly(factorialW(n - 1).map(_ * n))
      _ <- Vector(s"fact $n $ans").tell
    } yield ans
  }

}

object FactMain {
  def main(args: Array[String]) : Unit = {
    println("Without writer")

    Await.result(Future.sequence(Vector(
      Future(Factorial.factorial(3)),
      Future(Factorial.factorial(3)),
      Future(Factorial.factorial(3))
    )), 5.seconds)

    println("With writer")

    Await.result(Future.sequence(Vector(
      Future(println(Factorial.factorialW(3).run)),
      Future(println(Factorial.factorialW(3).run)),
      Future(println(Factorial.factorialW(3).run))
    )), 5.seconds)


  }
}
