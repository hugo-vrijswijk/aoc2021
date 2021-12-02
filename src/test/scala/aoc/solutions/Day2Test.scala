package aoc.solutions

import munit.CatsEffectSuite

class Day2Test extends CatsEffectSuite:

  test("part 1") {
    Day2.part1.assertEquals(1947824)
  }

  test("part 2") {
    Day2.part2.assertEquals(1813062561)
  }

end Day2Test
