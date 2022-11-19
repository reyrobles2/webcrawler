package com.udacity.webcrawler.profiler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A method interceptor that checks whether {@link Method}s are annotated with the {@link Profiled}
 * annotation. If they are, the method interceptor records how long the method invocation took.
 */
final class ProfilingMethodInterceptor implements InvocationHandler {

  private final Clock clock;

  // TODO: You will need to add more instance fields and constructor arguments to this class.
  private final Object delegate;
  private final ProfilingState state;
  private final ZonedDateTime startTime;


  ProfilingMethodInterceptor(Clock clock, Object delegate,
                             ProfilingState state, ZonedDateTime startTime) {

    this.clock = Objects.requireNonNull(clock);
    this.delegate = Objects.requireNonNull(delegate);
    this.state = Objects.requireNonNull(state);
    this.startTime = Objects.requireNonNull(startTime);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // TODO: This method interceptor should inspect the called method to see if it is a profiled
    //       method. For profiled methods, the interceptor should record the start time, then
    //       invoke the method using the object that is being profiled. Finally, for profiled
    //       methods, the interceptor should record how long the method call took, using the
    //       ProfilingState methods.

    Object invokedObject;
    Instant startTime = null;
    boolean isProfiled = method.getAnnotation(Profiled.class) != null;
    if (isProfiled) {
      startTime = clock.instant();
    }
    try {
      invokedObject = method.invoke(this.delegate, args);

    } catch (InvocationTargetException ex) {
      throw ex.getTargetException();
    } catch (IllegalAccessException ex) {
      // IllegalAccessException should be caught in a try-catch and
      // wrapped in an unchecked exception such as RuntimeException
      throw new RuntimeException(ex);

    } finally {
      if (isProfiled) {
        Duration duration = Duration.between(startTime, clock.instant());
        state.record(this.delegate.getClass(), method, duration);
      }
    }
    return invokedObject;
  }
}
