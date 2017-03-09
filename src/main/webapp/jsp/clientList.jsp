<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<script type="text/javascript">
	var params = {
		pageSize:5,
		isPage:true,
		url:"getPage.do?" + Math.floor(Math.random() * (100000 + 1))
	};
	var dataList = new Container(true,"userList");
	var contendLink = function(data){
		return data.passId + "_";
	}
	var heads=[];
	heads[0] = {"code":"passId",formatter:contendLink,classes:"c"};
	heads[1] = {"code":"username",classes:"c"};
	
	dataList.error = function(XMLHttpRequest, textStatus, errorThrown){
    	console.log(textStatus);
	}

	$(function(){
		dataList.showInfo(heads,params);
	});
</script>

<div style="display:block;">
	<table class="" cellspacing="0" id="userList"> 
		<thead>
			<tr> 
				<th>passId</th>
				<th>username</th>
			</tr> 
		</thead> 
		<tbody>
			
		</tbody> 
	</table>
	<div style="float:right;">
		<ul id="staticPage"></ul>
	</div>
</div>
