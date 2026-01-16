import io.circe._
import io.circe.generic.semiauto._

object JsonCodecs {

  given Encoder[Item] = deriveEncoder
  given Decoder[Item] = deriveDecoder

  given Encoder[Driver] = deriveEncoder
  given Decoder[Driver] = deriveDecoder

  given Encoder[Establishment] = deriveEncoder
  given Decoder[Establishment] = deriveDecoder

  given Encoder[OrderStatus] =
    Encoder.encodeString.contramap {
      case OrderCreated   => "CREATED"
      case OrderAccepted  => "ACCEPTED"
      case OrderCancelled => "CANCELLED"
    }

  given Decoder[OrderStatus] =
    Decoder.decodeString.emap {
      case "CREATED"   => Right(OrderCreated)
      case "ACCEPTED"  => Right(OrderAccepted)
      case "CANCELLED" => Right(OrderCancelled)
      case other       => Left(s"Unknown status $other")
    }

  given Encoder[Order] = deriveEncoder
  given Decoder[Order] = deriveDecoder

  given Encoder[OrderError] = deriveEncoder


 given Encoder[OrderEvent] = Encoder.instance {
  case e @ OrderCreatedEvent(order, ts) =>
    Json.obj(
      "type" -> Json.fromString("OrderCreated"),
      "timestamp" -> Json.fromString(ts),
      "order" -> order.asJson
    )

  case e @ OrderAcceptedEvent(order, ts) =>
    Json.obj(
      "type" -> Json.fromString("OrderAccepted"),
      "timestamp" -> Json.fromString(ts),
      "order" -> order.asJson
    )

  case e @ OrderCancelledEvent(order, ts) =>
    Json.obj(
      "type" -> Json.fromString("OrderCancelled"),
      "timestamp" -> Json.fromString(ts),
      "order" -> order.asJson
    )
}

}
