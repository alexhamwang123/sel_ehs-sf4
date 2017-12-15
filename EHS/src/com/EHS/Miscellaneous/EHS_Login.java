//This program defines how to login to different EHS platforms as different users
//Platforms include: EHS UAT, EHS Secret Menu
//User roles for EHS UAT Login: 1) Admin, 2) Normal User, 3) USA Manager, 4) User Support, 5) Super Admin

package com.EHS.Miscellaneous;

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

public class EHS_Login {

	public static void main(String[] args) throws IOException {
		
        // - - - -- - - - - - Login as Admin - - - - - - - //
		
		System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get("https://192.168.15.131:8330");
		
		driver.findElement(By.id("login_login_id")).sendKeys("admin");
		
		File file=new File(System.getProperty("user.dir")+"/EHS.password.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String val = prop.getProperty("adminpassword");
		driver.findElement(By.id("login_password")).sendKeys(val);
		
		driver.findElement(By.name("submit")).click();
		

	}

}

               // - - - - -Login as Normal User - - - - - // 

/* System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");

WebDriver driver = new ChromeDriver();

driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

driver.manage().window().maximize();

driver.get("https://192.168.15.131:8330");

driver.findElement(By.id("login_login_id")).sendKeys("X00001554");

File file=new File(System.getProperty("user.dir")+"/EHS.password.properties");
FileInputStream inStream=new FileInputStream(file);
Properties prop=new Properties();
prop.load(inStream);
String val = prop.getProperty("userpassword");
driver.findElement(By.id("login_password")).sendKeys(val);

driver.findElement(By.name("submit")).click();

}
} */
