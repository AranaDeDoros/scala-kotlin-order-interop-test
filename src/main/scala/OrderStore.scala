/*import scala.collection.concurrent.TrieMap

object OrderStore {

  private val orders = TrieMap.empty[Long, Order]

  def put(order: Order): Order = {
    orders.update(order.id, order)
    order
  }

  def get(id: Long): Option[Order] =
    orders.get(id)
}
*/

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

  def get(id: Long): Option[Order] =
    orders.get(id)
}
