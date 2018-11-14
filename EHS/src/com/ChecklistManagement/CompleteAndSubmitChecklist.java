//Test if the web site brings on the same page where the user left 
//when he does not click all the answers and clicks ‘Save’ and then ‘Cancel’

package com.ChecklistManagement;

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
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
@Test(priority=4)
//@Test(dependsOnGroups = "ehs",priority=4)
public class CompleteAndSubmitChecklist {

	public void CompleteAndSubmitChecklist() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
		//driver.manage().window().maximize();
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("login_login_id")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);

		driver.findElement(By.name("submit")).click();

		Thread.sleep(4500);
		WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Courses')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;

		js.executeScript("arguments[0].click();", courseAdmin);
		
		driver.findElement(By.id("srch_fld")).sendKeys("04a1awuKpJ");
		
		driver.findElement(By.name("searchButton")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(7000);


		//Click on the Enroll button
		driver.findElement(By.className("onelang")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on 'Default-English' Language option
		driver.findElement(By.id("crselink1")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Clicking the Radio Button
		//driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();
		
		//Clicking on Question 1
//		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		//Clicking on Question 2
//		driver.findElement(By.cssSelector("input[type='radio'][value='e2b2820a092849b866dded7b5ca71f3a']")).click();
//
//
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
		driver.findElement(By.cssSelector("input[type='checkbox'][value='13c496ea53471d3bce0c72e9b4a63b66']")).click();
		
		driver.findElement(By.cssSelector("input[type='checkbox'][value='4bd53adf7e6118d7c8625537fdd551c6']")).click();
		
		driver.findElement(By.cssSelector("input[type='checkbox'][value='9dadf671b25ecf8ba926941141405bf9']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//*[@id='78b39db9d6c9f3801f1792d2073b9c32']")).clear();
		
		driver.findElement(By.xpath("//*[@id='78b39db9d6c9f3801f1792d2073b9c32']")).sendKeys("40");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on the Submit button
		driver.findElement(By.cssSelector("input[type='button'][value='Submit']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on the confirm pop up
		driver.findElement(By.id("fancyConfirm_ok")).click();
		Thread.sleep(2000);
		driver.quit();
		

	}

}
