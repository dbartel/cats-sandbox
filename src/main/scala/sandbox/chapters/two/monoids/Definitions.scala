package sandbox.chapters.two.monoids

// A semigroup is something that can be combined (e.g. Plus.combine(1,2) == 3)
trait Semigroup[A] {
  def combine(x: A, y: A): A
}

// A Monoid is a semigroup with an empty property (e.g. Plus.empty = 0)
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) = monoid
}

object MonoidLaws {
  def identity[A](a: A)(implicit monoid: Monoid[A]): Boolean = {
    (monoid.combine(a, monoid.empty) == a) && (monoid.combine(monoid.empty, a) == a)
  }

  def associative[A](a: A, b: A, c: A)(implicit monoid: Monoid[A]) : Boolean =
    monoid.combine(a, monoid.combine(b, c)) == monoid.combine(monoid.combine(a, b), c)

}

// Correctnes proofs in tests
object BooleanMonoids {
  implicit val AndBooleanMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def combine(x: Boolean, y: Boolean) : Boolean =  x && y
    def empty: Boolean = true
  }

  implicit val OrBooleanMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def combine(x: Boolean, y: Boolean) : Boolean = x || y
    def empty: Boolean = false
  }

  implicit val ExclusiveBooleanMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def combine(x: Boolean, y: Boolean) : Boolean = (x && !y) || (y && !x)
    def empty: Boolean = false
  }

  implicit val ExlusiveNorBooleanMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def combine(x: Boolean, y: Boolean) : Boolean = (!x || y) && (x || !y)
    def empty: Boolean = true
  }
}



