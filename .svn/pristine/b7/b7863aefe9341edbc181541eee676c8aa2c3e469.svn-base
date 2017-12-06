$('.activeStatus').change(function (){
		var status = false;
		var id = $(this).data("id");
		if($(this).is(":checked")==false)
		{
			status= false;
			$(this).attr('checked', false);
			// $(this).attr('value', false);
		}
		else{
			status= true;
			$(this).attr('checked', true);
			//$(this).attr('value', true);
		}
		var name = $(this).attr("name");

		$.ajax({
			url :"updateCommonConfigStatus",
			data : {
				id :id,
				status:status,
				switchType:name
			}, 
			success : function(data, textStatus, jqXHR) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}

		});
  });
  
  $('.tboActive').change(function (){
		var status = false;
		var id = $(this).data("id");
		if($(this).is(":checked")==false)
		{
			status= false;
			$(this).attr('checked', false);
			// $(this).attr('value', false);
		}
		else{
			status= true;
			$(this).attr('checked', true);
			//$(this).attr('value', true);
		}
		var name = $(this).attr("name");

		$.ajax({
			url :"updateTboStatus",
			data : {
				id :id,
				active:status,
			}, 
			success : function(data, textStatus, jqXHR) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}

		});
});
  
  $('.bluestarActive').change(function (){
		var status = false;
		var id = $(this).data("id");
		if($(this).is(":checked")==false)
		{
			status= false;
			$(this).attr('checked', false);
			// $(this).attr('value', false);
		}
		else{
			status= true;
			$(this).attr('checked', true);
			//$(this).attr('value', true);
		}
		var name = $(this).attr("name");

		$.ajax({
			url :"updateBluestarStatus",
			data : {
				id :id,
				active:status,
			}, 
			success : function(data, textStatus, jqXHR) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}

		});
});
  
  $('.desiyaActive').change(function (){
		var status = false;
		var id = $(this).data("id");
		if($(this).is(":checked")==false)
		{
			status= false;
			$(this).attr('checked', false);
			// $(this).attr('value', false);
		}
		else{
			status= true;
			$(this).attr('checked', true);
			//$(this).attr('value', true);
		}
		var name = $(this).attr("name");

		$.ajax({
			url :"updateDesiyaStatus",
			data : {
				id :id,
				active:status,
			}, 
			success : function(data, textStatus, jqXHR) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}

		});
});
 
  $('.etravelActive').change(function (){
		var status = false;
		var id = $(this).data("id");
		if($(this).is(":checked")==false)
		{
			status= false;
			$(this).attr('checked', false);
			// $(this).attr('value', false);
		}
		else{
			status= true;
			$(this).attr('checked', true);
			//$(this).attr('value', true);
		}
		var name = $(this).attr("name");

		$.ajax({
			url :"updateEtravelStatus",
			data : {
				id :id,
				active:status,
			}, 
			success : function(data, textStatus, jqXHR) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}

		});
});
 
 /* $('.trawellTagActive').change(function (){
		var status = false;
		var id = $(this).data("id");
		if($(this).is(":checked")==false)
		{
			status= false;
			$(this).attr('checked', false);
			// $(this).attr('value', false);
		}
		else{
			status= true;
			$(this).attr('checked', true);
			//$(this).attr('value', true);
		}
		var name = $(this).attr("name");

		$.ajax({
			url :"updateTrawellTagStatus",
			data : {
				id :id,
				active:status,
			}, 
			success : function(data, textStatus, jqXHR) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}

		});
});
*/