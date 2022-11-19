package com.udacity.webcrawler.profiler;

import javax.inject.Inject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

/**
 * Concrete implementation of the {@link Profiler}.
 */
final class ProfilerImpl implements Profiler {

  private final Clock clock;
  private final ProfilingState state = new ProfilingState();
  private final ZonedDateTime startTime;

  @Inject
  ProfilerImpl(Clock clock) {
    this.clock = Objects.requireNonNull(clock);
    this.startTime = ZonedDateTime.now(clock);
  }

  @Profiled
  public Boolean isAnnotatedProfiled(Class<?> klass){
    Method[] methods = klass.getDeclaredMethods();
    if (methods.length != 0) {
      for (Method method : methods){
        if (method.getAnnotation(Profiled.class) != null){
          return true;
        }
      }
      return false;
    } else {
      return false;
    }
  }

  @Override
  public <T> T wrap(Class<T> klass, T delegate) {
//    Objects.requireNonNull(klass);

    // TODO: Use a dynamic proxy (java.lang.reflect.Proxy) to "wrap" the delegate in a
    //       ProfilingMethodInterceptor and return a dynamic proxy from this method.
    //       See https://docs.oracle.com/javase/10/docs/api/java/lang/reflect/Proxy.html.
    if (!isAnnotatedProfiled(Objects.requireNonNull(klass))) {
      throw new IllegalArgumentException(klass.getName() + " has no @Profiled Annotated methods.");
    }

    ProfilingMethodInterceptor profilingMethodInterceptor =
            new ProfilingMethodInterceptor(this.clock, delegate, this.state, this.startTime);

    Object proxy = Proxy.newProxyInstance(
            ProfilerImpl.class.getClassLoader(),
            new Class[]{Objects.requireNonNull(klass)},
            profilingMethodInterceptor
    );

    return (T) proxy;
  }

  @Override
  public void writeData(Path path) {
    // TODO: Write the ProfilingState data to the given file path. If a file already exists at that
    //       path, the new data should be appended to the existing file.

    // Try with resources will automatically close the writer file
    // after executing the Try block
    try (FileWriter fileWriter =
                 new FileWriter(Objects.requireNonNull(path).toFile(), true)){
      writeData(fileWriter);
    } catch (IOException ex) {
      ex.getLocalizedMessage();
    }

  }

  @Override
  public void writeData(Writer writer) throws IOException {
    writer.write("Run at " + RFC_1123_DATE_TIME.format(startTime));
    writer.write(System.lineSeparator());
    state.write(writer);
    writer.write(System.lineSeparator());
  }
}
