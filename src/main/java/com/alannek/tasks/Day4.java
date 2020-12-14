package com.alannek.tasks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;

import lombok.Builder;
import lombok.Data;

/**
 * Day four task
 *
 * @author created: amino on 12/14/2020 12:39 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public class Day4 implements SolvableIf
{
    List< Passport > passports;
    Set< String > requiredFields = Set.of( "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" );

    public Day4( String aInput )
    {

        passports = Arrays.stream( aInput.split( "\n\n" ) )
            .map( passportData -> Passport.builder()
                .fields( Splitter.on( Pattern.compile( "[\\s,\n]" ) )
                    .withKeyValueSeparator( ":" )
                    .split( passportData ) )
                .build() )
            .collect( Collectors.toList() );
    }

    @Override
    public Integer solvePartOne()
    {

        return execute( new Validator() );

    }

    @Override
    public Integer solvePartTwo()
    {
        Validator validator = new Validator();
        Map< String, Predicate< String > > rules = validator.getRules();
        rules.put( "byr", n -> n.matches( "(19[2-9][0-9]|200[0-2])" ) );
        rules.put( "iyr", n -> n.matches( "(201[0-9]|2020)" ) );
        rules.put( "eyr", n -> n.matches( "(202[0-9]|2030)" ) );
        rules.put( "hgt", n -> n.matches( "^((1[5-8][0-9]|19[0-3])+cm|(59|6[0-9]|7[0-6])+in)$" ) );
        rules.put( "hcl", n -> n.matches( "^#+[0-9,a-z]{6}$" ) );
        rules.put( "ecl", n -> n.matches( "^(amb|blu|brn|gry|grn|hzl|oth)$" ) );
        rules.put( "pid", n -> n.matches( "^\\d{9}$" ) );
        return execute( validator );

    }

    private Integer execute( Validator aValidator )
    {
        int validPassports = 0;
        for( Passport passport : passports )
        {
            if( passport.getFields()
                .keySet()
                .containsAll( requiredFields ) && aValidator.validate( passport ) )
            {
                validPassports++;
            }

        }
        return validPassports;
    }
}

@Data
@Builder
class Passport
{
    private Map< String, String > fields;
}

@Data
class Validator
{
    private Map< String, Predicate< String > > rules = new HashMap<>();

    public boolean validate( Passport aPassport )
    {
        for( String key : rules.keySet() )
        {
            if( !rules.get( key )
                .test( aPassport.getFields()
                    .get( key ) ) )
            {
                return false;
            }
        }
        return true;
    }

}