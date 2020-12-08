package com.alannek.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Day one task
 *
 * @author created: amino on 12/7/2020 8:59 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day1 implements SolvableIf
{

    List< Integer > entries;

    @Override
    public String solve( String aInput )
    {
        processInput( aInput );
        return "part one: " + solvePartOne() + ", part two: " + solvePartTwo();
    }

    private void processInput( String aInput )
    {
        entries = Arrays.stream( aInput.split( "\n" ) )
            .map( Integer::parseInt )
            .collect( Collectors.toList() );
    }

    private String solvePartOne()
    {
        HashSet< Integer > uniqueEntries = new HashSet<>( entries );
        for( int i = 0; i < entries.size(); i++ )
        {
            if( uniqueEntries.contains( 2020 - entries.get( i ) ) )
            {
                return String.valueOf( entries.get( i ) * (2020 - entries.get( i )) );
            }

        }

        return "Solution not found";
    }

    private String solvePartTwo()
    {
        for( int i = 0; i < entries.size() - 2; i++ )
        {
            HashSet< Integer > sumOfPairs = new HashSet<>();
            int currentSum = 2020 - entries.get( i );

            for( int j = i + 1; j < entries.size(); j++ )
            {
                if( sumOfPairs.contains( currentSum - entries.get( j ) ) )
                {
                    return String
                        .valueOf( entries.get( i ) * entries.get( j ) * (currentSum - entries.get( j )) );
                }
                sumOfPairs.add( entries.get( j ) );
            }

        }
        return "Solution not found";
    }

}
