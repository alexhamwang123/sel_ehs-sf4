//This program automates My Training Report on the EHS system and generates the report.
//It also selects a date range and clicks on Go

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

public class MyTrainingReport {

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
		
		//Clicking on My Training Report under Reports
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[6]/ul/li[1]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Selecting a date range 'From'
		//driver.findElement(By.id("dateFrom")).click();

		//driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();

		// Selecting 1 December 2017 as the Date From
		//driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[6]/a")).click();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Selecting a date range 'To'
		//driver.findElement(By.id("dateTo")).click();

		// Selecting 14 December 2017 as the date range 'To'
		//driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[5]/a")).click();

		// Clicking on 'Go'
		driver.findElement(By.id("Button_Go")).click();
		

	}

}
