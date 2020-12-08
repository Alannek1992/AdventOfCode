package com.alannek.taskrunner;

import com.alannek.tasks.SolvableIf;

import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Execution of daily tasks
 *
 * @author created:  amino on 12/7/2020 10:51 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class TaskRunner {

    private static int solvedDays = 1;

    public static void main(String[] args) throws Exception {

        for (int i = 1; i == solvedDays; i++) {
            SolvableIf task = getTask(i);
            String solution = task.solve(readFile(i));
            System.out.println("The solution for the Day" + i + " is: " + solution);

        }
    }

    private static SolvableIf getTask(int aDayNo) {
        try {
            Class<SolvableIf> aClass = (Class<SolvableIf>) Class.forName("com.alannek.tasks.Day" + aDayNo);
            Constructor<SolvableIf> constructor = aClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create an instance of a task!");
        }

    }

    private static String readFile(int aDayNo) {
        try {
            return Files.readString(Path.of(TaskRunner.class.getResource("/Day" + aDayNo + ".txt").toURI()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot read the file for day: " + aDayNo);
        }

    }
}
