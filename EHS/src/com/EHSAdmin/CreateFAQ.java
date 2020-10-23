package com.EHSAdmin;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
public class CreateFAQ {

	public void CreateFaq() throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait Wait = new WebDriverWait(driver, 30);
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

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
            js.executeScript("arguments[0].click();",ele);


            Thread.sleep(2000);
            //Click on FAQ Management
            driver.findElement(By.partialLinkText("FAQ Management")).click();
            Thread.sleep(3500);
            // Click on Create FAQ
            driver.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-light dropdown-toggle-no-caret']")).click();

            WebElement Create_FAQBtn=driver.findElement(By.xpath("//a[contains(text(),'Create New FAQ')]"));
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
            new Select( driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/div[1]/select[1]"))).selectByVisibleText("TRAINING PLAN");
           //Click Save Btn
           Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
           Thread.sleep(1000);
           WebElement Save=	driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
           js.executeScript("arguments[0].click();", Save);

           // clicking the 'Back' button
           Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Back')]")));
           Thread.sleep(1000);
           WebElement Back=	driver.findElement(By.xpath("//button[contains(text(),'Back')]"));
           js.executeScript("arguments[0].click();", Back);
            //Select Category
            new Select( driver.findElement(By.xpath("//*[@id=\"faqCate\"]"))).selectByVisibleText("TRAINING PLAN");

     /*   Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("secondmenu")));
        actions.click();
        actions.sendKeys(title);
        actions.build().perform();
     */
            driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(title);
            Thread.sleep(500);
            //CLick the search result
            driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")).click();
            Thread.sleep(2500);
            String Editing="editingTimes";
            driver.findElement(By.xpath("//*[@id=\"content\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"content\"]")).sendKeys(Editing);
            Thread.sleep(500);
            //Click Save Btn
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
            Thread.sleep(1000);
            WebElement Save1=	driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
            js.executeScript("arguments[0].click();", Save1);

            // clicking the 'Back' button
            Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Back')]")));
            Thread.sleep(1000);
            WebElement Back1=	driver.findElement(By.xpath("//button[contains(text(),'Back')]"));
            js.executeScript("arguments[0].click();", Back1);
            //Search the FAQ
            driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(title);
            //CLick the search result
            driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")).click();
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
            //Click the list contains help btn
            driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/header[1]/div[1]/div[1]/ul[1]/li[2]/a[1]")).click();
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
