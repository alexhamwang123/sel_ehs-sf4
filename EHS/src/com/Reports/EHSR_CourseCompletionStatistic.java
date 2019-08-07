//This program automates Course Completion Statistic on the EHS system and generates the report.
//It also selects a date range and clicks on Go
//EHS UAT > Reports > EHS Reports > Course Completion Statistic

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
public class EHSR_CourseCompletionStatistic {

	public void EHSR_CourseCompletionStatistic() throws IOException, InterruptedException {

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
		
		//Clicking on EHS Reports under Reports
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Reports')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		Thread.sleep(1500);
		//Click on Course Completion Statistic 
		driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[2]")).click();
		Thread.sleep(4500);
        //Select a Date Range. Click on Date From
        WebElement From= driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/div/div[1]/div[1]/input"));
        js.executeScript("arguments[0].value='01/01/2019'",From);
        Thread.sleep(500);
        WebElement To=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/div/div[2]/div[1]/input"));

        js.executeScript("arguments[0].value='01/04/2019'",To);
		//Click on Go to display the complete Course Completion Statistic
        WebElement Go=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[13]/div/button[1]"));
        js.executeScript("arguments[0].click();",Go);
		
		Thread.sleep(1500);
		
		//Scroll down 
		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("window.scrollBy(0,250)", "");


		Thread.sleep(2000);
		driver.quit();
		

	}

}
