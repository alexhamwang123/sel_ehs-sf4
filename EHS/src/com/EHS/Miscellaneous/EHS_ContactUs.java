//This script is written to fill the description in the Contact page on the EHS home page
//Path: EHS UAT > Contact > Enter Detail > Submit

package com.EHS.Miscellaneous;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class EHS_ContactUs {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get("https://192.168.15.131:8330");
		
		driver.findElement(By.id("login_login_id")).sendKeys("admin");
		
		driver.findElement(By.id("login_password")).sendKeys("m514roqir");
		
		driver.findElement(By.name("submit")).click();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on the Contact button
		//driver.findElement(By.id("helpMenu")).click();
		driver.findElement(By.xpath("//*[@id='topbar']/div[3]")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Enter the detail
		driver.findElement(By.name("content")).sendKeys("I am not able to connect to the server. My internet connection is active. Please help me");
		
		//Click on 'Submit'
	    driver.findElement(By.cssSelector("input[type='button'][value='Submit']")).click();

	}

}
