
package sandbox.chapters.four

import cats._

object Fold {

  // Unsafe fold right, results in stack overflow for large lists
  def unsafeFoldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = as match {
    case head :: tail => fn(head, unsafeFoldRight(tail, acc)(fn))
    case Nil => acc
  }

  // Safer fold right using Eval.defer
  def foldRightEval[A,B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] = as match {
    case head :: tail => Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
    case Nil => acc
  }

  // Safer fold right by mapping over deferred Eval
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = foldRightEval(as, Eval.now(acc)) { (a, b) => b.map(fn(a, _)) }.value

}
