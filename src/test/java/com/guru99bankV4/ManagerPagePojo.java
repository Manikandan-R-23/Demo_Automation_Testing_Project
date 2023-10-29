package com.guru99bankV4;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;

public class ManagerPagePojo extends BaseClass {

	public ManagerPagePojo() {
		PageFactory.initElements(driv, this);
	}

	@FindBy(xpath = "//td[text()='Manger Id : mngr531081']")
	private WebElement managerIdText;

	@FindBy(xpath = "//a[text()='Change Password']")
	private WebElement changePasswordTab;

	@FindBy(xpath = "//a[text()='New Customer']")
	private WebElement newCustomerTab;

	@FindBy(xpath = "//a[text()='New Account']")
	private WebElement newAccountTab;

	public WebElement getManagerIdText() {
		return managerIdText;
	}

	public WebElement getChangePasswordTab() {
		return changePasswordTab;
	}

	public WebElement getNewCustomerTab() {
		return newCustomerTab;
	}

	public WebElement getNewAccountTab() {
		return newAccountTab;
	}

}
