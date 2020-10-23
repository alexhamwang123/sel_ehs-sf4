package com.OnlineCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class OnlineRandomQuestionOrder {
    public void OnlineRandomQuestionOrder()throws IOException,InterruptedException{


        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        WebDriverWait Wait= new WebDriverWait(driver,30);
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
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);

        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js1.executeScript("arguments[0].click()", courseAdmin1);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[1]/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[1]/div[1]/div/input")).sendKeys("DanielOnlineRandom01");
        Thread.sleep(1000);

        //CLick the result
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();

        //Click Online Variants
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();
        //Click Edit Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn-sm btn btn-outline-primary border-0']")).click();

        //Click Edit Course Content Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Edit Course Content')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Edit Course Content')]")).click();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(1000);

        //Select question order as normal
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        new Select(driver.findElement(By.xpath("//div[3]//div[1]//select[1]"))).selectByVisibleText("Display quiz questions in order");

        //Click Save btn
        Thread.sleep(1000);

        WebElement Save_Btn=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js.executeScript("arguments[0].click()", Save_Btn);
        Thread.sleep(1000);
        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        //Close the window
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        driver.close();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys("DanielOnlineRandom01");

        Thread.sleep(1000);

        //click the result
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        //Click English btn
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]")));
        WebElement English= driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]"));
        js1.executeScript("arguments[0].click()", English);

        Thread.sleep(2000);

        try{
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        } catch (Exception e) {
           System.out.println("Check Point 01");
        }

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(3000);

        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[1]/div[1]/p")));
        String Q1=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[1]/div[1]/p")).getText();
        String Q2=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[2]/div[1]/p")).getText();
        String Q3=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[3]/div[1]/p")).getText();
        String Q4=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[4]/div[1]/p")).getText();

        System.out.println(Q1+Q2+Q3+Q4);

        if ((Q1+Q2+Q3+Q4).equals("Q1Q2Q3Q4")) {
            System.out.println("Normal Order Successful");
        } else {

            Assert.fail("Normal Order Failed");
        }

        //Close the window
        Thread.sleep(1000);
        driver.close();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        //Select & Test Random Order
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click()", Admin1);
        Thread.sleep(1000);

        WebElement courseAdmin2 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js2.executeScript("arguments[0].click()", courseAdmin2);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[1]/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[1]/div[1]/div/input")).sendKeys("DanielOnlineRandom01");
        Thread.sleep(1000);

        //CLick the result
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();

        //Click Online Variants
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();
        //Click Edit Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn-sm btn btn-outline-primary border-0']")).click();

        //Click Edit Course Content Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Edit Course Content')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Edit Course Content')]")).click();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(1000);

        //Select question order as normal
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        new Select(driver.findElement(By.xpath("//div[3]//div[1]//select[1]"))).selectByVisibleText("Display quiz questions in random order");

        //Click Save btn
        Thread.sleep(1000);

        WebElement Save_Btn1=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js.executeScript("arguments[0].click()", Save_Btn1);
        Thread.sleep(1000);
        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        //Close the window
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        driver.close();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys("DanielOnlineRandom01");

        Thread.sleep(1000);

        //click the result
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        //Click English btn
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]")));
        WebElement English1= driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]"));
        js1.executeScript("arguments[0].click()", English1);

        Thread.sleep(2000);

        try {
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }


        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        Thread.sleep(3000);

        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[1]/div[1]/p")));
        String Q01=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[1]/div[1]/p")).getText();
        String Q02=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[2]/div[1]/p")).getText();
        String Q03=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[3]/div[1]/p")).getText();
        String Q04=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div[1]/ol/li[4]/div[1]/p")).getText();

        System.out.println(Q01+Q02+Q03+Q04);

        if(Q01=="Q1"&&Q02=="Q2"&&Q03=="Q3"&&Q04=="Q4"){

            Assert.fail("Random Order Failed");
        }
        else{
            System.out.println("Random Order Successful");

        }

        driver.quit();

    }
}
