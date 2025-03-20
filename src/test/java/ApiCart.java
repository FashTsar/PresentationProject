import net.minidev.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

public class ApiCart {
    private DataProperties dataProperties;
    private ApiTemplates apiTemplates;
    private ApiAuth apiAuth;

    // тело запроса для добавления содержимого в корзину
    public String bodyAddProductCart(int prodId) {
        apiAuth = new ApiAuth();

        UUID uuid = UUID.randomUUID();

        JSONObject body = new JSONObject()
                .put("id", uuid)
                .put("prod_id", prodId)
                .put("cookie", apiAuth.auth())
                .put("flag", true);
        return body.toString();
    }

    // тело запроса для просмотра содержимого корзины
    public String bodyDataCart() {
        apiAuth = new ApiAuth();

        JSONObject body = new JSONObject()
                .put("cookie", apiAuth.auth())
                .put("flag", true);
        return body.toString();
    }

    // тело запроса для удаления товара из корзины
    public String bodyDeleteProductCart(String id) {
        JSONObject body = new JSONObject()
                .put("id", id);
        return body.toString();
    }

    /*
    --------------------------------------- ЗАПРОСЫ ---------------------------------------
     */

    // получаем данные из корзины
    public void addProductCart(int prodId) {
        apiTemplates = new ApiTemplates();
        dataProperties = new DataProperties();

        String body = bodyAddProductCart(prodId);

        apiTemplates.postStatusCode200(dataProperties.getDataProperties("pens.properties", "addtocart"), body); // делаем запрос
    }

    // получаем данные из корзины
    public String getDataCart() {
        apiTemplates = new ApiTemplates();
        dataProperties = new DataProperties();

        String body = bodyDataCart();

        return apiTemplates.postBodyString(dataProperties.getDataProperties("pens.properties", "viewcart"), body); // делаем запрос
    }

    // удаляем товар из корзины
    public void deleteProductCart(String id) {
        apiTemplates = new ApiTemplates();
        dataProperties = new DataProperties();

        String body = bodyDeleteProductCart(id);

        apiTemplates.postStatusCode200(dataProperties.getDataProperties("pens.properties", "deleteitem"), body); // делаем запрос
    }

    /*
    --------------------------------------- ДЕЙСТВИЯ ---------------------------------------
     */

    public void clearCart() {
        String dataProductsInCart = getDataCart();

        JSONArray idsProductsInCart = com.jayway.jsonpath.JsonPath.parse(dataProductsInCart)
                .read("$.Items[*].id");

        for (Object o : idsProductsInCart) {
            String idProductsInCart = o.toString();
            deleteProductCart(idProductsInCart);
        }
        System.out.println("Очистка корзины прошла успешно.");
    }
}
