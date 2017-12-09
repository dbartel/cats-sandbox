package sandbox.chapters.two.adder

import cats.Monoid
import cats.syntax.semigroup._
import cats.instances.double._

object SuperAdder {
  // Int only adder
  // def add(items: List[Int]) : Int = items.fold(Monoid[Int].empty) { (i,j)  => i |+| j }

  def add[A](items: List[A])(implicit monoid: Monoid[A]) = items.fold(Monoid[A].empty)(_ |+| _)
}


case class Order(totalCost: Double, quantity: Double)

object OrderImplicits {
  implicit val OrderMonoid : Monoid[Order] = new Monoid[Order] {
    def combine(x: Order, y: Order) = Order(x.totalCost |+| y.totalCost, x.quantity |+| y.quantity)
    def empty = Order(Monoid[Double].empty, Monoid[Double].empty)
  }
}
