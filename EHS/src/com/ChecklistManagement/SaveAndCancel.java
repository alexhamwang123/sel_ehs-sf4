//Test if the web site brings on the same page where the user left 
//when he does not click all the answers and clicks ‘Save’ and then ‘Cancel’

package com.ChecklistManagement;

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
import java.util.concurrent.TimeUnit;

public class SaveAndCancel {

	public static void main(String[] args) throws IOException {
		
        //System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
		
//		WebDriver driver = new ChromeDriver();
//
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		File file = new File(System.getProperty("user.dir")+"/EHS/PasswordFileEHS.properties");
//		FileInputStream inStream=new FileInputStream(file);
//		Properties prop=new Properties();
//		prop.load(inStream);
//		String urladdr = prop.getProperty("url");
//		driver.get(urladdr);
//		driver.manage().window().maximize();
//		driver.findElement(By.id("login_login_id")).sendKeys("X00001554");
//		String val = prop.getProperty("userpassword");
//		driver.findElement(By.id("login_password")).sendKeys(val);
//
//		driver.findElement(By.name("submit")).click();
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		driver.findElement(By.xpath("//*[@id='navPrimary']/li[2]/a")).click();
//
//		driver.findElement(By.id("srch_fld")).sendKeys("trismax");
//
//		driver.findElement(By.name("searchButton")).click();
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//Click on the Enroll button
//		driver.findElement(By.xpath("//*[@id='msg_headf3884a8ef079d9b188b2b4ddf3ddf1ee']/table/tbody/tr/td[5]/img")).click();
//
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//Click on 'Default-English' Language option
//		driver.findElement(By.id("crselink1")).click();
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		//Clicking the Radio Button
//		//driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();
//
//		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();
//
//
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		driver.findElement(By.xpath("//*[@id='775cdb32e2e8cc9145320877a80040a5']")).clear();
//		driver.findElement(By.xpath("//*[@id='775cdb32e2e8cc9145320877a80040a5']")).sendKeys("40");
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		driver.findElement(By.id("fancyConfirm_ok")).click();
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		driver.findElement(By.cssSelector("input[type='button'][value='Cancel']")).click();
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		driver.findElement(By.id("fancyConfirm_ok")).click();
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		driver.findElement(By.id("srch_fld")).sendKeys("trismax");
//
//		driver.findElement(By.name("searchButton")).click();
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		driver.findElement(By.xpath("//*[@id='msg_headf3884a8ef079d9b188b2b4ddf3ddf1ee']/table/tbody/tr/td[5]/img")).click();
//
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		driver.findElement(By.id("crselink1")).click();
		

	}

}
