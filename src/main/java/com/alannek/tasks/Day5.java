package com.alannek.tasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.alannek.util.TaskCache;
import com.google.common.collect.Range;

/**
 * Day five task
 *
 * @author created: amino on 12/15/2020 12:33 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day5 implements SolvableIf
{

    TaskCache cache;
    private List< String > entries;

    public Day5( String aInput )
    {
        entries = Arrays.asList( aInput.split( "\n" ) );
        cache = TaskCache.getInstance();

    }

    @Override
    public Integer solvePartOne()
    {

        List< Integer > tickets = findTickets();
        cache.getCache()
            .put( "DAY5-PART-ONE", tickets );
        return Collections.max( tickets );

    }

    @Override
    public Integer solvePartTwo()
    {
        List< Integer > tickets = (List< Integer >)cache.getCache()
            .getIfPresent( "DAY5-PART-ONE" );

        for( int i = 0; i < tickets.size(); i++ )
        {
            if( i != tickets.get( i ) - tickets.get( 0 ) )
            {
                return tickets.get( i ) - 1;
            }
        }
        return 0;

    }

    private List< Integer > findTickets()
    {
        return entries.stream()
            .map( entry -> evaluate( entry.substring( 0, 7 ), Range.open( 0, 127 ) ) * 8
                + evaluate( entry.substring( 7 ), Range.open( 0, 7 ) ) )
            .sorted()
            .collect( Collectors.toList() );
    }

    private Integer evaluate( String aCharacters, Range< Integer > aRange )
    {
        if( aCharacters.length() == 1 )
        {
            return aCharacters.matches( "[F,L]" ) ? aRange.lowerEndpoint() : aRange.upperEndpoint();
        }

        Supplier< Range< Integer > > operation = () -> {
            if( aCharacters.substring( 0, 1 )
                .matches( "[F,L]" ) )
            {
                return Range.closed( aRange.lowerEndpoint(),
                    aRange.upperEndpoint() - 1 - (aRange.upperEndpoint() - aRange.lowerEndpoint()) / 2 );
            }
            return Range.closed(
                aRange.lowerEndpoint() + 1 + (aRange.upperEndpoint() - aRange.lowerEndpoint()) / 2,
                aRange.upperEndpoint() );
        };

        return evaluate( aCharacters.substring( 1 ), operation.get() );
    }
}
