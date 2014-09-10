/**
 * Copyright (c) 2002-2014 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.io.enterprise.pagecache.impl.muninn;

import static org.neo4j.io.pagecache.stress.Conditions.numberOfEvictions;

import org.junit.Test;
import org.neo4j.io.pagecache.stress.Condition;
import org.neo4j.io.pagecache.stress.PageCacheStressTest;
import org.neo4j.io.pagecache.stress.SimpleMonitor;

public class MuninnPageCacheStressIT
{
    @Test
    public void shouldHandleTheStressOfTenMillionEvictions() throws Exception
    {
        SimpleMonitor simpleMonitor = new SimpleMonitor();
        Condition condition = numberOfEvictions( simpleMonitor, 10_000_000 );

        PageCacheStressTest runner = new PageCacheStressTest.Builder()
                .with( simpleMonitor )
                .with( condition )
                .build( new SimpleMuninnPageCacheFactory() );

        runner.run();
    }
}