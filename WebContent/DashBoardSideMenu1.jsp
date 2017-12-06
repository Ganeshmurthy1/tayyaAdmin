<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:if test="%{#session.Company!=null && #session.User!=null}">

	<aside class="main-sidebar">
		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					<s:if test="%{#session.User.Imagepath == null}">
						<img check-image ng-src="images/default.png" class="img-circle" />

					</s:if>
					<s:else>
						<img check-image
							ng-src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>"
							class="img-circle" />

					</s:else>
					<%-- <img
						src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>"
						class="img-circle" alt="no image" /> --%>

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
			 
				<s:if
					test="%{(#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms())|| 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isCms()) || 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder())|| (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isTechHead()) ||(#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isTechSupport()) ||(#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isTechSupport() && #session.User.userrole_id.isTechHead())  || (#session.User.userrole_id.isAdmin()) || #session.Company.companyRole.isCorporate()}">
					<s:if test="%{#session.Company.companyRole.isDistributor()}">
						<li class="treeview"><a href="home"> <i
								class="fa fa-home"></i> <span>Dashboard </span>
						</a></li>
						
						
					<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Configurations</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								<s:if test="%{#session.User.agentWallet.walletType == 'Prepaid'}">
									<li><a href="#"><i class="fa fa-building"></i>Configuration<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<s:if test="%{#session.User.agentWallet.walletType == 'Prepaid'}">
													<li><a href="#">Company<i
															class="fa fa-angle-left pull-right"></i>
															<ul class="treeview-menu">
																<li><a href="superUserCompanyList"><i
																		class="fa fa-circle-o"></i> All</a></li>
																<li><a href="addCompany"><i
																		class="fa fa-pencil-square-o"></i> Add Company</a></li>
																<li class="ad-menu-border"><a
																	href="getNonApprovalCompaniesList">Pending Approvals
																		List </a></li>
																<li class="ad-menu-border"><a
																	href="getApprovalCompaniesList"> Approved List </a></li>
															</ul>
													</a></li>
					
													<li class="ad-menu-border"><a href="#"> Company Config
															<i class="fa fa-angle-left pull-right"></i>
															<ul class="treeview-menu">
																<li><a href="c_ConfigList"><i
																		class="fa fa-circle-o"></i> All</a></li>
																<li><a href="addNewCompanyConfig"><i
																		class="fa fa-pencil-square-o"></i> Add <s:property
																			value="%{#session.Company.Companyname}" /> Config</a></li>
															</ul>
													</a></li>
													
												</s:if>
					
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-user"></i>Employees<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="superUser_UserList"><i
													class="fa fa-circle-o"></i> All</a></li>
												<li><a href="addSuperUser_user"><i
													class="fa fa-pencil-square-o"></i> Add New Employee</a></li>
											</ul>
									</a></li>
								</s:if>
									
									
									<li><a href="#"><i class="fa fa-diamond"></i>Markup(s)<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">

												<li class="ad-menu-border"><a href="#"><i
														class="fa fa-compass"></i> Flight Markup <i
														class="fa fa-angle-left pull-right"></i>
					
														<ul class="treeview-menu">
															<li><a href="markupList"><i class="fa fa-circle-o"></i>
																	Flight Markup List</a></li>
															<li><a href="addMarkup"><i
																	class="fa fa-pencil-square-o"></i> Add Flight Markup</a></li>
														</ul> </a></li>
					
												<li class="ad-menu-border"><a href="#"><i
														class="fa fa-compass"></i> Hotel Markup <i
														class="fa fa-angle-left pull-right"></i>
					
														<ul class="treeview-menu">
															<li><a href="hotelMarkupList"><i class="fa fa-list"></i>
																	Hotel Markup List</a></li>
															<li><a href="addHotelMarkup"><i
																	class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
														</ul> </a></li>
														<li class="ad-menu-border"><a href="#"><i
															class="fa fa-compass"></i> Bus Markup <i
															class="fa fa-angle-left pull-right"></i>
					
															<ul class="treeview-menu">
																<li><a href="busMarkupList"><i
																		class="fa fa-circle-o"></i> Bus Markup List</a></li>
																<li><a href="addBusMarkup"><i
																		class="fa fa-pencil-square-o"></i> Add Bus Markup</a></li>
															</ul> </a></li>
															
											</ul>
									</a></li>
									
							</ul>
					</li>
					
<!-- </a></li>

							</ul></li> -->
							<%-- <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
								
	
							</ul>
						</li> --%>
							
							
							<li class="treeview"><a href="#"> <i
								class="fa fa-book"></i> <span>Accounts</span> <i
								class="fa fa-angle-left pull-right"></i>
								</a>
		
									<ul class="treeview-menu">
										
											<li><a href="#"><i class="fa fa-money"></i>Manage Wallet<i
													class="fa fa-angle-left pull-right"></i>
													<ul class="treeview-menu">
														<s:if test="#session.User.userrole_id.isSuperuser()">
															<li><a href="goMyWallet"> Add MyWallet</a></li>
														</s:if>
														<s:if test="%{#session.User.agentWallet.walletType == 'Prepaid'}">
															<li><a href="showWalletUsers"> Add UserWallet</a></li>
														</s:if>
													</ul>
											</a></li>
									</ul>
							</li>
							
							<li class="treeview"><a href="#"> <i
								class="fa fa-flag-checkered"></i> <span>Reports</span> <i
								class="fa fa-angle-left pull-right"></i>
								</a>
									<ul class="treeview-menu">
											<li><a href="#"><i class="fa fa-files-o"></i>CRM<i
													class="fa fa-angle-left pull-right"></i>
													<ul class="treeview-menu">
														<li><a href="CustomerOrderList"><i
																class="fa fa-circle-o"></i>All</a></li> 
														<li><a href="showFrontUsers"><i class="fa fa-circle-o"></i>B2C</a></li> 
													</ul>
											</a></li>
											
											<li><a href="txHistory"><i class="fa fa-list"></i>View Account 
											</a></li>
									</ul>
						   </li>
							
						 
						 <li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyReportList"><i
														class="fa fa-circle-o"></i>Report List</a></li>
				
												<li><a href="companyFlightOrderList"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="commissionReport"><i
														class="fa fa-circle-o"></i>Commission Report</a></li>
												<li><a href="getCustomerInvoiceList"><i
														class="fa fa-circle-o"></i>Customer Invoice</a></li>
												<!-- <li><a href="getSuperAgentInvoiceList"><i
												class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
												<li><a href="getSuperAgentCommInvoiceList"><i
														class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyHotelOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="hotelCommissionReport"><i
														class="fa fa-circle-o"></i>Commission Report</a></li>
												<li><a href="hotelCustomerInvoiceList"><i
														class="fa fa-circle-o"></i>Customer Invoice</a></li>
												<!-- <li><a href="getSuperAgentInvoiceList"><i
												class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
												<li><a href="hotelAgentCommInvoiceList"><i
														class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
											</ul>
									</a></li>
							</ul>
					</li>
							
						<%-- <li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>Flight Fin Sheet </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								<li><a href="goFlightTravelRequest">Add Flight Travel
										Request <i class="fa fa-angle-left pull-right"></i>
										<li><a href="FlightTravelRequestList">Flight Travel
												Request List<i class="fa fa-angle-left pull-right"></i>
										</a></li>
							</ul> </a></li> --%>
						<%--  <li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>Hotel Fin Sheet </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								<li><a href="goHotelTravelRequest">Add Hotel Travel
										Request <i class="fa fa-angle-left pull-right"></i>
										<li><a href="HotelTravelRequestList">Hotel Travel
												Request List<i class="fa fa-angle-left pull-right"></i>
										</a></li>
							</ul></li> --%>
						<%-- <li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>CRM </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">
								<li><a href="PassengerProfileList?pageId=34"><i
										class="fa fa-circle-o"></i>Passenger Profile</a></li>
								<li><a href="FrequentFlyerList?pageId=35"><i
										class="fa fa-circle-o"></i>Frequent Flyer</a></li>
								<li><a href="PassportVisaList?pageId=36"><i
										class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
								<li><a href="EmergencycontactList?pageId=37"><i
										class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
							</ul></li> --%>

					</s:if>



					<s:elseif
						test="%{(#session.Company.companyRole.isCorporate() && #session.User.userrole_id.isUsermode()) ||(#session.User.userrole_id.isTravelDesk() || #session.User.userrole_id.isAdmin())}">

						<li class="treeview active"><a href="home"> <i
								class="fa fa-home"></i> <span>Dashboard</span> <i
								class="fa fa-angle-left pull-right"></i>

						</a>
							<!-- <ul class="treeview-menu">
								<li><a href="home"> Home</a></li>
								<li><a href="txHistory">My Transactions</a></li>
								<li><a href="myOutstandingReport">Outstanding Report</a></li>
							</ul> -->
							
							</li>
							<%--  <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> --%>
							
							<li class="treeview"><a href="#"> <i
											class="fa fa-building"></i> <span>All Configurations</span> <i
											class="fa fa-angle-left pull-right"></i>
									</a>
										<ul class="treeview-menu">					
												<li><a href="#"><i class="fa fa-user"></i>Employees<i
														class="fa fa-angle-left pull-right"></i>
														<ul class="treeview-menu">
															<li><a href="superUser_UserList"><i
																class="fa fa-pencil-square-o"></i> All</a></li>
															<li><a href="addSuperUser_user"><i
																class="fa fa-pencil-square-o"></i> Add Employee</a></li>
														</ul>
												</a></li>								
										</ul>
								</li>

						<%--manish <li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>Employee Management</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">

								<!-- <li><a href="#">Designation<i
										class="fa fa-angle-left pull-right"></i>
										<ul class="treeview-menu">
											<li><a href="designationsList"><i
													class="fa fa-circle-o"></i> All</a></li>
											<li><a href="adduserdesignation"><i
													class="fa fa-pencil-square-o"></i> Add Designation</a></li>
										</ul>
								</a></li>
								<li><a href="#">Band<i
										class="fa fa-angle-left pull-right"></i>
										<ul class="treeview-menu">
											<li><a href="bandList"><i class="fa fa-circle-o"></i>
													All</a></li>
											<li><a href="adduserband"><i
													class="fa fa-pencil-square-o"></i> Add Band</a></li>
										</ul>
								</a></li> -->

							</ul></li> mmmm--%>
							
						
						
						<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyReportList"><i
													class="fa fa-circle-o"></i>Report List</a></li>

												<li><a href="companyFlightOrderList"><i
													class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyHotelOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-bus"></i>Bus Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyBusReports"><i
													class="fa fa-circle-o"></i>Report List</a></li>
											<li><a href="companyBusOrders"><i
													class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-car"></i>Car Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyCarReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyCarOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-train"></i>Train Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyTrainReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyTrainOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-cc-visa"></i>Visa Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyVisaReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyVisaOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-medkit"></i>Insurance Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyInsuranceReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyInsuranceOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-pie-chart"></i>Miscellaneous Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyMiscellaneousReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyMiscellaneousOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
							</ul>
					</li>
							
							<li class="treeview"><a href="#"> <i
									class="fa fa-flag-checkered"></i> <span>Reports</span> <i
									class="fa fa-angle-left pull-right"></i>
								</a>

								<ul class="treeview-menu">
									
										<li><a href="goCommonDisReport"><i class="fa fa-list"></i>Common DSR Report 
										</a></li>
										
										<li><a href="txHistory"><i class="fa fa-list"></i>View Account 
										</a></li>
										
										<li><a href="myLedgerReport"><i class="fa fa-list"></i>Ledger Report 
										</a></li>
										
										<li><a href="#"><i class="fa fa-files-o"></i>CRM<i
												class="fa fa-angle-left pull-right"></i>
												<ul class="treeview-menu">
													<li><a href="CustomerOrderList"><i class="fa fa-circle-o"></i>All</a></li> 
													<li><a href="FrontUserList"><i class="fa fa-circle-o"></i>B2C</a></li>  
												</ul>
										</a></li>
								</ul>
						  </li>
							
							
							
							
						<s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
							<li class="treeview"><a href="goTrips"> <i
									class="fa fa-files-o"></i> <span>Trip Requests </span> <i
									class="fa fa-angle-left pull-right"></i>
							</a></li>
						</s:if>
					</s:elseif>

					<s:elseif
						test="%{#session.Company.companyRole.isCorporate() && (#session.User.userrole_id.isAdmin() || #session.User.userrole_id.isReports() ||  #session.User.userrole_id.isOrder() || #session.User.userrole_id.isCms())}">

						<li class="treeview active"><a href="home"> <i
								class="fa fa-home"></i> <span>Dashboard </span> <i
								class="fa fa-angle-left pull-right"></i>

						</a>
							<!-- <ul class="treeview-menu">
								<li><a href="home"> Home</a></li>
								<li><a href="txHistory">View Account</a></li>

							</ul> -->
							
						</li>
						<%-- manish <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> mmmm--%>



						<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyReportList"><i
													class="fa fa-circle-o"></i>Report List</a></li>

												<li><a href="companyFlightOrderList"><i
													class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyHotelOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
							</ul>
					</li>

							<li class="treeview"><a href="#"> <i
								class="fa fa-flag-checkered"></i> <span>Reports</span> <i
								class="fa fa-angle-left pull-right"></i>
								</a>
		
									<ul class="treeview-menu">																										
											<li><a href="#"><i class="fa fa-files-o"></i>CRM<i
													class="fa fa-angle-left pull-right"></i>
													<ul class="treeview-menu">
														<li><a href="CustomerOrderList"><i
																class="fa fa-circle-o"></i>All</a></li> 
														<li><a href="showFrontUsers"><i class="fa fa-circle-o"></i>B2C</a></li> 
													</ul>
											</a></li>
											
											<li><a href="txHistory"><i class="fa fa-list"></i>View Account 
											</a></li>
																																															
									</ul>
							</li>

					</s:elseif>



					<s:elseif test="%{#session.Company.companyRole.isAgent()}">
						<li class="treeview"><a href="home"> <i
								class="fa fa-home"></i> <span>Dashboard </span>
						</a></li>
						<%-- manish <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> mmmm--%>
						<%-- <li class="treeview"><a href="myCommissionBlockList"> <i
								class="fa fa-files-o"></i> <span>My Deal Sheet</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a></li> --%>

								
					<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Configurations</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
																									
									<li><a href="#"><i class="fa fa-diamond"></i>Markup(s)<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">

												<li class="ad-menu-border"><a href="#"><i
														class="fa fa-compass"></i> Flight Markup <i
														class="fa fa-angle-left pull-right"></i>
					
														<ul class="treeview-menu">
															<li><a href="markupList"><i class="fa fa-circle-o"></i>
																	Flight Markup List</a></li>
															<li><a href="addMarkup"><i
																	class="fa fa-pencil-square-o"></i> Add Flight Markup</a></li>
														</ul> </a></li>
					
												<li class="ad-menu-border"><a href="#"><i
														class="fa fa-compass"></i> Hotel Markup <i
														class="fa fa-angle-left pull-right"></i>
					
														<ul class="treeview-menu">
															<li><a href="hotelMarkupList"><i class="fa fa-list"></i>
																	Hotel Markup List</a></li>
															<li><a href="addHotelMarkup"><i
																	class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
														</ul> </a></li>
														<li class="ad-menu-border"><a href="#"><i
															class="fa fa-compass"></i> Bus Markup <i
															class="fa fa-angle-left pull-right"></i>
					
															<ul class="treeview-menu">
																<li><a href="busMarkupList"><i
																		class="fa fa-circle-o"></i> Bus Markup List</a></li>
																<li><a href="addBusMarkup"><i
																		class="fa fa-pencil-square-o"></i> Add Bus Markup</a></li>
															</ul> </a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-user"></i>Employees<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="superUser_UserList"><i
														class="fa fa-circle-o"></i> All</a></li>
												<li><a href="addSuperUser_user"><i class="fa fa-pencil-square-o"></i>
														Add New Employee</a></li>
											</ul>
									</a></li>
																	
							</ul>
					</li>
					
								
						<li class="treeview"><a href="#"> <i
								class="fa fa-book"></i> <span>Accounts</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
									<li><a href="#"><i class="fa fa-money"></i>Manage Wallet<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="superMyTx">Wallet History</a></li>
												<s:if test="#session.User.userrole_id.isSuperuser()">
													<li><a href="goMyWallet"> Add MyWallet</a></li>
												</s:if>
												<li><a href="showWalletUsers"> Add UserWallet</a></li>
											</ul>
									</a></li>

							</ul>
						</li>
						
						
						<li class="treeview"><a href="#"> <i
								class="fa fa-flag-checkered"></i> <span>Reports</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">																										
									<li><a href="#"><i class="fa fa-money"></i>Transaction History<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="txHistory">View Account</a></li>  
												<li><a href="superMyTx">Wallet History</a></li>
											</ul>
									</a></li>
									
																																													
							</ul>
						</li>
							
						<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyReportList"><i
														class="fa fa-circle-o"></i>Report List</a></li>
				
												<li><a href="companyFlightOrderList"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="commissionReport"><i
														class="fa fa-circle-o"></i>Commission Report</a></li>
												<li><a href="getMyInvoiceList"><i
														class="fa fa-circle-o"></i>My Invoice(s)</a></li>
												<li><a href="getCustomerInvoiceList"><i
														class="fa fa-circle-o"></i>Customer Invoice(s)</a></li>
				
												<li><a href="getSuperAgentCommInvoiceList"><i
														class="fa fa-circle-o"></i>Agent Invoice(s)</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyHotelOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="hotelCommissionReport"><i
														class="fa fa-circle-o"></i>Commission Report</a></li>
				
												<li><a href="hotelMyCommInvoiceList"><i
														class="fa fa-circle-o"></i>My Invoice(s)</a></li>
				
												<li><a href="hotelCustomerInvoiceList"><i
														class="fa fa-circle-o"></i>Customer Invoice(s)</a></li>
												<!-- <li><a href="getSuperAgentInvoiceList"><i
												class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
												<li><a href="hotelAgentCommInvoiceList"><i
														class="fa fa-circle-o"></i>Agent Invoice(s)</a></li>
											</ul>
									</a></li>
							</ul>
					</li>


						<%-- <li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>CRM </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">

								<li><a href="PassengerProfileList?pageId=34"><i
										class="fa fa-circle-o"></i>Passenger Profile</a></li>
								<li><a href="FrequentFlyerList?pageId=35"><i
										class="fa fa-circle-o"></i>Frequent Flyer</a></li>
								<li><a href="PassportVisaList?pageId=36"><i
										class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
								<li><a href="EmergencycontactList?pageId=37"><i
										class="fa fa-circle-o"></i>Emergency Contact Details</a></li>


								<!-- <li><a href="PassengerProfileList"><i
										class="fa fa-circle-o"></i>Passenger Profile</a></li>
								<li><a href="FrequentFlyerList"><i
										class="fa fa-circle-o"></i>Frequent Flyer</a></li>
								<li><a href="PassportVisaList"><i
										class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
								<li><a href="EmergencycontactList"><i
										class="fa fa-circle-o"></i>Emergency Contact Details</a></li> -->
							</ul></li> --%>

					</s:elseif>


					<s:else>
						<li class="treeview active"><a href="home"> <i
								class="fa fa-home"></i> <span>Dashboard </span> <i
								class="fa fa-angle-left pull-right"></i>

						</a>
							<!-- <ul class="treeview-menu">
								<li><a href="home"> Home</a></li>
								<li><a href="txHistory">View Account</a></li>

							</ul> --></li>
						
							<%-- manish <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
								
	
							</ul>
						</li> mmmm --%>
						
							<%-- <li class="treeview"><a href="#"> <i class="fa fa-money"></i>
									<span>Manage Wallet </span> <i
									class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
	
									<s:if test="#session.User.userrole_id.isSuperuser()">
										<li><a href="goMyWallet"> Add MyWallet</a></li>
									</s:if>
	
									<li><a href="showWalletUsers"> Add UserWallet</a></li>
								</ul></li> --%>
						
					
					<li class="treeview"><a href="#"> <i
								class="fa fa-book"></i> <span>Accounts</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">
									<li><a href="#"><i class="fa fa-money"></i>Manage Wallet<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<s:if test="#session.User.userrole_id.isSuperuser()">
													<li><a href="goMyWallet"> Add MyWallet</a></li>
												</s:if>
												<li><a href="showWalletUsers"> Add UserWallet</a></li>
											</ul>
									</a></li>
							</ul>
					</li>
						
					<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Configurations</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">

									<li><a href="#"><i class="fa fa-building"></i>Configuration<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">

													<li><a href="#">Company<i
															class="fa fa-angle-left pull-right"></i>
															<ul class="treeview-menu">
																<li><a href="superUserCompanyList"><i
																		class="fa fa-circle-o"></i> All</a></li>
																<li><a href="addCompany"><i
																		class="fa fa-pencil-square-o"></i> Add Company</a></li>
																<li class="ad-menu-border"><a
																	href="getNonApprovalCompaniesList">Pending Approvals
																		List </a></li>
																<li class="ad-menu-border"><a
																	href="getApprovalCompaniesList"> Approved List </a></li>
															</ul>
													</a></li>
					
													<li class="ad-menu-border"><a href="#"> Company Config
															<i class="fa fa-angle-left pull-right"></i>
															<ul class="treeview-menu">
																<li><a href="c_ConfigList"><i class="fa fa-circle-o"></i>
																		All</a></li>
																<li><a href="addNewCompanyConfig"><i
																		class="fa fa-pencil-square-o"></i> Add <s:property
																			value="%{#session.Company.Companyname}" /> Config</a></li>
															</ul>
													</a></li>
													
											</ul>
									</a></li>
											
									<li><a href="#"><i class="fa fa-diamond"></i>Markup(s)<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">

												<li class="ad-menu-border"><a href="#"><i
														class="fa fa-compass"></i> Flight Markup <i
														class="fa fa-angle-left pull-right"></i>
					
														<ul class="treeview-menu">
															<li><a href="markupList"><i class="fa fa-circle-o"></i>
																	Flight Markup List</a></li>
															<li><a href="addMarkup"><i
																	class="fa fa-pencil-square-o"></i> Add Flight Markup</a></li>
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
															class="fa fa-compass"></i> Bus Markup <i
															class="fa fa-angle-left pull-right"></i>
					
															<ul class="treeview-menu">
																<li><a href="busMarkupList"><i
																		class="fa fa-circle-o"></i> Bus Markup List</a></li>
																<li><a href="addBusMarkup"><i
																		class="fa fa-pencil-square-o"></i> Add Bus Markup</a></li>
															</ul> </a></li>
															
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-user"></i>Employees<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="superUser_UserList"><i
														class="fa fa-circle-o"></i> All</a></li>
												<li><a href="addSuperUser_user"><i
														class="fa fa-pencil-square-o"></i> Add New Employee</a></li>
											</ul>
									</a></li>
									
							</ul>
					</li>
					
						
						<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyReportList"><i
														class="fa fa-circle-o"></i>Report List</a></li>
				
												<li><a href="companyFlightOrderList"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="commissionReport"><i
														class="fa fa-circle-o"></i>Commission Report</a></li>
												<li><a href="getCustomerInvoiceList"><i
														class="fa fa-circle-o"></i>Customer Invoice</a></li>
												<!-- <li><a href="getSuperAgentInvoiceList"><i
												class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
												<li><a href="getSuperAgentCommInvoiceList"><i
														class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyHotelOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="hotelCommissionReport"><i
														class="fa fa-circle-o"></i>Commission Report</a></li>
												<li><a href="hotelCustomerInvoiceList"><i
														class="fa fa-circle-o"></i>Customer Invoice</a></li>
				
												<li><a href="hotelAgentCommInvoiceList"><i
														class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
											</ul>
									</a></li>
							</ul>
					</li>
						
						<%-- <li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>CRM </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">

								<li><a href="PassengerProfileList?pageId=34"><i
										class="fa fa-circle-o"></i>Passenger Profile</a></li>
								<li><a href="FrequentFlyerList?pageId=35"><i
										class="fa fa-circle-o"></i>Frequent Flyer</a></li>
								<li><a href="PassportVisaList?pageId=36"><i
										class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
								<li><a href="EmergencycontactList?pageId=37"><i
										class="fa fa-circle-o"></i>Emergency Contact Details</a></li> 
							</ul></li> --%>
							
							 
							
							
						<li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>CMS Settings </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">
								<li><a href="addDeals"><i class="fa fa-circle-o"></i>Add
										Deals </a></li>
								<li><a href="ShowListOfDeals"><i class="fa fa-circle-o"></i>Show
										All Deals </a></li>
							</ul></li>
							
							<li class="treeview"><a href="#"> <i
								class="fa fa-flag-checkered"></i> <span>Reports</span> <i
								class="fa fa-angle-left pull-right"></i>
								</a>
		
									<ul class="treeview-menu">
										
											<li><a href="#"><i class="fa fa-files-o"></i>CRM<i
													class="fa fa-angle-left pull-right"></i>
													<ul class="treeview-menu">
														<li><a href="CustomerOrderList"><i
																class="fa fa-circle-o"></i>All</a></li> 
														<li><a href="showFrontUsers"><i class="fa fa-circle-o"></i>B2C</a></li> 
													</ul>
											</a></li>
											
											
											
											<li><a href="txHistory"><i class="fa fa-list"></i>View Account 
											</a></li>
											
									</ul>
							</li>
							
							
					</s:else>
 
				</s:if>
				<%--  <s:elseif test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">   --%>
				<s:elseif
					test="%{#session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder()}">
					<li class="treeview active"><a href="home"> <i
							class="fa fa-home"></i> <span>Dashboard</span>

					</a></li>
					<%-- manish <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> mmmm --%>
						
						
						<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyReportList"><i
														class="fa fa-circle-o"></i>Report List</a></li>
					
												<li><a href="companyFlightOrderList"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="commissionReport"><i class="fa fa-circle-o"></i>Commission
														Report</a></li>
												<li><a href="getCustomerInvoiceList"><i
														class="fa fa-circle-o"></i>Customer Invoice</a></li>
												<!-- <li><a href="getSuperAgentInvoiceList"><i
													class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
												<li><a href="getSuperAgentCommInvoiceList"><i
														class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyHotelOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="hotelCommissionReport"><i
														class="fa fa-circle-o"></i>Commission Report</a></li>
												<li><a href="hotelCustomerInvoiceList"><i
														class="fa fa-circle-o"></i>Customer Invoice</a></li>
					
												<li><a href="hotelAgentCommInvoiceList"><i
														class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
											</ul>
									</a></li>
							</ul>
					</li>
						
				</s:elseif>
				<s:elseif test="%{#session.User.userrole_id.isReports()}">
					<li class="treeview active"><a href="home"> <i
							class="fa fa-home"></i> <span>Dashboard </span> <i
							class="fa fa-angle-left pull-right"></i>
					</a></li>
					<%-- manish <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> mmmm --%>
						<s:if test="%{#session.Company.companyRole.isSuperUser()}">
						
							<li class="treeview"><a href="#"> <i
								class="fa fa-book"></i> <span>Accounts</span> <i
								class="fa fa-angle-left pull-right"></i>
							</a>
	
								<ul class="treeview-menu">
									
										<li><a href="#"><i class="fa fa-money"></i>Manage Wallet<i
												class="fa fa-angle-left pull-right"></i>
												<ul class="treeview-menu">
													<li><a href="showWalletUsers"> Add UserWallet</a></li>
												</ul>
										</a></li>
										
										<li><a href="#"><i class="fa fa-money"></i>Payment Receivable(s)<i
												class="fa fa-angle-left pull-right"></i>
												<ul class="treeview-menu">
														<li><a href="goPayment">Add Payment</a></li>
														<li><a href="paymentReceivableList">List</a></li>
												</ul>
										</a></li>
										
										<li><a href="showKnockOffList"><i class="fa fa-files-o"></i>KnockOff List<i
												class="fa fa-angle-left pull-right"></i>
										</a></li>
										
								</ul>
						</li>
						
						
						<li class="treeview"><a href="#"> <i
								class="fa fa-flag-checkered"></i> <span>Reports</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

								<ul class="treeview-menu">
									
										<li><a href="#"><i class="fa fa-money"></i>Transaction History<i
												class="fa fa-angle-left pull-right"></i>
												<ul class="treeview-menu">
													<li><a href="superMyTx">Wallet History</a></li>
												</ul>
										</a></li>
										
										<li><a href="goCommonDisReport"><i class="fa fa-list"></i>Common DSR Report<i
												class="fa fa-angle-left pull-right"></i>
										</a></li>
										
										<li><a href="txHistory"><i class="fa fa-list"></i>View Account 
										</a></li>
										
								</ul>
						</li>
						
						</s:if>	
						
						
						<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyReportList"><i
													class="fa fa-circle-o"></i>Report List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelReports"><i
									class="fa fa-circle-o"></i>Report List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-bus"></i>Bus Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyBusReports"><i
													class="fa fa-circle-o"></i>Report List</a></li>
									</a></li>
									
									<li><a href="#"><i class="fa fa-car"></i>Car Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyCarReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-train"></i>Train Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyTrainReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-cc-visa"></i>Visa Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyVisaReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-medkit"></i>Insurance Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyInsuranceReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-pie-chart"></i>Miscellaneous Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyMiscellaneousReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
											</ul>
									</a></li>
							</ul>
					</li>
					
				</s:elseif>
				<s:elseif test="%{#session.User.userrole_id.isOrder()}">
					<li class="treeview  "><a href="home"> <i
							class="fa fa-home"></i> <span>Dashboard </span>
					</a></li>
					<%-- <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> --%>
						
						
						<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyFlightOrderList"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="commissionReport"><i class="fa fa-circle-o"></i>Commission
														Report</a></li>
												<li><a href="getCustomerInvoiceList"><i
														class="fa fa-circle-o"></i>Customer Invoice</a></li>
												<!-- <li><a href="getSuperAgentInvoiceList"><i
													class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
												<li><a href="getSuperAgentCommInvoiceList"><i
														class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelOrders"><i
													class="fa fa-circle-o"></i>Order List</a></li>
											<li><a href="hotelCommissionReport"><i
													class="fa fa-circle-o"></i>Commission Report</a></li>
											<li><a href="hotelCustomerInvoiceList"><i
													class="fa fa-circle-o"></i>Customer Invoice</a></li>
				
											<li><a href="hotelAgentCommInvoiceList"><i
													class="fa fa-circle-o"></i>Agent Commission Invoice</a></li>
											</ul>
									</a></li>
							</ul>
					</li>

				</s:elseif>
				<!-- ........................................................................................................................................ -->

				<s:elseif
					test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isDistributor() || #session.User.userrole_id.isTechHead()}">
					<li class="treeview"><a href="home"> <i class="fa fa-home"></i>
							<span>Dashboard </span>
					</a></li>
						
						<%-- manish <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> mmmm --%>
					<%-- <li class="treeview"><a href="#"> <i
						        class="fa fa-user"></i> <span>Employee Designations</span> <i
						        class="fa fa-angle-left pull-right"></i>
						      </a>
						       <ul class="treeview-menu">
						        <li><a href="employeeDesignationsPage"><i class="fa fa-plus"></i>Add Employee Info<i class="fa fa-angle-left pull-right"></i>
						        </a></li>
						        <li><a href="ShowEmployeeBandList"><i class="fa fa-list"></i>Band List<i class="fa fa-angle-left pull-right"></i>
						        </a></li>
						       </ul>
						</li> --%>

					<li class="treeview"><a href="#"> <i
								class="fa fa-flag-checkered"></i> <span>Reports</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-money"></i>Transaction History<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="superMyTx">Wallet History</a></li>
												<!-- <li><a href="txHistory">View Account</a></li> -->
												<!-- <li><a href="myOutstandingReport">Outstanding Report</a></li> -->
											</ul>
									</a></li>
									
									<s:if test="%{#session.Company.companyRole.isDistributor()}">
									<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
									<li><a href="goCommonDisReport"><i class="fa fa-list"></i>Common DSR Report<i
											class="fa fa-angle-left pull-right"></i>
									</a></li>
									</s:if></s:if>
									
									<li><a href="#"><i class="fa fa-files-o"></i>CRM<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="CustomerOrderList"><i
														class="fa fa-circle-o"></i>All</a></li> 
												<li><a href="showFrontUsers"><i class="fa fa-circle-o"></i>B2C</a></li> 
											</ul>
									</a></li>
									
									<s:if test="%{#session.User.userrole_id.isSuperuser() ||(#session.emulateByCompany!=null && #session.emulateByUser!=null)}">
									<li><a href="goCommonDisReport"><i class="fa fa-list"></i>Common DSR Report 
									</a></li>
									</s:if>
									
									<li><a href="txHistory"><i class="fa fa-list"></i>View Account 
									</a></li>
									
									<s:if test="%{#session.User.userrole_id.isSuperuser()}">
									<li><a href="myLedgerReport"><i class="fa fa-list"></i>Ledger Report 
									</a></li>
									</s:if>
									
									<s:if test="#session.User.userrole_id.isSuperuser()|| #session.User.userrole_id.isTechHead()">
									<li><a href="#"><i class="fa fa-files-o"></i>Marketing Sales Leads <i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="AgentSalesLeadCompanyList?pageId=42"><i
													class="fa fa-circle-o"></i>Sales Leads Report </a></li>
											</ul>
									</a></li>
									</s:if>
									
									<s:if test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isDistributor() || #session.User.userrole_id.isTechHead()}">
									<s:if test="#session.User.userrole_id.isSuperuser()">
									<li><a href="#"><i class="fa fa-list"></i>IP Report<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="CustomerIpListView">Ip Status</a></li>
												<li><a href="CustomerIpHistoryView">Ip History</a></li>
											</ul>
									</a></li>
									</s:if></s:if>
									
									<s:if test="#session.User.userrole_id.isSuperuser()">
									<li><a href="#"><i class="fa fa-money"></i>Booked & Search Dash<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="FlightBookAndSearchView">Flight</a></li>
												<li><a href="HotelBookAndSearchView">Hotel</a></li>
												<li><a href="BusBookAndSearchView">Bus</a></li>
											</ul>
									</a></li>
									</s:if>
							</ul>
					</li>
					
					<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Configurations</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								<s:if test="%{#session.User.agentWallet.walletType == 'Prepaid'}">
									<li><a href="#"><i class="fa fa-building"></i>Configuration<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<s:if test="%{#session.User.agentWallet.walletType == 'Prepaid'}">
													<li><a href="#">Company<i
															class="fa fa-angle-left pull-right"></i>
															<ul class="treeview-menu">
																<li><a href="superUserCompanyList"><i
																		class="fa fa-circle-o"></i> All</a></li>
																<li><a href="addCompany"><i
																		class="fa fa-pencil-square-o"></i> Add Company</a></li>
																<li class="ad-menu-border"><a
																	href="getNonApprovalCompaniesList">Pending Approvals
																		List </a></li>
																<li class="ad-menu-border"><a
																	href="getApprovalCompaniesList"> Approved List </a></li>
															</ul>
													</a></li>
					
													<li class="ad-menu-border"><a href="#"> Company Config
															<i class="fa fa-angle-left pull-right"></i>
															<ul class="treeview-menu">
																<li><a href="c_ConfigList"><i class="fa fa-circle-o"></i>
																		All</a></li>
																<li><a href="addNewCompanyConfig"><i
																		class="fa fa-pencil-square-o"></i> Add <s:property
																			value="%{#session.Company.Companyname}" /> Config</a></li>
															</ul>
													</a></li>
													
													<li class="ad-menu-border"><a href="#"> RM Config
															<i class="fa fa-angle-left pull-right"></i>
															<ul class="treeview-menu">
																<li><a href="goRmConfigs"><i class="fa fa-plus-square"></i>
																		Add</a></li>
																<li><a href="editRmConfig"><i
																		class="fa fa-pencil-square-o"></i> Update </a></li>
															</ul>
													</a></li>
												</s:if>
					
											</ul>
									</a></li>
								</s:if>
									
									
									
									
									<li><a href="#"><i class="fa fa-diamond"></i>Markup(s)<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">

												<li class="ad-menu-border"><a href="#"><i
														class="fa fa-compass"></i> Flight Markup <i
														class="fa fa-angle-left pull-right"></i>
					
														<ul class="treeview-menu">
															<li><a href="markupList"><i class="fa fa-circle-o"></i>
																	Flight Markup List</a></li>
															<li><a href="addMarkup"><i
																	class="fa fa-pencil-square-o"></i> Add Flight Markup</a></li>
														</ul> </a></li>
					
												<li class="ad-menu-border"><a href="#"><i
														class="fa fa-compass"></i> Hotel Markup <i
														class="fa fa-angle-left pull-right"></i>
					
														<ul class="treeview-menu">
															<li><a href="hotelMarkupList"><i class="fa fa-list"></i>
																	Hotel Markup List</a></li>
															<li><a href="addHotelMarkup"><i
																	class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
														</ul> </a></li>
														<li class="ad-menu-border"><a href="#"><i
															class="fa fa-compass"></i> Bus Markup <i
															class="fa fa-angle-left pull-right"></i>
					
															<ul class="treeview-menu">
																<li><a href="busMarkupList"><i
																		class="fa fa-circle-o"></i> Bus Markup List</a></li>
																<li><a href="addBusMarkup"><i
																		class="fa fa-pencil-square-o"></i> Add Bus Markup</a></li>
															</ul> </a></li>
															<li class="ad-menu-border"><a href="#"><i
															class="fa fa-compass"></i> Insurance Markup <i
															class="fa fa-angle-left pull-right"></i>
					
															<ul class="treeview-menu">
																<li><a href="insuranceMarkupList"><i
																		class="fa fa-circle-o"></i> Insurance Markup List</a></li>
																<li><a href="addInsuranceMarkup"><i
																		class="fa fa-pencil-square-o"></i> Add Insurance Markup</a></li>
															</ul> </a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-user"></i>Employees<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="superUser_UserList"><i class="fa fa-list"></i>
														All</a></li>
												<li><a href="addSuperUser_user"><i class="fa fa-plus"></i>
														Add New Employee</a></li>
											</ul>
									</a></li>
									
									<s:if test="#session.User.userrole_id.isSuperuser()|| #session.User.userrole_id.isTechHead()">
									<li><a href="#"><i class="fa fa-building"></i>Supplier Configurations<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="#">Our Suppliers<i
														class="fa fa-angle-left pull-right"></i>
														<ul class="treeview-menu">
															<li><a href="addApiProvider"><i
																	class="fa fa-circle-o"></i> Add Supplier</a></li>
															<li><a href="apiProviderList"><i
																	class="fa fa-pencil-square-o"></i>Supplier List</a></li>
														</ul>
												</a></li>
												<li><a href="#">TBO Supplier Config<i
														class="fa fa-angle-left pull-right"></i>
														<ul class="treeview-menu">
															<li><a href="addTboConfig"><i class="fa fa-circle-o"></i>
																	Add TBO Config</a></li>
															<li><a href="listTboConfig"><i
																	class="fa fa-circle-o"></i> List TBO Config</a></li>
															<!-- <li><a href="apiProviderList"><i
																	class="fa fa-pencil-square-o"></i>ApiProvider List</a></li> -->
														</ul>
												</a></li>
												<li><a href="#">e-Travel Smart Supplier Config<i
														class="fa fa-angle-left pull-right"></i>
														<ul class="treeview-menu">
															<li><a href="addEtravelSmartConfig"><i class="fa fa-circle-o"></i>
																	Add e-Travel Config</a></li>
															<li><a href="listEtravelSmartConfig"><i
																	class="fa fa-circle-o"></i> List e-Travel Config</a></li>
														</ul>
												</a></li>
												<li><a href="#">Trawell Tag Supplier Config<i
														class="fa fa-angle-left pull-right"></i>
														<ul class="treeview-menu">
														<li><a href="addinsurancedata"><i class="fa fa-circle-o"></i>
																	Upload TrawellTag Data</a></li>
															<li><a href="addTrawellTagConfig"><i class="fa fa-circle-o"></i>
																	Add Trawell Tag Config</a></li>
															<li><a href="listTrawellTagConfig"><i
																	class="fa fa-circle-o"></i> List Trawell Tag Config</a></li>
														</ul>
												</a></li>
												<li><a href="#">Desiya Supplier Config<i
														class="fa fa-angle-left pull-right"></i>
														<ul class="treeview-menu">
															<li><a href="addDesiyaConfig"><i
																	class="fa fa-circle-o"></i> Add Desiya Config</a></li>
															<li><a href="listDesiyaConfig"><i
																	class="fa fa-circle-o"></i> List Desiya Config</a></li>
															<!-- <li><a href="apiProviderList"><i
																	class="fa fa-pencil-square-o"></i>ApiProvider List</a></li> -->
														</ul>
												</a></li>
												<li><a href="#">Bluestar Supplier Config<i
														class="fa fa-angle-left pull-right"></i>
														<ul class="treeview-menu">
															<li><a href="addBluestarConfig"><i
																	class="fa fa-circle-o"></i> Add Bluestar Config</a></li>
															<li><a href="listBluestarConfig"><i
																	class="fa fa-circle-o"></i> List Bluestar Config</a></li>
															<!-- <li><a href="apiProviderList"><i
																	class="fa fa-pencil-square-o"></i>ApiProvider List</a></li> -->
														</ul>
												</a></li>
				
												<li><a href="#">My Supplier Config<i
														class="fa fa-angle-left pull-right"></i>
														<ul class="treeview-menu">
															<li><a href="addCommonConfig"><i
																	class="fa fa-circle-o"></i> Add Common Config</a></li>
															<li><a href="listCommonConfig"><i
																	class="fa fa-pencil-square-o"></i>Common Config List</a></li>
														</ul>
												</a></li>
				
											</ul>
									</a></li>
									</s:if>
							</ul>
					</li>
					
					
					<li class="treeview"><a href="#"> <i
								class="fa fa-book"></i> <span>Accounts</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-money"></i>Manage Wallet<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="superMyTx">Wallet History</a></li>
												<s:if test="#session.User.userrole_id.isSuperuser()">
													<li><a href="goMyWallet"> Add MyWallet</a></li>
												</s:if>
												<li><a href="showWalletUsers"> Add UserWallet</a></li>
											</ul>
									</a></li>
									
									<s:if test="%{#session.User.userrole_id.isSuperuser()}">
									<li><a href="#"><i class="fa fa-money"></i>Payment Receivable(s)<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
													<li><a href="goPayment">Add Payment</a></li>
													<li><a href="paymentReceivableList">List</a></li>
											</ul>
									</a></li>
									
									<li><a href="showKnockOffList"><i class="fa fa-files-o"></i>KnockOff List<i
											class="fa fa-angle-left pull-right"></i>
									</a></li>
									</s:if>
							</ul>
					</li>

				<s:if test="%{#session.User.userrole_id.isSuperuser() || #session.User.userrole_id.isTechHead()}">
					 <li class="treeview"><a href="goTrips"> <i
							class="fa fa-files-o"></i> <span>Trip Requests </span> <i
							class="fa fa-angle-left pull-right"></i>
					</a></li> 
				</s:if>	
					<s:if test="%{#session.User.userrole_id.isSuperuser()}">
				<li class="treeview"><a href="showExpenseList"> <i
							class="fa fa-files-o"></i> <span>Trip Expenses</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a></li>
					
					</s:if>	
					 
					 <li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyReportList"><i
													class="fa fa-circle-o"></i>Report List</a></li>

												<li><a href="companyFlightOrderList"><i
													class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="commissionReport"><i class="fa fa-list"></i>Commission
													Report</a></li>
											<s:if test="%{#session.Company.companyRole.isDistributor()}">
												<li><a href="getMyInvoiceList"><i class="fa fa-list"></i>My
														Invoice(s)</a></li>
				
											</s:if>
											<li><a href="getCustomerInvoiceList"><i
													class="fa fa-list"></i>Customer Invoice(s)</a></li>
											<!-- <li><a href="getSuperAgentInvoiceList"><i
												class="fa fa-circle-o"></i>Agent Invoice</a></li> -->
											<li><a href="getSuperAgentCommInvoiceList"><i
													class="fa fa-list"></i>Agent Invoice(s)</a></li>
											<!-- <li><a href="goFlightDisReport"><i
													class="fa fa-circle-o"></i>Flight DSR Report </a></li> -->
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyHotelOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												
												<li><a href="hotelCommissionReport"><i
													class="fa fa-list"></i>Commission Report</a></li>
											
												<s:if test="%{#session.User.userrole_id.isSuperuser() || #session.User.userrole_id.isTechHead()}">
												<li class="distHide"><a href="hotelMyCommInvoiceList" ><i
														class="fa fa-list"></i>My Invoice(s)</a></li>
														
														<li  class="distHide"><a href="hotelCustomerInvoiceList"><i
													class="fa fa-list"></i>Customer Invoice(s)</a></li>
				
													<li  class="distHide"><a href="hotelAgentCommInvoiceList"><i
													class="fa fa-list"></i>Agent Invoice(s)</a></li>
												
												</s:if>
											</ul>
									</a></li>
									
									<s:if test="%{#session.User.userrole_id.isSuperuser() ||(#session.emulateByCompany!=null && #session.emulateByUser!=null)}">
									<li><a href="#"><i class="fa fa-bus"></i>Bus Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyBusReports"><i
													class="fa fa-circle-o"></i>Report List</a></li>
											<li><a href="companyBusOrders"><i
													class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-car"></i>Car Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyCarReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyCarOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-train"></i>Train Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyTrainReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyTrainOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-cc-visa"></i>Visa Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyVisaReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyVisaOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-medkit"></i>Insurance Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyInsuranceReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyInsuranceOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-pie-chart"></i>Miscellaneous Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyMiscellaneousReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyMiscellaneousOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									</s:if>
							</ul>
					</li>
							 
					<s:if
						test="#session.User.userrole_id.isSuperuser()|| #session.User.userrole_id.isTechHead()">
						
						<%-- <li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>Master Configurations</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">

								<li><a href="#">Common Config<i
										class="fa fa-angle-left pull-right"></i>
										<ul class="treeview-menu">
											<li><a href="goCommonConfig"><i
													class="fa fa-circle-o"></i> Add Common Config</a></li>
											<li><a href="commonConfigList"><i
													class="fa fa-pencil-square-o"></i>Common Config List</a></li>
										</ul>
								</a></li>

							</ul></li> --%>

						<li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>Payment Card Details</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">
								<li><a href="addPamentInfo"><i class="fa fa-plus"></i>Add
										Payment Info </a></li>
								<li><a href="PaymentCardList"><i class="fa fa-list"></i>PaymentCardList
								</a></li>
							</ul></li>
						<%-- <li class="treeview"><a href="goHotelQuotationSchema"> <i
								class="fa fa-files-o"></i> <span>add Hotel Quotation Schema </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							 </li> --%>

						<%-- <li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>Flight Fin Sheet </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								<li><a href="goFlightTravelRequest">Add Flight Travel
										Request <i class="fa fa-angle-left pull-right"></i>
										<li><a href="FlightTravelRequestList">Flight Travel
												Request List<i class="fa fa-angle-left pull-right"></i>
										</a></li>
										<li><a href="goFlightDisReport">Flight DSR Report<i class="fa fa-angle-left pull-right"></i>
										</a></li>
							</ul> </a></li> --%>
						<%-- <li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>Hotel Fin Sheet </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								<li><a href="goHotelTravelRequest">Add Hotel Travel Request <i class="fa fa-angle-left pull-right"></i>
										<li><a href="HotelTravelRequestList">Hotel Travel
												Request List<i class="fa fa-angle-left pull-right"></i>
										</a></li>
										<li><a href="goHotelDisReport">Hotel DSR Report<i class="fa fa-angle-left pull-right"></i>
										</a></li>
										<li><a href="goCommonDisReport">Common DSR Report<i class="fa fa-angle-left pull-right"></i>
										</a></li>
							</ul></li>
							 --%>

						<%-- 
						<li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>CRM </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">


								<li><a href="PassengerProfileList?pageId=34"><i
										class="fa fa-circle-o"></i>Passenger Profile</a></li>
								<li><a href="FrequentFlyerList?pageId=35"><i
										class="fa fa-circle-o"></i>Frequent Flyer</a></li>
								<li><a href="PassportVisaList?pageId=36"><i
										class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
								<li><a href="EmergencycontactList?pageId=37"><i
										class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
								<!-- <li><a href="PassengerProfileList"><i
									class="fa fa-circle-o"></i>Passenger Profile</a></li>
							<li><a href="FrequentFlyerList"><i
									class="fa fa-circle-o"></i>Frequent Flyer</a></li>
							<li><a href="PassportVisaList"><i class="fa fa-circle-o"></i>Photo/Passport/Visa
									Details</a></li>
							<li><a href="EmergencycontactList"><i
									class="fa fa-circle-o"></i>Emergency Contact Details</a></li> -->

								<s:if test="#session.User.userrole_id.isSuperuser()">
									<li class="treeview"><a href="showFrontUsers"> <i
											class="fa fa-circle-o"></i> <span>B2C Users</span>
									</a></li>
								</s:if>
							</ul></li> --%>
						<li class="treeview"><a href="#"> <i
								class="fa fa-files-o"></i> <span>Settings </span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">
								<!-- 	<li><a href="#">CMS<i
										class="fa fa-angle-left pull-right"></i>
										<ul class="treeview-menu">
											<li><a href="addDeals"><i class="fa fa-circle-o"></i>
													Add Deals</a></li>
											<li><a href="ShowListOfDeals"><i
													class="fa fa-pencil-square-o"></i>Show All Deals</a></li>
										</ul>
								</a></li>
 -->
								<li><a href="#">Hotels<i
										class="fa fa-angle-left pull-right"></i>
										<ul class="treeview-menu">
											<li><a href="goHotels"><i class="fa fa-circle-o"></i>
													Add Hotel</a></li>
											<li><a href="hotelList"><i
													class="fa fa-pencil-square-o"></i>Hotel List</a></li>
										</ul>
								</a></li>

								<li><a href="#">Bug Tracker<i
										class="fa fa-angle-left pull-right"></i>
										<ul class="treeview-menu">
											<li><a href="addBugTracker"><i
													class="fa fa-circle-o"></i>Add BugTracker</a></li>
											<li><a href="goBugTrackerList"><i
													class="fa fa-circle-o"></i>BugTracker List</a></li>

										</ul>
								</a></li>
						<s:if test="%{#session.Company.companyRole.isSuperUser}">
						<li><a href="goFlightSearchCache">Flight Search Cache<i
								class="fa fa-angle-left pull-right"></i>
								
						</a></li>

					</s:if>


								<!-- <li><a href="#">Airports<i
										class="fa fa-angle-left pull-right"></i>
										<ul class="treeview-menu">
											<li><a href="AddAirportPage"><i
													class="fa fa-circle-o"></i> Add Airport</a></li>
											<li><a href="ShowAirportList"><i
													class="fa fa-pencil-square-o"></i>Show All Airports</a></li>
										</ul>
								</a></li> -->
								<li><a href="#">Browsing History<i
										class="fa fa-angle-left pull-right"></i>
										<ul class="treeview-menu">
											<li><a href="browsinghistories"><i
													class="fa fa-pencil-square-o"></i>Show All History</a></li>
										</ul>
								</a></li>
							</ul></li>
					</s:if>
					<s:if test="%{#session.Company.companyRole.isSuperUser}">
						<li><a href="#">Bug Tracker<i
								class="fa fa-angle-left pull-right"></i>
								<ul class="treeview-menu">
									<li><a href="addBugTracker"><i class="fa fa-circle-o"></i>Add
											BugTracker</a></li>
									<li><a href="goBugTrackerList"><i class="fa fa-circle-o"></i>BugTracker
											List</a></li>

								</ul>
						</a></li>
					</s:if>

				</s:elseif>
				<s:elseif test="%{#session.Company.companyRole.isAgent()}">
					<li class="treeview"><a href="home"> <i class="fa fa-home"></i>
							<span>Dashboard </span>
					</a></li>
					<%-- manish <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> mmmm --%>
					<%-- <li class="treeview"><a href="myCommissionBlockList"> <i
							class="fa fa-files-o"></i> <span>My Deal Sheet</span>
					</a></li> --%>
					
					<li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Configurations</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
								<ul class="treeview-menu">
									
										<li><a href="#"><i class="fa fa-diamond"></i>Markup(s)<i
												class="fa fa-angle-left pull-right"></i>
												<ul class="treeview-menu">
	
													<li class="ad-menu-border"><a href="#"><i
															class="fa fa-compass"></i> Flight Markup <i
															class="fa fa-angle-left pull-right"></i>
						
															<ul class="treeview-menu">
																<li><a href="markupList"><i class="fa fa-circle-o"></i>
																		Flight Markup List</a></li>
																<li><a href="addMarkup"><i
																		class="fa fa-pencil-square-o"></i> Add Flight Markup</a></li>
															</ul> </a></li>
						
													<li class="ad-menu-border"><a href="#"><i
															class="fa fa-compass"></i> Hotel Markup <i
															class="fa fa-angle-left pull-right"></i>
						
															<ul class="treeview-menu">
																<li><a href="hotelMarkupList"><i class="fa fa-list"></i>
																		Hotel Markup List</a></li>
																<li><a href="addHotelMarkup"><i
																		class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>
															</ul> </a></li>
															<li class="ad-menu-border"><a href="#"><i
																class="fa fa-compass"></i> Bus Markup <i
																class="fa fa-angle-left pull-right"></i>
						
																<ul class="treeview-menu">
																	<li><a href="busMarkupList"><i
																			class="fa fa-circle-o"></i> Bus Markup List</a></li>
																	<li><a href="addBusMarkup"><i
																			class="fa fa-pencil-square-o"></i> Add Bus Markup</a></li>
																</ul> </a></li>
												</ul>
										</a></li>
										
										<li><a href="#"><i class="fa fa-user"></i>Employees<i
												class="fa fa-angle-left pull-right"></i>
												<ul class="treeview-menu">
													<li><a href="superUser_UserList"><i class="fa fa-circle-o"></i>
															All</a></li>
													<li><a href="addSuperUser_user"><i class="fa fa-pencil-square-o"></i>
															Add New Employee</a></li>
												</ul>
										</a></li>
										
										
								</ul>
						</li>
					
					<%-- <li class="treeview"><a href="#"> <i
							class="fa "></i> <span>Flight Markup</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="markupList">
									Flight Markup List</a></li>
							<li><a href="addMarkup"><i class="fa fa-pencil-square-o"></i>
									Add Flight Markup</a></li>

						</ul></li>
					<li class="treeview"><a href="#"> <i
							class="fa "></i> <span>Hotel Markup</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="hotelMarkupList">
									Hotel Markup List</a></li>
							<li><a href="addHotelMarkup"><i
									class="fa fa-pencil-square-o"></i> Add Hotel Markup</a></li>

						</ul></li> --%>
					
					<li class="treeview"><a href="#"> <i
								class="fa fa-book"></i> <span>Accounts</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-money"></i>Manage Wallet<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="superMyTx">Wallet History</a></li>
												<s:if test="#session.User.userrole_id.isSuperuser()">
													<li><a href="goMyWallet"> Add MyWallet</a></li>
												</s:if>

												<li><a href="showWalletUsers"> Add UserWallet</a></li>
											</ul>
									</a></li>
									
							</ul>
					</li>
						
					 <li class="treeview"><a href="#"> <i
								class="fa fa-building"></i> <span>All Bookings</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>

							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-plane"></i>Flight Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyReportList"><i
														class="fa fa-circle-o"></i>Report List</a></li>
					
												<li><a href="companyFlightOrderList"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="commissionReport"><i class="fa fa-circle-o"></i>Commission
														Report</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-hotel"></i>Hotel Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyHotelReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyHotelOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
												<li><a href="hotelCommissionReport"><i
														class="fa fa-circle-o"></i>Commission Report</a></li>
												
												
											</ul>
									</a></li>
									
									<s:if
										test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
									<li><a href="#"><i class="fa fa-bus"></i>Bus Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyBusReports"><i
													class="fa fa-circle-o"></i>Report List</a></li>
											<li><a href="companyBusOrders"><i
													class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-car"></i>Car Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyCarReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyCarOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-train"></i>Train Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyTrainReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyTrainOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-cc-visa"></i>Visa Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyVisaReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyVisaOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-medkit"></i>Insurance Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyInsuranceReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyInsuranceOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
									
									<li><a href="#"><i class="fa fa-pie-chart"></i>Miscellaneous Bookings<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="companyMiscellaneousReports"><i
														class="fa fa-circle-o"></i>Report List</a></li>
												<li><a href="companyMiscellaneousOrders"><i
														class="fa fa-circle-o"></i>Order List</a></li>
											</ul>
									</a></li>
								</s:if>
							</ul>
					</li>	
					
					<li class="treeview"><a href="#"> <i
								class="fa fa-flag-checkered"></i> <span>Reports</span> <i
								class="fa fa-angle-left pull-right"></i>
						</a>
							<ul class="treeview-menu">
								
									<li><a href="#"><i class="fa fa-money"></i>Transaction History<i
											class="fa fa-angle-left pull-right"></i>
											<ul class="treeview-menu">
												<li><a href="superMyTx">Wallet History</a></li>
												<li><a href="txHistory">View Account</a></li>
											</ul>
									</a></li>
									
									<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
									<li><a href="goCommonDisReport"><i class="fa fa-list"></i>Common DSR Report 
									</a></li>
									</s:if>
									
									<li><a href="txHistory"><i class="fa fa-list"></i>View Account 
									</a></li>									
									
							</ul>
					</li>
						<s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
							<li class="treeview"><a href="goTrips"> <i
									class="fa fa-files-o"></i> <span>Trip Requests </span> <i
									class="fa fa-angle-left pull-right"></i>
							</a></li>
							
						</s:if>
						
						 <%-- <s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
							 <li class="treeview"><a href="goCommonDisReport"> <i
							class="fa fa-list"></i> <span>Common DSR Report</span>
					</a></li>
							
						</s:if> --%>
					<%-- 
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
							<span>CRM </span> <i class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<!-- <li><a href="PassengerProfileList"><i
									class="fa fa-circle-o"></i>Passenger Profile</a></li>
							<li><a href="FrequentFlyerList"><i
									class="fa fa-circle-o"></i>Frequent Flyer</a></li>
							<li><a href="PassportVisaList"><i class="fa fa-circle-o"></i>Photo/Passport/Visa
									Details</a></li>
							<li><a href="EmergencycontactList"><i
									class="fa fa-circle-o"></i>Emergency Contact Details</a></li> -->

							<li><a href="PassengerProfileList?pageId=34"><i
									class="fa fa-circle-o"></i>Passenger Profile</a></li>
							<li><a href="FrequentFlyerList?pageId=35"><i
									class="fa fa-circle-o"></i>Frequent Flyer</a></li>
							<li><a href="PassportVisaList?pageId=36"><i
									class="fa fa-circle-o"></i>Photo/Passport/Visa Details</a></li>
							<li><a href="EmergencycontactList?pageId=37"><i
									class="fa fa-circle-o"></i>Emergency Contact Details</a></li>
						</ul></li> --%>
					
				</s:elseif>

				<%--   </s:elseif>   --%>
				<s:elseif test="%{#session.User.userrole_id.isCms()}">
					<li class="treeview active"><a href="home"> <i
							class="fa fa-home"></i> <span>Dashboard </span>
					</a></li>
					<%-- <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> --%>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
							<span>CMS Settings </span> <i class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="addDeals"><i class="fa fa-circle-o"></i>Add
									Deals </a></li>
							<li><a href="ShowListOfDeals"><i class="fa fa-circle-o"></i>Show
									All Deals </a></li>
						</ul></li>

				</s:elseif>

				<s:elseif test="%{#session.User.userrole_id.isTechSupport()}">
					<li class="treeview active"><a href="home"> <i
							class="fa fa-home"></i> <span>Dashboard </span>
					</a></li>
					<%-- <li class="treeview"><a href="#"> <i class="fa fa-bar-chart"></i>
							<span>Analytics</span> <i
							class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="AllRecords">Analysis Graph</a></li>
								<li><a href="OverAllDashboard">Over All Dashboard</a></li>
								<li><a href="FlightIndividualDashboard">Flight</a></li>
								<li><a href="HotelIndividualDashboard">Hotel</a></li>
								<li><a href="CarIndividualDashboard">Car</a></li>
								<li><a href="BusIndividualDashboard">Bus</a></li>
								<li><a href="TrainIndividualDashboard">Train</a></li>
								<li><a href="VisaIndividualDashboard">Visa</a></li>
								<li><a href="InsuranceIndividualDashboard">Insurance</a></li>
								<li><a href="MiscellaneousIndividualDashboard">Miscellaneous</a></li>
	
							</ul>
						</li> --%>
					<li><a href="#">Bug Tracker<i
							class="fa fa-angle-left pull-right"></i>
							<ul class="treeview-menu">
								<li><a href="addBugTracker"><i class="fa fa-circle-o"></i>Add
										BugTracker</a></li>
								<li><a href="goBugTrackerList"><i class="fa fa-circle-o"></i>BugTracker
										List</a></li>

							</ul>
					</a></li>
				</s:elseif>

			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
</s:if>

