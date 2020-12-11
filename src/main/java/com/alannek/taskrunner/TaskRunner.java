package com.alannek.taskrunner;

import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import com.alannek.tasks.SolvableIf;

/**
 * Execution of daily tasks
 *
 * @author created: amino on 12/7/2020 10:51 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class TaskRunner
{

    public static void main( String[] args )
    {

        int solvedDays = 1;
        for( int i = 1; i == solvedDays; i++ )
        {
            SolvableIf task = getTask( i );
            Optional< Integer > partOne = task.solvePartOne();
            Optional< Integer > partTwo = task.solvePartTwo();
            System.out.println(
                "Day" + i + " solution: first part -> " + (partOne.isEmpty() ? "Not Found" : partOne.get())
                    + ", second part -> " + (partTwo.isEmpty() ? "Not Found" : partTwo.get()) );

        }
    }

    private static SolvableIf getTask( int aDayNo )
    {
        try
        {
            Class< SolvableIf > aClass =
                (Class< SolvableIf >)Class.forName( "com.alannek.tasks.Day" + aDayNo );
            Constructor< SolvableIf > constructor = aClass.getConstructor( String.class );
            return constructor.newInstance( (readFile( aDayNo )) );
        }
        catch( Exception e )
        {
            throw new RuntimeException( "Cannot create an instance of a task!" );
        }

    }

    private static String readFile( int aDayNo )
    {
        try
        {
            return Files.readString( Path.of( TaskRunner.class.getResource( "/Day" + aDayNo + ".txt" )
                .toURI() ) );
        }
        catch( Exception e )
        {
            e.printStackTrace();
            throw new RuntimeException( "Cannot read the file for day: " + aDayNo );
        }

    }

}
