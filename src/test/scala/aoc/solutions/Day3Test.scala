package aoc.solutions

import aoc.AocSuite
import aoc.input.day3
import fs2.Stream

class Day3Test extends AocSuite:

  test("part 1 example") {
    val input =
      Stream("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010")

    Day3.part1(input).assertLastEqualsPure(198)
  }

  test("part 1") {
    day3.through(Day3.part1).assertLastEquals(3969000)
  }

  test("part 2") {
    day3.compile.toList.map(Day3.part2).assertEquals(4267809)
  }

end Day3Test
