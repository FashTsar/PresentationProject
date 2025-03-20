public class ApiCatalog {
    private DataProperties dataProperties;
    private ApiTemplates apiTemplates;

    /*
    --------------------------------------- ЗАПРОСЫ ---------------------------------------
     */

    // получаем данные из корзины
    public String getProducts() {
        apiTemplates = new ApiTemplates();
        dataProperties = new DataProperties();

        return apiTemplates.getBodyString(dataProperties.getDataProperties("pens.properties", "entries")); // делаем запрос
    }
}
