//This script is written to create a new Announcement and check in the list of announcements 
//whether the newly created announcement appears in the list for EHS system

package com.EHSAdmin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class CreateAnnouncement {

	public void CreateAnnouncement1() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait Wait= new WebDriverWait(driver,30);
		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
        driver.manage().window().maximize();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on 'EHS Admin'

		JavascriptExecutor js = (JavascriptExecutor)driver;

		WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));

		js.executeScript("arguments[0].click();",ele1);


		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on 'Create Announcement'
		WebElement CreatBtn= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[1]/div/div[4]/div/ul/a"));
		js.executeScript("arguments[0].click();",CreatBtn);



		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
		String title = generator.generate(10);

		//Entering the 'Announcement Title'
		driver.findElement(By.id("annTitle")).sendKeys(title);
		Thread.sleep(1500);
		//Entering the Announcement Content
		driver.switchTo().frame("annContent_ifr");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys("this is a test");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		driver.switchTo().defaultContent();
		Thread.sleep(1500);

		//Click the Calendar
		driver.findElement(By.id("annEndDate")).click();
		Thread.sleep(1500);

		//      System.out.println("Local Date Now=" + dateFormat.format(date));
		JavascriptExecutor js10 = (JavascriptExecutor)driver;
		java.util.List<WebElement> list = driver.findElements(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div/form/div[2]/div[4]/div/div/div[1]/div[2]/div/div[2]/div/span"));
		Calendar calendar = new GregorianCalendar();


		int DateofToday= calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("DateofToday:"+DateofToday);

		String TodayOnly= String.valueOf(DateofToday);
		System.out.println("TodayOnly:"+TodayOnly);
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
				js10.executeScript("arguments[0].click();", e);
				break;
			}
			else{System.out.println("Object Not Found ");
			}
		}

		Thread.sleep(2000);
		//Clicking on 'Save'
		WebElement Save=driver.findElement(By.id("annSave"));
		js.executeScript("arguments[0].click();",Save);

		Thread.sleep(3000);

		//clicking on 'Back'
		WebElement Back=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div/form/div[2]/div[8]/div/div/button[2]"));
		js.executeScript("arguments[0].click();",Back);


		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Search Title
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[1]/div/div[1]/div/input")).sendKeys(title);
		Thread.sleep(1500);


		//Entering the Announcement Content
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[2]/div[1]/table/tbody/tr/td[1]")).click();
		Thread.sleep(3000);
		driver.switchTo().frame("annContent_ifr");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys("Im editing this content");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		JavascriptExecutor js1 = (JavascriptExecutor)driver;

		//Clicking on 'Save'
		WebElement Save1=driver.findElement(By.id("annSave"));
		js1.executeScript("arguments[0].click();",Save1);
		Thread.sleep(3000);
		js1.executeScript("arguments[0].click();",Save1);
		Thread.sleep(3000);

		//clicking on 'Back'
		WebElement Back1=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div/form/div[2]/div[8]/div/div/button[2]"));
		js1.executeScript("arguments[0].click();",Back1);

		Thread.sleep(3000);
		//Search Title
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[1]/div/div[1]/div/input")).clear();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[1]/div/div[1]/div/input")).sendKeys(title);

		//CLick the result
		Thread.sleep(3500);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div[2]/div[1]/table/tbody/tr/td[1]")).click();
		Thread.sleep(3000);
		driver.switchTo().frame("annContent_ifr");
		Thread.sleep(1500);
		if(!driver.findElement(By.xpath("//p[contains(text(),'this is a testIm editing this content')]")).getAttribute("innerHTML").contains("editing")) {
			Assert.fail("something went wrong with editing the announcement content");
		}

		driver.quit();



	}

}
