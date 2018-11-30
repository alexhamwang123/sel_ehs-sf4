package com.OnlineCourseManagement;
        import org.apache.commons.text.RandomStringGenerator;
        import org.openqa.selenium.*;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.ui.Select;
        import org.testng.Assert;
        import org.testng.annotations.Test;

        import java.awt.*;
        import java.awt.datatransfer.StringSelection;
        import java.awt.event.KeyEvent;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.util.List;
//        import java.util.ArrayList;
        import java.util.Hashtable;

//        import java.util.List;
        import java.util.Enumeration;

        import java.security.Key;
        import java.util.Properties;
        import java.util.concurrent.TimeUnit;

        import static org.apache.commons.text.CharacterPredicates.DIGITS;
        import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test(priority=46)
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
        arrL.put("EHS-11111","EHS-11111");
        int $p = 0;
        int $t=0;
        int $tu=0;
        int $r = 0;
restartLoop:
        while (true) {
            JavascriptExecutor js = (JavascriptExecutor) driver;

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
                Boolean firstTimeEntry = true;

                //myResults = driver.findElements(By.tagName("td"));
                myResults = driver.findElements(By.xpath("//table[@id=\"userRecord\"]/tbody/tr/td"));
                if (myResults.size() > 0) {
                    for (;$p < myResults.size(); $p++) {
                        if (isBreak) break;
                        if ($p >= (myResults.size()-1) ) {
                            $p=0;

                            break;
                        };
                        WebElement myResult4;
                        myResult4 = (WebElement) myResults.get($p);
                        String idstr2 = myResult4.getText();
                        //It must be Survey_Only_New

                        if ("Active".equals(idstr2)) {
                            //we have to  make one to be inactived at least if it mets.
                            int $k = 0;
                            $k = $p;
//                            if (doWeHaveToJump)
//                                $p = $p;
//                            else
                                $p = $p - 3;

                            WebElement myResult5;
                            myResult5 = (WebElement) myResults.get($p);
                            String idstr5 = myResult5.getText();
                            java.util.List<WebElement> myResults3a;
//                            myResults3a = driver.findElements(By.tagName("a"));
                            myResults3a = driver.findElements(By.xpath("//table[@id=\"userRecord\"]/tbody/tr/td/a"));
                            int $j = 0;
                            $p = $k;
                            if (myResults3a.size() > 0) {
//                                for (WebElement myResult3a : myResults3a) {
                                    for (; $tu<myResults3a.size(); $tu++){
                                        if ($tu == 0) $tu=1;
                                        WebElement myResult3a = myResults3a.get($tu);

                                    String idstr3a = myResult3a.getText();
                                    String id3a = myResult3a.getAttribute("id");



                                    if (idstr5.equals(idstr3a) || (firstTimeEntry && arrL.size() == 2  && ("Active".equals(idstr2) && !idstr5.equals(idstr3a))  ) ) { // Line is 198 !idstr3a.equals("EHS-11111") ) {
                                        //for (int s = 0; s <  arrL.size(); s++) {
                                            for (Enumeration<String> e = arrL.elements(); e.hasMoreElements();) {
//
                                                //we do not know this record yet. Please do it now.

                                                String idstr3aOri = e.nextElement();

                                                if (!idstr3a.equals(idstr3aOri) && $t <= arrL.size()) {//Let do it now.
                                                    if (idstr3a.equals(idstr3aOri))continue;
                                                    $t++;
                                                    doWeHaveToJump = true;
                                                    if (firstTimeEntry ) {
                                                        driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
                                                        Thread.sleep(4500);
                                                        driver.findElement(By.name("detailCourseIsActive")).click();
                                                        Thread.sleep(1500);
                                                        driver.findElement(By.name("langIsViewable")).click();
                                                        Thread.sleep(1800);
                                                        driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
                                                        Thread.sleep(1700);
                                                        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
                                                        Thread.sleep(2000);
                                                        firstTimeEntry = false;
                                                        try {
                                                            $r++;
//                                                            $p = ($p - 3)*2*arrL.size();
                                                            $p = $p*arrL.size();
//                                                            $p = $p*$r++;
                                                            String stroutput= String.valueOf(Integer.valueOf($p));
                                                            arrL.put(idstr3a,idstr3a);
                                                            String returnMSG = driver.findElement(By.xpath("//label[@class=\"err_msg\"]")).getAttribute("innerHTML");
                                                            if (!returnMSG.equals("This course may not be made inactive or invisible because it is included in a RC.")) {
                                                                $r++;
                                                                 //restartLoop;
                                                                // Assert.fail("the online course does not show up as Record saved successfully.");
                                                            }//Record saved successfully.

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
//                                                    if (idstr5.equals(idstr3a))//OKOK
//                                                    {
//                                                    }
                                                    if (!idstr3a.equals(idstr3aOri))
                                                        break;
                                                    if(arrL.size()==1 ) {
                                                        firstTimeEntry = true;
                                                    } else {
                                                        firstTimeEntry = false;
                                                    }
                                                        driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
                                                        Thread.sleep(4500);
                                                        driver.findElement(By.name("detailCourseIsActive")).click();
                                                        Thread.sleep(1500);
                                                        driver.findElement(By.name("langIsViewable")).click();
                                                        Thread.sleep(1900);
                                                        driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
                                                        Thread.sleep(1500);
                                                        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
                                                        Thread.sleep(2000);
                                                        firstTimeEntry = false;
                                                        try {
                                                            $r++;
                                                            $p = $p * arrL.size();
                                                            if ($p >= (myResults.size()-1) ) {
                                                                $p=0;
                                                            }
                                                            String stroutput = String.valueOf(Integer.valueOf($p));
                                                            arrL.put(stroutput, idstr3a);
                                                            String returnMSG = driver.findElement(By.xpath("//label[@class=\"err_msg\"]")).getAttribute("innerHTML");
                                                            if (!returnMSG.equals("This course may not be made inactive or invisible because it is included in a RC.")) {
                                                                $r++;
                                                                continue restartLoop;
                                                                // Assert.fail("the online course does not show up as Record saved successfully.");
                                                            }//Record saved successfully.

                                                        } catch (NoSuchElementException $e) {
                                                            continue restartLoop;
                                                        }


                                                        String currentWin = driver.getWindowHandle();
                                                        try {
                                                            driver.findElement(By.className("onelang")).click();
                                                        } catch (NoSuchElementException ae) {
                                                            continue restartLoop;
                                                        }
//                                                    }

                                                    //Thread.sleep(1500);

                                                    //continue restartLoop;

                                                }

                                            }




                                    }
                                    //We Have to go to next because there is no same situation
                                    else {
//
                                        if (id3a.equals("")) continue;
//
                                        if(arrL.size()==1 ) {
                                            firstTimeEntry = true;
                                        } else {
                                            firstTimeEntry = false;
                                        }

                                        driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
                                        Thread.sleep(6000);
                                        driver.findElement(By.name("detailCourseIsActive")).click();
                                        Thread.sleep(1500);
                                        driver.findElement(By.name("langIsViewable")).click();
                                        Thread.sleep(1900);
                                        driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
                                        Thread.sleep(1500);
                                        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
                                        Thread.sleep(2000);
                                        firstTimeEntry = false;

                                        try {
                                            $r++;
                                            $p = $p * arrL.size();
                                            if ($p >= 100){
                                                $p=0;
                                            }
                                            String stroutput = String.valueOf(Integer.valueOf($p));
                                            arrL.put(stroutput, idstr3a);
                                            String returnMSG = driver.findElement(By.xpath("//label[@class=\"err_msg\"]")).getAttribute("innerHTML");
                                            if (!returnMSG.equals("This course may not be made inactive or invisible because it is included in a RC.")) {

                                                continue restartLoop;

                                            }

                                        } catch (NoSuchElementException $e) {
                                            continue restartLoop;
                                        }

                                    }


                                }
                                $j++;
                            }
                            $p++;
                        } else {
                        }
                    }
                    // Nothing is active. So we have to break it out first.
                    try {
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
                        continue restartLoop2;
                    } else {
                        isPaging = true;
                    }
                    isBreak = true;
                    //we should break it again.
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

        driver.quit();

        }

}

