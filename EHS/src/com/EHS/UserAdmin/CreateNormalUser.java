//This program logs in as an admin, creates a new user and provides him with the Normal User rights.

package com.EHS.UserAdmin;

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

public class CreateNormalUser {

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
		
		//Clicking on 'User Admin'
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[1]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Click on Create User
		driver.findElement(By.cssSelector("input[type='button'][value='Create User']")).click();

		// Enter the First Name of the user that you wish to create
		driver.findElement(By.id("detailFirstName")).sendKeys("Hustin");

		// Enter the Last Name of the user that you wish to create
		driver.findElement(By.id("detailLastName")).sendKeys("Rade");

		// Click on 'Select' for Site
		driver.findElement(By.id("selectBtnSite")).click();

		// Enter the search value as "SCV"
		driver.findElement(By.id("searchName")).sendKeys("SCV");

		// Click on Search
		driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click on 'SCV' from the search results
		driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr/td[2]/a")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Enter the email of the user that you wish to create
		driver.findElement(By.name("detailEmailAddress")).sendKeys("hustin@trismax.com");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("window.scrollBy(0,850)", "");

		// Clicking on USA Normal User
		// driver.findElement(By.cssSelector("input[type='checkbox'][value='1']")).click();

		// Click on 'Save' button
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		

	}

}
