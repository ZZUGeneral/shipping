package com.cit.its.shipping.front.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cit.its.shipping.front.FrontApplicationTests;
import com.cit.its.shipping.front.entity.Client;
import com.cit.its.shipping.front.service.ClientService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientServiceImplTest extends FrontApplicationTests {

    @Autowired
    ClientService service;

    @Test
    public void clientList() {

        Client client = new Client();
        client.setValid(true);
        client.setName("on");
        IPage<Client> clientPage = service.clientPageList(client, 0, 10);
        List<Client> content = clientPage.getRecords();
        content.forEach(System.out::println);
    }

    @Test
    public void sensorList() {
        List<Client> clients = service.sensorList();
        clients.forEach(System.out::println);
    }
}