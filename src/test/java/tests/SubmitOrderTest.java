package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.*;
import testcomponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getSubmitOrderData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> testData) throws IOException {
        ProductCatalogPage productCatalog = landingPage.loginApplication(testData.get("email"), testData.get("password"));
        productCatalog.addProductToCart(testData.get("productName"));

        CartPage cartPage = productCatalog.getHeader().goToCartPage();
        Boolean match = cartPage.verifyProductDisplayed(testData.get("productName"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCoutry(testData.get("countryName"));
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        Assert.assertTrue(confirmationPage.successMessageDisplay());
        confirmationPage.logout();
    }

    @Test(dependsOnMethods = "submitOrder", dataProvider = "getOrderHistoryData")
    public void orderHistory(HashMap<String,String> testData) {
        ProductCatalogPage productCatalog = landingPage.loginApplication(testData.get("email"), testData.get("password"));
        OrdersPage ordersPage = productCatalog.getHeader().goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyProductInOrderDisplay(testData.get("productName")));
        ordersPage.logout();
    }

    @DataProvider
    public Object[][] getSubmitOrderData() throws IOException {
        List<HashMap<String,String>> testData = getJsonDataToMap("//src//test//java//data//purchaseOrder.json");
        return new Object[][] {{testData.get(0)}, {testData.get(1)}};
    }

    @DataProvider
    public Object[][] getOrderHistoryData() {
        HashMap<String,String> testData = new HashMap<String, String>();
        testData.put("email", "yibiv465523@chosenx.com");
        testData.put("password", "cg6G@bficYJi6U4");
        testData.put("productName", "QWERTY");
        return new Object[][]{{testData}};
    }
}
