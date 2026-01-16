import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.circe._
import io.circe.syntax._
import JsonCodecs.given

object OrderRoutes {

  given EntityEncoder[IO, Order] = jsonEncoderOf
  given EntityEncoder[IO, OrderError] = jsonEncoderOf

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {

case POST -> Root / "orders" =>
  val event = OrderDispatcher.create()
  OrderEventLog.append(event)
  val order = OrderStore.applyEvent(event)
  Ok(order.asJson)

case POST -> Root / "orders" / LongVar(id) / "ack" =>
  OrderStore.get(id) match
    case Some(order) =>
      OrderDispatcher.accept(order) match
        case Right(event) =>
          OrderEventLog.append(event)
          val updated = OrderStore.applyEvent(event)
          Ok(updated.asJson)

        case Left(err) =>
          BadRequest(err.asJson)

    case None =>
      NotFound()

case POST -> Root / "orders" / LongVar(id) / "cancel" =>
  OrderStore.get(id) match
    case Some(order) =>
      OrderDispatcher.cancel(order) match
        case Right(event) =>
          OrderEventLog.append(event)
          val updated = OrderStore.applyEvent(event)
          Ok(updated.asJson)

        case Left(err) =>
          BadRequest(err.asJson)

    case None =>
      NotFound()
  }
}
