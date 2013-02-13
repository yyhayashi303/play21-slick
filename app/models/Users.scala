package models

import common.BaseModel

/**
 * Date: 13/01/15
 * Time: 16:15
 */
case class User(id: Int, firstName: String, lastName: String, email: String)
object Users extends BaseModel.BaseTable[User]("USER") {
  import BaseModel.profile.simple._

  def id = column[Int]("id", O PrimaryKey, O AutoInc)
  def firstName = column[String]("first_name", O DBType "varchar(20)")
  def lastName = column[String]("last_name", O DBType "varchar(20)")
  def email = column[String]("email", O DBType "varchar(32)")
  def * = id ~ firstName ~ lastName ~ email <> (User.apply _, User.unapply _)
  def ins = firstName ~ lastName ~ email returning id

  def findAll()(implicit session: Session) = Query(Users).sortBy(_.id).list()
  def find(id: Int)(implicit session: Session) = Query(Users).where(_.id === id).firstOption
  def insert(user: User)(implicit session: Session) = {
    Users.ins.insert((user.firstName, user.lastName, user.email))
  }
  def update(user: User)(implicit session: Session) = {
    Users.where(_.id === user.id).update(user)
  }
  def delete(id: Int)(implicit session: Session) = {
    Users.where(_.id === id).delete
  }
}
