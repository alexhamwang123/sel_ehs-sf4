//This program is to submit a checklist without completing it, and
//observe the behavior. Ideally, the web site should prompt a message
//saying that "Please complete all required questions (marked with a red "*") before submission."

package com.ChecklistManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.Alert;
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

import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

@Test(priority=0)
public class SubmitIncompleteChecklist {
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		Comparator<IMethodInstance> comparator = new Comparator<IMethodInstance>() {
			private int getLineNo(IMethodInstance mi) {
				int result = 0;

				String methodName = mi.getMethod().getConstructorOrMethod().getMethod().getName();
				String className  = mi.getMethod().getConstructorOrMethod().getDeclaringClass().getCanonicalName();
				ClassPool pool    = ClassPool.getDefault();

				try {
					CtClass cc        = pool.get(className);
					CtMethod ctMethod = cc.getDeclaredMethod(methodName);
					result            = ctMethod.getMethodInfo().getLineNumber(0);
				} catch (NotFoundException e) {
					e.printStackTrace();
				}

				return result;
			}

			public int compare(IMethodInstance m1, IMethodInstance m2) {
				return getLineNo(m1) - getLineNo(m2);
			}
		};

		IMethodInstance[] array = methods.toArray(new IMethodInstance[methods.size()]);
		Arrays.sort(array, comparator);
		return Arrays.asList(array);
	}

	public void SubmitIncompleteChecklist() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();
		WebDriverWait Wait= new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
		driver.manage().window().maximize();
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("kimi-checklist-006"); //checklist name on localhost


		Thread.sleep(3000);



		//Click on the Enroll button
		driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']")).click();

		Thread.sleep(3000);
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}


		//Click on 'Default-English' Language option
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		WebElement Language_English=driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
		js2.executeScript("arguments[0].click();",Language_English);


		Thread.sleep(3000);


		//Clicking the Radio Button
		//driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();

//		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();


		Thread.sleep(2000);

	//Click Save
		driver.findElement(By.xpath("//div[@class='buttons d-inline-block float-right']//button[@class='btn btn-secondary'][contains(text(),'Save')]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		Thread.sleep(2000);
        driver.close();
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("Courses")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys("kimi-checklist-006");//checklist name is on localhost


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		//it's for test course
		String paused = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[3]")).getAttribute("innerHTML");
//		System.out.println(paused);

		if (!paused.contains("Paused")) {
			Assert.fail("the course does not show up as paused");
		}

		Thread.sleep(3500);
		driver.quit();


		//Click on the Enroll button




	}

}