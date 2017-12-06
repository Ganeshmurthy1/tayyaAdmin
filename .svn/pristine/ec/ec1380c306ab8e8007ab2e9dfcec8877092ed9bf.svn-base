function roomDetails(){
	var roomindex1 = "$0,";
	var roomindex2 = "$1,";
	var roomindex3 = "$2,";
	var roomindex4 = "$3,"; 

	var firstRoomAge = '';
	var secondRoomAge = '';
	var thirdRoomAge = '';
	var fourthRoomAge = '';

	var totalrooms = $("#totalrooms").val();
	var room1adult = $("#RoomAdult1").val();
	var room2adult = $("#RoomAdult2").val();
	var room3adult = $("#RoomAdult3").val();
	var room4adult =$("#RoomAdult4").val();
	var room1child = $("#Childs").val();
	var room2child = $("#Childs2").val();
	var room3child = $("#Childs3").val();
	var room4child = $("#Childs4").val();

    var room1childage = "";
    var room1childage2 = "";
    var room1childage3 = "";
    var room1childage4 = "";
    var room1childage5 = "";
    for(var i=0;i<room1child;i++){
         	  room1childage += $('#room1Age'+(i+1)).val(); 
       
        if(i != room1child -1){ 
     	   room1childage += ",";
        }            
		}
    for(var i=0;i<room2child;i++){
   
    	room1childage2 += $('#room2Age'+(i+1)).val();
      
       if(i != room2child -1){ 
    	   room1childage2 += ",";
       }            
	}
    for(var i=0;i<room3child;i++){
    	   	room1childage3 += $('#room3Age'+(i+1)).val();
      
       if(i != room3child -1){ 
    	   room1childage3 += ",";
       }            
	}
    for(var i=0;i<room4child;i++){
    	   	room1childage4 += $('#room4Age'+(i+1)).val();
       if(i != room4child -1){ 
    	   room1childage4 += ",";
       }            
	}
    var child1WithAge = '';
    var child2WithAge = '';
    var child3WithAge = '';
    var child4WithAge = '';
   
    if(room1child == 0){
    	child1WithAge = 0;
    }else{
    	child1WithAge = room1child+','+room1childage;
    }
    if(room2child == 0){
    	child2WithAge = 0;
    }else{
    	child2WithAge = room2child+','+room1childage2;
    }
    if(room3child == 0){
    	child3WithAge = 0;
    }else{
    	child3WithAge = room3child+','+room1childage3;
    }
    if(room4child == 0){
    	child4WithAge = 0;
    }else{
    	child4WithAge = room4child+','+room1childage4;
    }
    
	var room1complete = roomindex1+room1adult+','+child1WithAge;
	var room2complete = roomindex2+room2adult+','+child2WithAge;
	var room3complete = roomindex3+room3adult+','+child3WithAge;
	var room4complete = roomindex4+room4adult+','+child4WithAge;

var rooms = []; var totalAdultCount = []; var totalChildCount = [];
	
	if(totalrooms == 1){
		rooms = room1complete;
		totalAdultCount = parseInt(room1adult);
		totalChildCount = parseInt(room1child);
	}else if(totalrooms == 2){
		rooms = room1complete+','+room2complete;
		totalAdultCount = parseInt(room1adult) + parseInt(room2adult);
		totalChildCount = parseInt(room1child)+  parseInt(room2child);
		
	}else if(totalrooms == 3){
		rooms = room1complete+','+room2complete+','+room3complete;
		totalAdultCount = parseInt(room1adult) + parseInt(room2adult) + parseInt(room3adult);
		totalChildCount = parseInt(room1child) + parseInt(room2child) + parseInt(room3child);
	}else if(totalrooms == 4){
		rooms = room1complete+','+room2complete+','+room3complete+','+room4complete;
		totalAdultCount = parseInt(room1adult) + parseInt(room2adult) + parseInt(room3adult)+ parseInt(room4adult);
		totalChildCount = parseInt(room1child) + parseInt(room2child) + parseInt(room3child)+ parseInt(room4child);
	}
	return rooms;
	/*console.log('rooms',rooms);
	$('#rooms').val(rooms);*/
/*
console.log('rooms',rooms);
console.log('totalAdultCount',totalAdultCount);
console.log('totalChildCount',totalChildCount);*/


};

