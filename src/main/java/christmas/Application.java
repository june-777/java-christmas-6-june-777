package christmas;

import christmas.configuration.ApplicationConfiguration;
import christmas.controller.FrontController;

public class Application {
    public static void main(String[] args) {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        FrontController frontController = applicationConfiguration.frontController();
        frontController.run();
    }
}
