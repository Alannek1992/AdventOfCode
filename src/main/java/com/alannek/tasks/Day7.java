package com.alannek.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alannek.util.TaskUtil;

/**
 * Day 7 task
 *
 * @author created: amino on 12/16/2020 12:30 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day7 implements SolvableIf
{

    List< String > rules;

    public Day7( String aInput )
    {

        rules = Arrays.asList( aInput.split( "\n" ) );

    }

    @Override
    public Integer solvePartOne()
    {

        return findPartOne( ".*\\s+shiny gold.*", new HashSet<>() );

    }

    @Override
    public Integer solvePartTwo()
    {

        return findPartTwo( "shiny gold.*" );

    }

    private Integer findPartOne( String aRegex, Set< String > aValues )
    {
        List< String > list = getFilteredList( aRegex );

        for( String rule : list )
        {
            String color = TaskUtil.findMatch( "(\\w+[\\s]+\\w+){1}", rule );
            aValues.add( color );
            findPartOne( ".*\\s+" + color + ".*", aValues );
        }

        return aValues.size();

    }

    private Integer findPartTwo( String aRegex )
    {
        int sum = 0;

        List< String > list = getFilteredList( aRegex );

        for( String rule : list )
        {
            if( rule.matches( ".*\\d.*" ) )
            {

                String[] words = rule.split( " " );

                for( int i = 0; i < words.length; i++ )
                {
                    if( words[ i ].matches( "\\d+" ) )
                    {
                        sum += Integer.parseInt( words[ i ] ) + Integer.parseInt( words[ i ] )
                            * findPartTwo( words[ i + 1 ] + " " + words[ i + 2 ] + ".*" );
                    }
                }
            }

        }

        return sum;
    }

    private List< String > getFilteredList( String aRegex )
    {
        return rules.stream()
            .filter( rule -> rule.matches( aRegex ) )
            .collect( Collectors.toList() );
    }

}
