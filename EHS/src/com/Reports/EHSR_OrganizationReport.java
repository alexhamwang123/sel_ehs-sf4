//This program automates Organization Report on the EHS system and generates the report.
//It also selects a date range and clicks on Go
//EHS UAT > Reports > EHS Reports > Organization Report

package com.Reports;

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
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test
public class EHSR_OrganizationReport {

	public void EHSR_OrganizationReport() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();
		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);
		
		//Clicking on EHS Reports under Reports
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[6]/ul/li[4]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		Thread.sleep(1500);
		//Click on Organization Report 
		driver.findElement(By.xpath("//*[@id='content']/div[1]/div/div/a[6]")).click();
		Thread.sleep(1500);
		//Click on Select for selecting a Manager
		driver.findElement(By.cssSelector("input[type='button'][value='Select']")).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Enter the First Name of the manager you wish to find and select 
		driver.findElement(By.name("firstName")).sendKeys("Bugra");
		
		//Click on Search 
		driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
		Thread.sleep(1500);
		//Click on the search result 
		driver.findElement(By.xpath("//*[@id='teammanager_result']/div/table/tbody/tr/td[2]/a")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Now, Select a Risk Category, by clicking on Select button
		driver.findElement(By.id("jobClassNo")).click();
		Thread.sleep(1500);
		//Select a Risk Category by id. Here we select 2
		driver.findElement(By.id("c76d705254ea0780e87d67ee8d609000")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click Ok
		driver.findElement(By.cssSelector("input[type='button'][value='Ok']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on Go
		driver.findElement(By.id("Button_Go")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on the pop up that appears after clicking Go
		driver.findElement(By.id("fancyConfirm_ok")).click();
		Thread.sleep(3500);
		driver.quit();

	}

}
