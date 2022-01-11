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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Test
public class DirectReport {

    public void DirectReport() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait Wait= new WebDriverWait(driver,30);
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

        //Clicking on 'User Admin'
        JavascriptExecutor js = (JavascriptExecutor)driver;

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        js.executeScript("arguments[0].click();",ele);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("badgeNo"))));
        Thread.sleep(2000);
        driver.findElement(By.id("badgeNo")).sendKeys("X00001572");

        //CLick Find Button
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Find Users')]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'Find Users')]")).click();

        //CLick on the User found
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(" /html/body/div[1]/main/div/div[1]/div/div/div[2]/div/div/table/tbody/tr/td[1]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath(" /html/body/div[1]/main/div/div[1]/div/div/div[2]/div/div/table/tbody/tr/td[1]")).click();

        //Click on the Direct Report Page
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/header/div[2]/nav/div/ul/li[6]/a")).click();
        Thread.sleep(1000);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Manager Reports')]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Manager Reports')]")).click();
        Thread.sleep(2000);
        Thread.sleep(2000);


        //Array to contain the Badge name
        ArrayList<String> BadgeList= new ArrayList<String>();
        //Find Direct reports Table All TRS
        List <WebElement> Profile_DirectReport_TableTRs= driver.findElements(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div/table/tbody/tr"));
        for(WebElement e:Profile_DirectReport_TableTRs){
            //Find Direct reports Table All TDS

            List<WebElement> TDS=  e.findElements(By.tagName("td"));
            //Get the Badge Name From the First value of all TDS
            WebElement BadgeElement=TDS.get(1);

            String StringBadge=BadgeElement.getText();
             System.out.println("Check Point 1");
             System.out.println(StringBadge);
             //Add the badge name to Arraylist
             BadgeList.add(StringBadge);
        }


        driver.findElement(By.partialLinkText("Manager's Portal")).click();
        Thread.sleep(3500);

        for(int i=0;i<BadgeList.size();i++){
          String OutputBadgeName=  BadgeList.get(i);
          System.out.println(OutputBadgeName);
           if(!driver.getPageSource().contains(OutputBadgeName)){
               Assert.fail("The direct Report in Manager Portal doesnt match the value in User Profile Page");
           }
        }
        Thread.sleep(2500);

        driver.quit();





    }

}
