package com.MavenTestngLog4j;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class MavenLog4JXML 
{
	static Logger logger=Logger.getLogger(MavenLog4JXML.class);
	public WebDriver driver;
  @Test
  public void loginWithValidDetails() 
  {
	  PropertyConfigurator.configure("E:\\eclipse\\MavenLog4jTestNG\\src\\test\\resources\\log4j.properties");
	  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("Pranali@1");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Passw@rd1");
	  driver.findElement( By.xpath("//input[@name='login']")).click();
	  boolean flag=driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")).isDisplayed();
      System.out.println("Home page is displayed"+flag);
      driver.findElement(By.linkText("SIGN-OFF")).click();
	  logger.debug("User has signed off from Mercury Tours application");
  }
  
  @Test
  public void loginWithInvalidDetails()
  {
	  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("Pranali1");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Passw@rd1");
	  driver.findElement( By.xpath("//input[@name='login']")).click();
	  boolean flag=driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")).isDisplayed();
      logger.warn("Home page is displayed"+flag);
  }
  @BeforeMethod
  public void beforeMethod() 
  {
	  Set<Cookie> cookies=driver.manage().getCookies();
	  for(Cookie cookie:cookies)
	  {
		  logger.info(cookie.getName());
	  }
  }

  @AfterMethod
  public void afterMethod() throws IOException 
  {
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFileToDirectory(src, new File("E:\\eclipse\\MavenLog4jTestNG\\src\\test\\resources\\Screenshots\\"));
	  logger.info("Screenshot captured");
  }

  @BeforeClass
  public void maximizeWindow() 
  {
	  driver.manage().window().maximize();
	  logger.info("driver maximized");
  }

  @AfterClass
  public void afterClass() 
  {
  }

  @BeforeTest
  public void enterUrl() 
  {
	  driver.get("http://newtours.demoaut.com/mercurywelcome.php");
  }

  @AfterTest
  public void closeBrowser() 
  {
	  driver.manage().deleteAllCookies();
  }

  @BeforeSuite
  public void openBrowser() 
  {
	  System.setProperty("webdriver.chrome.driver", "E:\\Selenum\\29062019\\chromedriver_win32\\chromedriver.exe");
	  driver=new ChromeDriver();
	  logger.info("Chrome driver is up");  
  }

  @AfterSuite
  public void afterSuite() 
  {
	  driver.quit();
	  logger.info("Driver is closed");
  }

}
