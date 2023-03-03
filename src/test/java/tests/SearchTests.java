package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Test
    void completeOnboardingTest() {
        step("Check main screen text", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia\n" +
                    "…in over 300 languages"));
        });
        step("Go to New ways to explore screen", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));

        });
        step("Go to  Reading lists with sync screen", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
        });
        step("Go to Send anonymous data screen", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Send anonymous data"));
            $(id("org.wikipedia.alpha:id/rejectButton")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/acceptButton")).shouldBe(visible);
        });
        step("Ending onboarding", () -> {
            $(id("org.wikipedia.alpha:id/rejectButton")).click();
            $(accessibilityId("Search Wikipedia")).shouldBe(visible);
        });
    }


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
}
