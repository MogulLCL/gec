<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>部门信息修改</title>
	<base href="<%=basePath%>">
<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="assets/css/zabuto_calendar.css">
<link rel="stylesheet" type="text/css"
	href="assets/js/gritter/css/jquery.gritter.css" />
<link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">

<script src="assets/js/chart-master/Chart.js"></script>

</head>

<body>

	<section id="container"> <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
	<!--header start--> <header class="header black-bg">
	<div class="sidebar-toggle-box">
		<div class="fa fa-bars tooltips" data-placement="right"
			data-original-title="Toggle Navigation"></div>
	</div>
	<!--logo start--> <a href="/Announcement/list" class="logo"><b>DASHGUM
			FREE</b></a> <!--logo end-->
	<div class="nav notify-row" id="top_menu">
		<!--  notification start -->
		<ul class="nav top-menu">
			<!-- settings start -->
			<li class="dropdown"><a data-toggle="dropdown"
				class="dropdown-toggle" href="index.html#"> <i
					class="fa fa-tasks"></i> <span class="badge bg-theme">4</span>
			</a>
				<ul class="dropdown-menu extended tasks-bar">
					<div class="notify-arrow notify-arrow-green"></div>
					<li>
						<p class="green">You have 4 pending tasks</p>
					</li>
					<li><a href="index.html#">
							<div class="task-info">
								<div class="desc">DashGum Admin Panel</div>
								<div class="percent">40%</div>
							</div>
							<div class="progress progress-striped">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%">
									<span class="sr-only">40% Complete (success)</span>
								</div>
							</div>
					</a></li>
					<li><a href="index.html#">
							<div class="task-info">
								<div class="desc">Database Update</div>
								<div class="percent">60%</div>
							</div>
							<div class="progress progress-striped">
								<div class="progress-bar progress-bar-warning"
									role="progressbar" aria-valuenow="60" aria-valuemin="0"
									aria-valuemax="100" style="width: 60%">
									<span class="sr-only">60% Complete (warning)</span>
								</div>
							</div>
					</a></li>
					<li><a href="index.html#">
							<div class="task-info">
								<div class="desc">Product Development</div>
								<div class="percent">80%</div>
							</div>
							<div class="progress progress-striped">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
									style="width: 80%">
									<span class="sr-only">80% Complete</span>
								</div>
							</div>
					</a></li>
					<li><a href="index.html#">
							<div class="task-info">
								<div class="desc">Payments Sent</div>
								<div class="percent">70%</div>
							</div>
							<div class="progress progress-striped">
								<div class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
									style="width: 70%">
									<span class="sr-only">70% Complete (Important)</span>
								</div>
							</div>
					</a></li>
					<li class="external"><a href="#">See All Tasks</a></li>
				</ul></li>
			<!-- settings end -->
			<!-- inbox dropdown start-->
			<li id="header_inbox_bar" class="dropdown"><a
				data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
					<i class="fa fa-envelope-o"></i> <span class="badge bg-theme">5</span>
			</a>
				<ul class="dropdown-menu extended inbox">
					<div class="notify-arrow notify-arrow-green"></div>
					<li>
						<p class="green">You have 5 new messages</p>
					</li>
					<li><a href="index.html#"> <span class="photo"><img
								alt="avatar" src="/photo/${nowUser.imgname}"></span> <span
							class="subject"> <span class="from">${nowUser.username}</span>
								<span class="time">Just now</span>
						</span> <span class="message"> Hi mate, how is everything? </span>
					</a></li>
					<li><a href="index.html#"> <span class="photo"><img
								alt="avatar" src="assets/img/ui-divya.jpg"></span> <span
							class="subject"> <span class="from">Divya Manian</span> <span
								class="time">40 mins.</span>
						</span> <span class="message"> Hi, I need your help with this. </span>
					</a></li>
					<li><a href="index.html#"> <span class="photo"><img
								alt="avatar" src="assets/img/ui-danro.jpg"></span> <span
							class="subject"> <span class="from">Dan Rogers</span> <span
								class="time">2 hrs.</span>
						</span> <span class="message"> Love your new Dashboard. </span>
					</a></li>
					<li><a href="index.html#"> <span class="photo"><img
								alt="avatar" src="assets/img/ui-sherman.jpg"></span> <span
							class="subject"> <span class="from">Dj Sherman</span> <span
								class="time">4 hrs.</span>
						</span> <span class="message"> Please, answer asap. </span>
					</a></li>
					<li><a href="index.html#">See all messages</a></li>
				</ul></li>
			<!-- inbox dropdown end -->
		</ul>
		<!--  notification end -->
	</div>
	<div class="top-menu">
		<ul class="nav pull-right top-menu">
			<li><a class="logout" href="login.html">Logout</a></li>
		</ul>
	</div>
	</header> <!--sidebar start--> <aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">
			<!-- 如果登录的是用户 -->
			<c:if test="${nowEmployee!=null}">
				<p class="centered">
					<a href="profile.html"><img src="/photo/${nowEmployee.imgname}"
						class="img-circle" width="60"></a>
				</p>
				<h5 class="centered">${nowEmployee.username}</h5>
			</c:if>
			<!-- 如果登录的是管理员 -->
			<c:if test="${nowUser!=null}">
				<p class="centered">
					<a href="profile.html"><img src="/photo/${nowUser.imgname}"
						class="img-circle" width="60"></a>
				</p>
				<h5 class="centered">${nowUser.username}</h5>
			</c:if>

			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-dashboard"></i> <span>用户管理</span>
			</a>
				<ul class="sub">
					<li><a href="/user/list">查询用户</a></li>
					<c:if test="${nowUser.status==1}">
						<li><a href="User_insert.jsp">添加用户</a></li>
					</c:if>
				</ul></li>

			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-desktop"></i> <span>员工管理</span>
			</a>
				<ul class="sub">
					<li><a href="/employee/list">查询员工</a></li>
					<c:if test="${nowUser.status==1}">
						<li><a href="Emp_insert.jsp">添加员工</a></li>
					</c:if>
				</ul></li>

			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-cogs"></i> <span>职位管理</span>
			</a>
				<ul class="sub">
					<li><a href="/job/list">查询职位</a></li>
					<c:if test="${nowUser.status==1}">
						<li><a href="Job_insert.jsp">添加职位</a></li>
					</c:if>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-book"></i> <span>部门管理</span>
			</a>
				<ul class="sub">
					<li><a href="/dept/list">查询部门</a></li>
					<c:if test="${nowUser.status==1}">
						<li><a href="Dept_insert.jsp">添加部门</a></li>
					</c:if>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-tasks"></i> <span>公告管理</span>
			</a>
				<ul class="sub">
					<li><a href="/Announcement/list">公告展示</a></li>
					<c:if test="${nowUser.status!=null}">
						<li><a href="Announcement_insert.jsp">添加公告</a></li>
					</c:if>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-th"></i> <span>下载中心</span>
			</a>
				<ul class="sub">
					<li><a href="/upload/list">查询文件</a></li>
					<li><a href="File_insert.jsp">上传文件</a></li>
				</ul></li>

		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside> <!--sidebar end-->
	<div class="copyrights">
		Collect from <a href="http://www.cssmoban.com/">网页模板</a>
	</div>

	<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
	<!--main content start--> <section id="main-content"> <section
		class="wrapper">

	<div class="row">
		<div class="col-lg-9 main-chart">
			<!-- input -->
			<form action="${pageContext.servletContext.contextPath}/job/update"
				method="post">
				<div class="content-panel" style="margin-top: 100px;">
					<table class="table table-striped table-advance table-hover">
						<h4>
							<i class="fa fa-angle-right"></i> Advanced Table
						</h4>
						<hr>
						<thead>
							<tr>
								<th><i class="fa fa-bullhorn"></i>职位</th>
								<th class="hidden-phone"><i class="fa fa-question-circle"></i>
									职位描述</th>
								<th><i class=" fa fa-edit"></i> 确认提交</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="text" name="name"
									value="${updateJob.name}" /></td>
								<td class="hidden-phone"><input type="text" name="remark"
									value="${updateJob.remark}" /></td>
								<td><input type="hidden" name="id" value="${updateJob.id}" />
									<button class="btn btn-success btn-xs" type="submit">
										<i class="fa fa-check">Submit</i>
									</button></td>
							</tr>
						</tbody>
					</table>
			</form>
		</div>
		<!-- /row mt -->
	</div>
	<!-- /col-lg-9 END SECTION MIDDLE --> <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->

	<div class="col-lg-3 ds">
		<!--COMPLETED ACTIONS DONUTS CHART-->
		<!-- WHITE PANEL - TOP USER -->
		<div class="white-panel pn">
			<div class="white-header">
				<h5>TOP USER</h5>
			</div>
			<p>
				<img src="/photo/${nowUser.imgname}" class="img-circle" width="50">
			</p>
			<p>
				<b>${nowUser.username}</b>
			</p>
			<div class="row">
				<div class="col-md-6">
					<p class="small mt">MEMBER SINCE</p>
					<p>
						<fmt:formatDate value="${nowUser.createdate}" pattern="yyyy" />
					</p>
				</div>
				<div class="col-md-6">
					<p class="small mt">User Status</p>
					<p>
						<c:if test="${nowUser.status==1}">超级管理员</c:if>
						<c:if test="${nowUser.status==0}">管理员</c:if>
					</p>
				</div>
			</div>
		</div>
		<!-- USERS ONLINE SECTION -->
		<h3>TEAM MEMBERS</h3>
		<c:forEach items="${teamMembers}" var="member">
			<div class="desc">
				<div class="thumb">
					<img class="img-circle" src="/photo/${member.imgname}" width="35px"
						height="35px" align="">
				</div>
				<div class="details">
					<p>
						<span style="color: blue; font-size: 20px;">${member.username}</span><br />
						<muted> <c:if test="${member.status==1}">超级管理员</c:if> <c:if
							test="${member.status==0}">管理员</c:if> </muted>
					</p>
				</div>
			</div>
		</c:forEach>

		<!-- CALENDAR-->
		<div id="calendar" class="mb">
			<div class="panel green-panel no-margin">
				<div class="panel-body">
					<div id="date-popover" class="popover top"
						style="cursor: pointer; disadding: block; margin-left: 33%; margin-top: -50px; width: 175px;">
						<div class="arrow"></div>
						<h3 class="popover-title" style="disadding: none;"></h3>
						<div id="date-popover-content" class="popover-content"></div>
					</div>
					<div id="my-calendar"></div>
				</div>
			</div>
		</div>
		<!-- / calendar -->

	</div>
	<!-- /col-lg-3 -->
	</div>
	<! --/row --> </section> </section> <!--main content end--> <!--footer start--> <footer
		class="site-footer">
	<div class="text-center">
		2014 - Alvarez.is - More Templates <a href="http://www.cssmoban.com/"
			target="_blank" title="模板之家">模板之家</a> - Collect from <a
			href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
		<a href="index.html#" class="go-top"> <i class="fa fa-angle-up"></i>
		</a>
	</div>
	</footer> <!--footer end--> </section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/jquery-1.8.3.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/js/jquery.sparkline.js"></script>


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<script type="text/javascript"
		src="assets/js/gritter/js/jquery.gritter.js"></script>
	<script type="text/javascript" src="assets/js/gritter-conf.js"></script>

	<!--script for this page-->
	<script src="assets/js/sparkline-chart.js"></script>
	<script src="assets/js/zabuto_calendar.js"></script>


	<script type="application/javascript">



        $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });

            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "show_data.php?action=1",
                    modal: true
                },
                legend: [
                    {type: "text", label: "Special event", badge: "00"},
                    {type: "block", label: "Regular event", }
                ]
            });
        });


        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        }


        function jumpPage(page){
        	//要修改访问的页码
        	document.getElementById("page").value=page;
        	document.getElementById("form_query").submit();
        }









	</script>


</body>
</html>
