<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- testing bty harsha -->
	<div id="nav" role="navigation"> 
	<!-- <a href="#nav" title="Show navigation">Show navigation</a>
	 <a href="#" title="Hide navigation">Hide navigation</a> -->
      <ul class="clearfix">
    <li><a href="javascript:void(0)"> <span>Flight</span></a> 
     <ul>
        <li><a href="companyReportList"> Report List</a> </li>
        <li><a href="companyFlightOrderList"> Order List</a></li>
        <!-- <li><a href="commissionReport"> Commission Report</a></li>
        <li><a href="getCustomerInvoiceList"> Customer Invoice(s)</a></li>
       <li> <a href="getSuperAgentCommInvoiceList"> Agent Invoice(s)</a></li> -->
      </ul>
    
    </li>
    <li> <a href="javascript:void(0)"><span>Hotel</span></a>
        <ul>
        <li><a href="companyHotelReports"> Report List</a> </li>
        <li><a href="companyHotelOrders"> Order List</a></li>
       <!--  <li><a href="hotelCommissionReport"> Commission Report</a></li> -->
        <!-- <li><a href="#"> Customer Invoice(s)</a></li>
       	<li> <a href="#"> Agent Invoice(s)</a></li> -->
      	</ul>
        </li>
    <li> <a href="javascript:void(0)"><span>Car</span></a>
          <ul>
        <li><a href="companyCarReports"> Report List</a> </li>
        <li><a href="companyCarOrders"> Order List</a></li>
       <!--  <li><a href="#"> Commission Report</a></li>
        <li><a href="#"> Customer Invoice(s)</a></li>
       <li> <a href="#"> Agent Invoice(s)</a></li> -->
      </ul>
        </li>
        
         <li> <a href="javascript:void(0)"><span>Bus</span></a>
          <ul>
         <li><a href="companyBusReports"> Report List</a> </li>
        <li><a href="companyBusOrders"> Order List</a></li>
       <!--  <li><a href="#"> Commission Report</a></li>
        <li><a href="#"> Customer Invoice(s)</a></li>
       <li> <a href="#"> Agent Invoice(s)</a></li> -->
      </ul>
        </li>
         <li> <a href="javascript:void(0)"><span>Train</span></a>
          <ul>
        <li><a href="companyTrainReports"> Report List</a> </li>
        <li><a href="companyTrainOrders"> Order List</a></li>
        <!-- <li><a href="#"> Commission Report</a></li>
        <li><a href="#"> Customer Invoice(s)</a></li>
       <li> <a href="#"> Agent Invoice(s)</a></li> -->
      </ul>
        </li>
         <li> <a href="javascript:void(0)"><span>Visa</span></a>
          <ul>
        <li><a href="companyVisaReports"> Report List</a> </li>
        <li><a href="companyVisaOrders"> Order List</a></li>
       <!--  <li><a href="#"> Commission Report</a></li>
        <li><a href="#"> Customer Invoice(s)</a></li>
       <li> <a href="#"> Agent Invoice(s)</a></li> -->
      </ul>
        </li>
         <li> <a href="javascript:void(0)"><span>Miscelaneous</span></a>
          <ul>
        <li><a href="companyMiscellaneousReports"> Report List</a> </li>
        <li><a href="companyMiscellaneousOrders"> Order List</a></li>
        <!-- <li><a href="#"> Commission Report</a></li>
        <li><a href="#"> Customer Invoice(s)</a></li>
       <li> <a href="#"> Agent Invoice(s)</a></li> -->
      </ul>
        </li>
         <li> <a href="javascript:void(0)"><span>Insurance </span></a>
          <ul>
         <li><a href="companyInsuranceReports"> Report List</a> </li>
        <li><a href="companyInsuranceOrders"> Order List</a></li>
        <!-- <li><a href="#"> Commission Report</a></li>
       	 <li><a href="#"> Customer Invoice(s)</a></li>
       	 <li> <a href="#"> Agent Invoice(s)</a></li> -->
      	</ul>
        </li> 
        
        <li class="admin-wallet"> <a href="#" onclick="ibeform();"> IBE  </a> 
             <input type="hidden" id="url" value="<s:text name="global.ibeconnecturl" ></s:text>">
           <input type="hidden" id="thelink" value="<s:property value="#session.Encryptedid" />">
            </li>  
        
        <s:if test="%{(#session.emulateByCompany!=null && #session.emulateByUser!=null) || (#session.User.userrole_id.isSuperuser())}">
         <li><a href="javascript:void(0)" > <span>Create Quick Trip  </span>  </a>
							<ul >
								<li><a href="goQuickLinkOfflibeHotelBookingPage">Hotel</a></li>
								<li><a href="goQuickLinkOfflibeFlightBookingPage">Flight</a></li>
								<!-- <li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>	
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>	 -->					
							</ul> 
					 </li>
					 
				<li><a href="javascript:void(0)" >
								<span >Create Trip </span>
							</a>
							<ul>
								<li><a href="goHotelTravelRequest">Hotel</a></li>
								<li><a href="goFlightTravelRequest">Flight</a></li>
								<li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>	
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>							
							</ul>
						 
					</li>
        <%-- </s:if>
                
       	            <s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}"> --%>
             <!-- <li> -->
            <%--  <div class="emulated">
          		Emulated By Company :  <s:property value="#session.emulateByCompany.companyname" /> &nbsp;&nbsp;&nbsp;
  					Emulated By User :  <s:property value="#session.emulateByUser.username" /> &nbsp;&nbsp;&nbsp;
  					 <form action="companylogin" id="logForm" method="post" class="form-horizontal">
  					   <input type="hidden" name="Email"     id="email"  class="form-control input-sm" value='<s:property value="#session.emulateByUser.email" />'>
		               <input type="hidden" name="Password"  id="Password"   class="form-control input-sm" value='<s:property value="#session.emulateByUser.password" />'>
        		      <input type="hidden" name="company_userid"  class="form-control input-sm" value='<s:property value="#session.emulateByCompany.company_userid" />'>
                       <button type="submit" class="btn btn-danger btn-xs">Switch Back</button>
                        &nbsp;&nbsp;&nbsp;
					</form>						   
	          
          </div> --%>
         <!--  </li> -->
		</s:if>
		<%-- <li >
               <span id="showEmulateBox">
   					Emulate 
			   </span>
	          </li>   --%> 
           
		 <%--  <s:if test="%{#session.Company.companyRole.isSuperUser()}">
		       <li >
               <div id="showEmulateBox">
   					Emulate 
			   </div>
	          </li>  
             </s:if>   --%>
            
             <s:if test="%{#session.Company.companyRole.isSuperUser() || (#session.Company.companyRole.isSuperUser() &&  (#session.User.userrole_id.isOrder() ||  #session.User.userrole_id.isReports() || #session.User.userrole_id.isAdmin() || #session.User.userrole_id.isTechSupport() || #session.User.userrole_id.isTechHead()))}">
             <li >
               <div id="showEmulateBox">
   					Emulate 
			   </div>
	          </li> 
              </s:if>
              
              
    
  </ul>
</div>


 <div class="modal fade"  data-backdrop="static" data-keyboard="false" id="emulateModal" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <h4 class="modal-title"></h4> -->
				 <h4 class="modal-title text-center"><b>Please select the company </b> </h4>
			</div>
			<div class="modal-body ">
			<div class="clearfix">
			<div class="clearfix md-craete">
	 			 <input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId-emulate"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email-emulate">
				<input type="hidden" 
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">  
					<input type="hidden"
					value="<s:property value="%{#session.Company.companyid}"/>"
					id="loginCompanyId">  
					  
					<form action="companyLoginEmulate">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Company Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="companyNameEmulate" placeholder="type company Name" >
														<input type="hidden" name="emulateToCompanyId"    id="emulateToCompanyId" value="0">
													</div>
												</div>
												<div class="emulate-btns text-right">
					 <input type="submit" value="Go" class="btn btn-success btn-xs" > 
					  <button type="button" class="btn btn-danger btn-xs" data-dismiss="modal">Close</button>
					  </div>
					 </form>
				 
				</div>
				</div>
				</div>
			<div class="modal-footer">
         </div>
      
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
  $(document).ready(function() {
	 $("#showEmulateBox").click(function () {
		 var loginCompanyId=$("#loginCompanyId").val();
		$('#emulateModal').modal('show');
            
			   var company_list = [];
			   var citymap={};
			   $.ajax({
					//Action Name
					url :"CompanyListUnderSuperUser",
					dataType : "json",
					data : {
					 parent_company_user_id :$("#companyUserId-emulate").val(),
						email : $("#email-emulate").val()
					},
					success : function(data, textStatus, jqXHR) {
						
						var items = data.records;
						console.log("items",items);
						   for(var i = 0; i < data.records.length; i++) {
							   if((data.records[i].companyid!=loginCompanyId) && $("#companyUserId-emulate").val()==data.records[i].parent_company_userid){
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
 
 