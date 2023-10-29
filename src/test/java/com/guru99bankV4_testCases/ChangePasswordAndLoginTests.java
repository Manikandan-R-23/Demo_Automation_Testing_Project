package com.guru99bankV4_testCases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;
import com.guru99bankV4.ChangePasswordPagePojo;
import com.guru99bankV4.HomePagePojo;
import com.guru99bankV4.ManagerPagePojo;

public class ChangePasswordAndLoginTests extends BaseClass {

	String baseUrl = "https://www.demo.guru99.com/V4/";
	String newPass;

	// manager userId and password shall be changed according to user

	@BeforeClass
	private void bfrCls() {
		launchEdgeBrowser();
		implicitlyWaitFor(30);
	}

	@BeforeMethod
	private void bfrMth() {
		loadUrl(baseUrl);
		maximzeWindow();
	}

	@Test
	private void sm1() {
		HomePagePojo p1 = new HomePagePojo();
		WebElement userIdTextBox = p1.getUserIdTextBox();
		WebElement passwordTextBox = p1.getPasswordTextBox();
		sendText(userIdTextBox, "mngr531081");
		// Should be correct password
		sendText(passwordTextBox, "YgebUra");
		clickWebElement(p1.getLoginButton());
		ManagerPagePojo p2 = new ManagerPagePojo();
		clickWebElement(p2.getChangePasswordTab());
		ChangePasswordPagePojo p3 = new ChangePasswordPagePojo();
		WebElement oldPasswordtextBox = p3.getOldPasswordtextBox();
		WebElement newPasswordtextBox = p3.getNewPasswordtextBox();
		WebElement confirmPasswordtextBox = p3.getConfirmPasswordtextBox();
		sendText(oldPasswordtextBox, "sdefjg2");
		sendText(newPasswordtextBox, "123@456");
		sendText(confirmPasswordtextBox, "123@456");
		clickWebElement(p3.getSubmitButton());
		String toCheck = alertGetText();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(toCheck.contains("Old password is incorrect"), "Verify pop-up appeared");
		alertAccept();
		sa.assertTrue(fetchCurrentUrl().contains("Password"), "Verify re-directed page");
	}

	@Test
	private void sm2() {
		HomePagePojo p1 = new HomePagePojo();
		WebElement userIdTextBox = p1.getUserIdTextBox();
		WebElement passwordTextBox = p1.getPasswordTextBox();
		sendText(userIdTextBox, "mngr531081");
		// should be correct password
		sendText(passwordTextBox, "YgebUra");
		clickWebElement(p1.getLoginButton());
		ManagerPagePojo p2 = new ManagerPagePojo();
		clickWebElement(p2.getChangePasswordTab());
		ChangePasswordPagePojo p3 = new ChangePasswordPagePojo();
		WebElement oldPasswordtextBox = p3.getOldPasswordtextBox();
		WebElement newPasswordtextBox = p3.getNewPasswordtextBox();
		WebElement confirmPasswordtextBox = p3.getConfirmPasswordtextBox();
		sendText(oldPasswordtextBox, "YgebUra");
		// New password can be assigned here
		sendText(newPasswordtextBox, this.newPass = "123@456");
		sendText(confirmPasswordtextBox, "123@456");
		clickWebElement(p3.getSubmitButton());
		String toCheck = alertGetText();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(toCheck.contains("Password is changed"), "Verify pop-up appeared");
		alertAccept();
		sa.assertTrue(fetchTitle().contains("Guru99 Bank Home Page"), "Verify re-directed page");
	}

	@Test
	private void sm3() {
		HomePagePojo p1 = new HomePagePojo();
		WebElement userIdTextBox = p1.getUserIdTextBox();
		WebElement passwordTextBox = p1.getPasswordTextBox();
		sendText(userIdTextBox, "mngr531081");
		sendText(passwordTextBox, newPass);
		clickWebElement(p1.getLoginButton());
		ManagerPagePojo p2 = new ManagerPagePojo();
		WebElement toCheck = p2.getManagerIdText();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(toCheck.getText().contains("mngr531081"), "Verify manager home page");
		System.out.println("Take note - New Password is : " + newPass);
	}

	@AfterClass
	private void afrCls() {
		quitBrowser();
	}

}
