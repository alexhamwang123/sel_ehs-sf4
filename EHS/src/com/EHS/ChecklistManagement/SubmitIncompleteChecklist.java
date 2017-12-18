//This program is to submit a checklist without completing it, and
//observe the behavior. Ideally, the web site should prompt a message
//saying that "Please complete all required questions (marked with a red "*") before submission."

package com.EHS.ChecklistManagement;

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

public class SubmitIncompleteChecklist {

	public static void main(String[] args) throws IOException {
		
        System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://192.168.15.131:8330");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.id("login_login_id")).sendKeys("X00001554");
		
		File file=new File(System.getProperty("user.dir")+"/EHS.password.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String val = prop.getProperty("userpassword");
		driver.findElement(By.id("login_password")).sendKeys(val);
		
		driver.findElement(By.name("submit")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
        driver.findElement(By.xpath("//*[@id='navPrimary']/li[2]/a")).click();
		
		driver.findElement(By.id("srch_fld")).sendKeys("trismax");
		
		driver.findElement(By.name("searchButton")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		

		//Click on the Enroll button
		driver.findElement(By.xpath("//*[@id='msg_headf3884a8ef079d9b188b2b4ddf3ddf1ee']/table/tbody/tr/td[5]/img")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		//Click on 'Default-English' Language option
		driver.findElement(By.id("crselink1")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		
		//Clicking the Radio Button
		//driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();
		
		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		//Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
		driver.findElement(By.cssSelector("input[type='checkbox'][value='2333436002495318bfdbf2418975d5ee']")).click();
		
		driver.findElement(By.cssSelector("input[type='checkbox'][value='e0c1339bc52e35535bf77bf81173da25']")).click();
		
		driver.findElement(By.cssSelector("input[type='checkbox'][value='3f1caee76cbd14813ce7b01fa73b6a9b']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		//Entering value in text box for Question 4
		
		driver.findElement(By.xpath("//*[@id='775cdb32e2e8cc9145320877a80040a5']")).clear();
		
		driver.findElement(By.xpath("//*[@id='775cdb32e2e8cc9145320877a80040a5']")).sendKeys("40");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		driver.findElement(By.cssSelector("input[type='button'][value='Submit']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		driver.findElement(By.id("fancyConfirm_ok")).click();
		

	}

}
