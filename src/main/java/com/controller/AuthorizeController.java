/**
 * 
 */
package com.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Client;
import com.model.User;
import com.service.ClientService;
import com.service.OAuthService;
import com.utils.StringHelper;

/**
 * @desc : TODO
 * @author: Zhu
 * @date : 2017年3月13日
 */
@Controller
public class AuthorizeController {

	public static final String INVALID_CLIENT_DESCRIPTION = "客户端验证失败，如错误的client_id/client_secret。";

	@Autowired
	private OAuthService oAuthService;

	@Autowired
	private ClientService clientService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/authorize.do")
	public Object authorize(Model model, HttpServletRequest request) throws URISyntaxException, OAuthSystemException {

		try {

			// 构建OAuth 授权请求
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

			// 检查传入的客户端id是否正确
			if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
				OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_CLIENT)
						.setErrorDescription(INVALID_CLIENT_DESCRIPTION).buildJSONMessage();
				return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
			}

			Subject subject = SecurityUtils.getSubject();
			// 如果用户没有登录，跳转到登陆页面
			if (!subject.isAuthenticated()) {
				Client client = new Client();
				client.setClientId(oauthRequest.getClientId());
				Client clientR = clientService.getClient(client);

				String userName = request.getParameter("userName");
				String password = request.getParameter("password");

				if (StringHelper.isEmpty(userName) || StringHelper.isEmpty(password)) {
					model.addAttribute("client", clientR);
					model.addAttribute("redirecUri", oauthRequest.getRedirectURI());
					model.addAttribute("responseType", oauthRequest.getResponseType());
					model.addAttribute("scope", oauthRequest.getScopes());
					return "oauthLogin";
				}

				UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
				boolean flag = false;
				try {
					subject.login(token);
					if (subject.isAuthenticated()) {
						flag = true;
					}
				} catch (UnknownAccountException e) {
					model.addAttribute("result", "帐号不存在");
					e.printStackTrace();
				} catch (IncorrectCredentialsException e) {
					model.addAttribute("result", "密码不正确");
					e.printStackTrace();
				} catch (Exception e) {
					model.addAttribute("result", "未知错误");
					e.printStackTrace();
				}

				if (!flag) {
					model.addAttribute("client", clientR);
					model.addAttribute("redirecUri", oauthRequest.getRedirectURI());
					model.addAttribute("responseType", oauthRequest.getResponseType());
					model.addAttribute("scope", oauthRequest.getScopes());
					return "oauthLogin";
				}
			}

			User user = (User) subject.getPrincipal();

			// 生成授权码
			String authorizationCode = null;
			// responseType目前仅支持CODE，另外还有TOKEN
			String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
			if (responseType.equals(ResponseType.CODE.toString())) {
				OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
				authorizationCode = oauthIssuerImpl.authorizationCode();
				oAuthService.addAuthCode(authorizationCode, user.getUserName());
			}

			// 进行OAuth响应构建
			OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request,
					HttpServletResponse.SC_FOUND);
			// 设置授权码
			builder.setCode(authorizationCode);
			// 得到到客户端重定向地址
			String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

			// 构建响应
			final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();

			// 根据OAuthResponse返回ResponseEntity响应
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(new URI(response.getLocationUri()));
			return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));

		} catch (OAuthProblemException e) {
			// 出错处理
			String redirectUri = e.getRedirectUri();
			if (OAuthUtils.isEmpty(redirectUri)) {
				// 告诉客户端没有传入redirectUri直接报错
				return new ResponseEntity("OAuth callback url needs to be provided by client!!!", HttpStatus.NOT_FOUND);
			}

			// 返回错误消息（如?error=）
			final OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(e)
					.location(redirectUri).buildQueryMessage();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(new URI(response.getLocationUri()));
			return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
		}
	}
}
