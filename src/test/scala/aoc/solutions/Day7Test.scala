package aoc.solutions

import aoc.input.day7
import munit.CatsEffectSuite

class Day7Test extends CatsEffectSuite:

  val testInput = Seq(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
  test("part 1 example") {
    assertEquals(Day7.part1(testInput), 37)
  }

  test("part 1") {
    day7.compile.toList.map(Day7.part1).assertEquals(344138)
  }

  test("part 2 example") {
    assertEquals(Day7.part2(testInput), 168)
  }

  test("part 2") {
    day7.compile.toList.map(Day7.part2).assertEquals(94862124)
  }

end Day7Test
