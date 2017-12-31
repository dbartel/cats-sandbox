package sandbox.chapters.four

import cats.Monad


sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)

  def leaf[A](value: A) : Tree[A] = Leaf(value)

}

object TreeInstances {
  implicit val treeMonad = new Monad[Tree] {
    def flatMap[A,B](tree: Tree[A])(fn: A => Tree[B]): Tree[B] = tree match {
      case Leaf(a) => fn(a)
      case Branch(a,b) => Branch(flatMap(a)(fn), flatMap(b)(fn))
    }

    def pure[A](value: A) : Tree[A] = Leaf(value)


    def tailRecM[A,B](a: A)(fn: A => Tree[Either[A,B]]): Tree[B] = fn(a) match {
      case Leaf(Left(a)) => tailRecM(a)(fn)
      case Leaf(Right(b)) => pure(b)
      case Branch(l, r) => Branch(flatMap(l) {
        case Left(a) => tailRecM(a)(fn)
        case Right(b) => pure(b)
      }, flatMap(r) {
        case Left(a) => tailRecM(a)(fn)
        case Right(b) => pure(b)
      })
    }
  }

}
