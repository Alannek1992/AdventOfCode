package com.alannek.tasks;

import java.util.Optional;

/**
 * Represents solvable task
 *
 * @author created: amino on 12/7/2020 8:57 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public interface SolvableIf
{
    Optional< Integer > solvePartOne();

    Optional< Integer > solvePartTwo();
}
