package aoc.solutions

import aoc.input.day3
import cats.Monoid

object Day3:
  def part1 =
    given freqMonoid: Monoid[Frequency] with
      def empty                               = Frequency(0, 0)
      def combine(a: Frequency, b: Frequency) = Frequency(a.times0 + b.times0, a.times1 + b.times1)

    day3
      .map(_.map(b => if b == '0' then Frequency(1, 0) else Frequency(0, 1)))
      .fold(Seq.fill(12)(freqMonoid.empty)) { (acc, freqs) =>
        acc.zip(freqs).map(freqMonoid.combine(_, _))
      } // Count frequencies of all columns
      .map(_.map(f => if f.times0 > f.times1 then 0 else 1))
      .map(_.mkString)
      .map(Integer.parseInt(_, 2))
      .map { x =>
        val y      = Integer.toString(x, 2);
        val yl     = y.length;
        val mask   = (Math.pow(2, yl) - 1).toInt; // calculate mask
        val result = ~x & mask;

        result * x
      }
      .compile
      .lastOrError
  end part1

  def part2 = day3.compile.toVector.map { input =>
    def findRating(predicate: (Int, Int) => Boolean) =
      (0 until input.head.length).foldLeft(input) { (seq, index) =>
        val count     = seq.count(_.charAt(index) == '1')
        val threshold = (seq.length + 1) / 2
        val digit     = if predicate(count, threshold) then '1' else '0'
        if seq.length > 1 then seq.filter(_.charAt(index) == digit) else seq
      }

    def parseRating(predicate: (Int, Int) => Boolean) = java.lang.Integer.parseInt(findRating(predicate).head, 2)

    val generatorRating = parseRating(Ordering[Int].gteq)
    val scrubberRating  = parseRating(Ordering[Int].lt)
    generatorRating * scrubberRating
  }

  case class Frequency(times0: Int, times1: Int)

  case class Power(gamma: Int, epsilon: Int):
    def consumption: Int = gamma * epsilon
end Day3
