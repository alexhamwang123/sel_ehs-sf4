package com.EHSAdmin;

import java.io.*;
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

//@Test
@Test(priority=63)
public class CreateFAQ {

	public void CreateFaq() throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);
		
		//Clicking on EHS Admin
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
				
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on FAQ Management
        driver.findElement(By.partialLinkText("FAQ Management")).click();
		Thread.sleep(1500);
		// Click on Create FAQ
		driver.findElement(By.cssSelector("input[type='button'][value='Create FAQ']")).click();
        Thread.sleep(1500);
		// Enter the FAQ Title
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title = generator.generate(10);

        driver.findElement(By.name("save[Title]")).sendKeys(title);

		// Enter the FAQ description
		driver.findElement(By.name("save[Content]")).sendKeys("this is the faq description!");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Click on Save
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();
        Thread.sleep(1500);
        new Select(driver.findElement(By.className("form-control"))).selectByVisibleText("FAQ Title");

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys(title);
        actions.build().perform();

        driver.findElement(By.cssSelector("input[value='Go']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[onclick*='getFaqDetail']")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("save[Content]")).clear();
        driver.findElement(By.name("save[Content]")).sendKeys("im editing the faq description !");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();
        Thread.sleep(1500);

        new Select(driver.findElement(By.className("form-control"))).selectByVisibleText("FAQ Title");

        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys(title);
        actions.build().perform();

        driver.findElement(By.cssSelector("input[value='Go']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[onclick*='getFaqDetail']")).click();
        Thread.sleep(1500);

        if(!driver.findElement(By.name("save[Content]")).getAttribute("innerHTML").equals("im editing the faq description !")) {
            Assert.fail("something went wrong while editing the faq content");
        }
        Thread.sleep(2000);
        driver.quit();


	}

}
