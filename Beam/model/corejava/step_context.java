package org.apache.beam.runners.core;

/**
 * The context in which a specific step is executing, including access to state and timers.
 *
 * <p>This interface exists as the API between a runner and the support code, but is not user
 * facing.
 *
 * <p>These will often be scoped to a particular step and key, though it is not required.
 */
public interface StepContext {

  StateInternals stateInternals();

  TimerInternals timerInternals();
}
