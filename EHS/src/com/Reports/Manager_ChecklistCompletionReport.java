//This program is written to automate the 'Reports' function on the EHS UAT Web Site
//This program focuses particularly on 'Checklist Completion Report' under Manager Reports
//Flow: EHS UAT > Reports > Manager Reports > Checklist Completion Report

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
public class Manager_ChecklistCompletionReport {

	public void Manager_ChecklistCompletionReport() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //driver.manage().window().maximize();
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

		
		//Clicking on Manager Reports under Reports
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'Manager Reports')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		//Click on Checklist Completion Report
		driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[3]")).click();
		
		//Click on 'Select' button against Checklist
		driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[3]/div/div/button")).click();
		Thread.sleep(4500);
		//Search the Course
		driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).sendKeys("006");
		// Let's select a course 'CHK01 Checklist 1' by clicking on it
		//Checklist-001_OLD3-2-2012 Home Office Checklist
		driver.findElement(By.xpath("//*[@id=\"select-results\"]/div[1]/div[2]/table/tbody/tr/td")).click();
//		driver.findElement(By.id("876fbb07572270e686f2d68f06515e8c")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Scroll Down to find the OK button
		//JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		//js1.executeScript("window.scrollBy(0,250)", "");

		// Click OK
		driver.findElement(By.xpath("//*[@id=\"__BVID__13___BV_modal_footer_\"]/button")).click();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click 'Go' to generate the Survey Completion Report
		WebElement Go=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[5]/div/button[1]"));
		js.executeScript("arguments[0].click();",Go);
		Thread.sleep(2500);
		driver.quit();

	}

}
