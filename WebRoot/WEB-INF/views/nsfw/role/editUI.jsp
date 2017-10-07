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
	    	//提交表单
			document.forms[0].submit();
		};
	</script>
 <body class="rightBody">
 <form:form commandName="role" id="form" name="roleForm" action="updateRole" method="post" enctype="multipart/form-data">
   <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>角色管理</strong>&nbsp;-&nbsp;编辑角色</div></div>
    <div class="tableH2">编辑角色</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">角色名称：</td>
            <td><input type="text" id="name" name="name" value="${role.name}"> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色权限：</td>
            <td>
            	<c:forEach items="${rolePrivilegeNs}" var="rolePrivilegeN">
            		<!-- 默认选中 -->
            		${rolePrivilegeN.codeN}<input type="checkbox" value="${rolePrivilegeN.codeW}" checked="checked" name="privilegeId">
            	</c:forEach>
            	<!-- 剩余添加项 -->
            	<c:forEach items="${iPrivilegeNs}" var="rolePrivilegeN">
            		${rolePrivilegeN.codeN}<input type="checkbox" value="${rolePrivilegeN.codeW}"  name="privilegeId">
            	</c:forEach>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">状态：</td>
            <td>
            ${role.state=='1'?'有效':'无效'}<input type="radio" name="state" value="${role.state=='1'?'有效':'无效'}" checked="checked"> 
            ${role.state=='0'?'有效':'无效'}<input type="radio" name="state" value="${role.state=='0'?'0':'1'}">
            </td>
        </tr>
    </table>
    <input type="hidden" name="roleId" value="${role.roleId}">
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form:form>
</body>
</html>
