package com.alannek.tasks;

import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;

/**
 * Day three task
 *
 * @author created: amino on 12/14/2020 11:22 AM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day3 implements SolvableIf
{
    List< String > entries;

    public Day3( String aInput )
    {
        entries = Arrays.asList( aInput.split( "\n" ) );
    }

    @Override
    public Integer solvePartOne()
    {
        return execute( 3, 1 );
    }

    @Override
    public Integer solvePartTwo()
    {
        List< Pair< Integer, Integer > > slopes = List.of( Pair.with( 1, 1 ), Pair.with( 3, 1 ),
            Pair.with( 5, 1 ), Pair.with( 7, 1 ), Pair.with( 1, 2 ) );
        return slopes.stream()
            .map( slope -> execute( slope.getValue0(), slope.getValue1() ) )
            .reduce( 1, ( acc, curr ) -> curr * acc );
    }

    private Integer execute( int aRight, int aDown )
    {
        int encounteredTrees = 0;
        int moveRightIndex = aRight;
        for( int i = aDown; i < entries.size(); i += aDown )
        {
            String line = entries.get( i );
            if( line.charAt( moveRightIndex ) == '#' )
            {
                encounteredTrees++;
            }
            moveRightIndex += aRight;
            if( moveRightIndex >= line.length() )
            {
                moveRightIndex = moveRightIndex - line.length();
            }
        }
        return encounteredTrees;
    }
}
