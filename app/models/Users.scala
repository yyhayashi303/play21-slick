package models

import scala.slick.driver.MySQLDriver.simple._

/**
 * Date: 13/01/15
 * Time: 16:15
 */
case class User(id: Int, firstName: String, lastName: String, mailAddress: String)
object Users extends Table[User]("USER") {
  def id = column[Int]("id", O PrimaryKey, O AutoInc)
  def firstName = column[String]("first_name", O DBType "varchar(20)")
  def lastName = column[String]("last_name", O DBType "varchar(20)")
  def mailAddress = column[String]("mail_address", O DBType "varchar(32)")
  def * = id ~ firstName ~ lastName ~ mailAddress <> (User.apply _, User.unapply _)
  def ins = firstName ~ lastName ~ mailAddress returning id

  def findAll()(implicit db: Database) = db.withSession {
    implicit session: Session => Query(Users).sortBy(_.id).list()
  }
  def find(id: Int)(implicit db: Database) = db.withSession {
    implicit session: Session => Query(Users).where(_.id === id).firstOption
  }
  def insert(user: User)(implicit db: Database) = db.withSession {
    implicit session: Session => Users.ins.insert((user.firstName, user.lastName, user.mailAddress))
  }
  def update(user: User)(implicit db: Database) = db.withSession {
    implicit session: Session => Users.where(_.id === user.id).update(user)
  }
  def delete(id: Int)(implicit db: Database) = db.withSession {
    implicit session: Session => Users.where(_.id === id).delete
  }
}
