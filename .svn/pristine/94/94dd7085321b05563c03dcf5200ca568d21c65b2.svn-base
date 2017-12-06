<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <style>
.popupnotifiy {
	width: 100%;
	padding-top: 5px;
	margin: 0px;
	padding-bottom: 0px;
	padding-left: 10px;
	border-bottom: 1px solid #ccc;
	min-height: 62px;
	padding-right: 10px;
	color: #555;
}

.popupnotifiy:hover {
	background: #f1f1f1;
}

.minheight200px {
	min-height: 350px;
	height: 376px;
	overflow-y: scroll;
	word-break: break-word;
}

.popupnotifiy p {
	margin-bottom: 2px;
}

.popupnotifiy .popnotdetails {
	font-size: 13px;
	margin-top: 5px;
}

.popupnotifiy  span {
	color: #3c8dbc;
	font-size: 11px;
}

.popupnotifiy b span {
	color: #3c8dbc;
	font-size: 11px;
}
.customFont{
font-size:15px;
}
.scrollContent{
overflow-y:scroll;
height: 378px;
}
#detnotifydetail .md-message{
margin-top: 10px;
}
#detnotifydetail .md-craete  {
font-size: 12px;
}
</style>
 
</head>
<!-- Created by basha this popup custom notification box and popup edit and expire -->
 
<!-- <body  data-ng-app="NotifyAPP"  data-ng-controller="headerController"> -->
 
<body  data-ng-app="NotifyAPP"  data-ng-controller="headerController">
<div data-ng-show="CounterMore" class="round hollow text-center">
 <input type="hidden" id="notifyUserId" name="userId"
  value="<s:property value="#session.User.id" />" /> <input
  name="companyId" type="hidden" id="notifyCompanyId"
  value="<s:property value="#session.User.Companyid" />"> <%-- <a
  id="openNotifyClass" data-ng-click="openNotiffy();$event.stopPropagation(); " href="javascript:void()"><span
  class="glyphicon glyphicon-comment cutomnotifyicon" ></span>
   <span data-ng-bind="customnotificationcount" class="customnotificationcount"></span>
   </a>
 --%>
</div>

<div class="popup-box" id="qnimate">
	<div class="popup-head">
		<div class="popup-head-left pull-left">Notifications
		 </div> 
		 
		<div class="popup-head-right pull-right">
		<a class="btn btn-danger btn-xs" href="#" data-ng-click="markallasreadcustomnotifications()"> Mark all as read</a>
			
			<button data-widget="remove" data-ng-click="removeNotification()"
				class="chat-header-button pull-right" type="button">
				<i class="glyphicon glyphicon-off"></i>
			</button>
		</div>
	</div>
	
	
	
	
	
	<div class="scrollContent">
	<!-- <div class="popup-messages "  data-ng-repeat="notifydetails in customnotificationArray track by $index" data-ng-click="noftifybyid(notifydetails)"> -->
	<div class="popup-messages "  data-ng-repeat="notifydetails in customnotificationArray track by $index" >
	
	<div  id='{{$index}}' class="popupnotifiy"  >
	<p class="clearfix"><b><span class="pull-left "> {{notifydetails.details[0].title}} </span><small class="pull-right">  {{notifydetails.createdby}}  </small></b> 
	</p> 
	<p class="popnotdetails"> {{notifydetails.details[0].comments}} </p><span class="pull-right">{{notifydetails.createdAt | date:'medium'}} </span>
	<div>
	<button type="button" data-ng-click="noftifybyid(notifydetails)"  class="btn btn-default btn-xs" data-dismiss="modal">View</button>
	<button type="button" data-ng-click="notificationEditClickpopup(notifydetails)"  class="btn btn-default btn-xs" data-dismiss="modal">Edit</button>
	 <button type="button" data-ng-click="expireCount(notifydetails.notificationId)"  class="btn btn-default btn-xs" data-dismiss="modal">Close</button></b> 
	</p> </div>
			 
	</div>
	
	</div>
	
	</div>
</div>


<div class="modal fade"  data-backdrop="static" data-keyboard="false" id="detnotifydetail" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			 <input type=hidden id="Notifyid" name="Notifyid" value="" data-ng-bind="notificationId">
				 
				<!-- <h4 class="modal-title"></h4> -->
				 <h4 class="modal-title text-center"><b>Title: <span data-ng-bind="title"></span></b> </h4>
				 
				 
			</div>
			<div class="modal-body ">
			<div class="clearfix">
			
			<div class="row clearfix md-craete">
					 <div class="col-md-6">
					 <b>Created By :</b> <span data-ng-bind="createdby"></span>
					 </div>	
					 <div class="col-md-6 text-right">
					  <b>Created At :</b> <span data-ng-bind="createdat | date:'medium'"></span>	
					 
					 </div> 

				</div>
				 
					 <%-- <h3 class="text-center"><b>Title: <span data-ng-bind="title"></span></b> </h3> --%>
					 
					 <div class="clearfix md-message">
					 <b>Message:</b> 
					 <p> <span data-ng-bind="comments"></span></p>	
					   
					 
					 </div>
					 
					 
				</div>
				</div>
			<div class="modal-footer">
			
			<input type="hidden" id="Notif_id" name="Notifyid" value="{{notificationId}}" > 
			<input type="hidden" id="title" name="title" value="{{title}}">
			<input type="hidden" id="comments" name="comments" value="{{comments}}">
			  <button class="btn btn-warning btn-xs" type="submit" data-ng-click="notificationEditClick()"> Edit </button>
			  <button type="button" data-ng-click="expireCount(notificationId)"  class="btn btn-default btn-xs" data-dismiss="modal">Close</button>
			 
			 
         </div>
      
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->




	
<footer class="main-footer">
       <div class="pull-right hidden-xs">
          
        </div>  
             <strong>Copyright &copy; 2014-2017 <a href="http://intellicommsolutions.com/">Intellicommsolutions</a>.</strong> All rights reserved.
      </footer>
      	
      	  <%-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
  <%--   <script src="js/bootstrap.min.js" type="text/javascript"></script> --%>
    <%-- <script type="text/javascript">
      $.widget.bridge('uibutton', $.ui.button);
    </script> --%>
     <script src="js/app.min.js" type="text/javascript"></script>
     <script src="dashboard-plugins/morris/morris.min.js" type="text/javascript"></script>
	 <script src="dashboard-plugins/morris/raphael-min.js" type="text/javascript"></script>
	 <script src="dashboard-plugins/jquery.sparkline.min.js" type="text/javascript"></script> 


<!-- END PAGE LEVEL PLUGINS -->
	<script src="dashboard-plugins/lintas.js" type="text/javascript"></script>
	<script src="dashboard-plugins/index3.js" type="text/javascript"></script>
	
	
    <script src="js/datatables/jquery.dataTables.min.js"type="text/javascript"></script>
    <script src="js/datatables/dataTables.buttons.min.js" type="text/javascript"></script>
    <script src="js/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script>
	<script src="js/datatables/buttons.bootstrap.min.js" type="text/javascript"></script>
	<script src="js/datatables/jszip.min.js" type="text/javascript"></script>
	<script src="js/datatables/pdfmake.min.js" type="text/javascript"></script>
	<script src="js/datatables/vfs_fonts.js" type="text/javascript"></script>
	<script src="js/datatables/buttons.html5.min.js" type="text/javascript"></script>
	<script src="js/datatables/buttons.print.min.js" type="text/javascript"></script>
	
	
	
	<script  src="js/jQuery.print.js"  type="text/javascript"  ></script>  
	<script src="js/jquery-ui.js"></script>
	<script src="js/hoteladdrooms.js"></script> 
	
      <script type="text/javascript">
		$(function() {
			if(document.getElementById('reportTypeHidden')!=null && document.getElementById('reportTypeHidden')!=undefined)
			{
				var  reportType=document.getElementById('reportTypeHidden').value;
			 	document.getElementById('reportType').value = reportType;
			}
		});
	</script>

 
 <script>
 function autoRefreshgetwallet()
 {
	 angular.element(document.getElementById('walletAmount')).scope();/*.getSystemNotification() */
	 var totUrl=$(location).attr('href');
		var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
		  var finalUrl = newUrl+"userWalletJson";
		 
    //a function which will load data from other file after x seconds
    			 $.ajax({
					    method: "POST",
					    url:finalUrl,
					  	success:function(data,status)
						{ 
				    	 console.log("walletbalance.."+data.walletRecord[0].walletbalance);
					     $("#walletAmount").text(numberWithCommas(data.walletRecord[0].walletbalance.toFixed(2))+" "+data.walletRecord[0].currencyCode);
                         if(data.walletRecord[0].depositBalance != undefined){
                              $("#depositBalance").text(numberWithCommas(data.walletRecord[0].depositBalance.toFixed(2))+" "+data.walletRecord[0].currencyCode);
						}else{
							 $("#depositBalance").text(numberWithCommas(0));
						}
                              var floatAmount=parseFloat(data.walletRecord[0].walletBalanceRange)
						 $("#walletAmount1").text(numberWithCommas(Math.abs(floatAmount.toFixed(2)))+" "+data.walletRecord[0].currencyCode);
						},
						error: function(xhr,status,error)
						{
							console.log(error);
						  
						}
					});  
  }
 
 function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
 $(function() {	 
	 autoRefreshgetwallet();
	 setInterval('autoRefreshgetwallet()',60000); // refresh div after 60 secs
 });
 $(function() {
	 
	 var floatAmount=parseFloat($("#creditRange").val())
	 $("#walletAmount1").text(numberWithCommas(Math.abs(floatAmount.toFixed(2))));
	 
	 
 });
  
     </script>
      <script>
 
 function ibeform(){ 
 var data = $('#thelink').val(); 
 var url = $('#url').val();
 var mapForm = document.createElement("form");
    mapForm.target = "Map";
    mapForm.method = "POST"; // or "post" if appropriate
    mapForm.action = url;

    var mapInput = document.createElement("input");
    mapInput.type = "text";
    mapInput.name = "encryptedadmindata";
    mapInput.value = data;
    mapForm.appendChild(mapInput);
	console.debug("data "+data);
	console.debug("url "+url);
    document.body.appendChild(mapForm);

    // map = window.open();
    mapForm.submit();
if (map) {
   // mapForm.submit();
   
} else {
  //  alert('You must allow popups for this map to work.');
}
 }
</script> 
  <script src="js/header.js?ver=3.0" ></script> 
  
  <script src="js/jquery-ui-timepicker-addon.min.js" type="text/javascript"></script>
 <script>
$("#notfiDateTime1").datetimepicker();
 $("#notfiDateTime2").datetimepicker();
 $('#notfiDateTime1').datetimepicker({
		timeFormat: "hh:mm:tt"
	}); 
 </script> 
 
 
 <script type="text/javascript">
 
  $(document).ready(function() {
	 $("#showEmulateBox").click(function () {
           $('#emulateModal').modal('show');
            var company_list = []; 
			  $.ajax({
				//Action Name
				url :"CompanyListUnderSuperUser",
				dataType : "json",
				data : {
				 parent_company_user_id : $("#companyUserId-emulate").val(),
					email : $("#email-emulate").val()
				},
				success : function(data, textStatus, jqXHR) {
					var items = data.records;
					for (var i = 0; i < data.records.length; i++) {
						dataValue = data.records[i];
						if(data.records[i].companyid!=loginCompanyId && $("#companyUserId-emulate").val()==data.records[i].parent_company_userid){
							   company_list.push(data.records[i].companyname);
								citymap[data.records[i].companyname] = data.records[i].companyid;
						   }
					}
					console.log(company_list);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			}); 
			  
			  $("#companyNameEmulate").autocomplete(
						{
							source : function(request, response) {
								var matcher = new RegExp('^'
										+ $.ui.autocomplete.escapeRegex(request.term),
										"i");
								response($.grep(company_list, function(item) {
									return matcher.test(item);

								}));
							},
							select: function (event, ui) {
								 			
								var  companyId = citymap[ui.item.value];
								  $('#emulateToCompanyId').val(companyId);
							 }
						});
            });
 }); 
 
  </script>
 
   
     <script>   
$(document).ready(function() {   
      var elements = document.querySelectorAll('input,select,textarea');

for (var i = elements.length; i--;) {
    elements[i].addEventListener('invalid', function () {
        this.scrollIntoView(false);
    });
}
 });
 
 
</script>  

 <script type="text/javascript">
  $(function() {
    $('#datetimepicker2').datetimepicker({
      language: 'en',
      pick12HourFormat: true
    });
  });
  $('#notfiDateTime1').datetimepicker({
		timeFormat: "hh:mm tt"
	});
  $(document).ready(function() {
		 $("#showEmulateBox").click(function () {
	           $('#emulateModal').show();
	            });

	$(function(){
		 $("#addClass").click(function () {
		           $('#qnimate').addClass('popup-box-on');
		             });
		             $("#removeClass").click(function () {
		           $('#qnimate').removeClass('popup-box-on');
		             });
		   })
  });
  $('.dropdown-toggle').dropdown(); 
  
  
  </script> 
<%-- <script>
var DEBUG = false;
if(!DEBUG){
    if(!window.console) window.console = {};
    var methods = ["log", "debug", "warn", "info"];
    for(var i=0;i<methods.length;i++){
        console[methods[i]] = function(){};
    }
}
</script>  --%>
  
 	

</body>
</html>

