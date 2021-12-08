package aoc.solutions

import aoc.input.day5
import munit.CatsEffectSuite
import fs2.Stream
import aoc.AocSuite

class Day5Test extends AocSuite:

  val input = Stream(
    "0,9 -> 5,9",
    "8,0 -> 0,8",
    "9,4 -> 3,4",
    "2,2 -> 2,1",
    "7,0 -> 7,4",
    "6,4 -> 2,0",
    "0,9 -> 2,9",
    "3,4 -> 1,4",
    "0,0 -> 8,8",
    "5,5 -> 8,2"
  )

  test("part 1 example") {
    input.through(Day5.part1).assertLastEqualsPure(5)
  }

  test("part 1") {
    day5.through(Day5.part1).assertLastEquals(5169)
  }

  test("part 2 example") {
    input.through(Day5.part2).assertLastEqualsPure(12)
  }

  test("part 2") {
    day5.through(Day5.part2).assertLastEquals(22083)
  }

end Day5Test
