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
import org.openqa.selenium.support.ui.Select;
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

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
		//driver.manage().window().maximize();
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		driver.findElement(By.id("login_login_id")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);

		driver.findElement(By.name("submit")).click();

		Thread.sleep(10000);


		driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

		driver.findElement(By.id("srch_fld")).sendKeys("04a1awuKpJ"); //checklist name on localhost

		driver.findElement(By.name("searchButton")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}


		//Click on the Enroll button
		driver.findElement(By.xpath("//*[@id='msg_headd96dd51c141c3643425434a4898d8e09']/table/tbody/tr/td[5]/img")).click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		//Click on 'Default-English' Language option
		driver.findElement(By.id("crselink1")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}

		//Clicking the Radio Button
		//driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();

//		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		//Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
		driver.findElement(By.cssSelector("input[type='checkbox'][value='13c496ea53471d3bce0c72e9b4a63b66']")).click(); // Cat

		driver.findElement(By.cssSelector("input[type='checkbox'][value='4bd53adf7e6118d7c8625537fdd551c6']")).click(); // Elephant

		driver.findElement(By.cssSelector("input[type='checkbox'][value='9dadf671b25ecf8ba926941141405bf9']")).click(); // Fox

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		//Entering value in text box for Question 4

		driver.findElement(By.xpath("//*[@id='78b39db9d6c9f3801f1792d2073b9c32']")).clear();

		driver.findElement(By.xpath("//*[@id='78b39db9d6c9f3801f1792d2073b9c32']")).sendKeys("40");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		//not Submit, it's gonna to  be Saved value.
//		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

//		driver.findElement(By.id("fancyConfirm_ok")).click();
//		Thread.sleep(1500);

		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
//		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("fancyConfirm_ok")).click();
		Thread.sleep(5000);

		driver.findElement(By.partialLinkText("Courses")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("srch_fld")).sendKeys("04a1awuKpJ");//checklist name is on localhost

		driver.findElement(By.name("searchButton")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		//it's for 04a1awuKpJ g
		String paused = driver.findElement(By.xpath("//*[@id=\"msg_headd96dd51c141c3643425434a4898d8e09\"]/table/tbody/tr/td[3]")).getAttribute("innerHTML").substring(133,139);
//		System.out.println(paused);

		if (!paused.equals("Paused")) {
			Assert.fail("the course does not show up as paused");
		}

		Thread.sleep(3500);
		driver.quit();


		//Click on the Enroll button




	}

}