package com.EHSAdmin;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class CreateAndEditCertificate {

	public void CreateAndEditCertificate() throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
		try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
			actions.build().perform(); }
		catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
		driver.manage().window().maximize();
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(4500);


		//Clicking on EHS Admin
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Admin')]")).click();
		Thread.sleep(1000);

		WebElement EHSAdmin = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",EHSAdmin);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Admin')]")).click();
		Thread.sleep(1000);

		WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));

		js.executeScript("arguments[0].click();",ele1);
		Thread.sleep(2000);
		//Clicking on 'Certificate Management'
		driver.findElement(By.partialLinkText("Certificate Management")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clicking on 'Create Certificate'
		//driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div/div[2]/div[1]/div[2]/button")).click();
		//Thread.sleep(1500);
		WebElement CreateCert=driver.findElement(By.xpath("//a[contains(text(),'Create New Certificate')]"));
		js.executeScript("arguments[0].click();",CreateCert);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
		String title = generator.generate(10);

		// Entering the Certificate Name that you want for the certificate
		driver.findElement(By.id("name")).sendKeys(title);

		// Entering the Description that you want for your certificate
		driver.findElement(By.id("descr")).sendKeys("this is the certificate description !");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clicking on the 'Save' button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Save')]"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread.sleep(2000);
		// clicking the 'Back' button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Back')]"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Back')]")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Searching for the lab name that you just created to show in the search result
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//input[@class='form-control']")));
		actions.click();
		actions.sendKeys(title);
		actions.build().perform();

		//Click the first result
		Thread.sleep(2500);
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();
		Thread.sleep(1500);

		driver.findElement(By.id("descr")).clear();
		driver.findElement(By.id("descr")).sendKeys("im editing the certificate description");
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Save')]"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Back')]"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Back')]")).click();
		Thread.sleep(1500);
		actions.moveToElement(driver.findElement(By.xpath("//input[@class='form-control']")));
		actions.click();
		actions.sendKeys(title);
		actions.build().perform();

		//Click the first result
		Thread.sleep(2500);
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();
		Thread.sleep(1500);
		String Edit = driver.findElement(By.id("descr")).getAttribute("value");

		System.out.println(Edit);

		if(!Edit.equals("im editing the certificate description")) {
			Assert.fail("something went wrong while editing the certificate");
		}
		Thread.sleep(2000);

		driver.quit();

	}

}
