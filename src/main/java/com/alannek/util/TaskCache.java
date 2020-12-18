package com.alannek.util;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import lombok.Data;

/**
 * Caching the computed results
 *
 * @author created: amino on 12/15/2020 4:39 PM
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */

@Data
public class TaskCache
{
    private static TaskCache INSTANCE;
    private Cache< String, Object > cache;

    private TaskCache()
    {
        cache = CacheBuilder.newBuilder()
            .maximumSize( 1000 )
            .expireAfterWrite( 10, TimeUnit.SECONDS )
            .build();
    };

    public static TaskCache getInstance()
    {
        if( INSTANCE == null )
        {
            return new TaskCache();
        }
        return INSTANCE;
    }

}
