package team_VK.application.database;

import team_VK.application.core.domain.Client;

import java.util.List;

public interface ClientsRepository {

    void addClient(Client client);
    void deleteClient(Client client);
    List<Client> getListClients();

}
