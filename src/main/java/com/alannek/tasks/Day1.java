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

    public Day1( String aInput )
    {
        entries = Arrays.stream( aInput.split( "\n" ) )
            .map( Integer::parseInt )
            .collect( Collectors.toList() );
    }

    @Override
    public Integer solvePartOne()
    {
        HashSet< Integer > uniqueEntries = new HashSet<>( entries );
        for( Integer aEntry : entries )
        {
            if( uniqueEntries.contains( 2020 - aEntry ) )
            {
                return aEntry * (2020 - aEntry);
            }

        }

        return null;
    }

    @Override
    public Integer solvePartTwo()
    {
        for( int i = 0; i < entries.size() - 2; i++ )
        {
            HashSet< Integer > sumOfPairs = new HashSet<>();
            int currentSum = 2020 - entries.get( i );

            for( int j = i + 1; j < entries.size(); j++ )
            {
                if( sumOfPairs.contains( currentSum - entries.get( j ) ) )
                {
                    return entries.get( i ) * entries.get( j ) * (currentSum - entries.get( j ));
                }
                sumOfPairs.add( entries.get( j ) );
            }

        }
        return null;
    }

}
