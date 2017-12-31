package sandbox.chapters.four

import org.scalatest._

class DbreaderSpec extends WordSpec {
  lazy val users = Map(
    (1 -> "test"),
    (2 -> "user"))

  lazy val passwords = Map(
    "test" -> "abc",
    "user" -> "123")

  lazy val db = Db(
    users,
    passwords
  )

  "#findUsername" when {
    "valid" should {
      "return a user" in {
        val result = Dbreader.findUsername(1).run(db)
        assert(result == Some("test"))
      }
    }

    "invalid" should {
      "return none" in {
        val result = Dbreader.findUsername(-1).run(db)
        assert(result == None)
      }
    }
  }

  "#checkPassword" when {
  "valid" should {
      "return true" in {
        val result = Dbreader.checkPassword("user", "123").run(db)
        assert(result == true)
      }
    }

    "invalid" should {
      "return false" in {
        val result = Dbreader.checkPassword("user", "bad").run(db)
        assert(result == false)
      }
    }
  }

  "#checkLogin" when {
    "valid" should {
      "return true" in {
        val result = Dbreader.checkLogin(1, "abc").run(db)
        assert(result == true)
      }
    }

    "invalid password" should {
      "return false" in {
        val result = Dbreader.checkLogin(2, "abc").run(db)
        assert(result == false)
      }
    }

    "invalid user" should {
      "return false" in {
        val result = Dbreader.checkLogin(-1, "abc").run(db)
        assert(result == false)
      }
    }        
  }




}
