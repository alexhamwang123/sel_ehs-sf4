//This program automates Compliance Report on the EHS system and generates the report.
//It also selects a date range and clicks on Go
//EHS UAT > Reports > EHS Reports > Compliance Report

package com.Reports;

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
public class EHSR_ComplianceReport {

	public void EHSR_ComplianceReport() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();
		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
actions.build().perform(); }
catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Admin')]")).click();
		Thread.sleep(1000);

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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("R2zMeXamXC");

		//Click the Result
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")).click();
		//CLick Statistics
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Statistics')]")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Statistics')]")).click();
		Thread.sleep(2000);
		String Active_Number= driver.findElement(By.xpath("//div[@class='tab-pane active card-body']//div[4]//div[1]//div[1]")).getAttribute("innerHTML").substring(6,7);

		System.out.println("Active_Number"+Active_Number);

		//Clicking on EHS Reports under Reports
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
		driver.findElement(By.xpath("/html/body/div[1]/div/header/div[2]/nav/div/ul/li[6]/a")).click();
		Thread.sleep(1000);

		WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Reports')]"));
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();",ele1);
		Thread.sleep(3500);
		//Click on Compliance Report 
		driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[5]")).click();
		Thread.sleep(3500);
		//Click on Select for choosing a Risk Category
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[1]/div/div/button")).click();
		Thread.sleep(4500);
		//Choose a risk category, say 2
		// AHA 4028f6bb21ec62fe0121ec6425eb0001
		driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).sendKeys("R2zMeXamXC");
//		driver.findElement(By.id("c76d705254ea0780e87d67ee8d609000")).click();
		Thread.sleep(1500);
		//Click the Ok button
		driver.findElement(By.xpath("//*[@id=\"select-results\"]/div[1]/div[2]/table/tbody/tr/td")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();

		
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Click on Go to generate the report 
		WebElement Go=driver.findElement(By.xpath("//button[contains(text(),'Go')]"));
		js.executeScript("arguments[0].click();",Go);
		//Click OK Btn
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();


		Thread.sleep(3500);
		String ComplianceNO=driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr/td[2]")).getText();
		System.out.println("ComplianceNO"+ComplianceNO);
		String ActiveNumber_ComplianceReprot=ComplianceNO.substring(ComplianceNO.length()-2,ComplianceNO.length()-1);
		System.out.println("ActiveNumber_ComplianceReprot"+ActiveNumber_ComplianceReprot);

		if(Active_Number.equals(ActiveNumber_ComplianceReprot)){
			System.out.println("Test is successful");
		}
		else{
			Assert.fail("Test Failed");
		}

		driver.quit();
		

	}

}
