package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.text.*;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class NoShowNotPassedCompleted {
    public void NoShowNotPassedCompleted() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir") + "/PasswordFileEHS.properties");
        FileInputStream inStream = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);


        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click()", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search_result\"]/div/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(By.name("detailCourseTitle")));
        Thread.sleep(1000);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal & Refresh");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Save']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='addClass']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='addClass']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("site_radio")));
        Thread.sleep(1000);
        driver.findElement(By.id("site_radio")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("selectBtnSite")));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnSite")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("searchName")));
        Thread.sleep(1000);
        driver.findElement(By.id("searchName")).sendKeys("SCV");
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(1500);
        String building = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building);
        driver.findElement(By.name("detailClassRoom")).sendKeys("room01");
        driver.findElement(By.name("detailClassMaxSize")).sendKeys("5");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd")).click();
        Thread.sleep(1500);
        Calendar calendar = new GregorianCalendar();
        int month = calendar.get(Calendar.MONTH);
        String smonth = "";
        String cmonth = "";

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        day --;
        String sday = "";
        if(day < 10) {
            sday = "0" + day;
        } else { sday = "" + day; }
        if (day == 0) {
            sday = "28";
        }

        int year = calendar.get(Calendar.YEAR);
//        System.out.println(month);
        switch (month) {
            case 0: smonth = "Jan ";break;
            case 1: smonth = "Feb ";break;
            case 2: smonth = "Mar ";break;
            case 3: smonth = "Apr ";break;
            case 4: smonth = "May ";break;
            case 5: smonth = "Jun ";break;
            case 6: smonth = "Jul ";break;
            case 7: smonth = "Aug ";break;
            case 8: smonth = "Sep ";break;
            case 9: smonth = "Oct ";break;
            case 10: smonth = "Nov ";break;
            case 11: smonth = "Dec ";break;
        }

        month ++;
        if(month < 10) {
            cmonth = "0" + month;
        } else { cmonth = "" + month; }


        js.executeScript("document.getElementById('TimeAdd_datepicker').value='" + smonth + sday + "," + year + "'");
        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("06");
        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("45");
        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("13");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("TimeAdd_Save")));
        Thread.sleep(1000);
        driver.findElement(By.id("TimeAdd_Save")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveClassCourse")));
        Thread.sleep(1000);
        driver.findElement(By.id("saveClassCourse")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"FirstForm\"]/div[4]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"FirstForm\"]/div[4]/a")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"crseRecord\"]/tbody/tr/td[7]/a[1]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"crseRecord\"]/tbody/tr/td[7]/a[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("selectTeamMemberButton1")));
        Thread.sleep(1000);
        driver.findElement(By.id("selectTeamMemberButton1")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("badgeNo")));
        Thread.sleep(1000);
        driver.findElement(By.name("badgeNo")).sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][value='Search']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"directreport\"]/tbody/tr/td[1]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"directreport\"]/tbody/tr/td[1]/a")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("emailme")));
        Thread.sleep(2000);
        driver.findElement(By.name("emailme")).click();

        Thread.sleep(3000);
        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark No Show");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='OK']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(3500);

        WebElement lilok = driver.findElement(By.cssSelector("input[type='button'][value='ok']"));
        js.executeScript("arguments[0].click();", lilok);
        Thread.sleep(3000);
        String noshow = "";
        try {
            Thread.sleep(4000);
            noshow = driver.findElement(By.xpath("//td[contains(text(),'No Show')]")).getAttribute("innerHTML").substring(45,52);
        } catch (IndexOutOfBoundsException e) {
        }
        if(!noshow.equals("No Show")) {
            Assert.fail("user is not marked as No Show");
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.name("emailme")));
        Thread.sleep(2000);
        driver.findElement(By.name("emailme")).click();
        Thread.sleep(3500);
        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark Not Passed");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='OK']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(3500);
        WebElement lilok0 = driver.findElement(By.cssSelector("input[type='button'][value='ok']"));
        js.executeScript("arguments[0].click();", lilok0);
        Thread.sleep(4000);
        String notPassed = "";
        try {
            Thread.sleep(3000);
            notPassed = driver.findElement(By.xpath("//td[contains(text(),'Not Passed')]")).getAttribute("innerHTML").substring(45,55);
        } catch (IndexOutOfBoundsException e) {

        }
        if(!notPassed.equals("Not Passed")) {
            Assert.fail("user is not marked as Not Passed");
        }
        Thread.sleep(3500);

        wait.until(ExpectedConditions.elementToBeClickable(By.name("emailme")));
        Thread.sleep(2000);
        driver.findElement(By.name("emailme")).click();
        Thread.sleep(3500);
        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark Completed");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='OK']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(3500);
        WebElement lilok1 = driver.findElement(By.cssSelector("input[type='button'][value='ok']"));
        Thread.sleep(3500);
        js.executeScript("arguments[0].click();", lilok1);
        Thread.sleep(2000);
        String complete = "";
        try {
            Thread.sleep(3000);
            complete = driver.findElement(By.xpath("//td[contains(text(),'yes')]")).getAttribute("innerHTML").substring(45,48);
            Thread.sleep(1500);
        } catch (IndexOutOfBoundsException e) {

        }

        if (!complete.equals("yes")) {
            Assert.fail("user is not marked as completed");
        }
        Thread.sleep(3500);


        driver.findElement(By.partialLinkText("My History")).click();
        Thread.sleep(3500);

        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"my-courses\"]/div/div/div[1]/div/div[3]/div[2]/div[2]/div/span"));
        SimpleDateFormat ShowTodayOnlyFormat = new SimpleDateFormat("dd");

        int DateofToday= calendar.get(Calendar.DAY_OF_MONTH);

        String TodayOnly= ShowTodayOnlyFormat.format(DateofToday);
        if (TodayOnly.equals("01")){
            TodayOnly="1";
        }
        if (TodayOnly.equals("02")){
            TodayOnly="2";
        }
        if (TodayOnly.equals("03")){
            TodayOnly="3";
        }
        if (TodayOnly.equals("04")){
            TodayOnly="4";
        }
        if (TodayOnly.equals("05")){
            TodayOnly="5";
        }
        if (TodayOnly.equals("06")){
            TodayOnly="6";
        }
        if (TodayOnly.equals("07")){
            TodayOnly="7";
        }
        if (TodayOnly.equals("08")){
            TodayOnly="8";
        }
        if (TodayOnly.equals("09")){
            TodayOnly="9";
        }
        System.out.println("TodayOnly is"+TodayOnly);


        String Number= TodayOnly;

        for(WebElement e : list) {
            String dateofcanlendar = e.getAttribute("textContent");

            System.out.println(dateofcanlendar);

                  if (dateofcanlendar.equals(Number)) {
               System.out.println("Object Found Yeah Yeah Yeah");
                js1.executeScript("arguments[0].click();", e);
                break;
            }
            else{System.out.println("Object Not Found ");
            }
        }
        //Click the last page button
        Thread.sleep(1500);
        try{  WebElement Last_Page=driver.findElement(By.xpath("//*[@id=\"my-courses\"]/div/div/div[2]/div[2]/nav/ul/li[5]/a"));
            if(Last_Page.isDisplayed()){
                Last_Page.click();
            }
        }
        catch(Exception e){
            System.out.println("There is no last page element");}

        Thread.sleep(3000);
        if (!driver.getPageSource().contains(courseId)) {
            Assert.fail("the course did not show up as completed in my history");
        }



        WebElement MytrainingReport=driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[6]/div/a[1]"));
        js.executeScript("arguments[0].click();", MytrainingReport);

        Thread.sleep(1500);
        new Select(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/select"))).selectByVisibleText("Classroom");


        List<WebElement> list1 = driver.findElements(By.xpath("//*[@id=\"main\"]/div[2]/div/div[2]/div/div/div[1]/div[2]/div/span"));


        String TodayOnly1= ShowTodayOnlyFormat.format(DateofToday);

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
        // Click Go Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/div[2]/div/div[3]/div/button[1]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[3]/div/button[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button[2]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button[2]")).click();

        try{  WebElement Last_Page=driver.findElement(By.xpath("//*[@id=\"my-courses\"]/div/div/div[2]/div[2]/nav/ul/li[5]/a"));
            if(Last_Page.isDisplayed()){
                Last_Page.click();
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