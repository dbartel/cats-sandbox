package sandbox.chapters.three

import cats.Functor

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]


object TreeImplicits {
  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    def map[A, B](value: Tree[A])(func: A => B) : Tree[B] = value match {
      case Leaf(a) => Leaf(func(a))
      case Branch(l, r) => Branch(map(l)(func), map(r)(func))
    }
  }

}
