package aoc.solutions

import aoc.input.day3
import cats.Monoid
import fs2.Pipe

object Day3:

  def part1[F[_]]: Pipe[F, String, Int] =
    case class Frequency(times0: Int, times1: Int)

    given freqMonoid: Monoid[Frequency] =
      Monoid.instance(Frequency(0, 0), (a, b) => Frequency(a.times0 + b.times0, a.times1 + b.times1))

    _.map(_.map(b => if b == '0' then Frequency(1, 0) else Frequency(0, 1)))
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
  end part1

  def part2(input: Seq[String]) =
    def findRating(predicate: (Int, Int) => Boolean) =
      (0 until input.head.length).foldLeft(input) { (seq, index) =>
        val count     = seq.count(_.charAt(index) == '1')
        val threshold = (seq.length + 1) / 2
        val digit     = if predicate(count, threshold) then '1' else '0'
        if seq.length > 1 then seq.filter(_.charAt(index) == digit) else seq
      }

    def parseRating(predicate: (Int, Int) => Boolean) = Integer.parseInt(findRating(predicate).head, 2)

    val generatorRating = parseRating(Ordering[Int].gteq)
    val scrubberRating  = parseRating(Ordering[Int].lt)
    generatorRating * scrubberRating
  end part2

end Day3
