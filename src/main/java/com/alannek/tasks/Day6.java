package com.alannek.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import lombok.Builder;
import lombok.Data;

/**
 * Day six task
 *
 * @author created: amino on 12/15/2020 5:22 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day6 implements SolvableIf
{

    List< Group > entries;

    public Day6( String aInput )
    {
        entries = Arrays.stream( aInput.split( "\n\n" ) )
            .map( entry -> Group.builder()
                .persons( Arrays.stream( entry.split( "\n" ) )
                    .map( answer -> Person.builder()
                        .answers( Sets.newHashSet( answer.split( "" ) ) )
                        .build() )
                    .collect( Collectors.toList() ) )
                .build() )
            .collect( Collectors.toList() );

    }

    @Override
    public Integer solvePartOne()
    {
        return execute( ( a, b ) -> {
            a.addAll( b );
            return a;
        } );
    }

    @Override
    public Integer solvePartTwo()
    {
        return execute( ( a, b ) -> {

            a.retainAll( b );
            return a;
        } );
    }

    public Integer execute( BiFunction< Set< String >, Set< String >, Set< String > > aFunction )
    {
        return entries.stream()
            .map( group -> group.getPersons()
                .stream()
                .map( person -> person.getAnswers() )
                .reduce(
                    ( acc, curr ) -> aFunction.apply( Sets.newHashSet( acc ), Sets.newHashSet( curr ) ) ) )
            .map( Optional::get )
            .map( Set::size )
            .reduce( 0, Integer::sum );
    }

}

@Data
@Builder
class Group
{
    private List< Person > persons;
}

@Data
@Builder
class Person
{
    private Set< String > answers;
}
