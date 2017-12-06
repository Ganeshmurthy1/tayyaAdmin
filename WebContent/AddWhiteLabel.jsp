<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>

<div class="row" id="dash-us-register">

				
				<form action="insertBandName" method="post" class="form-horizontal"
					name="myForm" id="formSubmit">
					

					<div class="form-group">
						<label for="newBandName" class="col-sm-2 control-label">Band Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value=""
								required id="newBandName" name="newBandName"
								placeholder="Enter new band name">
						</div>
					</div>
					<div class="form-group">
						<label for="newBandCode" class="col-sm-2 control-label">Band Code</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value=""
								required id="newBandCode" name="newBandCode"
								placeholder="Enter new band code">
						</div>
					</div>
					<div class="col-sm-12" id="hiddenDiv"></div>

				</form>


				<div class=" text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="button" id="addBandButton"
							onclick="checkNameExistingOrSave(document.getElementById('newBandName').value,document.getElementById('newBandCode').value);"
							class="btn btn-primary btn-lg">Add Band</button>

						<!-- <button type="button" class="btn btn-primary btn-lg">Add
								Designation</button> -->
					</div>
				</div>
				<!-- </form> -->
			</div>
			
			<script>
					
		$(document).ready(function(){		
			
			 $("#formSubmit").validate({
		    	 ignore: false,  
		    	rules: {
		            "email": {
		                required: true,
		                email: true
		            },
		        },
		        
		        messages: {
		            "email": {
		                required: "Please, enter an email",
		                email: "Email is invalid"
		            },
		        },
		        submitHandler: function (form) { 
		            return false; 
		        }, 
		        highlight: function(element, errorClass, validClass) { 
		            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-ok').addClass('glyphicon-remove');
		            $(element).addClass(errorClass).removeClass(validClass);
		            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		          },
		          success: function(element) {
		            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-remove').addClass('glyphicon-ok');
		         element.closest('.form-group').removeClass('has-error').addClass('has-success');
		            $(element).remove();
		          }
		    });
		    
			
			
		$('#addBandButton').click(function() {
			 $("#formSubmit").valid();
			 if($("#formSubmit").valid()){ 
				 	var bandName=document.getElementById('newBandName').value;
				 	var bandCode=document.getElementById('newBandCode').value
				 	//checkNameExistingOrSave(bandName,bandCode);
				 	 checkNameExistingOrSave(bandName,bandCode);
		     	// document.getElementById("createApiProvider").submit();
		     }
		})
		
		function checkNameExistingOrSave(bandName,bandCode) {
			console.log("bandName",bandName,bandCode);
			var boolName = true;
			var boolCode = true;
			<c:forEach items="${bandModelList}" var="item" varStatus="status">
			if ("${item.bandName}".toLowerCase() === bandName.toLowerCase()) {
				document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Name Already Existed !   Please try another ...</i></span>';
				boolName = false;
			}
			if ("${item.bandCode}".toLowerCase() === bandCode.toLowerCase()) {
				document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Code Already Existed !   Please try another ...</i></span>';
				boolCode = false;
			}
			</c:forEach>
			if (boolName && boolCode) {
				document.getElementById("formSubmit").submit();
			}
          }
		})
	</script>