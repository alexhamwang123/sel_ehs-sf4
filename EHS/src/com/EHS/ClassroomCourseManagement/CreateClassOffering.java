//This script is written to create a new classroom offering for an already existing classroom course
//Path: EHS UAT > Course Admin > Clasroom Course Management > choose an existing course > Create offering

package com.EHS.ClassroomCourseManagement;

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

public class CreateClassOffering {

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
		
		//Click on Course Admin
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[3]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Click on Classroom Course Management
		driver.findElement(By.xpath("//*[@id='left']/table/tbody/tr[3]/td/a")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Choose an existing course for which you want to create a class offering. Here we choose Classroom 3
		driver.findElement(By.xpath("//*[@id='userRecord']/tbody/tr[11]/td[1]/a")).click();

		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("window.scrollBy(0,550)", "");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on the 'Add' button
		driver.findElement(By.xpath("//*[@id='addClass']/input")).click();

		// Click on the radio button for Site
		driver.findElement(By.id("site_radio")).click();

		// Click on the Select button
		driver.findElement(By.id("selectBtnSite")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on SCV
		driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr[1]/td[1]/a")).click();

		// Enter the Building name
		driver.findElement(By.name("detailClassBuilding")).sendKeys("Building 0091");

		// Enter the Room
		driver.findElement(By.name("detailClassRoom")).sendKeys("Room 0905");

		// Enter the size of the class
		driver.findElement(By.name("detailClassMaxSize")).sendKeys("7");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JavascriptExecutor js2 = ((JavascriptExecutor) driver);
		js2.executeScript("window.scrollBy(0,350)", "");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click on the 'Add' button to add date and time for the class
		driver.findElement(By.id("TimeAdd")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on the Date
		driver.findElement(By.id("TimeAdd_datepicker")).click();

		// Select the Date, 29th November
		// driver.findElement(By.name("detailClassStartDateSelect")).click();

		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[4]/a")).click();

		// Selecting Time from the drop down
		new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("10");

		// Click on Save button
		driver.findElement(By.id("TimeAdd_Save")).click();

		// Click on the final Save button
		driver.findElement(By.id("saveClassCourse")).click();
		

	}

}
