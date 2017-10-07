<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>role add</title>
    <%@include file="/common/header.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.0.js"></script>
	<script type="text/javascript">
	//提交表单
   	function doSubmit(){
   		var name = $("#name");
   		if(name.val() == ""){
   			alert("角色名不能为空！");
   			name.focus();
   			return false;
   		}
   		var state = $("#state");
   		if(state.val() == ""){
   			alert("状态不能为空！");
   			state.focus();
   			return false;
   		}
   		//提交表单
   		document.forms[0].submit();
   	}
	</script>
  </head>
  
<body class="rightBody">
<form:form commandName="role" action="addrole" method="post" >

     <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>角色管理</strong>&nbsp;-&nbsp;新增角色</div></div>
    <div class="tableH2">新增角色</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">角色名称：</td>
            <td><form:input path="name" id="name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色权限：</td>
            <td>
                <input type="checkbox" value="xzgl" name="privilegeId">行政管理
                <input type="checkbox" value="hqfw" name="privilegeId">后勤服务
                <input type="checkbox" value="spaces" name="privilegeId">我的空间
                <input type="checkbox" value="zxxx" name="privilegeId">在线学习
                <input type="checkbox" value="nsfw" name="privilegeId">纳税服务
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">状态：</td>
            <td>有效:<input id="state" type="radio" name="state" value="1"/>无效:<input type="radio" name="state" value="0" /> </td>
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
