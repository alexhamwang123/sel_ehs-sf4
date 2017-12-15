//This script is to test what the system prompts after the user
//answers partial questions and click on 'Grade Quiz'

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

public class OnlineCourse_PartiallyCompleteAndSubmit {
	
public static void main(String[] args) throws IOException {
		
        System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get("https://192.168.15.131:8330");
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
driver.findElement(By.xpath("//*[@id='navPrimary']/li[2]/a")).click();
		
		driver.findElement(By.id("srch_fld")).sendKeys("trismax");
		
		driver.findElement(By.name("searchButton")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String mainWindow = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//*[@id='msg_headcc58b92c0ccf8164d84fd405d994f073']/table/tbody/tr/td[5]/img")).click();
		
		for(String winHandle : driver.getWindowHandles()){
			 if(winHandle!=mainWindow)
	          driver.switchTo().window(winHandle);
		    }
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.partialLinkText("English")).click();
		
		driver.findElement(By.xpath("//*[@id='label']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on Question 1
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('top1').getElementsByTagName('input')[0].click();");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on the 'Next' Button
		 WebElement nextButton= driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		 js.executeScript("arguments[0].click();",nextButton);
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //Clicking on Page 2's Question 2 and Question 3
		 JavascriptExecutor js1 = (JavascriptExecutor)driver;
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[4].click();");
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[6].click();");
		 
		 //Clicking on Next Button
		 WebElement nextButton1 = driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		 js.executeScript("arguments[0].click();",nextButton1);
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //Clicking on Page 3's Question 1
		 JavascriptExecutor js2 = (JavascriptExecutor)driver;
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[0].click();");
		 
		 //Clicking on Next Button
		 WebElement nextButton2 = driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		 js.executeScript("arguments[0].click();",nextButton2);
		 
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //Clicking on Page 4's Question 2
		 JavascriptExecutor js3 = (JavascriptExecutor)driver;
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[2].click();");
		 
		 //Clicking the 'grade quiz' button
		 WebElement gradeButton = driver.findElement(By.cssSelector(".buttonSet input"));
		 js.executeScript("arguments[0].click();",gradeButton);
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 driver.findElement(By.cssSelector("#buttons .DialogBoxButton.noTouch.scormSubmitGrade")).click();

}
}
