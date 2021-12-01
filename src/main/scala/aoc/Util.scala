package aoc

import cats.effect.IO
import fs2.io.file.*
import fs2.text.*

object Util:
  def readFile(input: String) =
    Files[IO]
      .readAll(Path(s"src/main/resources/input/$input.txt"))
      .through(utf8.decode)
      .through(lines)
      .filter(_.nonEmpty)

  def readFileInts(input: String) = readFile(input).map(_.toInt)
end Util
