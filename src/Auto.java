import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;



public class Auto 
{
	private WebDriver webDriver = new ChromeDriver();
	private AutoTrader auto;
	//private WebDriver webDriver = new FirefoxDriver();
	
	Actions builder = new Actions(webDriver);
	
	private static ExtentReports report; private ExtentTest test; 
	private static String reportFilePath = "report.html";
	
	private static SpreadSheetReader reader1; 
	static ArrayList<String> spreadData = new ArrayList<String>();
	
	private String nameBuilder= "Test "; 
	private static int testNo = 1;
	
	static String email = "";
	static String password = "";
	
	@BeforeClass
	public static void setUp()
	{
		report = new ExtentReports();
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
		extentHtmlReporter.config().setChartVisibilityOnOpen(true);
		extentHtmlReporter.config().setReportName("ReportName");
        extentHtmlReporter.config().setDocumentTitle("DocumentTitle");
        
        report.attachReporter(extentHtmlReporter);
       
        reader1 = new SpreadSheetReader("C:\\Users\\Administrator\\workspace\\autoTrader\\sheet1.xlsx");
	}
	
	@BeforeClass
	public static void beforeClass()
	{
		System.out.println("before class");	
		
		spreadData.addAll(reader1.readRow(1, "Input Data")); 	
		email = spreadData.get(0);
		password = spreadData.get(1);
	}
	
	@Before
	public void before()
	{
		System.out.println("before");
		
		auto = PageFactory.initElements(webDriver,AutoTrader.class);
		
		test = report.createTest(nameBuilder + testNo);
		testNo++;
	}
	
	@Test
	public void SpreadSheetReadTest()
	{
		spreadData.addAll(reader1.readRow(1, "Input Data")); 	
		email = spreadData.get(0);
		password = spreadData.get(1);
		String builder = "";
		builder = email + " " + password;
		System.out.println(email + " " + password);
		
		assertEquals(email + " " + password, builder);
		test.log(Status.INFO, "Spreadsheet Test");
		test.pass("Successful Spreadsheet test performed");
	}
	
	@Test
	public void CreateAccounTest() throws IOException
	{
		System.out.println("test");		
		webDriver.navigate().to("http://www.autotrader.co.uk/");
		auto.SignInButton.click();
		auto.SignUpButton.click();
		auto.EmailInput.sendKeys(email);
		auto.passwordInput.sendKeys(password);
		take(webDriver, "screen1");
		wait1("#social--signup--submit");
		auto.SignupNowbutton.click();	
		take(webDriver, "screen2");
		test.log(Status.INFO, "Create Account Test");
		test.pass("Successful Account Creation Performed");
		test.addScreenCaptureFromPath("C:\\Users\\Administrator\\workspace\\autoTrader\\screen1.jpg");
		test.addScreenCaptureFromPath("C:\\Users\\Administrator\\workspace\\autoTrader\\screen2.jpg");
		assertEquals("signed in my account icon",auto.accountHover.getText());
		webDriver.close();
	}
	
	@Test
	public void LoginTest() throws IOException
	{	
		webDriver.navigate().to("http://www.autotrader.co.uk/");
		auto.SignInButton.click();
		take(webDriver,"screen4");
		auto.emailLogin.sendKeys(email);
		auto.passwordLogin.sendKeys(password);
		take(webDriver,"screen3");
		test.log(Status.INFO, "Login Test");
		test.pass("Successful Login Performed");
		test.addScreenCaptureFromPath("C:\\Users\\Administrator\\workspace\\autoTrader\\screen3.jpg");
		test.addScreenCaptureFromPath("C:\\Users\\Administrator\\workspace\\autoTrader\\screen4.jpg");
		webDriver.close();		
	}
	
	@Test
	public void LogoutTest() throws IOException 
	{	
		webDriver.navigate().to("http://www.autotrader.co.uk/");
		auto.SignInButton.click();
		take(webDriver,"screen5");
		auto.emailLogin.sendKeys(email);
		auto.passwordLogin.sendKeys(password);
		auto.SignInNowButton.click();
		take(webDriver,"screen6");
		builder.moveToElement(auto.accountHover).perform();
		auto.signOut.click();
		take(webDriver,"screen7");
		test.log(Status.INFO, "Logout Test");
		test.pass("Successful Logout Performed");
		test.addScreenCaptureFromPath("C:\\Users\\Administrator\\workspace\\autoTrader\\screen5.jpg");
		test.addScreenCaptureFromPath("C:\\Users\\Administrator\\workspace\\autoTrader\\screen6.jpg");
		test.addScreenCaptureFromPath("C:\\Users\\Administrator\\workspace\\autoTrader\\screen7.jpg");
		webDriver.close();		
	}
	
	
	@Ignore //incomplete
	public void carSearchTest() throws IOException
	{	
		spreadData.clear();
		webDriver.navigate().to("http://www.autotrader.co.uk/");
		
		
		spreadData.addAll(reader1.readRow(1, "search")); 	
		String postcode = spreadData.get(0);
		
//		System.out.println(auto.distanceSelect.getText()); only have half an hour left, wont be able to setup selectors for each field with so will have a hardcoded search for now
//		String distance = spreadData.get(1);
		
		auto.postcode.sendKeys(postcode);
		
		builder.moveToElement(auto.distanceSelect).perform(); //need to see examples of hover menu manipulation
		builder.click(auto.distanceSelect).perform();
		wait1("#radius > option:nth-child(4)");
		builder.moveToElement(auto.distanceActual).perform();
		builder.click(auto.distanceActual).perform();
//		auto.makeSelect.sendKeys("bb");
//		
//		auto.model.click();
//		auto.model.sendKeys("3");
//		
//		builder.moveToElement(auto.minCost).perform();
//		builder.click().perform();
//		builder.moveToElement(auto.minCostActual).perform();
//		builder.click();
	}
	
	@Ignore
	public void test5() throws IOException
	{
					
	}
	
	@After
	public void after()

	{
		System.out.println("after");	
		report.flush();
	}
	
	@AfterClass
	public static void afterClass()
	{
		System.out.println("After class");
	}
	
	public void wait1(final String css) 
	{
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver) //global - instant in before
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		@SuppressWarnings("unused")
		WebElement foo = wait.until (new Function<WebDriver, WebElement>()    //
				{
					public WebElement apply(WebDriver driver)
					{
						return driver.findElement(By.cssSelector(css));
					}
				}
			);
		
		
	}
	
	public static String take(WebDriver webDriver, String fileName)  
	 {
		 try
		 {
			 
	        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
	        String pathname = System.getProperty("user.dir") + File.separatorChar + fileName +".jpg";
	        FileUtils.copyFile(scrFile, new File(pathname));
	        
	        System.out.println("File Saved at: " + pathname);
	        return pathname;
		 }
		 
		 catch(IOException ioe)
		 {
			ioe.printStackTrace();
		 }
		 return "";
	 }
	

	
	
	
}
