package aoc

import cats.data.Chain
import cats.effect.IO
import cats.parse.{LocationMap, Parser}
import fs2.io.file.{Files, Path}
import fs2.{text, Chunk, Pipe, Pull, Stream}

object Util:

  def readFileLines(input: String) =
    Files[IO]
      .readAll(Path(s"src/main/resources/input/$input.txt"))
      .through(text.utf8.decode)
      .through(text.lines)
      .filter(_.nonEmpty)

  def readFileInts(input: String) = readFileLines(input).map(_.toInt)

  def readFileString(input: String) = Files[IO]
    .readAll(Path(s"src/main/resources/input/$input.txt"))
    .through(text.utf8.decode)

  extension [A](parser: Parser[A])
    def parseAllGet(input: String) = parser.parseAll(input) match
      case Right(value) => value
      case Left(Parser.Error(offset, cause)) =>
        val caret = LocationMap(input).toCaretUnsafe(offset)
        val err   = s"Error at ${caret.line + 1}:${caret.col + 1} (${input(offset)}), $cause"
        throw new IllegalArgumentException(err)

  def groupBy[F[_], K, V](f: V => K): Pipe[F, V, (K, Chain[V])] =
    def go(state: Map[K, Chain[V]]): Stream[F, V] => Pull[F, (K, Chain[V]), Unit] = _.pull.uncons.flatMap {
      case Some((chunks, tail)) =>
        val newMap = chunks.foldLeft(state) { (state, num) =>
          val key  = f(num)
          val prev = state.getOrElse(key, Chain.empty)
          state.updated(key, prev.append(num))
        }
        go(newMap)(tail)
      case None =>
        val chunk = Chunk.seq(state.toSeq)
        Pull.output(chunk) >> Pull.done
    }

    go(Map.empty)(_).stream
  end groupBy

end Util
