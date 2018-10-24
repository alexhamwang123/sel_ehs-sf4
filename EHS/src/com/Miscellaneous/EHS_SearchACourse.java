//This script is for displaying a list of courses after a specific course is searched
//Login > Courses > Enter course 'trismax' in search > Click 'Go' > Courses should be displayed
//In reference to mantis issue: 11065

package com.Miscellaneous;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//@Test
@Test(priority=72)
public class EHS_SearchACourse {

	public void EHS_SearchACourse() throws IOException {
		
       System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();

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
		
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[2]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Enter the name of the course here that you wish to search
		driver.findElement(By.id("srch_fld")).sendKeys("trismax");
		
		//Clicks on the Search button
		driver.findElement(By.name("searchButton")).click();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();


    }

}
