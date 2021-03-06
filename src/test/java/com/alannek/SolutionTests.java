package com.alannek;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import com.alannek.tasks.Day1;
import com.alannek.tasks.Day10;
import com.alannek.tasks.Day11;
import com.alannek.tasks.Day2;
import com.alannek.tasks.Day3;
import com.alannek.tasks.Day4;
import com.alannek.tasks.Day5;
import com.alannek.tasks.Day6;
import com.alannek.tasks.Day7;
import com.alannek.tasks.Day8;
import com.alannek.tasks.Day9;
import com.alannek.tasks.SolvableIf;

/**
 * Testing each of the daily tasks
 *
 * @author created: amino on 12/11/2020 4:16 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
class SolutionTests
{

    @Test
    void dayOne()
    {
        String input = "1721\n" + "979\n" + "366\n" + "299\n" + "675\n" + "1456";
        doTest( new Day1( input ), 514579, 241861950 );

    }

    @Test
    void dayTwo()
    {
        String input = "1-3 a: abcde\n" + "1-3 b: cdefg\n" + "2-9 c: ccccccccc";
        doTest( new Day2( input ), 2, 1 );
    }

    @Test
    void dayThree()
    {
        String input = "..##.......\n" + "#...#...#..\n" + ".#....#..#.\n" + "..#.#...#.#\n" + ".#...##..#.\n"
            + "..#.##.....\n" + ".#.#.#....#\n" + ".#........#\n" + "#.##...#...\n" + "#...##....#\n"
            + ".#..#...#.#";

        doTest( new Day3( input ), 7, 336 );
    }

    @Test
    void dayFour()
    {
        String inputPartOne =
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" + "byr:1937 iyr:2017 cid:147 hgt:183cm\n" + "\n"
                + "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" + "hcl:#cfa07d byr:1929\n" + "\n"
                + "hcl:#ae17e1 iyr:2013\n" + "eyr:2024\n" + "ecl:brn pid:760753108 byr:1931\n" + "hgt:179cm\n"
                + "\n" + "hcl:#cfa07d eyr:2025 pid:166559648\n" + "iyr:2011 ecl:brn hgt:59in";

        doTest( new Day4( inputPartOne )::solvePartOne, 2 );

        String invalidInputPartTwo = "eyr:1972 cid:100\n"
            + "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926\n" + "\n" + "iyr:2019\n"
            + "hcl:#602927 eyr:1967 hgt:170cm\n" + "ecl:grn pid:012533040 byr:1946\n" + "\n"
            + "hcl:dab227 iyr:2012\n" + "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277\n" + "\n"
            + "hgt:59cm ecl:zzz\n" + "eyr:2038 hcl:74454a iyr:2023\n" + "pid:3556412378 byr:2007";

        doTest( new Day4( invalidInputPartTwo )::solvePartTwo, 0 );

        String validInputPartTwo = "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n"
            + "hcl:#623a2f\n" + "\n" + "eyr:2029 ecl:blu cid:129 byr:1989\n"
            + "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm\n" + "\n" + "hcl:#888785\n"
            + "hgt:164cm byr:2001 iyr:2015 cid:88\n" + "pid:545766238 ecl:hzl\n" + "eyr:2022\n" + "\n"
            + "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719";

        doTest( new Day4( validInputPartTwo )::solvePartTwo, 4 );

    }

    @Test
    void dayFive()
    {
        String input = "FBFBBFFRLR\nBFFFBBFRRR\nFFFBBBFRRR\nBBFFBBFRLL";
        doTest( new Day5( input )::solvePartOne, 820 );
    }

    @Test
    void daySix()
    {
        String input = "abc\n" + "\n" + "a\n" + "b\n" + "c\n" + "\n" + "ab\n" + "ac\n" + "\n" + "a\n" + "a\n"
            + "a\n" + "a\n" + "\n" + "b";

        doTest( new Day6( input ), 11, 6 );
    }

    @Test
    void daySeven()
    {
        String input = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n"
            + "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n"
            + "bright white bags contain 1 shiny gold bag.\n"
            + "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n"
            + "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n"
            + "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n"
            + "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n"
            + "faded blue bags contain no other bags.\n" + "dotted black bags contain no other bags.";

        doTest( new Day7( input ), 4, 32 );

        String inputPartTwo = "shiny gold bags contain 2 dark red bags.\n"
            + "dark red bags contain 2 dark orange bags.\n" + "dark orange bags contain 2 dark yellow bags.\n"
            + "dark yellow bags contain 2 dark green bags.\n" + "dark green bags contain 2 dark blue bags.\n"
            + "dark blue bags contain 2 dark violet bags.\n" + "dark violet bags contain no other bags.";

        doTest( new Day7( inputPartTwo )::solvePartTwo, 126 );
    }

    @Test
    void dayEight()
    {
        String input = "nop +0\n" + "acc +1\n" + "jmp +4\n" + "acc +3\n" + "jmp -3\n" + "acc -99\n"
            + "acc +1\n" + "jmp -4\n" + "acc +6";

        doTest( new Day8( input ), 5, 8 );
    }

    @Test
    void dayNine()
    {
        String input = "35\n" + "20\n" + "15\n" + "25\n" + "47\n" + "40\n" + "62\n" + "55\n" + "65\n" + "95\n"
            + "102\n" + "117\n" + "150\n" + "182\n" + "127\n" + "219\n" + "299\n" + "277\n" + "309\n" + "576";

        doTest( new Day9( 5, input ), 127, 62 );
    }

    @Test
    void dayTen()
    {
        String input = "28\n" + "33\n" + "18\n" + "42\n" + "31\n" + "14\n" + "46\n" + "20\n" + "48\n" + "47\n"
            + "24\n" + "23\n" + "49\n" + "45\n" + "19\n" + "38\n" + "39\n" + "11\n" + "1\n" + "32\n" + "25\n"
            + "35\n" + "8\n" + "17\n" + "7\n" + "9\n" + "4\n" + "2\n" + "34\n" + "10\n" + "3";

        doTest( new Day10( input ), 220, 19208 );

        String inputPartTwo =
            "16\n" + "10\n" + "15\n" + "5\n" + "1\n" + "11\n" + "7\n" + "19\n" + "6\n" + "12\n" + "4";

        doTest( new Day10( inputPartTwo )::solvePartTwo, 8 );
    }

    @Test
    void dayEleven()
    {
        String input = "L.LL.LL.LL\n" + "LLLLLLL.LL\n" + "L.L.L..L..\n" + "LLLL.LL.LL\n" + "L.LL.LL.LL\n"
            + "L.LLLLL.LL\n" + "..L.L.....\n" + "LLLLLLLLLL\n" + "L.LLLLLL.L\n" + "L.LLLLL.LL";

        doTest( new Day11( input ), 37, 26 );
    }

    private void doTest( SolvableIf aDay, Integer aPartOneExpectedValue, Integer aPartTwoExpectedValue )
    {
        assertEquals( aPartOneExpectedValue, aDay.solvePartOne() );
        assertEquals( aPartTwoExpectedValue, aDay.solvePartTwo() );
    }

    private void doTest( Supplier< Integer > aSupplier, Integer aExpectedValue )
    {
        assertEquals( aExpectedValue, aSupplier.get() );
    }

}
