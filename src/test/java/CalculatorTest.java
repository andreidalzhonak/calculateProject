import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {
  private AndroidDriver<MobileElement> driver;

  @AndroidFindBy(id = "com.google.android.calculator:id/digit_5")
  private MobileElement five;

  @AndroidFindBy(id = "com.google.android.calculator:id/op_add")
  private MobileElement add;

  @AndroidFindBy(id = "com.google.android.calculator:id/digit_3")
  private MobileElement three;

  @AndroidFindBy(id = "com.google.android.calculator:id/eq")
  private MobileElement equal;

  @AndroidFindBy(id = "com.google.android.calculator:id/result_final")
  private MobileElement result;

  @BeforeClass
  public void setUp() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("appium:deviceName", "Android Emulator");
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("appPackage", "com.google.android.calculator");
    capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
    capabilities.setCapability("noReset", true);

    driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  @Test
  public void testAddition() {
    five.click();
    add.click();
    three.click();
    equal.click();

    String actualResult = result.getText();
    String expectedResult = "8";
    assert actualResult.equals(expectedResult);
  }

  @AfterClass
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}