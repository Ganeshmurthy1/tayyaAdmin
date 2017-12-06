<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Company</title>
	<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<%-- <h1>
				Dashboard <small>Control panel</small>
			</h1> --%>
			<!-- <ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<s:if test="hasActionErrors()">
				<div class="success-alert">
					<span class="fa fa-thumbs-o-up fa-1x"></span>
					<s:actionerror />
				</div>
			</s:if>
			<s:if test="hasActionMessages()">
				<div class="success-alert">
					<span class="fa fa-thumbs-o-up fa-1x"></span>
					<s:actionmessage />
				</div>
			</s:if>
			<div class="row" id="dash-us-register">
				 
				<form action="companyupdate" method="post" class="form-horizontal" id="companyEdit">
					<div class="form-group">
    <label for="Company" class="col-sm-2 control-label">Company</label>
    <div class="col-sm-8">
      <input type="text" class="form-control input-sm" id="company" name="Companyname" value="<s:property value="%{#session.Company.Companyname}"/>"  disabled="disabled"  placeholder="Company Name" autocomplete="off" required>
    </div>
  </div>

  <div class="form-group">
    <label for="Website" class="col-sm-2 control-label">Website</label>
    <div class="col-sm-8">     
       <input  class="form-control input-sm" type="url" name="Website" required value="<s:property value="%{#session.Company.Website}"/>"  placeholder="http://www.example.com" />
    </div>
  </div>
  <%-- <div class="form-group">
    <label for="Username" class="col-sm-2 control-label">User ID </label>
    <div class="col-sm-8">
      <input type="text" class="form-control input-sm" id="username" name="Username"  value="<s:property value="%{#session.Company.Username}"/>" disabled="disabled" placeholder="User ID " autocomplete="off" required>
    </div>
  </div> --%>
  <div class="form-group">
    <label for="Email" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-8">   
      <input type="email" class="form-control input-sm" name="Email"  id="email" placeholder="Email" value="<s:property value="%{#session.Company.Email}"/>"  disabled="disabled" autocomplete="off" required>
    </div>
  </div>

  <div class="form-group">
    <label for="Address" class="col-sm-2 control-label">Address</label>
    <div class="col-sm-8">
      <textarea class="form-control input-sm" id="address" name="Address" placeholder="Address"   autocomplete="off" required><s:property value="%{#session.Company.Address}"/></textarea>
   
    </div>
  </div>

  <div class="form-group">
    <label for="Country" class="col-sm-2 control-label">Country</label>
      <div class="col-sm-8">
        <select class="form-control input-sm" name="Countryname" id="country" disabled="disabled" autocomplete="off" required>
          <option value="<s:property value="%{#session.Company.Countryname}"/>" ><s:property value="%{#session.Company.Countryname}"/></option>
         
        </select>      
      </div>
  </div>

  <div class="form-group">
    <label for="City" class="col-sm-2 control-label">City</label>
      <div class="col-sm-8">
        <select class="form-control input-sm" name="City" id="city" disabled="disabled" autocomplete="off" required>
           <option value="<s:property value="%{#session.Company.City}"/>"><s:property value="%{#session.Company.City}"/></option>
         
        </select>      
      </div>
  </div>
   
   <div class="form-group">
    <label for="telphone" class="col-sm-2 control-label">Phone</label>
    <div class="col-sm-8">     
       <input type="tel" class="form-control input-sm" name="Phone" id="telphone" value="<s:property value="%{#session.Company.Phone}"/>" placeholder="8105979291" autocomplete="off" required>
    </div>
  </div>

 <%--  <div class="form-group">
    <label for="fax" class="col-sm-2 control-label">Fax</label>
    <div class="col-sm-8">     
       <input type="tel" class="form-control input-sm" name="Fax" id="fax" value="<s:property value="%{#session.Company.Fax}"/>" placeholder="(425) 555-0067" autocomplete="off" required>

      
    </div>
  </div>
 --%>
  

  <div class="border-common">
              
                <p class="h4"><b>Logo</b></p>
              
  </div>

    <div class="form-group">
        <label for="uploadimage" class="col-sm-2 control-label">Upload Image</label>
        <div class="col-sm-8">  

        <div class="row">
       
      <div class="col-sm-6 file-upload">

      <figure><img ng-src="{{imageSrc}}" height="100" width="100" alt="user image"></figure>
         <input type="file" id="uploadimage"  name="Imagepath" ng-model="Imagepath" accept="image/*"  ng-file-select="onFileSelect($files)"   >               
         
      </div>
      <!-- 

      <div class="col-sm-6 ">
        <div id="fileinfo">
            
                          <div id="filename">logo.png</div>
                          <div id="filesize">7.6 KB</div>
                          <div id="filetype">image/jpeg</div>
                          <div id="filedim">90 x 50</div>
            </div>  
      </div> -->
    </div>
   
    
    </div>

    
  </div>

  <div class="border-common">
              
                <p class="h4"><b>Branch</b></p>
             
  </div>


  <div class="form-group">
                  <label for="Company-type" class="col-sm-2 control-label">Location</label>
                  <div class="col-sm-8">     
                     <select class="form-control input-sm" name="location" disabled="disabled" id="location" autocomplete="off" required>
                         <option value="<s:property value="%{#session.Company.location}"/>"><s:property value="%{#session.Company.location}"/></option>
                       
                      </select>  

                      <div class="row">
                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                             <div class="radio">
                                  <label>
                                    <input type="radio" name="Services" id="optionsRadios1" value="Airways" checked>
                                    Airways
                                  </label>
                                </div>
                                <div class="radio">
                                  <label>
                                    <input type="radio" name="Services" id="optionsRadios2" value="Car">
                                    Car
                                  </label>
                                </div>
                           </div>

                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                             <div class="radio">
                <label>
                  <input type="radio" name="Services" id="optionsRadios1" value="Hotel">
                 Hotel
                </label>
              </div>
              <div class="radio">
                <label>
                  <input type="radio" name="Services" id="optionsRadios2" value="other">
                 other
                </label>
              </div>
                           </div>
                         </div>   
                  </div>


  </div>

  <div class="border-common">
              
                <p class="h4"><b>Description</b></p>
             
  </div>

  <div class="form-group">
    <label for="Description" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-8">
      <textarea class="form-control input-sm" id="Description" name="Companydescription"  placeholder="Description" autocomplete="off" required><s:property value="%{#session.Company.Companydescription}"/></textarea>
    </div>
  </div>



  <div class="border-common">
             
                <p class="h4"><b>Billing Address</b></p>
              
  </div>

  <div class="form-group">
    <label for="Company" class="col-sm-2 control-label">Company</label>
    <div class="col-sm-8">
      <input type="text" class="form-control input-sm" id="company" name="Billingcompany" value="<s:property value="%{#session.Company.Billingcompany}"/>" disabled="disabled" placeholder="Company Name" autocomplete="off" required>
    </div>
  </div>

  <div class="form-group">
    <label for="Reference" class="col-sm-2 control-label">Reference</label>
    <div class="col-sm-8">
      <input type="text" class="form-control input-sm" id="Reference" name="Billingreference" value="<s:property value="%{#session.Company.Billingreference}"/>" disabled="disabled" placeholder="Your Reference" autocomplete="off" required>
    </div>
  </div>

  <div class="form-group">
    <label for="Address" class="col-sm-2 control-label">Address</label>
    <div class="col-sm-8">
      <textarea class="form-control input-sm" id="address" name="Billingaddress" placeholder="Address"  disabled="disabled"  autocomplete="off" required><s:property value="%{#session.Company.Billingaddress}"/></textarea>    
    </div>
  </div>
  <div class="form-group ">
    <label for="Country" class="col-sm-2 control-label">Country</label>
      <div class="col-sm-8">
        <select class="form-control input-sm" name="Billingcountry" id="country" disabled="disabled" autocomplete="off" required>
          <option value="<s:property value="%{#session.Company.Billingcountry}"/>"><s:property value="%{#session.Company.Billingcountry}"/></option>
         
        </select>      
      </div>
  </div>

<div class="border-common clearfix">

                <p class="h4"><b>Security</b></p>
               
             
  </div>

   <div class="form-group">
    <label for="Question" class="col-sm-2 control-label">Question</label>
    <div class="col-sm-8">

    <input type="text" class="form-control input-sm" id="Question" name="Securityquestion" value="<s:property value="%{#session.Company.Securityquestion}"/>" placeholder="Question" autocomplete="off" required>     
    </div>
  </div>

  <div class="form-group">
    <label for="Question" class="col-sm-2 control-label">Answer</label>
    <div class="col-sm-8">
    <input type="text" class="form-control input-sm" id="Answer" name="Securityanswer" value="<s:property value="%{#session.Company.Securityanswer}"/>" placeholder="Answer" autocomplete="off" required>     
    </div>
  </div>

 <div class="form-group text-center">
      <div class="col-xs-12 submitWrap text-center">
        <button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Update</button>
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
	 <script type="text/javascript" src="js/app.js"></script>
	    <%@ include file="DashboardFooter.jsp"%> 
	<%--  <%@ include file="DashFooter.jsp" %> --%>  
		<script> 
	$(document).ready(function(){  
	    $("#companyEdit").validate({
	    	 rules: { 
	            "email": {
	                required: true,
	                email: true
	            }   
	        }, 
	        
	        messages: { 
	            "email": {
	                required: "Please, enter an email",
	                email: "Email is invalid"
	            },
	        }, 
	        highlight: function(element, errorClass, validClass) { 
	            $(element).addClass(errorClass).removeClass(validClass);
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	          },
	          success: function(element) { 
	         element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            $(element).remove();
	          },
	        submitHandler: function (form) {   
	            return false;  
	        } 
	    });
	    
	    $('#buttonSubmit').click(function() { 
	  	if($("#companyEdit").valid())  
	    	 document.getElementById("companyEdit").submit();
	  /* 	else
	  		document.getElementById("requiredspan").val = "Please Fill Required Feilds"  */
	    });    
	    
	});

	</script>
</body>

</html>