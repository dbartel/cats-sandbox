package sandbox.chapters.four

import cats.data._
import cats.syntax.applicative._

case class Db(
  usernames: Map[Int, String],
  passwords: Map[String, String]
)


object Dbreader {

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int) : DbReader[Option[String]] = Reader(db => db.usernames get userId)

  def checkPassword(username: String, password: String): DbReader[Boolean] = Reader(db => (db.passwords get username).map { p => p == password } getOrElse false )

  def checkLogin(userId: Int, password: String): DbReader[Boolean] = for {
    username <- findUsername(userId)
    validPassword <- username.map { u => checkPassword(u, password) }.getOrElse { false.pure[DbReader] }
  } yield validPassword


}
