/**
 * 
 */
package com.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ClientDao;
import com.github.pagehelper.PageHelper;
import com.model.Client;
import com.service.ClientService;
import com.utils.BeanUtil;
import com.utils.PageResult;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月7日
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientDao clientDao;
	
	/**
	 * @desc : TODO
	 * @date : 2017年3月7日
	 */
	@Override
	public PageResult<Client> getClients(PageResult<Client> pageResult, Client client) {
		// TODO Auto-generated method stub
		if(pageResult.getIsPage() == true){
			PageHelper.startPage(pageResult.getPageNo(), pageResult.getPageSize());		
		}
		BeanUtil.toPagedResult(clientDao.getClients(client), pageResult);
		return pageResult;
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月7日
	 */
	@Override
	public void addClient(Client client) {
		// TODO Auto-generated method stub
		client.setClientId(UUID.randomUUID().toString());
		client.setClientSecret(UUID.randomUUID().toString());
		clientDao.addClient(client);
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月7日
	 */
	@Override
	public void delClient(int id) {
		// TODO Auto-generated method stub
		clientDao.delClient(id);
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月7日
	 */
	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
		clientDao.updateClient(client);
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public Client getClient(Client client) {
		// TODO Auto-generated method stub
		List<Client> clients = clientDao.getClients(client);
		if(clients == null || clients.size() < 1){
			return null;
		}
		return clients.get(0);
	}
}
