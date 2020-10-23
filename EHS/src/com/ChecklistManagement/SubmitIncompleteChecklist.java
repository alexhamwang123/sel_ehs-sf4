//This program is to submit a checklist without completing it, and
//observe the behavior. Ideally, the web site should prompt a message
//saying that "Please complete all required questions (marked with a red "*") before submission."

package com.ChecklistManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test

public class SubmitIncompleteChecklist {

	public void SubmitIncompleteChecklist() throws IOException, InterruptedException {

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
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(2000);
		Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

		Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("DanielChecklistUnfinished"); //checklist name on localhost



		//Click on the Enroll button
		Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")));
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

        driver.close();
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		Wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Courses")));
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("Courses")).click();
		Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys("DanielChecklistUnfinished");//checklist name is on localhost


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		//it's for test course
		String paused = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[3]")).getAttribute("innerHTML");
//		System.out.println(paused);

		if (!paused.contains("Paused")) {
			Assert.fail("the course does not show up as paused");
		}

		Thread.sleep(3500);
		driver.quit();


		//Click on the Enroll button




	}

}