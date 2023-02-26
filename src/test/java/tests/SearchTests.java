package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

public class SearchTests extends TestBase {

    @Test
    void searchBrowserStackTest() {
        step("Type search", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void openArticleTest() {
        step("Type search", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(accessibilityId("Search Wikipedia")).click();

            $(id("org.wikipedia.alpha:id/search_container")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Sony");
        });
        step("Click on the article", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click()
        );
        step("Verify article", () -> {
            $(className("android.widget.TextView")).shouldHave(text("Sony"));
        });
    }
}
