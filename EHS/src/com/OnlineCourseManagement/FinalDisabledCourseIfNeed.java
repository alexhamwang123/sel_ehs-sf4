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

                //myResults = driver.findElements(By.tagName("td"));
                myResults = driver.findElements(By.xpath("//table[@id=\"userRecord\"]/tbody/tr/td"));
                if (myResults.size() > 0) {
                    for (int $p = 0; $p < myResults.size(); $p++) {
                        if (isBreak) break;
                        WebElement myResult4;
                        myResult4 = (WebElement) myResults.get($p);
                        String idstr2 = myResult4.getText();
                        //It must be Survey_Only_New
                        System.out.println("Line 142 idstr2=" + idstr2 );
                        if ("Active".equals(idstr2)) {
                            //we have to  make one to be inactived at least if it mets.
                            int $k = 0;
                            $k = $p;
                            $p = $p - 3;
                            WebElement myResult5;
                            myResult5 = (WebElement) myResults.get($p);
                            String idstr5 = myResult5.getText();
                            System.out.println(" Line 150 idstr5=" + idstr5 );
                            java.util.List<WebElement> myResults3a;
//                            myResults3a = driver.findElements(By.tagName("a"));
                            myResults3a = driver.findElements(By.xpath("//table[@id=\"userRecord\"]/tbody/tr/td/a"));
                            int $j = 0;
                            $p = $k;
                            if (myResults3a.size() > 0) {
                                for (WebElement myResult3a : myResults3a) {
                                    String idstr3a = myResult3a.getText();
                                    System.out.println("idstr3a=" + idstr3a );
                                    String id3a = myResult3a.getAttribute("id");
                                    if (idstr5.equals(idstr3a) && !idstr3a.equals("EHS-11111    ")) {
                                        System.out.println("Line 163 idstr3a=" + idstr3a );
                                        System.out.println("Line 164 idstr5=" + idstr5 );
                                        // System.out.println("$ j is " + $j);
//System.out.println("idstr3a is" + idstr3a);
                                        driver.findElement(By.xpath("//a[@id='" + id3a + "']")).click();
                                        Thread.sleep(1500);
                                        driver.findElement(By.name("detailCourseIsActive")).click();
                                        Thread.sleep(1500);
                                        driver.findElement(By.name("langIsViewable")).click();
                                        Thread.sleep(1900);
                                        try {
                                            //ErrorMsg

                                        } catch (Exception $e) {

                                        }
                                        driver.findElement(By.cssSelector("input[type='submit'][value='Yes']")).click();
                                        Thread.sleep(1500);
                                        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
                                        Thread.sleep(1000);
                                        String currentWin = driver.getWindowHandle();
                                        try {
                                            driver.findElement(By.className("onelang")).click();
                                        } catch (NoSuchElementException e) {
                                            continue restartLoop;
                                        }
                                        Thread.sleep(1500);

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

