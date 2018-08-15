package com.EHSAdmin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class CreateCertificate {

	public void CreateCertificate() throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://twn:WrongAdeeDow2-@demo.accentrixus.com:8330");

        driver.manage().window().maximize();

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);

		
		//Clicking on EHS Admin
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[4]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
				
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on 'Certificate Management'
        driver.findElement(By.partialLinkText("Certificate Management")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clicking on 'Create Certificate'
		driver.findElement(By.cssSelector("input[type='button'][value='Create Certificate']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title = generator.generate(10);

        // Entering the Certificate Name that you want for the certificate
		driver.findElement(By.name("detailCertName")).sendKeys(title);

		// Entering the Description that you want for your certificate
		driver.findElement(By.name("detailCertDescr")).sendKeys("this is the certificate description !");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clicking on the 'Save' button
		driver.findElement(By.cssSelector("input[type='submit'][value='Save']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// clicking the 'Back' button
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Searching for the lab name that you just created to show in the search result
        Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id("secondmenu")));
		actions.click();
		actions.sendKeys(title);
		actions.build().perform();

		driver.findElement(By.cssSelector("input[value='Go']")).click();
		Thread.sleep(2500);
		driver.findElement(By.className("editAction")).click();
		Thread.sleep(1500);

		driver.findElement(By.name("detailCertDescr")).clear();
		driver.findElement(By.name("detailCertDescr")).sendKeys("im editing the certificate description");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();
        Thread.sleep(1500);
        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys(title);
        actions.build().perform();

        driver.findElement(By.cssSelector("input[value='Go']")).click();
        Thread.sleep(2500);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(1500);
        if(!driver.findElement(By.name("detailCertDescr")).getAttribute("innerHTML").equals("im editing the certificate description")) {
            Assert.fail("something went wrong while editing the certificate");
        }
        Thread.sleep(2000);
        driver.quit();


	}

}
