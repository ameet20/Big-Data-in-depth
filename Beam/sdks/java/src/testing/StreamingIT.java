package org.apache.beam.sdk.testing;

/**
 * Category tag used to mark tests which execute in streaming mode. Example usage:
 *
 * <pre><code>
 *    {@literal @}Test
 *    {@literal @}Category(StreamingIT.class)
 *     public void testStreamingPipeline() {
 *       StreamingOptions options = ...;
 *       options.setStreaming(true);
 *       StreamingPipeline.main(...);
 *     }
 * </code></pre>
 *
 * @deprecated tests which use unbounded PCollections should be in the category {@link
 *     UsesUnboundedPCollections}. Beyond that, it is up to the runner and test configuration to
 *     decide whether to run in streaming mode.
 */
@Deprecated
public interface StreamingIT {}
