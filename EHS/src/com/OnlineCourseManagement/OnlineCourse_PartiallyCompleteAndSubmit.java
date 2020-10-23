//This script is to test what the system prompts after the user
//answers partial questions and click on 'Grade Quiz'

package com.OnlineCourseManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Test
//Mark
public class OnlineCourse_PartiallyCompleteAndSubmit {
	
	public void OnlineCourse_PartiallyCompleteAndSubmit() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();
		WebDriverWait Wait= new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");

		driver.get(urladdr);
try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
actions.build().perform(); }
catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }

		driver.manage().window().maximize();

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(3000);



		//got to the prerequisite and complete it
		Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

		Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("kimi-online-006"); //checklist name on localhost

		Thread.sleep(3000);



		//Click on the Enroll button
		Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();

		Thread.sleep(3000);
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}


		//Click on 'Default-English' Language option
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		WebElement Language_English=driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
		js2.executeScript("arguments[0].click();",Language_English);


		Thread.sleep(3000);


		try {
			if (driver.findElement(By.xpath("//button[contains(text(),'OK')]")).isDisplayed()) {
				driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
			}
		}
		catch(Exception e){
			System.out.println("There is no 'go back to last point Btn' Window");
		}

		Thread.sleep(3000);

		//Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
		driver.findElement(By.xpath("//input[@value='10039']")).click(); // Cat

		Thread.sleep(2000);

		driver.close();
		Thread.sleep(2000);

		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.findElement(By.partialLinkText("Courses")).click();
		Thread.sleep(4500);

		//Search the Course
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("kimi-online-006");
		Thread.sleep(1500);
		String working = "";

		working = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[3]")).getAttribute("innerHTML");

		System.out.println(working);

		if (!working.contains("Paused")) {
			Assert.fail("There is no Paused notification");
		}


		Thread.sleep(3500);
		driver.quit();



	}
}
