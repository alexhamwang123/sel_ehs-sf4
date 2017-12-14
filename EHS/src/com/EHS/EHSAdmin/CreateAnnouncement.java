//This script is written to create a new Announcement and check in the list of announcements 
//whether the newly created announcement appears in the list for EHS system

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

public class CreateAnnouncement {

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
		
		//Clicking on 'EHS Admin'
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[4]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on Announcement
		//driver.findElement(By.xpath("//*[@id='left']/table/tbody/tr[1]/td/a")).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on 'Create Announcement'
		driver.findElement(By.cssSelector("input[type='button'][value='Create Announcement']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Check the box for 'Never Expires'
		if ( !driver.findElement(By.name("detailAnnouncementIsNeverExpire")).isSelected() )
		{
		     driver.findElement(By.name("detailAnnouncementIsNeverExpire")).click();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 //Clicking on Announcement Category: Welcome
		 driver.findElement(By.cssSelector("input[type='radio'][value='DB_Welcome']")).click();
		
		 //Clicking on Announcement Category: Risk Survey Incomplete
		 //driver.findElement(By.cssSelector("input[type='radio'][value='RS_Incomplete']")).click();
			
		 //Clicking on Announcement Category: Risk Survey Completed
		 //driver.findElement(By.cssSelector("input[type='radio'][value='RS_Completed']")).click();
				
		 //Clicking on Announcement Category: Manager Portal Welcome
		 //driver.findElement(By.cssSelector("input[type='radio'][value='MP_Welcome']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Entering the 'Announcement Title'
		driver.findElement(By.name("detailAnnouncementTitle")).sendKeys("Announcement 4");
		
		//Entering the Announcement Content
		driver.findElement(By.name("detailAnnouncementContent")).sendKeys("This is the latest announcement in the series");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on 'Save'
		driver.findElement(By.cssSelector("input[type='submit'][value='Save']")).click();
		
		//clicking on 'Back'
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Select(driver.findElement(By.xpath("//*[@id='EHSForm']/div[1]/div[2]/select"))).selectByVisibleText("Announcement Title");
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("srch_fld")).sendKeys("Announcement 1");
		
		//Click on the 'Go' button to display the result
		driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
		

	}

}
