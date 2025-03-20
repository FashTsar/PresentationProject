public class PageProductCard {
    /*
    ---------- ЭЛЕМЕНТЫ НА СТРАНИЦЕ ----------
     */

    public String getProductTable() {
        return "//div[@id='tbodyid']";
    }

    public String getProductImg() {
        return "//div[@id='imgp']//img";
    }

    public String getProductTitle() {
        return getProductTable()+"/h2[@class='name']";
    }

    public String getProductPrice() {
        return getProductTable()+"/h3[@class='price-container']";
    }

    public String getProductDescription() {
        return getProductTable()+"//div[@id='myTabContent']//p";
    }

    public String getButtonAddToCart() {
        return "//a[text()='Add to cart']";
    }
}
