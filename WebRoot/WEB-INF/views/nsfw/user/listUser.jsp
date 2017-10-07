<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户管理</title>
     <%@include file="/common/header.jsp" %>
     <script type="text/javascript">
         //全选、全反选
		 function doSelectAll(){
			 $("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
		 };
         //添加
         function doAdd() {
         	var url = "${pageContext.request.contextPath}/addUser_";
         	window.location.href = url;
		 };
         //Excel导出
         function doExportExcel() {
        	 var url = "${pageContext.request.contextPath}/addExportExcel";
        	 window.open(url);
		};
         //Excel导入
        function doImportExcel() {
		};
     </script>
  </head>
  
  <body class="rightBody">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>用户管理</strong></div> </div>
                <div class="search_art">
                    <li>
                                                            用户名:<input type="text" name="" class="s_text" id="userName" style="width:160px;">
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <form:form method="POST" action="doImportExcel" enctype="multipart/form-data" >
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                        <input type="button" value="导出" class="s_button" onclick="doExportExcel()"/>&nbsp;
                    	<input name="fileUser" type="file"/>
                        <input type="submit" value="导入" class="s_button" />&nbsp;
                    </li>
                    </form:form>
                </div>
                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td width="140" align="center">用户名</td>
                            <td width="140" align="center">帐号</td>
                            <td width="160" align="center">所属部门</td>
                            <td width="80" align="center">性别</td>
                            <td align="center">电子邮箱</td>
                            <td width="100" align="center">操作</td>
                        </tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td align="center"><input type="checkbox" name="selectedRow" value="${user.id}" /></td>
                                <td align="center">${user.name}</td>
                                <td align="center">${user.account}</td>
                                <td align="center">${user.dept}</td>
                                <td align="center">${user.gender?'男':'女'}</td>
                                <td align="center">${user.email}</td>
                                <td align="center">
                                    <a href="doEdit?id=${user.id}">编辑</a>
                                    <a href="doRemove?id=${user.id}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        <div class="c_pate" style="margin-top: 5px;">
		<table width="100%" class="pageDown" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td align="right">
                 	总共1条记录，当前第 1 页，共 1 页 &nbsp;&nbsp;
                            <a href="#">上一页</a>&nbsp;&nbsp;<a href="#">下一页</a>
					到&nbsp;<input type="text" style="width: 30px;" onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
					max="" value="1" /> &nbsp;&nbsp;
			    </td>
			</tr>
		</table>	
        </div>
        </div>
    </div>

</body>
</html>
