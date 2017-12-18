//This script is written to test whether the name of the checklist appears 
//in My Training Report record, after the user completes the checklist for the EHS system.

package com.EHS.ChecklistManagement;

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
import java.util.concurrent.TimeUnit;

public class Checklist_MyTrainingReport {

	public static void main(String[] args) throws IOException {
		
        System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://192.168.15.131:8330");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.id("login_login_id")).sendKeys("X00001554");
		
		File file=new File(System.getProperty("user.dir")+"/EHS.password.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String val = prop.getProperty("userpassword");
		driver.findElement(By.id("login_password")).sendKeys(val);
		
		driver.findElement(By.name("submit")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        driver.findElement(By.xpath("//*[@id='navPrimary']/li[2]/a")).click();
		
		driver.findElement(By.id("srch_fld")).sendKeys("trismax");
		
		driver.findElement(By.name("searchButton")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//Click on the Enroll button
		driver.findElement(By.xpath("//*[@id='msg_headf3884a8ef079d9b188b2b4ddf3ddf1ee']/table/tbody/tr/td[5]/img")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on 'Default-English' Language option
		driver.findElement(By.id("crselink1")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Clicking the Radio Button
		//driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();
		
		//Clicking on Question 1
		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Clicking on Question 2
		driver.findElement(By.cssSelector("input[type='radio'][value='e2b2820a092849b866dded7b5ca71f3a']")).click();

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
		driver.findElement(By.cssSelector("input[type='checkbox'][value='2333436002495318bfdbf2418975d5ee']")).click();
		
		driver.findElement(By.cssSelector("input[type='checkbox'][value='e0c1339bc52e35535bf77bf81173da25']")).click();
		
		driver.findElement(By.cssSelector("input[type='checkbox'][value='3f1caee76cbd14813ce7b01fa73b6a9b']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//*[@id='775cdb32e2e8cc9145320877a80040a5']")).clear();
		
		driver.findElement(By.xpath("//*[@id='775cdb32e2e8cc9145320877a80040a5']")).sendKeys("40");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on the Submit button
		driver.findElement(By.cssSelector("input[type='button'][value='Submit']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on the confirm pop up
		driver.findElement(By.id("fancyConfirm_ok")).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on Back
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

		// - - - Now, we check whether the completed survey appears in My Training Report - - -//

		// Click on 'My Training Report' under Reports
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[3]/a"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);

		// Select Checklist from the drop down
		new Select(driver.findElement(By.name("selectedCourseType"))).selectByVisibleText("Checklist");

		// Click on 'Go'
		driver.findElement(By.cssSelector("input[type='button'][value='Go']")).click();
		

	}

}
