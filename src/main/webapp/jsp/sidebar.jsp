<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<div id="sidebar-nav">
	<ul id="dashboard-menu">
		<li class="active">
			<div class="pointer">
				<div class="arrow"></div>
				<div class="arrow_border"></div>
			</div> <a href="index.do"> <i class="icon-home"></i> <span>首页</span>
		</a>
		</li>
		<li>
			<a class="dropdown-toggle" href="#"> 
				<i class="icon-group"></i> <span>账户</span> <i class="icon-chevron-down"></i>
			</a>
			<ul class="submenu">
				<li><a href="personalInfo.do">账户资料</a></li>
			</ul>
		</li>

		<li>
			<a href="client.do"> 
				<i class="icon-calendar-empty"></i>
				<span>授权管理</span>
			</a>
		</li>
	</ul>
</div>