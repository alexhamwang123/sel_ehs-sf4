//This script is to test what the system prompts after the user
//answers partial questions and click on 'Grade Quiz'

package com.OnlineCourseManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
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
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test
//Mark
public class OnlineCourse_PartiallyCompleteAndSubmit {
	
	public void OnlineCourse_PartiallyCompleteAndSubmit() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver,30);
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

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(3000);

		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
		JavascriptExecutor js0 = (JavascriptExecutor)driver;
		js0.executeScript("arguments[0].click();",ele);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



// Click on Create User
		WebElement Create_User=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/ul/li[3]/div/a[1]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
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

		// sign out current user
		WebElement Logout0=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		js1.executeScript("arguments[0].click()", Logout0);
		Thread.sleep(1500);
		// driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
		//Thread.sleep(2000);
		//Sign in the user that never completed the prerequisite & try the prerequisite
		driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
		Thread.sleep(1500);

		driver.findElement(By.id("username")).sendKeys(userid);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(4500);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("welcomeShowRS"))));
		Thread.sleep(1000);
		driver.findElement(By.id("welcomeShowRS")).click();
		Thread.sleep(3500);
		driver.findElement(By.name("question[4586]")).click();
		driver.findElement(By.name("question[1361]")).click();
		driver.findElement(By.name("question[4562]")).click();
		driver.findElement(By.name("question[4225]")).click();
		driver.findElement(By.name("question[4193]")).click();
		driver.findElement(By.name("question[145]")).click();
		driver.findElement(By.name("question[1164]")).click();
		driver.findElement(By.name("question[4676]")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//*[@id=\"rs-modal1___BV_modal_footer_\"]/div/button")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("annContinue"))));
		Thread.sleep(1000);
		driver.findElement(By.id("annContinue")).click();
		Thread.sleep(4500);


		//got to the prerequisite and complete it
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("kimi-online-006"); //checklist name on localhost

		Thread.sleep(3000);



		//Click on the Enroll button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']")).click();

		Thread.sleep(3000);
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}


		//Click on 'Default-English' Language option
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		WebElement Language_English=driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
		js2.executeScript("arguments[0].click();",Language_English);


		Thread.sleep(3000);


		//Clicking the Radio Button
		//driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();

//		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();


		Thread.sleep(5000);

		//Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
		driver.findElement(By.xpath("//input[@value='10039']")).click(); // Cat

		Thread.sleep(2000);

		driver.close();
		Thread.sleep(2000);

		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.findElement(By.partialLinkText("Courses")).click();
		Thread.sleep(4500);

		//Search the Course
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("kimi-online-006");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
		Thread.sleep(1500);
		String working = "";

		working = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[3]")).getAttribute("innerHTML");

		System.out.println(working);

		if (!working.contains("Paused")) {
			Assert.fail("There is no Paused notification");
		}


		Thread.sleep(3500);
		driver.quit();



	}
}
