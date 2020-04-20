package be.service;

import be.entity.Client;

import java.util.ArrayList;
import java.util.List;

public interface ClientService {
    void save(Client client);
    void delete(Client client);
    List<Client> findAllClients();
}
