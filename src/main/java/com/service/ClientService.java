/**
 * 
 */
package com.service;

import com.model.Client;
import com.utils.PageResult;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月7日
 */
public interface ClientService {
	public PageResult<Client> getClients(PageResult<Client> pageResult, Client client);
	public void addClient(Client client);
	public void delClient(int id);
	public void updateClient(Client client);
}
