package sandbox.chapters.one

// Printable typeclass
trait Printable[A] {
  def format(a: A) : String
}

// Default instances for String, Int
object PrintableInstances {

  implicit object PrintableString extends Printable[String] {
    def format(s: String) : String = s.toString
  }

  implicit object PrintableInt extends Printable[Int] {
    def format(s: Int) : String = s.toString
  }

}

// Library for using printables
object Printable {
  def format[A](a: A)(implicit printer: Printable[A]) : String = printer.format(a)
  def print[A](a: A)(implicit printer: Printable[A]): Unit = println(printer.format(a))
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format()(implicit printer: Printable[A]): String = printer.format(value)
    def print()(implicit printer: Printable[A]): Unit = println(printer.format(value))

  }

}
