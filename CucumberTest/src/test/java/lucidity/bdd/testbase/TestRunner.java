package lucidity.bdd.testbase;

import java.io.*;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

//import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/lucidity/bdd/features"
		,glue = "lucidity.bdd.scenarios"
		,monochrome = true
		//,plugin = "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html”}"
		//,tags={"@SmokeTest"}
		)

public class TestRunner extends AbstractTestNGCucumberTests   {

//	@AfterClass
//	public static void writeExtentReport() {
//		Reporter.loadXMLConfig(new File("C:\\Lucidity BDD Test\\CucumberTest\\src\\test\\java\\lucidity\\bdd\\configs\\extent-config.xml"));
//		
//	}
	
}
