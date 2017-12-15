//This program is written to automate the 'Reports' function on the EHS UAT Web Site
//This program focuses on 'Compliance Report' under Manager Reports
//Flow: EHS UAT > Reports > Manager Reports > Compliance Report

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

public class Manager_ComplianceReport {

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
		
		//Click on Compliance Report
		driver.findElement(By.xpath("//*[@id='content']/div[1]/div[1]/div/a[4]")).click();
		
		//We must select a Risk Category to generate the Compliance Report.
		//Click on the Select button for Risk category
		driver.findElement(By.id("jobClassNo")).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Choose a Risk Category. Lets say we choose 2
		driver.findElement(By.id("c76d705254ea0780e87d67ee8d609000")).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on OK
		driver.findElement(By.cssSelector("input[type='button'][value='Ok']")).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on Go to generate the report
		driver.findElement(By.id("Button_Go")).click();
		
		
	}

}
