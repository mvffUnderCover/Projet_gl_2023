package com.atgl.g5api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStepdefs {

    WebDriver webDriver;
    @Given(": I wanna start a browser")
    public void iWannaGetAllClients() {
        System.setProperty("webdriver.chrome.driver","src/test/java/com/atgl/g5api/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @When(": I do a Get request at {string}")
    public void iDoAGetRequestAtHttpLocalhostClients(String arg0) {
        webDriver.get(arg0);
    }

    @Then(": I receive {string}")
    public void iReceiveAllTheClient(String args0) {
        System.out.println(webDriver.getPageSource().contains(args0));
    }
}
