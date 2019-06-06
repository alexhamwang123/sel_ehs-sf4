//This program automates Checklist Submission Report on the EHS system and generates the report.
//It also selects a date range and clicks on Go
//EHS UAT > Reports > EHS Reports > Checklist Submission Report

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
		driver.findElement(By.xpath("//a[contains(text(),'Checklist Submission Report')]")).click();
		Thread.sleep(1500);
		//Click on Select button that appears in front of Checklist 
		driver.findElement(By.id("selectCourseDisabled")).click();
		Thread.sleep(1500);
		//Choose a course. Here, we choose 8787 Document Management \
		//Checklist-001_OLD3-2-2012 Home Office Checklist
		driver.findElement(By.id("srch_fld")).sendKeys("EHS-3600");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"applesearch\"]/div/div[6]/input")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"86\"]")).click();
		Thread.sleep(1500);
		//Click on the Ok button
		driver.findElement(By.xpath("//*[@id=\"course_result\"]/center/input")).click();
		
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on Go button to display the Report 
		WebElement Go=driver.findElement(By.id("Button_Go"));
		js.executeScript("arguments[0].click();",Go);
		Thread.sleep(3500);

		driver.quit();


	}

}
