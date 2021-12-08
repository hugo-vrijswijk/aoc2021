package aoc.solutions

import aoc.Util.{groupBy, parseAllGet}
import cats.Eq
import cats.parse.{Numbers, Parser, Rfc5234}
import fs2.Collector.Builder
import fs2.{Pipe, Stream}

object Day5:

  def part1[F[_]]: Pipe[F, String, Int] = _.map(parser.parseAllGet).filter(_.isOrthogonal).through(overlap)

  def part2[F[_]]: Pipe[F, String, Int] = _.map(parser.parseAllGet).through(overlap)

  def overlap[F[_]]: Pipe[F, Line, Int] =
    _.map(_.points).flatMap(Stream.emits).through(groupBy(identity)).filter(_._2.size > 1).through(size)

  def size[F[_], A]: Pipe[F, A, Int] = _.fold(0)((acc, _) => acc + 1)

  val parser =
    val number = Numbers.nonNegativeIntString.map(_.toInt)
    val coord  = ((number <* Parser.char(',')) ~ number).map(Coord(_, _))
    ((coord <* Parser.string(" -> ")) ~ coord).map(Line(_, _))

  case class Coord(x: Int, y: Int)
  case class Line(from: Coord, to: Coord):
    def isOrthogonal: Boolean = from.x == to.x || from.y == to.y
    def points: Seq[(Int, Int)] =
      val xs = from.x to to.x by (if from.x < to.x then 1 else -1)
      val ys = from.y to to.y by (if from.y < to.y then 1 else -1)
      xs.zipAll(ys, from.x, from.y)
  end Line
end Day5
