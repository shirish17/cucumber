package com.org.test.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
(
		format ={"pretty","json:target/json/output.json"},
		features="src/test/resources",
		tags={"@Test"}
)
public class RunnerTest {

}
