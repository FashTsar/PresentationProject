public class PageCart {

    /*
    ---------- ЭЛЕМЕНТЫ НА СТРАНИЦЕ ----------
     */

    public String getProductImg(int numberProduct) {
        return "//tbody/tr["+numberProduct+"]/td[1]/img";
    }

    public String getProductTitle(int numberProduct) {
        return "//tbody/tr["+numberProduct+"]/td[2]";
    }

    public String getProductPrice(int numberProduct) {
        return "//tbody/tr["+numberProduct+"]/td[3]";
    }

    public String getButtonDeleteProduct(int numberProduct) {
        return "//tbody/tr["+numberProduct+"]/td[4]/a[text()='Delete']";
    }

    public String getButtonPlaceOrder() {
        return "//button[text()='Place Order']";
    }
}
