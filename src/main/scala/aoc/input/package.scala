package aoc.input

import aoc.Util.*
import fs2.Stream

def day1 = readFileInts("Day1")

def day2 = readFileLines("Day2")

def day3 = readFileLines("Day3")

def day4 = readFileString("Day4")

def day5 = readFileLines("Day5")

def day6 = readFileLines("Day6").flatMap(s => Stream.emits(s.split(','))).map(_.toInt)

def day7 = readFileLines("Day7").flatMap(s => Stream.emits(s.split(','))).map(_.toInt)

def day8 = readFileLines("Day8")
