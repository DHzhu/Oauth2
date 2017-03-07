/**
 * 
 */
package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.Client;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月7日
 */
public interface ClientDao extends SqlMapper{
	public List<Client> getClients(Client client);
	public void addClient(Client client);
	public void delClient(@Param("id") int id);
	public void updateClient(Client client);
}
