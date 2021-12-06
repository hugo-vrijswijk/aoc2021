package aoc.solutions

import cats.parse.{Numbers, Parser}
import cats.parse.Rfc5234
import aoc.Util.parseAllGet

object Day5:
  def part1(input: Seq[String]): Int =
    val lines = parse(input)
    overlap(lines.filter(_.isOrthogonal))

  def part2(input: Seq[String]): Int =
    val lines = parse(input)
    overlap(lines)

  def overlap(lines: Seq[Line]): Int = lines.flatMap(_.points).groupBy(identity).count(_._2.length > 1)

  def parse(input: Seq[String]): Seq[Line] =
    val number = Numbers.nonNegativeIntString.map(_.toInt)
    val coord  = ((number <* Parser.char(',')) ~ number).map(Coord(_, _))
    val line   = ((coord <* Parser.string(" -> ")) ~ coord).map(Line(_, _))

    input.map(line.parseAllGet)
  end parse

  case class Coord(x: Int, y: Int)
  case class Line(from: Coord, to: Coord):
    def isOrthogonal: Boolean = from.x == to.x || from.y == to.y
    def points: Seq[(Int, Int)] =
      val xs = from.x to to.x by (if from.x < to.x then 1 else -1)
      val ys = from.y to to.y by (if from.y < to.y then 1 else -1)
      xs.zipAll(ys, from.x, from.y).map(_ -> _)
  end Line
end Day5
