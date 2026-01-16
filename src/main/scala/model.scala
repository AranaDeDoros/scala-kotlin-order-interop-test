case class Order(
  id: Long,
  items: List[Item],
  driver: Driver,
  dateCreated: String,
  establishment: Establishment,
  status: OrderStatus,
  comments: List[String]
):
  def total: Double =
    items.map(_.price).sum

case class Establishment(id: Long, name: String, isActive: Boolean)
case class Item(id: Long, name: String, price: Double)

case class OrderError(message: String)

case class Driver(id: Long, name: String):

  def accept(order: Order): Either[OrderError, Order] =
    order.status match
      case OrderCreated => Right(order.copy(status = OrderAccepted))
      case _            => Left(OrderError("invalid status change"))

  def cancel(order: Order): Either[OrderError, Order] =
    order.status match
      case OrderCancelled => Left(OrderError("order is already cancelled"))
      case _              => Right(order.copy(status = OrderCancelled))

sealed trait OrderStatus
case object OrderCreated   extends OrderStatus
case object OrderAccepted  extends OrderStatus
case object OrderCancelled extends OrderStatus


sealed trait OrderEvent:
  def orderId: Long
  def timestamp: String

case class OrderCreatedEvent(order: Order, timestamp: String)
  extends OrderEvent:
  val orderId: Long = order.id

case class OrderAcceptedEvent(order: Order, timestamp: String)
  extends OrderEvent:
  val orderId: Long = order.id

case class OrderCancelledEvent(order: Order, timestamp: String)
  extends OrderEvent:
  val orderId: Long = order.id

