package sandbox.chapters.three

import cats.instances.function._
import cats.syntax.functor._

trait Codec[A] {
  self =>

  def encode(value: A) : String
  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
    def encode(value: B): String = enc.map(self.encode)(value)
    def decode(value: String) : B = (self.decode _ andThen dec)(value)
  }
}

object Codec {
  def encode[A](value: A)(implicit c: Codec[A]) : String = c.encode(value)
  def decode[A](value: String)(implicit c: Codec[A]): A = c.decode(value)
}


object CodecInstances {
  implicit val stringCodec : Codec[String] = new Codec[String] {
    def encode(value: String) = value
    def decode(value: String) = value
  }

  implicit val doubleCodec: Codec[Double] = stringCodec.imap[Double]((s:String) => s.toDouble, (d:Double) => d.toString)

  implicit def boxCodec[A](implicit codec: Codec[A]): Codec[Box[A]] = codec.imap( (a: A) => Box(a), (b: Box[A]) => b.value)

}
