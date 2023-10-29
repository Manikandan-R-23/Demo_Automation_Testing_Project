package com.guru99bankV4;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;

public class AddNewCustomerPagePojo extends BaseClass {

	public AddNewCustomerPagePojo() {
		PageFactory.initElements(driv, this);
	}

	@FindBy(xpath = "//input[@name='name']")
	private WebElement customerNameTextBox;

	@FindBy(xpath = "//input[@value='m']")
	private WebElement genderMaleRadioButton;

	@FindBy(xpath = "//input[@value='f']")
	private WebElement genderFemaleRadioButton;

	@FindBy(xpath = "//textarea[@name='addr']")
	private WebElement addressTextBox;

	@FindBy(xpath = "//input[@name='city']")
	private WebElement cityTextBox;

	@FindBy(xpath = "//input[@name='state']")
	private WebElement stateTextBox;

	@FindBy(xpath = "//input[@name='pinno']")
	private WebElement pinNoTextBox;

	@FindBy(xpath = "//input[@name='telephoneno']")
	private WebElement mobileNoTextBox;

	@FindBy(xpath = "//input[@name='emailid']")
	private WebElement emailIdTextBox;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordTextBox;

	@FindBys({ @FindBy(xpath = "//input[@name='sub']"), @FindBy(xpath = "//input[@type='submit']") })
	private WebElement submitButton;

	public WebElement getCustomerNameTextBox() {
		return customerNameTextBox;
	}

	public WebElement getGenderMaleRadioButton() {
		return genderMaleRadioButton;
	}

	public WebElement getGenderFemaleRadioButton() {
		return genderFemaleRadioButton;
	}

	public WebElement getAddressTextBox() {
		return addressTextBox;
	}

	public WebElement getCityTextBox() {
		return cityTextBox;
	}

	public WebElement getStateTextBox() {
		return stateTextBox;
	}

	public WebElement getPinNoTextBox() {
		return pinNoTextBox;
	}

	public WebElement getMobileNoTextBox() {
		return mobileNoTextBox;
	}

	public WebElement getEmailIdTextBox() {
		return emailIdTextBox;
	}

	public WebElement getPasswordTextBox() {
		return passwordTextBox;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

}
