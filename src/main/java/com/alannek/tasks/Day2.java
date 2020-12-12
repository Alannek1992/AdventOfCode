package com.alannek.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import org.javatuples.Triplet;

import lombok.AllArgsConstructor;
import lombok.Data;

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

        return execute( predicateInput -> {

            IntPredicate intPredicate =
                n -> n >= predicateInput.rules.getValue0() && n <= predicateInput.rules.getValue1();

            return intPredicate
                .test( predicateInput.password.replaceAll( "[^" + predicateInput.rules.getValue2() + "]", "" )
                    .length() );
        } );

    }

    @Override
    public Integer solvePartTwo()
    {
        return execute( predicateInput -> predicateInput.password
            .charAt( predicateInput.rules.getValue0() - 1 ) == predicateInput.rules.getValue2()
            ^ predicateInput.password.charAt( predicateInput.rules.getValue1() - 1 ) == predicateInput.rules
                .getValue2() );

    }

    private Triplet< Integer, Integer, Character > prepareTriplet( String aEntry )
    {

        String[] minMax = aEntry.replaceAll( "[^-\\d]", "" )
            .split( "-" );
        char rule = aEntry.charAt( aEntry.indexOf( " " ) + 1 );
        return Triplet.with( Integer.parseInt( minMax[ 0 ] ), Integer.parseInt( minMax[ 1 ] ), rule );
    }

    private Integer execute( Predicate< PredicateInput > aPredicate )
    {
        Integer validPasswordsCount = 0;
        for( String entry : entries )
        {

            Triplet< Integer, Integer, Character > rules = prepareTriplet( entry );
            String password = entry.substring( entry.indexOf( ":" ) + 2 );

            if( aPredicate.test( new PredicateInput( rules, password ) ) )
            {
                validPasswordsCount++;
            }
        }
        return validPasswordsCount;
    }

}

@Data
@AllArgsConstructor
class PredicateInput
{
    Triplet< Integer, Integer, Character > rules;
    String password;

}
