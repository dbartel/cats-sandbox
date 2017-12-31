
package sandbox.chapters.four

object IdInstances {

  type Id[A] = A

  implicit val idMyMonad: MyMonad[Id] = new MyMonad[Id] {
    def pure[A](a: A): Id[A] = a

    def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)
  }

}

