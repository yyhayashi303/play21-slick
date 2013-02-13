package models.common

import play.api.Application
import slick.driver.ExtendedProfile

/**
 * Date: 13/02/12
 * Time: 16:42
 */
trait Profile {
  val SLICK_DRIVER = "slick.db.driver"
  val DEFAULT_SLICK_DRIVER = "scala.slick.driver.H2Driver"

  def getProfile(implicit app: Application): ExtendedProfile = {
    val driverClass = app.configuration.getString(SLICK_DRIVER).getOrElse(DEFAULT_SLICK_DRIVER)
    singleton[ExtendedProfile](driverClass)
  }

  private def singleton[T](name : String)(implicit man: Manifest[T]): T =
    Class.forName(name + "$").getField("MODULE$").get(man.runtimeClass).asInstanceOf[T]

}
