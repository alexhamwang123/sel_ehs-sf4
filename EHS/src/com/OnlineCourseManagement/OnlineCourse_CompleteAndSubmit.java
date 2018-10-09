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
public class OnlineCourse_CompleteAndSubmit {

	public void OnlineCourse_CompleteAndSubmit() throws IOException, InterruptedException {

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
		driver.findElement(By.id("srch_fld")).sendKeys("trismax");
		
		driver.findElement(By.name("searchButton")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String mainWindow = driver.getWindowHandle();
		
        driver.findElement(By.xpath("//*[@id='msg_headcc58b92c0ccf8164d84fd405d994f073']/table/tbody/tr/td[5]/img")).click();
		
		for(String winHandle : driver.getWindowHandles()){
			 if(winHandle!=mainWindow)
	          driver.switchTo().window(winHandle);
		    }
		
        driver.findElement(By.partialLinkText("English")).click();
		
		driver.findElement(By.xpath("//*[@id='label']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on Page 1's Questions 1 and 2
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('top1').getElementsByTagName('input')[0].click();");
		js.executeScript("document.getElementById('top1').getElementsByTagName('input')[3].click();");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on the 'Next' Button
		 WebElement nextButton= driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		 js.executeScript("arguments[0].click();",nextButton);
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//Clicking on Page 2's Question 1, 2 and 3
		 JavascriptExecutor js1 = (JavascriptExecutor)driver;
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[0].click();");
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[4].click();");
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[6].click();");
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 //Clicking on the 'Next' button
		 WebElement nextButton1= driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		 js.executeScript("arguments[0].click();",nextButton1);
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //Clicking on Page 3's Questions 1 and 2
		 JavascriptExecutor js2 = (JavascriptExecutor)driver;
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[0].click();");
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[4].click();");
		 
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //Clicking on the Next button
		 WebElement nextButton2 = driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		 js.executeScript("arguments[0].click();",nextButton2);
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 
		//Clicking on Page 4's Question 1 and 2
		 JavascriptExecutor js3 = (JavascriptExecutor)driver;
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[0].click();");
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[2].click();");
		 
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //Clicking the 'grade quiz' button
		 WebElement gradeButton = driver.findElement(By.cssSelector(".buttonSet input"));
		 js.executeScript("arguments[0].click();",gradeButton);
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 
		driver.findElement(By.cssSelector("#buttons .DialogBoxButton.noTouch.scormSubmitGrade")).click();
		Thread.sleep(2000);
		WebElement exit = driver.findElement(By.xpath("//*[@id=\"nav_exit\"]"));
		js.executeScript("arguments[0].click();", exit);
		Thread.sleep(1500);
		driver.switchTo().window(mainWindow);

		Thread.sleep(3500);
		driver.quit();

		

	}

}
