package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.NoSuchElementException;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class NoShowNotPassedCompleted {
    public void NoShowNotPassedCompleted() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait Wait = new WebDriverWait(driver, 30);
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
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1500);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);

        Thread.sleep(2000);
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(1000);
        WebElement CreateBtn = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js.executeScript("arguments[0].click()", CreateBtn);


        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
        new Select(driver.findElement(By.id("course-category"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.id("course-fulfill"))).selectByVisibleText("Normal & Refresh");

                String CapitalLetter = generator.generate(1).toUpperCase();
        String courseId = generator.generate(10);
        courseId=CapitalLetter.concat(courseId);
        System.out.println(courseId);

        String Building = generator.generate(11);
        System.out.println(courseId);
        driver.findElement(By.id("course-num")).sendKeys(courseId);

        //input description
        driver.findElement(By.id("input-desc")).sendKeys("This is description");

        //Completion Message
        driver.findElement(By.id("completion-message")).sendKeys("Congrats Completion");

        //input Course Number
        driver.findElement(By.id("course-title")).sendKeys("This is title");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Classroom Details
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("expiration")));
        new Select(driver.findElement(By.id("expiration"))).selectByVisibleText("6 months");
        Thread.sleep(2000);;


        //input Classroom Details
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("training-hour")));
        //CLick Viewable btn
        WebElement ViewBtn = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[1]/div/a[2]/div/input"));
         js.executeScript("arguments[0].click()", ViewBtn);
        driver.findElement(By.id("training-hour")).sendKeys("3");
        driver.findElement(By.id("credit-hour")).sendKeys("3");

        //Click Save
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Offer Schedule
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Management')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();

        //Click Add offer
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[1]/button")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[1]/button")).click();

        //Click Site Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/button")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/button")).click();

        //Search SCV
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys("SCV");

        //Click SCV
        driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")).click();


        //Building
        driver.findElement(By.id("building")).sendKeys(Building);
        driver.findElement(By.id("room")).sendKeys("001");
        driver.findElement(By.id("class-size")).clear();
        driver.findElement(By.id("class-size")).sendKeys("5");

        //Click Edit Btn of time schedule
        driver.findElement(By.xpath("//*[@id=\"classTimeTable\"]/tbody/tr/td[6]/div/button[2]")).click();
        Thread.sleep(1000);
        //Click the left calendar
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div[8]/table/tbody/tr/td[1]/div/div/button")).click();
        Thread.sleep(1000);
        WebElement CalendarBox02=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div[8]/table/tbody/tr/td[1]/div/div/div/div/div/div[2]"));
        List<WebElement> list2 = CalendarBox02.findElements(By.tagName("span"));
        //Reverse the list to find the end day in case there are two todays or two yesterdays
        Collections.reverse(list2);
        Calendar calendar1 = new GregorianCalendar();
        int DateofToday1= calendar1.get(Calendar.DAY_OF_MONTH);
        System.out.println("DateofToday:"+DateofToday1);

        String TodayOnly2= String.valueOf(DateofToday1);
        System.out.println("TodayOnly:"+TodayOnly2);
        /*
        calendar.add(Calendar.DATE,1);
        Date date= calendar.getTime();
        String FullDateofToday= date.toString();
        String DateofToday=FullDateofToday.substring(FullDateofToday.length()-2);
        System.out.println("DateofToday:"+DateofToday);
        String TodayOnly1= DateofToday;
        System.out.println("TodayOnly1:"+TodayOnly1);

         */

        if (TodayOnly2.equals("01")){
            TodayOnly2="1";
        }
        if (TodayOnly2.equals("02")){
            TodayOnly2="2";
        }
        if (TodayOnly2.equals("03")){
            TodayOnly2="3";
        }
        if (TodayOnly2.equals("04")){
            TodayOnly2="4";
        }
        if (TodayOnly2.equals("05")){
            TodayOnly2="5";
        }
        if (TodayOnly2.equals("06")){
            TodayOnly2="6";
        }
        if (TodayOnly2.equals("07")){
            TodayOnly2="7";
        }
        if (TodayOnly2.equals("08")){
            TodayOnly2="8";
        }
        if (TodayOnly2.equals("09")){
            TodayOnly2="9";
        }
        String Number2= TodayOnly2;
        System.out.println(Number2);


        for(WebElement e : list2) {
            String dateofcanlendar2 = e.getAttribute("textContent");

            System.out.println(dateofcanlendar2);

            if (dateofcanlendar2.equals(Number2)) {
                System.out.println("Object Found Yeah Yeah Yeah");
                js.executeScript("arguments[0].click();", e);
                break;
            }
            else{System.out.println("Object Not Found ");
            }
        }
        Thread.sleep(1000);

        // Calendar&Time//click save
        driver.findElement(By.xpath(" //*[@id=\"classTimeTable\"]/tbody/tr/td[6]/div/button[1]")).click();
        Thread.sleep(1000);
        //Click the Save Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();
        Thread.sleep(2500);



        //Click Offer Schedule
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();
        //Click Add attendee
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        WebElement AddAttendee=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[4]/div/button[3]"));
        js.executeScript("arguments[0].click()", AddAttendee);
        //CLick Attendee Tab
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[1]/ul/li[2]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[1]/ul/li[2]/a")).click();

        //Click Add attendee btn
        Thread.sleep(1000);
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[1]/ul/li[2]/a")));
        WebElement AttendeeBtn=driver.findElement(By.xpath("//a[contains(text(),'Add Attendee')]"));
        js.executeScript("arguments[0].click()", AttendeeBtn);
        //Send in Badge No
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("criteriaBadge")));
        Thread.sleep(1000);
        driver.findElement(By.id("criteriaBadge")).sendKeys(username);

        //Click Search
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();

        //Click Result
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[1]/div/div/div/form/div/div[2]/table/tbody/tr/td[1]")));
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div/form/div/div[2]/table/tbody/tr/td[1]")).click();


        //CLick Select User
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[2]/table/thead/tr/th[1]/input")).click();

        //Mark as No Show
        Thread.sleep(1000);
        new Select(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[1]/div[2]/select"))).
                selectByVisibleText("No Show");

        Thread.sleep(3000);
        String noshow = "";
        try {
            Thread.sleep(4000);
             noshow = driver.findElement(By.xpath("//td[contains(text(),'No Show')]")).getText();
        } catch (IndexOutOfBoundsException e) {
        }
        if(!noshow.equals("No Show")) {
            Assert.fail("user is not marked as No Show");
        }


        //Mark as Not Passed
        Thread.sleep(1000);
        new Select(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[1]/div[2]/select"))).
                selectByVisibleText("Not Passed");


        Thread.sleep(3000);
        String notPassed = "";
        try {
            notPassed = driver.findElement(By.xpath("//td[contains(text(),'Not Passed')]")).getText();
        } catch (IndexOutOfBoundsException e) {

        }
        if(!notPassed.equals("Not Passed")) {
            Assert.fail("user is not marked as Not Passed");
        }


        //Mark as completed
        Thread.sleep(1000);
        new Select(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[1]/div[2]/select"))).
                selectByVisibleText("Completed");
        String complete = "";
        try {
            complete = driver.findElement(By.xpath("//td[contains(text(),'Completed')]")).getText();
            Thread.sleep(1500);
        } catch (IndexOutOfBoundsException e) {

        }

        if (!complete.equals("Completed")) {
            Assert.fail("user is not marked as completed");
        }
        Thread.sleep(1500);


        driver.findElement(By.partialLinkText("My History")).click();
        Thread.sleep(3500);

        JavascriptExecutor js1 = (JavascriptExecutor)driver;

        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);
        Thread.sleep(3000);
        if (!driver.getPageSource().contains(courseId)) {
            Assert.fail("the course did not show up as completed in my history");
        }


        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Reports')]"));
        js.executeScript("arguments[0].click()", Admin1);
        Thread.sleep(1500);
        WebElement MytrainingReport=driver.findElement(By.xpath("//a[contains(text(),'My Training Report')]"));
        js.executeScript("arguments[0].click();", MytrainingReport);

        Thread.sleep(1500);
        new Select(driver.findElement(By.xpath("//*[@id=\"main\"]/div[3]/div/div[1]/div/select"))).selectByVisibleText("Classroom");


        //Click the left calendar
        driver.findElement(By.xpath("//div[@class='input-group']//div[1]//button[1]")).click();
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
        driver.findElement(By.xpath("//div[@class='form-group row']//div[2]//button[1]")).click();
        Thread.sleep(1000);
        WebElement CalendarBox3=driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div/div[2]"));
        List<WebElement> list3 = CalendarBox3.findElements(By.tagName("span"));
        //Reverse the list to find the end day in case there are two todays or two yesterdays
        Collections.reverse(list3);
        for(WebElement e : list3) {
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
        // Click Go Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary mr-1']")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='btn btn-primary mr-1']")).click();

        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();


        try {
            WebElement Last_Page = driver.findElement(By.xpath("//a[@class='page-link last icon']"));
            if (Last_Page.isDisplayed()) {
                JavascriptExecutor js3 = (JavascriptExecutor)driver;
                js3.executeScript("arguments[0].click();", Last_Page);
            }
        }
        catch(Exception e){
            System.out.println("There is no last page element");}

        if (!driver.getPageSource().contains(courseId)) {
            Assert.fail("the course does not show as completed in my training report");
        }
        Thread.sleep(2000);



        driver.quit();

    }
}