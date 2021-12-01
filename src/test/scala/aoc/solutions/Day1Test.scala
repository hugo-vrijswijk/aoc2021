package aoc.solutions

import munit.CatsEffectSuite

class Day1Test extends CatsEffectSuite:

  test("part 1 solution") {
    Day1Solution.part1.assertEquals(1233)
  }

  test("part 2 solution") {
    Day1Solution.part2.assertEquals(1275)
  }

end Day1Test
