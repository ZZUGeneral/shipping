package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.entity.Client;

import java.util.List;

public interface ClientService extends IService<Client> {

    IPage<Client> clientPageList(Client client, Integer page, Integer size);

    List<Client> sensorList();

}
