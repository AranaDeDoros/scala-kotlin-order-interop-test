import scala.collection.concurrent.TrieMap
import scala.collection.mutable

object OrderStore {

  private val orders =
    TrieMap.empty[Long, Order]

  def applyEvent(event: OrderEvent): Order =
    val order = event match
      case OrderCreatedEvent(o, _)   => o
      case OrderAcceptedEvent(o, _)  => o
      case OrderCancelledEvent(o, _) => o

    orders.update(order.id, order)
    order

  def getAll: Seq[Order] =
    orders.flatMap { o => get(o._1) }.toSeq

  def get(id: Long): Option[Order] =
    orders.get(id)
}
