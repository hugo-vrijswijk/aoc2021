package aoc.solutions

import aoc.input.day6
import munit.CatsEffectSuite

class Day6Test extends CatsEffectSuite:

  val testInput = Seq(3, 4, 3, 1, 2)

  test("part 1 example") {
    assertEquals(Day6.part1(testInput), 5934L)
  }

  test("part 1") {
    day6.compile.toList.map(Day6.part1).assertEquals(350605)
  }

  test("part 2 example") {
    assertEquals(Day6.part2(testInput), 26984457539L)
  }

  test("part 2") {
    day6.compile.toList.map(Day6.part2).assertEquals(1592778185024L)
  }

end Day6Test
