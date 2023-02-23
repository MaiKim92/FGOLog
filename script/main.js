$(document).ready(function() {
    getServantsOwned();
    $('#servant-list').show();
    $('#party-list').hide();
    $('#wish-list').hide();
    $('#master-profile').hide();
    $('#material-list').hide();
    $.ajax({
         type: "GET",
         url: "http://localhost:8080/api/public/materials/1",
         dataType: 'json',
         success: function (result, status, xhr) {
               $('#sq-number').html(result.number.toLocaleString('en-US'));
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
               $('#ticket-number').html(result.number.toLocaleString('en-US'));
         },
         error: function (xhr, status, error) {
              console.log(error);
              return null;
         }
     });
    $.ajax({
         type: "GET",
         url: "http://localhost:8080/api/public/materials/20",
         dataType: 'json',
         success: function (result, status, xhr) {
               $('#qp-number').html(result.number.toLocaleString('en-US'));
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
        $('#material-list').hide();
        $('#task-list').hide();
        $('#party-list').hide();
        $('#wish-list').hide();
        $('#master-profile').hide();
        $('#menu-item-2').removeClass("selected");
        $('#menu-item-3').removeClass("selected");
        $('#menu-item-4').removeClass("selected");
        $('#menu-item-5').removeClass("selected");
        $('#menu-item-6').removeClass("selected");
        if (!$(this).hasClass("selected")) {
            $(this).addClass("selected");
        }
     });

     $('#menu-item-2').click(function() {
        getParties();
        $('#heading h1').html("PARTIES");
        $('#servant-list').hide();
        $('#material-list').hide();
        $('#task-list').hide();
        $('#party-list').show();
        $('#wish-list').hide();
        $('#master-profile').hide();
        $('#menu-item-1').removeClass("selected");
        $('#menu-item-3').removeClass("selected");
        $('#menu-item-4').removeClass("selected");
        $('#menu-item-5').removeClass("selected");
        $('#menu-item-6').removeClass("selected");
        if (!$(this).hasClass("selected")) {
            $(this).addClass("selected");
        }
     });

     $('#menu-item-3').click(function() {
        getMaterials();
        $('#heading h1').html("MATERIAL");
        $('#servant-list').hide();
        $('#material-list').show();
        $('#task-list').hide();
        $('#party-list').hide();
        $('#wish-list').hide();
        $('#master-profile').hide();
        $('#menu-item-1').removeClass("selected");
        $('#menu-item-2').removeClass("selected");
        $('#menu-item-4').removeClass("selected");
        $('#menu-item-5').removeClass("selected");
        $('#menu-item-6').removeClass("selected");
        if (!$(this).hasClass("selected")) {
            $(this).addClass("selected");
        }
     });

     $('#menu-item-4').click(function() {
        getTasks();
        $('#heading h1').html("ONGOING TASKS");
        $('#servant-list').hide();
        $('#material-list').hide();
        $('#task-list').show();
        $('#party-list').hide();
        $('#wish-list').hide();
        $('#master-profile').hide();
        $('#menu-item-1').removeClass("selected");
        $('#menu-item-2').removeClass("selected");
        $('#menu-item-3').removeClass("selected");
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
        $('#material-list').hide();
        $('#task-list').hide();
        $('#party-list').hide();
        $('#wish-list').show();
        $('#master-profile').hide();
        $('#menu-item-1').removeClass("selected");
        $('#menu-item-2').removeClass("selected");
        $('#menu-item-3').removeClass("selected");
        $('#menu-item-4').removeClass("selected");
        $('#menu-item-6').removeClass("selected");
        if (!$(this).hasClass("selected")) {
            $(this).addClass("selected");
        }
     });

     $('#menu-item-6').click(function() {
        $('#heading h1').html("MASTER PROFILE");
        $('#servant-list').hide();
        $('#material-list').hide();
        $('#task-list').hide();
        $('#party-list').hide();
        $('#wish-list').hide();
        $('#master-profile').show();
        $('#menu-item-1').removeClass("selected");
        $('#menu-item-2').removeClass("selected");
        $('#menu-item-3').removeClass("selected");
        $('#menu-item-4').removeClass("selected");
        $('#menu-item-5').removeClass("selected");
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
                    content += "<div data-toggle=\"modal\" id = \"servant--" + data.servant.id + "\" data-target = \"#servant-dialog\" data-servant = \"" + data.servant.id + "\" class = \"servant col-6 col-md-4 col-lg-3 col-xxl-2\">";
                    content +=      "<img class = \"portrait\" src=\"./" + data.servant.thumbnailUrl + "\" title = \"" + data.servant.name + "\">";
                    content +=      "<div class = \"level\">";
                    content +=          "<div class = \"level-number\">";
                    content +=              "<span class = \"small\">LV.</span><span>" + data.servant.level + "</span>";
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

    function getMaterials() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/materials",
            dataType: 'json',
            success: function (result, status, xhr) {
                var content = "";
                result.forEach(function(data) {
                    if (data.id != 1 && data.id != 20) { // Saint Quartz and QP
                        content += "<div data-toggle=\"modal\" id = \"material--" + data.id + "\" class = \"item col-6 col-md-4 col-lg-3 col-xxl-2\">";
                        content +=      "<img class = \"portrait\" src=\"./" + data.imageUrl + "\" title = \"" + data.name + "\">";
                        content +=      "<div class = \"number\">";
                        content +=          "<div class = \"item-number\">";
                        content +=              "<span>" + data.number.toLocaleString('en-US') + "</span>";
                        content +=          "</div>";
                        content +=      "</div>";
                        content += "</div>";
                    }
                });
                $("#material-list").html(content);
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
                    content +=      "<img class = \"portrait\" src=\"" + data.servant.thumbnailUrl + "\" title = \"" + data.servant.name + "\">";
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
                    content += "<div class = \"main-party-container\">";
                    content += "<div class = \"party col-11 row\">";
                    content += "<h3 class = \"party-name\">" + data.name + "</h3>";
                    content += "<div class = \"d-none d-lg-block col-6 sub-title\">";
                    content +=      "<span class = \"team-subtitle team-front\">Starting </span><span class =\"team-subtitle\">Member</span><div class = \"main-line\"> </div>"
                    content += "</div>";
                    content += "<div class = \"d-none d-lg-block col-6 sub-title\">";
                    content +=      "<span class = \"team-subtitle team-back\">Sub </span><span class =\"team-subtitle\">Member</span><div class = \"sub-line\"> </div>"
                    content += "</div>";
                    content += "</div>";
                    content += "<div class = \"party-container col-11 row\">";
                    var servants = data.servants;
                    servants.forEach(function(ser) {
                        var data = "data-toggle = \"modal\"";
                        // If servant doesn't have an ID, then disable popup modal on click
                        if (ser.servant.id == null) {
                            data = "";
                        }
                        var imageUrl = encodeURI(ser.servant.imageUrl);
                        var ceImageUrl = encodeURI("view/assets/img/craft-essences/" + ser.craftEssence.name + ".png");
                        content +=      "<div class = \"col-6 col-sm-4 col-lg-3 col-xl-2 party-servant row\">";
                        content +=          "<div class = \"party-servant-container\">";
                        content +=              "<div title = \"" + ser.servant.name + "\" style = \"background-image: url(" + imageUrl + ");\" id = \"party-servant--" + ser.servant.id + "\" class = \"col-12 servant-portrait\" data-servant = " + ser.servant.id + " " + data + " data-target = \"#servant-dialog\">";
                        content +=                      "<img src =\"view/assets/img/classes/" + ser.servant.servantClass + ".png\" class = \"class-icon\">"
                        content +=                  "<div class = \"skill-levels\">";
                        content +=                      "<span class = \"skill-level\">";
                        ser.skills.forEach(function(skill) {
                            if (skill.level == null) {
                                content += "-";
                            } else {
                                content +=                  skill.level;
                            }
                            if (skill.seq < 3) {
                                content +=                  "/";
                            }
                        });
                        content +=                      "</span>";
                        content +=                      "<span class = \"np-level small\"> NP</span><span class = \"np-level\"> " + ser.servant.npLevel + "</span>"
                        content +=                  "</div>";
                        content +=                  "<div title = \"" + ser.craftEssence.name + "\"style = \"background-image: url(" + ceImageUrl + ");\" class = \"col-12 craft-essence\">";
                        content +=                  "</div>";
                        content +=              "</div>";
                        content +=          "</div>";
                        content +=      "</div>";
                    });
                    content += "</div>";
                    content += "<div title = \"" + data.mysticCode.name + "\" class = \"master-equip\">";
                    content += "<img src = \"view/assets/img/mystic-codes/" + data.mysticCode.name + ".png\" class = \"master-equipment\">"
                    content += "</div>";
                    content += "</div>";
                });
                $("#party-list").html(content);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    function getTasks() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/tasks",
            dataType: 'json',
            success: function (result, status, xhr) {
                var content = "";
                result.tasks.forEach(function(task) {
                    content += "<div id = \"task-group-" + task.taskGroup.id + "\" class = \"task-group col-12 row\">";
                    content += "<h4 class = \"task-group-name\">" + task.taskGroup.name + "</h4>"
                    var material;
                    task.tasks.forEach(function(data) {
                        material = data.material;
                        var percent = (parseInt(data.progress) / parseInt(data.goal)) * 100 + "%";
                        content +=      "<div id = \"task-" + data.id + "\" class = \" col-12 col-lg-6 row task-item\">";
                        content +=          "<div class = \"row task-item-container\">";
                        content +=          "<div class = \"col-3\">";
                        content +=              "<img src = \"" + material.imageUrl + "\" class = \"item\" title = \"" + material.name +"\">";
                        content +=          "</div>";
                        content +=          "<div class = \"col-9 row\">";
                        content +=              "<div class = \"task-name col-12\">";
                        content +=                  "<h6>Collect " + data.goal.toLocaleString('en-US') + " " + material.name +"</h6>";
                        content +=              "</div>";
                        content +=              "<div class = \"task-progress col-12 row\">";
                        content +=                  "<div class = \"progress col-8\">";
                        content +=                      "<div class = \"progress-bar\" role = \"progressbar\" style = \"width: " + percent + "\"></div>";
                        content +=                  "</div>";
                        content +=                  "<div class = \"task-name progress-number col-4\">";
                        content +=                      "<div>" + data.progress.toLocaleString('en-US') + "/" + data.goal.toLocaleString('en-US') + "</div>";
                        content +=                  "</div>";
                        content +=              "</div>";
                        if (data.status.toLowerCase() == 'completed') {
                            content +=              "<div>";
                            content +=                  "<div class = \"task-name task-completed progress-number col-9\">";
                            content +=                      "<h5>" + data.status.replaceAll("_", " ") + "</h5>";
                            content +=                  "</div>";
                            content +=              "</div>";
                        }
                        content +=          "</div>";
                        content +=          "</div>";
                        content +=      "</div>";
                    });
                    content += "</div>";
                });
                $("#task-list").html(content);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    $('#servant-list').on('click', '[id^=servant--]', function() {
        var elements = [$('#skill-1'), $('#skill-2'), $('#skill-3')];
        elements.forEach(
            function(element){element.html("")
        });
        var id = $(this).data('servant');
        $("#servant-name").html("");
        $('#servant-img').html("");
        $('.section-name').html("");
        $('#card-1').html("");
        $('#card-2').html("");
        $('#card-3').html("");
        $('#card-4').html("");
        $('#card-5').html("");
        $("#servant-level").html("");
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servants/" + id,
            dataType: 'json',
            success: function (result, status, xhr) {
                var level = "";
                if (result.servant.npLevel > 0) {
                    var elements = [$('#skill-1'), $('#skill-2'), $('#skill-3')];
                    let i = 0;
                    elements.forEach(function(element) {
                        var content = "";
                        content += "<img class = \"skill-img\" src = \"" + result.skills[i].imageUrl +"\"></img>";
                        content += "<div class = \"skill-name row\">" + result.skills[i].name + "</div>";
                        content += "<div class = \"skill-level\"><span class = \"stat-name\">Level: </span><span class = \"stat\">" + result.skills[i].level + "</span></div>";
                        elements[i].html(content);
                        i++;
                    });
                    level += "<h4><span class =\"stat-name\">Level:</span><span class = \"stat\"> " + result.servant.level + "</span></h4>";
                    $("#servant-level").html(level);
                    $("#section-name-skill").html("Skills");
                    $("#section-name-card").html("Command Cards");
                }
                $("#servant-name").html(result.servant.name);
                $("#servant-img").html("<img class = \"servant-img np-" + result.servant.npLevel + "\" src = \"" + result.servant.imageUrl + "\">");
                $("#card-1").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[0].type + ".png\">");
                $("#card-2").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[1].type + ".png\">");
                $("#card-3").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[2].type + ".png\">");
                $("#card-4").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[3].type + ".png\">");
                $("#card-5").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[4].type + ".png\">");
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

    $('#wish-list').on('click', '[id^=wishlist--]', function() {
        var elements = [$('#skill-1'), $('#skill-2'), $('#skill-3')];
        elements.forEach(
            function(element){element.html("")
        });
        var id = $(this).data('servant');
        $("#servant-name").html("");
        $('#servant-img').html("");
        $('.section-name').html("");
        $('#card-1').html("");
        $('#card-2').html("");
        $('#card-3').html("");
        $('#card-4').html("");
        $('#card-5').html("");
        $("#servant-level").html("");
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servants/" + id,
            dataType: 'json',
            success: function (result, status, xhr) {
                var level = "";
                if (result.servant.npLevel > 0) {
                    var elements = [$('#skill-1'), $('#skill-2'), $('#skill-3')];
                    let i = 0;
                    elements.forEach(function(element) {
                        var content = "";
                        content += "<img class = \"skill-img\" src = \"" + result.skills[i].imageUrl +"\"></img>";
                        content += "<div class = \"skill-name row\">" + result.skills[i].name + "</div>";
                        content += "<div class = \"skill-level\"><span class = \"stat-name\">Level: </span><span class = \"stat\">" + result.skills[i].level + "</span></div>";
                        elements[i].html(content);
                        i++;
                    });
                    level += "<h4><span class =\"stat-name\">Level:</span><span class = \"stat\"> " + result.servant.level + "</span></h4>";
                    $("#section-name-skill").html("Skills");
                    $("#section-name-card").html("Command Cards");
                    $("#servant-level").html(level);
                }
                $("#servant-name").html(result.servant.name);
                $("#servant-img").html("<img class = \"servant-img np-" + result.servant.npLevel + "\" src = \"" + result.servant.imageUrl + "\">");
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

    $('#party-list').on('click', '[id^=party-servant--]', function() {
        var elements = [$('#skill-1'), $('#skill-2'), $('#skill-3')];
        elements.forEach(
            function(element){element.html("")
        });
        var id = $(this).data('servant');
        if (id == null) {
            return;
        }
        $("#servant-name").html("");
        $('#servant-img').html("");
        $('.section-name').html("");
        $('#card-1').html("");
        $('#card-2').html("");
        $('#card-3').html("");
        $('#card-4').html("");
        $('#card-5').html("");
        $("#servant-level").html("");
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servants/" + id,
            dataType: 'json',
            success: function (result, status, xhr) {
                var level = "";
                if (result.servant.npLevel > 0) {
                    $("#servant-level").html(level);
                    $("#section-name-skill").html("Skills");
                    $("#section-name-card").html("Command Cards");
                    $("#card-1").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[0].type + ".png\">");
                    $("#card-2").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[1].type + ".png\">");
                    $("#card-3").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[2].type + ".png\">");
                    $("#card-4").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[3].type + ".png\">");
                    $("#card-5").html("<img class = \"card-img\" src = \"view/assets/img/" + result.commandCards[4].type + ".png\">");
                    level += "<h4><span class =\"stat-name\">Level:</span><span class = \"stat\"> " + result.servant.level + "</span></h4>";
                    var elements = [$('#skill-1'), $('#skill-2'), $('#skill-3')];
                    let i = 0;
                    elements.forEach(function(element) {
                        var content = "";
                        content += "<img class = \"skill-img\" src = \"" + result.skills[i].imageUrl +"\"></img>";
                        content += "<div class = \"skill-name row\">" + result.skills[i].name + "</div>";
                        content += "<div class = \"skill-level\"><span class = \"stat-name\">Level: </span><span class = \"stat\">" + result.skills[i].level + "</span></div>";
                        elements[i].html(content);
                        i++;
                    });
                }
                $("#servant-name").html(result.servant.name);
                $("#servant-img").html("<img class = \"servant-img np-" + result.servant.npLevel + "\" src = \"" + result.servant.imageUrl + "\">");
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

});
