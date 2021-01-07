package lv.javaguru.app.core.services;

import lv.javaguru.app.database.Database;

public class ShowReservationsService {

    private final Database database;

    public ShowReservationsService(Database database) {
        this.database = database;
    }

    public void getAll() {
        System.out.println("Reservation list: ");

        database.getAll().entrySet().forEach(System.out::println);
    }
}
