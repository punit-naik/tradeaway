function addOptions(data){
    var select = $('#categories');
    for (var i = 0; i < data.length; i++) {
      var option = document.createElement('option');
      option.text = data[i]["Mode"];
      option.value="some";
      select.append(option);
    }

}



$(document).ready(function(){
	$( "#signinForm" ).submit(function( e ) { 
		e.preventDefault();
	});


});

