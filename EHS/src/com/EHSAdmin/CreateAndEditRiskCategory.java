//This script is written to create a new Risk Category under RC Admin

package com.EHSAdmin;

import org.apache.commons.text.RandomStringGenerator;
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

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class CreateAndEditRiskCategory {

	public void CreateRiskCategory() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
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

        //Clicking on EHS Admin
        JavascriptExecutor js = (JavascriptExecutor)driver;

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
        js.executeScript("arguments[0].click();",ele);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]")).click();
		
		//Click on the 'Create Risk Category' button
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/button"))));
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/button")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/ul/a[1]")).click();
            try {
                    Thread.sleep(2000);
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title = generator.generate(10);
            //Enter the Risk Category Name
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-name"))));
            Thread.sleep(1000);
            driver.findElement(By.id("input-name")).sendKeys(title);

            //Enter the Risk Category Abbr Name
            driver.findElement(By.id("input-abbrev")).sendKeys(title);

            //Enter the description
            driver.findElement(By.id("input-desc")).sendKeys("this is the description for at least 20 characters!");

            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div/div[4]/div/div/div/div[1]/div/div/button")).click();
            Thread.sleep(2500);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("criteriaBadge"))));
            Thread.sleep(1000);
            driver.findElement(By.id("criteriaBadge")).sendKeys(username);
            //Click Search
            Thread.sleep(2500);
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/footer/div/button")).click();
            Thread.sleep(2500);
            //Click Result
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]"))));
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
            Thread.sleep(1000);
            //Click Save btn
            WebElement Save= driver.findElement(By.xpath("//*[@id=\"admin-curriculum\"]/div/div[2]/button"));
            JavascriptExecutor js2 = (JavascriptExecutor)driver;
            js2.executeScript("arguments[0].click();", Save);

        Thread.sleep(3500);
            //Clicking on EHS Admin
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin1);
        Thread.sleep(1000);

        WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
            JavascriptExecutor js1 = (JavascriptExecutor)driver;
            js1.executeScript("arguments[0].click();",ele1);

            try {
                    Thread.sleep(2000);
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }

        driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]")).click();
        Thread.sleep(4500);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(title);
        Thread.sleep(4000);
        //CLick the result
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(3500);
        driver.findElement(By.id("input-desc")).clear();
        driver.findElement(By.id("input-desc")).sendKeys("im editing the description !");
        Thread.sleep(500);
        //Click Save
        driver.findElement(By.xpath("//*[@id=\"admin-curriculum\"]/div/div[2]/button")).click();
        Thread.sleep(4500);
            //Clicking on EHS Admin
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin2=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin2);
        Thread.sleep(1000);

        WebElement ele2 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
            JavascriptExecutor js3 = (JavascriptExecutor)driver;
            js3.executeScript("arguments[0].click();",ele2);

            try {
                    Thread.sleep(2000);
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }

            driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]")).click();
            Thread.sleep(4500);
            driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(title);
            Thread.sleep(4000);
            //CLick the result
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();
            Thread.sleep(3500);

        if(!driver.findElement(By.id("input-desc")).getAttribute("value").equals("im editing the description !")) {
            Assert.fail("something went wrong while editing the description");
        }

        Thread.sleep(2000);
        driver.quit();

	}

}
