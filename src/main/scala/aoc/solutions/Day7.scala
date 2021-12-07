package aoc.solutions

object Day7:

  def part1(input: Seq[Int]): Int = impl(input, (n, targetDistance) => (n - targetDistance).abs)

  def part2(input: Seq[Int]): Int =
    impl(input, (n, targetDistance) => ((n - targetDistance).abs * ((n - targetDistance).abs + 1)) / 2)

  private def impl(input: Seq[Int], distance: (Int, Int) => Int): Int =
    (input.min to input.max).map(distanceToCheck => input.map(distance(_, distanceToCheck)).sum).min

end Day7
