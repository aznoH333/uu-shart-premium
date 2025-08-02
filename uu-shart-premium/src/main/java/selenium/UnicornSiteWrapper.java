package selenium;

import org.openqa.selenium.By;

public class UnicornSiteWrapper {

    private final SeleniumWrapper selenium;
    private final String testUrl;

    public UnicornSiteWrapper(String testUrl, SeleniumWrapper wrapper) {
        this.testUrl = testUrl;
        this.selenium = wrapper;
        selenium.goToUrl(testUrl);
    }


    public void login() {
        // get login button
        selenium.waitForElement(By.xpath(UnicornConstants.LOGIN_XPATH));
        selenium.clickElement(By.xpath(UnicornConstants.LOGIN_XPATH));
        selenium.awaitUserAction("Please login");
    }


}
