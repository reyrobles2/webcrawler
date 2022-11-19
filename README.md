# webcrawler
Udacity - PROJECT 5 - Parallel Web Crawler

Welcome! This is your first week at the startup, UdaciSearch. You've been hired on as an Engineer, and you're really excited to make a big splash. UdaciSearch is interested in figuring out popular search terms on the internet in order to improve the SEO of their clients. Everyone wants to pop up at the top of a potential user's search!
You are given the source code for your company's legacy web crawler, which is single-threaded. You notice it's a bit slow, and you quickly realize a way to improve its performance and impress your new manager. You can upgrade the code to take advantage of multi-core architectures to increase crawler throughput. Furthermore, you will measure the performance of your crawler to prove that, given the same amount of time, the multi-threaded implementation can visit more web pages than the legacy implementation. It might just be your first week, but you're set to impress!

### Getting Started
#### Dependencies
•	Java 11 or higher
•	Maven 3.6.3 or higher
•	IntelliJ IDEA

#### Installation
•	Download the JDK 14. Accept the license agreements and run the downloaded installer.
•	Follow the official installation and run mvn -version in a terminal to make sure you have at least version 3.6.3 installed.
•	Download the Community Edition of IntelliJ IDEA. Run the downloaded installer.

#### Using the Terminal
In this project, you will be executing commands in a terminal to compile your code, run unit tests, and run the web crawler app. If you are using IntelliJ (recommended), you can use the built-in Terminal tab by clicking the "Terminal" button near the lower left corner of the main window, or by pressing the keyboard shortcut (Alt + F12 on Windows/Linux, or ⌥ + F12 on Mac).
If you already have another terminal application you like to use, such as the terminal that comes with the operating system you are running, that will also work with this project. If you go this route, make sure you run the terminal from the root directory of the project. In other words, the terminal's working directory should be the same folder where the pom.xml file is located.

Note: If you are using IntelliJ's Terminal tab, it automatically opens the terminal in the project's root directory.

Once you have a terminal open, make sure everything is working by typing (or copy-pasting) the following mvn command into the terminal and pressing the Enter key:
mvn test -Dtest=WebCrawlerTest -DcrawlerImplementations=com.udacity.webcrawler.SequentialWebCrawler
(In case you're wondering, "mvn" is short for "Maven". This command tells Maven to run the unit test named WebCrawlerTest, and uses a command-line flag to set the system property crawlerImplementations to "com.udacity.webcrawler.SequentialWebCrawler".)
If it worked, you should see something like this at the bottom of the terminal:

    [INFO] Results:
    [INFO] 
    [INFO] Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
    [INFO] 
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  2.017 s
    [INFO] Finished at: 2020-09-17T21:35:46-04:00
    [INFO] ------------------------------------------------------------------------

#### Unit Testing
The starter code comes with some JUnit 5 tests to check your work. You are not expected to write additional tests yourself. The instructions will tell you when and how to run the unit tests for each feature as you go.
If you do decide to write more tests yourself, please do not modify any of the existing tests. You can add new test files and you can add @Test and @ParameterizedTest methods to the existing test files, but please do not touch any of the existing @Test or @ParameterizedTest methods.



##### Acknowledgement to the following sources:

Udacity - Cognizant Full Stack Developer Nanodegree Program https://www.udacity.com/; Java https://www.java.com/; Stack Overflow https://stackoverflow.com/; GitHub users https://github.com; Baeldung https://www.baeldung.com/; Tutorialspoint https://www.tutorialspoint.com/; GeeksforGeeks https://www.geeksforgeeks.org/; javaTpoint https://www.javatpoint.com/
