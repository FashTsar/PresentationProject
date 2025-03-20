import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static io.qameta.allure.Allure.step;

public class CartApiTests {
    public ApiCart apiCart;
    public ApiCatalog apiCatalog;
    public ActionsForReportAfterStep actionsForReportAfterStep;
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        apiCart = new ApiCart();
        apiCatalog = new ApiCatalog();
        actionsForReportAfterStep = new ActionsForReportAfterStep(driver);

        apiCart.clearCart();
    }

    @AfterEach
    public void tearDown() {
        apiCart.clearCart();
    }

    @Epic("Корзина")
    @Feature("Проверка добавление товара в корзину")
    @Test
    public void checkAddToCartProductApi(){
        boolean result = false;

        // 1. получаем список продуктов и их id'шников и выбираем рандомный товар для теста
        step("1. получаем список продуктов и их id'шников");
        String dataProducts = apiCatalog.getProducts();
        JSONArray idsProducts = com.jayway.jsonpath.JsonPath.parse(dataProducts)
                .read("$.Items[*].id");
        int count = idsProducts.size()-1;
        int randomProduct = (int) (Math.random() * count);
        int idProduct = com.jayway.jsonpath.JsonPath.parse(dataProducts)
                .read("$.Items["+randomProduct+"].id");
        actionsForReportAfterStep.reportAfterStepForApiTests("checkAddToCartProductApi", "1");

        // 2. добавляем товар в корзину
        step("2. добавляем товар в корзину");
        apiCart.addProductCart(idProduct);
        actionsForReportAfterStep.reportAfterStepForApiTests("checkAddToCartProductApi", "2");

        // 3. проверяем что товар в корзине
        step("3. проверяем что товар в корзине");
        String dataProductsInCart = apiCart.getDataCart();
        int idProductCart = com.jayway.jsonpath.JsonPath.parse(dataProductsInCart)
                .read("$.Items[0].prod_id");
        if (idProductCart == idProduct) result = true;
        actionsForReportAfterStep.reportAfterStepForApiTests("checkAddToCartProductApi", "3", result);

        Assertions.assertTrue(result, "Тест провалился");
    }
}
