import models.Users
import play.api._
import play.api.Play.current
import play.api.db.DB
import models.common.BaseModel


/**
 * Date: 13/02/12
 * Time: 19:13
 */
object Global extends GlobalSettings {
  import BaseModel.profile.simple._

  override def onStart(app: Application) {
    Logger.info("Application has started")
    Database.forDataSource(DB.getDataSource()).withSession {implicit session: Session =>
      Users.ddl.create
    }
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
    Database.forDataSource(DB.getDataSource()).withSession {implicit session: Session =>
      Users.ddl.drop
    }
  }

}