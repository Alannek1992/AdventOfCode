package com.alannek.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Pair;

import lombok.AllArgsConstructor;
import lombok.Data;

enum InstructionType
{
    nop, acc, jmp
}

/**
 * Day eight task
 *
 * @author created: amino on 12/18/2020 8:52 AM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day8 implements SolvableIf
{
    List< Instruction > instructions;

    public Day8( String aInput )
    {
        instructions = Arrays.stream( aInput.split( "\n" ) )
            .map( line -> new Instruction( InstructionType.valueOf( line.substring( 0, 3 ) ),
                Integer.valueOf( line.substring( line.charAt( 4 ) == '+' ? 5 : 4 ) ) ) )
            .collect( Collectors.toList() );
    }

    @Override
    public Integer solvePartOne()
    {
        return execute( instructions ).getValue0();
    }

    @Override
    public Integer solvePartTwo()
    {
        for( int i = 0; i < instructions.size(); i++ )
        {
            if( instructions.get( i )
                .getKey()
                .equals( InstructionType.jmp ) )
            {
                ArrayList< Instruction > copy = new ArrayList<>();
                copy.addAll( instructions );
                copy.set( i, new Instruction( InstructionType.nop, 20 ) );
                Pair< Integer, Boolean > result = execute( copy );
                if( result.getValue1() )
                {
                    return result.getValue0();
                }
            }
        }
        return 0;
    }

    private Pair< Integer, Boolean > execute( List< Instruction > aInstructions )
    {

        HashSet< Integer > executedOps = new HashSet<>();
        int visitedInstructionIdx = 0;
        int acc = 0;

        while( !executedOps.contains( visitedInstructionIdx ) )
        {
            if( visitedInstructionIdx >= aInstructions.size() )
            {
                return Pair.with( acc, true );
            }

            executedOps.add( visitedInstructionIdx );
            Instruction instruction = aInstructions.get( visitedInstructionIdx );
            switch( instruction.getKey() )
            {
                case acc:
                    acc += instruction.getOperation();
                    visitedInstructionIdx++;
                    break;
                case jmp:
                    visitedInstructionIdx += instruction.getOperation();
                    break;
                case nop:
                    visitedInstructionIdx++;
                    break;
            }

        }
        return Pair.with( acc, false );
    }

}

@Data
@AllArgsConstructor
class Instruction
{
    InstructionType key;
    Integer operation;

}
