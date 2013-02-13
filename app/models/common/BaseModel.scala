package models.common

import play.api.Play.current
import play.api.db.DB
import slick.driver.ExtendedProfile

/**
 * Date: 13/02/12
 * Time: 21:01
 */
object BaseModel extends Profile {
  lazy val profile: ExtendedProfile = getProfile
  import profile.simple._

  def withSession[T](f: (Session => T)): T = Database.forDataSource(DB.getDataSource()).withSession(f)

  abstract class BaseTable[T](name: String) extends Table[T](name)
}
