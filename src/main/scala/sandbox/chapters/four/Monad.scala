
package sandbox.chapters.four

import scala.language.higherKinds

trait MyMonad[F[_]] {

  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  // Defining map through flatMap and pure
  def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value) { a =>
    pure(func(a))
  }
}

object MyMonad {
  def pure[A, F[_]](a: A)(implicit monad: MyMonad[F]) : F[A] = monad.pure(a)

  def flatMap[A, B, F[_]](value: F[A], func: A => F[B])(implicit monad: MyMonad[F]): F[B] = monad.flatMap(value)(func)

  def map[A, B, F[_]](value: F[A], func: A => B)(implicit monad: MyMonad[F]): F[B] = monad.map(value)(func)

}



