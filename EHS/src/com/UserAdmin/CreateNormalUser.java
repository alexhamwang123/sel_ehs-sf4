//This program logs in as an admin, creates a new user and provides him with the Normal User rights.

package com.UserAdmin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//@Test
@Test
public class CreateNormalUser {

	public void CreateNormalUser() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//driver.manage().window().maximize();

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");

		driver.get(urladdr);

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on 'User Admin'
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
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

		Thread.sleep(3500);
		//自動會有detailBadgeNumber ，也就是我們要用的bdgeeNumber ID
		String id = driver.findElement(By.id("detailBadgeNumber")).getAttribute("value");
		System.out.println("id is " + id);

		// Enter the First Name of the user that you wish to create
		driver.findElement(By.id("detailFirstName")).sendKeys(id);

		// Enter the Last Name of the user that you wish to create
		driver.findElement(By.id("detailLastName")).sendKeys(id);

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
		driver.findElement(By.name("detailEmailAddress")).sendKeys(id + "@trismax.com");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("window.scrollBy(0,850)", "");


		// Click on 'Save' button
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

		Thread.sleep(2000);

		WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		js.executeScript("arguments[0].click()",Logout);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("username")).sendKeys(id);
		driver.findElement(By.id("password")).sendKeys(id);

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(4500);

		driver.quit();


	}

}
