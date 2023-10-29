package com.guru99bankV4;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;

public class ChangePasswordPagePojo extends BaseClass {

	public ChangePasswordPagePojo() {
		PageFactory.initElements(driv, this);
	}

	@FindBy(xpath = "//input[@name='oldpassword']")
	private WebElement oldPasswordtextBox;

	@FindBy(xpath = "//input[@name='newpassword']")
	private WebElement newPasswordtextBox;

	@FindBy(xpath = "//input[@name='confirmpassword']")
	private WebElement confirmPasswordtextBox;

	@FindBy(xpath = "//input[@name='sub']")
	private WebElement submitButton;

	public WebElement getOldPasswordtextBox() {
		return oldPasswordtextBox;
	}

	public WebElement getNewPasswordtextBox() {
		return newPasswordtextBox;
	}

	public WebElement getConfirmPasswordtextBox() {
		return confirmPasswordtextBox;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

}
