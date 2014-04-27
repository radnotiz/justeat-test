justeat-test
============

Let's see an example test for Web applications using Cucumber on JVM

# Prepare
* Install [Java(TM)](http://java.com) (version > 1.6) on your host computer, if not already installed

* Download and unzip https://github.com/radnotiz/justeat-test/archive/master.zip

or install [Git](http://git-scm.com) and type 
```
git clone https://github.com/radnotiz/justeat-test.git
```
on your console.

# Run
Type 
```
gradle test
```
on your console.

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

# Under the hood

Tests are built upon [Cucumber-JVM](https://github.com/cucumber/cucumber-jvm)

Features files under the `src/test/resource` folder specify behavior

Step definitions are implemented in the `src/test/java` directory 

[JUnit](http://junit.org) runners are fired up by [Gradle](http://www.gradle.org) in separate processes

Cucumber integrates to JUnit by @Cucumber annotated JUnit classes

Browser automation is provided by [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/)
