//This script is written to edit details of an already existing Lab in the EHS UAT system.

package com.EHS.EHSAdmin;

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

public class EditLab {

	public static void main(String[] args) throws IOException {
		
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on EHS Admin
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[4]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
				
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on 'Lab Management'
		driver.findElement(By.xpath("//*[@id='left']/table/tbody/tr[3]/td/a")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Search for a lab. Lets say 'Test Lab 2'
		driver.findElement(By.id("srch_fld")).sendKeys("Test Lab 2");
		
		//Click on Go
		driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Click on the search result by the id
		driver.findElement(By.id("915d8c7313d891060113e0b47ce500a4")).click();

		// Edit the Lab Name
		driver.findElement(By.name("detailLabName")).clear();
		driver.findElement(By.name("detailLabName")).sendKeys("New Lab 1");

		// Edit the Lab Description
		driver.findElement(By.name("detailLabDescr")).clear();
		driver.findElement(By.name("detailLabDescr")).sendKeys("This is the new lab description");

		// Edit the Room Number
		driver.findElement(By.id("aReplyEmail")).clear();
		driver.findElement(By.id("aReplyEmail")).sendKeys("Room 121");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on the Save button
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		

	}

}
