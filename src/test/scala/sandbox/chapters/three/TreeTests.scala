package sandbox.chapters.three

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import cats.Functor

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary._

object TreeGenerator {
  def genLeaf(): Gen[Leaf[Int]] = for {
    value <- arbitrary[Int]
  } yield Leaf(value)

  def genBranch(l: Int) : Gen[Branch[Int]] = for {
    left <- genTree(l + 1)
    right <- genTree(l + 1)
  } yield Branch(left, right)

  def genTree(l: Int = 0): Gen[Tree[Int]] = l match {
    case n if n > 3 => genLeaf
    case _ => Gen.oneOf(genLeaf, genBranch(l))
  }

  implicit val arbitraryTree: Arbitrary[Tree[Int]] = Arbitrary(genTree(0))

}

object TreeTests extends Properties("TreeFunctor") {
  import sandbox.chapters.three.TreeImplicits.treeFunctor
  import sandbox.chapters.three.TreeGenerator.arbitraryTree


  property("identity") = forAll { (t: Tree[Int]) =>
    Functor[Tree].map(t) { l => l } == t
  }

  property("map") = forAll { (t: Tree[Int]) =>
    Functor[Tree].map(t) { l => l.toString } match {
      case t: Tree[String] => true
      case _ => false
    }
  }

}
