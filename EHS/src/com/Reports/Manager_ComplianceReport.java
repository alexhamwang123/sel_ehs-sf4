//This program is written to automate the 'Reports' function on the EHS UAT Web Site
//This program focuses on 'Compliance Report' under Manager Reports
//Flow: EHS UAT > Reports > Manager Reports > Compliance Report

package com.Reports;

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
@Test(priority=98)
public class Manager_ComplianceReport {

	public void Manager_ComplianceReport() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //driver.manage().window().maximize();
		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(4500);
		
		//Clicking on Manager Reports under Reports
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'Manager Reports')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
		Thread.sleep(2500);
		//Click on Compliance Report
		driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[4]")).click();
		Thread.sleep(2500);
		//We must select a Risk Category to generate the Compliance Report.
		//Click on the Select button for Risk category
		driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[2]/div/div/button")).click();
		
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Choose a Risk Category. Lets say we choose 2
		//AHA 4028f6bb21ec62fe0121ec6425eb0001
//		driver.findElement(By.id("c76d705254ea0780e87d67ee8d609000")).click();
		driver.findElement(By.xpath("//*[@id=\"select-results\"]/div[1]/div[2]/table/tbody/tr[2]/td")).click();
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on OK
		driver.findElement(By.xpath("//*[@id=\"__BVID__13___BV_modal_footer_\"]/button")).click();
		
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on Go to generate the report
		WebElement Go=driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[5]/div/button[1]"));
		js.executeScript("arguments[0].click();",Go);
		Thread.sleep(3500);

		driver.quit();
		
	}

}
