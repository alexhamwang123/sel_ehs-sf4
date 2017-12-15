//This program logs in and then logs out from EHS UAT platform to check the log out functionality 

package com.EHS.Miscellaneous;

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

public class EHS_LogOut {

	public static void main(String[] args) throws IOException {
		
		// - - - -- - - - - - Login as Admin - - - - - - - //
		
				System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
				
				WebDriver driver = new ChromeDriver();
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				driver.manage().window().maximize();
				
				driver.get("https://192.168.15.131:8330");
				
				driver.findElement(By.id("login_login_id")).sendKeys("admin");
				
				File file=new File(System.getProperty("user.dir")+"/EHS.password.properties");
				FileInputStream inStream=new FileInputStream(file);
				Properties prop=new Properties();
				prop.load(inStream);
				String val = prop.getProperty("adminpassword");
				driver.findElement(By.id("login_password")).sendKeys(val);
				
				driver.findElement(By.name("submit")).click();
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//--- Once into the system, we will now log out of the system ---//
				
		// Click on logout
		WebElement ele = driver.findElement(By.xpath("//*[@id='lightbox']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on OK on the pop up that says "Thank you for using EHS."
		driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
		
		
	}

}
