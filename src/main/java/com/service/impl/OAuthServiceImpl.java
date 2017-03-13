/**
 * 
 */
package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.model.Client;
import com.service.ClientService;
import com.service.OAuthService;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月13日
 */
@Service
public class OAuthServiceImpl implements OAuthService{

	private Cache cache;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
    public OAuthServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }
	
	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public void addAuthCode(String authCode, String username) {
		// TODO Auto-generated method stub
		cache.put(authCode, username);
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public void addAccessToken(String accessToken, String username) {
		// TODO Auto-generated method stub
		cache.put(accessToken, username);
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public boolean checkAuthCode(String authCode) {
		// TODO Auto-generated method stub
		return cache.get(authCode) != null;
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public boolean checkAccessToken(String accessToken) {
		// TODO Auto-generated method stub
		return cache.get(accessToken) != null;
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public String getUsernameByAuthCode(String authCode) {
		// TODO Auto-generated method stub
		return (String)cache.get(authCode).get();
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public String getUsernameByAccessToken(String accessToken) {
		// TODO Auto-generated method stub
		return (String)cache.get(accessToken).get();
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public long getExpireIn() {
		// TODO Auto-generated method stub
		return 3600L;
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public boolean checkClientId(String clientId) {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.setClientId(clientId);
		return clientService.getClient(client) != null;
	}

	/**
	 * @desc : TODO
	 * @date : 2017年3月13日
	 */
	@Override
	public boolean checkClientSecret(String clientSecret) {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.setClientSecret(clientSecret);;
		return clientService.getClient(client) != null;
	}

}
