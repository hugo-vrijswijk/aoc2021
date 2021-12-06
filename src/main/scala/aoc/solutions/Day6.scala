package aoc.solutions

object Day6:

  private def go(input: Seq[Int], days: Int): Long =
    (1 to days)
      .foldLeft(input.groupMapReduce(identity)(_ => 1L)(_ + _)) { (current, _) =>
        val fish = current.map((k, v) => (k - 1, v)).withDefaultValue(0L)
        fish.removed(-1).updated(6, fish(-1) + fish(6)).updated(8, fish(-1))
      }
      .values
      .sum

  end go

  def part1 = go(_, 80)

  def part2 = go(_, 256)

end Day6
