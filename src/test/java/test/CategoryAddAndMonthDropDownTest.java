package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.BasePage;
import util.BrowserFactory;

public class CategoryAddAndMonthDropDownTest {
	WebDriver driver;
	BasePage basePage;

	Random rnd = new Random();
	String categoryName = "KiranTestNG" + rnd.nextInt(100);
	String duplicateCategoryName = "duplicate" + rnd.nextInt(100);

	@BeforeMethod
	public void runEverything() {
		driver = BrowserFactory.Init();
		basePage = PageFactory.initElements(driver, BasePage.class);

	}

	@Test(priority = 1)
	public void userShouldBeAbleToAddCategory() {
		basePage.addCategory(categoryName);
		List<String> actualList = basePage.getCategoryList();
		Assert.assertTrue(BrowserFactory.doesDataExist(categoryName, actualList), "New Category Does Not Exist!!");
		System.out.println("New Category name" + ":" + categoryName);
	}

	@Test(priority = 2)
	public void userShouldNotBeAbleToAddDuplicate() {
		basePage.addCategory(duplicateCategoryName);
		basePage.addCategory(duplicateCategoryName);
		Assert.assertTrue(basePage.duplicateMessageDisplayStatus(), "Duplicate Category Was Not Created!!");
		System.out.println("Duplicate Category was not created");
	}

	@Test(priority = 3)
	public void dropDownShouldHaveAllMonths() {

		List<String> months = new ArrayList<String>();
		months.add("None");
		months.add("Jan");
		months.add("Feb");
		months.add("Mar");
		months.add("Apr");
		months.add("May");
		months.add("Jun");
		months.add("Jul");
		months.add("Aug");
		months.add("Sep");
		months.add("Oct");
		months.add("Nov");
		months.add("Dec");

		List<String> dropDownList = basePage.getMonthList();
		Assert.assertTrue(BrowserFactory.doesDataMatch(months, dropDownList), "Values Do Not Match!!");

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
