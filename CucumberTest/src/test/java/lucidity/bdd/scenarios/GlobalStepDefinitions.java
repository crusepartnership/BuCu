package lucidity.bdd.scenarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lucidity.bdd.testbase.TestFunctions;

public class GlobalStepDefinitions extends TestFunctions{
	
	@Given("^For test \"([^\"]*)\", User opens \"([^\"]*)\" and navigates to \"([^\"]*)\"$")
	public void launchBrowserUrl(String sTCName, String sBrowserType, String sUrl) throws Throwable {
//		System.out.println("Running test step "+ ++TCCount +"...");
		
		TCName = sTCName;
		
		initialize();
		
		launchBrowser(sBrowserType, sUrl);
		
		TimeUnit.SECONDS.sleep(5);
	}
	
	/****************************************************************************************
	 * Step Definition: clicks an element <param>
	 * @param sElement - the object identifier to be clicked
	 * @throws Exception
	 ****************************************************************************************/
	@And("^clicks \"([^\"]*)\"$")
	public void action_click(String sElement) throws Throwable {
		System.out.println("Running test step "+ ++TCCount +"...");
		
		BDD_LOGS("Trying to CLICK element...");
		
		if (body_click(sElement)){
			ReportResults("PASS", "Succesfully clicked the object: "+sElement, true);
			BDD_LOGS("Element CLICKED successfully!");
		}else{
			ReportResults("FAIL", "Did not succesfully clicked the object: "+sElement, true);
			BDD_LOGS("Element CLICK failed!");
		}	
		
	    //throw new PendingException();
	}
	
	/****************************************************************************************
	 * Step Definition: inputs a value into an element <param>
	 * @param sElement - the object identifier and input value
	 * @throws Exception
	 ****************************************************************************************/
	@And("^inputs \"([^\"]*)\"$")
	public void actions_input(String sElement) throws Throwable {
		System.out.println("Running test step "+ ++TCCount +"...");
		
		BDD_LOGS("Trying to INPUT on element...");
		
		if (body_input(sElement)){
			ReportResults("PASS", "Input successful! (value;object - "+sElement+")", true);
			BDD_LOGS("Input entered successfully!");
		}else{
			ReportResults("FAIL", "Input failed! (value;object - "+sElement+")", true);
			BDD_LOGS("Input failed!");
		}	

	}

	@Then("^verify Intranet header \"([^\"]*)\" is displayed$")
	public void verifyElement(String sElement) throws Throwable {
	    
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		boolean exists = driver.findElements( By.xpath(sElement) ).size() != 0;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		if(exists) {
			System.out.println("Logged in to Lucidity Intranet successfully!");	
		} else {
			System.out.println("Cannot find Intranet header!");
		}
		
	    //throw new PendingException();
	}

	
}
