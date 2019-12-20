package com.EHSAdmin;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class CreateFAQ {

	public void CreateFaq() throws InterruptedException, IOException {

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

            //Clicking on EHS Admin
            WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();",ele);


            Thread.sleep(2000);
            //Click on FAQ Management
            driver.findElement(By.partialLinkText("FAQ Management")).click();
            Thread.sleep(3500);
            // Click on Create FAQ
            WebElement Create_FAQBtn=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[1]/div[3]/ul/a"));
            js.executeScript("arguments[0].click();",Create_FAQBtn);
            Thread.sleep(1500);
            // Enter the FAQ Title
            RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
            String title = generator.generate(10);

            driver.findElement(By.xpath("//*[@id=\"title\"]")).sendKeys(title);

            // Enter the FAQ description
            driver.findElement(By.xpath("//*[@id=\"content\"]")).sendKeys("this is the faq description!");

            try {
                    Thread.sleep(2000);
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }

            //Select Category
            new Select( driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/form/div[2]/div[1]/div/div/select"))).selectByVisibleText("TRAINING PLAN");
            //Click on Save
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/form/div[2]/div[4]/fieldset/div/div/button[1]")).click();
            Thread.sleep(3500);
            //Click back
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/form/div[2]/div[4]/fieldset/div/div/button[2]")).click();
            Thread.sleep(3500);
            //Select Category
            new Select( driver.findElement(By.xpath("//*[@id=\"faqCate\"]"))).selectByVisibleText("TRAINING PLAN");

     /*   Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys(title);
        actions.build().perform();
     */
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/input")).sendKeys(title);
            Thread.sleep(500);
            //CLick the search result
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[2]/table/tbody/tr/td[2]")).click();
            Thread.sleep(2500);
            String Editing="editingTimes";
            driver.findElement(By.xpath("//*[@id=\"content\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"content\"]")).sendKeys(Editing);
            Thread.sleep(500);
            //Click on Save
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/form/div[2]/div[4]/fieldset/div/div/button[1]")).click();
            Thread.sleep(3500);
            //Click back
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/form/div[2]/div[4]/fieldset/div/div/button[2]")).click();
            Thread.sleep(3500);
            //Search the FAQ
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/input")).sendKeys(title);
            //CLick the search result
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[2]/table/tbody/tr/td[2]")).click();
            System.out.println("Checkpoint 1");
            Thread.sleep(1500);
            Thread.sleep(1500);


            String Content= driver.findElement(By.id("content")).getText();
            WebElement TextArea=driver.findElement(By.id("content"));
            System.out.println("Checkpoint 2"+Content);
            System.out.println("Checkpoint 3"+TextArea.getAttribute("value"));
            if(!TextArea.getAttribute("value").contains(Editing)) {
                    Assert.fail("something went wrong while editing the faq content");
            }

            Thread.sleep(2000);
            WebElement Help=driver.findElement(By.xpath("//a[contains(text(),'Help')]"));
            js.executeScript("arguments[0].click();",Help);

            Thread.sleep(3000);
            for(String winhandle: driver.getWindowHandles()){
                    driver.switchTo().window(winhandle);
            }

            Thread.sleep(3000);
            if(!driver.getPageSource().contains(title)){
                    Assert.fail("The test fialed"); }
            else{
                    System.out.println("The test is successful");
            }
            Thread.sleep(2000);
            driver.quit();



    }

}
