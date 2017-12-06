function flightsearch() {

    var origin = $("#twooriginid").val();
    var destination = $("#twodestinationid").val();
    var deptdate = $("#twodpd1").val();
    var adult = $("#oneadults option:selected").text();
    var child = $("#onekid option:selected").text();
    var infant = $("#oneinfant option:selected").text();
    var arrdate = $("#twodpd2").val();
    var orifirstindex = origin.indexOf(',') + 1;
    var orilastindex = origin.lastIndexOf(',');
    var desfirstindex = destination.indexOf(',') + 1;
    var deslastindex = destination.lastIndexOf(',');
    var origincountry = origin.substring(orifirstindex, orilastindex);
    var destinatiocountry = destination.substring(desfirstindex,
        deslastindex);
    var total = parseInt(adult) + parseInt(child) + parseInt(infant);
    var ibeurl = $("#ibeurl").val();

    if (origin == "") {
        $("#errori").text("Please Enter the Origin City");
        $('#errori').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if (destination == "") {
        $("#errdes").text("Please Enter the Destination City");
        $('#errdes').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if (origin == destination) {
        $("#errdes").text("Please Enter the Different Destination City");
        $('#errdes').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if (deptdate == "") {
        $("#errdep").text("Select the Depart date");
        $('#errdep').stop().fadeIn(400).delay(3000).fadeOut(400);

    } else if (arrdate == "" && $('#twodpd2').prop('disabled') == false) {
        $("#errarr").text("Select the Arrival date");
        $('#errarr').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if (origincountry == "India" && destinatiocountry == "India") {

        if ($("#triptype").val() == 'R') {
            $("#actionurl").val("specialroundtrip");
            $('#twowayform').attr('action', ibeurl);
            $("#twowayform").submit();
        } else {
            $("#actionurl").val("onewaySearch");
            $('#twowayform').attr('action', ibeurl);
            $("#twowayform").submit();
        }
    } else {

        if ($("#triptype").val() == 'R') {
            $("#actionurl").val("TwowaySearch");
            $('#twowayform').attr('action', ibeurl);
            $("#twowayform").submit();
        } else {
            $("#actionurl").val("onewaySearch");
            $('#twowayform').attr('action', ibeurl);
            $("#twowayform").submit();
        }
    }

}

function multicitysubmit() {
    var origin1 = $("#origin1").val();
    var destination1 = $("#destmulti1").val();
    var multidate1 = $("#multidate1").val();
    var origin2 = $("#origin2").val();
    var destination2 = $("#destmulti2").val();
    var multidate2 = $("#multidate2").val();
    var adult = $("#multiadults option:selected").text();
    var child = $("#multikid option:selected").text();
    var infant = $("#multiinfant option:selected").text();
    var total = parseInt(adult) + parseInt(child) + parseInt(infant);
    var ibeurl = $("#ibeurlmulti").val();
    if (origin1 == "") {
        $("#multi1fromerror").text("Select First From ");
        $('#multi1fromerror').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if (destination1 == "") {
        $("#multi1toerror").text("Select First To");
        $('#multi1toerror').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if (multidate1 == "") {
        $("#multi1departerror").text("Select the First Departure date");
        $('#multi1departerror').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if (origin2 == "") {
        $("#multi2fromerror").text("Select Second From ");
        $('#multi2fromerror').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if (destination2 == "") {
        $("#multi2toerror").text("Select Second To");
        $('#multi2toerror').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if (multidate2 == "") {
        $("#multi2departerror").text("Select the Second Departure date");
        $('#multi2departerror').stop().fadeIn(400).delay(3000).fadeOut(400);
    } else if ($(".city3").is(':visible')) {
        var origin3 = $("#origin3").val();
        var destination3 = $("#destmulti3").val();
        var multidate3 = $("#multidate3").val();
        if (origin3 == "") {
            $("#multi3fromerror").text("Select Third From ");
            $('#multi3fromerror').stop().fadeIn(400).delay(3000).fadeOut(400);
        } else if (destination3 == "") {
            $("#multi3toerror").text("Select Third To");
            $('#multi3toerror').stop().fadeIn(400).delay(3000).fadeOut(400);
        } else if (multidate3 == "") {
            $("#multi3departerror").text("Select Third Departure date");
            $('#multi3departerror').stop().fadeIn(400).delay(3000).fadeOut(400);
        } else if (parseInt(total) > 9) {
            $(".errorsmulti").text("Only 9 Travellers Allowed ");
        } else if ($(".city4").is(':visible')) {
            var origin4 = $("#origin4").val();
            var destination4 = $("#destmulti4").val();
            var multidate4 = $("#multidate4").val();
            if (origin4 == "") {
                $("#multi4fromerror").text("Select the Fourth From ");
                $('#multi4fromerror').stop().fadeIn(400).delay(3000).fadeOut(400);
            } else if (destination4 == "") {
                $("#multi4toerror").text("Select the Fourth To");
                $('#multi4toerror').stop().fadeIn(400).delay(3000).fadeOut(400);
            } else if (multidate4 == "") {
                $("#multi4departerror").text("Select the Fourth Departure date");
                $('#multi4departerror').stop().fadeIn(400).delay(3000).fadeOut(400);
            } else if (parseInt(total) > 9) {
                $(".errorsmulti").text("Only 9 Travellers Allowed ");
                $('.errorsmulti').stop().fadeIn(400).delay(3000).fadeOut(400);
            } else {
                $("#multiactionurl").val("multiwaySearch");
                $('#multi-form').attr('action', ibeurl);
                $("#multi-form").submit();
            }
        } else {
            $("#multiactionurl").val("multiwaySearch");
            $('#multi-form').attr('action', ibeurl);
            $("#multi-form").submit();
        }
    } else {
        $("#multiactionurl").val("multiwaySearch");
        $('#multi-form').attr('action', ibeurl);
        $("#multi-form").submit();
    }
}

$("#oneway").click(function() {
    $("#triptype").val("O");
    $("#tra").removeClass('active in');
    $("#return").addClass('active in');
});

$("#return-tab").click(function() {
    $("#triptype").val("R");
    $("#tra").removeClass('active in');
    $("#return").addClass('active in');
});

function selecttriptype() {
    var type = $("#triptypeselect").val();
    if (type == 'R')
        $('#twodpd2').prop('disabled', false);
    else {
        $('#twodpd2').prop('disabled', true);
        $('#twodpd2').val('');
    }

}

$(document).ready(function() {
    var countrylist = "<option value=Airline>Airline</option>";
    var list = [];
    $.ajax({
        url: 'airlines.json',
        type: 'post',
        dataType: 'json',
        success: function(data) {
            data.sort(compare);
            for (var i = 0; i < data.length; i++) {
                countrylist += "<option value=" + data[i].airlinecode + ">" + data[i].airlinename + " ( " + data[i].airlinecode + " )" + "</option>";
            }
            $("#onewayairline").append(countrylist);
            $("#roundairline").append(countrylist);
            $("#multiairline").append(countrylist);
        }
    });
    $("#origin2").focus(function() {
        $("#origin2").val($("#destmulti1").val());
    });
    $("#origin3").focus(function() {
        $("#origin3").val($("#destmulti2").val());
    });
    $("#origin4").focus(function() {
        $("#origin4").val($("#destmulti3").val());
    });
});

function compare(a, b) {
    if (a.airlinename < b.airlinename) return -1;
    else if (a.airlinename > b.airlinename) return 1;
    else
        return 0;
}

function sortarray(list) {
    var sortlistarr1 = [];
    sortlistarr1 = list.sort(function(a, b) {
        return String(a.airlinename.trim()) - String(b.airlinename.trim());
    });
    console.log(sortlistarr1);
    return sortlistarr1;
}
$(function() {
    $('.login-pop-up, .b-cl, .b-ok').click(function() {
        closePopup();
    });

    function closePopup() {
        $('.login-pop-up, .b-cl, .b-ok').hide();
    }
});
$(function() {
    var values = new Array();
    $(document).on('change', '.form-group-multiple-selectsone .input-group-multiple-selectone:last-child select', function() {
        var selectsLength = $('.form-group-multiple-selectsone .input-group-multiple-selectone select').length;
        var optionsLength = ($(this).find('option').length) - 1;
        if (selectsLength < optionsLength) {
            var sInputGroupHtml = $(this).parent().html();
            var sInputGroupClasses = $(this).parent().attr('class');
            $(this).parent().parent().append('<div class="' + sInputGroupClasses + '">' + sInputGroupHtml + '</div>');
        }
        updateValues();
    });
    $(document).on('change', '.form-group-multiple-selectsone .input-group-multiple-selectone:not(:last-child) select', function() {
        updateValues();
    });
    $(document).on('click', '.input-group-addon-removeone', function() {
        var selectsLength = $('.form-group-multiple-selectsone .input-group-multiple-selectone select').length;
        if (selectsLength > 1) {
            $(this).parent().remove();
            updateValues();
        }
    });

    function updateValues() {
        values = new Array();
        $('.form-group-multiple-selectsone .input-group-multiple-selectone select').each(function() {
            var value = $(this).val();
            if (value != 0 && value != "") {
                values.push(value);
            }
        });
        $('.form-group-multiple-selectsone .input-group-multiple-selectone select').find('option').each(function() {
            var optionValue = $(this).val();
            var selectValue = $(this).parent().val();
            if (in_array(optionValue, values) != -1 && selectValue != optionValue) {
                $(this).attr('disabled', 'disabled');
            } else {
                $(this).removeAttr('disabled');
            }
        });
    }

    function in_array(needle, haystack) {
        var found = 0;
        for (var i = 0, length = haystack.length; i < length; i++) {
            if (haystack[i] == needle) return i;
            found++;
        }
        return -1;
    }
});
$(function() {
    var values = new Array();
    $(document).on('change', '.form-group-multiple-selectsround .input-group-multiple-selectround:last-child select', function() {
        var selectsLength = $('.form-group-multiple-selectsround .input-group-multiple-selectround select').length;
        var optionsLength = ($(this).find('option').length) - 1;
        if (selectsLength < optionsLength) {
            var sInputGroupHtml = $(this).parent().html();
            var sInputGroupClasses = $(this).parent().attr('class');
            $(this).parent().parent().append('<div class="' + sInputGroupClasses + '">' + sInputGroupHtml + '</div>');
        }
        updateValues();
    });
    $(document).on('change', '.form-group-multiple-selectsround .input-group-multiple-selectround:not(:last-child) select', function() {
        updateValues();
    });
    $(document).on('click', '.input-group-addon-removeround', function() {
        var selectsLength = $('.form-group-multiple-selectsround .input-group-multiple-selectround select').length;
        if (selectsLength > 1) {
            $(this).parent().remove();
            updateValues();
        }
    });

    function updateValues() {
        values = new Array();
        $('.form-group-multiple-selectsround .input-group-multiple-selectround select').each(function() {
            var value = $(this).val();
            if (value != 0 && value != "") {
                values.push(value);
            }
        });
        $('.form-group-multiple-selectsround .input-group-multiple-selectround select').find('option').each(function() {
            var optionValue = $(this).val();
            var selectValue = $(this).parent().val();
            if (in_array(optionValue, values) != -1 && selectValue != optionValue) {
                $(this).attr('disabled', 'disabled');
            } else {
                $(this).removeAttr('disabled');
            }
        });
    }

    function in_array(needle, haystack) {
        var found = 0;
        for (var i = 0, length = haystack.length; i < length; i++) {
            if (haystack[i] == needle) return i;
            found++;
        }
        return -1;
    }
});
$(function() {
    var values = new Array();
    $(document).on('change', '.form-group-multiple-selectsmulti .input-group-multiple-selectmulti:last-child select', function() {
        var selectsLength = $('.form-group-multiple-selectsmulti .input-group-multiple-selectmulti select').length;
        var optionsLength = ($(this).find('option').length) - 1;
        if (selectsLength < optionsLength) {
            var sInputGroupHtml = $(this).parent().html();
            var sInputGroupClasses = $(this).parent().attr('class');
            $(this).parent().parent().append('<div class="' + sInputGroupClasses + '">' + sInputGroupHtml + '</div>');
        }
        updateValues();
    });
    $(document).on('change', '.form-group-multiple-selectsmulti .input-group-multiple-selectmulti:not(:last-child) select', function() {
        updateValues();
    });
    $(document).on('click', '.input-group-addon-removemulti', function() {
        var selectsLength = $('.form-group-multiple-selectsmulti .input-group-multiple-selectmulti select').length;
        if (selectsLength > 1) {
            $(this).parent().remove();
            updateValues();
        }
    });

    function updateValues() {
        values = new Array();
        $('.form-group-multiple-selectsmulti .input-group-multiple-selectmulti select').each(function() {
            var value = $(this).val();
            if (value != 0 && value != "") {
                values.push(value);
            }
        });
        $('.form-group-multiple-selectsmulti .input-group-multiple-selectmulti select').find('option').each(function() {
            var optionValue = $(this).val();
            var selectValue = $(this).parent().val();
            if (in_array(optionValue, values) != -1 && selectValue != optionValue) {
                $(this).attr('disabled', 'disabled');
            } else {
                $(this).removeAttr('disabled');
            }
        });
    }

    function in_array(needle, haystack) {
        var found = 0;
        for (var i = 0, length = haystack.length; i < length; i++) {
            if (haystack[i] == needle) return i;
            found++;
        }
        return -1;
    }
});
var query = (typeof(custom) == "undefined") ? window.location.search.substring(1) : custom;
var hu = query;
var gy = hu.split("&");
var vars = [],
    hash;
for (i = 0; i < gy.length; i++) {
    var ft = gy[i].split("=");
    vars[ft[0]] = ft[1];
    if (ft[0] == 'request_locale') {
        $("#tworequestlocale").val(decodeURIComponent(ft[1]));
        $("#onerequestlocale").val(decodeURIComponent(ft[1]));
        $("#multirequestlocale").val(decodeURIComponent(ft[1]));
    }
    if (ft[0] == 'origin1') $("#origin1").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'origin2') $("#origin2").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'origin3') $("#origin3").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'origin4') $("#origin4").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'destination1') $("#destmulti1").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'destination2') $("#destmulti2").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'destination3') $("#destmulti3").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'destination4') $("#destmulti4").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'depDate1') $("#multidate1").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'depDate2') $("#multidate2").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'depDate3') $("#multidate3").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'depDate4') $("#multidate4").val(decodeURIComponent(ft[1]));
    if (ft[0] == 'trips') {
        if (ft[1] == '3') $(".city3").show();
        if (ft[1] == '4') {
            $(".city3").show();
            $(".city4").show();
            $(".rowButton").hide();
        }
        $("#myTabs li").removeClass("active");
        $("#myTabContent .tab-pane").removeClass("active in");
        $("#multirole").addClass("active");
        $(".tabmulti").addClass("active in");
    }
}
$('#twodpd1').datepicker({
    numberOfMonths: 1,
    firstDay: 1,
    dateFormat: 'dd/mm/yy',
    minDate: '0',
    onSelect: function(dateStr) {    	
        var d1 = $('#twodpd1').datepicker("getDate");
        d1.setDate(d1.getDate() + 0);        
        var d2 = $('#twodpd1').datepicker("getDate");
        d2.setDate(d2.getDate() + 31);       
        $("#twodpd2").datepicker("setDate", null);
        $("#twodpd2").datepicker("option", "minDate", d1);
    },
    onClose: function(dateSt) {    	
        $("#twodpd2").focus();
    }
});
$("#twodpd2").datepicker({
    numberOfMonths: 1,
    firstDay: 1,
    dateFormat: 'dd/mm/yy',
    onSelect: function(dateStr) {}
});
var dates = $("#onedpd").datepicker({
    minDate: 0,
    dateFormat: 'dd/mm/yy',
    numberOfMonths: 1,
    onSelect: function(date) {
        for (var i = 0; i < dates.length; ++i) {
            if (dates[i].id < this.id) $(dates[i]).datepicker('option', 'maxDate', date);
            else if (dates[i].id > this.id) $(dates[i]).datepicker('option', 'minDate', date);
        }
    }
});
var multidate = $("#multidate1,#multidate2,#multidate3,#multidate4,#multidate5").datepicker({
    minDate: 0,
    dateFormat: 'dd/mm/yy',
    numberOfMonths: 1,
    onSelect: function(date) {
        for (var i = 0; i < multidate.length; ++i) {
            if (multidate[i].id < this.id) $(multidate[i]).datepicker('option', 'maxDate', date);
            else if (multidate[i].id > this.id) $(multidate[i]).datepicker('option', 'minDate', date);
        }
    }
});
$('#datain').datepicker({
    numberOfMonths: 1,
    firstDay: 1,
    dateFormat: 'dd/mm/yy',
    minDate: '0',
    onSelect: function(dateStr) {
        var d1 = $(this).datepicker("getDate");
        d1.setDate(d1.getDate() + 0);
        var d2 = $(this).datepicker("getDate");
        d2.setDate(d2.getDate() + 31);
        $("#dateout").datepicker("setDate", null);
        $("#dateout").datepicker("option", "minDate", d1);
    },
    onClose: function(dateSt) {
        $("#dateout").focus();
    }
});
$("#dateout").datepicker({
    numberOfMonths: 1,
    firstDay: 1,
    dateFormat: 'dd/mm/yy',
    onSelect: function(dateStr) {}
});

/*function twowaysubmit() {
    var origin = $("#twooriginid").val();
    var destination = $("#twodestinationid").val();
    var deptdate = $("#twodpd1").val();
    var arrdate = $("#twodpd2").val();
    var adult = $("#twoadults option:selected").text();
    var child = $("#twokid option:selected").text();
    var infant = $("#twoinfant option:selected").text();
    var orifirstindex = origin.indexOf(',') + 1;
    var orilastindex = origin.lastIndexOf(',');
    var desfirstindex = destination.indexOf(',') + 1;
    var deslastindex = destination.lastIndexOf(',');
    var origincountry = origin.substring(orifirstindex, orilastindex);
    var destinatiocountry = destination.substring(desfirstindex, deslastindex);;
    var total = parseInt(adult) + parseInt(child) + parseInt(infant);
    if (origin == "") { $("#twowayfromerror").text("Select From"); } else if (destination == "") { $("#twowayfromerror").text("");
        $("#twowaytoerror").text("Select To"); } else if (deptdate == "") { $("#twowaytoerror").text("");
        $("#twowaydeparterror").text("Select the Depart date"); } else if (arrdate == "" && $('#arrival:visible').length != 0) { $("#twowaydeparterror").text("");
        $("#twowayarrivalerror").text("Select the Arrival date"); } else if (parseInt(total) > 9) { $("#twowayfromerror").text("");
        $("#twowaytoerror").text("");
        $("#twowaydeparterror").text("");
        $("#twowayarrivalerror").text("");
        $(".errors").text("Only 9 Travellers Allowed "); } else if (origincountry === "Malaysia" && destinatiocountry === "Malaysia") { $("#twowayfromerror").text("");
        $("#twowaytoerror").text("");
        $("#twowaydeparterror").text("");
        $("#twowayarrivalerror").text("");
        if ($("#triptype").val() == 'R') { $('#twowayform').attr('action', 'specialroundtrip');
            $("#twowayform").submit(); } else { $('#twowayform').attr('action', 'onewaySearch');
            $("#twowayform").submit(); } } else { $("#twowayfromerror").text("");
        $("#twowaytoerror").text("");
        $("#twowaydeparterror").text("");
        $("#twowayarrivalerror").text("");
        if ($("#triptype").val() == 'R') { $('#twowayform').attr('action', 'TwowaySearch');
            $("#twowayform").submit(); } else { $('#twowayform').attr('action', 'onewaySearch');
            $("#twowayform").submit(); } } }*/

/*function onewaysubmit() {
    var origin = $("#search-source").val();
    var destination = $("#to").val();
    var deptdate = $("#onedpd").val();
    var adult = $("#oneadults option:selected").text();
    var child = $("#onekid option:selected").text();
    var infant = $("#oneinfant option:selected").text();
    var total = parseInt(adult) + parseInt(child) + parseInt(infant);
    if (origin == "") { $("#onewayfromerror").text("Select From"); } else if (destination == "") { $("#onewayfromerror").text("");
        $("#onewaytoerror").text("Select To"); } else if (deptdate == "") { $("#onewaytoerror").text("");
        $("#onewaydeparterror").text("Select the date"); } else if (parseInt(total) > 9) { $("#onewayfromerror").text("");
        $("#onewaytoerror").text("");
        $("#onewaydeparterror").text("");
        $(".errorsone").text("Only 9 Travellers Allowed "); } else { $("#onewayfromerror").text("");
        $("#onewaytoerror").text("");
        $("#onewaydeparterror").text("");
        $("#onewayform").submit(); } }
*/

function AddRooms() {
    var totalrooms = $('#totalrooms').val();
    $('#totalroomtext').text(totalrooms);
    $('#totalpeopletext').text(totalrooms);
    var addrooms = "";
    $('.hotel-repeatadd').html("");
    for (var i = 0; i < parseInt(totalrooms - 1); i++) {
        var index = i + 2;
        addrooms += "<div id='rowid" + index + "' class='clearfix row' ><div class='col-sm-1 clearfix rooms-multiple'><p class='ro-heading'>Room " + index + ":</p></div><div class='col-sm-2 clearfix'><div class='form-group'><label for='inputGroupSuccess2'>Adult(s)</label><div class='input-group'><span class='input-group-addon'><i class='fa fa-user-plus'></i></span> <select class='form-control' name='Adults" + index + "' id='RoomAdult" + index + "' autocomplete='off' required onchange='ChangeRoomChild(" + index + ")'><option>1</option><option>2</option><option>3</option><option>4</option></select></div></div></div>" + "<div class='col-sm-2 clearfix'><div class='form-group'><label for='inputGroupSuccess2'>Child</label><div class='input-group'><span class='input-group-addon'><i class='fa fa-child'></i></span><select class='form-control' id='Childs" + index + "' name='Childs" + index + "' autocomplete='off' required onchange='InsertAge(" + index + ")'><option>0</option> <option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select></div></div></div><div id='childrow" + index + "' class='col-sm-7'></div></div>"

    }
    $('.hotel-repeatadd').append(addrooms);
}

function InsertAge(rowindex) {
    var total = $('#Childs' + rowindex).val();
    var totalrooms = $('#totalrooms').val();
    if (totalrooms == 1) {
        $('#totalchildtext').text(parseInt(total));
    }
    if (totalrooms == 2)
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()));
    if (totalrooms == 3)
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()));
    if (totalrooms == 4)
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()));
    if (totalrooms == 5)
        $('#totalpeopletext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()) + parseInt($('#Childs5').val()));
    if (totalrooms == 6)
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()) + parseInt($('#Childs5').val()) + parseInt($('#Childs6').val()));

    var addages = "";
    $('#childrow' + rowindex).html("");
    for (var i = 0; i < parseInt(total); i++) {
        addages += "<div class='col-sm-2 clearfix' id='c-age' ><div class='form-group'><label for='inputGroupSuccess2'>Age" + (i + 1) + "</label><div class='input-group'><select class='form-control' name='Age" + rowindex + "' autocomplete='off' required><option value='less1'>< 1</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option> </select></div></div></div>";
    }
    var firstcol = "<div class='col-sm-2'></div>";
    $('#childrow' + rowindex).append(firstcol);
    $('#childrow' + rowindex).append(addages);
}

function ChangeRoomChild(roomindex) {
    var totaladult = $('#RoomAdult' + roomindex).val();
    var totalrooms = $('#totalrooms').val();
    $('#Childs' + roomindex).html("");
    if (totaladult == '1') {

        var option = "<option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option> ";
        $('#Childs' + roomindex).append(option);
    }
    if (totaladult == '2') {
        var option = "<option>0</option><option>1</option><option>2</option><option>3</option><option>4</option>";
        $('#Childs' + roomindex).append(option);
    }
    if (totaladult == '3') {
        var option = "<option>0</option><option>1</option><option>2</option><option>3</option>";
        $('#Childs' + roomindex).append(option);
    }
    if (totaladult == '4') {
        var option = "<option>0</option><option>1</option><option>2</option>";
        $('#Childs' + roomindex).append(option);
    }
    $('#childrow' + roomindex).html("");
    if (totalrooms == 1) {
        $('#totalpeopletext').text(parseInt(totaladult));
        $('#totalchildtext').text(parseInt($('#Childs').val()));
    }
    if (totalrooms == 2) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()));
    }
    if (totalrooms == 3) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()) + parseInt($('#RoomAdult3').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()));
    }

    if (totalrooms == 4) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()) + parseInt($('#RoomAdult3').val()) + parseInt($('#RoomAdult4').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()));
    }

    if (totalrooms == 5) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()) + parseInt($('#RoomAdult3').val()) + parseInt($('#RoomAdult4').val()) + parseInt($('#RoomAdult5').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()) + parseInt($('#Childs5').val()));
    }
    if (totalrooms == 6) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()) + parseInt($('#RoomAdult3').val()) + parseInt($('#RoomAdult4').val()) + parseInt($('#RoomAdult5').val()) + parseInt($('#RoomAdult6').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()) + parseInt($('#Childs5').val()) + parseInt($('#Childs6').val()));
    }
}

function FirstrowChildchange(rowindex) {
    var total = $('#Childs').val();
    var totalrooms = $('#totalrooms').val();
    if (totalrooms == 1) {
        $('#totalchildtext').text(parseInt(total));
    }
    if (totalrooms == 2)
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()));
    if (totalrooms == 3)
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs2').val()));
    if (totalrooms == 4)
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()));
    if (totalrooms == 5)
        $('#totalpeopletext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()));
    if (totalrooms == 6)
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()) + parseInt($('#Childs5').val()));
    if (total == '0') {
        $('.Room1Child1').hide();
        $('.Room1Child2').hide();
        $('.Room1Child3').hide();
        $('.Room1Child4').hide();
        $('.Room1Child5').hide();
    }

    if (total == '1') {
        $('.Room1Child1').show();
        $('.Room1Child2').hide();
        $('.Room1Child3').hide();
        $('.Room1Child4').hide();
        $('.Room1Child5').hide();
    }
    if (total == '2') {
        $('.Room1Child1').show();
        $('.Room1Child2').show();
        $('.Room1Child3').hide();
        $('.Room1Child4').hide();
        $('.Room1Child5').hide();
    }
    if (total == '3') {
        $('.Room1Child1').show();
        $('.Room1Child2').show();
        $('.Room1Child3').show();
        $('.Room1Child4').hide();
        $('.Room1Child5').hide();
    }
    if (total == '4') {
        $('.Room1Child1').show();
        $('.Room1Child2').show();
        $('.Room1Child3').show();
        $('.Room1Child4').show();
        $('.Room1Child5').hide();
    }
    if (total == '5') {
        $('.Room1Child1').show();
        $('.Room1Child2').show();
        $('.Room1Child3').show();
        $('.Room1Child4').show();
        $('.Room1Child5').show();
    }
}

function FirstRoomChangeChild() {
    var totaladult = $('#Room1Adult').val();
    var totalpeoplelength = $('#totalpeopletext').text();
    var totalrooms = $('#totalrooms').val();

    $('#Childs').html("");
    if (totaladult == '1') {

        var option = "<option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option>";
        $('#Childs').append(option);
    }
    if (totaladult == '2') {
        var option = "<option>0</option><option>1</option><option>2</option><option>3</option><option>4</option>";
        $('#Childs').append(option);
    }
    if (totaladult == '3') {
        var option = "<option>0</option><option>1</option><option>2</option><option>3</option>";
        $('#Childs').append(option);
    }
    if (totaladult == '4') {
        var option = "<option>0</option><option>1</option><option>2</option>";
        $('#Childs').append(option);
    }
    $('.Room1Child1').hide();
    $('.Room1Child2').hide();
    $('.Room1Child3').hide();
    $('.Room1Child4').hide();
    $('.Room1Child5').hide();

    if (totalrooms == 1) {
        $('#totalpeopletext').text(parseInt(totaladult));
        $('#totalchildtext').text(parseInt($('#Childs').val()));
    }
    if (totalrooms == 2) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()));
    }
    if (totalrooms == 3) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()) + parseInt($('#RoomAdult3').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()));
    }

    if (totalrooms == 4) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()) + parseInt($('#RoomAdult3').val()) + parseInt($('#RoomAdult4').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()));
    }

    if (totalrooms == 5) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()) + parseInt($('#RoomAdult3').val()) + parseInt($('#RoomAdult4').val()) + parseInt($('#RoomAdult5').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()) + parseInt($('#Childs5').val()));
    }
    if (totalrooms == 6) {
        $('#totalpeopletext').text(
            parseInt($('#Room1Adult').val()) + parseInt($('#RoomAdult2').val()) + parseInt($('#RoomAdult3').val()) + parseInt($('#RoomAdult4').val()) + parseInt($('#RoomAdult5').val()) + parseInt($('#RoomAdult6').val()));
        $('#totalchildtext').text(
            parseInt($('#Childs').val()) + parseInt($('#Childs2').val()) + parseInt($('#Childs3').val()) + parseInt($('#Childs4').val()) + parseInt($('#Childs5').val()) + parseInt($('#Childs6').val()));
    }

}

function AddCities() {
    var rowCount = $('#Multicitydivs > #city:visible').length;
    var addlist = '';
    if (rowCount == 2) {
        $(".city3").css('display', 'block');
        $("#tripsid").val("3");
    }
    if (rowCount == 3) {
        $(".city4").css('display', 'block');
        $("#col3").css('display', 'none');
        $(".rowButton").css('display', 'none');
        $("#tripsid").val("4");
    }
}

function deleterow3() {
    $(".city3").css('display', 'none');
    $("#tripsid").val("2");
}

function deleterow4() {
    $("#col3").css('display', 'block');
    $(".city4").css('display', 'none');
    $(".rowButton").css('display', 'block');
    $("#tripsid").val("3");
}
$(document).on('click', '.accordion', function(event) {
    event.stopPropagation();
    var $this = $(this);
    var parent = $this.data('parent');
    var actives = parent && $(parent).find('.collapse.in');
    var href = '#';
    var hasData = '#';
    if (actives && actives.length) {
        hasData = actives.data('collapse');
        actives.collapse('hide');
    }
    make_minus = false;
    if ($this.find('.fa').hasClass('fa-plus')) {
        make_minus = true;
    }
    $('.fa-minus').removeClass('fa-minus').addClass('fa-plus');
    if (make_minus) {
        $this.find('.fa').removeClass('fa-plus').addClass('fa-minus');
    }
    var target = $this.attr('data-target') || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '');
    $(target).collapse('toggle');
})
$(function() {
    var citylist = [];
    loadstations();
});

function loadstations() {
    $.getJSON('hotel_city.json', {
        format: "json"
    }).done(function(data) {
        citylist = [];
        $.each(data, function(i, value) {
            citylist.push(value.city + "," + value.country + "(" + value.c_code + ")");
        });
        $("#hotelcity").autocomplete({
            source: function(request, response) {
                var matcher = new RegExp('^' + $.ui.autocomplete.escapeRegex(request.term), "gi");
                response($.grep(citylist, function(item) {
                    return matcher.test(item);
                }));
            }
        });
    });
}
$.widget("app.autocomplete", $.ui.autocomplete, {
    options: {
        highlightClass: "ui-state-highlight"
    },
    _renderItem: function(ul, item) {
        var re = new RegExp("(" + this.term + ")", "gi"),
            cls = this.options.highlightClass,
            template = "<span class='" + cls + "'>$1</span>",
            label = item.label.replace(re, template),
            $li = $("<li/>").appendTo(ul);
        $("<a/>").attr("href", "#").html(label).appendTo($li);
        return $li;
    }
});

function formatDate(myDate) {
    var abbrMonths = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    var abbrDays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

    function zeroPadding(val) {
        return val.toString().length === 1 ? "0" + val : val;
    }
    return myDate.getDate() + " " + (abbrMonths[myDate.getMonth()]);
}