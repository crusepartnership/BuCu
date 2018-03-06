# BuCu
Business Cucumber or Bucu is an automated testing framework which uses Cucumber to run tests written in a high-level (business) language. This language is called Gherkin which is then translated by the framework to run tests in Selenium. Cucumber is a BDD (Business Driven Development) based management tool.
---

## Getting Started

Cucumber framework runs on Java and for this, we've used Eclipse as the IDE. Eclipse provides as an array of readily available plugins which gives Cucumber more power and versatility. 
---

In the meantime, the framework is only available on Windows OS.
---

### Prerequisites

* Basic to advance knowledge on Java
* Basic to advance knowledge on Selenium WebDriver
---

### Installing

To get started with the setup, please visit this [wiki](https://integralcs.atlassian.net/wiki/spaces/DEVOPS/pages/347832321).

---
## Running the tests

Tests on Cucumber framework are written on Gherkin (see example below).
```
*Feature*: Search feature for users
This feature is very important because it will allow users to filter products
*Scenario*: When a user searches, without spelling mistake, for a product name present in inventory. All the products with similar name should be displayed
_Given_ User is on the main page of www.myshopingsite.com
_When_ User searches for laptops
_Then_ search page should be updated with the lists of laptops
```
The above example is basic script that can be improved by inserting parameters for each step/action.
```
*Feature*: Search feature for users
This feature is very important because it will allow users to filter products
*Scenario*: When a user searches, without spelling mistake, for a product name present in inventory. All the products with similar name should be displayed
_Given_ User is on the main page of www.myshopingsite.com
_When_ User searches for laptops
_Then_ search page should be updated with the lists of laptops
```




Tests on are run with the help of Maven which utilizes execution, reporting, documentation, and all other components on the framework.

For more info on how to run tests, please visit this [wiki]()



### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc

