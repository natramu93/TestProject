package steps;

import org.openqa.selenium.Keys;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import po.HomePage;
import utils.KeyWords;

public class Definition {

	@Before
	public void setup() {
		KeyWords.initialize("gc");
	}
	
	@After
	public void teardown() {
		KeyWords.quit();
	}
	
	@Given("I am able to navigate onto Google.com")
	public void i_am_able_to_navigate_onto_google() {
		KeyWords.navigate("http://google.com");
	}
	
	@When("I Search for {string}")
	public void i_search_for(String string) {
		KeyWords.type(HomePage.searchBar, string+Keys.ENTER);
	}
	
	@Then("I should see the title as {string}")
	public void i_should_see_the_title_as(String string) {
		KeyWords.assertTitle(string);
	}
}
