justeat-test
============

Let's see an example test for Web applications using Cucumber on JVM with an experimental way of parallel execution (tests/scenarios are statically distributed over the test runner processes that are forked by Gradle) 

# Prepare
* Install [Java(TM)](http://java.com) (version > 1.6) on your host computer, if not already installed

* Either download and unzip https://github.com/radnotiz/justeat-test/archive/master.zip 
 
 or 
 
 install [Git](http://git-scm.com) and type 
 ```
 git clone https://github.com/radnotiz/justeat-test.git
 ```
 on your console.

* Be sure [Firefox](http://www.mozilla.org/en-US/firefox/) is installed on your local host.

# Run
Change your working directory
```
cd justeat-test
```
and type 
```
gradle clean test
```
on your console.

To use real browser instead of the in memory headless driver, add an argument as follows
```
gradle clean test -Dbrowser.driver=firefox
```

# Check results
Follow the console output and find the location of test results and open the html report in a browser:
```
:clean
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:compileTestJava
:processTestResources
:testClasses
:test
Check test results at build/reports/test/index.html

BUILD SUCCESSFUL

Total time: 54.68 secs
```

Alternatively you may want to use XML output files as well at `build/test-results/' folder

# Under the hood

Tests are built upon [Cucumber-JVM](https://github.com/cucumber/cucumber-jvm)

Features files under the `src/test/resource` folder specify behavior

Step definitions are implemented in the `src/test/java` directory 

[JUnit](http://junit.org) runners are fired up by [Gradle](http://www.gradle.org) in separate processes

Cucumber integrates to JUnit by @Cucumber annotated JUnit classes

Browser automation is provided by [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/)

# Known issues

* When font size is set to other than 100% on Win7 click won't work with Firefox. [Details](https://code.google.com/p/selenium/issues/detail?id=7223)
