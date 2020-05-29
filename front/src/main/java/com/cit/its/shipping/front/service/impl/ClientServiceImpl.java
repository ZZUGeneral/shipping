package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.enums.ClientTypeEnum;
import com.cit.its.shipping.front.dao.ClientMapper;
import com.cit.its.shipping.front.entity.Client;
import com.cit.its.shipping.front.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<Client> sensorList() {
        LambdaQueryWrapper<Client> queryWrapper = new QueryWrapper<Client>()
                .lambda()
                .eq(Client::getValid, true)
                .gt(Client::getType, ClientTypeEnum.CODE_CLIENT.getCode())
                .orderByDesc(true, Client::getState);
        return this.list(queryWrapper);
    }

    @Override
    public void updateClientState(String topic, String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);
        String clientId = jsonObject.get("clientid").toString();
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", clientId);
        System.out.println("*******************" + topic);
        System.out.println("************************************************************" + StrUtil.endWith(topic, "disconnected"));
        Client client = new Client();
        if (StrUtil.endWith(topic, "disconnected")) {
            System.out.println("------------------------------------------------------------");
            client.setState(0);
            Long time = Long.parseLong(jsonObject.get("disconnected_at").toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time * 1000);
            Date date = calendar.getTime();
            client.setOfflineAt(date);
        } else {
            System.out.println("==============================================================");
            client.setState(1);
            Long time = Long.parseLong(jsonObject.get("connected_at").toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time * 1000);
            Date date = calendar.getTime();
            System.out.println("===============" + time + "==============" + date.toString());
            client.setOnlineAt(date);
        }
        clientMapper.update(client, queryWrapper);
    }


    @Override
    public IPage<Client> clientPageList(Client client, Integer currentPage, Integer pageSize) {
        Page page = new Page(currentPage, pageSize);
        LambdaQueryWrapper<Client> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(client.getName())) {
            queryWrapper.like(Client::getName, client.getName());
        }
        if (StrUtil.isNotEmpty(client.getClientId())) {
            queryWrapper.like(Client::getClientId, client.getClientId());
        }
        if (ObjectUtil.isNotNull(client.getType())) {
            queryWrapper.eq(Client::getType, client.getType());
        } else {
            queryWrapper.gt(Client::getType, ClientTypeEnum.CODE_CLIENT);
        }
        if (ObjectUtil.isNotNull(client.getState())) {
            queryWrapper.eq(Client::getState, client.getState());
        }
        queryWrapper.eq(Client::getValid, true);
        queryWrapper.orderByDesc(Client::getState);
        return clientMapper.selectPage(page, queryWrapper);
    }

}
