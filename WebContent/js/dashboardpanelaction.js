$(".panel-fullscreen").on("click",function(){ 
	    panel_fullscreen($(this).parents(".panel"));
	    return false;
	}); 

	function panel_fullscreen(panel){    
	    
	    if(panel.hasClass("panel-fullscreened")){
	        panel.removeClass("panel-fullscreened").unwrap();
	        panel.find(".panel-body").css("height","");
	        panel.find(".panel-fullscreen .fa").removeClass("fa-compress").addClass("fa-expand");        
	        
	        $(window).resize();
	    }else{
	        var head    = panel.find(".panel-heading");
	        var body    = panel.find(".panel-body");
	        var footer  = panel.find(".panel-footer");
	        var hplus   = 30;
	        
	        if(body.hasClass("panel-body-table") || body.hasClass("padding-0")){
	            hplus = 0;
	        }
	        if(head.length > 0){
	            hplus += head.height()+21;
	        } 
	        if(footer.length > 0){
	            hplus += footer.height()+21;
	        } 

	        panel.find(".panel-body").height($(window).height() - hplus);
	        
	        
	        panel.addClass("panel-fullscreened").wrap('<div class="panel-fullscreen-wrap"></div>');        
	        panel.find(".panel-fullscreen .fa").removeClass("fa-expand").addClass("fa-compress");
	        
	        $(window).resize();
	    }
	}
	
	function panel_remove(panel,action,callback){    
	    if(action && action === "before" && typeof callback === "function") 
	        callback();
	    
	    panel.animate({'opacity':0},200,function(){
	        panel.parent(".panel-fullscreen-wrap").remove();
	        $(this).remove();        
	        if(action && action === "after" && typeof callback === "function") 
	            callback();
	        
	        
	        onload();
	    });    
	}