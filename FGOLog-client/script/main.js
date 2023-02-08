$(document).ready(function() {

    $.ajax({
         type: "GET",
         url: "http://localhost:8080/api/materials/1",
         dataType: 'json',
         success: function (result, status, xhr) {
               $('#sq-number').html(result.number);
         },
         error: function (xhr, status, error) {
              console.log(error);
              return null;
         }
     });
    $.ajax({
         type: "GET",
         url: "http://localhost:8080/api/materials/3",
         dataType: 'json',
         success: function (result, status, xhr) {
               $('#ticket-number').html(result.number);
         },
         error: function (xhr, status, error) {
              console.log(error);
              return null;
         }
     });

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/servants-owned",
        dataType: 'json',
        success: function (result, status, xhr) {
            var content = "";
            var i = 0;
            result.forEach(function(data) {
                content += "<div class = \"servant col-6 col-md-4 col-lg-3 col-xl-2\">";
                content +=      "<img class = \"portrait\" src=\"" + data.thumbnailUrl + "\" title = \'" + data.name + "\'>";
                content +=      "<div class = \"level\">";
                content +=          "<div class = \"level-number\">";
                content +=              "<span class = \"small\">LV</span>" + data.level;
                content +=          "</div>";
                content +=      "</div>";
                content += "</div>";
            });
            $("#servant-list").html(content);
        },
        error: function (xhr, status, error) {
             console.log(error);
        }
    });

});
