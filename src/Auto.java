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
	
	private static ExtentReports report; private ExtentTest test; private static String reportFilePath = "report.html";
	
	private static SpreadSheetReader reader1; ArrayList<String> spreadData = new ArrayList<String>();
	
	private String nameBuilder= "Test "; private static int testNo = 1;
	
	@BeforeClass
	public static void setUp()
	{
		report = new ExtentReports();
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
		extentHtmlReporter.config().setChartVisibilityOnOpen(true);
		extentHtmlReporter.config().setReportName("ReportName");
        extentHtmlReporter.config().setDocumentTitle("DocumentTitle");
        
        report.attachReporter(extentHtmlReporter);
       
        reader1 = new SpreadSheetReader("C:\\Users\\Administrator\\workspace\\test2\\spread1.xlsx");
	}
	
	@BeforeClass
	public static void beforeClass()
	{
		System.out.println("before class");	
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
	public void test() throws IOException
	{
		System.out.println("test");		
		assertEquals(1,1);
		
					
	}
	
	@Ignore
	public void test2() throws IOException
	{	
		
	}
	
	@Ignore
	public void test3()
	{
		spreadData.addAll(reader1.readRow(0, "Input Data")); 
		spreadData.addAll(reader1.readRow(1, "Input Data")); 	
		for(String x : spreadData)
		{
			System.out.println(x);
		}
		test.log(Status.INFO, "Spreadsheet Test");
		test.pass("Successful Spreadsheet test performed");
	}
	
	@Ignore
	public void test4() throws IOException
	{	
		
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
	
	public void wait1(final String css) //check if final fucks it up
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
