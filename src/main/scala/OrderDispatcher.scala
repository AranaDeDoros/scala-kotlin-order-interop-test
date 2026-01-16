import scala.util.Random
import java.time.Instant

object OrderDispatcher {

  private val rnd = new Random()

  private def randomId(): Long =
    math.abs(rnd.nextLong())

  private def randomName(prefix: String): String =
    s"$prefix-${rnd.alphanumeric.take(6).mkString}"

  private def randomPrice(): Double =
    BigDecimal(5 + rnd.nextDouble() * 50)
      .setScale(2, BigDecimal.RoundingMode.HALF_UP)
      .toDouble

  def randomItem(): Item =
    Item(randomId(), randomName("item"), randomPrice())

  def randomItems(): List[Item] =
    List.fill(1 + rnd.nextInt(4))(randomItem())

  def randomDriver(): Driver =
    Driver(randomId(), randomName("driver"))

  def randomEstablishment(): Establishment =
    Establishment(randomId(), randomName("restaurant"), isActive = true)

  def createOrder(): Order =
    Order(
      id = randomId(),
      items = randomItems(),
      driver = randomDriver(),
      dateCreated = Instant.now().toString,
      establishment = randomEstablishment(),
      status = OrderCreated,
      comments = Nil
    )

  def create(): OrderCreatedEvent =
    val order = createOrder()
    OrderCreatedEvent(order, Instant.now().toString)

  def accept(order: Order): Either[OrderError, OrderAcceptedEvent] =
    order.driver.accept(order).map { updated =>
      OrderAcceptedEvent(updated, Instant.now().toString)
    }

  def cancel(order: Order): Either[OrderError, OrderCancelledEvent] =
    order.driver.cancel(order).map { updated =>
      OrderCancelledEvent(updated, Instant.now().toString)
    }
}
