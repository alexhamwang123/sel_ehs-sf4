//The purpose of this program is to enroll for a classroom training class
//Expected Result: The number of seats open should decrease by 1 and user 
//should receive a confirmation email with iCal attached

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

public class Classroom_ClassRegistration {

	public static void main(String[] args) throws IOException {
		
        System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get("https://192.168.15.131:8330");
		
		driver.findElement(By.id("login_login_id")).sendKeys("X00001554");
		
		File file=new File(System.getProperty("user.dir")+"/EHS.password.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String val = prop.getProperty("userpassword");
		driver.findElement(By.id("login_password")).sendKeys(val);
		
		driver.findElement(By.name("submit")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Click on 'Catalog' on the home page
		driver.findElement(By.xpath("//*[@id='menu_courses']/a")).click();

		// Select 'Classroom' from the drop down on Course Type
		new Select(driver.findElement(By.id("course-type"))).selectByVisibleText("Classroom");

		// Select a classroom course that has seats available, such as Classroom 001
		driver.findElement(By.xpath("//*[@id='msg_head3b5d87dc98f3e16d2abee664602db4f7']/table/tbody/tr/td[5]/img")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on the Enroll button
		driver.findElement(By.id("ca1528c02cfa3a797a70d300f481cb61")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// --- The email with the iCal attached should arrive now ---//
		
		
		

	}

}
