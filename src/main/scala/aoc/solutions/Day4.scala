package aoc.solutions

import cats.data.NonEmptyList
import cats.parse.*
import fs2.{Pure, Stream}
import aoc.Util.parseAllGet

object Day4:
  def part1(input: String) = go(input).head

  def part2(input: String) = go(input)

  private def go(input: String) =
    val (instructions, boards) = parseBoards(input)

    // Stream for lazy evaluation so we only calculate the first element for part 1
    Stream
      .emits(instructions.toList)
      .fold[(Seq[Int], List[Board])]((Seq.empty, boards.toList)) { case ((oldWinners, boards), instruction) =>
        val (winningBoards, openBoards) = boards.toList.partition(_.hasWinner)

        val (newWinners, togo) = openBoards.map(_.mark(instruction)).partition(_.hasWinner)

        (oldWinners ++ (winningBoards ++ newWinners).map(winningBoard => winningBoard.score(instruction)), togo)
      }
      .flatMap(f => Stream.emits(f._1))
  end go

  opaque type Instructions = NonEmptyList[Int]
  opaque type Boards       = NonEmptyList[Board]
  opaque type Row          = NonEmptyList[(Boolean, Int)]
  opaque type Board        = NonEmptyList[Row]

  def parseBoards(input: String): (Instructions, Boards) =
    val number                             = Numbers.nonNegativeIntString.map(_.toInt)
    val instructions: Parser[Instructions] = number.repSep(1, Parser.char(',')) <* Rfc5234.lf

    val spaces               = Rfc5234.wsp.rep.void
    val row: Parser[Row]     = (Parser.pure(false).with1 ~ number).surroundedBy(spaces.?).rep(5, 5)
    val board: Parser[Board] = row.repSep(5, 5, Rfc5234.lf)

    val parser = instructions ~ board.surroundedBy(Rfc5234.lf.?).repSep(1, Rfc5234.lf)

    parser.parseAllGet(input)
  end parseBoards

  extension (b: Board)
    def score(lastNumber: Int): Int = b.flatMap(identity).filter(!_._1).map(_._2).sum * lastNumber

    def col(n: Int): NonEmptyList[(Boolean, Int)] = b.map(row => row.toList(n))

    def hasWinner: Boolean =
      def winningRow = b.exists(_.forall(_._1))
      def winningCol = (0 to b.head.length - 1).exists(r =>
        val col = b.col(r)
        col.forall(_._1)
      )
      winningRow || winningCol
    end hasWinner

    def mark(n: Int): Board = b.map(row => row.map((prev, num) => if n == num then (true, num) else (prev, num)))
  end extension
end Day4
