//This program creates a new classroom course by entering the Course Number, Course Title,
//entering other relevant details and clicking Save to create the new classroom course

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

public class CreateClassroomCourse {

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
		
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[3]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on Classroom Course Management
		driver.findElement(By.xpath("//*[@id='left']/table/tbody/tr[3]/td/a")).click();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement ele1 = driver.findElement(By.xpath("//*[@id='srch_fld']"));
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele1);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Click on Create Course
		driver.findElement(By.cssSelector("input[type='button'][value='Create Course']")).click();
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Enter the 'Course Number'
		driver.findElement(By.name("detailCourseNo")).sendKeys("ClassRoom 6");
		
		//Enter the 'Course Title'
		driver.findElement(By.name("detailCourseTitle")).sendKeys("CR 6");
		
		//Select a 'Course Category' such as 'Regular' from the options in Course Category in drop down list
		new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
		
		//Selecting 'Normal' from the options in Course Fulfill type in drop down list 
		new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Selecting 'Never Expires' from the options in Expiration in drop down list
		new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
		
		//Click on the Save button
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on the Back button to display the list of all the Classroom courses
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
		

	}

}
