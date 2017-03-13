<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<script type="text/javascript">
	var params = {
		pageSize:1,
		isPage:true
	};
	var url = "getClients.do";
	var dataList = new Container(true,"userList");
	var contendLink = function(data){
		return '<i class="icon-edit"></i>' + data.id;
	}
	var heads=[];
	heads[0] = {"code":"clientName",classes:"center"};
	heads[1] = {"code":"clientId",classes:"center"};
	heads[2] = {"code":"clientKey",classes:"center"};
	heads[3] = {"code":"id",formatter:contendLink,classes:"center"};
	
	dataList.error = function(XMLHttpRequest, textStatus, errorThrown){
    	console.log(textStatus);
	}

	$(function(){
		dataList.showInfo(heads,params,url);
	});
</script>

<div id="pad-wrapper">
	<div class="row">
		<div class="col-md-12">
			<div style="display:block;">
				<table class="table table-hover table-striped" cellspacing="0" id="userList"> 
					<thead>
						<tr> 
							<th>客户端名称</th>
							<th>客户端ID</th>
							<th>客户端KEY</th>
							<th>操作</th>
						</tr> 
					</thead> 
					<tbody>
						
					</tbody> 
				</table>
				<div  style="float:right;">
					<ul id="staticPage"></ul>
				</div>
			</div>
		</div>
	</div>
</div>
