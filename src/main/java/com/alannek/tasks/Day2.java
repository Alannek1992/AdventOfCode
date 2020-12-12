package com.alannek.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import org.javatuples.Triplet;

/**
 * Day two task
 *
 * @author created: amino on 12/12/2020 1:58 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day2 implements SolvableIf
{
    List< String > entries;

    public Day2( String aInput )
    {
        entries = Arrays.asList( aInput.split( "\n" ) );
    }

    @Override
    public Integer solvePartOne()
    {
        Integer validPasswordsCount = 0;
        for( String entry : entries )
        {

            Triplet< Integer, Integer, Character > rules = prepareTriplet( entry );

            String password = entry.substring( entry.indexOf( ":" ) + 2 );

            IntPredicate predicate = n -> n >= rules.getValue0() && n <= rules.getValue1();

            if( predicate.test( password.replaceAll( "[^" + rules.getValue2() + "]", "" )
                .length() ) )
            {
                validPasswordsCount++;
            }

        }

        return validPasswordsCount;
    }

    @Override
    public Integer solvePartTwo()
    {
        Integer validPasswordsCount = 0;
        for( String entry : entries )
        {
            Triplet< Integer, Integer, Character > rules = prepareTriplet( entry );
            String password = entry.substring( entry.indexOf( ":" ) + 2 );
            Predicate< Character > predicate1 = n -> password.charAt( rules.getValue0() - 1 ) == n;

            Predicate< Character > predicate2 = n -> password.charAt( rules.getValue1() - 1 ) == n;

            if( predicate1.test( rules.getValue2() ) ^ predicate2.test( rules.getValue2() ) )
            {
                validPasswordsCount++;
            }

        }

        return validPasswordsCount;
    }

    private Triplet< Integer, Integer, Character > prepareTriplet( String aEntry )
    {

        String[] minMax = aEntry.replaceAll( "[^-\\d]", "" )
            .split( "-" );
        char rule = aEntry.charAt( aEntry.indexOf( " " ) + 1 );
        return Triplet.with( Integer.parseInt( minMax[ 0 ] ), Integer.parseInt( minMax[ 1 ] ), rule );
    }

}
