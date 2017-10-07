<%@page import="java.util.List"%>
<%@page import="java.io.OutputStream"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    application.setAttribute("basePath",basePath);
%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>信息发布管理</title>
     <base href="<%=basePath%>">
    <script type="text/javascript">
  	//全选、全反选
	function doSelectAll(){
		// jquery 1.6 前
		//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
		//prop jquery 1.6+建议使用
		$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
	}
  	//异步发布信息,信息的id及将要改成的信息状态
  	function doPublic(infoId, state){
  		alert("infoId"+infoId+" "+state);
  		//1、更新信息状态
  		$.ajax({
  			url:"${pageContext.request.contextPath}/info_publicInfo",
  			data:{"infoId":infoId, "state":state},
  			type:"post",
  			success: function(msg){
  				//2、更新状态栏、操作栏的显示值
  				if("更新状态成功" == msg){
  					if(state == 1){//说明信息状态已经被改成 发布，状态栏显示 发布，操作栏显示 停用
  						$("#show_"+infoId).html("发布");
  						$("#oper_"+infoId).html('<a href="javascript:doPublic(\''+infoId+'\',0)">停用</a>');
  					} else {
  						$("#show_"+infoId).html("停用");
  						$("#oper_"+infoId).html('<a href="javascript:doPublic(\''+infoId+'\',1)">发布</a>');
  					}
  				} else {alert("更新信息状态失败！");}
  			},
  			error: function(){
  				alert("更新信息状态失败！");
  			}
  		});
  	}
  	
  	function doAdd() {
  		var url = "${basePath}/addInfo_";
		window.location.href = url;
	}
  	function doSearch() {
  		var title = encodeURI(document.getElementById('infoTitle').value);
		var url = "${basePath}/doSearch_INFO?title="+title;
		window.location.href = url;
	}
  	function doDeleteAll() {
  		var inc = $("input[name='selectedRow']").attr("checked", true);
  		var no = "";
  		for (var i = 0; i < inc.length; i++) {
  			if(i == inc.length-1){
				no += inc.val();
  			}else{
				no += inc.val() +",";
  			}
		}
  		var url = "${pageContext.request.contextPath}/removeInfo?infoId="+no;
		window.location.href = url;
	}
    </script>
</head>
<body class="rightBody">
<form:form action="" method="post" name="form1">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>信息发布管理</strong></div> </div>
                <div class="search_art">
                    <li>
                        	信息标题：<input type="text" class="s_text" id="infoTitle" style="width:160px;" value="xxxxx">
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td align="center">信息标题</td>
                            <td width="120" align="center">信息分类</td>
                            <td width="120" align="center">创建人</td>
                            <td width="140" align="center">创建时间</td>
                            <td width="80" align="center">状态</td>
                            <td width="120" align="center">操作</td>
                        </tr>
                        <c:forEach items="${pageResult.items}" var="info"><!-- infos, info-->
                            <tr>
                                <td align="center"><input type="checkbox" name="selectedRow" value="${info.infoId}"/></td>
                                <td align="center">${info.title}</td>
                                <td align="center">
                                <c:choose>
                                	<c:when test="${info.type=='tzgg'}">通知公告</c:when>
                                	<c:when test="${info.type=='zcsd'}">政策速递</c:when>
                                	<c:when test="${info.type=='nszd'}">纳税指导</c:when>
                                </c:choose>
                                </td>
                                <td align="center">${info.creator}</td>
                                <td align="center"><fmt:formatDate value="${info.createTime}"/></td>
                                <td id="show_${info.infoId}" align="center">${info.state==1?'发布':'停用'} </td>
                                <td align="center">
                                	<span  id="oper_<s:property value='infoId'/>">
                                	<c:choose>
	                                	<c:when test="${info.state==1}">
	                                		<a href="javascript:doPublic('${info.infoId}',0)">停用</a>
	                                	</c:when>
	                                	<c:otherwise>
	                                	<a href="javascript:doPublic('${info.infoId}',1)">发布</a>
	                                	</c:otherwise>
                                	</c:choose>
                                	</span>
                                    <a href="updateInfo_?infoId=${info.infoId}">编辑</a>
                                    <a href="removeInfo?infoId=${info.infoId}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <script type="text/javascript">
           	var XXpageNo = document.getElementById('XXpageNo');
           	var url = "${basePath}/showInfoList?pageNo="+XXpageNo.value;
           	window.location.href = url;
           	
            function doGoPage(pageNo) {
            	var url = "${basePath}/showInfoList?pageNo="+pageNo;
            	window.location.href = url;
			}
            </script>
        <div class="c_pate" style="margin-top: 5px;">
		<table width="100%" class="pageDown" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td align="right">总共${pageResult.totalCount}条记录，当前第 ${pageResult.pageNo} 页，共 ${pageResult.totalPageCount} 页 &nbsp;&nbsp;
				<c:if test="${pageResult.pageNo > 1}">
                 <a href="javascript:doGoPage(${pageResult.pageNo-1})">上一页</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.pageNo < pageResult.totalPageCount}">
                 <a href="javascript:doGoPage(${pageResult.pageNo+1})">下一页</a>
				</c:if>
				到&nbsp;<input id="XXpageNo" type="text" style="width: 30px;" onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
					max="" value="${pageResult.pageNo}" /> &nbsp;&nbsp;
			    </td>
			</tr>
		</table>	
        </div>

        </div>
    </div>
</form:form>
</body>
</html>