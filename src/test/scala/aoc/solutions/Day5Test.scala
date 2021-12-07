package aoc.solutions

import aoc.input.day5
import munit.CatsEffectSuite

class Day5Test extends CatsEffectSuite:

  val input = Seq(
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
    assertEquals(Day5.part1(input), 5)
  }

  test("part 1") {
    day5.compile.toVector.map(Day5.part1).assertEquals(5169)
  }

  test("part 2 example") {
    assertEquals(Day5.part2(input), 12)
  }

  test("part 2") {
    day5.compile.toVector.map(Day5.part2).assertEquals(22083)
  }

end Day5Test
