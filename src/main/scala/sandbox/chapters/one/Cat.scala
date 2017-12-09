package sandbox.chapters.one

import cats.Show
import cats.Eq
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.string._


// Defining our "Cat" model
final case class Cat(name: String, age: Int, color: String)


object CatInstances {

  // Our printable typeclass instance
  implicit object PrintableCat extends Printable[Cat] {
    def format(cat: Cat) : String = s"${cat.name} is a ${cat.age} year-old ${cat.color} cat"
  }

  // cats Show instance using helper
  implicit val catShow : Show[Cat] = Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat")

  /*
   Without helper sugar, it'd look something like this:

   implicit val catShow: Show[Cat] = new Show[Cat] {
      def show(cat: Cat) : String = ???
   }

   */

  // cats eq instance
  implicit val catEq : Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) => cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat1.color }

}
