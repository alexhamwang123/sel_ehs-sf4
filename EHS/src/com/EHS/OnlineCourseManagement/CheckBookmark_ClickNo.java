//This script is written to test if the course brings the user to Page 1 of x, 
//after the user clicks on 'No' when asked "Do you want to resume from where you left"

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

public class CheckBookmark_ClickNo {

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
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[0].click();");
		 js.executeScript("document.getElementById('top1').getElementsByTagName('input')[3].click();");
		 
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 // driver.findElements(By.cssSelector("#top1 input")).get(0).click();
		 //driver.findElements(By.cssSelector("#top1 input")).get(2).click();
		 
		 //Clicking on the 'Next' Button
		 WebElement nextButton= driver.findElement(By.cssSelector("#footer_Play .button_next.unbindelements"));
		 js.executeScript("arguments[0].click();",nextButton);
		 
		 
		// driver.findElements(By.id("courseNextBtn")).get(0).click();
		 
		//Clicking on Question 1, Page 1
	   //driver.findElement(By.cssSelector("input[type='radio'][value='686bc39bd5c7cc3550fe3ede35c62464']")).click();

		//driver.findElement(By.cssSelector("input[type=radio'][value='02549326602054897d061e3f95405706']")).click(); 

		 //driver.findElement(By.xpath("//*[@id='nav_exit']/p")).click();
		

	}

}
