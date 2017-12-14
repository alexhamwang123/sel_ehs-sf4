//This script is written to edit/make changes to an already existing Risk Category in RCT UAT

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

public class EditRiskCategory {

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
		
		//Click on RC Management
		driver.findElement(By.xpath("//*[@id='left']/table/tbody/tr[2]/td/a")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on an existing risk category. Here, we choose 2
		driver.findElement(By.id("c76d705254ea0780e87d67ee8d609000")).click();
		
		//Edit the Risk Category Name
		driver.findElement(By.name("detailJobClassName")).clear();
		driver.findElement(By.name("detailJobClassName")).sendKeys("New 2");
		
		//Edit the Risk Category Abbr Name
		driver.findElement(By.name("detailJobClassAbbrName")).clear();
		driver.findElement(By.name("detailJobClassAbbrName")).sendKeys("2");
		
		//Edit the Description
		driver.findElement(By.name("detailJobClassDescr")).clear();
		driver.findElement(By.name("detailJobClassDescr")).sendKeys("Enter the Description here");
		
		//Click on the Save button
		driver.findElement(By.cssSelector("input[type='submit'][value='Save']")).click();
		

	}

}
