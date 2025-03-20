public class ModalLogIn {
    /*
    ---------- ЭЛЕМЕНТЫ НА СТРАНИЦЕ ----------
     */

    public String getBlockModal() {
        return "//div[@id='logInModal']";
    }

    public String getInputUsername() {
        return getBlockModal()+"//input[@id='loginusername']";
    }

    public String getInputPassword() {
        return getBlockModal()+"//input[@id='loginpassword']";
    }

    public String getButtonLogIn() {
        return getBlockModal()+"//button[text()='Log in']";
    }
}
