package aoc.solutions

import aoc.input.day3
import cats.effect.IO
import fs2.Stream
import munit.CatsEffectSuite

class Day3Test extends CatsEffectSuite:

  test("part 1 example") {
    val input =
      Stream("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010")

    Day3.part1[IO](input).assertEquals(198)
  }

  test("part 1") {
    Day3.part1(day3).assertEquals(3969000)
  }

  test("part 2") {
    day3.compile.toList.map(Day3.part2).assertEquals(4267809)
  }

end Day3Test
