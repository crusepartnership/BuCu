# BuCu

Business Cucumber or Bucu is an automated testing framework which uses Cucumber to run tests written in a high-level (business) language. This language is called Gherkin which is then translated by the framework to run tests in Selenium. Cucumber is a BDD (Business Driven Development) based automated test management tool.

---
### Getting Started
---
Cucumber framework runs on Java and for this, we've used Eclipse as the IDE. Eclipse provides as an array of readily available plugins which gives Cucumber more power and versatility.

---
### Prerequisites
---
* Basic to advance knowledge on Java
* Basic to advance knowledge on Selenium WebDriver

_In the meantime, BuCu is only available on Windows OS._

---
### Installing
---
To get started with the setup, please visit this [wiki](https://integralcs.atlassian.net/wiki/spaces/DEVOPS/pages/347832321).

---
### Running the tests
---
Tests on Cucumber framework are written on Gherkin (see example below).

```Gherkin
Feature: Search feature for users

Scenario: When a user searches, without spelling mistake, for a product name present in inventory. 
All the products with similar name should be displayed

Given User is on the main page of www.myshopingsite.com
And User inputs Laptop into the Search Bar
And User clicks the search button
Then verify search page updated with the list of laptops
```
Each line after **Feature** and **Scenario** are called _steps/actions_. These lines correspond to a specific code or module on the framework which is designed to perform a specific automated Selenium task (e.g. click, enter, verify).

The above example is basic script that can be improved by inserting parameters for each step/action.

```Gherkin
Feature: Search feature for users

Scenario: When a user searches, without spelling mistake, for a product name present in inventory. 
All the products with similar name should be displayed

Given User is on the main page of <ShopHomepage>
And User inputs <SearchID> into <TxtSearchField>
And User clicks <BtnSearch>
Then verify search page updated with the <LstLaptop>

Examples:
| ShopHomepage | SearchID | TxtSearchField | BtnSearch | LstLaptop |
| www.myshopingsite.com | Laptop | //*[@id='fields_search'] | //*[@id='btn_search'] | //list/*[@id='laptops'] |
```
The objects inside <> are called parameters which Gherkin uses to identify variables which holds certain values which are defined on the *Examples* section based on their occurrence. These parameters may contain texts and object identifiers (object IDs, xpaths, etc.) which will be used to input data or other specific Selenium automated *steps/actions* on the target objects.

Tests on are run with the help of Maven which utilizes execution, reporting, documentation, and all other components on the framework.

For more info on how to run tests, please visit this [wiki]()

### And coding style tests

Explain what these tests test and why

```
Give an example
```

### Built With
* [Cucumber](https://cucumber.io/) - The test automation management framework used
* [Java](https://java.com/) - The language used to build the framework
* [Eclipse Oxygen](https://www.eclipse.org/ide/) - The IDE to wrap the framework
* [Selenium WebDriver](https://www.eclipse.org/ide/) - The automation tool used
* [Maven](https://maven.apache.org/) - Dependency Management

### Versioning

Coming soon...

### Authors

* **RJ Fabella** - *Initial work*

### Source
* http://toolsqa.com/cucumber/


## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc

