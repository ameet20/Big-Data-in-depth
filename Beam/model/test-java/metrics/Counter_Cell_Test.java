package org.apache.beam.runners.core.metrics;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.beam.sdk.metrics.MetricName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Tests for {@link CounterCell}. */
@RunWith(JUnit4.class)
public class CounterCellTest {

  private CounterCell cell = new CounterCell(MetricName.named("hello", "world"));

  @Test
  public void testDeltaAndCumulative() {
    cell.inc(5);
    cell.inc(7);
    assertThat(cell.getCumulative(), equalTo(12L));
    assertThat("getCumulative is idempotent", cell.getCumulative(), equalTo(12L));

    assertThat(cell.getDirty().beforeCommit(), equalTo(true));
    cell.getDirty().afterCommit();
    assertThat(cell.getDirty().beforeCommit(), equalTo(false));
    assertThat(cell.getCumulative(), equalTo(12L));

    cell.inc(30);
    assertThat(cell.getCumulative(), equalTo(42L));

    assertThat(cell.getDirty().beforeCommit(), equalTo(true));
    cell.getDirty().afterCommit();
    assertThat(cell.getDirty().beforeCommit(), equalTo(false));
  }

  @Test
  public void testEquals() {
    CounterCell counterCell = new CounterCell(MetricName.named("namespace", "name"));
    CounterCell equal = new CounterCell(MetricName.named("namespace", "name"));
    Assert.assertEquals(counterCell, equal);
    Assert.assertEquals(counterCell.hashCode(), equal.hashCode());
  }

  @Test
  public void testNotEquals() {
    CounterCell counterCell = new CounterCell(MetricName.named("namespace", "name"));

    Assert.assertNotEquals(counterCell, new Object());

    CounterCell differentDirty = new CounterCell(MetricName.named("namespace", "name"));
    differentDirty.getDirty().beforeCommit();
    Assert.assertNotEquals(counterCell, differentDirty);
    Assert.assertNotEquals(counterCell.hashCode(), differentDirty.hashCode());

    CounterCell differentValue = new CounterCell(MetricName.named("namespace", "name"));
    differentValue.inc();
    Assert.assertNotEquals(counterCell, differentValue);
    Assert.assertNotEquals(counterCell.hashCode(), differentValue.hashCode());

    CounterCell differentName = new CounterCell(MetricName.named("DIFFERENT", "DIFFERENT"));
    Assert.assertNotEquals(counterCell, differentName);
    Assert.assertNotEquals(counterCell.hashCode(), differentName.hashCode());
  }
}
