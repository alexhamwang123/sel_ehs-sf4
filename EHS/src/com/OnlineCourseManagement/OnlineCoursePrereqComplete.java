package com.OnlineCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test

public class OnlineCoursePrereqComplete {
    public void CompletetheprerequisitebeforetakingChecklist() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir") + "/PasswordFileEHS.properties");

        FileInputStream inStream = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);
        try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
            actions.build().perform(); }
        catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }

        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);
        //go to onlinecourseManagement


        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();

        //pick a class  & Assign prerequisite



        //Send in course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[1]/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[1]/div[1]/div/input")).sendKeys("EHS-1500");

        //Click Result
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/table/tbody/tr/td[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/table/tbody/tr/td[1]")).click();

        //Click Online Details
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[2]/a")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[2]/a")).click();

        //Click Prereq Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[2]/div/div[6]/div/div/div/div/div/button"))));
        if(!driver.getPageSource().contains("EHS-1900-2017")) {
            Thread.sleep(1000);
            //input prereq
            //Click Select Btn
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[2]/div/div[6]/div/div/div/div/div/button")).click();

            //Search prereq courese
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")));
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys("EHS-1900-2017");

            //Select the result
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();

        }



        //got to the prerequisite and complete it
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("kimi-online-006"); //checklist name on localhost


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

        try {
            if (driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[1]")).isDisplayed()) {
                driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[1]")).click();
            }
        }
        catch(Exception e){
            System.out.println("There is no 'go back to last point Btn' Window");
        }


        Thread.sleep(2000);

        //Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"c-quiz\"]/ol/li/div[2]/label[4]/input"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"c-quiz\"]/ol/li/div[2]/label[4]/input")).click(); // Cat

        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"c-body\"]/div/div[2]/div[2]/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn float-right']")).click();
        Thread.sleep(2000);
        WebElement OKBtn=driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]"));
        js2.executeScript("arguments[0].click();",OKBtn);
        Thread.sleep(2000);
        //After completion of prerequisite, try "1bLkHwGarU" again.
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("EHS-1500");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
        Thread.sleep(1500);
        String handle= driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {

            if(!winHandle.equals(handle)){
                driver.switchTo().window(winHandle);
                WebElement Successconfrimbtn=driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
                if(Successconfrimbtn.isDisplayed()){
                    System.out.println("Test is successful");
                }
            }
        }


        driver.quit();






    }
}
