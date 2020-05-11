//This script is written to create a new Lab.
//Flow: EHS UAT > Admin > EHS Admin > Lab Management > Create Lab

package com.EHSAdmin;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
public class CreateAndEditLab {

	public void CreateLab() throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

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
        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",ele);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));

        js.executeScript("arguments[0].click();",ele1);
        Thread.sleep(2000);


		// Click on 'Lab Management'
        driver.findElement(By.partialLinkText("Lab Management")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click on 'Create Lab'
		WebElement CreateBtb=driver.findElement(By.xpath("//a[contains(text(),'Create New Lab')]"));
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();",CreateBtb);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
		String title = generator.generate(10);

        // Enter the 'Lab Name' that you wish to create
		driver.findElement(By.id("input-labName")).sendKeys(title);

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Enter the Description of the lab that you wish to create
		driver.findElement(By.id("input-labDesc")).sendKeys("this is the lab description!");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on 'Lab Manager' to assign a manager to the lab you wish to create
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/div/div[4]/div/div/div[1]/div/button")).click();

		// Enter the Badge Number
		driver.findElement(By.id("criteriaBadge")).sendKeys(username);

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click the 'Search' button
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/footer/div/button")).click();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Choosing the user username from the list for this case
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();


		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on 'Site'
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/div/div[6]/div/div/div[1]/button")).click();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Choosing the site 'DLL' for this case
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys("SCV");
        Thread.sleep(4500);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();



		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Select Building
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/div/div[5]/div/div/div[1]/button")).click();
        Thread.sleep(4500);
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys("Almanor");
        Thread.sleep(4500);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr[1]/td")).click();



		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Enter the room number that you wish where the lab session would take place
		driver.findElement(By.id("input-labRoom")).sendKeys("114");
        Thread.sleep(1000);
		// Click the 'Save' button to save the lab session record to display in the list of Labs
		WebElement Save=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/button"));
		js1.executeScript("arguments[0].click();",Save);

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on the 'Back' button to show the name of the lab in the list of labs
		driver.navigate().back();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Now searching for the Lab's name to see if it gets displayed in the list of the labs
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[1]/div/input")).sendKeys(title);
		Thread.sleep(1500);
        //Click the Lab
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[1]/table/tbody/tr/td[1]")).click();
		Thread.sleep(3000);
		String description = generator.generate(20);
		driver.findElement(By.id("input-labDesc")).clear();
        driver.findElement(By.id("input-labDesc")).sendKeys("im editing the lab description with a random word: " + description);
        Thread.sleep(1500);
        WebElement Save01=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/button"));
		js1.executeScript("arguments[0].click();",Save01);
        Thread.sleep(3500);
        driver.navigate().back();

        Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[1]/div/input")).clear();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[1]/div/input")).sendKeys(title);
        Thread.sleep(1500);
		//Click the Lab
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div[1]/table/tbody/tr/td[1]")).click();
		Thread.sleep(4000);
		//if (!driver.getPageSource().contains(description)) {
        //    Assert.fail("something went wrong while editing the lab description");
        //}
        driver.quit();



    }

}
