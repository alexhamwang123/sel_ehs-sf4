package com.OnlineCourseManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.List;
import java.util.*;
import java.text.*;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



//@Test
@Test(priority=45)
public class OnlineScormVerify {
    public void CreateScormClass() throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

//        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[8]/ul/li[3]/a"));
        js.executeScript("arguments[0].click()", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(4500);

        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Survey_Only_New");//We have to make it via manually, just in case.
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        Thread.sleep(600);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(4500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(2000);
        String courseTitle = generator.generate(10);
        driver.findElement(By.name("detailCourseTitle")).sendKeys(courseTitle);
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(1000);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
        String path = System.getProperty("user.dir") + "/Intro_OneDrive.zip";
      //  System.out.println("path="+path);
        StringSelection str = new StringSelection(path);
        clipboard.setContents(str, str);

//PLEASE Choice scorm type  and file_Scrom_Upload.
        driver.findElement(By.cssSelector("input[type='radio'][value='scorm']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(path);
        Thread.sleep(1000);

        Thread.sleep(1500);
        driver.findElement(By.id("btn_Scorm_UploadFile")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1700);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(3500);
        driver.findElement(By.name("langIsViewable")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("detailCourseIsActive")).click();
        Thread.sleep(1700);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("srch_fld")).sendKeys(courseId);


        driver.findElement(By.name("searchButton")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        String currentWin = driver.getWindowHandle();
        Thread.sleep(1500);
        try {
            driver.findElement(By.className("onelang")).click();
        } catch (NoSuchElementException e) {
            Assert.fail("something went wrong while creating and uploading the scorm course");
        }
        Thread.sleep(1500);

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        Thread.sleep(500);
        driver.findElement(By.className("crselink1")).click();
        Thread.sleep(1500);
//        robot.keyPress(KeyEvent.VK_META);
//        robot.keyPress(KeyEvent.VK_W);
//        robot.keyRelease(KeyEvent.VK_META);
//        robot.keyRelease(KeyEvent.VK_W);
//        Thread.sleep(2500);
        //we have to finish the course first, then complete it for scorm.
        //first one
        driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
        Thread.sleep(1500);
        //second one
        driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //time in second
        Thread.sleep(2000);

        String innerText=driver.findElement(By.xpath("//*[@id=\"control-next\"]/div")).getText();
        System.out.println("innerText=" + innerText);
        if(innerText.equals("NEXT")){
        driver.findElement(By.xpath("//*[@id=\"control-next\"]/a")).click();
        }

        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@id=\"control-next\"]/a")).click();
        Thread.sleep(1500);
        driver.switchTo().defaultContent();
        //gThread.sleep(1000);
        //first one
        driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
        //driver.switchTo().defaultContent();
        Thread.sleep(1500);
        //second one
        driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //time in second
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"item_6RV08inxbAS\"]/div/div[1]/div")).click();//.selectByVisibleText("True");
        //Then submit
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        //first one
        driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
        //driver.switchTo().defaul
        //
        // tContent();
        Thread.sleep(1500);
        //second one
        driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
        Thread.sleep(1000);
        //submit first one
        String submitText=driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]/div")).getText();
        System.out.println("SubmitText=" + submitText);
        if(submitText.equals("SUBMIT")){
            WebElement targetDiv = driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]"));
            JavascriptExecutor executor= (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", targetDiv);
        }
        else {
            Assert.fail("Should not be submitted here line 192");
        }
        Thread.sleep(1000);
        String continueDivText = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_63vTZMCZlM1 textlib\"]/canvas[@class=\"content\"]")).getText();
        System.out.println("continueDivText=" + continueDivText);
        if(continueDivText.equals("")){
            //Then submit
            Thread.sleep(600);
            Actions builder = new Actions(driver);
            WebElement continueDivEle = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_63vTZMCZlM1 textlib\"]/canvas[@class=\"content\"]"));
            Action drawAction = builder.moveToElement(continueDivEle,136/2,34/2)  // start point
                    .click()
                    .build();
            drawAction.perform();
            System.out.println("continueDivText=" + continueDivText);
      }
        else {
            Assert.fail("Should not be submitted here line 209");
        }
        //Seocnd Questions
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
        Thread.sleep(600);
        //first one
        driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
        Thread.sleep(700);
        driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
        Thread.sleep(800);
        //Let's Choice the Microsoft
        String secondQuestionElementDivText =driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_5dkZK7ONPxW textlib\"]/canvas[@class=\"content\"]")).getText();
        System.out.println("secondQuestionElementDivText=" + secondQuestionElementDivText);
        if(secondQuestionElementDivText.equals("")){
            //Then submit
            Thread.sleep(500);
            Actions builder = new Actions(driver);
            WebElement secondQuestionElementDiv = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_5dkZK7ONPxW textlib\"]/canvas[@class=\"content\"]"));
            Action drawAction = builder.moveToElement(secondQuestionElementDiv,689/2,58/2)  // start point
                    .click()
                    .build();
            drawAction.perform();
            System.out.println("secondQuestionElementDivText=" + secondQuestionElementDivText);
        }
        else {
            Assert.fail("Should not be submitted here line 235");
        }
        Thread.sleep(500);
        driver.switchTo().defaultContent();
        Thread.sleep(600);
        //first one
        driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
        Thread.sleep(700);
        //second one
        driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
        Thread.sleep(800);
        //first one
        String submitText2=driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]/div")).getText();
        System.out.println("submitText2=" + submitText2);
        if(submitText2.equals("SUBMIT")){
            WebElement targetDiv2 = driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]"));
            JavascriptExecutor executor2= (JavascriptExecutor)driver;
            executor2.executeScript("arguments[0].click();", targetDiv2);
        }
        else {
            Assert.fail("Should not be submitted here line 255");
        }
        Thread.sleep(1000);
        String continueDivText2 = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6gytkCQVSz7 textlib\"]/canvas[@class=\"content\"]")).getText();
        System.out.println("continueDivText2=" + continueDivText2);
        if(continueDivText2.equals("")){
            Thread.sleep(600);
            Actions builder2 = new Actions(driver);
            WebElement continueDivEle2 = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6gytkCQVSz7 textlib\"]/canvas[@class=\"content\"]"));
            Action drawAction2 = builder2.moveToElement(continueDivEle2,136/2,34/2)  // start point
                    .click()
                    .build();
            drawAction2.perform();
            System.out.println("continueDivText2=" + continueDivText2);
        }
        else {
            Assert.fail("Should not be submitted here line 271");
        }
        //Third Questions
        Thread.sleep(500);
        driver.switchTo().defaultContent();
        Thread.sleep(600);
        driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
        Thread.sleep(700);
        driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
        Thread.sleep(800);
        //Let's Choice the THree. Videos and Docuemnts and Photos
        String thirdQuestionElementDivVideosText =driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6SbbAbd8vb3 textlib\"]/canvas[@class=\"content\"]")).getText();
        String thirdQuestionElementDivDocuemntsText =driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6l8QiU7zo26 textlib\"]/canvas[@class=\"content\"]")).getText();
        String thirdQuestionElementDivPhotosText =driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_5dfOOSV7gny textlib\"]/canvas[@class=\"content\"]")).getText();

        System.out.println("thirdQuestionElementDivVideosText=" + thirdQuestionElementDivVideosText);
        System.out.println("thirdQuestionElementDivDocuemntsText=" + thirdQuestionElementDivDocuemntsText);
        System.out.println("thirdQuestionElementDivPhotosText=" + thirdQuestionElementDivPhotosText);
        if(thirdQuestionElementDivVideosText.equals("") && thirdQuestionElementDivVideosText.equals("") && thirdQuestionElementDivVideosText.equals("")){
            //Then submit
            Thread.sleep(500);
            Actions builderVedio = new Actions(driver);
            Actions builderDocuemnt = new Actions(driver);
            Actions builderPhoto = new Actions(driver);
            WebElement thirdQuestionElementDivVideo = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6SbbAbd8vb3 textlib\"]/canvas[@class=\"content\"]"));
            WebElement thirdQuestionElementDivDocuemnt = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6l8QiU7zo26 textlib\"]/canvas[@class=\"content\"]"));
            WebElement thirdQuestionElementDivPhoto = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_5dfOOSV7gny textlib\"]/canvas[@class=\"content\"]"));
            Action drawActionVideo = builderVedio.moveToElement(thirdQuestionElementDivVideo,649/2,45/2)  // start point
                    .click()
                    .build();
            drawActionVideo.perform();
            Action drawActionDocuemnt = builderDocuemnt.moveToElement(thirdQuestionElementDivDocuemnt,649/2,45/2)  // start point
                    .click()
                    .build();
            drawActionDocuemnt.perform();
            Action drawActionPhoto = builderPhoto.moveToElement(thirdQuestionElementDivPhoto,649/2,45/2)  // start point
                    .click()
                    .build();
            drawActionPhoto.perform();
            System.out.println("thirdQuestionElementDivVideosText=" + thirdQuestionElementDivVideosText);
            System.out.println("thirdQuestionElementDivDocuemntsText=" + thirdQuestionElementDivDocuemntsText);
            System.out.println("thirdQuestionElementDivPhotosText=" + thirdQuestionElementDivPhotosText);

        }
        else {
            Assert.fail("Should not be submitted here line 316");
        }
        Thread.sleep(500);
        driver.switchTo().defaultContent();
        Thread.sleep(600);
        driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
        Thread.sleep(700);
        driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
        Thread.sleep(800);
        String submitText3=driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]/div")).getText();
        System.out.println("SubmitText3=" + submitText3);
        if(submitText3.equals("SUBMIT")){
            WebElement targetDiv3 = driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]"));
            JavascriptExecutor executor3= (JavascriptExecutor)driver;
            executor3.executeScript("arguments[0].click();", targetDiv3);
        }
        else {
            Assert.fail("Should not be submitted here line 333");
        }
        Thread.sleep(1000);
        String continueDivText3 = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_5z66WgZXo0y textlib\"]/canvas[@class=\"content\"]")).getText();
        System.out.println("continueDivText3=" + continueDivText3);
        if(continueDivText3.equals("")){
            Thread.sleep(600);
            Actions builder3 = new Actions(driver);
            WebElement continueDivEle3 = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_5z66WgZXo0y textlib\"]/canvas[@class=\"content\"]"));
            Action drawAction3 = builder3.moveToElement(continueDivEle3,136/2,34/2)  // start point
                    .click()
                    .build();
            drawAction3.perform();
            System.out.println("continueDivText3=" + continueDivText3);
        }
        else {
            Assert.fail("Should not be submitted here line 349");
        }
        //Submit Results.item image vectorshape item_6JIGnuBEjkK textlib
        Thread.sleep(1500);
        String submitResultsStr = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6JIGnuBEjkK textlib\"]/canvas[@class=\"content\"]")).getText();
        System.out.println("submitResultsStr=" + submitResultsStr);
        String mainWin = driver.getWindowHandle();
        if(continueDivText2.equals("")){
            //Then submit
            Thread.sleep(600);
            Actions builderSR = new Actions(driver);
            WebElement submitResultsEle = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6JIGnuBEjkK textlib\"]/canvas[@class=\"content\"]"));
            Action drawActionSR = builderSR.moveToElement(submitResultsEle,179/2,51/2)  // start point
                    .click()
                    .build();
            drawActionSR.perform();
            System.out.println("submitResultsStr=" + submitResultsStr);
        } else {
            Assert.fail("Should not be submitted here line 367");
        }
        // Click on All Classes tab
        // find a SCORM course and click play
        // complete the course
        // back to the All Classes tab and search for this course
        // see if Status is now showing Completed
        // also, go to the My Training Report to see if the system successfully record the completion in the report.
        // courseId  My Training Report
        Thread.sleep(1000);
//        WebDriver driver2 = new ChromeDriver();
//        driver2.switchTo().defaultContent();
        Thread.sleep(1500);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            break;
        }
        Thread.sleep(800);
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        WebElement myTrainingReport = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[1]/a"));
        js2.executeScript("arguments[0].click()", myTrainingReport);
        Thread.sleep(1000);
        new Select(driver.findElement(By.name("selectedCourseType"))).selectByVisibleText("Online");
        Thread.sleep(1600);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
//        System.out.println("Local Date Now=" + dateFormat.format(date));
        js.executeScript("document.getElementById('dateFrom').value='" + dateFormat.format(date)+ "'");
        Thread.sleep(500);
        js.executeScript("document.getElementById('dateTo').value='" + dateFormat.format(date) + "'");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("button[type='submit'][value='Go']")).click();//Go
        Thread.sleep(1200);
        String courseIdTxt="";
        String currentPageMax = "0";
        String currentPageNo = "0";
        Boolean isBreak =false;
        restartLoop:
        while (true) {
            java.util.List<WebElement> myResults, myResults3a;
            myResults = driver.findElements(By.xpath("//*[@id=\"userRecord\"]/tbody/tr"));
            if (myResults.size() > 0) {
                for (int $p = 0; $p < myResults.size(); $p++) {
                    WebElement myResult;
                    myResult = (WebElement) myResults.get($p);
                    myResults3a = driver.findElements(By.tagName("td"));
                    if (myResults3a.size() > 0) {
                        for (WebElement myResult3a : myResults3a) {
                            String idstr3a = myResult3a.getText();
//                            System.out.println("didstr3a=" + idstr3a);
                            if (courseId.equals(idstr3a)) {
                                isBreak=true;
                                break;//We dont need to do anything.
                                //Maybe find it. So that we don't need to do anything at all.
                            }
                        }
                        restartLoop2:
                        while (true) {

                            try {
                                    WebElement pageMaxEle = driver.findElement(By.id("pageMax"));
                                    currentPageMax = pageMaxEle.getAttribute("value");
                                    WebElement pageNo = driver.findElement(By.id("pageNo"));
                                    currentPageNo = pageNo.getAttribute("value");
                                    currentPageNo = String.valueOf(Integer.valueOf(currentPageNo));
                                    Integer currentPageNoInt = Integer.valueOf(currentPageNo) + 1;
                                System.out.println("Before taking the isBreak yet, isBreak=" + isBreak);
                                    System.out.println("Before taking the currentPageNoInt yet, currentPageNoInt=" + currentPageNoInt);
                                    System.out.println("Before taking the currentPageMax yet, currentPageMax=" + currentPageMax);
                                    if (Integer.valueOf(currentPageMax) >= currentPageNoInt && isBreak) {
                                        //we have to go to next.
                                        WebElement inputTHREE = driver.findElement(By.id("pageNo"));
                                        // erase any existing value (because clear does not send any events
                                        for (int i = 0; i < inputTHREE.getAttribute("value").length(); i++) {
                                            inputTHREE.sendKeys(Keys.BACK_SPACE);
                                        }
                                        inputTHREE.clear();
                                        inputTHREE.sendKeys(String.valueOf(currentPageNoInt));
                                        //We have to next.
                                        String strPagination = "pagination";
                                        driver.findElement(By.xpath("//ul[@class='" + strPagination + "']/li[4]/a")).click();
                                        continue restartLoop2;
                                    }
                                    else {
                                        break;
                                    }
//                                    System.out.println("After taking the currentPageNoInt yet, currentPageNoInt=" + currentPageNoInt);
//                                    System.out.println("After taking the course id yet, currentPageMax=" + currentPageMax);
//                                    continue restartLoop2;
                                    // break;

                            } catch (NoSuchElementException e) {
                                break;
                            }
                        }
                        if (isBreak){
                            break;
                        }
                    }
                }
            }
            if (isBreak){
                break;
            }
//            System.out.println("Before taking the course id yet, courseIdTxt=" + courseIdTxt);
//            System.out.println("Before taking the course id yet, courseId=" + courseId);
            if (courseIdTxt.equals(courseId)) {
                System.out.println("courseIdTxt=" + courseIdTxt);
                System.out.println("courseId=" + courseId);
                System.out.println("Ｗe find the same course id here");
                break;
            } else {
                Assert.fail("Should not be submitted here line 486");
            }
        }
        Thread.sleep(3000);
//        driver.quit();
    }
}
