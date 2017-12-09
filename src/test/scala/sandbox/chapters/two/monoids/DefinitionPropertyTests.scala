package sandbox.chapters.two.monoids

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll


object AndBooleanSpecification extends Properties("AndBooleanMonoid") {
  import sandbox.chapters.two.monoids.BooleanMonoids.AndBooleanMonoid

  property("identity") = forAll { (a: Boolean) =>
    MonoidLaws.identity(a)
  }

  property("associative") = forAll { (a: Boolean, b: Boolean, c: Boolean) =>
    MonoidLaws.associative(a, b, c)
  }
}


object OrBooleanSpecification extends Properties("OrBooleanMonoid") {
  import sandbox.chapters.two.monoids.BooleanMonoids.OrBooleanMonoid

  property("identity") = forAll { (a: Boolean) =>
    MonoidLaws.identity(a)
  }

  property("associative") = forAll { (a: Boolean, b: Boolean, c: Boolean) =>
    MonoidLaws.associative(a, b, c)
  }
}

object ExclusiveBooleanSpecification extends Properties("ExclusiveBooleanMonoid") {
  import sandbox.chapters.two.monoids.BooleanMonoids.ExclusiveBooleanMonoid

  property("identity") = forAll { (a: Boolean) =>
    MonoidLaws.identity(a)
  }

  property("associative") = forAll { (a: Boolean, b: Boolean, c: Boolean) =>
    MonoidLaws.associative(a, b, c)
  }
}

object ExclusiveNorBooleanSpecification extends Properties("ExclusiveNorBooleanMonoid") {
  import sandbox.chapters.two.monoids.BooleanMonoids.ExlusiveNorBooleanMonoid

  property("identity") = forAll { (a: Boolean) =>
    MonoidLaws.identity(a)
  }

  property("associative") = forAll { (a: Boolean, b: Boolean, c: Boolean) =>
    MonoidLaws.associative(a, b, c)
  }
}




