//Test if the web site brings on the same page where the user left 
//when he does not click all the answers and clicks ‘Save’ and then ‘Cancel’

package com.ChecklistManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.xpath.operations.Variable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
@Test(priority=4)
//@Test(dependsOnGroups = "ehs",priority=4)
public class CompleteAndSubmitChecklist {

	public void CompleteAndSubmitChecklist() throws IOException, InterruptedException {

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
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("kimi-checklist-006"); //checklist name on localhost


        Thread.sleep(3000);



        //Click on the Enroll button
        driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']")).click();

        Thread.sleep(3000);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        //Click on 'Default-English' Language option
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        WebElement Language_English=driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
        js2.executeScript("arguments[0].click();",Language_English);


        Thread.sleep(3000);


        //Clicking the Radio Button
        driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div/div/div[2]/div[1]/div/div[3]/label[2]/input")).click();

 		driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div/div/div[2]/div[2]/div/div[3]/label[1]/input")).click();


        Thread.sleep(2000);

        //Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
        //driver.findElement(By.xpath("//input[@value='Y']")).click(); // Cat

        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div/div/div[1]/div/button[3]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'Exit Course')]")).click();
        Thread.sleep(2000);

		driver.quit();
		

	}

}
