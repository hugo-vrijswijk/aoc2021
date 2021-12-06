package aoc

import cats.effect.IO
import fs2.io.file.*
import fs2.text.*
import cats.parse.{LocationMap, Parser}
object Util:
  def readFile(input: String) =
    Files[IO]
      .readAll(Path(s"src/main/resources/input/$input.txt"))
      .through(utf8.decode)
      .through(lines)
      .filter(_.nonEmpty)

  def readFileInts(input: String) = readFile(input).map(_.toInt)

  def readFileString(input: String): IO[String] = Files[IO]
    .readAll(Path(s"src/main/resources/input/$input.txt"))
    .through(utf8.decode)
    .compile
    .lastOrError

  extension [A](parser: Parser[A])
    def parseAllGet(input: String) = parser.parseAll(input) match
      case Right(value) => value
      case Left(Parser.Error(offset, cause)) =>
        val caret = LocationMap(input).toCaretUnsafe(offset)
        val err   = s"Error at ${caret.line + 1}:${caret.col + 1} (${input(offset)}), $cause"
        throw new IllegalArgumentException(err)
end Util
