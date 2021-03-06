 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<script src="js/company_filter.js">
	
</script>

 
<style>
.ui-autocomplete {
	height: auto;
} 

.timeline {
    position: relative;
    padding: 21px 0px 10px;
    margin-top: 4px;
    margin-bottom: 30px;
}

.timeline .line {
    position: absolute;
    width: 4px;
    display: block;
    background: currentColor;
    top: 0px;
    bottom: 0px;
    margin-left: 30px;
}

.timeline .separator {
    border-top: 1px solid currentColor;
    padding: 5px;
    padding-left: 40px;
    font-style: italic;
    font-size: .9em;
    margin-left: 30px;
}

.timeline .line::before { top: -4px; }
.timeline .line::after { bottom: -4px; }
.timeline .line::before,
.timeline .line::after {
    content: '';
    position: absolute;
    left: -4px;
    width: 12px;
    height: 12px;
    display: block;
    border-radius: 50%;
    background: currentColor;
}

.timeline .panel {
    position: relative;
    margin: 10px 0px 21px 70px;
    clear: both;
}

.timeline .panel::before {
    position: absolute;
    display: block;
    top: 8px;
    left: -24px;
    content: '';
    width: 0px;
    height: 0px;
    border: inherit;
    border-width: 12px;
    border-top-color: transparent;
    border-bottom-color: transparent;
    border-left-color: transparent;
}

.timeline .panel .panel-heading.icon * { font-size: 20px; vertical-align: middle; line-height: 40px; }
.timeline .panel .panel-heading.icon {
    position: absolute;
    left: -59px;
    display: block;
    width: 40px;
    height: 40px;
    padding: 0px;
    border-radius: 50%;
    text-align: center;
    float: left;
}

.timeline .panel-outline {
    border-color: transparent;
    background: transparent;
    box-shadow: none;
}

.timeline .panel-outline .panel-body {
    padding: 10px 0px;
}

.timeline .panel-outline .panel-heading:not(.icon),
.timeline .panel-outline .panel-footer {
    display: none;
}

.codetails .navbar-nav>li>a{
    font-size: 14px;
}
.codetails .navbar-nav>li>a:hover,
.codetails .navbar-nav>li>a:active{
background: #fff;
}
.codetails .col-sm-12{
 padding: 0px; 
 }
  .codetails  .input{
  border-bottom: 1px solid #ccc;
  width: 100%;
  background-color: #fff;
  }
 
</style>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-sm-3 clearfix pull-left">
				<h4>Customer Details</h4>
			</div>
				<div class="col-sm-9 clearfix pull-right " data-placement="top">
<!-- 		<div class="row">
		<div class="col-sm-5 clearfix pull-left " data-placement="top">
		</div>
		<div class="col-sm-8 clearfix " data-placement="top">	
		     	
				<a href="FlightTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Flight Order
				</a>
				<a href="HotelTravelRequestList"
					class="btn btn-top-link btn-xs"  >
					 Hotel Order
				</a>
				<a href="CarTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Car Order
				</a>				
				
				<a href="TrainTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Train Order
				</a>
				<a href="VisaTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Visa Order
				</a>
				<a href="InsuranceTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Insurance Order
				</a>
			</div>		
			   
			</div>	 -->		
			</div>
		</div>
	</section>
	<!-- Main content -->
	<section class="content">
		<div class="row"> 
					<div class="col-sm-12">
						<h4>
							<a href="javascript:history.back();"><span class="pull-right"><i class="fa fa-angle-left"></i> Back</span></a>
						</h4>
					</div> 
					
					<div class="col-sm-12">
						 <form action="updateCustomerDetailsOfCustomerOrders" method="post"  class="codetails"> 
						 <div class="col-xs-3">
						<div class="form-group">
					<div class=" col-sm-12 col-xs-12"  >
						<div class="text-center"> 
			                  		 <img  src="images/default.png" class="user-image avatar img-circle" alt="No Image" />
							  	 
							<h6>Upload a different photo...</h6>
							<div class="col-sm-6 ">
								<div id="fileinfo">
									<div id="fileError"></div><!-- required="required" --> 
								</div>              
							</div>
							<input type="file" id="uploadimage" accept="image/*"
								 ng-file-select="onFileSelect($files)"
								class="text-center center-block well well-sm"  name="Imagepath">
						</div>
					</div>
					</div> 
					</div>
					
					
					<div class="col-sm-9"> 
					
					
						    <!-- Page header -->
    
 
 <div class="clearfix">
 
 <div class="page-header clearfix"> 
					<ul class="nav navbar-nav navbar-left  scroll">
								<li class="page-scroll"><a href="#id-profile">// Profile</a></li>
								<li class="page-scroll"><a href="#id-document">// Documents</a></li> 
								<li class="page-scroll"><a href="#id-bookings">// My Bookings</a></li>
								<li class="page-scroll"><a href="#id-essentialinfo">// Essential Info</a></li>
									<li class="page-scroll"><a href="#id-contact">// Contact</a></li>
								<li  ><a href="javascript:void(0)" class="btn btn-success btn-xs"  id="coDetailsEdit">Edit</a></li> 
							</ul>  
    </div>
    <div class="timeline"> 
        <!-- Line component -->
        <div class="line text-muted"></div> 
        <!-- Panel -->
        <article class="panel panel-danger" id="id-profile">
            <div class="panel-heading icon">
                <i class="fa fa-user"></i>
            </div> 
             <div class="panel-heading">
			        <h2 class="panel-title">Profile </h2>
			        <%-- <input class="input" id="title<s:property value="id"/>" type="text" name="title" value="<s:property value="userId"/>"> --%> 
			   </div> 
            <div class="panel-body">
              <div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> Title</label>
					<div class="col-sm-12">
					<input class="input" id="title<s:property value="id"/>" type="text" name="title" value="<s:property value="frontUserDetail.title"/>"> 
					</div>
				</div>
			</div> 
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> FirstName</label>
					<div class="col-sm-12">
					<input class="input" id="firstName<s:property value="id"/>" type="text" name="firstName" value="<s:property value="frontUserDetail.firstName"/>"> 
					</div>
				</div>
			</div>  
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> LastName</label>
					<div class="col-sm-12">
					<input class="input" id="lastName<s:property value="id"/>" type="text" name="lastName" value="<s:property value="frontUserDetail.lastName"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> birthday</label>
					<div class="col-sm-12">
					<input class="input" id="birthday<s:property value="id"/>" type="text" name="birthday" value="<s:property value="frontUserDetail.birthday"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> Age</label>
					<div class="col-sm-12">
					<input class="input" id="age<s:property value="id"/>" type="text" name="age" value="<s:property value="frontUserDetail.age"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> Address</label>
					<div class="col-sm-12">
					<input class="input" id="address<s:property value="id"/>" type="text" name="address" value="<s:property value="frontUserDetail.address"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> Address 2</label>
					<div class="col-sm-12">
					<input class="input" id="address2<s:property value="id"/>" type="text" name="address2" value="<s:property value="frontUserDetail.address2"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> Zip</label>
					<div class="col-sm-12">
					<input class="input" id="zip<s:property value="id"/>" type="text" name="zip" value="<s:property value="frontUserDetail.zip"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> City</label>
					<div class="col-sm-12">
					<input class="input" id="city<s:property value="id"/>" type="text" name="city" value="<s:property value="frontUserDetail.city"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> Phone</label>
					<div class="col-sm-12">
					<input class="input" id="phone<s:property value="id"/>" type="text" name="phone" value="<s:property value="frontUserDetail.phone"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> Mobile</label>
					<div class="col-sm-12">
					<input class="input" id="mobile<s:property value="id"/>" type="text" name="mobile" value="<s:property value="frontUserDetail.mobile"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> Email</label>
					<div class="col-sm-12">
					<input class="input" id="email<s:property value="id"/>" type="text" name="email" value="<s:property value="frontUserDetail.email"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> Gender</label>
					<div class="col-sm-12">
					<input class="input" id="gender<s:property value="id"/>" type="text" name="gender" value="<s:property value="frontUserDetail.gender"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> CountryId</label>
					<div class="col-sm-12">
					<input class="input" id="countryId<s:property value="id"/>" type="text" name="countryId" value="<s:property value="frontUserDetail.countryId"/>"> 
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="form-group clearfix">
					<label class="col-sm-12 control-label"> State</label>
					<div class="col-sm-12">
					<input class="input" id="state<s:property value="id"/>" type="text" name="state" value="<s:property value="frontUserDetail.state"/>"> 
					</div>
				</div>
			</div>
            </div>
          
        </article>
        <!-- /Panel -->
    
        <!-- Panel -->
        <article class="panel panel-warning" id="id-document">
            <div class="panel-heading icon">
                <i class="glyphicon glyphicon-picture"></i>
            </div>
             <div class="panel-heading">
                <h2 class="panel-title">Documents</h2>
            </div>
            <div class="panel-body">
                  <div class=" col-sm-6 "  >
						<div class="text-left"> 
			                  		 <img  src="images/default.png" class="user-image avatar " alt="No Image" style="width: 125px;"/> 
							<h6>Upload a different Document</h6>
							<div class="col-sm-6 ">
								<div id="fileinfo">
									<div id="fileError"></div><!-- required="required" --> 
								</div>   
								<input type="file" id="uploadimage" accept="image/*"
								 ng-file-select="onFileSelect($files)"
								class="text-center center-block well well-sm"  name="Imagepath">           
							</div>
							
						</div>
					</div>
					<div class=" col-sm-6 text-right"> 
							  <button class="btn btn-top-link btn-xs" type="button"  >Download</button> 
							  <button class="btn btn-top-link btn-xs" type="button"  >Delete</button>
							  <button class="btn btn-top-link btn-xs" type="button"  >Share</button> 
					</div>
            </div> 
        </article>
        <!-- /Panel --> 
    
        <!-- Panel -->
        <article class="panel panel-success" id="id-bookings"> 
            <div class="panel-heading icon">
                <i class="fa fa-calendar"></i>
            </div> 
            <div class="panel-heading">
                <h2 class="panel-title">My Bookings</h2>
            </div> 
            <div class="panel-body">
                Anything you can!
            </div> 
        </article>
        <!-- /Panel --> 
        
        
          <!-- Panel -->
        <article class="panel panel-success" id="id-essentialinfo"> 
            <div class="panel-heading icon">
                <i class="glyphicon glyphicon-info-sign"></i>
            </div> 
            <div class="panel-heading">
                <h2 class="panel-title">Genral Info</h2>
            </div> 
            <div class="panel-body">
                Add general info
            </div> 
        </article>
        <!-- /Panel -->
        
        
        <!-- Panel -->
        <article class="panel panel-primary" id="id-contact"> 
            <!-- Icon -->
            <div class="panel-heading icon">
                <i class="fa fa-file-text"></i>
            </div>
             
            <div class="panel-heading">
                <h2 class="panel-title">Contact</h2>
            </div>
            
            <div class="panel-body">
                Some new content has been added.
            </div>
            <!-- /Body --> 
        </article>
        <!-- /Panel -->
    <div class="pull-right"> 
         <input type="button" id="update" class="btn btn-success btn-xs"  name="update"  value="Update"/> 
		 <a href="javascript:void(0)" class="btn btn-success btn-xs"  id="cancel">
			 Cancel
		</a> 
	</div> 
    </div>
     
</div>					
					</div>
						 
						 
						 </form>
					</div> 
				 
			  
		 </div>
		
	 
	</section>

</div>


<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css"> 
<script>
  
var $root = $('.scroll .page-scroll');
$('.scroll .page-scroll a').click(function() {
    var href = $.attr(this, 'href');
    $root.animate({
        scrollTop: $(href).offset().top
    }, 500, function () {
        window.location.hash = href;
    });
    return false;
});
 

$( document ).ready(function() {
	 $("input").prop('disabled', !$("input").prop('disabled'));
	 $("#update").hide(); 
	 $("#cancel").hide(); 
 $("#coDetailsEdit").click(function() {
	  $("input").prop('disabled', !$("input").prop('disabled')); 
		  $("#update").show();
		  $("#cancel").show(); 
		  $("#coDetailsEdit").hide();
		  $("input").css({
			  "border":"1px solid #ccc",
			  "padding":"2px"
		  }); 
	});  
 
 $("#cancel").click(function() {
	  $("input").prop('disabled', !$("input").prop('disabled')); 
	  $("#update").hide();
	  $("#cancel").hide(); 
	  $("#coDetailsEdit").show();
	  $("input").removeAttr("style")
 });
 
 $("a[href^='#']").click(function(e){
	  e.preventDefault();
	  var elem = $($(this).attr('href')); 
	});
 
 
//Select all links with hashes
 $('.page-scroll a[href*="#"]')
   // Remove links that don't actually link to anything
   .not('[href="#"]')
   .not('[href="#0"]')
   .click(function(event) {
     // On-page links
     if (
       location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') 
       && 
       location.hostname == this.hostname
     ) {
       // Figure out element to scroll to
       var target = $(this.hash);
       target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
       // Does a scroll target exist?
       if (target.length) {
         // Only prevent default if animation is actually gonna happen
         event.preventDefault();
         $('html, body').animate({
           scrollTop: target.offset().top
         }, 1000, function() {
           // Callback after animation
           // Must change focus!
   /*         var $target = $(target);
           $target.focus();
           if ($target.is(":focus")) { // Checking if the target was focused
             return false;
           } else {
             $target.attr('tabindex','-1'); // Adding tabindex for elements not focusable
             $target.focus(); // Set focus again
           }; */
         });
       }
     }
   });
 
});
	
</script>
 




 