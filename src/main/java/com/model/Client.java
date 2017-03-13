/**
 * 
 */
package com.model;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月7日
 */
public class Client extends BaseEntiy{

	private static final long serialVersionUID = 4067418994149624981L;
	
	private int id;
	private String clientName;
	private String clientId;
	private String clientSecret;
	/**
	 * 
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * 
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * 
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * 
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * 
	 */
	public String getClientSecret() {
		return clientSecret;
	}
	/**
	 * 
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
}
