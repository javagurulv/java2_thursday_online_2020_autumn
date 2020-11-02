package store;

import store.mockSpringClasses.ThymeLeafMock;

//@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ThymeLeafMock userInterfaceMock = new ThymeLeafMock();
        userInterfaceMock.run();
    }

}
