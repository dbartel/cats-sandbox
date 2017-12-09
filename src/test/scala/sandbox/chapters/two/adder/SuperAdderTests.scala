package sandbox.chapters.two.adder

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import cats.instances.int._
import cats.instances.option._


object SuperAdderSpecification extends Properties("SuperAdder[Int]") {
  property("add") = forAll { (list: List[Int]) =>
    SuperAdder.add(list) == list.sum
  }

  property("optional") = forAll { (list: List[Option[Int]]) =>
    val result = if (list.flatten.isEmpty) None else Some(list.flatten.sum)
    SuperAdder.add(list) == result
  }
}

object SuperAdderOrderSpec extends Properties("SuperAdder[Order]") {
  import sandbox.chapters.two.adder.OrderImplicits._
  import org.scalacheck.Gen
  import org.scalacheck.Arbitrary
  import org.scalacheck.Arbitrary._

  def genOrder : Gen[Order] = for {
    amount <- arbitrary[Double]
    quantity <- arbitrary[Double]
  } yield Order(amount, quantity)

  def genOrderList : Gen[List[Order]] = Gen.sized { size =>
    Gen.listOfN(size, genOrder)
  }

  implicit val arbritraryOrder : Arbitrary[List[Order]] = Arbitrary(genOrderList)

  property("add") = forAll { (list: List[Order]) =>
    SuperAdder.add(list) == list.fold(Order(0,0)) { (a, b) => Order(a.totalCost + b.totalCost, a.quantity + b.quantity) }
  }

}

