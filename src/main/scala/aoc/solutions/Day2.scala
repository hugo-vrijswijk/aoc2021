package aoc.solutions

import aoc.Util.parseAllGet
import aoc.input
import cats.parse.{Numbers, Parser, Rfc5234}
import fs2.Pipe

object Day2:

  def part1[F[_]]: Pipe[F, String, Int] = _.map(parser.parseAllGet)
    .fold(Location(0, 0)) { (location, instruction) =>
      instruction match
        case Instruction.Down(units)    => location.copy(depth = location.depth + units)
        case Instruction.Up(units)      => location.copy(depth = location.depth - units)
        case Instruction.Forward(units) => location.copy(x = location.x + units)
    }
    .map(_.position)

  def part2[F[_]]: Pipe[F, String, Int] = _.map(parser.parseAllGet)
    .fold(Location(0, 0)) { (location, instruction) =>
      instruction match
        case Instruction.Down(units) => location.copy(aim = location.aim + units)
        case Instruction.Up(units)   => location.copy(aim = location.aim - units)
        case Instruction.Forward(units) =>
          location.copy(x = location.x + units, depth = location.aim * units + location.depth)
    }
    .map(_.position)

  private val parser: Parser[Instruction] =
    val direction = Parser.stringIn(Seq("down", "up", "forward")).map {
      case "down"    => Instruction.Down(_)
      case "up"      => Instruction.Up(_)
      case "forward" => Instruction.Forward(_)
    }
    val units = Numbers.digits.map(_.toInt)

    ((direction <* Rfc5234.wsp) ~ units).map(_(_))
  end parser

  private enum Instruction(units: Int):
    case Down(units: Int) extends Instruction(units)
    case Up(units: Int) extends Instruction(units)
    case Forward(units: Int) extends Instruction(units)

  private case class Location(x: Int, depth: Int, aim: Int = 0):
    def position = x * depth

end Day2
