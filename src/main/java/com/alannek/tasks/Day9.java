package com.alannek.tasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.alannek.util.TaskCache;

/**
 * Day nine task
 *
 * @author created: amino on 12/18/2020 1:42 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day9 implements SolvableIf
{

    List< Long > numbers;
    Integer preamble = 25;
    TaskCache cache;

    public Day9( String aInput )
    {
        numbers = Arrays.stream( aInput.split( "\n" ) )
            .map( Long::parseLong )
            .collect( Collectors.toList() );

        cache = TaskCache.getInstance();
    }

    public Day9( Integer aPreamble, String aInput )
    {
        this( aInput );
        preamble = aPreamble;
    }

    @Override
    public Integer solvePartOne()
    {
        for( int i = preamble; i < numbers.size(); i++ )
        {
            HashSet< Long > preambleValues = new HashSet<>( numbers.subList( i - preamble, i ) );
            boolean valid = false;

            for( int j = i - preamble; j < i; j++ )
            {
                long num = numbers.get( i ) - numbers.get( j );
                if( preambleValues.contains( num ) && num - numbers.get( j ) != numbers.get( j ) )
                {
                    valid = true;
                    break;
                }
            }
            if( !valid )
            {

                cache.getCache()
                    .put( "DAY9-FIRST-PART-SOLUTION-INDEX", i );
                return numbers.get( i )
                    .intValue();

            }
        }

        return 0;
    }

    @Override
    public Integer solvePartTwo()
    {

        Integer incorrectNumIndex = (Integer)cache.getCache()
            .getIfPresent( "DAY9-FIRST-PART-SOLUTION-INDEX" );

        List< Long > sublist = numbers.subList( 0, incorrectNumIndex );

        for( int i = 0; i < sublist.size(); i++ )
        {
            long total = numbers.get( incorrectNumIndex );

            for( int j = i; j < sublist.size(); j++ )
            {
                total -= sublist.get( j );
                if( total == 0 )
                {
                    List< Long > conRange = sublist.subList( i, j );
                    return Collections.max( conRange )
                        .intValue()
                        + Collections.min( conRange )
                            .intValue();

                }
                else if( total < 0 )
                {
                    break;
                }
            }
        }
        return 0;

    }
}
