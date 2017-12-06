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
<style>
<!--

-->
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
            All Customer Orders
          </h1>
        
        </section>
        <!-- Main content -->
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <div class="row">
          
          <div class="col-sm-12 col-md-12">
          
        <!--  flightorder row -->
	         <div class="row">
	                     <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-aqua">              
             
                <div class="actions">
                <div class="btn-group btn-group-devided" data-toggle="buttons" >
                 
                </div>
              </div>
                <div class="inner" >
                  <h3 id="newOrder"></h3>
                  <p><s:text name="global.FlightNewOrder" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-plane"></i>
                </div>
                
                <div class="small-week small-box-footer">
                <input name="pageId" type="hidden" value="3">
										<input name="actionId" type="hidden" value="6">
										<input name="statusCode" type="hidden" value="0">
               <label class="btn btn-transparent grey-salsa btn-sm ">
                    <a href="showWeekOrderList?showType=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa  btn-sm ">
                          <a href="showWeekOrderList?showType=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-sm ">
                                <a href="showWeekOrderList?showType=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label>
              </div>
                
                
               <!--  <a href="showWeekOrderList?type=today" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a> -->
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3 id="flightconfirmCount"><sup style="font-size: 20px"></sup></h3>
                  <p><s:text name="global.FlightConfirmedOrdercount" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-check"></i>
                </div>
                
                
                <a href="showWeekOrderList?showType=flightconfirm" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-blue">
                <div class="inner">
                  <h3 id="flightpaymentcount"></h3>
                  <p>Flight Payment Success</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-up"></i>
                </div>
                <a href="showWeekOrderList?showType=flightpayment" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
            
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3 id="flightpaymentfailedcount"></h3>
                  <p>Flight Payment Failed</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-down"></i>
                </div>
                <a href="showWeekOrderList?showType=flightpaymentfailed" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
            <!-- ./col -->
	         </div><!-- ./row -->
	         <!--  flightorder row ends -->
	         <!--  hotel order row -->
	                      
         <!-- Small boxes (Stat box) -->
          <div class="row">
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-aqua">
              <div class="small-week">
              
              </div>
                <div class="actions">
                <div class="btn-group btn-group-devided" data-toggle="buttons" >
                 
                </div>
              </div>
                <div class="inner" >
                  <h3 id="hotelOrders"></h3>
                  <p> <s:text name="global.HotelNewOrder" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-hotel"></i>
                </div>
                
                <div class="small-week small-box-footer">
               <label class="btn btn-transparent grey-salsa  btn-sm ">
                    <a href="showHotelWeekOrderList?showType=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-sm ">
                          <a href="showHotelWeekOrderList?showType=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-sm ">
                                <a href="showHotelWeekOrderList?showType=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label>
              </div>
                
               
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3 id="hotelconfirmCount"><sup style="font-size: 20px"></sup></h3>
                  <p><s:text name="global.HotelConfirmedOrdercount" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-check"></i>
                </div>
                <a href="showHotelWeekOrderList?showType=hotelconfirm" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-blue">
                <div class="inner">
                  <h3 id="hotelpaymentcount"></h3>
                  <p>Hotel Payment Success</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-up"></i>
                </div>
                <a href="showHotelWeekOrderList?showType=hotelpayment" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
              <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3 id="hotelpaymentfailedcount"></h3>
                  <p>Hotel Payment Failed</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-down"></i>
                </div>
                <a href="showHotelWeekOrderList?showType=hotelpaymentfailed" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
          </div><!-- /.row -->
	         <!--  hotel order row -->
	         
	       <s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false)}">  
	          <div class="row">
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-aqua">
              <div class="small-week">
               <!-- <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                    <a href="showHotelWeekOrderList?type=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                          <a href="showHotelWeekOrderList?type=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                                <a href="showHotelWeekOrderList?type=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label> -->
              </div>
                <div class="actions">
                <div class="btn-group btn-group-devided" data-toggle="buttons" >
                 
                </div>
              </div>
                <div class="inner" >
                  <h3 id="carOrders"></h3>
                  <p> <s:text name="global.CarNewOrder" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-car"></i>
                </div>
                
                <div class="small-week small-box-footer">
               <label class="btn btn-transparent grey-salsa  btn-sm ">
                    <a href="showCarWeekOrderList?showType=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-sm ">
                          <a href="showCarWeekOrderList?showType=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-sm ">
                                <a href="showCarWeekOrderList?showType=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label>
              </div>
                
                <!-- <a href="showHotelWeekOrderList?type=today" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a> -->
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3 id="carconfirmCount"><sup style="font-size: 20px"></sup></h3>
                  <p><s:text name="global.CarConfirmedOrdercount" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-check"></i>
                </div>
                <a href="showCarWeekOrderList?showType=carconfirm" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-blue">
                <div class="inner">
                  <h3 id="carpaymentcount"></h3>
                  <p>Car Payment Success</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-up"></i>
                </div>
                <a href="showCarWeekOrderList?showType=carpayment" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
              <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3 id="carpaymentfailedcount"></h3>
                  <p>Car Payment Failed</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-down"></i>
                </div>
                <a href="showCarWeekOrderList?showType=carpaymentfailed" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
          </div>
          
           <div class="row">
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-aqua">
              <div class="small-week">
               <!-- <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                    <a href="showHotelWeekOrderList?type=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                          <a href="showHotelWeekOrderList?type=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                                <a href="showHotelWeekOrderList?type=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label> -->
              </div>
                <div class="actions">
                <div class="btn-group btn-group-devided" data-toggle="buttons" >
                 
                </div>
              </div>
                <div class="inner" >
                  <h3 id="busOrders"></h3>
                  <p> <s:text name="global.BusNewOrder" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-bus"></i>
                </div>
                
                <div class="small-week small-box-footer">
               <label class="btn btn-transparent grey-salsa  btn-sm ">
                    <a href="showBusWeekOrderList?showType=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-sm ">
                          <a href="showBusWeekOrderList?showType=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-sm ">
                                <a href="showBusWeekOrderList?showType=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label>
              </div>
                
                <!-- <a href="showHotelWeekOrderList?type=today" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a> -->
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3 id="busconfirmCount"><sup style="font-size: 20px"></sup></h3>
                  <p><s:text name="global.BusConfirmedOrdercount" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-check"></i>
                </div>
                <a href="showBusWeekOrderList?showType=busconfirm" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-blue">
                <div class="inner">
                  <h3 id="buspaymentcount"></h3>
                  <p>Bus Payment Success</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-up"></i>
                </div>
                <a href="showBusWeekOrderList?showType=buspayment" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
              <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3 id="buspaymentfailedcount"></h3>
                  <p>Bus Payment Failed</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-down"></i>
                </div>
                <a href="showBusWeekOrderList?showType=buspaymentfailed" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-aqua">
              <div class="small-week">
               <!-- <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                    <a href="showHotelWeekOrderList?type=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                          <a href="showHotelWeekOrderList?type=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                                <a href="showHotelWeekOrderList?type=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label> -->
              </div>
                <div class="actions">
                <div class="btn-group btn-group-devided" data-toggle="buttons" >
                 
                </div>
              </div>
                <div class="inner" >
                  <h3 id="trainOrders"></h3>
                  <p> <s:text name="global.TrainNewOrder" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-train"></i>
                </div>
                
                <div class="small-week small-box-footer">
               <label class="btn btn-transparent grey-salsa  btn-sm ">
                    <a href="showTrainWeekOrderList?showType=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-sm ">
                          <a href="showTrainWeekOrderList?showType=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-sm ">
                                <a href="showTrainWeekOrderList?showType=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label>
              </div>
                
                <!-- <a href="showHotelWeekOrderList?type=today" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a> -->
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3 id="trainconfirmCount"><sup style="font-size: 20px"></sup></h3>
                  <p><s:text name="global.TrainConfirmedOrdercount" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-check"></i>
                </div>
                <a href="showTrainWeekOrderList?showType=trainconfirm" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-blue">
                <div class="inner">
                  <h3 id="trainpaymentcount"></h3>
                  <p>Train Payment Success</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-up"></i>
                </div>
                <a href="showTrainWeekOrderList?showType=trainpayment" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
              <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3 id="trainpaymentfailedcount"></h3>
                  <p>Train Payment Failed</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-down"></i>
                </div>
                <a href="showTrainWeekOrderList?showType=trainpaymentfailed" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-aqua">
              <div class="small-week">
               <!-- <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                    <a href="showHotelWeekOrderList?type=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                          <a href="showHotelWeekOrderList?type=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                                <a href="showHotelWeekOrderList?type=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label> -->
              </div>
                <div class="actions">
                <div class="btn-group btn-group-devided" data-toggle="buttons" >
                 
                </div>
              </div>
                <div class="inner" >
                  <h3 id="insuranceOrders"></h3>
                  <p> <s:text name="global.InsuranceNewOrder" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-medkit"></i>
                </div>
                
                <div class="small-week small-box-footer">
               <label class="btn btn-transparent grey-salsa  btn-sm ">
                    <a href="showInsuranceWeekOrderList?showType=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-sm ">
                          <a href="showInsuranceWeekOrderList?showType=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-sm ">
                                <a href="showInsuranceWeekOrderList?showType=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label>
              </div>
                
                <!-- <a href="showHotelWeekOrderList?type=today" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a> -->
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3 id="insuranceconfirmCount"><sup style="font-size: 20px"></sup></h3>
                  <p><s:text name="global.InsuranceConfirmedOrdercount" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-check"></i>
                </div>
                <a href="showInsuranceWeekOrderList?showType=insuranceconfirm" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-blue">
                <div class="inner">
                  <h3 id="insurancepaymentcount"></h3>
                  <p>Insurance Payment Success</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-up"></i>
                </div>
                <a href="showInsuranceWeekOrderList?showType=insurancepayment" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
              <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3 id="insurancepaymentfailedcount"></h3>
                  <p>Insurance Payment Failed</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-down"></i>
                </div>
                <a href="showInsuranceWeekOrderList?showType=insurancepaymentfailed" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-aqua">
              <div class="small-week">
               <!-- <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                    <a href="showHotelWeekOrderList?type=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                          <a href="showHotelWeekOrderList?type=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
                                <a href="showHotelWeekOrderList?type=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label> -->
              </div>
                <div class="actions">
                <div class="btn-group btn-group-devided" data-toggle="buttons" >
                 
                </div>
              </div>
                <div class="inner" >
                  <h3 id="visaOrders"></h3>
                  <p> <s:text name="global.VisaNewOrder" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-cc-visa"></i>
                </div>
                
                <div class="small-week small-box-footer">
               <label class="btn btn-transparent grey-salsa  btn-sm ">
                    <a href="showVisaWeekOrderList?showType=today" class="small-box-footer"  style="color: white"> Today <i class="fa fa-arrow-circle-right"></i></a>
                    </label>
                    <label class="btn btn-transparent grey-salsa btn-sm ">
                          <a href="showVisaWeekOrderList?showType=week" class="small-box-footer"  style="color: white">Week<i class="fa fa-arrow-circle-right"></i></a>
                                </label>
                                <label class="btn btn-transparent grey-salsa btn-sm ">
                                <a href="showVisaWeekOrderList?showType=month" class="small-box-footer"  style="color: white">Month <i class="fa fa-arrow-circle-right"></i></a>
              </label>
              </div>
                
                <!-- <a href="showHotelWeekOrderList?type=today" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a> -->
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3 id="visaconfirmCount"><sup style="font-size: 20px"></sup></h3>
                  <p><s:text name="global.VisaConfirmedOrdercount" ></s:text></p>
                </div>
                <div class="icon">
                  <i class="fa fa-check"></i>
                </div>
                <a href="showVisaWeekOrderList?showType=visaconfirm" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-blue">
                <div class="inner">
                  <h3 id="visapaymentcount"></h3>
                  <p>Visa Payment Success</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-up"></i>
                </div>
                <a href="showVisaWeekOrderList?showType=visapayment" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
              <div class="col-sm-6 col-md-3">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3 id="visapaymentfailedcount"></h3>
                  <p>Visa Payment Failed</p>
                </div>
                <div class="icon">
                  <i class="fa fa-thumbs-down"></i>
                </div>
                <a href="showVisaWeekOrderList?showType=visapaymentfailed" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
          </div> 
          
       </s:if>   
          </div>
          </div>
          <!--  Number of agents and distributers ends --> 
          
         
          
 	
         </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
     <%--     <%@ include file="DashboardFooter.jsp"%>  --%>
      <%--  <script src="js/jquery.js" type="text/javascript"></script> --%>
       <script src="js/jquery.min.js" type="text/javascript"></script>
       
         <script src="js/jquerycalUI.js"></script>
           <%-- <script src="js/angular.js"></script> --%>  
       <script src="js/ui-bootstrap-tpls-0.9.0.js"></script>  
       <script src="js/bootstrap.min.js" type="text/javascript"></script>
   <script src="js/custom.js"></script>
     <script src="js/listcomplete.js?ver=5.0" defer></script>
     
      <script src="js/Chart.min.js"></script>
    <script src="js/chartDataSample.js"></script>
    <script type="text/javascript"
		src="//js.maxmind.com/js/apis/geoip2/v2.1/geoip2.js"></script>

	<script type="text/javascript">
		var onSuccess = function(location) {
			
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
	
	
	
	

	       <script>
	       $('document').ready(function(){	
                    
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
 function autoRefresh_div()
 {
	 
    // a function which will load data from other file after x seconds
     var protocol=location.protocol;
  	 var host=location.host;
  	 var totUrl=$(location).attr('href');
 	 var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
 	 var finalUrl = newUrl+"getHotelOrderJson";
   				comfirmList= [];
 				$.ajax({
					    method: "GET",
					    url:finalUrl,
					   /*  data: {type:"today"}, */
					    success:function(data,status)
						{ 
					   		 $("#hotelOrders").text(data.jsonobj.HotelOrdercount);
					    	 $("#hotelconfirmCount").text(data.jsonobj.Hotelconfirmordercount);
					    	 $("#hotelpaymentcount").text(data.jsonobj.Hotelpaymentordercount);		
					    	 $("#hotelpaymentfailedcount").text(data.jsonobj.Hotelpaymentfailedordercount);		
					     },
						error: function(xhr,status,error)
						{
							console.log(error)
						  /*  alert(error); */
						}
					});  
 				 
 				finalUrl = newUrl+"getCarOrderJson";
 			   				comfirmList= [];
 			 				$.ajax({
 								    method: "GET",
 								    url:finalUrl,
 								   /*  data: {type:"today"}, */
 								    success:function(data,status)
 									{ 
 								    	console.log("id.."+data.jsonobj);
 								        $("#carOrders").text(data.jsonobj.CarOrdercount);
 								    	 $("#carconfirmCount").text(data.jsonobj.Carconfirmordercount);
 								    	 $("#carpaymentcount").text(data.jsonobj.Carpaymentordercount);
 								    	 $("#carpaymentfailedcount").text(data.jsonobj.Carpaymentfailedordercount);
 								     },
 									error: function(xhr,status,error)
 									{
 										console.log(error)
 									  /*  alert(error); */
 									}
 								});  	
 			 				finalUrl = newUrl+"getFlightOrderJson";
 			   				comfirmList= [];
 			 				$.ajax({
 								    method: "GET",
 								    url:finalUrl,
 								   /*  data: {type:"today"}, */
 								    success:function(data,status)
 									{ 
 								    	console.log("id.."+data.jsonobj);
 								         $("#newOrder").text(data.jsonobj.FlightOrdercount);
 								    	 $("#flightconfirmCount").text(data.jsonobj.Flightconfirmordercount);
 								    	 $("#flightpaymentcount").text(data.jsonobj.Flightpaymentordercount);
 								    	 $("#flightpaymentfailedcount").text(data.jsonobj.Flightpaymentfailedordercount);
 								    },
 									error: function(xhr,status,error)
 									{
 										console.log(error)
 									  /*  alert(error); */
 									}
 								});  	
 			 				finalUrl = newUrl+"getVisaOrderJson";
 			   				comfirmList= [];
 			 				$.ajax({
 								    method: "GET",
 								    url:finalUrl,
 								   /*  data: {type:"today"}, */
 								    success:function(data,status)
 									{ 
 								    	 $("#visaOrders").text(data.jsonobj.VisaOrdercount);
 								    	 $("#visaconfirmCount").text(data.jsonobj.Visaconfirmordercount);
 								    	 $("#visapaymentcount").text(data.jsonobj.Visapaymentordercount);
 								    	 $("#visapaymentfailedcount").text(data.jsonobj.Visapaymentfailedordercount);
 								    	
 								     },
 									error: function(xhr,status,error)
 									{
 										console.log(error)
 									  /*  alert(error); */
 									}
 								});  	
 			 				finalUrl = newUrl+"getTrainOrderJson";
 			   				comfirmList= [];
 			 				$.ajax({
 								    method: "GET",
 								    url:finalUrl,
 								   /*  data: {type:"today"}, */
 								    success:function(data,status)
 									{ 
 								    	 $("#trainOrders").text(data.jsonobj.TrainOrdercount);
 								    	 $("#trainconfirmCount").text(data.jsonobj.Trainconfirmordercount);
 								    	 $("#trainpaymentcount").text(data.jsonobj.Trainpaymentordercount);
 								    	 $("#trainpaymentfailedcount").text(data.jsonobj.Trainpaymentfailedordercount);
 								     },
 									error: function(xhr,status,error)
 									{
 										console.log(error)
 									  /*  alert(error); */
 									}
 								});  	
 			 				finalUrl = newUrl+"getBusOrderJson";
 			   				comfirmList= [];
 			 				$.ajax({
 								    method: "GET",
 								    url:finalUrl,
 								   /*  data: {type:"today"}, */
 								    success:function(data,status)
 									{ 
 								    	console.log("id.."+data.jsonobj);
 								        $("#busOrders").text(data.jsonobj.BusOrdercount);
 								    	 $("#busconfirmCount").text(data.jsonobj.Busconfirmordercount);
 								    	 $("#buspaymentcount").text(data.jsonobj.Buspaymentordercount);
 								    	 $("#buspaymentfailedcount").text(data.jsonobj.Buspaymentfailedordercount);
 								     },
 									error: function(xhr,status,error)
 									{
 										console.log(error)
 									  /*  alert(error); */
 									}
 								});  	
 			 				finalUrl = newUrl+"getInsuranceOrderJson";
 			   				comfirmList= [];
 			 				$.ajax({
 								    method: "GET",
 								    url:finalUrl,
 								   /*  data: {type:"today"}, */
 								    success:function(data,status)
 									{ 
 								         $("#insuranceOrders").text(data.jsonobj.InsuranceOrdercount);
 								    	 $("#insuranceconfirmCount").text(data.jsonobj.Insuranceconfirmordercount);
 								    	 $("#insurancepaymentcount").text(data.jsonobj.Insurancepaymentordercount);
 								    	 $("#insurancepaymentfailedcount").text(data.jsonobj.Insurancepaymentfailedordercount);
 								     },
 									error: function(xhr,status,error)
 									{
 										console.log(error)
 									  /*  alert(error); */
 									}
 								});  	
 			 				finalUrl = newUrl+"getCompanyUserJson";
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
	