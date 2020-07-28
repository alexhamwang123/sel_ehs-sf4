//This script is written to test if the course brings the user to Page 1 of x, 
//after the user clicks on 'No' when asked "Do you want to resume from where you left"

package com.OnlineCourseManagement;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//@Test
@Test
public class CheckBookmark_ClickNo {

	public void CheckBookmark_ClickNo() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);
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


		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")));
		Thread.sleep(1000);
		 driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys("kimi-online-004");
		 
	//	 driver.findElement(By.name("searchButton")).click();
		 
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 String mainWindow = driver.getWindowHandle();
		 //msg_head857e61d0-2598-102a-b70c-b707fad2 -> it is a EHS-1000 - EHS Essentials
		 driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();
		 
		 for(String winHandle : driver.getWindowHandles()){
			 if(winHandle!=mainWindow)
	          driver.switchTo().window(winHandle);
		    }
		
		 
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]")));
		Thread.sleep(2000);
		JavascriptExecutor js= (JavascriptExecutor)driver;
		 WebElement English= driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
		 js.executeScript("arguments[0].click();",English);
//         driver.findElement(By.xpath("//*[@id='label']")).click();
		 
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //Click Cancel to not resume where left last time
		driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();

		Thread.sleep(5000);
		 //Next Btn
		JavascriptExecutor js0= (JavascriptExecutor)driver;
		WebElement NextBtn= driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/div/div[2]/button[3]"));
		js0.executeScript("arguments[0].click();",NextBtn);
		Thread.sleep(2000);

		//Click Exit Btn
		JavascriptExecutor js1= (JavascriptExecutor)driver;
		WebElement ExitBtn=driver.findElement(By.xpath("//li[contains(text(),'Exit')]"));
		js1.executeScript("arguments[0].click();",ExitBtn);

		Thread.sleep(2000);
		//Click OK Btn for Exit

		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		Thread.sleep(2000);


		for(String winhandle:driver.getWindowHandles()){
			driver.switchTo().window(winhandle);
		}

		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys("kimi-online-004");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();
		Thread.sleep(2000);
		for(String winhandle:driver.getWindowHandles()){
			driver.switchTo().window(winhandle);
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]")));
		Thread.sleep(2000);

		JavascriptExecutor js2= (JavascriptExecutor)driver;
		WebElement English1= driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
		js2.executeScript("arguments[0].click();",English1);
		Thread.sleep(2500);

		driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();
		Thread.sleep(3000);

		if(driver.getPageSource().contains("Page 1 of 45")){
			System.out.println("The test is successful");
		}
		else{
			Assert.fail("The test failed");
		}

		driver.quit();
	}

}
