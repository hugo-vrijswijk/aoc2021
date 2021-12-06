package aoc.solutions

import aoc.input.day6
import munit.CatsEffectSuite

import scala.concurrent.duration.Duration

class Day6Test extends CatsEffectSuite:

  override val munitTimeout = Duration(3, "s")

  test("part 1 example") {
    val input = Seq(3, 4, 3, 1, 2)

    assertEquals(Day6.part1(input), 5934L)
  }

  test("part 1") {
    day6.compile.toList.map(Day6.part1).assertEquals(350605)
  }

  test("part 2 example") {
    val input = Seq(3, 4, 3, 1, 2)

    assertEquals(Day6.part2(input), 26984457539L)
  }

  test("part 2") {
    day6.compile.toList.map(Day6.part2).assertEquals(1592778185024L)
  }

end Day6Test
