<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>信息发布管理</title>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/lang/zh-cn/zh-cn.js"></script>

    <script>
   		window.UEDITOR_HOME_URL = "${basePath }js/ueditor/";
    	var ue = UE.getEditor('editor');
    </script>

</head>
<body class="rightBody">
<form:form enctype="multipart/form-data" method="post" action="updateInfo">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>信息发布管理</strong>&nbsp;-&nbsp;修改信息</div></div>
    <div class="tableH2">修改信息</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">信息分类：</td>
            <td>
             <select name="type">
	            <c:forEach items="${listInfo}" var="info_">
		            <c:choose>
			            <c:when test="${info_=='tzgg'}">
				            <c:if test="${info_== info.type}"><option selected="selected">通知公告</option></c:if>
				            <option>通知公告</option>
			            </c:when>
		            </c:choose>
		            
		            <c:choose>
			            <c:when test="${info_=='zcsd'}">
			             	<c:if test="${info_== info.type}"><option selected="selected">政策速递</option></c:if>
			           	 	<option>政策速递</option>
			            </c:when>
		            </c:choose>
		            
		            <c:choose>
			            <c:when test="${info_=='nszd'}">
			            <c:if test="${info_== info.type}"><option selected="selected">纳税指导</option></c:if>
			           	 <option>纳税指导</option>
			            </c:when>
		            </c:choose>
	            </c:forEach>
            </select>
            </td>
            <td class="tdBg" width="200px">来源：</td>
            <td><input type="text" name="source" value="${info.source}"> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">信息标题：</td>
            <td colspan="3"><textarea rows="1" cols="90" name="title">${info.title}</textarea> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">信息内容：</td>
            <td colspan="3"><textarea name="content" id="editor" style="width:90%;height:160px;">${info.content}</textarea></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td colspan="3"><textarea name="memo" rows="3" cols="90">${info.memo}</textarea></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">创建人：</td>
            <td>
            	${info.creator}
            	<input type="hidden" name="creator" value="${info.creator}">
            </td>
            <td class="tdBg" width="200px">创建时间：</td>
            <td>
            	<fmt:formatDate value="${info.createTime}"/>
            	<input type="hidden" name="createTime" value="${info.createTime}">
            </td>
        </tr>
    </table>
    <input type="hidden" name="infoId" value="${info.infoId}">
    <input type="hidden" name="state" value="${info.state}">
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form:form>
</body>
</html>