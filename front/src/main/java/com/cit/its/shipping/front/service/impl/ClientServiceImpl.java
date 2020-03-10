package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
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
