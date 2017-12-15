//This script is written to create a new online course under Online Training Management
//Path: EHS UAT > Admin > Course Admin > Online Training Management > Create Course

package com.EHS.OnlineCourseManagement;

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

public class CreateOnlineClass {

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
		
		//Clicking on 'Course Admin'
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[3]/a"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on Create Course
		driver.findElement(By.cssSelector("input[type='button'][value='Create Course']")).click();
		
		//Enter a Course Number
		driver.findElement(By.name("detailCourseNumber")).sendKeys("EHS-Online-1");
		
		//Select a Course Category from the dropdown 
		new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("EHS - Safety");
		
		//Select a Course Fulfill type
		new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
		
		//Select Default Expiration
		new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
		
		//Click on the Save button
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		
		
	}

}
