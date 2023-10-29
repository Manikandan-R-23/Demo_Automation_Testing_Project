package com.guru99bankV4;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;

public class AddNewAccountPagePojo extends BaseClass {

	public AddNewAccountPagePojo() {
		PageFactory.initElements(driv, this);
	}

	@FindBy(xpath = "//input[@name='cusid']")
	private WebElement customerIdTextBox;

	@FindBy(xpath = "//select[@name='selaccount']")
	private WebElement accountTypeDropdown;

	@FindBy(xpath = "//input[@name='inideposit']")
	private WebElement initialDepositTextBox;

	@FindBy(xpath = "//input[@name='button2']")
	private WebElement submitButton;

	public WebElement getCustomerIdTextBox() {
		return customerIdTextBox;
	}

	public WebElement getAccountTypeDropdown() {
		return accountTypeDropdown;
	}

	public WebElement getInitialDepositTextBox() {
		return initialDepositTextBox;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

}
