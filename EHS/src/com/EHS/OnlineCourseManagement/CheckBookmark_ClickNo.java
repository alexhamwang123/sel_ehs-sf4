//This script is written to test if the course brings the user to Page 1 of x, 
//after the user clicks on 'No' when asked "Do you want to resume from where you left"

package com.EHS.OnlineCourseManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class CheckBookmark_ClickNo {

	public void CheckBookmark_ClickNo() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://twn:WrongAdeeDow2-@demo.accentrixus.com:8330");

		driver.manage().window().maximize();

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("login_login_id")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);

		driver.findElement(By.name("submit")).click();

		Thread.sleep(4500);

		
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
		 Thread.sleep(1500);
		 driver.findElement(By.xpath("//*[@id='label']")).click();
		 
		 try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.quit();
		

	}

}
