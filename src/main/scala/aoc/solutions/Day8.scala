package aoc.solutions

import fs2.{Pipe, Stream}

object Day8:

  def part1[F[_]](input: Stream[F, String]): Stream[F, Int] =
    input.map(parseEntry).map(_.digits.count(isKnownDigit)).foldMonoid // foldMonoid is `sum`

  def part2[F[_]](input: Stream[F, String]): Stream[F, Int] =
    input.map(parseEntry).map(unscrambleEntry).foldMonoid

  private def isKnownDigit(pattern: String): Boolean = Seq(2, 3, 4, 7).contains(pattern.length)

  private def unscrambleEntry(entry: Entry): Int =
    val segments = 'a' to 'g'
    // Map *sorted* non-scrambled segment pattern to digit
    val segmentsToDigit =
      Seq("abcefg", "cf", "acdeg", "acdfg", "bcdf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg").zipWithIndex.toMap
    // Digit:  0 1 2 3 4 5 6 7 8 9
    // Length: 6 2 5 5 4 5 6 3 7 6
    def fromLength(x: Int) = entry.patterns.filter(_.length == x).flatten.toSet
    // Segment:     a b c d e f g
    // Occurrences: 8 6 8 7 4 9 7
    val occurrences             = entry.patterns.flatten.groupMapReduce(identity)(_ => 1)(_ + _)
    def fromOccurrences(x: Int) = segments.filter(occurrences(_) == x).toSet

    val setCF   = fromLength(2) // Digit 1 has pattern length two and uses segments c and f
    val setBCDF = fromLength(4)

    val setE  = fromOccurrences(4)
    val setB  = fromOccurrences(6)
    val setDG = fromOccurrences(7)
    val setAC = fromOccurrences(8)
    val setF  = fromOccurrences(9)

    val setC = setCF.diff(setF)
    val setA = setAC.diff(setC)
    val setG = setDG.diff(setBCDF)
    val setD = setDG.diff(setG)

    val unscramble = Seq(setA, setB, setC, setD, setE, setF, setG).zip(segments).map((k, v) => (k.head, v)).toMap

    entry.digits.map(scrambled => segmentsToDigit(scrambled.map(unscramble).sorted)).mkString.toInt
  end unscrambleEntry

  private case class Entry(patterns: Seq[String], digits: Seq[String])

  private def parseEntry(entry: String): Entry =
    val Array(patterns, digits) = entry.split(" \\| ")
    Entry(patterns.split(" "), digits.split(" "))

end Day8
