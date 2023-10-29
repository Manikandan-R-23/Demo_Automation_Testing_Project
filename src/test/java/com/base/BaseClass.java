package com.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driv;
	public static Alert alt;
	public static Actions act;
	public static Select sel;
	public static Robot rob;
	public static JavascriptExecutor js;

	// WebDriver methods

	public static void launchChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driv = new ChromeDriver();
	}

	public static void launchEdgeBrowser() {
		WebDriverManager.edgedriver().setup();
		driv = new EdgeDriver();
	}

	public static void launchFirefoxBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driv = new FirefoxDriver();
	}

	public static void loadUrl(String url) {
		driv.get(url);
	}

	public static String fetchTitle() {
		return driv.getTitle();
	}

	public static String fetchCurrentUrl() {
		return driv.getCurrentUrl();
	}

	public static void maximzeWindow() {
		driv.manage().window().maximize();
	}

	public static void minimizeWindow() {
		driv.manage().window().minimize();
	}

	public static void switchToPreviousWebpage() {
		driv.navigate().back();
	}

	public static void switchToNextWebpage() {
		driv.navigate().forward();
	}

	public static void refreshCurrentWebpage() {
		driv.navigate().refresh();
	}

	public static void switchToWindow(int windowIndexPosition) {
		Set<String> setAllWinId = driv.getWindowHandles();
		List<String> allWinId = new LinkedList<String>();
		allWinId.addAll(setAllWinId);
		driv.switchTo().window(allWinId.get(windowIndexPosition));
	}

	public static void implicitlyWaitFor(int seconds) {
		driv.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	public static void closeTab() {
		driv.close();
	}

	public static void quitBrowser() {
		driv.quit();
	}

	// WebElement methods

	public static WebElement findElementByXpath(String s) {
		return driv.findElement(By.xpath(s));
	}

	public static void sendText(WebElement refName, String text) {
		refName.sendKeys(text);
	}

	public static void clickWebElement(WebElement refName) {
		refName.click();
	}

	public static void printTextInWebElement(WebElement refName) {
		System.out.println(refName.getText());
	}

	public static void printAttributeValueInWebElement(WebElement refName, String attributeName) {
		System.out.println(refName.getAttribute(attributeName));
	}

	public static String getAttributeValueInWebElement(WebElement refName, String attributeName) {
		return refName.getAttribute(attributeName);
	}

	// Alert interface methods

	public static void alertAccept() {
		alt = driv.switchTo().alert();
		alt.accept();
	}

	public static void alertDismiss() {
		alt = driv.switchTo().alert();
		alt.dismiss();
	}

	public static void alertPrintText() {
		alt = driv.switchTo().alert();
		System.out.println(alt.getText());
	}

	public static String alertGetText() {
		alt = driv.switchTo().alert();
		return alt.getText();
	}

	public static void alertSendText(String text) {
		alt = driv.switchTo().alert();
		alt.sendKeys(text);
	}

	// Actions class methods

	public static void mouseActionMoveToElement(WebElement target) {
		act = new Actions(driv);
		act.moveToElement(target).perform();
	}

	public static void mouseActionDragDrop(WebElement source, WebElement target) {
		act = new Actions(driv);
		act.dragAndDrop(source, target).perform();
	}

	public static void mouseActionDoubleClick(WebElement target) {
		act = new Actions(driv);
		act.doubleClick(target).perform();
	}

	public static void mouseActionContextClick(WebElement target) {
		act = new Actions(driv);
		act.contextClick(target).perform();
	}

	// Select class methods

	public static void selSelectByValue(WebElement refName, String value) {
		sel = new Select(refName);
		sel.selectByValue(value);
	}

	public static void selSelectByIndex(WebElement refName, int indexPosition) {
		sel = new Select(refName);
		sel.selectByIndex(indexPosition);
	}

	public static void selSelectByText(WebElement refName, String text) {
		sel = new Select(refName);
		sel.selectByVisibleText(text);
	}

	public static void selDeselectByValue(WebElement refName, String value) {
		sel = new Select(refName);
		sel.deselectByValue(value);
	}

	public static void selDeselectByIndex(WebElement refName, int indexPosition) {
		sel = new Select(refName);
		sel.deselectByIndex(indexPosition);
	}

	public static void selDeselectByText(WebElement refName, String text) {
		sel = new Select(refName);
		sel.deselectByVisibleText(text);
	}

	public static void selDeselectAll(WebElement refName) {
		sel = new Select(refName);
		sel.deselectAll();
	}

	public static void selPrintFirstSelectedOption(WebElement refName) {
		sel = new Select(refName);
		System.out.println("First selected option in the dropdown box: " + sel.getFirstSelectedOption().getText());
	}

	public static void selPrintAllSelectedOptions(WebElement refName) {
		sel = new Select(refName);
		List<WebElement> allSelectedOptions = sel.getAllSelectedOptions();
		System.out.println("All selected options in the dropdown box:");
		for (WebElement eachOption : allSelectedOptions) {
			System.out.println(eachOption.getText());
		}
	}

	public static void selPrintAllOptions(WebElement refName) {
		sel = new Select(refName);
		List<WebElement> options = sel.getOptions();
		System.out.println("All options present in the dropdown box:");
		for (WebElement eachOption : options) {
			System.out.println(eachOption.getText());
		}
	}

	// TakesScreenshot method

	public static void takeSnap(String fileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driv;
		File tempFile = ts.getScreenshotAs(OutputType.FILE);
		File permFile = new File(
				"C:\\Users\\Dell\\eclipse-workspace 3\\Guru99_test_automaton\\Test Screenshots\\" + fileName + ".png");
		FileUtils.copyFile(tempFile, permFile);
	}

	// JavascriptExecutor methods

	public static void jsExecutorSendText(String text, WebElement refName) {
		js = (JavascriptExecutor) driv;
		js.executeScript("arguments[0].setAttribute('value','" + text + "')", refName);
	}

	public static void jsExecutorClick(WebElement refName) {
		js = (JavascriptExecutor) driv;
		js.executeScript("arguments[0].click()", refName);
	}

	public static void jsExecutorPrintAttributeValueInWebElement(WebElement refName, String attributeName) {
		js = (JavascriptExecutor) driv;
		System.out.println(js.executeScript("return arguments[0].getAttribute('" + attributeName + "')", refName));
	}

	public static void jsExecutorScrollDown(WebElement refName) {
		js = (JavascriptExecutor) driv;
		js.executeScript("arguments[0].scrollIntoView(true)", refName);
	}

	public static void jsExecutorScrollUp(WebElement refName) {
		js = (JavascriptExecutor) driv;
		js.executeScript("arguments[0].scrollIntoView(false)", refName);
	}

	// DataDriven Framework

	public static void excelPrintParticularRowCellValue(String xlFileName, String sheetName, int rowIndexPosition,
			int cellIndexPosition) throws IOException {
		String cellValue;
		File xlFile = new File(
				"C:\\Users\\Dell\\eclipse-workspace\\MvnFirstProj\\Excel Sheet Files\\" + xlFileName + ".xlsx");
		FileInputStream fileRead = new FileInputStream(xlFile);
		Workbook book = new XSSFWorkbook(fileRead);
		Sheet sheet = book.getSheet(sheetName);
		Row row = sheet.getRow(rowIndexPosition);
		Cell cell = row.getCell(cellIndexPosition);
		int type = cell.getCellType();
		if (type == 1) {
			cellValue = cell.getStringCellValue();
			System.out.println("Row index position- " + rowIndexPosition + ", Cell index position- " + cellIndexPosition
					+ ", Cell value : " + cellValue);
		} else if (DateUtil.isCellDateFormatted(cell)) {
			Date date = cell.getDateCellValue();
			SimpleDateFormat sim = new SimpleDateFormat("DD-MM-YYYY");
			cellValue = sim.format(date);
			System.out.println("Row index position- " + rowIndexPosition + ", Cell index position- " + cellIndexPosition
					+ ", Cell value : " + cellValue);
		} else {
			double num = cell.getNumericCellValue();
			long numl = (long) num;
			cellValue = String.valueOf(numl);
			System.out.println("Row index position- " + rowIndexPosition + ", Cell index position- " + cellIndexPosition
					+ ", Cell value : " + cellValue);
		}
	}

	public static String excelGetParticularRowCellValue(String xlFileName, String sheetName, int rowIndexPosition,
			int cellIndexPosition) throws IOException {
		String cellValue;
		File xlFile = new File(
				"C:\\Users\\Dell\\eclipse-workspace\\MvnFirstProj\\Excel Sheet Files\\" + xlFileName + ".xlsx");
		FileInputStream fileRead = new FileInputStream(xlFile);
		Workbook book = new XSSFWorkbook(fileRead);
		Sheet sheet = book.getSheet(sheetName);
		Row row = sheet.getRow(rowIndexPosition);
		Cell cell = row.getCell(cellIndexPosition);
		int type = cell.getCellType();
		if (type == 1) {
			cellValue = cell.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(cell)) {
			Date date = cell.getDateCellValue();
			SimpleDateFormat sim = new SimpleDateFormat("DD-MM-YYYY");
			cellValue = sim.format(date);
		} else {
			double num = cell.getNumericCellValue();
			long numl = (long) num;
			cellValue = String.valueOf(numl);
		}
		return cellValue;
	}

	public static void excelCreateSheet(String xlFileName, String sheetName) throws IOException {
		File xlFile = new File(
				"C:\\Users\\Dell\\eclipse-workspace\\MvnFirstProj\\Excel Sheet Files\\" + xlFileName + ".xlsx");
		Workbook book = new XSSFWorkbook();
		book.createSheet(sheetName);
		FileOutputStream fileOut = new FileOutputStream(xlFile);
		book.write(fileOut);
	}

	public static void excelSendCellValueToParticularAlreadyWrittenCell(String xlFileName, String sheetName,
			int rowIndexPosition, int cellIndexPosition, String cellValue) throws IOException {
		File xlFile = new File(
				"C:\\Users\\Dell\\eclipse-workspace\\MvnFirstProj\\Excel Sheet Files\\" + xlFileName + ".xlsx");
		FileInputStream fileRead = new FileInputStream(xlFile);
		Workbook book = new XSSFWorkbook(fileRead);
		Sheet sheet = book.getSheet(sheetName);
		Row row = sheet.getRow(rowIndexPosition);
		Cell cell = row.getCell(cellIndexPosition);
		cell.setCellValue(cellValue);
		FileOutputStream fileOut = new FileOutputStream(xlFile);
		book.write(fileOut);
	}

	public static void excelSendCellValueToParticularCreateRowNewCell(String xlFileName, String sheetName,
			int rowIndexPosition, int cellIndexPosition, String cellValue) throws IOException {
		File xlFile = new File(
				"C:\\Users\\Dell\\eclipse-workspace\\MvnFirstProj\\Excel Sheet Files\\" + xlFileName + ".xlsx");
		FileInputStream fileRead = new FileInputStream(xlFile);
		Workbook book = new XSSFWorkbook(fileRead);
		Sheet sheet = book.getSheet(sheetName);
		Row row = sheet.createRow(rowIndexPosition);
		Cell cell = row.createCell(cellIndexPosition);
		cell.setCellValue(cellValue);
		FileOutputStream fileOut = new FileOutputStream(xlFile);
		book.write(fileOut);
	}

	public static void excelSendCellValueToParticularGetRowNewCell(String xlFileName, String sheetName,
			int rowIndexPosition, int cellIndexPosition, String cellValue) throws IOException {
		File xlFile = new File(
				"C:\\Users\\Dell\\eclipse-workspace\\MvnFirstProj\\Excel Sheet Files\\" + xlFileName + ".xlsx");
		FileInputStream fileRead = new FileInputStream(xlFile);
		Workbook book = new XSSFWorkbook(fileRead);
		Sheet sheet = book.getSheet(sheetName);
		Row row = sheet.getRow(rowIndexPosition);
		Cell cell = row.createCell(cellIndexPosition);
		cell.setCellValue(cellValue);
		FileOutputStream fileOut = new FileOutputStream(xlFile);
		book.write(fileOut);
	}

	// JUnit Framework

	public static void printStartDate() {
		Date d = new Date();
		System.out.println(d);
	}

	public static void printEndDate() {
		Date d = new Date();
		System.out.println(d + "\n");
	}

	// Robot class methods

	public static void robPnRCtrl() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyRelease(KeyEvent.VK_CONTROL);
	}

	public static void robPnRShift() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_SHIFT);
		rob.keyRelease(KeyEvent.VK_SHIFT);
	}

	public static void robPnRCapsLock() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_CAPS_LOCK);
		rob.keyRelease(KeyEvent.VK_CAPS_LOCK);
	}

	public static void robPnRTab() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyRelease(KeyEvent.VK_TAB);
	}

	public static void robPnRWindows() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_WINDOWS);
		rob.keyRelease(KeyEvent.VK_WINDOWS);
	}

	public static void robPressAlt() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_ALT);
	}

	public static void robReleaseAlt() throws AWTException {
		rob = new Robot();
		rob.keyRelease(KeyEvent.VK_ALT);
	}

	public static void robPnRSpace() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_SPACE);
		rob.keyRelease(KeyEvent.VK_SPACE);
	}

	public static void robPnRUpArrow() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_UP);
		rob.keyRelease(KeyEvent.VK_UP);
	}

	public static void robPnRDownArrow() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_DOWN);
		rob.keyRelease(KeyEvent.VK_DOWN);
	}

	public static void robPnRLeftArrow() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_LEFT);
		rob.keyRelease(KeyEvent.VK_LEFT);
	}

	public static void robPnRRightArrow() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_RIGHT);
		rob.keyRelease(KeyEvent.VK_RIGHT);
	}

	public static void robPnREnter() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void robPnRBackSpace() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_BACK_SPACE);
		rob.keyRelease(KeyEvent.VK_BACK_SPACE);
	}

	public static void robPnRF1() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F1);
		rob.keyRelease(KeyEvent.VK_F1);
	}

	public static void robPnRF2() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F2);
		rob.keyRelease(KeyEvent.VK_F2);
	}

	public static void robPnRF3() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F3);
		rob.keyRelease(KeyEvent.VK_F3);
	}

	public static void robPnRF4() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F4);
		rob.keyRelease(KeyEvent.VK_F4);
	}

	public static void robPnRF5() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F5);
		rob.keyRelease(KeyEvent.VK_F5);
	}

	public static void robPnRF6() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F6);
		rob.keyRelease(KeyEvent.VK_F6);
	}

	public static void robPnRF7() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F7);
		rob.keyRelease(KeyEvent.VK_F7);
	}

	public static void robPnRF8() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F8);
		rob.keyRelease(KeyEvent.VK_F8);
	}

	public static void robPnRF9() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F9);
		rob.keyRelease(KeyEvent.VK_F9);
	}

	public static void robPnRF10() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F10);
		rob.keyRelease(KeyEvent.VK_F10);
	}

	public static void robPnRF11() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F11);
		rob.keyRelease(KeyEvent.VK_F11);
	}

	public static void robPnRF12() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F12);
		rob.keyRelease(KeyEvent.VK_F12);
	}

	public static void robPnR0() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_0);
		rob.keyRelease(KeyEvent.VK_0);
	}

	public static void robPnR1() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_1);
		rob.keyRelease(KeyEvent.VK_1);
	}

	public static void robPnR2() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_2);
		rob.keyRelease(KeyEvent.VK_2);
	}

	public static void robPnR3() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_3);
		rob.keyRelease(KeyEvent.VK_3);
	}

	public static void robPnR4() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_4);
		rob.keyRelease(KeyEvent.VK_4);
	}

	public static void robPnR5() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_5);
		rob.keyRelease(KeyEvent.VK_5);
	}

	public static void robPnR6() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_6);
		rob.keyRelease(KeyEvent.VK_6);
	}

	public static void robPnR7() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_7);
		rob.keyRelease(KeyEvent.VK_7);
	}

	public static void robPnR8() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_8);
		rob.keyRelease(KeyEvent.VK_8);
	}

	public static void robPnR9() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_9);
		rob.keyRelease(KeyEvent.VK_9);
	}

	public static void robPnRAdd() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_ADD);
		rob.keyRelease(KeyEvent.VK_ADD);
	}

	public static void robPnRSubtract() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_SUBTRACT);
		rob.keyRelease(KeyEvent.VK_SUBTRACT);
	}

	public static void robPnRMultiply() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_MULTIPLY);
		rob.keyRelease(KeyEvent.VK_MULTIPLY);
	}

	public static void robPnRDivide() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_DIVIDE);
		rob.keyRelease(KeyEvent.VK_DIVIDE);
	}

	public static void robPnRA() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_A);
		rob.keyRelease(KeyEvent.VK_A);
	}

	public static void robPnRB() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_B);
		rob.keyRelease(KeyEvent.VK_B);
	}

	public static void robPnRC() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_C);
		rob.keyRelease(KeyEvent.VK_C);
	}

	public static void robPnRD() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_D);
		rob.keyRelease(KeyEvent.VK_D);
	}

	public static void robPnRE() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_E);
		rob.keyRelease(KeyEvent.VK_E);
	}

	public static void robPnRF() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_F);
		rob.keyRelease(KeyEvent.VK_F);
	}

	public static void robPnRG() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_G);
		rob.keyRelease(KeyEvent.VK_G);
	}

	public static void robPnRH() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_H);
		rob.keyRelease(KeyEvent.VK_H);
	}

	public static void robPnRI() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_I);
		rob.keyRelease(KeyEvent.VK_I);
	}

	public static void robPnRJ() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_J);
		rob.keyRelease(KeyEvent.VK_J);
	}

	public static void robPnRK() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_K);
		rob.keyRelease(KeyEvent.VK_K);
	}

	public static void robPnRL() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_L);
		rob.keyRelease(KeyEvent.VK_L);
	}

	public static void robPnRM() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_M);
		rob.keyRelease(KeyEvent.VK_M);
	}

	public static void robPnRN() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_N);
		rob.keyRelease(KeyEvent.VK_N);
	}

	public static void robPnRO() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_O);
		rob.keyRelease(KeyEvent.VK_O);
	}

	public static void robPnRP() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_P);
		rob.keyRelease(KeyEvent.VK_P);
	}

	public static void robPnRQ() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_Q);
		rob.keyRelease(KeyEvent.VK_Q);
	}

	public static void robPnRR() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_R);
		rob.keyRelease(KeyEvent.VK_R);
	}

	public static void robPnRS() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_S);
		rob.keyRelease(KeyEvent.VK_S);
	}

	public static void robPnRT() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_T);
		rob.keyRelease(KeyEvent.VK_T);
	}

	public static void robPnRU() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_U);
		rob.keyRelease(KeyEvent.VK_U);
	}

	public static void robPnRV() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_V);
		rob.keyRelease(KeyEvent.VK_V);
	}

	public static void robPnRW() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_W);
		rob.keyRelease(KeyEvent.VK_W);
	}

	public static void robPnRX() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_X);
		rob.keyRelease(KeyEvent.VK_X);
	}

	public static void robPnRY() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_Y);
		rob.keyRelease(KeyEvent.VK_Y);
	}

	public static void robPnRZ() throws AWTException {
		rob = new Robot();
		rob.keyPress(KeyEvent.VK_Z);
		rob.keyRelease(KeyEvent.VK_Z);
	}

	// TO CHANGE IN EACH BASECLASS FILE
	// 1) takeSnap method - image path folder
	// 2)

}
