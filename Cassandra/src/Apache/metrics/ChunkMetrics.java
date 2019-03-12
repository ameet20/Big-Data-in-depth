package org.apache.cassandra.metrics;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

import com.google.common.annotations.VisibleForTesting;

import com.codahale.metrics.Timer;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.github.benmanes.caffeine.cache.stats.StatsCounter;
import org.apache.cassandra.cache.ChunkCache;

import static org.apache.cassandra.metrics.CassandraMetricsRegistry.Metrics;

/**
 * Metrics for {@code ICache}.
 */
public class ChunkCacheMetrics extends CacheMetrics implements StatsCounter
{
    /** Latency of misses */
    public final Timer missLatency;

    /**
     * Create metrics for the provided chunk cache.
     *
     * @param cache Chunk cache to measure metrics
     */
    public ChunkCacheMetrics(ChunkCache cache)
    {
        super("ChunkCache", cache);
        missLatency = Metrics.timer(factory.createMetricName("MissLatency"));
    }

    @Override
    public void recordHits(int count)
    {
        hits.mark(count);
    }

    @Override
    public void recordMisses(int count)
    {
        misses.mark(count);
    }

    @Override
    public void recordLoadSuccess(long loadTime)
    {
        missLatency.update(loadTime, TimeUnit.NANOSECONDS);
    }

    @Override
    public void recordLoadFailure(long loadTime)
    {
    }

    @Override
    public void recordEviction()
    {
    }

    @Nonnull
    @Override
    public CacheStats snapshot()
    {
        return new CacheStats(hits.getCount(), misses.getCount(), missLatency.getCount(), 0L, missLatency.getCount(), 0L, 0L);
    }

    @VisibleForTesting
    public void reset()
    {
        hits.mark(-hits.getCount());
        misses.mark(-misses.getCount());
    }
}
