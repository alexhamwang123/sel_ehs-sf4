//This program logs in as an admin, creates a new user and provides him with the Super Admin rights.

package com.UserAdmin;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//@Test
@Test
public class CreateSuperAdmin {

	public void CreateSuperAdmin() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();

		WebDriverWait wait= new WebDriverWait(driver,30);
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
		WebElement Create_User=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/ul/li[3]/div/a[1]"));
		js.executeScript("arguments[0].click();", Create_User);


// Enter the First Name of the user that you wish to create
		String userid = driver.findElement(By.id("input-badgeNo")).getAttribute("value");

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-firstName"))));
		Thread.sleep(1000);
		driver.findElement(By.id("input-firstName")).sendKeys(userid);

// Enter the Last Name of the user that you wish to create
		driver.findElement(By.id("input-lastName")).sendKeys(userid);

// Click on 'Select' for Site
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"__BVID__31\"]/div/div[1]/button"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"__BVID__31\"]/div/div[1]/button")).click();
		Thread.sleep(1000);
// Enter the search value as "SCV"
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys("SCV");


// Click on 'SCV' from the search results
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();


// Enter the email of the user that you wish to create
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-email"))));
		Thread.sleep(1000);
		driver.findElement(By.id("input-email")).sendKeys(userid + "@trismax.com");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("window.scrollBy(0,850)", "");

// Clicking on USA Normal User
//driver.findElement(By.cssSelector("input[type='checkbox'][value='1']")).click();

// Click on 'Save' button
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/button")).click();

		Thread.sleep(2000);


//Click Role Page
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"__BVID__52___BV_tab_button__\"]"))));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"__BVID__52___BV_tab_button__\"]")).click();
		Thread.sleep(2000);
//Click the DanielAdmin Btn
		WebElement RolePicking= driver.findElement(By.xpath("//*[@id=\"__BVID__86\"]"));
		js.executeScript("arguments[0].click();", RolePicking);

        Thread.sleep(2000);
		WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		js.executeScript("arguments[0].click()",Logout);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("username")).sendKeys(userid);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(4500);

	   //	driver.quit();


	}

}