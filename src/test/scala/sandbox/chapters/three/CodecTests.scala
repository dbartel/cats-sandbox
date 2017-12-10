package sandbox.chapters.three

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object CodecDoubleTests extends Properties("Codec[Double]") {
  import sandbox.chapters.three.CodecInstances._

  property("encode") = forAll { (d: Double) =>
    Codec.encode(d) == d.toString
  }

  property("decode") = forAll { (d: Double) =>
    val s = d.toString
    Codec.decode[Double](s) == s.toDouble
  }

}

object CodecBoxTests extends Properties("Codec[Box[Double]]") {
  import sandbox.chapters.three.CodecInstances._

  property("encode") = forAll { (d: Double) =>
    Codec.encode(Box(d)) == d.toString
  }

  property("decode") = forAll { (d: Double) =>
    val s = d.toString
    Codec.decode[Box[Double]](s) == Box(d)
  }

}
