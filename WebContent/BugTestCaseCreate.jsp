<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bug Tracker Test Cases</title>

<style type="text/css">
#rooms {
	margin-top: 5px;
}

.panel-group .panel-heading+.panel-collapse>.panel-body {
	border: 1px solid #ddd;
}

.panel-group, .panel-group .panel, .panel-group .panel-heading,
	.panel-group .panel-heading a, .panel-group .panel-title, .panel-group .panel-title a,
	.panel-group .panel-body, .panel-group .panel-group .panel-heading+.panel-collapse>.panel-body
	{
	border-radius: 2px;
	border: 0;
}

.panel-group .panel-heading {
	padding: 0;
}

.panel-group .panel-heading a {
	display: block;
	background: #668bb1;
	color: #ffffff;
	padding: 8px;
	text-decoration: none;
	position: relative;
}

.panel-group .panel-heading a.collapsed {
	background: #eeeeee;
	color: inherit;
}

.panel-group .panel-heading a:after {
	content: '-';
	position: absolute;
	right: 20px;
	top: 8px;
	font-size: 20px;
}

.panel-group .panel-heading a.collapsed:after {
	content: '+';
}

.more-details a {
	width: 60px;
	text-align: left;
	position: relative;
}

.more-details a:after {
	content: '-';
	position: absolute;
	right: 10%;
	top: 18%;
	font-size: 15px;
	color: #fff;
}

.more-details a.collapsed:after {
	content: '+';
	/*   position: absolute;
  left: 10%;
  top:0px;
  font-size:25px; */
	color: #fff;
}

.panel-group .panel-collapse {
	margin-top: 5px !important;
}

.panel-group .panel-body {
	background: #ffffff;
	padding: 15px;
}

.panel-group .panel {
	background-color: transparent;
}

.panel-group .panel-body p:last-child, .panel-group .panel-body ul:last-child,
	.panel-group .panel-body ol:last-child {
	margin-bottom: 0;
}

.add-remove {
	margin: 20px 0px 20px;
}

#mandatory{
color:red;
}

</style>

<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
	<section class="content-header">
		<h1>
			<i class="fa fa-plus"></i>Create Bug Test Case
		</h1>
		<div class="breadcrumb-wrapper">
			<%-- <span class="label">You are here:</span> --%>
			<ol class="breadcrumb">
				<li><a href="goBugTrackerList">Bug List</a></li>
				<li><a href="viewTestCases?id=${id}">View Test Cases</a></li>
				<li class="active">Create Test Case</li>
			</ol>
		</div>
	</section>

		<!-- Main content -->
		<section class="content">
			
			<!-- Small boxes (Stat box) -->
			<s:if test="hasActionErrors()">
				<div class="succfully-updated clearfix" id="error-alert">

					<div class="col-sm-2">
						<i class="fa fa-check fa-3x"></i>
					</div>

					<div class="col-sm-10">

						<p>
							<s:actionerror />
						</p>

						<button type="button" id="cancel" class="btn btn-primary">Ok</button>

					</div>

				</div>

			</s:if>

			<s:if test="hasActionMessages()">
				<div class="sccuss-full-updated" id="success-alert">
					<div class="succfully-updated clearfix">

						<div class="col-sm-2">
							<i class="fa fa-check fa-3x"></i>
						</div>

						<div class="col-sm-10">
							<s:actionmessage />
							<button type="button" id="success" class="btn btn-primary">Ok</button>

						</div>

					</div>
				</div>
			</s:if>
			
			<div class="row">
			
			 
				<!-- createHoteOfflineBooking  -->
				<form action="addNewBugTestCase" method="post"
					class="form-horizontal" name="myForm" id="bookingFormId">
					<div id="myfform">
						 
						<!-- harsha added colapse -->
						<div class="col-sm-12"> 
										<h4> <a class="collapsed" > Add Test Cases</a>
										</h4>  
															<div class="col-sm-12 bug" id="bugTestCase">
															<div class="form-group">
																<label for="testCase"
																	class=" control-label"> Test Case</label>
																<textarea rows="3" cols="3" class="form-control input-sm"
																	name="bugTestCasesList[0].testCase"
																	placeholder="Enter Test Case" required></textarea>
															</div> 
														</div> 
														<div id="bugTestCaseCount"></div>
												 
												<div class="clearfix add-remove">
													<a class="btn btn-primary" role="button" id="addcase"
														onClick="add();"> <!-- onclick="add() onclick="remove_field()" -->
														Add More
													</a> <a class="btn btn-primary remove_field" id="removeroom"
														role="button" onclick="remove_field()" disabled>
														Remove </a>

												</div>
											</div> 
								
								 
						 

					<!-- harsha added colapse ended -->

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="submit" name="id" value="${id}"  id="bugtestcasesubmitnew" 
								class="btn btn-primary btn-lg">Save Test Cases</button>
						</div>
					</div>
</div>
				</form>
			</div>

			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
 
 

	<script>
	var cloneCount = 1;
	  
	function add(){ 
		var $addBugTestCase  = $('#bugTestCase')
		.clone()
		.attr("id",'bugTestCase'+cloneCount++)
		.appendTo('#bugTestCaseCount');
		//.insertAfter($('[id^=bugTestCase]:last'));
			 
		 var noOfBugTestCaseCount = $("#bugTestCaseCount").find('.form-group').length; 
		for (var i = 0; i <=parseInt(noOfBugTestCaseCount); i++) {  
				var d = $("#bugTestCase"+(i)).find("label").text("Test Case" + (i)); 
				
				$("#bugTestCase"+(i))
				.find('.form-group:last ')
				.each(function() {
							var prefix ="bugTestCasesList"+'['+i+']'+'.'+"testCase";
							 
							console.log(prefix);
							$(this)
									.find("textarea")
									.each(function() {
										$( this ).attr( 'name', prefix ); 
											});
							
						});
		} 
		       
		if ((noOfBugTestCaseCount) > 0) {
			$('.remove_field').removeAttr('disabled');
		} else {
			$('.remove_field').attr('disabled', 'disabled');
		}
		
	} 
	function remove_field() { 
		var noOfcust = $("#bugTestCaseCount").find('.bug').length; 
		$("#bugTestCaseCount").find('.bug:last-child').remove();
		if (noOfcust <= 1) {
			$('.remove_field').attr('disabled', 'disabled');
		} else {
			$('.remove_field').removeAttr('disabled');
		}
		noOfcust--;
	} 

		 
		function add1(currentObj) {
			var parentid = $("#" + currentObj.id).parent().parent().attr('id');
			console.log(parentid);
			var tobeappenedid = $("#" + currentObj.id).parent().prev().attr(
					'class');
			console.log(tobeappenedid);
			var noOfcust = $("#" + parentid).find('.panel-default').length;

			var $addrooms = $('#' + parentid).find(".panel-default:first")
					.clone();
			$('.' + tobeappenedid).append($addrooms);
			for (var i = 1; i <= parseInt(noOfcust); i++) {
				$addrooms.find("h4.panel-title a:first").text(
						"Guest Details" + (i));
				var s = $addrooms.find("h4.panel-title a").text();
				var splited = s.split(" ").join("");

				console.log("ssss" + splited);
				var url = $('.' + tobeappenedid).find(
						".panel-default  div.panel-collapse").attr("id")
				console.log("url " + url);

				$addrooms.find("h4.panel-title a").attr("href",
						"#guest" + splited + parentid);

				$('.' + tobeappenedid).find(
						".panel-default:last  div.panel-collapse").attr("id",
						"guest" + splited + parentid).removeClass("in");
				 
				$('.' + tobeappenedid)
						.find('.panel-default:last .form-group ')
						.each(function() {
									var prefix = "hotelOrderGuests ";
									var hidden = " ";
									console.log(prefix);
									$(this)
											.find("input")
											.each(
													function() {
														this.name = this.name.replace(/hotelOrderGuests\[\d+\]/,prefix);
													});
									$(this).find('input[type="hidden"]').attr('name' , hidden);
									$(this).find("select").each(
													function() {
														this.name = this.name.replace(/hotelOrderGuests\[\d+\]/,prefix);
													});
								});
			 
			}
			//$('.'+tobeappenedid).html($addrooms);
			if ((noOfcust) > 0) {
				$('.remove_field').removeAttr('disabled');
			} else {
				$('.remove_field').attr('disabled', 'disabled');
			}
		}

		 
		function remove_field1(currentObj) {
			var parentid = $("#" + currentObj.id).parent().parent().attr('id');
			var tobeappenedid = $("#" + currentObj.id).parent().prev().attr(
					'class');
			var noOfcust = $("#" + parentid).find('.panel-default').length;

			$('.' + tobeappenedid).find('.panel-default:last-child').remove();
			if (noOfcust <= 1) {
				$('.remove_field').attr('disabled', 'disabled');
			} else {
				$('.remove_field').removeAttr('disabled');
			}
			noOfcust--;
		} 
 
	</script>
	 
</body>
</html>