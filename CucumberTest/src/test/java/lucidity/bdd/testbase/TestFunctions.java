package lucidity.bdd.testbase;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class TestFunctions {

	public static WebDriver driver = null;	
    public static WebDriverWait wait = null;
    public static ExtentReports eReport = null;
    public static ExtentTest eReport_test = null;
    public static Logger logger = null;
   
    
    public static String winHandleBefore = null;
	public static String BrowserType = null;
	public static String TCName = null;
	public static String TCDescription = null;
    public static String FolderName = null;
	
    
    public static int iResult = 0; 
	public static int TCCount = 0;
	
	/****************************************************************************************
	 * Reset all parameters and variable before the test run
	 ****************************************************************************************/
	public void initialize() throws Exception {
		
		
		TCCount = 0;
		driver = null;
		//TCDescription = Files.readAllLines(Paths.get(System.getProperty("user.dir")+"\\src\\test\\java\\lucidity\\bdd\\features\\Search_Test.feature")).get(2);
		
		BDD_LOGS("TCDescription - "+TCDescription);

		DateFormat screenshotFormat = new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss");
        Date date = new Date();
        FolderName = "Lucidity_Test_Report-" + TCName + "_" + screenshotFormat.format(date);
        
        eReport = new ExtentReports(System.getProperty("user.dir")+"/target/"+FolderName+"/"+TCName+".html");
		
        eReport_test = eReport.startTest(TCName,"");
        
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/test/java/lucidity/bdd/configs/log4j.properties");
        
        logger =  Logger.getLogger("devpinoyLogger");
        
	}
	
	/****************************************************************************************
	 * Creates test reports
	 ****************************************************************************************/
	public void ReportResults(String sResult, String sMessage, boolean bScreenshot) throws Exception{
		
		DateFormat screenshotFormat = new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss");
		Date date = new Date();
        
        
        //String sScreenshot = this.getClass().getSimpleName() + screenshotFormat.format(date);
        String sScreenshot = TCName + "_" + screenshotFormat.format(date);
        
        try {
        	
        	if (sResult.equalsIgnoreCase("PASS")){
        		iResult = 1;
        	} else if(sResult.equalsIgnoreCase("FAIL")){
        		iResult = 2;
        	}else if(sResult.equalsIgnoreCase("DONE")){
        		iResult = 3;
        	}else if(sResult.equalsIgnoreCase("TESTNAME")){
        		iResult = 4;
        	}

        	eReport_test.setDescription(TCName);
        	
        	switch(iResult){
        	
        	case 1:
        		//Reporter.log("PASS: "+ sMessage+ ";\n", true);
                BDD_LOGS("Test step "+TCCount+" - PASSED");
                
                if (bScreenshot) {
                	takeScreenshot(sScreenshot, FolderName);
                    eReport_test.log(LogStatus.PASS, sMessage + eReport_test.addScreenCapture(sScreenshot+".png"));
                } else {
                    eReport_test.log(LogStatus.PASS, sMessage);
                }
                
                break;
        	
        	case 2:
        		//Reporter.log("FAIL: "+ sMessage+ ";\n", true);
                BDD_LOGS("test step "+TCCount+" - FAILED");
                
                if (bScreenshot){
                	takeScreenshot(sScreenshot, FolderName);
                	eReport_test.log(LogStatus.FAIL, sMessage + eReport_test.addScreenCapture(sScreenshot+".png"));
                } else {
                	eReport_test.log(LogStatus.FAIL, sMessage);
                }
                
                eReport.endTest(eReport_test);
                eReport.flush();
                Assert.fail("FAIL: "+ sMessage);
                break;
                     
        	case 3:
                Reporter.log("DONE:"+ sMessage + ";\n", true);
                System.out.println("DONE: "+ sMessage);
                eReport.endTest(eReport_test);
                eReport.flush();
                break;
                
        	case 4:
                Reporter.log("TestName: "+this.getClass().getSimpleName()+"\n",true);
                System.out.println("TestName: "+this.getClass().getSimpleName());
                break;
                
        	default: Reporter.log("Please enter correct values to the parameters");
           
        	}
        	
        	eReport.flush();
        	
        } catch(Exception e) {
        	ReportResults("FAIL", "Unexpected error/exception occured in ReportResults(): " + e.getMessage(), true);
            System.out.println(e.getMessage()+ "/n" + e.getStackTrace());
        }
		
		
		
	}
	
	/****************************************************************************************
	 * Takes screenshots
	 ****************************************************************************************/
	public void takeScreenshot(String fileName, String folderName) throws Exception{
		
		try{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile,  new File(System.getProperty("user.dir")+"/target/"+folderName+"/"+fileName+".png"));
		} catch(Exception e) {
            ReportResults("FAIL", "Unexpected error/exception occured in takeScreenshot(): " + e.getMessage(), true);
            System.out.println(e.getMessage()+ "/n" + e.getStackTrace());
        }
		
	}
	
	/****************************************************************************************
	 * Launches browser with URL
	 ****************************************************************************************/
	public void launchBrowser(String sBrowserType, String sUrl) throws Exception {
		
		TestFunctions.BrowserType = sBrowserType;
		boolean isMacOs = System.getProperty("os.name").toLowerCase().startsWith("mac os x");
		
		BDD_LOGS("Launching "+sBrowserType+" browser and navigating to "+sUrl);
		
		try {
			if(sBrowserType.contains("Edge")) {
				if (isMacOs) {
					System.out.print("Edge is not available on Mac.  Exiting...");
					System.exit(1);
				}
				System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"/webdriver/MicrosoftWebDriver.exe");
				
				driver = new EdgeDriver();
			}else if(sBrowserType.contains("Chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("chrome.switches", "--disable-extensions");
		        options.addArguments("start-maximized");
		        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/webdriver/chromedriver"+ ((!isMacOs) ? "exe" : ""));  
		        
				driver = new ChromeDriver(options);
			}else if(sBrowserType.contains("Firefox")){
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/webdriver/geckodriver"+ ((!isMacOs) ? "exe" : ""));  
				
				driver = new FirefoxDriver();
			}
			
		} catch (Exception e){
			ReportResults("FAIL", "Unexpected error/exception occurred: " + e.getMessage(), true);
            System.out.println(e.getMessage()+ "/n" + e.getStackTrace());
		}
			
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(sUrl);
		ReportResults("PASS", "Launched browser and navigated to site successfully!", true);
		BDD_LOGS("Launched browser and navigated to site successfully!");
		
	}
	
	/****************************************************************************************
	 * Body for: action_click(<param>)
	 ****************************************************************************************/
	public boolean body_click(String sElement) throws Exception {
		
		String sValue = null;
		String sObjId = null;
		
		boolean bOutcome= false;
		 
		try {
			
			if (sElement.contains(";")) {
				String[] parts = sElement.split(";");
				sValue = parts[0];
	            sObjId = parts[1]; 
	             
	            BDD_LOGS("Input value is: "+sValue);
	            BDD_LOGS("Element ID is: "+sObjId);
			} else {
				sObjId = sElement;
				BDD_LOGS("Element ID is: "+sObjId);
			}
			
			//Check if needed to switch to a frame or a window
	        //switchTo(sObjId);
			
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sObjId)));
//		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sObjId)));
			
		    List<WebElement> oList = driver.findElements(By.xpath(sObjId));
		    
		    if (oList.size()>0) {
		    	action_scrollTo(oList.get(0));
		    	winHandleBefore = driver.getWindowHandle();
		    	
		    	oList.get(0).click();
                
                bOutcome = true;
		    } 
			
		 } catch(Exception e){
			 ReportResults("FAIL", "Unexpected error/exception occured in body_click(): " + e.getMessage(), true);
             System.out.println(e.getMessage()+ "/n" + e.getStackTrace());
		 }
		
		return bOutcome;
	}
	

	/****************************************************************************************
	 * Body for: action_input(<param>)
	 ****************************************************************************************/
	public boolean body_input(String sElement) throws Exception {
		
		String sValue = null;
		String sObjId = null;
		
		boolean bOutcome= false;
		 
		try {
			
			if (sElement.contains(";")) {
				String[] parts = sElement.split(";");
				sValue = parts[0];
	            sObjId = parts[1]; 
	             
	            BDD_LOGS("Input value is: "+sValue);
	            BDD_LOGS("Element ID is: "+sObjId);
			} else {
				BDD_LOGS("ERROR - No input value detected!"+sObjId);
			}
			
			
			List<WebElement> oList = driver.findElements(By.xpath(sObjId));
			
			if (oList.size()>0) {
				oList.get(0).click();
                oList.get(0).clear();
                oList.get(0).sendKeys(sValue);
                
                bOutcome = true;
			} else {
				BDD_LOGS("ERROR - Object does not exist"+sObjId);
			}
			
			
			
		 } catch(Exception e){
			 ReportResults("FAIL", "Unexpected error/exception occured in body_input(): " + e.getMessage(), true);
             System.out.println(e.getMessage()+ "/n" + e.getStackTrace());
		 }
		
		return bOutcome;
	}
	
	
	public void action_scrollTo(WebElement wElement) throws Exception {
		
		boolean oVisible = wElement.isDisplayed();
		
		try {
			
			Actions actions = new Actions(driver);
			
			while (oVisible=false){
	             actions.sendKeys(Keys.PAGE_DOWN);
	         }
			
			TimeUnit.SECONDS.sleep(1);
	        //Actions actions = new Actions(driver);
	        actions.moveToElement(wElement);
	        actions.perform();
	        
		} catch(Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occurred in action_scrollTo(): " + e.getMessage(), true);
	        System.out.println(e.getMessage()+ "/n" + e.getStackTrace());
		}
		
		
	}
	
	public void BDD_LOGS(String sMessage) throws Exception {
		System.out.println("CUCUMBER LOG: "+sMessage);
	}
	
}
