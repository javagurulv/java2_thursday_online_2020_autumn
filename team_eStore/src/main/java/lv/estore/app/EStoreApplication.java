package lv.estore.app;

import lv.estore.app.dependency_injection.ApplicationContext;
import lv.estore.app.dependency_injection.DIApplicationContextBuilder;
import lv.estore.app.userInterface.ApplicationUI;

public class EStoreApplication {

    public static void main(String[] args) {
        private static ApplicationContext applicationContext =
                new DIApplicationContextBuilder().build("lv.estore.app");
        ApplicationUI applicationUI = new ApplicationUI();
        applicationUI.execute();
    }
}