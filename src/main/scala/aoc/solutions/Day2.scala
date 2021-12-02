package aoc.solutions

import aoc.input
import cats.parse.{Numbers, Parser, Rfc5234}

object Day2:

  def part1 = instructions
    .fold(Location(0, 0)) { (location, instruction) =>
      instruction match
        case Instruction.Down(units)    => location.copy(depth = location.depth + units)
        case Instruction.Up(units)      => location.copy(depth = location.depth - units)
        case Instruction.Forward(units) => location.copy(x = location.x + units)
    }
    .map(location => location.x * location.depth)
    .compile
    .lastOrError

  def part2 = instructions
    .fold(Location(0, 0)) { (location, instruction) =>
      instruction match
        case Instruction.Down(units) => location.copy(aim = location.aim + units)
        case Instruction.Up(units)   => location.copy(aim = location.aim - units)
        case Instruction.Forward(units) =>
          location.copy(x = location.x + units, depth = location.aim * units + location.depth)
    }
    .map(location => location.x * location.depth)
    .compile
    .lastOrError

  private val instructions = input.day2.map(parser.parseAll(_)).map {
    case Right(instruction) => instruction
    case Left(err)          => new IllegalArgumentException(err.toString)
  }

  private val parser: Parser[Instruction] =
    val direction = Parser.stringIn(Seq("down", "up", "forward"))
    val units     = Numbers.digits.map(_.toInt)

    ((direction <* Rfc5234.wsp) ~ units).map {
      case ("down", units)    => Instruction.Down(units)
      case ("up", units)      => Instruction.Up(units)
      case ("forward", units) => Instruction.Forward(units)
    }
  end parser

  private enum Instruction(units: Int):
    case Down(units: Int) extends Instruction(units)
    case Up(units: Int) extends Instruction(units)
    case Forward(units: Int) extends Instruction(units)

  private case class Location(x: Int, depth: Int, aim: Int = 0)

end Day2
