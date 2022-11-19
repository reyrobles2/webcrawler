package com.udacity.webcrawler.main;

import com.google.inject.Guice;
import com.udacity.webcrawler.WebCrawler;
import com.udacity.webcrawler.WebCrawlerModule;
import com.udacity.webcrawler.json.ConfigurationLoader;
import com.udacity.webcrawler.json.CrawlResult;
import com.udacity.webcrawler.json.CrawlResultWriter;
import com.udacity.webcrawler.json.CrawlerConfiguration;
import com.udacity.webcrawler.profiler.Profiler;
import com.udacity.webcrawler.profiler.ProfilerModule;

import javax.inject.Inject;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Objects;

public final class WebCrawlerMain {

  private final CrawlerConfiguration config;

  private WebCrawlerMain(CrawlerConfiguration config) {
    this.config = Objects.requireNonNull(config);
  }

  @Inject
  private WebCrawler crawler;

  @Inject
  private Profiler profiler;

  private void run() throws Exception {
    Guice.createInjector(new WebCrawlerModule(config), new ProfilerModule()).injectMembers(this);

    CrawlResult result = crawler.crawl(config.getStartPages());
    CrawlResultWriter resultWriter = new CrawlResultWriter(result);
    // TODO: Write the crawl results to a JSON file (or System.out if the file name is empty)
    if (!config.getResultPath().isEmpty()){
      // Get the Crawl Result Filename from config.getResultPath()
      Path pathCrawlResult = Path.of(config.getResultPath());
      resultWriter.write(pathCrawlResult);
    }
    else{
      // If config.getResultPath() is empty use the Standard Output (System.out) as the file name
      Writer stdWriterResultPath = new OutputStreamWriter(System.out);
      resultWriter.write(stdWriterResultPath);
      stdWriterResultPath.flush();
    }

    // TODO: Write the profile data to a text file (or System.out if the file name is empty)
    if (!config.getProfileOutputPath().isEmpty()){
      // Get the Profile Filename from config.getProfileOutputPath()
      Path pathProfileOutput = Path.of(config.getProfileOutputPath());
      profiler.writeData(pathProfileOutput);
    }
    else{
      // If config.getProfileOuputPath() is empty use the Standard Output (System.out) as the file name
      Writer stdWriterProfileOutput = new OutputStreamWriter(System.out);
      profiler.writeData(stdWriterProfileOutput);
      stdWriterProfileOutput.flush();
    }


  }

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: WebCrawlerMain [starting-url]");
      return;
    }

//    Path path = Path.of(args[0]);
//    Path path = Path.of("example.com/foo");
    CrawlerConfiguration config = new ConfigurationLoader(Path.of(args[0])).load();
//    CrawlerConfiguration config = new ConfigurationLoader(path).load();

    new WebCrawlerMain(config).run();
  }
}
