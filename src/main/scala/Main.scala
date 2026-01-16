import cats.effect._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router

object Main extends IOApp.Simple {

  private val app =
    Router("/" -> OrderRoutes.routes).orNotFound

  override def run: IO[Unit] =
    EmberServerBuilder
      .default[IO]
      .withHost("0.0.0.0")
      .withPort(8080)
      .withHttpApp(app)
      .build
      .useForever
}
