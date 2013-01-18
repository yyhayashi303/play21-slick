package models

import org.specs2.mutable._

import scala.slick.driver.MySQLDriver.simple._
import play.api.Play.current
import play.api.db.DB
import play.api.test._
import play.api.test.Helpers._

/**
 * Date: 13/01/15
 * Time: 19:10
 */
class UsersSpec extends Specification {

//  implicit val database = Database.forDataSource(DB.getDataSource())
  "Users" should {
    "findAll" in {
      running(FakeApplication()) {
        implicit val database = Database.forDataSource(DB.getDataSource())
        val list = models.Users.findAll()
        println(list)
      }
    }
    "find" in {
      running(FakeApplication()) {
        implicit val database = Database.forDataSource(DB.getDataSource())
        val user = models.Users.find(1)
        println(user)
      }
    }
    "insert" in {
      running(FakeApplication()) {
        implicit val database = Database.forDataSource(DB.getDataSource())
        val user = User(1, "first_test", "last_test", "test@gmail.com")
        val ret = Users.insert(user)
      }
    }
//    "update" in {
//      val user = User(1, "start", "bucks", "star_bucks@gmail.com")
//      Users.update(user)
//    }
  }
}

