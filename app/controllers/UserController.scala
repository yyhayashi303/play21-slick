package controllers

import play.api._
import play.api.mvc._
import models.{Users, User}
import scala.slick.driver.MySQLDriver.simple._
import play.api.Play.current
import play.api.db.DB

/**
 * Date: 13/01/15
 * Time: 18:35
 */
object UserController extends Controller {
  implicit lazy val database = Database.forDataSource(DB.getDataSource())

  def findAll = Action {
    val list = Users.findAll()
    list.foreach(println)
    Ok("ok")
  }
  def find = Action {
    val user = Users.find(1)
    println(user)
    Ok(user.toString)
  }
  def insert = Action {
    val user = User(0, "star", "backs", "star_backs@gmail.com")
    Users.insert(user)
    Ok("ok")
  }
  def update = Action {
    val user = User(1, "yy", "hayashi303", "test@gmail.com")
    Users.update(user)
    Ok("ok")
  }
  def delete = Action {
    Users.delete(1)
    Ok("ok")
  }
}
