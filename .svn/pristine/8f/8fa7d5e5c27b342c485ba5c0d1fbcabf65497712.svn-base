<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib prefix="s" uri="/struts-tags"%>
       <link href="css/chart.css" rel="stylesheet" type="text/css" />
       <link rel="stylesheet" type="text/css"	href="css/jquerydarkness-ui.min.css">
       <link href="css/jquerydarkness-ui-modified.css" rel="stylesheet">   
       <link href="css/animate-custom.css" rel="stylesheet" type="text/css" />
        <link href="css/dashboard-search.css" rel="stylesheet" type="text/css" />
      <%--  <script src="js/jquery.min.js"></script>   --%> 
<link rel="stylesheet" href="css/alert.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<style> 
 #search{
	color:red;
    text-align:center;

}
</style>
<%--  <meta http-equiv="refresh" content="5;url=<s:url includeParams="all" />"/> --%>
      <!-- Content Wrapper. Contains page content -->
      <s:if test="actionMsg!=null && actionMsg!='' ">
			<div class="succfully-updated clearfix" id="error-alert-main">
				<div class="col-sm-2">
					<i class="fa fa-check fa-3x"></i>
				</div>
				<div class="col-sm-10">
					<p>
						<s:property value="actionMsg" />
					</p>
					<button type="button" id="cancel-main" class="btn btn-primary">Ok</button>
				</div>
			</div>
	</s:if>
      <div class="content-wrapper" >
        <!-- Content Header (Page header) -->
        <section class="content-header" >
          <h1>
            Dash board
            <small>Control panel</small>
          </h1>
         <%--  <form action="dashboardsearchmanager" method="post">
          <div class="container">
	<div class="row">
		
           <div id="custom-search-input">
                            <div class="input-group col-md-11">
                                <input type="text" id="search" class="search-query form-control" name="searchingvalue" value="" minlength="3" maxlength="25" placeholder="Search like PNR/OrderId/Invoice No" required="required" autocomplete="off"/>
                                <span class="input-group-btn">
                                    <button class="btn btn-danger"  type="submit">
                                        <span class=" glyphicon glyphicon-search"></span>
                                    </button>
                                </span>
                            </div>
                        </div>
	</div>
</div>
</form> --%>
          <%-- <s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
          <div>
          (
  					Emulated By Company :  <s:property value="#session.emulateByCompany.companyname" />
  					Emulated By User :  <s:property value="#session.emulateByUser.username" />
  					 <form action="companylogin" id="logForm" method="post" class="form-horizontal">
  					   <input type="hidden" name="Email"     id="email"  class="form-control input-sm" value='<s:property value="#session.emulateByUser.email" />'>
		               <input type="hidden" name="Password"  id="Password"   class="form-control input-sm" value='<s:property value="#session.emulateByUser.password" />'>
        		      <input type="hidden" name="company_userid"  class="form-control input-sm" value='<s:property value="#session.emulateByCompany.company_userid" />'>
                       <button type="submit" class="btn btn-primary">Go Back</button>
					</form>						   
            )
          </div>
		</s:if>     --%>      
        
        </section>
        <!-- Main content -->
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <div class="row">
          
          
          </div>
          <!--  Number of agents and distributers ends --> 
          
                  <!--  Number of agents and distributers -->  
                   <div class="row">
                   
                   
                   
                   <a href="FlightIndividualDashboard" class="dashbox dashbox-1">
                   <div class="icon icon-planebg">
                  <i class="fa fa-plane"></i>
               	 </div>
                   		<h4>Flights</h4>
                   </a>
                   
                   <a href="HotelIndividualDashboard" class="dashbox dashbox-1">
                   <div class="icon icon-hotelbg">
	                  <i class="fa fa-hotel"></i>
	                </div> 
                   		<h4>Hotels</h4>
                   </a>
                   
                   <a href="BusIndividualDashboard" class="dashbox dashbox-1">
                   <div class="icon icon-busbg">
								                  <i class="fa fa-bus"></i>
								                </div>
                   		 
                   		<h4>Bus</h4>
                   </a>
                   
                   <a href="CarIndividualDashboard" class="dashbox dashbox-1">
                   <div class="icon icon-carbg">
								                  <i class="fa fa-car"></i>
								                </div>
                   		 
                   		<h4>Car</h4>
                   </a>
                   
                    <a href="TrainIndividualDashboard" class="dashbox dashbox-1">
                   <div class="icon icon-trainbg">
								                  <i class="fa fa-train"></i>
								                </div>
                   		 
                   		<h4>Train</h4>
                    </a>
                   
                     <a href="VisaIndividualDashboard" class="dashbox dashbox-1">
                   <div class="icon icon-visabg">
								                  <i class="fa fa-cc-visa"></i>
								                </div>
                   		 
                   		<h4>Visa</h4>
                    </a>
                     <a href="InsuranceIndividualDashboard" class="dashbox dashbox-1">
                   <div class="icon icon-planebg">
								                  <i class="fa fa-medkit"></i>
								                </div>
                   		 
                   		<h4>Insurance</h4>
                   </a>
                   
                    <a href="MiscellaneousIndividualDashboard" class="dashbox dashbox-1">
                   <div class="icon icon-planebg">
								                  <i class="fa fa-cogs"></i>
								                </div>
                   		 
                   		<h4>Miscellaneous</h4>
                    </a>
                    
                    <a href="OverAllDashboard" class="dashbox dashbox-1">
                   <div class="icon icon-planebg">
								                  <i class="fa fa-puzzle-piece"></i>
								                </div>
                   		 
                   		<h4>Overall</h4>
                    </a>
                    
                    <a href="AllRecords" class="dashbox dashbox-1">
                   <div class="icon icon-planebg">
								                  <i class="fa fa-area-chart"></i>
								                </div>
                   		 
                   		<h4>Analytics</h4>
                    </a>
                   
                   
                   
                   
          <div class="col-sm-12 col-md-12 onlyagents">
         
             <s:if test="%{#session.User.userrole_id.isSuperuser()}">
               <div class="col-sm-4">
                      <div class="row">
              <!-- small box -->
              <div class="small-box bg-orange-active">
                <div class="inner clearfix" >          
                  <!--  <div class="inner"> -->
                   <div class="col-sm-6">
                  <h3 id="distributorlist"> </h3>
                  </div>
                  <div class="col-sm-6 text-right">
                  <p>  <a href="getAllDistributors" class="small-box-footer"> <s:text name="global.Wholesaler" ></s:text>  
                <i class="fa fa-arrow-circle-right"></i></a></p>
             </div>
             </div>
             </div>             
                <div class="icon">
                  <i class="ion ion-pie-graph"></i>
                </div>
              </div>
            	</div>
                      <div class="col-sm-4">
                      <div class="row">
              <!-- small box -->
              <div class="small-box bg-orange-active">
                <div class="inner clearfix" >          
                  <!--  <div class="inner"> -->
                   <div class="col-sm-7">
                  <h3 id="corporatelist"> </h3>
                  </div>
                  <div class="col-sm-5 text-right">
                  <p>  <a href="getAllCorporate" class="small-box-footer">Corporate  
                <i class="fa fa-arrow-circle-right"></i></a></p>
             </div>
             </div>
             </div>             
                <div class="icon">
                  <i class="ion ion-pie-graph"></i>
                </div>
              </div>
            	</div><!-- ./col -->
             <div class="col-sm-4">
             <div class="row">
              <!-- small box -->
              <div class="small-box bg-lime-active">
                <div class="inner clearfix" >
                <div class="col-sm-7">
                   <h3 id="agentlist"> </h3>                   
                   </div>
                   
                   <div class="col-sm-5 text-right">
                   <p>
                   <a href="AllAgenciesList" class="small-box-footer"> Agencies <i class="fa fa-arrow-circle-right"></i></a></p>
                   </div>
                   
                   </div>
                
                </div>             
                 
                 
                <div class="icon">
                  <i class="ion ion-pie-graph"></i>
                </div>
                
                <!-- <a href="AllUser_UserList" class="small-box-footer">More Info<i class="fa fa-arrow-circle-right"></i></a> -->
                 <%-- <s:if test="%{#session.User.userrole_id.isSuperuser()}">
                 <a href="superUserCompanyList" class="small-box-footer">Wholesalers<i class="fa fa-arrow-circle-right"></i></a>
                 </s:if> --%>
              </div>
            </div><!-- ./col -->
              </s:if>
            
              <s:elseif test="%{#session.Company.companyRole.isCorporate()}">
                      <div class="col-sm-6">
                      <div class="row">
              <!-- small box -->
              <div class="small-box bg-orange-active">
                <div class="inner clearfix" >
                
               
                  <!--  <div class="inner"> -->
                   <div class="col-sm-7">
                  <h3 id="totalemployeecount"> </h3>
                  </div>
                  <div class="col-sm-5 text-right">
              
                  <p>  <a href="superUser_UserList" class="small-box-footer">Employees 
                <i class="fa fa-arrow-circle-right"></i></a></p>
              
                  </div>                
                  </div>         
                 
                </div>    
                 
                 
                <div class="icon">
                  <i class="ion ion-pie-graph"></i>
                </div>
              
              </div>
            </div><!-- ./col -->
              </s:elseif>
            <s:elseif test="%{#session.Company.companyRole.isDistributor}">
             <div class="col-sm-6">
                                  <div class="row">
              <!-- small box -->
              <div class="small-box bg-lime-active">
                <div class="inner clearfix" >
                 <div class="col-sm-7">
                   <h3 id="agentlist"> </h3>                   
                   </div>
                   
                   <div class="col-sm-5 text-right">
                   <p><a href=AllAgenciesList class="small-box-footer"> Agencies <i class="fa fa-arrow-circle-right"></i></a></p>
                   </div>
                    </div>
                
                </div>             
                 <div class="icon">
                  <i class="ion ion-pie-graph"></i>
                </div>
                
                <!-- <a href="AllUser_UserList" class="small-box-footer">More Info<i class="fa fa-arrow-circle-right"></i></a> -->
                 <%-- <s:if test="%{#session.User.userrole_id.isSuperuser()}">
                 <a href="superUserCompanyList" class="small-box-footer">Wholesalers<i class="fa fa-arrow-circle-right"></i></a>
                 </s:if> --%>
              </div>
            </div><!-- ./col -->
            
             </s:elseif>
            <s:elseif test="%{#session.Company.companyRole.isAgent()}">
              </s:elseif>
              
              
              
            <s:else>
            <div class="col-sm-6">
                                  <div class="row">
              <!-- small box -->
              <div class="small-box bg-lime-active">
                <div class="inner clearfix" >
                 <div class="col-sm-7">
                   <h3 id="agentlist"> </h3>                   
                   </div>
                   
                   <div class="col-sm-5 text-right">
                   <p><a href=AllAgenciesList class="small-box-footer"> Agencies <i class="fa fa-arrow-circle-right"></i></a></p>
                   </div>
                    </div>
                
                </div>             
                 <div class="icon">
                  <i class="ion ion-pie-graph"></i>
                </div>
                
                <!-- <a href="AllUser_UserList" class="small-box-footer">More Info<i class="fa fa-arrow-circle-right"></i></a> -->
                 <%-- <s:if test="%{#session.User.userrole_id.isSuperuser()}">
                 <a href="superUserCompanyList" class="small-box-footer">Wholesalers<i class="fa fa-arrow-circle-right"></i></a>
                 </s:if> --%>
              </div>
            </div><!-- ./col -->
             </s:else>
             
          </div><!-- /.row -->
          </div>
          
 	<div class="row">

         <%--  <div class="col-sm-12 caption-subject">
            <h4><span>Sales Summary</span></h4>
          </div> --%>
<!-- 	<div class="col-md-4">
                    <div class="pnl">
                        <div class="hd clearfix">
                           <h5>Weekly Sales</h5>
                           <div class="set pull-right">
                                <a href="#" class="fa fa-refresh"></a>
                                <a href="#" class="fa fa-expand"></a>
                           </div>
                        </div>
                        <div class="cnt">
                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="chart-dot-list">
                                        <li class="air">Air</li>
                                        <li class="hotel">Hotel</li>
                                        <li class="car">Car</li>
                                    </ul>
                                </div>
                                <div class="col-md-12">
                                    <div class="chart chart-doughnut">
                                        <canvas id="chart-area" width="262" height="196" style="width: 262px; height: 196px;">
                                    </canvas></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> -->

              <!--   <div class="col-md-8">
                    <div class="pnl ">
                        <div class="hd clearfix">
                           <h5>Weekly Sales</h5>
                           <div class="set pull-right">
                                <a href="#" class="fa fa-refresh"></a>
                                <a href="#" class="fa fa-expand"></a>
                           </div>
                        </div>
                        <div class="cnt">
                            <div class="chart">
                                <canvas id="chart-bar" height="289" width="867" style="width: 867px; height: 289px; "></canvas>
                            </div>
                        </div>
                    </div>
                </div> -->


                <!-- <div class="col-md-4">
                    <div class="pnl">
                        <div class="hd clearfix">
                           <h5>Monthly Sales</h5>
                           <div class="set pull-right">
                                <a href="#" class="fa fa-refresh"></a>
                                <a href="#" class="fa fa-expand"></a>
                           </div>
                        </div>
                        <div class="cnt">
                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="chart-dot-list">
                                        <li class="air">Air</li>
                                        <li class="hotel">Hotel</li>
                                        <li class="car">Car</li>
                                    </ul>
                                </div>
                                <div class="col-md-12">
                                    <div class="chart chart-doughnut">
                                        <canvas id="chart-area-month" width="262" height="196" style="width: 262px; height: 196px;">
                                    </canvas></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
 -->
               <!--  <div class="col-md-8">
                    <div class="pnl ">
                        <div class="hd clearfix">
                           <h5>Monthly Sales</h5>
                           <div class="set pull-right">
                                <a href="#" class="fa fa-refresh"></a>
                                <a href="#" class="fa fa-expand"></a>
                           </div>
                        </div>
                        <div class="cnt">
                            <div class="chart">
                                <canvas id="chart-bar-month" height="289" width="867" style="width: 867px; height: 289px;"></canvas>
                            </div>
                        </div>
                    </div>
                </div>  -->
                
                 <!-- <div class="col-md-8">
                    <div class="pnl ">
                        <div class="hd clearfix">
                           <h5>line</h5>
                           <div class="set pull-right">
                                <a href="#" class="fa fa-refresh"></a>
                                <a href="#" class="fa fa-expand"></a>
                           </div>
                        </div>
                        <div class="cnt">
                            <div class="chart">
                                <canvas id="line-chart" height="289" width="867" style="width: 867px; height: 289px;"></canvas>
                            </div>
                        </div>
                    </div>
                </div>  -->
                
                
                 <!-- <div class="col-md-4">
                    <div class="pnl">
                        <div class="hd clearfix">
                           <h5>today</h5>
                           <div class="set pull-right">
                                <a href="#" class="fa fa-refresh"></a>
                                <a href="#" class="fa fa-expand"></a>
                           </div>
                        </div>
                        <div class="cnt">
                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="chart-dot-list">
                                        <li class="air">Air</li>
                                        <li class="hotel">Hotel</li>
                                        <li class="car">Car</li>
                                    </ul>
                                </div>
                                <div class="col-md-12">
                                    <div class="chart chart-doughnut">
                                         <canvas id="today" width="262" height="196" style="width: 262px; height: 181px;">
                                    </canvas></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> -->
                
                
<%--                   <div ng-app="airDataApp"> 
                              <div ng-controller="OrderChartCtrl">
                <div class="col-sm-4" > 
                              <div class="panel panel-default" >
                                <div class="panel-heading clearfix">
                                    <div class="panel-title-box">
                                        <h3>Todays</h3> 
                                    </div>   
                               
                                    <ul class="panel-controls" style="margin-top: 2px;">
                                     
                                     <li><a href="#" class="panel-refresh"><span class="fa fa-refresh"></span></a></li>
                                        <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li> 
            							                          
                                    </ul>
                                </div>
                                <div class="panel-body panel-body-table">
                       
                            
                           
                                    <ul class="nav nav-tabs" id="tablist">
    								<li   class="active"><a  href="#todayBookings" id="tt" data-toggle="tab" ng-click="getDailyConfirmed()">Bookings</a></li>
    								<li><a  href="#todayBookings" id="tp" data-toggle="tab" ng-click="getWeeklyConfirmed()">Ticket Vs Canceled</a></li>
 									 </ul>   
 								 <div class="tab-content">
    								<div class="tab-pane active" id="todayBookings"> 
    								<div class="chart chart-doughnut">
                                        <canvas id="today" width="262" height="196" style="width: 262px; height: 181px;">
                                    </canvas></div>
                                     
   									 </div> 
  								</div> 
                                </div>
                            </div>  
                       </div> 
                          </div>  
                       </div> --%>
                       
        
        
        <!-- /.row -->
         </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
     <%--     <%@ include file="DashboardFooter.jsp"%>  --%>
       <%-- <script src="js/jquery.js" type="text/javascript"></script> --%>
       <script src="js/jquery.min.js" type="text/javascript"></script>
       
         <script src="js/jquerycalUI.js"></script>
           <%-- <script src="js/angular.js"></script> --%>  
       <script src="js/ui-bootstrap-tpls-0.9.0.js"></script>  
       <script src="js/bootstrap.min.js" type="text/javascript"></script>
   <script src="js/custom.js"></script>
     <script src="js/listcomplete.js?ver=5.0" defer></script>
     
      <script src="js/Chart.min.js"></script>
      
      
      <%-- <script src="js/https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.3/Chart.min.js"></script> --%>
      
      
      
<%--     <script src="js/chartDataSample.js"></script> --%>
    <script type="text/javascript"
		src="//js.maxmind.com/js/apis/geoip2/v2.1/geoip2.js"></script>
 
 
	    	   

	<script type="text/javascript">
		var onSuccess = function(location) {
			/*  console.log(
			     "Lookup successful:\n\n"
			     + JSON.stringify(location, undefined, 4)
			 ); */
			// console.log(location.country.names.en);
			var countryname = location.country.names.en;
			$
					.ajax({
						url : 'resources/Apicredential.properties',
						dataType : 'text',
						type : 'GET',
						statusCode : {
							404 : function(response) {
								//  alert(404);
							},
							200 : function(response) {
								var res = JSON.parse(response);
								$
										.ajax({
											url : res.ApiURL
													+ 'Search/currency/'
													+ countryname,
											dataType : 'text',
											type : 'GET',
											async : true,
											statusCode : {
												404 : function(response) {
													//  alert(404);
												},
												200 : function(response) {
													// alert(response);
													$("#onecurrencyname").val(response);
													$("#multicurrencyname").val(response);

												}
											},
											error : function(jqXHR, status,
													errorThrown) {
												//  alert('error');
											}
										});
							}
						},
						error : function(jqXHR, status, errorThrown) {
							//  alert('error');
						}
					});

		};

		var onError = function(error) {
			/*  console.log(
			  "Error:\n\n"
			  + JSON.stringify(error, undefined, 4)
			); */
		};

		geoip2.country(onSuccess, onError);
	</script>
	
	
	
	<!--  ................ Angular Autocomplete Js ..................... -->
	
	<Script>
	$("#twodpd2").removeAttr("disabled");
	$("#oneway").click(function(){
    $("#twodpd2").attr("disabled","disabled");
    $("#triptype").val("O");
    
});

$("#return-tab").click(function(){
    $("#twodpd2").removeAttr("disabled");
    $("#triptype").val("R");
});


$('#fdropdown').on('click', function(event) {
	event.stopPropagation();
	$('.mega-dropdown-menu').slideToggle();
});

	$('#dropdown').on('click', function(event) {
		event.stopPropagation();
		$('.dropdownwrap').slideToggle();
	});
    $('#but-up').on('click', function(event) {
		event.stopPropagation();
		$('.mega-dropdown-menu').slideUp();	
		
	});
	$('#but-up1').on('click',function(event){ 	   
		event.stopPropagation();	     
		$('.dropdownwrap').slideUp();
		
	});
	</script>

	       <script>
	       $('document').ready(function(){	
	    	   $(function() {
	    			getgraphdata();
	    		 });
	    		  
                    
                 // Fixed header
            		//-----------------------------------------------
            		headerTopHeight = $(".header-top").outerHeight(),
            		headerHeight = $("header.header.fixed").outerHeight();
            		$(window).resize(function() {
            			if(($(this).scrollTop() < headerTopHeight + headerHeight -5 ) && ($(window).width() > 767)) {
            				headerTopHeight = $(".header-top").outerHeight(),
            				headerHeight = $("header.header.fixed").outerHeight();
            			}
            		});

            		$(window).scroll(function() {
            			if (($(".header.fixed:not(.fixed-before)").length > 0)  && !($(".transparent-header .slideshow").length>0)) {
            				if (($(this).scrollTop() > headerTopHeight + headerHeight) && ($(window).width() > 767)) {
            					$("body").addClass("fixed-header-on");
            					$(".header.fixed:not(.fixed-before)").addClass('animated object-visible fadeInDown');
            					
            				} else {
            					$("body").removeClass("fixed-header-on");
            					
            					$(".header.fixed:not(.fixed-before)").removeClass('animated object-visible fadeInDown fixheader');
            				}
            			} else if ($(".header.fixed:not(.fixed-before)").length > 0) {
            				if (($(this).scrollTop() > headerTopHeight + headerHeight) && ($(window).width() > 767)) {
            					$("body").addClass("fixed-header-on"); 
            					$(".header.fixed:not(.fixed-before)").addClass('animated object-visible fadeInDown fixheader');
            				} else {
            					$("body").removeClass("fixed-header-on");
            					$(".header.fixed:not(.fixed-before)").removeClass('animated object-visible fadeInDown fixheader');
            				}
            			};
            		});

            		$(window).scroll(function() {
            			if (($(".header.fixed.fixed-before").length > 0)  && !($(".transparent-header .slideshow").length>0)) {
            				if (($(this).scrollTop() > headerTopHeight) && ($(window).width() > 767)) {
            					 $("body").addClass("fixed-header-on"); 
            					$(".header.fixed.fixed-before").addClass('object-visible');
            					
            				} else {
            					 $("body").removeClass("fixed-header-on");
            					
            					$(".header.fixed.fixed-before").removeClass('object-visible');
            				}
            			} else if ($(".header.fixed.fixed-before").length > 0) {
            				if (($(this).scrollTop() > headerTopHeight) && ($(window).width() > 767)) {
            					 $("body").addClass("fixed-header-on"); 
            					$(".header.fixed.fixed-before").addClass('object-visible');
            				} else {
            					$("body").removeClass("fixed-header-on");
            					$(".header.fixed.fixed-before").removeClass('object-visible');
            				}
            			};
            		});
	   	});

       
</script> 


	<script>
function incrementValue()
{
    var value = parseInt(document.getElementById('number').value, 10);
    value = isNaN(value) ? 0 : value;
    if(value<9){
        value++;
            document.getElementById('number').value = value;
    }
}
function decrementValue()
{
    var value = parseInt(document.getElementById('number').value, 9);
    value = isNaN(value) ? 0 : value;
    if(value>1){
        value--;
            document.getElementById('number').value = value;
    }

}
$(function(){

	$('input[name="adult"]').keyup(function(){

	    var adultValue = $('input[name="adult"]').val();
	    if(adultValue >=9)
	    {
	     $('input[name="adult"]').val(9);
	     $('input[name="kid"]').val('');
	     $('input[name="kid"]').attr('disabled','disabled');  
	    }
	    else
	    {
	     $('input[name="kid"]').removeAttr('disabled'); 
	    }
	});
	$('input[name="kid"]').keyup(function(){
	    var adultValue = parseInt($('input[name="adult"]').val());
	    var kidValue = parseInt($('input[name="kid"]').val()); 
	    if(isNaN(kidValue))
	    {
	     kidValue =0;
	    }
	    var total = adultValue + kidValue;

	    if(total >=9)
	    {
	     $('input[name="kid"]').val(9-adultValue);
	    }
	    else
	    {
	     $('input[name="kid"]').removeAttr('disabled');
	     $('input[name="infant"]').val(9-total);
	    }
	   
	    
	});

	});

</script>
<script>
$('.btn-number').click(function(e){
	e.preventDefault();        

	fieldName = $(this).attr('data-field');
	type      = $(this).attr('data-type');

	var input = $("#"+fieldName);    

	//var input = $("input[name='"+fieldName+"']");     
	var currentVal = input.text();

	if (!isNaN(currentVal)) {

		if(type == 'minus') {

			if(parseInt(currentVal) > input.attr('min')) {
				input.text(parseInt(currentVal) - 1).change();
				if(fieldName == "adultid"){
					$('#hiddenadult').val(parseInt(currentVal) - 1);           	  

					$('#infantid').attr('max',parseInt(currentVal) - 1);  
					if(parseInt($('#infantid').text()) > 0)
						$('#totaltraveller').text(parseInt($('#totaltraveller').text()) - parseInt($('#infantid').text()) );

					$('#infantid').text(0);  
				}

				if(fieldName == "kidid")
					$('#hiddenkid').val(parseInt(currentVal) - 1); 
				if(fieldName == "infantid")
					$('#hiddeninfant').val(parseInt(currentVal) - 1);

				var total =  $('#totaltraveller').text();
				$('#totaltraveller').text(parseInt(total) -1);
			} 
			if(input.text() == input.attr('min')) {
				$(this).attr('disabled', true);
			}

		} else if(type == 'plus') {
			//var totallist =  $('#totaltraveller').text();
			var adultcount =  parseInt($('#hiddenadult').val());
			var childcount =  parseInt($('#hiddenkid').val());


			if((adultcount+childcount) <= 8)
			{

				if(parseInt(currentVal) < input.attr('max')) {
					console.log('here' +input);
					input.text(parseInt(currentVal) + 1).change();  
					if(fieldName == "adultid"){

						$('#hiddenadult').val(parseInt(currentVal) + 1);   
						if(parseInt($('#infantid').text()) > 0)
							$('#totaltraveller').text(parseInt($('#totaltraveller').text()) - parseInt($('#infantid').text()) );
						$('#infantid').text(0);  
						$('#infantid').attr('max',parseInt(currentVal) + 1);  


					}                	
					if(fieldName == "kidid"){

						$('#hiddenkid').val(parseInt(currentVal) + 1); 
					}            

					if(fieldName == "infantid"){  
						$('#hiddeninfant').val(parseInt(currentVal) + 1);
					}


					var total =  $('#totaltraveller').text();
					$('#totaltraveller').text(parseInt(total) +1);

				}		

			}			
			if((adultcount+childcount) == 9 && adultcount <= parseInt($('#infantid').attr('max'))){

				if(parseInt(currentVal) < input.attr('max')) {
					if(fieldName == "infantid"){      
						input.text(parseInt(currentVal) + 1).change();  
						$('#hiddeninfant').val(parseInt(currentVal) + 1);
						var total =  $('#totaltraveller').text();
						$('#totaltraveller').text(parseInt(total) +1);
					} 

				}
			}

		}

	} else {
		input.text(0);
	}
});

$('ul.dropdown-menu.mega-dropdown-menu').on('click', function(event){    
	event.stopPropagation();

});
$('ul.dropdown-menu.mega-dropdown-menu').on('change', function(event){    
	event.stopPropagation();

});


$('.input-number').focusin(function(){
$(this).data('oldValue', $(this).val());
});
$('.input-number').change(function() {

minValue =  parseInt($(this).attr('min'));
maxValue =  parseInt($(this).attr('max'));
valueCurrent = parseInt($(this).text());

name = $(this).attr('id');
if(parseInt(valueCurrent) >= minValue) {
	$(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
} else {
	alert('Sorry, the minimum value was reached');
	$(this).text($(this).data('oldValue'));
}
if(parseInt(valueCurrent) <= maxValue) {
	$(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
} else {
	alert('Sorry, the maximum value was reached');
	$(this).text($(this).data('oldValue'));
}


});
$(".input-number").keydown(function (e) {
// Allow: backspace, delete, tab, escape, enter and .
if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 || (e.keyCode == 65 && e.ctrlKey === true) || (e.keyCode >= 35 && e.keyCode <= 39)){
	// let it happen, don't do anything
	return;
}
// Ensure that it is a number and stop the keypress
if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))) {
	e.preventDefault();
}
});
</script>

 <script>
 function autoRefresh_div()
 {
	 
     var protocol=location.protocol;
  	 var host=location.host;
  	 var totUrl=$(location).attr('href');
 	 var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
 	 var  finalUrl = newUrl+"getCompanyUserJson";
 			   				comfirmList= [];
 			 				$.ajax({
 								    method: "GET",
 								    url:finalUrl,
 								   /*  data: {type:"today"}, */
 								    success:function(data,status)
 									{ 
 								    	 $("#distributorlist").text(data.jsonobj.totaldistributorlist);
 								    	 $("#corporatelist").text(data.jsonobj.totalcorporatelist);
 								    	 $("#agentlist").text(data.jsonobj.totalagentlist);
 								    	 $("#totalemployeecount").text(data.jsonobj.totalemployeecount);
 								     },
 									error: function(xhr,status,error)
 									{
 										console.log(error)
 									  /*  alert(error); */
 									}
 								});  	
 				
  }
 $(function() {
	 autoRefresh_div();
	//getgraphdata();
	 //setInterval('autoRefresh_div()',60000); // refresh div after 60 secs
 });
  
     </script>
      
    <%@ include file="DashMainContentFooter.jsp"%>   
    
	<script type="text/javascript">
  $(document).ready(function() {
	  $('#cancel-main').click(function() {
		   $('#error-alert-main').hide();
		});  
	 
 });
 
 
 
 
 </script> 
 
 
 <script>
		Chart.defaults.global.pointHitDetectionRadius = 1;
		var customTooltips = function(tooltip) {
			// Tooltip Element
			var tooltipEl = document.getElementById('chartjs-tooltip');
			if (!tooltipEl) {
				tooltipEl = document.createElement('div');
				tooltipEl.id = 'chartjs-tooltip';
				tooltipEl.innerHTML = "<table></table>"
				this._chart.canvas.parentNode.appendChild(tooltipEl);
			}
			// Hide if no tooltip
			if (tooltip.opacity === 0) {
				tooltipEl.style.opacity = 0;
				return;
			}
			// Set caret Position
			tooltipEl.classList.remove('above', 'below', 'no-transform');
			if (tooltip.yAlign) {
				tooltipEl.classList.add(tooltip.yAlign);
			} else {
				tooltipEl.classList.add('no-transform');
			}
			function getBody(bodyItem) {
				return bodyItem.lines;
			}
			// Set Text
			if (tooltip.body) {
				var titleLines = tooltip.title || [];
				var bodyLines = tooltip.body.map(getBody);
				var innerHtml = '<thead>';
				titleLines.forEach(function(title) {
					innerHtml += '<tr><th>' + title + '</th></tr>';
				});
				innerHtml += '</thead><tbody>';
				bodyLines.forEach(function(body, i) {
					var colors = tooltip.labelColors[i];
					var style = 'background:' + colors.backgroundColor;
					style += '; border-color:' + colors.borderColor;
					style += '; border-width: 2px'; 
					var span = '<span class="chartjs-tooltip-key" style="' + style + '"></span>';
					innerHtml += '<tr><td>' + span + body + '</td></tr>';
				});
				innerHtml += '</tbody>';
				var tableRoot = tooltipEl.querySelector('table');
				tableRoot.innerHTML = innerHtml;
			}
			var positionY = this._chart.canvas.offsetTop;
			var positionX = this._chart.canvas.offsetLeft;
			// Display, position, and set styles for font
			tooltipEl.style.opacity = 1;
			tooltipEl.style.left = positionX + tooltip.caretX + 'px';
			tooltipEl.style.top = positionY + tooltip.caretY + 'px';
			tooltipEl.style.fontFamily = tooltip._fontFamily;
			tooltipEl.style.fontSize = tooltip.fontSize;
			tooltipEl.style.fontStyle = tooltip._fontStyle;
			tooltipEl.style.padding = tooltip.yPadding + 'px ' + tooltip.xPadding + 'px';
		};
		var lineChartData = {
			labels: ["January", "February", "March", "April", "May", "June", "July"],
			datasets: [{
				label: "My First dataset",
				borderColor: window.chartColors.red,
				pointBackgroundColor: window.chartColors.red,
				fill: false,
				data: [
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor()
				]
			}, {
				label: "My Second dataset",
				borderColor: window.chartColors.blue,
				pointBackgroundColor: window.chartColors.blue,
				fill: false,
				data: [
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor(), 
					randomScalingFactor()
				]
			}]
		};
		window.onload = function() {
			var chartEl = document.getElementById("chart");
			window.myLine = new Chart(chartEl, {
				type: 'line',
				data: lineChartData,
				options: {
					title:{
						display:true,
						text:'Chart.js Line Chart - Custom Tooltips'
					},
					tooltips: {
						enabled: false,
						mode: 'index',
						position: 'nearest',
						custom: customTooltips
					}
				}
			});
		};
	</script>
	