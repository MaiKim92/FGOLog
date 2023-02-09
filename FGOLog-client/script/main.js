$(document).ready(function() {
    getServantsOwned();
    $('#servant-list').show();
    $('#party-list').hide();
    $('#wish-list').hide();
    $.ajax({
         type: "GET",
         url: "http://localhost:8080/api/public/materials/1",
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
         url: "http://localhost:8080/api/public/materials/2",
         dataType: 'json',
         success: function (result, status, xhr) {
               $('#ticket-number').html(result.number);
         },
         error: function (xhr, status, error) {
              console.log(error);
              return null;
         }
     });

     $('#menu-item-1').click(function() {
        getServantsOwned();
        $('#heading h1').html("SERVANTS OWNED");
        $('#servant-list').show();
        $('#party-list').hide();
        $('#wish-list').hide();
        $('#menu-item-2').removeClass("selected");
        $('#menu-item-3').removeClass("selected");
        $('#menu-item-4').removeClass("selected");
        $('#menu-item-5').removeClass("selected");
        $('#menu-item-6').removeClass("selected");
        if (!$(this).hasClass("selected")) {
            $(this).addClass("selected");
        }
     });

     $('#menu-item-5').click(function() {
        getWishList();
        $('#heading h1').html("WISH LIST");
        $('#servant-list').hide();
        $('#party-list').hide();
        $('#wish-list').show();
        $('#menu-item-1').removeClass("selected");
        $('#menu-item-2').removeClass("selected");
        $('#menu-item-3').removeClass("selected");
        $('#menu-item-4').removeClass("selected");
        $('#menu-item-6').removeClass("selected");
        if (!$(this).hasClass("selected")) {
            $(this).addClass("selected");
        }
     });

     $('#menu-item-2').click(function() {
        getParties();
        $('#heading h1').html("PARTIES");
        $('#servant-list').hide();
        $('#party-list').show();
        $('#wish-list').hide();
        $('#menu-item-1').removeClass("selected");
        $('#menu-item-2').removeClass("selected");
        $('#menu-item-3').removeClass("selected");
        $('#menu-item-4').removeClass("selected");
        $('#menu-item-6').removeClass("selected");
        if (!$(this).hasClass("selected")) {
            $(this).addClass("selected");
        }
     });

    function getServantsOwned() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servants-owned",
            dataType: 'json',
            success: function (result, status, xhr) {
                var content = "";
                result.forEach(function(data) {
                    content += "<div data-toggle=\"modal\" id = \"servant--" + data.id + "\" data-target = \"#servant-dialog\" data-servant = \"" + data.id + "\" class = \"servant col-6 col-md-4 col-lg-3 col-xxl-2\">";
                    content +=      "<img class = \"portrait\" src=\"" + data.thumbnailUrl + "\" title = \'" + data.name + "\'>";
                    content +=      "<div class = \"level\">";
                    content +=          "<div class = \"level-number\">";
                    content +=              "<span class = \"small\">LV.</span><span>" + data.level + "</span>";
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
    }

    function getWishList() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/wish-items",
            dataType: 'json',
            success: function (result, status, xhr) {
                var content = "";
                var servant;
                result.forEach(function(data) {
                    content += "<div data-toggle=\"modal\" id = \"wishlist--" + data.servant.id + "\" data-target = \"#servant-dialog\" data-servant = \"" + data.servant.id + "\" class = \"servant col-6 col-md-4 col-lg-3 col-xxl-2\">";
                    content +=      "<img class = \"portrait\" src=\"" + data.servant.thumbnailUrl + "\" title = \'" + data.servant.name + "\'>";
                    content += "</div>";
                });
                $("#wish-list").html(content);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    function getParties() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/parties",
            dataType: 'json',
            success: function (result, status, xhr) {
                var content = "";
                var servant;
                result.forEach(function(data) {
                    content += "<div class = \"row\">";
                    content += "<h1 class = \"party-name\">" + data.name + "</h1>";
                    content += "</div>";
                    content += "<div class = \"row\">";
                    var servants = data.servants;
                    servants.forEach(function(servant) {
                        content +=      "<div class = \"col-2 servant\">";
                        content +=          "<div id = \"party-servant--" + servant.id + "\" data-servant = " + servant.id + " data-toggle = \"modal\" data-target = \"#servant-dialog\">";
                        content +=              "<img class = \"portrait\" src=\"" + servant.thumbnailUrl + "\" title = \'" + servant.name + "\'>";
                        content +=          "</div>";
                        content +=      "</div>";
                    });
                    content += "</div>";
                });
                $("#party-list").html(content);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    $('#servant-list').on('click', '[id^=servant--]', function() {
        var id = $(this).data('servant');
        $('#skill-title').show();
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servants/" + id,
            dataType: 'json',
            success: function (result, status, xhr) {
                var level = "";
                if (result.isHas == true) {
                    level += "<h5><span class =\"stat-name\">Level:</span><span class = \"stat\"> " + result.level + "</span></h5>";
                }
                $("#servant-name").html(result.name);
                $("#servant-img").html("<img class = \"servant-img has-" + result.isHas + "\" src = \"" + result.imageUrl + "\">");
                $("#servant-stats").html(level);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servant-skills/" + id,
            dataType: 'json',
            success: function (result, status, xhr) {
                $('[id^=skill-]').html("");
                var elements = [$('#skill-1'), $('#skill-2'), $('#skill-3')];
                var i = 0;
                console.log(result);
                result.forEach(function(data) {
                    var content = "";
                    content += "<img class = \"skill-img\" src = \"" + data.imageUrl +"\"></img>";
                    content += "<div class = \"skill-name row\">" + data.name + "</div>";
                    content += "<div class = \"skill-level\"><span class = \"stat-name\">Level: </span><span class = \"stat\">" + data.level + "</span></div>";
                    elements[i].html(content);
                    i++;
                });
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

    $('#wish-list').on('click', '[id^=wishlist--]', function() {
        var id = $(this).data('servant');
        $('#skill-title').hide();
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servants/" + id,
            dataType: 'json',
            success: function (result, status, xhr) {
                var level = "";
                if (result.isHas == true) {
                    level += "<h5><span class =\"stat-name\">Level:</span><span class = \"stat\"> " + result.level + "</span></h5>";
                }
                $("#servant-name").html(result.name);
                $("#servant-img").html("<img class = \"servant-img has-" + result.isHas + "\" src = \"" + result.imageUrl + "\">");
                $("#servant-stats").html(level);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servant-skills/" + id,
            dataType: 'json',
            success: function (result, status, xhr) {
                $('[id^=skill-]').html("");
                var elements = [$('#skill-1'), $('#skill-2'), $('#skill-3')];
                var i = 0;
                result.forEach(function(data) {
                    var content = "";
                    content += "<img class = \"skill-img\" src = \"" + data.imageUrl +"\"></img>";
                    content += "<div class = \"skill-name row\">" + data.name + "</div>";
                    content += "<div class = \"skill-level\"><span class = \"stat-name\">Level: </span><span class = \"stat\">" + data.level + "</span></div>";
                    elements[i].html(content);
                    i++;
                });
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

    $('#party-list').on('click', '[id^=party-servant--]', function() {
        var id = $(this).data('servant');
        $('#skill-title').hide();
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servants/" + id,
            dataType: 'json',
            success: function (result, status, xhr) {
                var level = "";
                if (result.isHas == true) {
                    level += "<h5><span class =\"stat-name\">Level:</span><span class = \"stat\"> " + result.level + "</span></h5>";
                }
                $("#servant-name").html(result.name);
                $("#servant-img").html("<img class = \"servant-img has-" + result.isHas + "\" src = \"" + result.imageUrl + "\">");
                $("#servant-stats").html(level);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servant-skills/" + id,
            dataType: 'json',
            success: function (result, status, xhr) {
                $('[id^=skill-]').html("");
                var elements = [$('#skill-1'), $('#skill-2'), $('#skill-3')];
                var i = 0;
                result.forEach(function(data) {
                    var content = "";
                    content += "<img class = \"skill-img\" src = \"" + data.imageUrl +"\"></img>";
                    content += "<div class = \"skill-name row\">" + data.name + "</div>";
                    content += "<div class = \"skill-level\"><span class = \"stat-name\">Level: </span><span class = \"stat\">" + data.level + "</span></div>";
                    elements[i].html(content);
                    i++;
                });
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

});
