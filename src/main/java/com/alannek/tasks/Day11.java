package com.alannek.tasks;

import java.util.Arrays;
import java.util.function.BiFunction;

import org.javatuples.Pair;

import com.alannek.util.TaskCache;

/**
 * Day eleven task
 *
 * @author created: amino on 12/21/2020 1:46 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day11 implements SolvableIf
{
    private char[][] entries;
    private TaskCache cache = TaskCache.getInstance();

    public Day11( String aInput )
    {
        String[] rows = aInput.split( "\n" );
        entries = new char[ rows.length ][ rows[ 0 ].length() ];
        for( int i = 0; i < rows.length; i++ )
        {
            String[] columns = rows[ i ].split( "" );
            for( int j = 0; j < columns.length; j++ )
            {
                entries[ i ][ j ] = columns[ j ].charAt( 0 );
            }
        }
        cache.getCache()
            .put( "INITIAL-INPUT", copyArray() );
    }

    @Override
    public Integer solvePartOne()
    {

        return execute( this::getAdjacentSeats, 3 );
    }

    @Override
    public Integer solvePartTwo()
    {
        entries = (char[][])cache.getCache()
            .getIfPresent( "INITIAL-INPUT" );
        BiFunction< Integer, Integer, Integer > func = ( rowIdx, colIdx ) -> {
            int occupiedSeats = 0;
            for( MovementType mt : MovementType.values() )
            {
                if( isFirstSawSeatOccupied( rowIdx, colIdx, mt ) )
                {
                    occupiedSeats++;
                }
            }
            return occupiedSeats;
        };
        return execute( func, 4 );
    }

    private int applyRules( BiFunction< Integer, Integer, Integer > aFunc, int aTolerance )
    {
        int changes = 0;

        char[][] copyArray = copyArray();
        for( int i = 0; i < entries.length; i++ )
        {
            for( int j = 0; j < entries[ i ].length; j++ )
            {

                char seatState = entries[ i ][ j ];
                int occupiedAdjSeats = aFunc.apply( i, j );

                if( seatState == 'L' && occupiedAdjSeats == 0 )
                {
                    copyArray[ i ][ j ] = '#';
                    changes++;
                }
                else if( seatState == '#' && occupiedAdjSeats > aTolerance )
                {
                    copyArray[ i ][ j ] = 'L';
                    changes++;
                }

            }
        }
        entries = copyArray;
        return changes;
    }

    private Integer getAdjacentSeats( int aRowIdx, int aColIdx )
    {
        int occupiedCount = 0;
        for( MovementType mt : MovementType.values() )
        {
            try
            {
                Pair< Integer, Integer > movement = getMovement( mt, Pair.with( aRowIdx, aColIdx ) );
                char c = entries[ movement.getValue0() ][ movement.getValue1() ];
                if( c == '#' )
                {
                    occupiedCount++;
                }
            }
            catch( Exception e )
            {
                continue;
            }
        }

        return occupiedCount;
    }

    private boolean isFirstSawSeatOccupied( int aRowIdx, int aColIdx, MovementType aMovementType )
    {
        try
        {
            Pair< Integer, Integer > movement = getMovement( aMovementType, Pair.with( aRowIdx, aColIdx ) );
            char c = entries[ movement.getValue0() ][ movement.getValue1() ];
            if( c == '#' )
            {
                return true;
            }
            else if( c == 'L' )
            {
                return false;
            }
            else
            {
                return isFirstSawSeatOccupied( movement.getValue0(), movement.getValue1(), aMovementType );
            }
        }
        catch( Exception e )
        {
            return false;

        }

    }

    private Pair< Integer, Integer > getMovement( MovementType aDirection,
        Pair< Integer, Integer > aRowAndColIdx )
    {
        switch( aDirection )
        {
            case UP:
                return Pair.with( aRowAndColIdx.getValue0() + 1, aRowAndColIdx.getValue1() );
            case UPLEFT:
                return Pair.with( aRowAndColIdx.getValue0() + 1, aRowAndColIdx.getValue1() - 1 );
            case UPRIGHT:
                return Pair.with( aRowAndColIdx.getValue0() + 1, aRowAndColIdx.getValue1() + 1 );
            case RIGHT:
                return Pair.with( aRowAndColIdx.getValue0(), aRowAndColIdx.getValue1() + 1 );
            case LEFT:
                return Pair.with( aRowAndColIdx.getValue0(), aRowAndColIdx.getValue1() - 1 );
            case DOWN:
                return Pair.with( aRowAndColIdx.getValue0() - 1, aRowAndColIdx.getValue1() );
            case DOWNLEFT:
                return Pair.with( aRowAndColIdx.getValue0() - 1, aRowAndColIdx.getValue1() - 1 );
            case DOWNRIGHT:
                return Pair.with( aRowAndColIdx.getValue0() - 1, aRowAndColIdx.getValue1() + 1 );
        }
        return aRowAndColIdx;
    }

    private char[][] copyArray()
    {
        return Arrays.stream( entries )
            .map( char[]::clone )
            .toArray( char[][]::new );
    }

    private Integer execute( BiFunction< Integer, Integer, Integer > aFunc, int aTolerance )
    {

        while( applyRules( aFunc, aTolerance ) != 0 )
        {

        }

        int count = 0;

        for( char[] aEntry : entries )
        {
            for( char aC : aEntry )
            {
                if( aC == '#' )
                {
                    count++;
                }
            }
        }
        return count;
    }

    enum MovementType
    {
        UP, UPRIGHT, UPLEFT, LEFT, RIGHT, DOWN, DOWNRIGHT, DOWNLEFT
    }

}
