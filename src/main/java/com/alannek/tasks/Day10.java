package com.alannek.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Day ten task
 *
 * @author created: amino on 12/20/2020 2:00 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day10 implements SolvableIf
{

    List< Integer > adapters;

    public Day10( String aInput )
    {
        adapters = Arrays.stream( aInput.split( "\n" ) )
            .map( Integer::parseInt )
            .collect( Collectors.toList() );

    }

    @Override
    public Integer solvePartOne()
    {
        List< Integer > sortedValues = sort( adapters );
        Map< Integer, Integer > diffs = new HashMap<>();

        diffs.put( 1, 0 );
        diffs.put( 3, 1 );
        int acc = 0;

        for( Integer sortedValue : sortedValues )
        {
            int diff = sortedValue - acc;
            diffs.put( diff, diffs.get( diff ) + 1 );
            acc += diff;
        }

        return diffs.get( 1 ) * diffs.get( 3 );
    }

    @Override
    public Integer solvePartTwo()
    {
        List< Integer > sortedAdapters = sort( adapters );
        sortedAdapters.add( 0, 0 );
        sortedAdapters.add( sortedAdapters.size(), Collections.max( sortedAdapters ) + 3 );
        ArrayList< Integer > values = new ArrayList<>();
        int cons = 0;

        for( int i = 0; i < sortedAdapters.size(); i++ )
        {
            if( sortedAdapters.size() > i + 1 && sortedAdapters.get( i + 1 ) - sortedAdapters.get( i ) > 1 )
            {
                if( cons > 0 )
                {
                    values.add( cons );
                    cons = 0;
                }

            }
            else
            {
                cons++;
            }
        }

        Long value = values.stream()
            .map( this::getVariants )
            .map( Long::valueOf )
            .reduce( 1l, ( accVal, curr ) -> accVal * curr );

        if( value.intValue() < value )
        {
            System.out.println( "Day10 part 2: Value is to big to be printed out as Integer: " + value );
        }

        return value.intValue();

    }

    private List< Integer > sort( List< Integer > aNumbers )
    {
        return aNumbers.stream()
            .sorted()
            .collect( Collectors.toList() );
    }

    private Integer getVariants( int value )
    {
        int baseRule = 4;
        if( value == 3 )
        {
            return baseRule;
        }
        if( value > 3 )
        {
            return getVariants( value - 1 ) * 2 - 1;
        }

        return value;
    }
}
