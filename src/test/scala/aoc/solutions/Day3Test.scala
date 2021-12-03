package aoc.solutions

import munit.CatsEffectSuite

class Day3Test extends CatsEffectSuite:

  test("part 1") {
    Day3.part1.assertEquals(3969000)
  }

  test("part 2") {
    Day3.part2.assertEquals(4267809)
  }

end Day3Test
