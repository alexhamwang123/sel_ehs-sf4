//This script is to test what the system prompts after the user
//answers partial questions and click on 'Grade Quiz'

package com.OnlineCourseManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test
public class OnlineCourse_PartiallyCompleteAndSubmit {
	
	public void OnlineCourse_PartiallyCompleteAndSubmit() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");

		driver.get(urladdr);

		driver.manage().window().maximize();

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("login_login_id")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);

		driver.findElement(By.name("submit")).click();

		Thread.sleep(4500);


		driver.findElement(By.xpath("//*[@id='navPrimary']/li[2]/a")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("srch_fld")).sendKeys("EHS-11111");
		Thread.sleep(1500);
		driver.findElement(By.name("searchButton")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String mainWindow = driver.getWindowHandle();
		//It is a EHS-11111 course id of msg_id.
		driver.findElement(By.xpath("//*[@id='msg_headb7d0d73b5e1ae80d8d245fed08ac7e7c']/table/tbody/tr/td[5]/img")).click();
//		driver.findElement(By.xpath("//*[@id='msg_headcc58b92c0ccf8164d84fd405d994f073']/table/tbody/tr/td[5]/img")).click();

		for (String winHandle : driver.getWindowHandles()) {
			if (winHandle != mainWindow)
				driver.switchTo().window(winHandle);
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.partialLinkText("English")).click();
		Thread.sleep(1500);
//		driver.findElement(By.xpath("//*[@id='label']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on Question 1
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('top1').getElementsByTagName('input')[0].click();");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on the 'Next' Button
		WebElement nextButton = driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		js.executeScript("arguments[0].click();", nextButton);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on Page 2's Question 2 and Question 3
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('top1').getElementsByTagName('input')[4].click();");
		Thread.sleep(1000);
		js.executeScript("document.getElementById('top1').getElementsByTagName('input')[6].click();");

		//Clicking on Next Button
		WebElement nextButton1 = driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		js.executeScript("arguments[0].click();", nextButton1);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on Page 3's Question 1
		js.executeScript("document.getElementById('top1').getElementsByTagName('input')[0].click();");
		Thread.sleep(1500);
		//Clicking on Next Button
		WebElement nextButton2 = driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		js.executeScript("arguments[0].click();", nextButton2);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on Page 4's Question 2
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('top1').getElementsByTagName('input')[2].click();");
		Thread.sleep(2000);

		WebElement exit = driver.findElement(By.xpath("//*[@id=\"nav_exit\"]"));
		js.executeScript("arguments[0].click();", exit);
		Thread.sleep(1500);
		driver.findElement(By.className("scormPauseCourse")).click();
		Thread.sleep(1500);

		driver.switchTo().window(mainWindow);

		driver.findElement(By.id("srch_fld")).sendKeys("EHS-11111");
		Thread.sleep(1500);
		driver.findElement(By.name("searchButton")).click();
		Thread.sleep(1500);
		String paused = driver.findElement(By.xpath("//*[@id=\"msg_headb7d0d73b5e1ae80d8d245fed08ac7e7c\"]/table/tbody/tr/td[3]")).getAttribute("innerHTML").substring(133,139);
		if (!paused.equals("Paused")) {
			Assert.fail("the online course does not show up as paused");
		}
		Thread.sleep(3500);
		driver.quit();



	}
}
