<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]><html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]><html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]><html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]><html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息授权系统-首页</title>

<%@include file="/jsp/include.jsp"%>

<!-- Include all needed stylesheets and scripts here -->





<!--[if lt IE 9]><script src="js/respond.min.js"></script><![endif]-->
<!--[if gte IE 9]>
<style type="text/css">
    .gradient {filter: none !important;}
</style>
<![endif]-->
</head>

<body>
    <div class="body_wrap">
        <div class="container">

            <!-- content -->
    		<div class="content " role="main">
				<!-- row -->
		        <div class="row">
		            <div class="col-xs-10 col-sm-4 col-sm-offset-4">
		            	<div style="height:200px">
		            	</div>
		            </div>
		        </div>
				<!-- row -->
		        <div class="row">
		            <div class="col-xs-10 col-sm-4 col-sm-offset-4">
		            	<!-- login widget -->
		                <div class="widget-container widget_login styled boxed boxed-cream">
		                    <div class="inner">
		                        <form action="#" method="post" class="loginform">
		                            <div class="field_text">
		                                <label for="user_login" class="label_title">Email</label>
		                                <input type="text" name="log" id="user_login" value="" placeholder="Email" />
		                                <span class="input_icon input_email"></span>
		                            </div>
		                            <div class="field_text">
		                                <label for="user_pass" class="label_title">Password</label>
		                                <input type="password" name="pwd" id="user_pass" value="" placeholder="Password" />
		                                <span class="input_icon input_pass"></span>
		                            </div>
		                            <div class="rowRemember clearfix">
		                                <div class="forgetmenot input_styled checklist">
		                                    <div class="rowCheckbox checkbox-middle"><input type="checkbox" name="remember" id="remember" value="3" checked><label for="remember">Remember&nbsp;me?</label></div>
		                                </div>
		                                <span class="forget_password"><a href="#">Forgot Password?</a></span>
		                                <input type="hidden" name="redirect_to" value="">
		                                <input type="hidden" name="testcookie" value="1">
		                            </div>
		                            <div class="rowSubmit">
		                                <span class="btn btn-hover"><input type="submit" name="login-submit" id="login-submit" value="Subscribe Now" /></span>
		                            </div>
		                        </form>
		                    </div>
		                </div>
		                <!--/ login widget -->
		            </div>
				</div>		
			</div>
        </div>
        <!--/ container -->
    </div>
</body>

</html>