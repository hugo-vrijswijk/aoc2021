package aoc.solutions

import aoc.input.day1
import fs2.Chunk

object Day1Solution:

  def part1 = day1
    .sliding(2)
    .fold(0) { case (acc, a) =>
      if a(1) > a(0) then acc + 1
      else acc
    }
    .compile
    .lastOrError

  def part2 =
    def sum[A: Numeric](chunk: Chunk[A]): A = chunk.toList.sum

    day1
      .sliding(3)
      .sliding(2)
      .fold(0) { case (acc, a) =>
        val left  = a(0)
        val right = a(1)

        if sum(right) > sum(left) then acc + 1
        else acc
      }
      .compile
      .lastOrError
  end part2
end Day1Solution
