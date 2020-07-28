package com.Reports;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
@Test
public class EHSR_EvaluationCompletionReport {
    public void EHSR_EvaluationCompletionReport() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 30);
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

        Thread.sleep(4500);
        //Click EHS Report
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        driver.findElement(By.xpath("/html/body/div[1]/div/header/div[2]/nav/div/ul/li[6]/a")).click();
        Thread.sleep(1000);

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Reports')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",ele);
        Thread.sleep(2500);
        //Click on EvaluationCompletionReport

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Evaluation Completion Report')]"))));
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[contains(text(),'Evaluation Completion Report')]")).click();

        //CLick Select Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/button[1]"))));
        Thread.sleep(500);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/button[1]")).click();
        Thread.sleep(2500);
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys("Ergo");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//td[contains(text(),'02072020:Ergo Evaluation')]")).click();
        Thread.sleep(1500);
        //Click on OK
        driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
        Thread.sleep(4500);


        //Click the left calendar
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[2]/div/div/div[1]/label")).click();
        Thread.sleep(1000);
        WebElement CalendarBox01=driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[2]/div/div/div[1]/div/div/div/div[2]"));
        List<WebElement> list1 = CalendarBox01.findElements(By.tagName("span"));

        //Reverse the list to find the end day in case there are two todays or two yesterdays
        Collections.reverse(list1);

        Calendar calendar = new GregorianCalendar();
        int DateofToday= calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("DateofToday:"+DateofToday);

        String TodayOnly1= String.valueOf(DateofToday);
        System.out.println("TodayOnly:"+TodayOnly1);
        /*
        calendar.add(Calendar.DATE,1);
        Date date= calendar.getTime();
        String FullDateofToday= date.toString();
        String DateofToday=FullDateofToday.substring(FullDateofToday.length()-2);
        System.out.println("DateofToday:"+DateofToday);
        String TodayOnly1= DateofToday;
        System.out.println("TodayOnly1:"+TodayOnly1);

         */

        if (TodayOnly1.equals("01")){
            TodayOnly1="1";
        }
        if (TodayOnly1.equals("02")){
            TodayOnly1="2";
        }
        if (TodayOnly1.equals("03")){
            TodayOnly1="3";
        }
        if (TodayOnly1.equals("04")){
            TodayOnly1="4";
        }
        if (TodayOnly1.equals("05")){
            TodayOnly1="5";
        }
        if (TodayOnly1.equals("06")){
            TodayOnly1="6";
        }
        if (TodayOnly1.equals("07")){
            TodayOnly1="7";
        }
        if (TodayOnly1.equals("08")){
            TodayOnly1="8";
        }
        if (TodayOnly1.equals("09")){
            TodayOnly1="9";
        }
        String Number1= TodayOnly1;
        System.out.println(Number1);


        for(WebElement e : list1) {
            String dateofcanlendar1 = e.getAttribute("textContent");

            System.out.println(dateofcanlendar1);

            if (dateofcanlendar1.equals(Number1)) {
                System.out.println("Object Found Yeah Yeah Yeah");
                js1.executeScript("arguments[0].click();", e);
                break;
            }
            else{System.out.println("Object Not Found ");
            }
        }
        Thread.sleep(1000);

        //Click the right calendar
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[2]/div/div/div[2]/label")).click();
        Thread.sleep(1000);
        WebElement CalendarBox02=driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]"));
        List<WebElement> list2 = CalendarBox02.findElements(By.tagName("span"));

        //Reverse the list to find the end day in case there are two todays or two yesterdays
        Collections.reverse(list2);

        for(WebElement e : list2) {
            String dateofcanlendar2 = e.getAttribute("textContent");

            System.out.println(dateofcanlendar2);

            if (dateofcanlendar2.equals(Number1)) {
                System.out.println("Object Found Yeah Yeah Yeah");
                js1.executeScript("arguments[0].click();", e);
                break;
            }
            else{System.out.println("Object Not Found ");
            }
        }



        Thread.sleep(1000);
        //Click on Go

        WebElement Go=driver.findElement(By.xpath("//button[contains(text(),'Go')]"));
        js.executeScript("arguments[0].click();",Go);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(3500);

        driver.quit();



    }

    }
