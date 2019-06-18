//This script is written to create a new Announcement and check in the list of announcements 
//whether the newly created announcement appears in the list for EHS system

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class CreateAnnouncement {

	public void CreateAnnouncement1() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait Wait= new WebDriverWait(driver,30);
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

        try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on 'EHS Admin'

		JavascriptExecutor js = (JavascriptExecutor)driver;

		WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));

		js.executeScript("arguments[0].click();",ele1);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on Announcement
		//driver.findElement(By.xpath("//*[@id='left']/table/tbody/tr[1]/td/a")).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on 'Create Announcement'
        Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Create Announcement']")));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector("input[type='button'][value='Create Announcement']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Check the box for 'Never Expires'
		if ( !driver.findElement(By.name("detailAnnouncementIsNeverExpire")).isSelected() )
		{
		     driver.findElement(By.name("detailAnnouncementIsNeverExpire")).click();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 //Clicking on Announcement Category: Welcome
		 new Select(driver.findElement(By.name("detailAnnouncementStyle"))).selectByVisibleText("Welcome");
		Thread.sleep(1000);
		driver.findElement(By.name("detailAnnouncementRemarks")).sendKeys("this is the announcement remarks!");
		 //Clicking on Announcement Category: Risk Survey Incomplete
		 //driver.findElement(By.cssSelector("input[type='radio'][value='RS_Incomplete']")).click();
			
		 //Clicking on Announcement Category: Risk Survey Completed
		 //driver.findElement(By.cssSelector("input[type='radio'][value='RS_Completed']")).click();
				
		 //Clicking on Announcement Category: Manager Portal Welcome
		 //driver.findElement(By.cssSelector("input[type='radio'][value='MP_Welcome']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title = generator.generate(10);

        //Entering the 'Announcement Title'
		driver.findElement(By.name("detailAnnouncementTitle")).sendKeys(title);
		Thread.sleep(1500);
		//Entering the Announcement Content
		driver.findElement(By.xpath("//*[@id=\"mceu_23\"]")).click();
		((ChromeDriver) driver).executeScript("tinyMCE.activeEditor.setContent('this is a test only ')");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on 'Save'
		driver.findElement(By.cssSelector("input[type='submit'][value='Save']")).click();
		Thread.sleep(3500);
		//clicking on 'Back'
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        new Select(driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/select"))).selectByVisibleText("Announcement Title");
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id("secondmenu")));
		actions.click();
		actions.sendKeys(title);
		actions.build().perform();
		//Click on the 'Go' button to display the result
		driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
        Thread.sleep(4000);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(7500);
        System.out.println("checkpoint 1");
		driver.findElement(By.xpath("//iframe[@id='ann-content_ifr']")).click();

		//driver.findElement(By.xpath("//body[@id='tinymce']")).clear();
		((ChromeDriver) driver).executeScript("tinyMCE.activeEditor.setContent('im editing the announcement content !!')");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();
        Thread.sleep(1500);
        new Select(driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/select"))).selectByVisibleText("Announcement Title");
        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys(title);
        actions.build().perform();
        driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();

        Thread.sleep(2000);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(1500);
        if(!driver.findElement(By.name("detailAnnouncementContent")).getAttribute("innerHTML").contains("im editing the announcement content !!")) {
            Assert.fail("something went wrong with editing the announcement content");
        }

        Thread.sleep(2000);
        driver.quit();


    }

}
