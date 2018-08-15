//This script is written to fill the description in the Contact page on the EHS home page
//Path: EHS UAT > Contact > Enter Detail > Submit

package com.Miscellaneous;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Test
public class EHS_ContactUs {

	public void EHS_ContactUs() throws IOException {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
        driver.get("https://twn:WrongAdeeDow2-@demo.accentrixus.com:8330");


        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();
		
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on the Contact button
		//driver.findElement(By.id("helpMenu")).click();
		driver.findElement(By.xpath("//*[@id='topbar']/div[3]")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Enter the detail
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('datepicker').value='02/14/2015'");


        driver.findElement(By.name("content")).sendKeys("I am not able to connect to the server. My internet connection is active. Please help me");
		
		//Click on 'Submit'
	    driver.findElement(By.cssSelector("input[type='button'][value='Submit']")).click();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();

	}

}
