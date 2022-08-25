package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/default-cucumber-reports.html", //html rapor
                "json:target/json-reports/cucumber.json",    //json rapor
                "junit:target/xml-report/cucumber.xml"      //xml rapor
        },
        features = "src/test/resources/features",     //feature yolu
        glue = {"stepdefinitions","hooks"},          // stepdefinitions ve hooks yolu
        tags = "@favoriuruneklesil",
        dryRun = false                       //step definition da olabilecek hata kontrolu

)
public class Runner {
}
