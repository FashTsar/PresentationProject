public class PageCatalog {
    /*
    ---------- ЭЛЕМЕНТЫ НА СТРАНИЦЕ ----------
     */

    public String getProductsTable() {
        return "//div[@id='tbodyid']";
    }

    public String getProductImg(int numberCard) {
        return getProductsTable()+"/div["+numberCard+"]//img";
    }

    public String getProductTitle(int numberCard) {
        return getProductsTable()+"/div["+numberCard+"]//h4[@class='card-title']/a";
    }

    public String getProductPrice(int numberCard) {
        return getProductsTable()+"/div["+numberCard+"]//h5";
    }

    public String getProductDescription(int numberCard) {
        return getProductsTable()+"/div["+numberCard+"]//p[@id='article']";
    }
}
