package com.example.resttesting;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
    monochrome = true,
    plugin = {"pretty", "html:target/cucumber", "json:target/cucumber/cucumber.json"},
    features = {"src/test/resources/features/createCustomer.feature", "src/test/resources/features/getCustomer.feature","src/test/resources/features/createCustomer_outlines.feature"},
    glue = {"com/example/resttesting/steps"},
    snippets = SnippetType.CAMELCASE,
    tags = {"@outline"}
)
public class Runner {
}
