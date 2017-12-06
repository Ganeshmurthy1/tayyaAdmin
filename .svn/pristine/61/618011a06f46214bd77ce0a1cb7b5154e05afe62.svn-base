<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
 <s:if test="%{#session.Company!=null && #session.User!=null && #session.User.userrole_id.isSuperuser()}">
  	<aside class="main-sidebar">
 	<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
				   <img src="<s:url action='ImageAction?imageId=%{#session.Company.Imagepath}'/>" class="img-circle" alt="User Image" />
				 <!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.Company.Companyname}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				 <li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li>
								
					</ul></li>
						<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Manage Wallet </span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					<li><a href="goMyWallet"><i class="fa fa-home"></i> Add MyWallet</a></li>
						<li><a href="showWalletUsers"><i class="fa fa-home"></i> Add UserWallet</a></li>
						<!-- <li><a href=showCompaniesWallet><i class="fa fa-home"></i> Add CompanyWallet</a></li> -->
						<!-- <li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li> -->
					</ul></li>
					
				
				<li class="treeview"><a href="#"> <i
						class="fa fa-building"></i> <span>Configurations</span> <i
						class="fa fa-angle-left pull-right"></i>
				</a>
				
				 <ul class="treeview-menu">
				 <li><a href="#"><i class="fa fa-user"></i>Company<i
								class="fa fa-angle-left pull-right"></i>
								<ul class="treeview-menu">
									<li><a href="superUserCompanyList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addCompany"><i
											class="fa fa-pencil-square-o"></i> Add Company</a>
											</li>
								</ul> </a>
					</li>
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-diamond"></i> Company Config <i
								class="fa fa-angle-left pull-right"></i>
								<ul class="treeview-menu">
									<li><a href="c_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewCompanyConfig"><i
											class="fa fa-pencil-square-o"></i> Add <s:property value="%{#session.Company.Companyname}"/> Config</a></li>
												 
							 
											
								</ul> </a>
						</li>
						<li class="ad-menu-border"><a href="getApprovalCompaniesList"><i class="fa fa-user"></i>New Company Approvals <i
								class="fa fa-angle-left pull-right"></i>
							 </a>
						</li>

					<!--   <li class="ad-menu-border"><a href="#"><i
								class="fa fa-cubes"></i> Galileo Config <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="g_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewGalileo"><i
											class="fa fa-pencil-square-o"></i> Add GalileoConfig</a></li>
								</ul> </a></li>  
 -->
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Flight Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="markupList"><i class="fa fa-circle-o"></i>
											Markup List</a></li>
									<li><a href="addMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
								</ul> </a></li>

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Hotel Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="hotelMarkupList"><i
											class="fa fa-circle-o"></i> Hotel Markup List</a></li>
									<li><a href="addHotelMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
								</ul> </a></li>
<!-- 
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-envelope-o"></i> MailConfig <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="mailConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewMailConfig"><i
											class="fa fa-pencil-square-o"></i> Add MailConfig</a></li>
								</ul> </a></li> -->
						<!-- <li><a href="#"><i class="fa fa-user"></i>Company<i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUserCompanyList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addCompany"><i
											class="fa fa-pencil-square-o"></i> Add Company</a></li>
								</ul> </a></li> -->
						<li><a href="#"><i class="fa fa-user"></i> Employees <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUser_UserList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addSuperUser_user"><i
											class="fa fa-pencil-square-o"></i> Add New Employee</a></li>
								</ul> </a></li>
				 	
 

					</ul></li>

				<%-- <li class="treeview"><a href="goOrderCustomerList"> <i
						class="fa fa-files-o"></i> <span>OrderCustomers </span> <i
						class="fa fa-angle-left pull-right"></i>
				</a></li>
 --%>
				 
			  <li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
									<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
									<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
						<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>		
								
								
					 </ul></li>
					 <li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul>
				</li>
				
				 <li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CMS Settings </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
								<li><a href="addDeals"><i
								class="fa fa-circle-o"></i>Add Deals </a></li>
								<li><a href="ShowListOfDeals"><i
								class="fa fa-circle-o"></i>Show All Deals </a></li>
								
								<!-- <li><a href="HotelList"><i
								class="fa fa-circle-o"></i>Add Hotel Deals </a></li>
								<li><a href="HotelList"><i
								class="fa fa-circle-o"></i>Add Flight Deals </a></li> -->
						
					</ul>
					 </li>
					 
		 </ul>
		</section>
		<!-- /.sidebar -->
	</aside>
 </s:if>

<s:elseif
	test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isDistributor() && #session.Company.bookingSystemType.isIBE()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.Company.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.Company.Companyname}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Manage Wallet </span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="showWalletUsers"><i class="fa fa-home"></i> Add UserWallet</a></li>
						 
					</ul></li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-building"></i> <span>Configurations</span> <i
						class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
					<li><a href="#"><i class="fa fa-user"></i>Company<i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUserCompanyList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addCompany"><i
											class="fa fa-pencil-square-o"></i> Add Company</a></li>
								</ul> </a></li>
					
					
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-diamond"></i> Company Config <i
								class="fa fa-angle-left pull-right"></i>
								<ul class="treeview-menu">
									<li><a href="c_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewCompanyConfig"><i
											class="fa fa-pencil-square-o"></i> Add <s:property value="%{#session.Company.Companyname}"/> Config</a></li>
								</ul> </a></li>

 							<li class="ad-menu-border"><a href="getApprovalCompaniesList"><i class="fa fa-user"></i>New Company Approvals <i
								class="fa fa-angle-left pull-right"></i>
							 </a>
						</li>

<!-- 
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-cubes"></i> Galileo Config <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="g_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewGalileo"><i
											class="fa fa-pencil-square-o"></i> Add GalileoConfig</a></li>
								</ul> </a></li> -->

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Flight Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="markupList"><i class="fa fa-circle-o"></i>
											Markup List</a></li>
									<li><a href="addMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
								</ul> </a></li>

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Hotel Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="hotelMarkupList"><i
											class="fa fa-circle-o"></i> Hotel Markup List</a></li>
									<li><a href="addHotelMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
								</ul> </a></li>
<!-- 
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-envelope-o"></i> MailConfig <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="mailConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewMailConfig"><i
											class="fa fa-pencil-square-o"></i> Add MailConfig</a></li>
								</ul> </a></li> -->
						
						<li><a href="#"><i class="fa fa-user"></i> Employees <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUser_UserList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addSuperUser_user"><i
											class="fa fa-pencil-square-o"></i> Add New Employee</a></li>
								</ul> </a></li>
				


					</ul></li>

				<%-- <li class="treeview"><a href="goOrderCustomerList"> <i
						class="fa fa-files-o"></i> <span>OrderCustomers </span> <i
						class="fa fa-angle-left pull-right"></i>
				</a></li> --%>

			 
  			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								
									<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
								
					</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
 				</ul>
		</section>
		<!-- /.sidebar -->
	</aside>

</s:elseif>

<s:elseif
	test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isDistributor() && #session.Company.bookingSystemType.isAPI()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.Company.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.Company.Companyname}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Manage Wallet </span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="showWalletUsers"><i class="fa fa-home"></i> Add UserWallet</a></li>
						 
					</ul></li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-building"></i> <span>Configurations</span> <i
						class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
					<li><a href="#"><i class="fa fa-user"></i>Company<i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUserCompanyList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addCompany"><i
											class="fa fa-pencil-square-o"></i> Add Company</a></li>
								</ul> </a></li>
					
					 <li class="ad-menu-border"><a href="#"><i
								class="fa fa-diamond"></i> Company Config <i
								class="fa fa-angle-left pull-right"></i>
								<ul class="treeview-menu">
									<li><a href="c_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewCompanyConfig"><i
											class="fa fa-pencil-square-o"></i> Add <s:property value="%{#session.Company.Companyname}"/> Config</a></li>
								</ul> </a></li>

				<li class="ad-menu-border"><a href="getApprovalCompaniesList"><i class="fa fa-user"></i>New Company Approvals <i
								class="fa fa-angle-left pull-right"></i>
							 </a>
						</li>

<!-- 
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-cubes"></i> Galileo Config <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="g_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewGalileo"><i
											class="fa fa-pencil-square-o"></i> Add GalileoConfig</a></li>
								</ul> </a></li> -->

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Flight Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="markupList"><i class="fa fa-circle-o"></i>
											Markup List</a></li>
									<li><a href="addMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
								</ul> </a></li>

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Hotel Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="hotelMarkupList"><i
											class="fa fa-circle-o"></i> Hotel Markup List</a></li>
									<li><a href="addHotelMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
								</ul> </a></li>

						<!-- <li class="ad-menu-border"><a href="#"><i
								class="fa fa-envelope-o"></i> MailConfig <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="mailConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewMailConfig"><i
											class="fa fa-pencil-square-o"></i> Add MailConfig</a></li>
								</ul> </a></li> -->
					 
						<li><a href="#"><i class="fa fa-user"></i> Employees <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUser_UserList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addSuperUser_user"><i
											class="fa fa-pencil-square-o"></i> Add New Employee</a></li>
								</ul> </a></li>
					 


					</ul></li>

				<%-- <li class="treeview"><a href="goOrderCustomerList"> <i
						class="fa fa-files-o"></i> <span>OrderCustomers </span> <i
						class="fa fa-angle-left pull-right"></i>
				</a></li> --%>
  



				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
								
								
					</ul></li>

 				</ul>
		</section>
		<!-- /.sidebar -->
	</aside>

</s:elseif>

<s:elseif
	test="%{#session.User.userrole_id.isUsermode()&& #session.Company.companyRole.isAgent() && #session.Company.bookingSystemType.isIBE()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="<s:url action='ImageAction?imageId=%{#session.Company.Imagepath}'/>" 	class="img-circle"  alt="User Image" />
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.Company.Companyname}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>

			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Manage Wallet </span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="showWalletUsers"><i class="fa fa-home"></i> Add UserWallet</a></li>
						 
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Flight Markup</span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="markupList"><i class="fa fa-home"></i> Markup List</a></li>
					  <li><a href="addMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
						 
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Hotel Markup</span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="hotelMarkupList"><i class="fa fa-home"></i> Markup List</a></li>
					  <li><a href="addHotelMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
						 
					</ul></li>
					  <li><a href="#"><i class="fa fa-user"></i> Employees <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="companyUserList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="adduser"><i class="fa fa-pencil-square-o"></i>
											Add New Employee</a></li>
								</ul> </a></li>
					
				<%-- <li class="treeview"><a href="goOrderCustomerList"> <i
						class="fa fa-files-o"></i> <span>OrderCustomers </span> <i
						class="fa fa-angle-left pull-right"></i>
				</a></li>
 --%>
				<li class="treeview active"><li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu"> <!-- reportlist -->
						<li><a href="companyReportList"><i class="fa fa-circle-o"></i>Report
								List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
								
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
					<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
						
					</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>

</s:elseif>

 

<!--  USER OPTIONS ARE SHOWING IF ROLE IS ADMIN-->

<s:elseif  test="%{#session.User!=null  && #session.Company!=null}">
 <s:if test="%{(#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms())|| 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isCms()) || 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder())|| (#session.User.userrole_id.isAdmin())}">
	 <s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	<s:if test="%{#session.Company.companyRole.isDistributor()&& #session.Company.bookingSystemType.isAPI()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
				 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Companyname}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Manage Wallet </span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="showWalletUsers"><i class="fa fa-home"></i> Add UserWallet</a></li>
						 
					</ul></li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-building"></i> <span>Configurations</span> <i
						class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
					<li><a href="#"><i class="fa fa-user"></i>Company<i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUserCompanyList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addCompany"><i
											class="fa fa-pencil-square-o"></i> Add Company</a></li>
								</ul> </a></li>
					
					 <li class="ad-menu-border"><a href="#"><i
								class="fa fa-diamond"></i> Company Config <i
								class="fa fa-angle-left pull-right"></i>
								<ul class="treeview-menu">
									<li><a href="c_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewCompanyConfig"><i
											class="fa fa-pencil-square-o"></i> Add <s:property value="%{#session.Company.Companyname}"/> Config</a></li>
								</ul> </a></li>

				<li class="ad-menu-border"><a href="getApprovalCompaniesList"><i class="fa fa-user"></i>New Company Approvals <i
								class="fa fa-angle-left pull-right"></i>
							 </a>
						</li>

<!-- 
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-cubes"></i> Galileo Config <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="g_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewGalileo"><i
											class="fa fa-pencil-square-o"></i> Add GalileoConfig</a></li>
								</ul> </a></li> -->

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Flight Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="markupList"><i class="fa fa-circle-o"></i>
											Markup List</a></li>
									<li><a href="addMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
								</ul> </a></li>

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Hotel Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="hotelMarkupList"><i
											class="fa fa-circle-o"></i> Hotel Markup List</a></li>
									<li><a href="addHotelMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
								</ul> </a></li>

						<!-- <li class="ad-menu-border"><a href="#"><i
								class="fa fa-envelope-o"></i> MailConfig <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="mailConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewMailConfig"><i
											class="fa fa-pencil-square-o"></i> Add MailConfig</a></li>
								</ul> </a></li> -->
					 
						<li><a href="#"><i class="fa fa-user"></i> Employees <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUser_UserList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addSuperUser_user"><i
											class="fa fa-pencil-square-o"></i> Add New Employee</a></li>
								</ul> </a></li>
					 


					</ul></li>

				<%-- <li class="treeview"><a href="goOrderCustomerList"> <i
						class="fa fa-files-o"></i> <span>OrderCustomers </span> <i
						class="fa fa-angle-left pull-right"></i>
				</a></li> --%>
  



				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
								
								
					</ul></li>

 				</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	 </s:if>
	
	<s:elseif test="%{#session.Company.companyRole.isDistributor()&& #session.Company.bookingSystemType.isIBE()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Manage Wallet </span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="showWalletUsers"><i class="fa fa-home"></i> Add UserWallet</a></li>
						 
					</ul></li>
					 <li class="treeview active"><a href="#"> <i
						class="fa fa-building"></i> <span>Configurations</span> <i
						class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="#"><i class="fa fa-user"></i>Company<i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUserCompanyList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addCompany"><i
											class="fa fa-pencil-square-o"></i> Add Company</a></li>
								</ul> </a></li>
					
					
					
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-diamond"></i> Company Config <i
								class="fa fa-angle-left pull-right"></i>
								<ul class="treeview-menu">
									<li><a href="c_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewCompanyConfig"><i
											class="fa fa-pencil-square-o"></i> Add CompanyConfig</a></li>
								</ul> </a></li>

									<li class="ad-menu-border"><a href="getApprovalCompaniesList"><i class="fa fa-user"></i>New Company Approvals <i
								class="fa fa-angle-left pull-right"></i>
							 </a>
						</li>


<!-- 
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-cubes"></i> Galileo Config <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="g_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewGalileo"><i
											class="fa fa-pencil-square-o"></i> Add GalileoConfig</a></li>
								</ul> </a></li> -->

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Flight Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="markupList"><i class="fa fa-circle-o"></i>
											Markup List</a></li>
									<li><a href="addMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
								</ul> </a></li>

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Hotel Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="hotelMarkupList"><i
											class="fa fa-circle-o"></i> Hotel Markup List</a></li>
									<li><a href="addHotelMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
								</ul> </a></li>
<!-- 
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-envelope-o"></i> MailConfig <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="mailConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewMailConfig"><i
											class="fa fa-pencil-square-o"></i> Add MailConfig</a></li>
								</ul> </a></li>
					 -->
						<li><a href="#"><i class="fa fa-user"></i> Employees <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUser_UserList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addSuperUser_user"><i
											class="fa fa-pencil-square-o"></i> Add New Employee</a></li>
								</ul> </a></li>
						 


					</ul></li>

			<%-- 	<li class="treeview"><a href="goOrderCustomerList"> <i
						class="fa fa-files-o"></i> <span>OrderCustomers </span> <i
						class="fa fa-angle-left pull-right"></i>
				</a></li> --%>
 
 			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
								

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
 				</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	
	 </s:elseif>
 <s:elseif
	test="%{#session.Company.companyRole.isAgent() && #session.Company.bookingSystemType.isIBE()}">
<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
				     <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
				<%-- 	<img src="<s:property value="%{#session.Company.Imagepath}"/>"
						class="img-circle" alt="User Image" /> --%>
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>

			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Manage Wallet </span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="showWalletUsers"><i class="fa fa-home"></i> Add UserWallet</a></li>
						 
					</ul></li>
					
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Flight Markup</span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="markupList"><i class="fa fa-home"></i> Markup List</a></li>
					  <li><a href="addMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
						 
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Hotel Markup</span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="hotelMarkupList"><i class="fa fa-home"></i> Markup List</a></li>
					  <li><a href="addHotelMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
						 
					</ul></li>
						<li><a href="#"><i class="fa fa-user"></i> Employees <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="companyUserList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="adduser"><i class="fa fa-pencil-square-o"></i>
											Add New Employee</a></li>
								</ul> </a></li>
					
				<%-- <li class="treeview"><a href="goOrderCustomerList"> <i
						class="fa fa-files-o"></i> <span>OrderCustomers </span> <i
						class="fa fa-angle-left pull-right"></i>
				</a></li>
 --%>
				<li class="treeview active"><li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu"> <!-- reportlist -->
						<li><a href="companyReportList"><i class="fa fa-circle-o"></i>Report
								List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
								
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
					<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
						
					</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>

</s:elseif>
	
 <s:else>
	
	<aside class="main-sidebar">
 	<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
					 
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						<li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Manage Wallet </span> <i
						class="fa fa-angle-left pull-right"></i>
 						</a>
					<ul class="treeview-menu">
					  <li><a href="showWalletUsers"><i class="fa fa-home"></i> Add UserWallet</a></li>
						<!-- <li><a href="superMyTx"><i class="fa fa-home"></i>My
								Transactions</a></li> -->
					</ul></li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-building"></i> <span>Configurations</span> <i
						class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
					 <li><a href="#"><i class="fa fa-user"></i>Company<i
								class="fa fa-angle-left pull-right"></i>
								<ul class="treeview-menu">
									<li><a href="superUserCompanyList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addCompany"><i
											class="fa fa-pencil-square-o"></i> Add Company</a>
											</li>
								</ul> </a>
					</li>
					
					
					
						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-diamond"></i> Company Config <i
								class="fa fa-angle-left pull-right"></i>
								<ul class="treeview-menu">
									<li><a href="c_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewCompanyConfig"><i
											class="fa fa-pencil-square-o"></i> Add CompanyConfig</a></li>
								</ul> </a></li>
					<li class="ad-menu-border"><a href="getApprovalCompaniesList"><i class="fa fa-user"></i>New Company Approvals <i
								class="fa fa-angle-left pull-right"></i>
							 </a>
						</li>
 					<li class="ad-menu-border"><a href="#"><i
								class="fa fa-cubes"></i> Galileo Config <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="g_ConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewGalileo"><i
											class="fa fa-pencil-square-o"></i> Add GalileoConfig</a></li>
								</ul> </a></li>

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Flight Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="markupList"><i class="fa fa-circle-o"></i>
											Markup List</a></li>
									<li><a href="addMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Markup</a></li>
								</ul> </a></li>

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-compass"></i> Hotel Markup <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="hotelMarkupList"><i
											class="fa fa-circle-o"></i> Hotel Markup List</a></li>
									<li><a href="addHotelMarkup"><i
											class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
								</ul> </a></li>

						<li class="ad-menu-border"><a href="#"><i
								class="fa fa-envelope-o"></i> MailConfig <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="mailConfigList"><i class="fa fa-circle-o"></i>
											All</a></li>
									<li><a href="addNewMailConfig"><i
											class="fa fa-pencil-square-o"></i> Add MailConfig</a></li>
								</ul> </a></li>
						 
						<li><a href="#"><i class="fa fa-user"></i>Employees <i
								class="fa fa-angle-left pull-right"></i>

								<ul class="treeview-menu">
									<li><a href="superUser_UserList"><i
											class="fa fa-circle-o"></i> All</a></li>
									<li><a href="addSuperUser_user"><i
											class="fa fa-pencil-square-o"></i> Add New Employee</a></li>
								</ul> </a></li>
				 	 
 

					</ul></li>

				<%-- <li class="treeview"><a href="goOrderCustomerList"> <i
						class="fa fa-files-o"></i> <span>OrderCustomers </span> <i
						class="fa fa-angle-left pull-right"></i>
				</a></li> --%>
 
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
									<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						  <li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
									<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>		
								
					</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
					 <li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CMS Settings </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
								<li><a href="addDeals"><i
								class="fa fa-circle-o"></i>Add Deals </a></li>
								<li><a href="ShowListOfDeals"><i
								class="fa fa-circle-o"></i>Show All Deals </a></li>
								
								 
						
					</ul>
					 </li>
					 
 		</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
</s:else>
</s:if>
 </s:if>
  <s:elseif test="%{(#session.User.userrole_id.isReports() && #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isReports() && #session.User.userrole_id.isOrder())}">
	<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	<s:if test="%{#session.Company.companyRole.isDistributor()&& #session.Company.bookingSystemType.isAPI()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						 
					</ul></li>
					    
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
								

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
								
					</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
 				</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	 </s:if>
	 <s:elseif test="%{#session.Company.companyRole.isDistributor()&& #session.Company.bookingSystemType.isIBE()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						 
					</ul></li>
			 
 
 			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
								

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
 				</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	
	 </s:elseif>
 <s:elseif
	test="%{#session.Company.companyRole.isAgent() && #session.Company.bookingSystemType.isIBE()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
				 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>

			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
					</ul></li>
				 
				<li class="treeview active"><li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i class="fa fa-circle-o"></i>Report
								List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
					
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>

</s:elseif>
	
 <s:else>
	
	<aside class="main-sidebar">
 	<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
					 
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						 
					</ul></li>
				  <li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="commissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="getCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="getSuperAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						  <li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>
								<li><a href="hotelCommissionReport"><i
								class="fa fa-circle-o"></i>Agent Commission Report</a></li>
								<li><a href="hotelCustomerInvoiceList"><i
								class="fa fa-circle-o"></i>Customer Invoice</a></li>
						<!-- <li><a href="getSuperAgentInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
						<li><a href="hotelAgentCommInvoiceList"><i
								class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
					</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
					 <li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CMS Settings </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
								<li><a href="addDeals"><i
								class="fa fa-circle-o"></i>Add Deals </a></li>
								<li><a href="ShowListOfDeals"><i
								class="fa fa-circle-o"></i>Show All Deals </a></li>
					 </ul>
					 </li>
 		</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
</s:else>
  </s:if>
	</s:elseif>
	
	
	
	
	
	
 <s:elseif test="%{(#session.User.userrole_id.isReports() && #session.User.userrole_id.isCms()) || (#session.User.userrole_id.isReports())}">
	<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	<s:if test="%{#session.Company.companyRole.isDistributor()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				  	<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
					 
					</ul></li>
 			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
					<!-- 	<li><a href="superUserOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li> -->

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
					<!-- 	<li><a href="superUserHotelOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li> -->
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
 				</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	
	 </s:if>
	 
	 <s:elseif test="%{#session.Company.companyRole.isAgent()}">
	 	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>

			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
					</ul></li>
				 
			 

				<li class="treeview active"><li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="reportlist"><i class="fa fa-circle-o"></i>Report
								List</a></li>
						<!-- <li><a href="companyFlightOrderRowList"><i
								class="fa fa-circle-o"></i>Order List</a></li> -->
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<!-- <li><a href="companyHotelOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li> -->
					 <li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li> 
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	   </s:elseif>
	 
	   <s:else>
	 	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
				 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						 
					</ul></li>
				 
   					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="companyReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<!-- <li><a href="superUserOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
 -->
					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="companyHotelReports"><i
								class="fa fa-circle-o"></i>Report List</a></li>
						<!-- <li><a href="superUserHotelOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li> -->
					</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
 		</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	 
	   </s:else>
	  </s:if>
	</s:elseif>
 
  <s:elseif test="%{(#session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isOrder())}">
	<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	<s:if test="%{#session.Company.companyRole.isDistributor()}">
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				  	<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
					 
					</ul></li>
 			<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<!-- <li><a href="superUserReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li> -->
					 <li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>  

					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<!-- <li><a href="superUserHotelReportRowList"><i
								class="fa fa-circle-o"></i>Report List</a></li> -->
					 	<li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>  
					</ul></li>
						<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
 				</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	
	 </s:if>
	 
	 <s:elseif test="%{#session.Company.companyRole.isAgent()}">
	 	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>

			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
					</ul></li>
				  <li class="treeview active"><li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
					<!-- 	<li><a href="reportlist"><i class="fa fa-circle-o"></i>Report
								List</a></li> -->
						 <li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li> 
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						  <li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li>  
					<!-- 	<li><a href="CompanyHotelReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li> -->
					</ul></li>
								<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	   </s:elseif>
	 
	   <s:else>
	 	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
				 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
						 
					</ul></li>
				 
   					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Flight</span> <i class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<!-- <li><a href="superUserReportList"><i
								class="fa fa-circle-o"></i>Report List</a></li> -->
						  <li><a href="companyFlightOrderList"><i
								class="fa fa-circle-o"></i>Order List</a></li>
 
					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>Hotel </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<!-- <li><a href="superUserHotelReportRowList"><i
								class="fa fa-circle-o"></i>Report List</a></li> -->
						  <li><a href="companyHotelOrders"><i
								class="fa fa-circle-o"></i>Order List</a></li> 
					</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="PassengerProfileList"><i
								class="fa fa-circle-o"></i>Passenger Profile</a></li>
						<li><a href="FrequentFlyerList"><i
								class="fa fa-circle-o"></i>Frequent Flyer</a></li>
						<li><a href="PassportVisaList"><i
								class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
						<li><a href="EmergencycontactList"><i
								class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
					</ul></li>
					
					
					  <li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CMS Settings </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
								<li><a href="addDeals"><i
								class="fa fa-circle-o"></i>Add Deals </a></li>
								<li><a href="ShowListOfDeals"><i
								class="fa fa-circle-o"></i>Show All Deals </a></li>
								
								<!-- <li><a href="HotelList"><i
								class="fa fa-circle-o"></i>Add Hotel Deals </a></li>
								<li><a href="HotelList"><i
								class="fa fa-circle-o"></i>Add Flight Deals </a></li> -->
						
					</ul>
					 </li>
 		</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	 
	   </s:else>
	  </s:if>
	</s:elseif>
	
	
	
	 <s:elseif test="%{#session.User.userrole_id.isCms()}">
	<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	 
	<aside class="main-sidebar">

		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					 <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>" class="img-circle" alt="User Image" />
				
					<!-- images/harsha.jpg -->
				</div>
				<div class="pull-left info">
					<p>
						<s:property value="%{#session.User.Username}" />
					</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu">
				<li class="header">MAIN NAVIGATION</li>
				  	<li class="treeview active"><a href="#"> <i
						class="fa fa-dashboard"></i> <span>Dashboard </span> <i
						class="fa fa-angle-left pull-right"></i>

				</a>
					<ul class="treeview-menu">
						<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
					 
					</ul></li>
 			 
 			  <li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
						<span>CMS Settings </span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
								<li><a href="addDeals"><i
								class="fa fa-circle-o"></i>Add Deals </a></li>
								<li><a href="ShowListOfDeals"><i
								class="fa fa-circle-o"></i>Show All Deals </a></li>
								
								<!-- <li><a href="HotelList"><i
								class="fa fa-circle-o"></i>Add Hotel Deals </a></li>
								<li><a href="HotelList"><i
								class="fa fa-circle-o"></i>Add Flight Deals </a></li> -->
						
					</ul>
					 </li>
 			  </ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	
	 </s:if>
	 
	</s:elseif>
  </s:elseif>
 