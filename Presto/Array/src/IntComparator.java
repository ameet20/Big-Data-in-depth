package io.prestosql.array;


public interface IntComparator
{
    /**
     * Compares the given primitive types.
     *
     * @return A positive integer, zero, or a negative integer if the first
     * argument is greater than, equal to, or smaller than, respectively, the
     * second one.
     * @see java.util.Comparator
     */
    int compare(int k1, int k2);
}
