<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>投诉受理管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.0.js"></script>
    <script type="text/javascript">
  	var list_url = "${basePath}nsfw/complain_listUI.action";
  	//搜索
  	function doSearch(){
  		//重置页号
  		$("#pageNo").val(1);
  		document.forms[0].action = list_url;
  		document.forms[0].submit();
  	}
  	//受理
  	function doDeal(compId){
  		document.forms[0].action = "${basePath}nsfw/complain_dealUI.action?complain.compId=" + compId;
  		document.forms[0].submit();
  	}
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>投诉受理管理</strong></div> </div>
                <div class="search_art">
                    <li>
                       	投诉标题：<input type="text" size="20" class="s_text" style="width:160px;">
                    </li>
                    <li>
                       	投诉时间：<input type="text" size="17" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                       	——<input type="text" size="17" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                    </li>
                    <li>
                       	状态：
                       	<select >
                       	<c:forEach items="${complainMap}" var="map">
                       	<option>${map.value}</option>
                       	</c:forEach>
                       	</select>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <label>12</label>
                    <li style="float:right;">
                    	<input type="button" value="统计" class="s_button" onclick="doAnnualStatistic()"/>&nbsp;
                    </li>

                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td align="center">投诉标题</td>
                            <td width="120" align="center">被投诉部门</td>
                            <td width="120" align="center">被投诉人</td>
                            <td width="140" align="center">投诉时间</td>
                            <td width="100" align="center">受理状态</td>
                            <td width="100" align="center">操作</td>
                        </tr>
                        <c:forEach items="${pageResult.items}" var="complain">
                        	<tr>
                        	    <td align="center">${complain.compTitle}</td>
                                <td align="center">${complain.toCompDept}</td>
                                <td align="center">${complain.toCompName}</td>
                                <td align="center"><fmt:formatDate value="${complain.compTime}"/></td>
                                <td align="center">
                                <c:if test="${complain.state=='1'}">已受理</c:if>
                                <c:if test="${complain.state=='0'}">待受理</c:if>
                                <c:if test="${complain.state=='2'}">已失效</c:if>
                                <td align="center">
                                    <a href="javascript:doDeal('${complain.compId}')">受理</a>
                                </td>
                        	</tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <script type="text/javascript">
           	var XXpageNo = document.getElementById('XXpageNo');
           	var url = "${basePath}/showComplainList?pageNo="+XXpageNo.value;
           	window.location.href = url;
           	
            function doGoPage(pageNo) {
            	var url = "${basePath}/showComplainList?pageNo="+pageNo;
            	window.location.href = url;
			}
            </script>
         <div class="c_pate" style="margin-top: 5px;">
			<table width="100%" class="pageDown" border="0" cellspacing="0" cellpadding="0">
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
</form>

</body>
</html>