package app

import cats.effect._
import io.odin._

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    consoleLogger[IO]().info("Hello World!").as(ExitCode.Success)
}
