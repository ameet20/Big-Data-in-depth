package org.apache.beam.runners.core.metrics;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.beam.sdk.metrics.MetricName;
import org.junit.Assert;
import org.junit.Test;

/** Tests for {@link GaugeCell}. */
public class GaugeCellTest {
  private GaugeCell cell = new GaugeCell(MetricName.named("hello", "world"));

  @Test
  public void testDeltaAndCumulative() {
    cell.set(5);
    cell.set(7);
    assertThat(cell.getCumulative().value(), equalTo(GaugeData.create(7).value()));
    assertThat("getCumulative is idempotent", cell.getCumulative().value(), equalTo(7L));

    assertThat(cell.getDirty().beforeCommit(), equalTo(true));
    cell.getDirty().afterCommit();
    assertThat(cell.getDirty().beforeCommit(), equalTo(false));

    cell.set(30);
    assertThat(cell.getCumulative().value(), equalTo(30L));

    assertThat(
        "Adding a new value made the cell dirty", cell.getDirty().beforeCommit(), equalTo(true));
  }

  @Test
  public void testEquals() {
    GaugeCell gaugeCell = new GaugeCell(MetricName.named("namespace", "name"));
    GaugeCell equal = new GaugeCell(MetricName.named("namespace", "name"));
    Assert.assertEquals(gaugeCell, equal);
    Assert.assertEquals(gaugeCell.hashCode(), equal.hashCode());
  }

  @Test
  public void testNotEquals() {
    GaugeCell gaugeCell = new GaugeCell(MetricName.named("namespace", "name"));

    Assert.assertNotEquals(gaugeCell, new Object());

    GaugeCell differentDirty = new GaugeCell(MetricName.named("namespace", "name"));
    differentDirty.getDirty().beforeCommit();
    Assert.assertNotEquals(gaugeCell, differentDirty);
    Assert.assertNotEquals(gaugeCell.hashCode(), differentDirty.hashCode());

    GaugeCell differentGaugeValue = new GaugeCell(MetricName.named("namespace", "name"));
    differentGaugeValue.update(GaugeData.create(1));
    Assert.assertNotEquals(gaugeCell, differentGaugeValue);
    Assert.assertNotEquals(gaugeCell.hashCode(), differentGaugeValue.hashCode());

    GaugeCell differentName = new GaugeCell(MetricName.named("DIFFERENT", "DIFFERENT"));
    Assert.assertNotEquals(gaugeCell, differentName);
    Assert.assertNotEquals(gaugeCell.hashCode(), differentName.hashCode());
  }
}
