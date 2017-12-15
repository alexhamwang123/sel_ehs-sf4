//This program is written to automate the 'Reports' function on the EHS UAT Web Site
//This program focuses particularly on 'Checklist Completion Report' under Manager Reports
//Flow: EHS UAT > Reports > Manager Reports > Checklist Completion Report

package com.EHS.Reports;

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

public class Manager_ChecklistCompletionReport {

	public static void main(String[] args) throws IOException {
 		
        System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get("https://192.168.15.131:8330");
		
		driver.findElement(By.id("login_login_id")).sendKeys("admin");
		
		File file=new File(System.getProperty("user.dir")+"/EHS.password.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String val = prop.getProperty("adminpassword");
		driver.findElement(By.id("login_password")).sendKeys(val);
		
		driver.findElement(By.name("submit")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on Manager Reports under Reports
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[6]/ul/li[2]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		//Click on Checklist Completion Report
		driver.findElement(By.xpath("//*[@id='content']/div[1]/div[1]/div/a[3]")).click();
		
		//Click on 'Select' button against Checklist
		driver.findElement(By.id("selectCourseDisabled")).click();

		// Let's select a course 'CHK01 Checklist 1' by clicking on it
		driver.findElement(By.id("876fbb07572270e686f2d68f06515e8c")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Scroll Down to find the OK button
		//JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		//js1.executeScript("window.scrollBy(0,250)", "");

		// Click OK
		driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click 'Go' to generate the Survey Completion Report
		driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
		

	}

}
