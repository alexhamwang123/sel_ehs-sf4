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
public class OnlineCourse_CompleteAndSubmit {

	public void OnlineCourse_CompleteAndSubmit() throws IOException, InterruptedException {

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

		driver.manage().window().maximize();

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(2000);

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
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Create User']"))));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type='button'][value='Create User']")).click();

		Thread.sleep(3000);

		// Enter the First Name of the user that you wish to create
		String userid = driver.findElement(By.id("detailBadgeNumber")).getAttribute("value");


		driver.findElement(By.id("detailFirstName")).sendKeys(userid);

		// Enter the Last Name of the user that you wish to create
		driver.findElement(By.id("detailLastName")).sendKeys(userid);

		// Click on 'Select' for Site
		driver.findElement(By.id("selectBtnSite")).click();

		// Enter the search value as "SCV"
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("searchName"))));
		Thread.sleep(1000);
		driver.findElement(By.id("searchName")).sendKeys("SCV");

		// Click on Search
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='submit'][value='Search']"))));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click on 'SCV' from the search results
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr/td[2]/a"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr/td[2]/a")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Enter the email of the user that you wish to create
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("detailEmailAddress"))));
		Thread.sleep(1000);
		driver.findElement(By.name("detailEmailAddress")).sendKeys(userid + "@trismax.com");

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
		Thread.sleep(2000);

		// Click on 'Save' button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Save']")).click();

		Thread.sleep(4000);

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
		driver.findElement(By.id("password")).sendKeys(userid);

		driver.findElement(By.xpath("//button[@type='submit']")).click();


		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("welcomeShowRS"))));
		Thread.sleep(1000);
		driver.findElement(By.id("welcomeShowRS")).click();
		Thread.sleep(1500);
		driver.findElement(By.name("question[4586]")).click();
		driver.findElement(By.name("question[1361]")).click();
		driver.findElement(By.name("question[4562]")).click();
		driver.findElement(By.name("question[4225]")).click();
		driver.findElement(By.name("question[4127]")).click();
		driver.findElement(By.name("question[4193]")).click();
		driver.findElement(By.name("question[145]")).click();
		driver.findElement(By.name("question[1164]")).click();
		driver.findElement(By.name("question[4676]")).click();

		Thread.sleep(1500);

		driver.findElement(By.xpath("//*[@id=\"rs-modal1___BV_modal_footer_\"]/div/button")).click();
		Thread.sleep(4500);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("annContinue"))));
		Thread.sleep(1000);
		driver.findElement(By.id("annContinue")).click();

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




		//Clicking the Radio Button
		//driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();

//		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();


		Thread.sleep(2000);

		//Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"c-quiz\"]/ol/li/div[2]/label[4]/input"))));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"c-quiz\"]/ol/li/div[2]/label[4]/input")).click(); // Cat
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"c-body\"]/div/div[2]/div[2]/button")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn float-right']")).click();
		Thread.sleep(2000);
		WebElement Exit=driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button[2]"));
		js2.executeScript("arguments[0].click();",Exit);
		Thread.sleep(2000);
		driver.quit();

		

	}

}
