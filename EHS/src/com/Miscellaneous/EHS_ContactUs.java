//This script is written to fill the description in the Contact page on the EHS home page
//Path: EHS UAT > Contact > Enter Detail > Submit

package com.Miscellaneous;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

//@Test
@Test
public class EHS_ContactUs {

	public void EHS_ContactUs1() throws IOException, InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait Wait= new WebDriverWait(driver,30);
 		driver.manage().window().maximize();
        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();//i[@class='fa fa-envelope-square']
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

        try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Logout')]")));
		//Click on the Contact button

        driver.findElement(By.xpath("/html/body/div[1]/div/header/div/div/ul/li[2]/a")).click();
        Thread.sleep(1000);
        WebElement Contact=driver.findElement(By.xpath("//i[@class='fa fa-envelope-square']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()",Contact);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Enter the detail



        //Click the  calendar
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[3]/div/div/label")).click();
        Thread.sleep(1000);
        WebElement CalendarBox01=driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[3]/div/div/div/div/div/div[2]/div[2]"));
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




        new Select(driver.findElement(By.xpath("//select[@class='form-control custom-select']"))).selectByVisibleText("Can’t Login");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys("I am not able to connect to the server. My internet connection is active. Please help me");
		
		//Click on 'Submit'
	    driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();

	}

}
