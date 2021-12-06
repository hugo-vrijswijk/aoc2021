package aoc.input

import aoc.Util.*
import fs2.Stream

def day1 = readFileInts("Day1")

def day2 = readFile("Day2")

def day3 = readFile("Day3")

def day4 = readFileString("Day4")

def day5 = readFile("Day5")

def day6 = readFile("Day6").flatMap(s => Stream.emits(s.split(','))).map(_.toInt)
