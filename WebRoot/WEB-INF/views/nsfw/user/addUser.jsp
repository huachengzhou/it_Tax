<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>User add</title>
    <%@include file="/common/header.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.0.js"></script>
	<script type="text/javascript">
	var vResult = false;
	//校验帐号唯一
	function doVerify(){
		//1、获取帐号
		var account = $("#account").val();
		if(account != ""){
			//2、校验 
			$.ajax({
				url:"${pageContext.request.contextPath}/user_verifyAccount",
				data: {"account": account},
				type: "post",
				async: false,//非异步
				success: function(msg){
					if("true" != msg){
						//帐号已经存在
						alert("帐号已经存在。请使用其它帐号！");
						//定焦
						$("#account").focus();
						vResult = false;
					} else {
						vResult = true;
					}
				}
			});
		}
	}
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
   		doVerify();
   		if(vResult){
    		//提交表单
    		document.forms[0].submit();
   		}
   	}
	</script>
  </head>
  
<body class="rightBody">
<form:form commandName="userN" id="form" action="addUser" method="post" enctype="multipart/form-data">

    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
    <div class="tableH2">新增用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><form:select path="dept" items="${depts}"></form:select></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <input type="file" name="file"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><form:input id="name" path="name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><form:input id="account" path="account" onchange="doVerify()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><form:password id="password" path="password"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td> 
            Male:<input type="radio" name="gender" value="true"/>Female:<input type="radio" name="gender" value="false" /> 
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td>
            <c:forEach items="${roles}" var="role">
            	${role.name}<input type="checkbox" value="${role.roleId}" name="roleId">
            </c:forEach>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><form:input path="mobile"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <td>
            <input class="Wdate" name="birthday" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td>
            有效:<input type="radio" name="state" value="1"/>无效:<input type="radio" name="state" value="0" /> 
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td ><textarea rows="10" cols="83" name="memo"></textarea></td>
        </tr>
    </table>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form:form>
</body>
</html>
