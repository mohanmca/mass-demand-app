package controllers

import play.api.mvc.{ Controller, WebSocket, Action }
import play.api.Routes
import play.api.libs.json.{ Writes, JsValue }
import play.api.libs.iteratee.{ Concurrent, Iteratee }
import play.api.libs.concurrent.Akka
import akka.actor.{ ActorRef, PoisonPill }
import server.RemotePlayer

/**
 * @author Mohan
 */
object SocketController extends Controller {

  def connectToGameServer = WebSocket.using[JsValue] {
    request =>
      val system = Akka.system(play.api.Play.current)
      val (out, channel) = Concurrent.broadcast[JsValue]

      val publish: RemotePlayer.ClientPublish = {
        msg => channel.push(msg)
      }

      // TODO - Maybe we construct this via a supervisor.
      val personActor: ActorRef = system.actorOf(server.RemotePlayer.props(publish))

      import concurrent.ExecutionContext.Implicits.global
      val in = Iteratee.foreach[JsValue] {
        msg =>
          personActor ! msg
      } map {
        _ =>
          // Called after the first iteratee is done (one EOF).
          personActor ! PoisonPill
      }
      (in, out)
  }
  def javascriptRoutes = Action {
    implicit request =>
      Ok(Routes.javascriptRouter("jsRoutes")(routes.javascript.SocketController.connectToGameServer)).as(JAVASCRIPT)
  }
}