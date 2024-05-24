package tests.ui.pageobjectstests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.ui.pageobjectstests.unitickets.UtMainPage;
import tests.ui.pageobjectstests.unitickets.UtSearchPage;

import java.util.List;

public class UtFilterTest extends BaseTest {

    @BeforeEach
    public void openSite() {
        driver.get("https://uniticket.ru/");
    }

    @Test
    public void test() {
        int expectedDayForward = 26;
        int expectedDayBack = 28;
        UtSearchPage searchPage = new UtMainPage(driver)
                .setCityFrom("Казань")
                .setCityTo("Дубай")
                .setDayForward(expectedDayForward)
                .setDayBack(expectedDayBack)
                .search();

        searchPage.waitForPage();
        searchPage.waitForTitleDisappear();

        int actualDayForward = searchPage.getMainDayForward();
        int actualDayBack = searchPage.getMainDayBack();
        Assertions.assertEquals(expectedDayForward, actualDayForward);
        Assertions.assertEquals(expectedDayBack, actualDayBack);

        List<Integer> daysForward = searchPage.getDaysForward();
        List<Integer> daysBack = searchPage.getDaysBack();

        boolean isAllDaysForwardOk = daysForward.stream().allMatch(x->x.equals(expectedDayForward));
        boolean isAllDaysBackOk = daysForward.stream().allMatch(x->x.equals(expectedDayBack));

        Assertions.assertTrue(isAllDaysForwardOk);
        Assertions.assertTrue(isAllDaysBackOk);
    }
}
