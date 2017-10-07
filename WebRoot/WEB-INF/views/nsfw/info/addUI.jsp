<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
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
    	var ue = UE.getEditor('editor',{ autoHeightEnabled: true,autoFloatEnabled: true});
    </script>
    <style type="text/css">
    .edui-popup-content.edui-default{ height: auto !important; }
    </style>
</head>
<body class="rightBody">
<form:form commandName="info" action="addInfo" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>信息发布管理</strong>&nbsp;-&nbsp;增加信息</div></div>
    <div class="tableH2">增加信息</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">信息分类：</td>
            <td>
            <select name="type">
	            <c:forEach items="${listInfo}" var="info_">
		            <c:choose>
			            <c:when test="${info_=='tzgg'}">
			           	 <option>通知公告</option>
			            </c:when>
		            </c:choose>
		            <c:choose>
			            <c:when test="${info_=='zcsd'}">
			           	 <option>政策速递</option>
			            </c:when>
		            </c:choose>
		            <c:choose>
			            <c:when test="${info_=='nszd'}">
			           	 <option>纳税指导</option>
			            </c:when>
		            </c:choose>
	            </c:forEach>
            </select>
            </td>
            <td class="tdBg" width="200px">来源：</td>
            <td><form:input path="source"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">信息标题：</td>
            <td colspan="3"><form:input path="title" cssStyle="width:90%"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">信息内容：</td>
            <td colspan="3"> <form:textarea path="content" id="editor" cssStyle="width:90%;height:160px;"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td colspan="3"> <form:textarea path="memo" cols="90" rows="3"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">创建人：</td>
            <td>
            	${user.name}
          		<input type="hidden" value="${user.name}" name="creator">
            </td>
            <td class="tdBg" width="200px">创建时间：</td>
            <td>
            <fmt:formatDate value="${info.createTime}"/>
            <input type="hidden" value="${info.createTime}" name="createTime">
            </td>
        </tr>
    </table>
     <!-- 默认信息状态为 发布 -->
      <input type="hidden" value="1" name="state">
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form:form>
</body>
</html>