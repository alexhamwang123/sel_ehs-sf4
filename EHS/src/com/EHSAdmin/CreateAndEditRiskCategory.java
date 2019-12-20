//This script is written to create a new Risk Category under RC Admin

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

//@Test
@Test
public class CreateAndEditRiskCategory {

	public void CreateRiskCategory() throws IOException, InterruptedException {

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

        driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]")).click();
		
		//Click on the 'Create Risk Category' button
		driver.findElement(By.cssSelector("input[type='button'][value='Create Risk Category']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title = generator.generate(10);
        //Enter the Risk Category Name
		driver.findElement(By.name("detailJobClassName")).sendKeys(title);
		
		//Enter the Risk Category Abbr Name
		driver.findElement(By.name("detailJobClassAbbrName")).sendKeys("RCR");
		
		//Enter the description
		driver.findElement(By.name("detailJobClassDescr")).sendKeys("this is the description!");

		Thread.sleep(1500);
		driver.findElement(By.id("selectBtnCreMaA")).click();
		Thread.sleep(2500);
		driver.findElement(By.name("badgeNo")).sendKeys(username);
		driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("a[href*='selectCourseManager']")).click();
        Thread.sleep(2500);



		
		//Click on the 'Save' button
		driver.findElement(By.id("saveJC")).click();
		Thread.sleep(7500);
        driver.findElement(By.id("addCourse")).click();
        Thread.sleep(4500);
        WebElement secondmenu = driver.findElement(By.id("secondmenu"));

        Actions actions = new Actions(driver);
        actions.moveToElement(secondmenu);
        actions.click();
        actions.sendKeys("Classroom1");
        actions.build().perform();


        driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
        Thread.sleep(1000);
        String value = driver.findElement(By.cssSelector("input[type='checkbox'][name='addCourse[]']")).getAttribute("value");
        Thread.sleep(500);
        System.out.println(value);
        driver.findElement(By.id(value)).click();
        Thread.sleep(500);
        //WebElement add = driver.findElement(By.cssSelector("input[type='button'][value='Add']"));
        WebElement add = driver.findElement(By.xpath("//*[@id=\"paging_result\"]/input[6]"));
        js.executeScript("arguments[0].click();", add);



        Thread.sleep(3500);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[value='Save']")).click();
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();
        Thread.sleep(4500);
        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys(title);
        actions.build().perform();
        driver.findElement(By.cssSelector("input[value='Go']")).click();
        Thread.sleep(4000);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(3500);
        driver.findElement(By.name("detailJobClassDescr")).clear();
        driver.findElement(By.name("detailJobClassDescr")).sendKeys("im editing the description !");
        Thread.sleep(500);

        driver.findElement(By.cssSelector("input[value='Save']")).click();
        Thread.sleep(4500);
        driver.findElement(By.cssSelector("input[value='Back']")).click();
        Thread.sleep(4500);
        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys(title);
        actions.build().perform();
        driver.findElement(By.cssSelector("input[value='Go']")).click();
        Thread.sleep(4000);
        driver.findElement(By.className("editAction")).click();
        Thread.sleep(3500);

        if(!driver.findElement(By.name("detailJobClassDescr")).getAttribute("innerHTML").equals("im editing the description !")) {
            Assert.fail("something went wrong while editing the description");
        }

        Thread.sleep(2000);
        driver.quit();

	}

}
