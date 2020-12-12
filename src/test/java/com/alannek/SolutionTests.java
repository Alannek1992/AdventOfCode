package com.alannek;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alannek.tasks.Day1;
import com.alannek.tasks.Day2;
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

    private void doTest( SolvableIf aDay, Integer partOneExpectedValue, Integer partTwoExpectedValue )
    {
        assertEquals( partOneExpectedValue, aDay.solvePartOne() );
        assertEquals( partTwoExpectedValue, aDay.solvePartTwo() );
    }
}
