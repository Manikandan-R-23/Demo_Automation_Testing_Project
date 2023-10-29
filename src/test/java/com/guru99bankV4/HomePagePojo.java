package com.guru99bankV4;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;

public class HomePagePojo extends BaseClass {

	public HomePagePojo() {
		PageFactory.initElements(driv, this);
	}

	@FindBy(xpath = "//input[@name='uid']")
	private WebElement userIdTextBox;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordTextBox;

	@FindBy(xpath = "//input[@name='btnLogin']")
	private WebElement loginButton;

	@FindBy(xpath = "//input[@name='btnReset']")
	private WebElement resetButton;

	public WebElement getUserIdTextBox() {
		return userIdTextBox;
	}

	public WebElement getPasswordTextBox() {
		return passwordTextBox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getResetButton() {
		return resetButton;
	}

}
