package com.guru99bankV4_testCases;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;
import com.guru99bankV4.AddNewAccountPagePojo;
import com.guru99bankV4.AddNewCustomerPagePojo;
import com.guru99bankV4.HomePagePojo;
import com.guru99bankV4.ManagerPagePojo;

public class AddNewCustomerAndAccountTests extends BaseClass {

	String baseUrl = "https://www.demo.guru99.com/V4/";

	@BeforeClass
	private void bfrCls() {
		launchEdgeBrowser();
		implicitlyWaitFor(30);
	}

	@BeforeMethod
	private void bfrMth() {
		loadUrl(baseUrl);
		maximzeWindow();
		HomePagePojo p1 = new HomePagePojo();
		WebElement userIdTextBox = p1.getUserIdTextBox();
		WebElement passwordTextBox = p1.getPasswordTextBox();
		sendText(userIdTextBox, "mngr531081");
		sendText(passwordTextBox, "123@456");
		clickWebElement(p1.getLoginButton());
	}

	@Test
	private void sm4() throws AWTException {
		ManagerPagePojo p2 = new ManagerPagePojo();
		clickWebElement(p2.getNewCustomerTab());
		AddNewCustomerPagePojo p3 = new AddNewCustomerPagePojo();
		sendText(p3.getCustomerNameTextBox(), "Virendra");
		clickWebElement(p3.getGenderMaleRadioButton());
		robPnRTab();
		robPnR0();
		robPnR4();
		robPnR1();
		robPnR1();
		robPnR2();
		robPnR0();
		robPnR1();
		robPnR3();
		robPnRTab();
		sendText(p3.getAddressTextBox(), "Jamnagar");
		sendText(p3.getCityTextBox(), "Jamnagar");
		sendText(p3.getStateTextBox(), "Gujarat");
		sendText(p3.getPinNoTextBox(), "567321");
		sendText(p3.getMobileNoTextBox(), "8000439024");
		sendText(p3.getEmailIdTextBox(), "Virendra@gmail.com");
		sendText(p3.getPasswordTextBox(), "123#456");
		clickWebElement(p3.getSubmitButton());
		WebElement toCheck = driv.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']"));
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(toCheck.getText().contains("Successfully!!"), "verify customer added successfully");
		WebElement logoutBtn = driv.findElement(By.xpath("//a[text()='Log out']"));
		clickWebElement(logoutBtn);
		alertAccept();
	}

	@Test
	private void sm5() {
		ManagerPagePojo p2 = new ManagerPagePojo();
		clickWebElement(p2.getNewAccountTab());
		AddNewAccountPagePojo p3 = new AddNewAccountPagePojo();
		sendText(p3.getCustomerIdTextBox(), "72446");
		selSelectByValue(p3.getAccountTypeDropdown(), "Savings");
		sendText(p3.getInitialDepositTextBox(), "500");
		clickWebElement(p3.getSubmitButton());
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(fetchCurrentUrl().contains("V4/manager/AccCreateMsg"), "verify account generated successfully");
		WebElement logoutBtn = driv.findElement(By.xpath("//a[text()='Log out']"));
		clickWebElement(logoutBtn);
		alertAccept();
	}

	@AfterClass
	private void afrCls() {
		quitBrowser();
	}

}
