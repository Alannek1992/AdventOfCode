package com.alannek.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class
 *
 * @author created: amino on 12/16/2020 5:54 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class TaskUtil
{
    public static String findMatch( String aRegex, String aSequence )
    {
        Pattern pattern = Pattern.compile( aRegex );
        Matcher matcher = pattern.matcher( aSequence );
        if( matcher.find() )
        {
            return matcher.group( 0 );
        };
        return aSequence;
    }
}
