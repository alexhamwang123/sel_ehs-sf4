//This program automates Course Completion Report on the EHS system and generates the report.
//It also selects a date range and clicks on Go
//EHS UAT > Reports > EHS Reports > Course Completion Report

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//@Test
@Test
public class EHSR_CourseCompletionReport {

	public void EHSR_CourseCompletionReport() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
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
		//Select a Date Range. Click on Date From
        WebElement From= driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/div/div[1]/div[1]/input"));
		js.executeScript("arguments[0].value='01/01/2019'",From);
		Thread.sleep(500);
		WebElement To=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/div/div[2]/div[1]/input"));

		js.executeScript("arguments[0].value='01/04/2019'",To);

		Thread.sleep(3500);
        //Click Course-Select Btn
        Thread.sleep(2000);
        WebElement CourseBtn=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[12]/label/input"));
        js.executeScript("arguments[0].click();",CourseBtn);

        Thread.sleep(1000);

        WebElement SelectBtn=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[12]/div/div/button"));
        js.executeScript("arguments[0].click();",SelectBtn);
        Thread.sleep(2000);
        //Search the Course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div[1]/div/input"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div[1]/div/input")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div[1]/div/input")).sendKeys("FfPtZ8gYcv");

        Thread.sleep(2000);
        new Select(driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[2]/div[4]/select"))).selectByVisibleText("Y");
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td")).click();
        //Click on OK
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/footer/button")).click();

        //Date
        WebElement July= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div[1]/div[3]/span[7]"));
        js.executeScript("arguments[0].click();",July);
        Thread.sleep(2000);
        WebElement Date_01= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div[1]/div[2]/div/span[9]"));
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js1.executeScript("arguments[0].click();",Date_01);
        Thread.sleep(2000);
        WebElement Date_02= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div[2]/div[2]/div/span[13]"));
        JavascriptExecutor js2=(JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click();",Date_02);

        //Click on Go

        WebElement Go=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[15]/div/button[1]"));
        js.executeScript("arguments[0].click();",Go);

		Thread.sleep(3500);

		driver.quit();
		
		

	}

}
