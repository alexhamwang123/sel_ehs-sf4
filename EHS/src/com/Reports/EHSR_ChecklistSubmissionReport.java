//This program automates Checklist Submission Report on the EHS system and generates the report.
//It also selects a date range and clicks on Go
//EHS UAT > Reports > EHS Reports > Checklist Submission Report

package com.Reports;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//@Test
@Test
public class EHSR_ChecklistSubmissionReport {

	public void EHSR_ChecklistSubmissionReport() throws IOException, InterruptedException {

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
try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
actions.build().perform(); }
catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(4500);
		
		//Clicking on EHS Reports under Reports
		WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'EHS Reports')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",courseAdmin);

		Thread.sleep(1500);
		//Click on Checklist Submission Report 
		driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[3]")).click();
		Thread.sleep(1500);
		//Select a Date Range. Click on Date From
		WebElement From= driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[2]/div/div/div[1]/div[1]/input"));
		js.executeScript("arguments[0].value='01/01/2019'",From);
		Thread.sleep(500);
		WebElement To=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[2]/div/div/div[2]/div[1]/input"));

		js.executeScript("arguments[0].value='01/07/2019'",To);

		//Click on Select button that appears in front of Checklist 
		driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/div/button")).click();
		Thread.sleep(1500);
		//Choose a course. Here, we choose 8787 Document Management \
		//Checklist-001_OLD3-2-2012 Home Office Checklist
		driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).sendKeys("EHS-3600");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"select-results\"]/div[1]/div[2]/table/tbody/tr/td")).click();
		Thread.sleep(1500);
		//Click on the Ok button
		driver.findElement(By.xpath("//*[@id=\"__BVID__7___BV_modal_footer_\"]/button")).click();
		
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Click on Go button to display the Report 
		WebElement Go=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[12]/div/button[1]"));
		js.executeScript("arguments[0].click();",Go);
		Thread.sleep(3500);

		driver.quit();


	}

}
