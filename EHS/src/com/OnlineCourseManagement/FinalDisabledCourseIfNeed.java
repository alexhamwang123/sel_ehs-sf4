package com.OnlineCourseManagement;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//        import java.util.ArrayList;
//        import java.util.List;

//@Test
@Test
public class FinalDisabledCourseIfNeed {

    public void FinalDisabledCourseIfNeed() throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

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
/*
        Robot robot = new Robot();
        robot.setAutoDelay(250);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_TAB);

//        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);
        Boolean isBreak = false;
        String currentPageMax = "0";
        String currentPageNo = "0";
        Boolean isPaging = false;
        Boolean firstTimeVisit = true;
        Boolean isPage = false;
//        String currentPageNo = "0";
//        Boolean isPaging = false;
//        Boolean firstTimeVisit = true;
//        Boolean isPage = false;

        Hashtable arrL = new Hashtable();
        //ArrayList<String> arrL = new ArrayList<String>();
//        arrL.put("EHS-11111","EHS-11111");
        Boolean $resetCounterP = false;
        arrL.put("00012345654","00012345654");
        //ArrayList<String> arrL = new ArrayList<String>();
        int $p = 0;
        int $t=0;
        int $tu=0;
        int $r = 0;
        Boolean firstTimeEntry1 = true;
        restartLoop:
        while (true) {

            if($resetCounterP) {
                $p = 0;
                $tu=0;
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;

                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
         WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));

            js.executeScript("arguments[0].click()", courseAdmin);

            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
            Thread.sleep(1500);

            restartLoop2:

            while (true) {

                WebElement inputOne = driver.findElement(By.className("form-control"));

// to set focus?
                inputOne.click();
                // erase any existing value (because clear does not send any events
                for (int i = 0; i < inputOne.getAttribute("value").length(); i++) {
                    inputOne.sendKeys(Keys.BACK_SPACE);
                }

// type in value
                inputOne.sendKeys("Course Category");


// click away to fire blur event
                driver.findElement(By.className("form-control")).click();
                Thread.sleep(1500);
                WebElement inputSec = driver.findElement(By.className("form-control"));
// to set focus?
                inputSec.click();
                for (int i = 0; i < inputSec.getAttribute("value").length(); i++) {
                    inputSec.sendKeys(Keys.BACK_SPACE);
                }
                inputSec.sendKeys(Keys.TAB);
//        Survey_Only_New
                new Select(driver.findElement(By.id("secondmenu")).findElement(By.id("searchCategory"))).selectByVisibleText("Survey_Only_New");//By.xpath("//option[@value='f9bbb962d5b1aa58b2115a7bf3b4c9a8']")));
                Thread.sleep(1500);
                driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div/div/input")).click();
                Thread.sleep(3500);

                try {
                    WebElement test = driver.findElement(By.id("pageNo"));
                    currentPageNo = test.getAttribute("value");
                    isPage = true;
                } catch (NoSuchElementException e) {
                }

                if (firstTimeVisit) {
                    firstTimeVisit = false;
                }
                else {
                    currentPageNo = String.valueOf(Integer.valueOf(currentPageNo));
                }
                java.util.List<WebElement> myResults;

                Boolean doWeHaveToJump = false;
                Boolean firstTimeEntry = false;
                //myResults = driver.findElements(By.tagName("td"));
                myResults = driver.findElements(By.xpath("//table[@id=\"userRecord\"]/tbody/tr/td"));
                if (myResults.size() > 0) {
                    for (;$p < myResults.size(); $p++) {

                        if (isBreak) break;
                        if ($p >= (myResults.size()) ) {
                            $tu=$p/5;
                            $p=$p-myResults.size();
                            break;
                        }
                        else if (($p  < 0 && $tu <= 0 )) {
                            $tu=$p/5;
                            $p=0;
                            break;
                        }
                        WebElement myResult4;
                        myResult4 = (WebElement) myResults.get($p);
                        String idstr2 = myResult4.getText();
                        //It must be Survey_Only_New

                        if ("Active".equals(idstr2)) {
                            //we have to  make one to be inactived at least if it mets.
                            int $k = 0;
                            $k = $p;
                            $p = $p - 4;

                            WebElement myResult5;
                            myResult5 = (WebElement) myResults.get($p);
                            String idstr5 = myResult5.getText();
                            java.util.List<WebElement> myResults3a;
//                            myResults3a = driver.findElements(By.tagName("a"));
                            myResults3a = driver.findElements(By.xpath("//table[@id=\"userRecord\"]/tbody/tr/td/a"));
                            int $j = 0;
                            $p = $k;
                            if (myResults3a.size() > 0) {
                                for (; $tu<myResults3a.size(); $tu++){
                                    if ($p >= 5)$tu=($p/5);
                                    else if ($p==0)
                                        $tu=0;
                                    else $tu=($p/5);
                                    if ($tu <0) $tu = 0;
                                    if($tu >= 19)$tu= 19;
                                    WebElement myResult3a = myResults3a.get($tu);

                                    String idstr3a = myResult3a.getText();
                                    String id3a = myResult3a.getAttribute("id");

                                    if (idstr5.equals(idstr3a)) {
                                        for (Enumeration<String> e = arrL.elements(); e.hasMoreElements();) {
                                            //we do not know this record yet. Please do it now.
                                            String idstr3aOri = e.nextElement();
                                            if (!idstr3a.equals(idstr3aOri) ) {//Let do it now.
                                                if (idstr3a.equals(idstr3aOri)) {
                                                    continue;
                                                }
                                                $t++;
                                                doWeHaveToJump = true;
                                                if (!idstr3a.equals(idstr3aOri)) {
                                                    driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
                                                    Thread.sleep(6500);
                                                    driver.findElement(By.name("detailIsActive")).click();
                                                    Thread.sleep(1500);
                                                    driver.findElement(By.name("langIsViewable")).click();
                                                    Thread.sleep(1800);
                                                    driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
                                                    Thread.sleep(1700);
                                                    driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
                                                    Thread.sleep(2000);
                                                    try {
                                                        $p = $p*arrL.size();
                                                        String returnMSG = driver.findElement(By.xpath("//label[@class=\"err_msg\"]")).getAttribute("innerHTML");
                                                        if (!returnMSG.equals("Record saved successfully.")) {
                                                            Assert.fail("Please ask Administrator for the detail why could be this one. Thank you.");
                                                        }
                                                    } catch (NoSuchElementException $e) {
                                                        continue restartLoop;
                                                    }
                                                    String currentWin = driver.getWindowHandle();
                                                    try {
                                                        driver.findElement(By.className("onelang")).click();
                                                    } catch (NoSuchElementException ae) {
                                                        continue restartLoop;
                                                    }

                                                    Thread.sleep(1500);

                                                }

                                            }
                                            else {
                                                //we dont need to do it now.
                                                if (!idstr3a.equals(idstr3aOri))
                                                    break;
                                                Thread.sleep(1500);
                                                driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
                                                Thread.sleep(7500);
                                                driver.findElement(By.name("detailIsActive")).click();
                                                Thread.sleep(1500);
                                                driver.findElement(By.name("langIsViewable")).click();
                                                Thread.sleep(1100);
                                                driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
                                                Thread.sleep(1950);
                                                driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
                                                Thread.sleep(2100);
                                                try {
                                                    $p = $p  * arrL.size();
                                                    if ($p >= (myResults.size()-4) ) {
                                                        $p=0;
                                                    }
                                                    String returnMSG = driver.findElement(By.xpath("//label[@class=\"err_msg\"]")).getAttribute("innerHTML");
                                                    arrL.put(idstr3a,idstr3a);
                                                    if (returnMSG.equals("This course may not be made inactive or invisible because it is included in a RC.")) {

                                                        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
                                                        Thread.sleep(2000);
                                                    }//Record saved successfully.
                                                    else if (!returnMSG.equals("This course may not be made inactive or invisible because it is included in aRC.")) {
                                                        Assert.fail("Please ask Administrator for the detail why could be this one. Thank you.");
                                                    }

                                                } catch (NoSuchElementException $e) {
                                                    $p = 99;
                                                    $resetCounterP = true;
                                                    break restartLoop2;
                                                }  catch (Exception $excep) {
                                                    continue restartLoop;
                                                }

                                                String currentWin = driver.getWindowHandle();
                                                try {
                                                    driver.findElement(By.className("onelang")).click();
                                                } catch (NoSuchElementException ae) {
                                                    continue;
                                                }
                                            }
                                        }
                                    }
                                    //We Have to go to next because there is no same situation
                                    else {
                                        if (id3a.equals("")) continue;

                                        try {
                                            if(arrL.size() > 0)
                                                $p = $p * arrL.size();
                                            else
                                                $p= $p;//Nothing is changed\
                                            if ($p >= 116){
                                                break;
                                            }
                                            String returnMSG = driver.findElement(By.xpath("//label[@class=\"err_msg\"]")).getAttribute("innerHTML");
                                            if (!returnMSG.equals("Record saved successfully.")) {
                                                continue restartLoop;
                                            }else
                                            {
                                                continue restartLoop;
                                            }

                                        } catch (NoSuchElementException $e) {
                                            break;
                                        } catch (Exception $excep) {
                                            continue restartLoop;
                                        }
                                    }
                                    //paging here
                                    // Nothing is active. So we have to break it out first.
                                    try {
                                        arrL.put(idstr3a,idstr3a);
                                        $resetCounterP = true;
                                        WebElement pageMaxEle= driver.findElement(By.id("pageMax"));
                                        currentPageMax = pageMaxEle.getAttribute("value");
                                    } catch (NoSuchElementException e) {
                                        break;
                                    }
                                    currentPageNo = String.valueOf(Integer.valueOf(currentPageNo));
                                    Integer currentPageNoInt = Integer.valueOf(currentPageNo) + 1;
                                    if (Integer.valueOf(currentPageMax) >= currentPageNoInt && isPage && (Integer.valueOf(currentPageMax) > 0) && (currentPageNoInt > 0) ) {
                                        //Let us go to next
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
                                        continue restartLoop2; //Only marked first records.
                                    } else {
                                        isPaging = true;
                                    }
                                    isBreak = true;
                                    //we should break it again.
                                    if (isBreak && isPaging) break;



                                }
                                $j++;
                            }
                            $p++;
                        } //it is active if-else.
                    }
                    // Nothing is active. So we have to break it out first.
                    try {
                        WebElement pageMaxEle= driver.findElement(By.id("pageMax"));
                        currentPageMax = pageMaxEle.getAttribute("value");
                        if($p == myResults.size()) {
                            $resetCounterP=false;
                            $p =0;
                            $tu = 0;
                            if(firstTimeEntry1) {
                                firstTimeEntry1 = false;
                                continue restartLoop2;
                            }
                        }
                    } catch (NoSuchElementException e) {
                        break;
                    }
                    currentPageNo = String.valueOf(Integer.valueOf(currentPageNo));
                    Integer currentPageNoInt = Integer.valueOf(currentPageNo) + 1;
                    if (Integer.valueOf(currentPageMax) >= currentPageNoInt && isPage && (Integer.valueOf(currentPageMax) > 0) && (currentPageNoInt > 0) ) {
                        //Let us go to next
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
                        continue;
                    } else {
                        isPaging = true;
                    }
                    isBreak = true;
//                    //we should break it again.
                    if (isBreak && isPaging) break;
                } else {
                    System.out.println("There is no record at all.");
                    break;
                }
            }
            break;
        }

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_G);

        Thread.sleep(1500);

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_V);

        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1500);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(1500);
        driver.findElement(By.id("lightbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();
        Thread.sleep(500);
*/
        driver.quit();

        }

}

