var randomScalingFactor = function(){ return Math.round(Math.random()*100)};

function getgraphdata()
{
	 
   // a function which will load data from other file after x seconds
     var protocol=location.protocol;
 	   var host=location.host;
 	  var totUrl=$(location).attr('href');
 	 	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
 	 	  var finalUrl = newUrl+"GetGraphDataJson";
 	   
 	   
 	   
 	  var url=protocol+"//"+host+"/TravelAdmin/GetGraphDataJson"; 
  				comfirmList= [];
				$.ajax({
					    method: "GET",
					    url:finalUrl,
					   /*  data: {type:"today"}, */
					    success:function(data,status)
						{ 
					    	
					    	console.log(data);
					    	//console.log("id.."+data.jsonobj.FlightDayofweeklistcount[2]);
					    //	FlightDayofweekdata = data.jsonobj.FlightDayofweeklistcount;
					    //	console.log(FlightDayofweekdata);
					    	
					    	
					    	//get here number of orders recieved weekly 
					    	var doughnutData = [
					    	    {
					    			value: data.jsonobj.FlightWeeklyOrderscount,
					    	        color: "rgb(0, 130, 188)",
					    	        highlight: "rgb(7, 156, 222)",
					    	        label: "Air"
					    	    },
					    	    {
					    	        value: data.jsonobj.HotelWeeklyOrdercount,
					    			color:"rgb(188, 0, 7)",
					    			highlight: "#af1519",
					    	        label: "Hotel"
					    	    }/*,
					    	    {
					    	        value: 400,
					    	        color: "rgb(29, 188, 0)",
					    	        highlight: "rgb(33, 211, 0)",
					    	        label: "Car"
					    	    },*/
					    	];

					    	var barChartData = {
					    			
					    			
					    	    /*labels : ["Mon","Tue","Wed","Thu","Fri","Sat","Sun"],*/
					    			 labels :  data.jsonobj.lastsevendays,
					    			datasets : [
					    	        {
					    	            label: "Economy",
					    	            fillColor : "rgb(0, 130, 188)",
					    	            strokeColor : "rgb(0, 130, 188)",
					    	            pointColor : "rgb(0, 130, 188)",
					    	            highlightFill: "rgba(0, 130, 188,0.75)",
					    	            highlightStroke: "rgba(0, 130, 188,1)",
					    	            data : data.jsonobj.FlightDayofweeklistcount
					    	        },
					    	        {
					    	            label: "Economy",
					    	            fillColor : "rgb(188, 0, 7)",
					    	            strokeColor : "rgb(188, 0, 7)",
					    	            pointColor : "rgb(188, 0, 7)",
					    	            highlightFill: "rgba(220,220,220,0.75)",
					    	            highlightStroke: "rgba(220,220,220,1)",
					    	            data : data.jsonobj.HotelDayofweeklistcount
					    	        }/*,
					    	        {
					    	            label: "Economy",
					    	            fillColor : "rgb(188, 0, 7)",
					    	            strokeColor : "rgb(188, 0, 7)",
					    	            pointColor : "rgb(188, 0, 7)",
					    	            highlightFill: "rgba(220,220,220,0.75)",
					    	            highlightStroke: "rgba(220,220,220,1)",
					    	            data : [10, 75, 50, 75, 50, 75, 80]
					    	        },
					    	        {
					    	            label: "Economy",
					    	            fillColor : "rgb(0,0,0)",
					    	            strokeColor : "rgb(0,0,0)",
					    	            pointColor : "rgb(0,0,0)",
					    	            highlightFill: "rgba(220,220,220,0.75)",
					    	            highlightStroke: "rgba(220,220,220,1)",
					    	            data : [25, 65, 50, 75, 50, 75, 89]
					    	        }*/
					    	    ]

					    	}

					    	//get here number of orders recieved Monthly 
					    	var doughnutDataMonthly = [
					    	    {
					    			value: data.jsonobj.FlightMonthlyOrderscount,
					    	        color: "rgb(0, 130, 188)",
					    	        highlight: "rgb(7, 156, 222)",
					    	        label: "Air"
					    	    },
					    	    {
					    	        value: data.jsonobj.HotelMonthlyOrdercount,
					    			color:"rgb(188, 0, 7)",
					    			highlight: "#af1519",
					    	        label: "Hotel"
					    	    }/*,
					    	    {
					    	        value: 200,
					    	        color: "rgb(29, 188, 0)",
					    	        highlight: "rgb(33, 211, 0)",
					    	        label: "Car"
					    	    },*/
					    	];

					    	var barChartDataMonthly = {
					    	    //labels : ["Mon","Tue","Wed","Thu","Fri","Sat","Sun"],
					    	    labels : ["Week1","Week2","Week3","Week4"],
					    	    
					    	    datasets : [
					    	        {
					    	            label: "Economy",
					    	            fillColor : "rgb(0, 130, 188)",
					    	            strokeColor : "rgb(0, 130, 188)",
					    	            pointColor : "rgb(0, 130, 188)",
					    	            highlightFill: "rgba(0, 130, 188,0.75)",
					    	            highlightStroke: "rgba(0, 130, 188,1)",
					    	            data : data.jsonobj.FlightWeekofmonthlistcount
					    	        },
					    	        {
					    	            label: "Economy",
					    	            fillColor : "rgb(188, 0, 7)",
					    	            strokeColor : "rgb(188, 0, 7)",
					    	            pointColor : "rgb(188, 0, 7)",
					    	            highlightFill: "rgba(188, 0, 7,0.75)",
					    	            highlightStroke: "rgba(188, 0, 7,1)",
					    	            data : data.jsonobj.HotelWeekofmonthlistcount
					    	        }
					    	    ]

					    	}
					    	
					    	
					    	var lineChart = {
						    	    labels : ["Mon","Tue","Wed","Thu","Fri","Sat","Sun"],
						    	    // labels : ["Week1","Week2","Week3","Week4"],
					    			//labels: [1500,1600,1700,1750,1800,1850,1900,1950,1999,2050],
						    	    datasets : [
						    	        { 	
						    	            /*borderColor: "#3e95cd",*/
						    	            
						    	             data : data.jsonobj.FlightWeeklyOrderscount,
						    	            //data: [86,114,221,467,547,675,897],
						    	            fill: false,
						    	        }  
						    	    ]

						    	}
					    	
					    	
					    	 // Dougnut Chart
					        var doctx = document.getElementById("chart-area").getContext("2d");
					        window.myDoughnut = new Chart(doctx).Doughnut(doughnutData, {responsive : true});
					        
					        // Bar Chart
					        var ctx = document.getElementById("chart-bar").getContext("2d");
					        window.myBar = new Chart(ctx).Bar(barChartData, {
					            responsive : true
					        });

					    	// for monthly Data 
					    	
					    	   // Dougnut Chart
					        var doctx = document.getElementById("chart-area-month").getContext("2d");
					        window.myDoughnut = new Chart(doctx).Doughnut(doughnutDataMonthly, {responsive : true});
					        
					        // Bar Chart
					        var ctx = document.getElementById("chart-bar-month").getContext("2d");
					        window.myBar = new Chart(ctx).Bar(barChartDataMonthly, {
					            responsive : true
					        });
					        
					        
					     // Line Chart
					        var ctx = document.getElementById("line-chart").getContext("2d");
					        window.myLine = new Chart(ctx).Line(lineChart, {
					            responsive : true
					        });
					    	
					     /*$("#newOrder").text(data.jsonobj.FlightOrdercount);
					    	 $("#hotelOrders").text(data.jsonobj.HotelOrdercount);
					    	 $("#flightconfirmCount").text(data.jsonobj.Flightconfirmordercount);
					    	 $("#hotelOrders").text(data.jsonobj.HotelOrdercount);
					    	 $("#hotelconfirmCount").text(data.jsonobj.Hotelconfirmordercount);
					    	 $("#flightpaymentcount").text(data.jsonobj.Flightpaymentordercount);
					    	 $("#hotelpaymentcount").text(data.jsonobj.Hotelpaymentordercount);			
					    	 $("#distributorlist").text(data.jsonobj.totaldistributorlist);		
					    	 $("#agentlist").text(data.jsonobj.totalagentlist);	*/
					  				      
					     },
						error: function(xhr,status,error)
						{
							console.log(error)
						  /*  alert(error); */
						}
					});  
 }





window.onload = function(){
	//getgraphdata();

	//console.log(FlightDayofweekdata);
   /*  // Dougnut Chart
    var doctx = document.getElementById("chart-area").getContext("2d");
    window.myDoughnut = new Chart(doctx).Doughnut(doughnutData, {responsive : true});
    
    // Bar Chart
    var ctx = document.getElementById("chart-bar").getContext("2d");
    window.myBar = new Chart(ctx).Bar(barChartData, {
        responsive : true
    });

	// for monthly Data 
	
	   // Dougnut Chart
    var doctx = document.getElementById("chart-area-month").getContext("2d");
    window.myDoughnut = new Chart(doctx).Doughnut(doughnutDataMonthly, {responsive : true});
    
    // Bar Chart
    var ctx = document.getElementById("chart-bar-month").getContext("2d");
    window.myBar = new Chart(ctx).Bar(barChartDataMonthly, {
        responsive : true
    });*/
    
    
}
