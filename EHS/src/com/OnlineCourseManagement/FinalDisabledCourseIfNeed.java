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

        import java.security.Key;
        import java.util.Properties;
        import java.util.concurrent.TimeUnit;

        import static org.apache.commons.text.CharacterPredicates.DIGITS;
        import static org.apache.commons.text.CharacterPredicates.LETTERS;

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

        Robot robot = new Robot();
        robot.setAutoDelay(250);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_TAB);

        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);
restartLoop:
        while (true) {
//            for (var i=0, j=100000, x="test"; i < 1000; i++, j--, x+= ".") {
//                if (/*some condition, want to restart the loop*/)
//                    continue restartLoop;
//            }
//            break;
//        }

            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
            js.executeScript("arguments[0].click()", courseAdmin);

            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
            Thread.sleep(1500);

            Thread.sleep(1500);// find element
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
            Thread.sleep(1500);

            java.util.List<WebElement> myResults;

            myResults = driver.findElements(By.tagName("td"));
            Boolean isBreak = false;
            int $i = 0;
            if (myResults.size() > 0) {
                System.out.println("There is one record above.");

                // this.myresultList($i,myResults,driver);
//    for (WebElement myResult : myResults) {
                Boolean pi = false;
                for (int $p = 0; $p < myResults.size(); $p++) {
                    if (isBreak) break;
                    WebElement myResult4;
                    myResult4 = (WebElement) myResults.get($p);
                    String idstr2 = myResult4.getText();
                    if ("Active".equals(idstr2)) {
//                        if (pi) break;
                        pi = true;
                        //we have to  make one to be inactived at least if it mets.
                        int $k = 0;
                        $k = $p;
                        $p = $p - 3;
                        System.out.println(idstr2);
                        System.out.println("p is " +$p);
                        WebElement myResult5;
                        myResult5 = (WebElement) myResults.get($p);
                        String idstr5 = myResult5.getText();
                        java.util.List<WebElement> myResults3a;
                        myResults3a = driver.findElements(By.tagName("a"));
                        int $j = 0;
                        $p = $k;
                        if (myResults3a.size() > 0) {
                            for (WebElement myResult3a : myResults3a) {
                                String idstr3a = myResult3a.getText();
                                String id3a = myResult3a.getAttribute("id");
                                if (idstr5.equals(idstr3a)) {
                                    // System.out.println("$ j is " + $j);

                                    driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
                                    Thread.sleep(1500);
                                    driver.findElement(By.name("detailCourseIsActive")).click();
                                    Thread.sleep(1500);
                                    driver.findElement(By.name("langIsViewable")).click();
                                    Thread.sleep(1900);
                                    driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
                                    Thread.sleep(2500);
                                    driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
                                    Thread.sleep(1500);
                                    driver.findElement(By.partialLinkText("Courses")).click();
                                    Thread.sleep(1500);
                                    driver.findElement(By.id("srch_fld")).sendKeys(idstr3a);
                                    Thread.sleep(1500);
                                    driver.findElement(By.name("searchButton")).click();
                                    Thread.sleep(1500);
                                    String currentWin = driver.getWindowHandle();
                                    try {
                                        driver.findElement(By.className("onelang")).click();
                                    } catch (NoSuchElementException e) {
                                        //isBreak = true;
                                        System.out.print("168");
                                        continue restartLoop;
//                        break ;
                                        //Assert.assertEquals(true, true);
//                           Assert.fail("something went wrong while creating and uploading the scorm course");
                                    }
                                    Thread.sleep(1500);

                                }

                            }
                            $j++;
                        }


//    System.out.println(idstr2);
//    java.util.List<WebElement> myResults3a = driver.findElements(By.tagName("a"));
//    int $j = 0;
                        $p++;
                    } else if (pi) {
                        //break it out for loop. since we don't need it anymore.
                        System.out.println("Here191");
                        isBreak=true;
                        break;

                    }
                    else {
                        //pi = true;
                    }
//                    else {
//                        //Assert.false("Should not come to here");
//                    }
                }


                //we should break it again.
                if (isBreak)break;


            } else {
                System.out.println("There is no record at all.");
                System.out.println("Here197");
                break;
            }

//            robot.keyPress(KeyEvent.VK_META);
//            robot.keyPress(KeyEvent.VK_SHIFT);
//            robot.keyPress(KeyEvent.VK_G);
//            robot.keyRelease(KeyEvent.VK_META);
//            robot.keyRelease(KeyEvent.VK_SHIFT);
//            robot.keyRelease(KeyEvent.VK_G);
//
//            Thread.sleep(1500);
//
//            robot.keyPress(KeyEvent.VK_META);
//            robot.keyPress(KeyEvent.VK_V);
//            robot.keyRelease(KeyEvent.VK_META);
//            robot.keyRelease(KeyEvent.VK_V);
//
//            Thread.sleep(1500);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//            Thread.sleep(1500);
//
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            Thread.sleep(1500);
//
//            driver.findElement(By.id("lightbox")).click();
//            Thread.sleep(1000);
//            driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
//            Thread.sleep(2000);
//            driver.findElement(By.id("login_login_id")).sendKeys(username);
//            driver.findElement(By.id("login_password")).sendKeys(password);
//
//            driver.findElement(By.name("submit")).click();
//            Thread.sleep(500);
//            driver.quit();
        }
        System.out.println("Here235");
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
        System.out.println("Here259");
        driver.findElement(By.id("lightbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();
        Thread.sleep(500);
        driver.quit();
//        driver.quit();

//        restartLoop:
//        while (true) {
//            continue restartLoop;
//        }
//#2
//        js = (JavascriptExecutor)driver;
//        courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
//        js.executeScript("arguments[0].click()", courseAdmin);
//
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
//        Thread.sleep(1500);
//
//        Thread.sleep(1500);// find element
//        inputOne = driver.findElement(By.className("form-control"));
//
//// to set focus?
//        inputOne.click();
//        // erase any existing value (because clear does not send any events
//        for (int i = 0; i < inputOne.getAttribute("value").length(); i++) {
//            inputOne.sendKeys(Keys.BACK_SPACE);
//        }
//
//// type in value
//        inputOne.sendKeys("Course Category");
//
//
//// click away to fire blur event
//        driver.findElement(By.className("form-control")).click();
//        Thread.sleep(1500);
//        inputSec = driver.findElement(By.className("form-control"));
//// to set focus?
//        inputSec.click();
//        for (int i = 0; i < inputSec.getAttribute("value").length(); i++) {
//            inputSec.sendKeys(Keys.BACK_SPACE);
//        }
//        inputSec.sendKeys(Keys.TAB);
////        Survey_Only_New
//        new Select(driver.findElement(By.id("secondmenu")).findElement(By.id("searchCategory"))).selectByVisibleText("Survey_Only_New");//By.xpath("//option[@value='f9bbb962d5b1aa58b2115a7bf3b4c9a8']")));
//        Thread.sleep(1500);
//        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div/div/input")).click();
//        Thread.sleep(1500);
//
////        java.util.List<WebElement> myResults;
//
//        myResults = driver.findElements(By.tagName("td"));
//        isBreak=false;
////        int $l = 0;
//        if (myResults.size() > 0){
//            System.out.println("There is one record above.");
//
//            // this.myresultList($l,myResults,driver);
////    for (WebElement myResult : myResults) {
//            for(int $p=0; $p<myResults.size();$p++){
//                if(isBreak) break;
//                WebElement myResult4;
//                myResult4 = (WebElement) myResults.get($p);
//                String idstr2 = myResult4.getText();
//                if("Active".equals(idstr2)) {
//                    int $k=0;
//                    $k = $p;
//                    $p = $p - 3;
//                    System.out.println(idstr2);
//                    WebElement myResult5;
//                    myResult5 = (WebElement) myResults.get($p);
//                    String idstr5 = myResult5.getText();
////                    java.util.List<WebElement> myResults3a = driver.findElements(By.tagName("a"));
//                    java.util.List<WebElement> myResults3c;
//                    myResults3c = driver.findElements(By.tagName("a"));
//                    int $j = 0;
//                    $p = $k;
//                    if (myResults3c.size() > 0) {
//                        for (WebElement myResult3a : myResults3c) {
//                            String idstr3a = myResult3a.getText();
//                            String id3a = myResult3a.getAttribute("id");
//                            if (idstr5.equals(idstr3a)) {
//                                // System.out.println("$ j is " + $j);
//
//                                driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
//                                Thread.sleep(1500);
//                                driver.findElement(By.name("detailCourseIsActive")).click();
//                                Thread.sleep(1500);
//                                driver.findElement(By.name("langIsViewable")).click();
//                                Thread.sleep(1900);
//                                driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
//                                Thread.sleep(2500);
//                                driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
//                                Thread.sleep(1500);
//                                driver.findElement(By.partialLinkText("Courses")).click();
//                                Thread.sleep(1500);
//                                driver.findElement(By.id("srch_fld")).sendKeys(idstr3a);
//                                Thread.sleep(1500);
//                                driver.findElement(By.name("searchButton")).click();
//                                Thread.sleep(1500);
//                                String currentWin = driver.getWindowHandle();
//                                try {
//                                    driver.findElement(By.className("onelang")).click();
//                                } catch (NoSuchElementException e) {
//                                    isBreak = true;
//                                    break ;
//                                }
//                                Thread.sleep(1500);
//
//                            }
//
//                        }
//                        $j++;
//                    }
//                    $p++;
//                }
//            }
//
//        }
//        else {
//            System.out.println("There is no record at all.");
//        }
//
//        robot.keyPress(KeyEvent.VK_META);
//        robot.keyPress(KeyEvent.VK_SHIFT);
//        robot.keyPress(KeyEvent.VK_G);
//        robot.keyRelease(KeyEvent.VK_META);
//        robot.keyRelease(KeyEvent.VK_SHIFT);
//        robot.keyRelease(KeyEvent.VK_G);
//
//        Thread.sleep(1500);
//
//        robot.keyPress(KeyEvent.VK_META);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_META);
//        robot.keyRelease(KeyEvent.VK_V);
//
//        Thread.sleep(1500);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        Thread.sleep(1500);
//
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//
//        Thread.sleep(1500);
//
//        driver.findElement(By.id("lightbox")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("login_login_id")).sendKeys(username);
//        driver.findElement(By.id("login_password")).sendKeys(password);
//
//        driver.findElement(By.name("submit")).click();
//        Thread.sleep(4500);
//        //#3
//        driver.findElement(By.id("lightbox")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("login_login_id")).sendKeys(username);
//        driver.findElement(By.id("login_password")).sendKeys(password);
//
//        driver.findElement(By.name("submit")).click();
//        Thread.sleep(500);
//
//        js = (JavascriptExecutor)driver;
//        courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
//        js.executeScript("arguments[0].click()", courseAdmin);
//
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
//        Thread.sleep(1500);
//
//        Thread.sleep(1500);// find element
//        inputOne = driver.findElement(By.className("form-control"));
//
//// to set focus?
//        inputOne.click();
//        // erase any existing value (because clear does not send any events
//        for (int i = 0; i < inputOne.getAttribute("value").length(); i++) {
//            inputOne.sendKeys(Keys.BACK_SPACE);
//        }
//
//// type in value
//        inputOne.sendKeys("Course Category");
//
//
//// click away to fire blur event
//        driver.findElement(By.className("form-control")).click();
//        Thread.sleep(1500);
//        inputSec = driver.findElement(By.className("form-control"));
//// to set focus?
//        inputSec.click();
//        for (int i = 0; i < inputSec.getAttribute("value").length(); i++) {
//            inputSec.sendKeys(Keys.BACK_SPACE);
//        }
//        inputSec.sendKeys(Keys.TAB);
////        Survey_Only_New
//        new Select(driver.findElement(By.id("secondmenu")).findElement(By.id("searchCategory"))).selectByVisibleText("Survey_Only_New");//By.xpath("//option[@value='f9bbb962d5b1aa58b2115a7bf3b4c9a8']")));
//        Thread.sleep(1500);
//        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div/div/input")).click();
//        Thread.sleep(1500);
//
////        java.util.List<WebElement> myResults;
//
//        myResults = driver.findElements(By.tagName("td"));
//        isBreak=false;
////        int $l = 0;
//        if (myResults.size() > 0){
//            System.out.println("There is one record above.");
//
//            // this.myresultList($l,myResults,driver);
////    for (WebElement myResult : myResults) {
//            for(int $p=0; $p<myResults.size();$p++){
//                if(isBreak) break;
//                WebElement myResult4;
//                myResult4 = (WebElement) myResults.get($p);
//                String idstr2 = myResult4.getText();
//                if("Active".equals(idstr2)) {
//                    int $k=0;
//                    $k = $p;
//                    $p = $p - 3;
//                    System.out.println(idstr2);
//                    WebElement myResult5;
//                    myResult5 = (WebElement) myResults.get($p);
//                    String idstr5 = myResult5.getText();
////                    java.util.List<WebElement> myResults3a = driver.findElements(By.tagName("a"));
//                    java.util.List<WebElement> myResults3c;
//                    myResults3c = driver.findElements(By.tagName("a"));
//                    int $j = 0;
//                    $p = $k;
//                    if (myResults3c.size() > 0) {
//                        for (WebElement myResult3a : myResults3c) {
//                            String idstr3a = myResult3a.getText();
//                            String id3a = myResult3a.getAttribute("id");
//                            if (idstr5.equals(idstr3a)) {
//                                // System.out.println("$ j is " + $j);
//
//                                driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
//                                Thread.sleep(1500);
//                                driver.findElement(By.name("detailCourseIsActive")).click();
//                                Thread.sleep(1500);
//                                driver.findElement(By.name("langIsViewable")).click();
//                                Thread.sleep(1900);
//                                driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
//                                Thread.sleep(2500);
//                                driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
//                                Thread.sleep(1500);
//                                driver.findElement(By.partialLinkText("Courses")).click();
//                                Thread.sleep(1500);
//                                driver.findElement(By.id("srch_fld")).sendKeys(idstr3a);
    //                                Thread.sleep(1500);
    //                                driver.findElement(By.name("searchButton")).click();
//                                Thread.sleep(1500);
//                                String currentWin = driver.getWindowHandle();
//                                try {
//                                    driver.findElement(By.className("onelang")).click();
//                                } catch (NoSuchElementException e) {
//                                    isBreak = true;
//                                    break ;
//                                }
//                                Thread.sleep(1500);
//
//                            }
//
//                        }
//                        $j++;
//                    }
//                    $p++;
//                }
//            }
//
//        }
//        else {
//            System.out.println("There is no record at all.");
//        }
//
//        robot.keyPress(KeyEvent.VK_META);
//        robot.keyPress(KeyEvent.VK_SHIFT);
//        robot.keyPress(KeyEvent.VK_G);
//        robot.keyRelease(KeyEvent.VK_META);
//        robot.keyRelease(KeyEvent.VK_SHIFT);
//        robot.keyRelease(KeyEvent.VK_G);
//
//        Thread.sleep(1500);
//
//        robot.keyPress(KeyEvent.VK_META);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_META);
//        robot.keyRelease(KeyEvent.VK_V);
//
//        Thread.sleep(1500);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        Thread.sleep(1500);
//
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//
//        Thread.sleep(1500);
//
//        driver.findElement(By.id("lightbox")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("login_login_id")).sendKeys(username);
//        driver.findElement(By.id("login_password")).sendKeys(password);
//
//        driver.findElement(By.name("submit")).click();
//        Thread.sleep(1500);
//
//        driver.quit();

        }

}

