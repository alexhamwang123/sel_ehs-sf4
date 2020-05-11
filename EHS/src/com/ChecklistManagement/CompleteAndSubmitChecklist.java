//Test if the web site brings on the same page where the user left 
//when he does not click all the answers and clicks ‘Save’ and then ‘Cancel’

package com.ChecklistManagement;

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
@Test
public class CompleteAndSubmitChecklist {

	public void CompleteAndSubmitChecklist() throws IOException, InterruptedException {

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
        catch (NoSuchElementException e)
        { System.out.println("Bypass mode is no more needed"); }
		driver.manage().window().maximize();
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(4500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        //Search the Course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("kimi-checklist-006"); //checklist name on localhost


        //Click on the Enroll button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']"))));
        Thread.sleep(1000);
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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div[2]/div/div[1]/div/div[3]/label[2]/input"))));
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div[2]/div/div[1]/div/div[3]/label[2]/input")).click();

 		driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div[2]/div/div[2]/div/div[3]/label[1]/input")).click();


        //Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
        //driver.findElement(By.xpath("//input[@value='Y']")).click(); // Cat

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div[3]/button[3]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div[3]/button[3]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Exit Course')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Exit Course')]")).click();
        Thread.sleep(4000);

        for(String Winhandle:driver.getWindowHandles()){
            driver.switchTo().window(Winhandle);
        }
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("kimi-checklist-006");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/table/tbody/tr[1]/td[3]"))));
        Thread.sleep(1000);
        WebElement Status= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/table/tbody/tr[1]/td[3]"));

        if(Status.getText().contains("Completed")){
            System.out.println("The Test is successful");
        }
        else
        {
            System.out.println("There is no Complete Status in Couse Page");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("My History"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("My History")).click();


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(" /html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[1]/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("kimi-checklist-006");

        if(driver.getPageSource().contains("kimi-checklist-006")){
            System.out.println("The Test is successful");
        }
        else
        {
            Assert.fail("the test failed in my history");
        }

        driver.quit();
	}

}
