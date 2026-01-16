import scala.collection.concurrent.TrieMap

object OrderEventLog {

  private val events =
    TrieMap.empty[Long, Vector[OrderEvent]]

  def append(event: OrderEvent): Unit =
    events.updateWith(event.orderId) {
      case Some(existing) => Some(existing :+ event)
      case None           => Some(Vector(event))
    }

  def eventsFor(orderId: Long): Vector[OrderEvent] =
    events.getOrElse(orderId, Vector.empty)

  def allEvents: Vector[OrderEvent] =
    events.values.flatten.toVector
}
