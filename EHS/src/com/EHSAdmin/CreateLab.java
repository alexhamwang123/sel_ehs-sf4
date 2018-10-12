//This script is written to create a new Lab.
//Flow: EHS UAT > Admin > EHS Admin > Lab Management > Create Lab

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
public class CreateLab {

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
        driver.manage().window().maximize();
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

		// Click on 'Lab Management'
        driver.findElement(By.partialLinkText("Lab Management")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click on 'Create Lab'
		driver.findElement(By.cssSelector("input[type='button'][value='Create Lab']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
		String title = generator.generate(10);

        // Enter the 'Lab Name' that you wish to create
		driver.findElement(By.name("detailLabName")).sendKeys(title);

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Enter the Description of the lab that you wish to create
		driver.findElement(By.name("detailLabDescr")).sendKeys("this is the lab description!");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on 'Lab Manager' to assign a manager to the lab you wish to create
		driver.findElement(By.id("selectBtnTeam")).click();

		// Enter the Badge Number
		driver.findElement(By.name("badgeNo")).sendKeys(username);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click the 'Search' button
		driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Choosing the user username from the list for this case
        driver.findElement(By.cssSelector("a[href*='selectTeamMemberNew']")).click();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on 'Site'
		driver.findElement(By.id("selectBtnSite")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Choosing the site 'DLL' for this case
        driver.findElement(By.id("searchName")).sendKeys("SCV");
		driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[onclick*='cliskselectSite']")).click();



		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(By.id("selectBtnBuilding")).click();
        Thread.sleep(2500);
        driver.findElement(By.name("searchBuilding[buildingName]")).sendKeys("Almanor");
        driver.findElement(By.cssSelector("input[value='Search']")).click();
		// Click on 'Building' to select a building where the lab session would be held
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[onclick*='cliskselectBuilding']")).click();



		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Enter the room number that you wish where the lab session would take place
		driver.findElement(By.id("aReplyEmail")).sendKeys("114");
        Thread.sleep(1000);
		// Click the 'Save' button to save the lab session record to display in the list of Labs
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on the 'Back' button to show the name of the lab in the list of labs
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Now searching for the Lab's name to see if it gets displayed in the list of the labs
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("detailLabDescr")).clear();

        String description = generator.generate(20);
        driver.findElement(By.name("detailLabDescr")).sendKeys("im editing the lab description with a random word: " + description);
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();

        Thread.sleep(2000);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(1500);
        if (!driver.findElement(By.name("detailLabDescr")).getAttribute("innerHTML").equals("im editing the lab description with a random word: " + description)) {
            Assert.fail("something went wrong while editing the lab description");
        }
        Thread.sleep(2000);
        driver.quit();



    }

}
