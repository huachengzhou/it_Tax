<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/common/header.jsp"%>
    <title>编辑用户</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.0.js"></script>
  </head>
  
	<script type="text/javascript">
		//提交表单
		function doSubmit(){
			var name = $("#name");
			if(name.val() == ""){
				alert("用户名不能为空！");
				name.focus();
				return false;
			}
			var password = $("#password");
			if(password.val() == ""){
				alert("密码不能为空！");
				password.focus();
				return false;
			}
			//帐号校验
	    	//提交表单
			document.forms[0].submit();
		};
		/*未实现*/
		function doVerify() {
			$.ajax({
				url:"${pageContext.request.contextPath}/user_verifyAccount",
				data: {"account": $("#account").val(),"id":${user.id}},
				type: "post",
				success: function(msg){
					if("true" != msg){
						//帐号已经存在
						alert("帐号已经存在。请使用其它帐号！");
						//定焦
						$("#account").focus();
					} 
				}
			});
		}
	</script>
 <body class="rightBody">
 <form:form commandName="user" id="form" name="userForm" action="updateUser" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;编辑用户</div></div>
    <div class="tableH2">编辑用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><form:select path="dept" items="${depts}"></form:select></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td><img alt="图片崩溃" src="${imgSrc}" width="100" height="100">
                <input type="file" name="file"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><input type="text" id="name" name="name" value="${user.name}"> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td>
            <input type="text" id="account" name="account" value="${user.account}" onchange="doVerify()">
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><input type="password" id="password" name="password" value="${user.password}"></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td>
            ${user.gender=='true'?'男':'女'}<input type="radio" name="gender" value="${user.gender}" checked="checked">
			${user.gender=='true'?'女':'男'}<input type="radio" name="gender" value="${user.gender=='true'?'女':'男'}">
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td>
            	 <c:forEach items="${list}" var="userRole"><!-- 默认选择 -->
            		${userRole.id.role.name}<input type="checkbox" value="${ruserRole.id.role.roleId}" name="roleId" checked="checked">
            	</c:forEach>
            	 <c:forEach items="${list2}" var="userRole"><!-- 剩余角色 -->
            		${userRole.id.role.name}<input type="checkbox" value="${ruserRole.id.role.roleId}" name="roleId">
            	</c:forEach>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><input type="text" name="email" value="${user.email}"></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><input type="text" name="mobile" value="${user.mobile}"></td>
        </tr>        
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <td>
            <input class="Wdate" name="birthday" type="text" value="${user.birthday}" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
            </td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td>
            ${user.state=='1'?'有效':'无效'}<input type="radio" name="state" value="${user.state}" checked="checked">
			${user.state=='0'?'有效':'无效'}<input type="radio" name="state" value="${user.state=='0'?'0':'1'}">
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><textarea rows="15" cols="73" name="memo">${user.memo}</textarea>
        </tr>
    </table>
    <input type="hidden" name="id" value="${user.id}">
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form:form>
</body>
</html>
