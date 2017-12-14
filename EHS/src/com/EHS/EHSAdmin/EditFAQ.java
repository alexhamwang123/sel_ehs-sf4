//This script is written to edit/make changes to an already existing FAQ in EHS UAT

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

public class EditFAQ {

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
		
		//Click on FAQ Management
		driver.findElement(By.xpath("//*[@id='left']/table/tbody/tr[6]/td/a")).click();
		
		//Choose any published FAQ to edit the details. Here we choose FAQ000001
		driver.findElement(By.xpath("//*[@id='faq_sortable']/tr[1]/td[2]/a")).click();

		// Edit the FAQ title
		driver.findElement(By.name("save[Title]")).clear();
		driver.findElement(By.name("save[Title]")).sendKeys("What is the purpose of this?");

		// Edit the FAQ Content
		driver.findElement(By.name("save[Content]")).clear();
		driver.findElement(By.name("save[Content]")).sendKeys(
				"Apple is now 100% committed to protecting the health and safety of employees. Part of this commitment is ensuring that every employee has training needed to stay safe on the job. Completion of the risk survey and all training in your training plan are required by law and Apple policy and should be done as soon as possible after starting your job here at Apple.");

		// Click on the Save button
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		

	}

}
