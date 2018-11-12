$(document).ready(function(){
  $("#myInput").on("keyup", function(e) {
    gofilter(e);
  });
  $("#makebrand").on("change", function(e) {
    gofilter(e);
  });
  $("#makeplate").on("change", function(e) {
    gofilter(e);
  });
  $("#maketype").on("change", function(e) {
    gofilter(e);
  });
  $("#resetserch").click(function (e) { 
    e.preventDefault();
    document.getElementById("serchform").reset();
    $(".searchItem ").filter(function() {	
      $(this).toggle($(this).text().toLowerCase().indexOf("") > -1)
    }); 
  });

 var gofilter =  function(e){
  e.preventDefault();
  var myInput =  $("#myInput").val().toLowerCase().trim();
  var makebrand =  $("#makebrand").val().toLowerCase();
  var makeplate =  $("#makeplate").val().toLowerCase();
  var maketype =  $("#maketype").val().toLowerCase();
    $(".searchItem ").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf("") > -1)
     if($(this).css("display") != "none" && myInput != ""){
      $(this).toggle( $(this).text().toLowerCase().indexOf(myInput) > -1)
     }
     if($(this).css("display") != "none" && makebrand != ""){
      $(this).toggle( $(this).text().toLowerCase().indexOf(makebrand) > -1)
     }
     if($(this).css("display") != "none" && makeplate != ""){
      $(this).toggle( $(this).text().toLowerCase().indexOf(makeplate) > -1)
     }
     if($(this).css("display") != "none" && maketype != ""){
      $(this).toggle( $(this).text().toLowerCase().indexOf(maketype) > -1)
     }

    });


}



});