package aoc.solutions

import aoc.input.day1
import fs2.{Chunk, Pipe}

object Day1:

  def part1[F[_]]: Pipe[F, Int, Int] = _.sliding(2)
    .fold(0) { case (acc, a) =>
      if a(1) > a(0) then acc + 1
      else acc
    }

  def part2[F[_]]: Pipe[F, Int, Int] =
    def sum[A](chunk: Chunk[A])(using n: Numeric[A]): A = chunk.foldLeft(n.zero)(n.plus(_, _))

    _.sliding(3)
      .sliding(2)
      .fold(0) { case (acc, a) =>
        val left  = a(0)
        val right = a(1)

        if sum(right) > sum(left) then acc + 1
        else acc
      }
  end part2

end Day1
