package aoc.solutions

import aoc.AocSuite
import aoc.input.day1

class Day1Test extends AocSuite:

  test("part 1") {
    day1.through(Day1.part1).assertLastEquals(1233)
  }

  test("part 2") {
    day1.through(Day1.part2).assertLastEquals(1275)
  }

end Day1Test
