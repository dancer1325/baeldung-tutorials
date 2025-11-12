# articles
* 💡[baeldung_articles_list.txt](baeldung_articles_list.txt)💡
  * as soon as NEW article -> you [need to update it](#how-is-it-generated)
## how is it generated?
* == ALL URLs / appear | https://www.baeldung.com/sitemapSOMENUMBER.xml
  * _Examples:_ [post-sitemap1.xml](post-sitemap1.xml)
* `python3 siteMapToText.py`

# Cloning the repository
* Problems:
  * Problem1: "Error"
    * Solution:
      * `git config --global http.postBuffer 5000000

# Courses

* [SPRING](https://www.baeldung.com/learn-spring-course)
* [REST WITH SPRING](https://www.baeldung.com/rest-with-spring-course)
* [SPRING SECURITY](https://www.baeldung.com/learn-spring-security-course)

# Java and Spring Tutorials

* == collection of small tutorials  

# Profile based segregation

* TODO:
We are using maven build profiles to segregate the huge list of individual projects we have in our repository.

The projects are broadly divided into 6 lists: default, default-jdk17, default-jdk22, default-jdk23, default-jdk8 and default-heavy. 

Next, they are segregated further based on the tests that we want to execute.

We also have a parents profile to build only parent modules.

Therefore, we have a total of 13 profiles:

| Profile           | Includes                    | Type of test enabled |
|-------------------|-----------------------------|----------------------|
| default           | JDK21 projects              | *UnitTest            |
| integration       | JDK21 projects              | *IntegrationTest     |
| default-jdk17     | JDK17 projects              | *UnitTest            |
| integration-jdk17 | JDK17 projects              | *IntegrationTest     |
| default-jdk22     | JDK22 projects              | *UnitTest            |
| integration-jdk22 | JDK22 projects              | *IntegrationTest     |
| default-jdk23     | JDK23 projects              | *UnitTest            |
| integration-jdk23 | JDK23 projects              | *IntegrationTest     |
| default-heavy     | Heavy/long running projects | *UnitTest            |
| integration-heavy | Heavy/long running projects | *IntegrationTest     |
| default-jdk8      | JDK8  projects              | *UnitTest            |
| integration-jdk8  | JDK8  projects              | *IntegrationTest     |
| parents           | Set of parent modules       | None                 |

# how to build
## the project?

Though it should not be needed often to build the entire repository at once because we are usually concerned with a specific module.

But if we want to, we can invoke the below command from the root of the repository if we want to build the entire repository with only Unit Tests enabled:

`mvn clean install -Pdefault,default-heavy`

or if we want to build the entire repository with Integration Tests enabled, we can do:

`mvn clean install -Pintegration,integration-heavy`

Analogously, for the JDK8 projects the commands are:

`mvn clean install -Pdefault-jdk8`

and

`mvn clean install -Pintegration-jdk8`

## 1! module?
To build a specific module, run the command: `mvn clean install` in the module directory.

It can happen that your module is part of a parent module e.g. `parent-boot-1`,`parent-spring-5` etc, then you will need to build the parent module first so that you can build your module.
We have created a `parents` profile that you can use to build just the parent modules, just run the profile as:
`mvn clean install -Pparents`


## modules | root of the repository?

To build specific modules from the root of the repository, run the command: `mvn clean install --pl akka-modules,algorithms-modules -Pdefault` in the root directory.

Here `akka-modules` and `algorithms-modules` are the modules that we want to build and `default` is the maven profile in which these modules are present.

# how to run 1 Spring Boot module?
To run a Spring Boot module, run the command: `mvn spring-boot:run` in the module directory.

# how to run Tests?

The command `mvn clean install` from within a module will run the unit tests in that module.
For Spring modules this will also run the `SpringContextTest` if present.

To run the integration tests, use the command:

`mvn clean install -Pintegration` or

`mvn clean install -Pintegration-heavy` or

`mvn clean install -Pintegration-jdk8`

depending on the list where our module exists
