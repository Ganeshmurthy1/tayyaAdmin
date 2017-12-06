<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="s" uri="/struts-tags"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html data-ng-app="NotifyAPP">
  <head>
   <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.4 -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- FontAwesome 4.3.0 -->
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />

    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="js/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="<s:text name="global.Appcss"></s:text>" rel="stylesheet" type="text/css" />
<!-- map plugin STYLES -->
    
<link href="dashboard-plugins/morris/morris.css" rel="stylesheet" type="text/css"> 
<link href="dashboard-plugins/css/use.css" id="style_components" rel="stylesheet" type="text/css">
<link href="js/datatables/buttons.bootstrap.min.css" rel="stylesheet" type="text/css" />
	<!-- <link href="css/jquery-ui.css" rel="stylesheet" type="text/css" /> --> 
	<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
	    <link href="css/chart.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-timepicker-addon.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/angular-csp.css">
<link rel="stylesheet" href="css/angular-ui-notification.min.css">
<link rel="stylesheet" href="css/Tayyarahadmin-lintas.css">
 

<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<%-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> --%>
<%-- <script src="js/jquery-ui-timepicker-addon.min.js" type="text/javascript"></script> --%>
<script type="text/ng-template" id="customTemplate.html"></script>
<script src="js/angular.js" type="text/javascript"></script>  
<script src="js/angular-ui-notification.min.js"></script>

  <script> 
$('[data-toggle="collapse"]').click(function() {
		alert("clicked");
		  $('.collapse.in').collapse('hide')
		});
  $(document).ready(function() { 
	$(document).tooltip({
    	disabled: true
    }); 
	
  });  
</script> 
 

<style type="text/css">
.ttst {
 position: absolute;
 top: 1px;
 left: 1%;
 color: #fff;
 background-color: #383838;
 padding: 10px;
 text-align: center;
 border-radius: 5px;
 -webkit-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
 -moz-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
 box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
 display: none;
 z-index: 999;
 font-size: 12px;
}
[ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
  display: none !important;
} 
.ui-autocomplete {
	max-height: 200px;
	width: auto;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: auto;
	font-family: "Trebuchet MS", "Helvetica", "Arial", "Verdana",
		"sans-serif";
	font-size: 1em;
	z-index: 99999;
 
}
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}

</style>
  </head>
 <!--  <body data-ng-controller="headerController" data-ng-clock class="sidebar-collapse"> -->
  <body data-ng-controller="headerController" data-ng-clock  class="sidebar-collapse">
       
   <div class="modal fade" id="notificationlModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Edit Notification </h4>
      </div> 
    </div>
  </div>
</div>
   <s:if test="%{#session.User!=null && #session.Company!=null}">
   <input type="hidden" id="notiCreatedUserId" name="userId"
								value="<s:property value="#session.User.id" />"> 
								<input name="companyId"
								type="hidden" id="notiCreatedCompanyId"
								value="<s:property value="#session.User.Companyid" />">
							 
   
 <s:if test="%{#session.User.userrole_id.isSuperuser()}">
      <header class="main-header">
        <!-- Logo -->
        <%--  <s:if test="<s:text name="global.IsLintas" ></s:text> == true">
        <label>some</label>
        </s:if> --%>
        <a href="home" class="logo">
         
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">  
          <form action="dashboardsearchmanager" method="post" class="ng-pristine ng-valid dashsearch"> 
								
						           <div id="custom-search-input">
						                            <div class="input-group col-md-11">
						                                <input type="text" id="search" class="search-query form-control" name="searchingvalue" value="" minlength="3" maxlength="25" placeholder="Search like PNR/OrderId/Invoice No" required="required" autocomplete="off">
						                                <span class="input-group-btn">
						                                    <button class="btn btn-danger" type="submit">
						                                        <span class=" glyphicon glyphicon-search"></span>
						                                    </button>
						                                </span>
						                            </div>
						                        </div>
						 
						 
						</form>
          
            <ul class="nav navbar-nav">
  	            <s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
             <li >
          <div class="emulated">
          		Emulated By Company :  <s:property value="#session.emulateByCompany.companyname" /> &nbsp;&nbsp;&nbsp;
  					Emulated By User :  <s:property value="#session.emulateByUser.username" /> &nbsp;&nbsp;&nbsp;
  					 <form action="companylogin" id="logForm" method="post" class="form-horizontal">
  					   <input type="hidden" name="Email"     id="email"  class="form-control input-sm" value='<s:property value="#session.emulateByUser.email" />'>
		               <input type="hidden" name="Password"  id="Password"   class="form-control input-sm" value='<s:property value="#session.emulateByUser.password" />'>
        		      <input type="hidden" name="company_userid"  class="form-control input-sm" value='<s:property value="#session.emulateByCompany.company_userid" />'>
                       <button type="submit" class="btn btn-primary btn-xs">Switch Back</button>
                        &nbsp;&nbsp;&nbsp;
					</form>						   
	           
          </div>
          </li>
		</s:if>
		      <!--  <li>
               <div id="showEmulateBox">
   					Emulate
			   </div>
	          </li>    -->
           <%--  <li class="admin-wallet"> <a href="#" onclick="ibeform();">IBE</a> 
             <input type="hidden" id="url" value="<s:text name="global.ibeconnecturl" ></s:text>">
           <input type="hidden" id="thelink" value="<s:property value="#session.Encryptedid" />">
            </li> 
              --%>
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                 <s:if test="%{#session.Company.Imagepath == null}"> 
                  <img check-image ng-src="images/default.png" class="user-image" />
				  </s:if>  
                 <s:else>
                  <img check-image ng-src="<s:url action='ImageAction?imageId=%{#session.Company.Imagepath}'/>" class="user-image" />
                   </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <li class="user-footer">
                      <a href="userorcompanyprofile"  class="block"><i class="fa fa-user"></i> Profile</a>
                       <a href="logout" class="block"><i class="fa fa-sign-out"></i> Sign out</a>
                  </li>
                </ul>
              </li>     
              <li>
	   			  <span class="white-color">  Credit : <span id="walletAmount"> <fmt:formatNumber type="number" pattern="###.00" value="${session.User.agentWallet.walletbalance}" /></span>  </span>
   			  </li>
		   		<li>	  
	              <span class="white-color">  Deposit : <span id="depositBalance"> <fmt:formatNumber type="number" pattern="###.00" value="${session.User.agentWallet.depositBalance}" /></span>  </span>
               </li>  
              <li class="dropdown notify">
               <%--  <a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown"  >
                  <span class="icon-with-child ">
                    <span class="fa fa-bell-o fa-lg"></span>
                    <span class="badge badge-danger badge-above right" data-ng-cloak>{{notificationcount}}</span>
                  </span>
                   <span class="visible-xs-block">
                    <span class="fa fa-bell fa-lg fa-fw"></span>
                    <span class="badge badge-danger pull-right"  data-ng-cloak>{{notificationcount}}</span>
                    Notifications
                  </span> 
                </a>hidden-xs  --%>
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg">
                  <div class="dropdown-header">
                    <a class="dropdown-link" href="#" data-ng-click="markallasread()">Mark all as read</a>
                    <h5 class="dropdown-heading">Recent Notifications</h5>
                  </div>
                  <div class="dropdown-body">
                    <div class="custom-scrollable-area" style=" width: 100%; height: 100%;"><div class="list-group list-group-divided custom-scrollbar" style="width: 100%; height: 100%;">
                       <div class="list-group-item clearfix"  data-ng-repeat="notify in notifylist" ><!-- data-ng-click="showdetail(notify)" -->
                        <a class="notification " data-ng-click="showdetail(notify)" href="#">
                          <div class="notification-media">
                            <img class="rounded" width="40" height="40" src="{{getImageUrl(notify)}}" alt="Tayyarah-Image">
                          </div>
                          <div class="notification-content">
                            <small class="notification-timestamp" style="color: #999;">{{convertMS(notify.createdAt)}}</small>
                            <h5 class="notification-heading">{{notify.description}}</h5>
                            <p class="notification-text" data-ng-repeat="detail in notify.details" >                                                       
                              <small class="truncate"><b>{{detail.description}} :</b><b> {{notify.createdby}} </b> {{getdetailsinfo(notify)}} {{detail.inventoryId}} </small>
                            </p>
                          </div>
                        </a>
                        
                         <!-- added by basha for creating a buttons are view and edit -->
                      <div class="clearfix notification-btn">
                       <a class="btn btn-success btn-xs" href="#" data-ng-click="showdetail(notify)"> View </a>
                    <!--   <a class="btn btn-warning btn-xs" href="#" data-ng-click="notificationEdit(notify)"> Edit </a> -->
                      </div>     
                        </div>    
                    </div><div class="custom-scrollbar-gripper" style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 87px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 4px; height: 216.939px;"></div><div class="custom-scrollbar-track" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 4px;"></div></div>
                  </div>
                  <div class="dropdown-footer">
                    <a class="dropdown-btn" href="Notifications">See All</a>
                    <!-- added by basha for insert a custom notifications  -->
                    <a class="btn btn-danger btn-xs" href="goNotificationPage"> Create Notification </a>
                    </div>
                </div>
              </li>        
            </ul>
          </div>
        </nav>
     
      </header>
      </s:if>
         <s:elseif test="%{((#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) ||(#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent() || #session.Company.companyRole.isCorporate())) && (#session.User.userrole_id.isAdmin() || #session.User.userrole_id.isReports() ||  #session.User.userrole_id.isOrder() || #session.User.userrole_id.isCms() ||#session.User.userrole_id.isTravelDesk() ||  #session.User.userrole_id.isTechSupport() || #session.User.userrole_id.isTechHead())}">
         <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
          <s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
             <li >
          <div class="emulated">
          		Emulated By Company :  <s:property value="#session.emulateByCompany.companyname" /> &nbsp;&nbsp;&nbsp;
  					Emulated By User :  <s:property value="#session.emulateByUser.username" /> &nbsp;&nbsp;&nbsp;
  					 <form action="companylogin" id="logForm" method="post" class="form-horizontal">
  					   <input type="hidden" name="Email"     id="email"  class="form-control input-sm" value='<s:property value="#session.emulateByUser.email" />'>
		               <input type="hidden" name="Password"  id="Password"   class="form-control input-sm" value='<s:property value="#session.emulateByUser.password" />'>
        		      <input type="hidden" name="company_userid"  class="form-control input-sm" value='<s:property value="#session.emulateByCompany.company_userid" />'>
                       <button type="submit" class="btn btn-primary btn-xs">Switch Back</button>
                        &nbsp;&nbsp;&nbsp;
					</form>						   
	           
          </div>
          </li>
		</s:if> 
		
				<%--  <s:if test="%{((#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false)&&(#session.User.userrole_id.isAdmin() ||  #session.User.userrole_id.isOrder()||  #session.User.userrole_id.isTechSupport() || #session.User.userrole_id.isTechHead()))}">
				 	<li >
		               <div id="showEmulateBox">
		   					Emulate
					   </div>
	          		</li> 
				     <li class="admin-wallet"> <a href="#" onclick="ibeform();">IBE</a>
				          <input type="hidden" id="url" value="<s:text name="global.ibeconnecturl" ></s:text>">
           <input type="hidden" id="thelink" value="<s:property value="#session.Encryptedid" />"> 
            </li>	   
				 </s:if> --%>
				 
				<%--  <s:if test="%{#session.User.userrole_id.isReports() && #session.Company.companyRole.isSuperUser()}">
             <li >
               <div id="showEmulateBox">
   					Emulate 
			   </div>
	          </li> 
              </s:if> --%>
				 
				 <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <s:if test="%{#session.Company.Imagepath == null}"> 
                  <img check-image ng-src="images/default.png" class="user-image" />
				  </s:if>  
                 <s:else>
                  <img check-image ng-src="<s:url action='ImageAction?imageId=%{#session.Company.Imagepath}'/>" class="user-image" />
                  </s:else>
                  <span class="hidden-xs"> <s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <li class="user-footer">
                     <a href="userorcompanyprofile"  class="block"><i class="fa fa-user"></i> Profile</a>
                       <a href="logout" class="block"><i class="fa fa-sign-out"></i> Sign out</a>
                  </li>
                </ul>
              </li>    
            	 <li>
	   			  <span class="white-color">  Credit : <span id="walletAmount"> <fmt:formatNumber type="number" pattern="###.00" value="${session.User.agentWallet.walletbalance}" /> INR</span>  </span>
   			  </li>
		   		<li>	  
	              <span class="white-color">  Deposit : <span id="depositBalance"> <fmt:formatNumber type="number" pattern="###.00" value="${session.User.agentWallet.depositBalance}" /> INR</span>  </span>
               </li> 
                 <li class="dropdown notify">
               <%--  <a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown"  >
                  <span class="icon-with-child ">
                    <span class="fa fa-bell-o fa-lg"></span>
                    <span class="badge badge-danger badge-above right" data-ng-clock>{{notificationcount}}</span>
                  </span>
                <span class=" hidden-xs">
                    <span class="fa fa-bell fa-lg fa-fw"></span>
                    <span class="badge badge-danger pull-right" data-ng-clock>{{notificationcount}}</span>
                    Notifications
                  </span>  
                </a> --%> <!-- visible-xs-block -->
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg">
                  <div class="dropdown-header">
                    <a class="dropdown-link" href="#">Mark all as read</a>
                    <h5 class="dropdown-heading">Recent Notifications</h5>
                  </div>
                  <div class="dropdown-body">
                    <div class="custom-scrollable-area" style=" width: 100%; height: 100%;"><div class="list-group list-group-divided custom-scrollbar" style="width: 100%; height: 100%;">
                      <div class="list-group-item clearfix"  data-ng-repeat="notify in notifylist" ><!-- data-ng-click="showdetail(notify)" -->
                        <a class="notification " data-ng-click="showdetail(notify)" href="#">
                          <div class="notification-media">
                            <img class="rounded" width="40" height="40" src="{{getImageUrl(notify)}}" alt="Daniel Taylor">
                          </div>
                          <div class="notification-content">
                            <small class="notification-timestamp" style="color: #999;">{{convertMS(notify.createdAt)}}</small>
                            <h5 class="notification-heading">{{notify.description}}</h5>
                            <p class="notification-text" data-ng-repeat="detail in notify.details" >                                                       
                              <small class="truncate"><b>{{detail.description}} :</b><b> {{notify.createdby}} </b> {{getdetailsinfo(notify)}} {{detail.inventoryId}} </small>
                            </p>
                          </div>
                        </a>
                      <div class="clearfix notification-btn">
                       <a class="btn btn-success btn-xs" href="#" data-ng-click="showdetail(notify)"> View </a>
                   <!--    <a class="btn btn-warning btn-xs" href="#" data-ng-click="notificationEdit(notify)"> Edit </a> -->
                      </div>     
                        </div>    
                             
                    </div><div class="custom-scrollbar-gripper" style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 87px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 4px; height: 216.939px;"></div><div class="custom-scrollbar-track" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 4px;"></div></div>
                  </div>
                  <div class="dropdown-footer">
                    <a class="dropdown-btn" href="Notifications">See All</a>
                     <a class="btn btn-danger btn-xs" href="goNotificationPage"> Create Notification </a>
                  </div>
                </div>
              </li>
            
            </ul>
          </div>
        </nav>
     
      </header>
      </s:elseif>
      <s:elseif
	test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent() || #session.Company.companyRole.isCorporate()}">
         <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
           </a>
       <div class="navbar-custom-menu">
      <s:if test="%{#session.User.userrole_id.isDemoUser()==true}">	
       <h4 class="modal-title" style="color:white">DEMO ACCOUNT</h4>
       </s:if>
            <ul class="nav navbar-nav">
            <s:if test="%{#session.Company.companyRole.isCorporate()}">
				<span class="admin-wallet" id="agExpiry">Agreement Expiry Date: <span class="agreementExipry"><s:property value="#session.Company.agreementTranExpiryDate"/></span>	</span>
				</s:if>
            <s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
             <li >
          <div class="emulated">
          		Emulated By Company :  <s:property value="#session.emulateByCompany.companyname" /> &nbsp;&nbsp;&nbsp;
  					Emulated By User :  <s:property value="#session.emulateByUser.username" /> &nbsp;&nbsp;&nbsp;
  					 <form action="companylogin" id="logForm" method="post" class="form-horizontal">
  					   <input type="hidden" name="Email"     id="email"  class="form-control input-sm" value='<s:property value="#session.emulateByUser.email" />'>
		               <input type="hidden" name="Password"  id="Password"   class="form-control input-sm" value='<s:property value="#session.emulateByUser.password" />'>
        		      <input type="hidden" name="company_userid"  class="form-control input-sm" value='<s:property value="#session.emulateByCompany.company_userid" />'>
                       <button type="submit" class="btn btn-primary btn-xs">Switch Back</button>
                        &nbsp;&nbsp;&nbsp;
					</form>						   
	           
          </div>
          
          </li>
		</s:if>  
		    <%-- <s:if test="%{#session.User.userrole_id.isSuperuser()}">
             <li >
               <div id="showEmulateBox">
   					Emulate 
			   </div>
	          </li> 
              </s:if> --%>
              <%-- <li class="admin-wallet"> <a href="#" onclick="ibeform();">IBE</a> 
               <input type="hidden" id="url" value="<s:text name="global.ibeconnecturl" ></s:text>">
           <input type="hidden" id="thelink" value="<s:property value="#session.Encryptedid" />">
              </li> --%>
              
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <s:if test="%{#session.Company.Imagepath == null}"> 
                  <img check-image ng-src="images/default.png" class="user-image" />
				  </s:if>  
                 <s:else>
                  <img check-image ng-src="<s:url action='ImageAction?imageId=%{#session.Company.Imagepath}'/>" class="user-image" />
                  </s:else>
                  <span class="hidden-xs"> <s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <li class="user-footer">
                     <a href="userorcompanyprofile"  class="block"><i class="fa fa-user"></i> Profile</a>
                       <a href="logout" class="block"><i class="fa fa-sign-out"></i> Sign out</a>
                  </li>
                </ul>
              </li>    
              
             <li>
	   			  <span class="white-color">  Credit : <span id="walletAmount"> <fmt:formatNumber type="number" pattern="###.00" value="${session.User.agentWallet.walletbalance}" /> INR</span>  </span>
   			  </li>
		   		<li>	  
	              <span class="white-color">  Deposit : <span id="depositBalance"> <fmt:formatNumber type="number" pattern="###.00" value="${session.User.agentWallet.depositBalance}" /> INR</span>  </span>
               </li>
               <s:if test="%{#session.User.agentWallet.walletType == 'Postpaid'}">
             	 <input type="hidden"  id="creditRange" value="<s:property value="%{#session.User.agentWallet.walletBalanceRange}"/>">
                <li class="admin-wallet" > 
                		<span class="white-color">My Credit Range : <span id="walletAmount1"> 
                       </span></span> 
				</li> 
              </s:if> 
               
               <li class="dropdown notify">
               <%--  <a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown"  >
                  <span class="icon-with-child">
                    <span class="fa fa-bell-o fa-lg"></span>
                    <span class="badge badge-danger badge-above right" data-ng-cloak>{{notificationcount}}</span>
                  </span>
                <span class="visible-xs-block">
                    <span class="fa fa-bell fa-lg fa-fw"></span>
                    <span class="badge badge-danger pull-right" data-ng-cloak>{{notificationcount}}</span>
                    Notifications
                  </span>  
                </a> hidden-xs--%>
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg">
                  <div class="dropdown-header">
                    <a class="dropdown-link" href="#">Mark all as read</a>
                    <h5 class="dropdown-heading">Recent Notifications</h5>
                  </div>
                  <div class="dropdown-body">
                    <div class="custom-scrollable-area" style=" width: 100%; height: 100%;"><div class="list-group list-group-divided custom-scrollbar" style="width: 100%; height: 100%;">
                       <div class="list-group-item clearfix"  data-ng-repeat="notify in notifylist" ><!-- data-ng-click="showdetail(notify)" -->
                        <a class="notification " data-ng-click="showdetail(notify)" href="#">
                          <div class="notification-media">
                            <img class="rounded" width="40" height="40" src="{{getImageUrl(notify)}}" alt="Daniel Taylor">
                          </div>
                          <div class="notification-content">
                            <small class="notification-timestamp" style="color: #999;">{{convertMS(notify.createdAt)}}</small>
                            <h5 class="notification-heading">{{notify.description}}</h5>
                            <p class="notification-text" data-ng-repeat="detail in notify.details" >                                                       
                              <small class="truncate"><b>{{detail.description}} :</b><b> {{notify.createdby}} </b> {{getdetailsinfo(notify)}} {{detail.inventoryId}} </small>
                            </p>
                          </div>
                        </a> 
                      <div class="clearfix notification-btn">
                       <a class="btn btn-success btn-xs" href="#" data-ng-click="showdetail(notify)"> View </a>
                    <!--   <a class="btn btn-warning btn-xs" href="#" data-ng-click="notificationEdit(notify)"> Edit </a> -->
                      </div>     
                        </div>    
                             
                    </div><div class="custom-scrollbar-gripper" style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 87px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 4px; height: 216.939px;"></div><div class="custom-scrollbar-track" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 4px;"></div></div>
                  </div>
                  <div class="dropdown-footer">
                    <a class="dropdown-btn" href="Notifications">See All</a>
                     <a class="btn btn-danger btn-xs" href="goNotificationPage"> Create Notification </a>
                      </div>
                </div>
              </li>
            
            </ul>
          </div>
        </nav>
        
        
        
     
      </header>
      </s:elseif>  
   <%--    <s:else>
       <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
                   <li class="dropdown notify">
                <a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown"  >
                  <span class="icon-with-child hidden-xs">
                    <span class="fa fa-bell-o fa-lg"></span>
                    <span class="badge badge-danger badge-above right" data-ng-clock>{{notificationcount}}</span>
                  </span>
                  <span class="visible-xs-block">
                    <span class="fa fa-bell fa-lg fa-fw"></span>
                    <span class="badge badge-danger pull-right" data-ng-clock>{{notificationcount}}</span>
                    Notifications
                  </span>
                </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg">
                  <div class="dropdown-header">
                    <a class="dropdown-link" href="#" data-ng-click="markallasread()" >Mark all as read</a>
                    <h5 class="dropdown-heading">Recent Notifications</h5>
                  </div>
                  <div class="dropdown-body">
                    <div class="custom-scrollable-area" style=" width: 100%; height: 100%;"><div class="list-group list-group-divided custom-scrollbar" style="width: 100%; height: 100%;">
                     <div class="list-group-item clearfix"  data-ng-repeat="notify in notifylist" ><!-- data-ng-click="showdetail(notify)" -->
                        <a class="notification " data-ng-click="showdetail(notify)" href="#">
                          <div class="notification-media">
                            <img class="rounded" width="40" height="40" src="{{getImageUrl(notify)}}" alt="Daniel Taylor">
                          </div>
                          <div class="notification-content">
                            <small class="notification-timestamp" style="color: #999;">{{convertMS(notify.createdAt)}}</small>
                            <h5 class="notification-heading">{{notify.description}}</h5>
                            <p class="notification-text" data-ng-repeat="detail in notify.details" >                                                       
                              <small class="truncate"><b>{{detail.description}} :</b><b> {{notify.createdby}} </b> {{getdetailsinfo(notify)}} {{detail.inventoryId}} </small>
                            </p>
                          </div>
                        </a>
                        
                         
                      <div class="clearfix notification-btn">
                       <a class="btn btn-success btn-xs" href="#" data-ng-click="showdetail(notify)"> View </a>
                     <!--  <a class="btn btn-warning btn-xs" href="#" data-ng-click="notificationEdit(notify)"> Edit </a> -->
 
                      </div>     
                              
                        </div>    
                             
                    </div><div class="custom-scrollbar-gripper" style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 87px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 4px; height: 216.939px;"></div><div class="custom-scrollbar-track" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 4px;"></div></div>
                  </div>
                  <div class="dropdown-footer">
                    <a class="dropdown-btn" href="Notifications">See All</a>
                     <a class="btn btn-danger btn-xs" href="goNotificationPage"> Create Notification </a>
                  </div>
                </div>
              </li>
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
              <li class="admin-wallet"> <h5>My custom noti: : <span id="customnoti"> </span></h5> </li> 
                <s:if test="%{#session.userWallet!=null}">
               <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.userWallet.walletbalance}"/> <s:property value="%{#session.userWallet.currencyCode}"/></h5> </li>
              </s:if>
              <s:else>
                  <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.User.agentWallet.walletbalance}"/> <s:property value="%{#session.User.agentWallet.currencyCode}"/></h5> </li>
                </s:else>
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                  <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
                 <s:else>
                   <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="user-image" alt="User Image" />
                   </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <li class="user-footer">
                    <a href="userorcompanyprofile"  class="block"><i class="fa fa-user"></i> Profile</a>
                       <a href="logout" class="block"><i class="fa fa-sign-out"></i> Sign out</a>
                  </li>
                </ul>
              </li>              
              
            </ul>
          </div>
        </nav>
     
      </header>
      </s:else> --%>
        
  <!--  USER PANEL ACCORDING TO USER ROLE as admin -->
  <%-- <s:elseif  test="%{#session.User!=null  && #session.Company!=null}"> --%>
<%--   
  <s:if test="%{(#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms())|| 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isCms()) || 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder())|| (#session.User.userrole_id.isAdmin())}">
  <s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
	  <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
                <s:if test="%{#session.userWallet!=null}">
               <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.userWallet.walletbalance}"/> <s:property value="%{#session.userWallet.currencyCode}"/></h5> </li>
              </s:if>
              <s:else>
                  <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.User.agentWallet.walletbalance}"/> <s:property value="%{#session.User.agentWallet.currencyCode}"/></h5> </li>
                </s:else>
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                  <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
                 <s:else>
                   <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="user-image" alt="User Image" />
                   </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                  <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="img-circle" alt="No Image" />
				  </s:if> 
				  <s:else>
                 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
                 </s:else>
                    <p>
                    <s:property value="%{#session.User.Username}"/>
                     
                    </p>
                  </li>
                 
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="userorcompanyprofile" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>              
              
            </ul>
          </div>
        </nav>
     
      </header>
	</s:if>
	<s:else>
	 <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
             
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                 <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
                 <s:else>
                  <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="user-image" alt="User Image" />
                  </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                  <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="img-circle" alt="No Image" />
				  </s:if> 
				  <s:else>
                   <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
                   </s:else>
                    <p>
                    <s:property value="%{#session.User.Username}"/>
                      Harsha - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="userorcompanyprofile" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>              
              
            </ul>
          </div>
        </nav>
     
      </header>
	 </s:else>
	 </s:if>
  </s:if> --%>
   
		<%--  <s:elseif test="%{(#session.User.userrole_id.isReports() && #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isReports() && #session.User.userrole_id.isOrder())}">
	<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	<s:if test="%{#session.Company.companyRole.isDistributor()|| #session.Company.companyRole.isAgent()}">
	  <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
                <s:if test="%{#session.userWallet!=null}">
               <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.userWallet.walletbalance}"/> <s:property value="%{#session.userWallet.currencyCode}"/></h5> </li>
              </s:if>
              <s:else>
                  <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.User.agentWallet.walletbalance}"/> <s:property value="%{#session.User.agentWallet.currencyCode}"/></h5> </li>
                </s:else>
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
				  <s:else>
                 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="user-image" alt="User Image" />
                 </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                   <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="img-circle" alt="No Image" />
				  </s:if> 
				  <s:else>
                   <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
                   </s:else>
                    <p>
                    <s:property value="%{#session.User.Username}"/>
                      Harsha - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  <!-- Menu Body -->
                 
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="userorcompanyprofile" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>              
              
            </ul>
          </div>
        </nav>
     
      </header>
	
	</s:if>
	 
	   <s:else>
	 	   <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
                <s:if test="%{#session.userWallet!=null}">
               <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.userWallet.walletbalance}"/> <s:property value="%{#session.userWallet.currencyCode}"/></h5> </li>
              </s:if>
              <s:else>
                  <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.User.agentWallet.walletbalance}"/> <s:property value="%{#session.User.agentWallet.currencyCode}"/></h5> </li>
                </s:else>
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                 <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
				  <s:else>
                 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="user-image" alt="User Image" />
                 </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                   <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="img-circle" alt="No Image" />
				  </s:if> 
				  <s:else>
                  <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />  
                  </s:else>                  
                  <p>
                    <s:property value="%{#session.User.Username}"/>
                      Harsha - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                 
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="userorcompanyprofile" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>              
              
              
            </ul>
          </div>
        </nav>
     
      </header>
	   </s:else>
	  </s:if>
	</s:elseif> --%>
	 <%--  <s:elseif  test="%{(#session.User.userrole_id.isReports() && #session.User.userrole_id.isCms()) 
	  || (#session.User.userrole_id.isReports())}">
	<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	<s:if test="%{#session.Company.companyRole.isDistributor()|| #session.Company.companyRole.isAgent()}">
	  <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
                
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
				  <s:else>
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                  </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="<s:property value="%{#session.User.Imagepath}"/>" class="img-circle" alt="User Image" />
                   <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="img-circle" alt="No Image" />
				  </s:if> 
				  <s:else>
                   <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
                   </s:else>
                    <p>
                    <s:property value="%{#session.User.Username}"/>
                     
                    </p>
                  </li>
                  <!-- Menu Body -->
                 
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="userorcompanyprofile" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>              
              
            </ul>
          </div>
        </nav>
     
      </header>
	
	</s:if>
	 
	   <s:else>
	 	   <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
                
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                   <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
				  <s:else>
                   <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="user-image" alt="User Image" />
                   </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                  <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="img-circle" alt="No Image" />
				  </s:if> 
				  <s:else>
                   <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
                    </s:else>
                    <p>
                    <s:property value="%{#session.User.Username}"/>
                      Harsha - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="userorcompanyprofile" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>              
              
              
            </ul>
          </div>
        </nav>
     
      </header>
	   </s:else>
	  </s:if>
	</s:elseif> --%>
 
	<%-- <s:elseif test="%{(#session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isOrder())}">
	<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
	   <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
                
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                  <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
				   <s:else>
                  <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="user-image" alt="User Image" />
                   </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                   <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="img-circle" alt="No Image" />
				  </s:if> 
				   <s:else>
                   <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
                    </s:else>
                    <p>
                    <s:property value="%{#session.User.Username}"/>
                      Harsha - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  <!-- Menu Body -->
                 
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="userorcompanyprofile" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>              
              
            </ul>
          </div>
        </nav>
     
      </header>
	 </s:if>
	 
	 
	   <s:else>
	 	   <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
                
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                 <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
				   <s:else>
                 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="user-image" alt="User Image" />
                 </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                   <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="img-circle" alt="No Image" />
				  </s:if> 
				   <s:else>
                    <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
                    </s:else>
                    <p>
                    <s:property value="%{#session.User.Username}"/>
                     
                    </p>
                  </li>
                  <!-- Menu Body -->
                
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="userorcompanyprofile" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>              
              
            </ul>
          </div>
        </nav>
     
      </header>
	   </s:else>
	  </s:if>
	</s:elseif> --%>
	<%--  <s:elseif test="%{#session.User.userrole_id.isCms()}">
	<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	 
	   <header class="main-header">
        <!-- Logo -->
        <a href="home" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="admin-wallet"> <h5>My Wallet : <span id="walletAmount"> </span></h5> </li> 
                <s:if test="%{#session.userWallet!=null}">
               <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.userWallet.walletbalance}"/> <s:property value="%{#session.userWallet.currencyCode}"/></h5> </li>
              </s:if>
              <s:else>
                  <li class="admin-wallet"> <h5>My Wallet : <s:property value="%{#session.User.agentWallet.walletbalance}"/> <s:property value="%{#session.User.agentWallet.currencyCode}"/></h5> </li>
                </s:else>
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<s:property value="%{#session.User.Imagepath}"/>" class="user-image" alt="User Image" />
                 <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="user-image" alt="No Image" />
				  </s:if> 
				   <s:else>
                  <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="user-image" alt="User Image" />
                  </s:else>
                  <span class="hidden-xs"><s:property value="%{#session.User.Username}"/></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                  <s:if test="%{#session.User.Imagepath == null}"> 
                  <img src="images/default.png" class="img-circle" alt="No Image" />
				  </s:if> 
				   <s:else>
                   <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
                   </s:else>
                    <p>
                    <s:property value="%{#session.User.Username}"/>
                      
                    </p>
                  </li>
                 
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="userorcompanyprofile" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>              
              
            </ul>
          </div>
        </nav>
     
      </header>
	 </s:if>
	 
	</s:elseif> --%>
	
 <%-- </s:elseif> --%>
 </s:if>
 

   
 </body>
 
  <script> 
     $(document).ready(function(){
    	 var isCorporate = ${Company.companyRole.isCorporate()}; 
    	 if(isCorporate=true){
	         var session = $(".agreementExipry").text();
	         var d = new Date(); 
	         var strDate = d.getDate()+ "-" + (d.getMonth()+1)+ "-" + d.getFullYear() ;  
	         /** Get Days Between Two Dates **/ 
	             function parseDate(str) {
	             var mdy = str.split('-');
	             return new Date(mdy[2], mdy[1]-1, mdy[0]);
	         }
	
	         function daydiff(first, second) {
	             return (second-first)/(1000*60*60*24);
	         }
	
	         var agrementDiffCheck= daydiff(parseDate(strDate), parseDate( session));
	         if(agrementDiffCheck<=2){
	        	 $("#agExpiry").css('color', 'red');
	         } 
    	 } 
    	 var isDistributor = ${Company.companyRole.isDistributor()}; 
    	 if(isDistributor=true){
    		 $(".distHide").css('display', 'none');
    	 }else{
    		 $(".distHide").css('display', 'block'); 
    	 }
     });   
     </script> 
 </html>
	 