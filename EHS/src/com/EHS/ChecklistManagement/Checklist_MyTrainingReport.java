//This script is written to test whether the name of the checklist appears 
//in My Training Report record, after the user completes the checklist for the EHS system.

package com.EHS.ChecklistManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class Checklist_MyTrainingReport {

	public void Checklist_MyTrainingReport() throws IOException, InterruptedException {
		
        System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://twn:WrongAdeeDow2-@demo.accentrixus.com:8330");
		
		driver.manage().window().maximize();

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("login_login_id")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);
		
		driver.findElement(By.name("submit")).click();
		
		try {
			Thread.sleep(4500);
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
		WebElement back = driver.findElement(By.cssSelector("input[type='button'][value='Back']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", back);


		// - - - Now, we check whether the completed survey appears in My Training Report - - -//

		// Click on 'My Training Report' under Reports
		driver.findElement(By.partialLinkText("Reports")).click();

		// Select Checklist from the drop down
		new Select(driver.findElement(By.name("selectedCourseType"))).selectByVisibleText("Checklist");


		Thread.sleep(1000);

		Calendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH);
		month ++;
		String smonth;
		if(month < 10) {
			smonth = "0" + month;
		} else {
			smonth = "" + month;
		}

		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String sday;
		if(day < 10) {
			sday = "0" + day;
		} else {
			sday = "" + day;
		}

		int year = calendar.get(Calendar.YEAR);


		js.executeScript("document.getElementById('dateFrom').value='" + smonth + "/" + sday + "/" + year + "'");
		js.executeScript("document.getElementById('dateTo').value='" + smonth + "/" + sday + "/" + year + "'");

		Thread.sleep(750);

		// Click on 'Go'
		driver.findElement(By.id("Button_Go")).click();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(!driver.getPageSource().contains("trismax accentrix")) {
			Assert.fail("the completed checklist does not show up in 'My Training Report'");
		}
		Thread.sleep(3500);


		driver.quit();
		

	}

}
