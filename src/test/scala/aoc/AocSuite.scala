package aoc

import cats.effect.IO
import fs2.{Pure, Stream}
import munit.{CatsEffectSuite, Location}

abstract class AocSuite extends CatsEffectSuite:

  extension [A](stream: Stream[IO, A])
    def assertLastEquals[B](value: B, clue: => Any = "values are not the same")(using Location, B <:< A): IO[Unit] =
      stream.compile.lastOrError.assertEquals(value, clue)

  extension [A](stream: Stream[Pure, A])
    def assertLastEqualsPure[B](value: B, clue: => Any = "values are not the same")(using Location, B <:< A): Unit =
      assertEquals(stream.toList.last, value, clue)

end AocSuite
