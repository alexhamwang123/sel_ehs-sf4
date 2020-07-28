//This program enters the EHS UAT and finds an existing user in the system.
//EHS UAT > Admin > User Admin > User Management > Enter Badge #/First Name/Last Name > Search

package com.UserAdmin;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//@Test
@Test
public class FindExistingUser {

	public void FindExistingUser() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
		try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
			actions.build().perform(); }
		catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on 'User Admin'
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Admin')]")).click();
		Thread.sleep(1000);


		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Enter the Badge Number in the Badge# search field
	    //driver.findElement(By.id("searchBadgeNo")).sendKeys("X00001700");
	    
	    //Or, if you want to find by the first name
		String findExistingUserStr = prop.getProperty("findExistingUser");
	    driver.findElement(By.id("badgeNo")).sendKeys(findExistingUserStr);
	    
	    //Or, if you want to find by the last name 
	    //driver.findElement(By.id("searchLastName")).sendKeys("Varu");
	    
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //Click on Search
	    driver.findElement(By.xpath("//button[contains(text(),'Find Users')]")).click();

	    Thread.sleep(3500);
	    driver.quit();
		

	}

}
