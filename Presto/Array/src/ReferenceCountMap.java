package io.prestosql.array;

import io.airlift.slice.SizeOf;
import io.airlift.slice.Slice;
import io.prestosql.spi.block.Block;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.String.format;
import static java.lang.reflect.Array.getLength;
public final class ReferenceCountMap
        extends Long2IntOpenHashMap
{
    private static final int INSTANCE_SIZE = ClassLayout.parseClass(ReferenceCountMap.class).instanceSize();

    /**
     * Increments the reference count of an object by 1 and returns the updated reference count
     */
    public int incrementAndGet(Object key)
    {
        return addTo(getHashCode(key), 1) + 1;
    }

    /**
     * Decrements the reference count of an object by 1 and returns the updated reference count
     */
    public int decrementAndGet(Object key)
    {
        long hashCode = getHashCode(key);
        int previousCount = addTo(hashCode, -1);
        if (previousCount == 1) {
            remove(hashCode);
        }
        return previousCount - 1;
    }

    /**
     * Returns the size of this map in bytes.
     */
    public long sizeOf()
    {
        return INSTANCE_SIZE + SizeOf.sizeOf(key) + SizeOf.sizeOf(value) + SizeOf.sizeOf(used);
    }

    /**
     * Get the 64-bit hash code for an object
     */
    private static long getHashCode(Object key)
    {
        // identityHashCode of two objects are not guaranteed to be different.
        // Any additional identity information can reduce collisions.
        // In the most cases below, we use size of an object to be the extra identity.
        // Experiments show that with 100 million objects created, using identityHashCode has a collision rate around 2.5%.
        // However, if these 100 million objects are combined with 10 different sizes, the collision rate is around 0.1%.
        // The collision rate can be lower than 0.001% if there are 1000 different sizes.
        int extraIdentity;
        if (key == null) {
            extraIdentity = 0;
        }
        else if (key instanceof Block) {
            extraIdentity = (int) ((Block) key).getRetainedSizeInBytes();
        }
        else if (key instanceof Slice) {
            extraIdentity = (int) ((Slice) key).getRetainedSize();
        }
        else if (key.getClass().isArray()) {
            extraIdentity = getLength(key);
        }
        else {
            throw new IllegalArgumentException(format("Unsupported type for %s", key));
        }
        return (((long) System.identityHashCode(key)) << Integer.SIZE) + extraIdentity;
    }
}
