import cats.effect.*
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import org.http4s.HttpApp
import com.comcast.ip4s.host
import com.comcast.ip4s.port

object Main extends IOApp.Simple {

  private val app: HttpApp[IO] =
    Router("/" -> OrderRoutes.routes).orNotFound

  override def run: IO[Unit] =
    EmberServerBuilder
      .default[IO]
      .withHost(host"0.0.0.0")
      .withPort(port"8080")     // 0.23.25 has this method
      .withHttpApp(app)
      .build
      .useForever
}
