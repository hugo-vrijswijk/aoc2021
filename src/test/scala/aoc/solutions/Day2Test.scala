package aoc.solutions

import aoc.AocSuite
import aoc.input.day2

class Day2Test extends AocSuite:

  test("part 1") {
    day2.through(Day2.part1).assertLastEquals(1947824)
  }

  test("part 2") {
    day2.through(Day2.part2).assertLastEquals(1813062561)
  }

end Day2Test
