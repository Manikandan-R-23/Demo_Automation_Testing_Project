package com.guru99bankV4_testCases;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;
import com.guru99bankV4.HomePagePojo;
import com.guru99bankV4.ManagerPagePojo;

public class LoginSectionTests extends BaseClass {

	String baseUrl = "https://www.demo.guru99.com/V4/";

	@BeforeClass(groups = { "sanity" })
	private void bfrCls() {
		launchEdgeBrowser();
		implicitlyWaitFor(30);
	}

	@BeforeMethod(groups = { "sanity" })
	private void bfrMth() {
		loadUrl(baseUrl);
		maximzeWindow();
	}

	@Test(groups = { "sanity" })
	private void ss1() throws IOException {
		HomePagePojo p = new HomePagePojo();
		@SuppressWarnings("resource")
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter valid user id:");
		String userId = s1.next();
		System.out.println("Enter valid password:");
		String pass = s1.next();
		sendText(p.getUserIdTextBox(), userId);
		sendText(p.getPasswordTextBox(), pass);
		clickWebElement(p.getLoginButton());
		ManagerPagePojo p1 = new ManagerPagePojo();
		WebElement managerIdText = p1.getManagerIdText();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(fetchTitle().contains("Manager"), "Login Check");
		sa.assertTrue(managerIdText.getText().contains(userId), "Login Check");
		takeSnap("testcase_ss1");
	}

	@Test(groups = "sanity", dataProvider = "invalidCredentials")
	private void ss2_ss3_ss4(String userId, String password) {
		HomePagePojo p = new HomePagePojo();
		sendText(p.getUserIdTextBox(), userId);
		sendText(p.getPasswordTextBox(), password);
		clickWebElement(p.getLoginButton());
		SoftAssert sa = new SoftAssert();
		String alertText = alertGetText();
		sa.assertTrue(alertText.contains("not valid"), "Login Check");
		alertAccept();
	}

	@DataProvider(name = "invalidCredentials")
	private Object[][] invalidCredentialsData() {
		return new Object[][] { { "nngr531082", "YgebUra" }, { "mngr531081", "YgeBusa" }, { "mngr631181", "ygecUrA" } };
	}

	@AfterClass(groups = { "sanity" })
	private void afrCls() {
		quitBrowser();
	}

}
