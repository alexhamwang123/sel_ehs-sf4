//This script is written to edit/make changes to an already existing announcement in EHS UAT.

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

public class EditAnnouncement {

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
		
		//Clicking on 'EHS Admin' from the menu on the home page in the drop down
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[4]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on an existing announcement, Lets say: AN000005
		driver.findElement(By.id("84920bfa32b3bb7a54d8023cb6a48b99")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Edit the announcement remarks 
		driver.findElement(By.name("detailAnnouncementRemarks")).clear();
		driver.findElement(By.name("detailAnnouncementRemarks")).sendKeys("This is the new remark");
		
		//Edit the Announcement Title
		//driver.findElement(By.name("detailAnnouncementTitle")).clear();
		//driver.findElement(By.name("detailAnnouncementTitle")).sendKeys("Announcement Number 1");
		
		//Edit the Announcement Content
		driver.findElement(By.name("detailAnnouncementContent")).clear();
		driver.findElement(By.name("detailAnnouncementContent")).sendKeys("This is the 1st announcement");
		
		//Click on Save to save the details
		driver.findElement(By.cssSelector("input[type='submit'][value='Save']")).click();

	}

}
