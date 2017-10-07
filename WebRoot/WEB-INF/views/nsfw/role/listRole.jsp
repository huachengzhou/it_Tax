<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>角色管理</title>
    <script type="text/javascript">
  //全选、全反选
	 function doSelectAll(){
		 $("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
	 };
    //添加
    function doAdd() {
    	var url = "${pageContext.request.contextPath}/addRole_";
    	window.location.href = url;
	 };
    
  	

    </script>
</head>
<body class="rightBody">
 <form:form method="POST" action="doImportExcel"  >
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>角色管理 </strong></div> </div>
                <div class="search_art">
                <ul>
                    <li>
                                                             角色名称： <textarea rows="2" cols="60" id="roleName" style="width: 160px;" class="s_text">${role.name}</textarea>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                    </li>
                 </ul>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td width="120" align="center">角色名称</td>
                            <td align="center">权限</td>
                            <td width="80" align="center">状态</td>
                            <td width="120" align="center">操作</td>
                        </tr>
                        	<c:forEach items="${roleNs}" var="roleN">
                            <tr >
                                <td align="center"><input type="checkbox" name="selectedRow" value=""/></td>
                                <td align="center">${roleN.role.name}</td>
                                <td align="center">
                                	<c:forEach items="${roleN.rolePrivilegeN}" var="rolePrivilegeN">
                                		<span>${rolePrivilegeN.codeN}</span>
                                	</c:forEach>
                                </td>
                                <td align="center">${roleN.role.state==1?'有效':'无效'}</td>
                                <td align="center">
                                    <a href="doRoleEdit?roleId=${roleN.role.roleId}">编辑</a>
                                    <a href="doRoleDelete?roleId=${roleN.role.roleId}">删除</a>
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
</form:form>

</body>
</html>