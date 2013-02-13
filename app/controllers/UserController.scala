package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import models._
import common.BaseModel
import slick.session.Session

/**
 * Date: 13/02/12
 * Time: 16:36
 */
object UserController extends Controller {
  val userForm = Form(
    mapping(
      "firstName" -> text.verifying(nonEmpty),
      "lastName" -> text.verifying(nonEmpty),
      "email" -> text.verifying(nonEmpty)
    ) {
      (firstName, lastName, email) => User(-1, firstName, lastName, email)
    } {
      u => Some(u.firstName, u.lastName, u.email)
    }
  )

  def register = Action {implicit request =>
    userForm.bindFromRequest.fold(
      errors => BadRequest,
      implicit user => {
        BaseModel.withSession {implicit session: Session =>
          Users.insert(user)
        }
        Redirect(routes.UserController.registerForm)
      }
    )
  }
  def registerForm = Action {
    val users = BaseModel.withSession {implicit session: Session =>
      Users.findAll
    }
    Ok(views.html.user.registration_form(userForm, users))
  }
}
