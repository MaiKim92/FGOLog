$(document).ready(function() {
    $('#material-list').hide();
    $('#command-code-list').hide();
    $('#task-list').hide();
    $('#wish-list').hide();
    getServants();

    $('#menu-item-1').click(function() {
        getServants();
        $('#servant-list').show();
        $('#material-list').hide();
        $('#command-code-list').hide();
        $('#task-list').hide();
        $('#wish-list').hide();
    });

    $('#menu-item-2').click(function() {
        getMaterials();
        $('#servant-list').hide();
        $('#material-list').show();
        $('#command-code-list').hide();
        $('#task-list').hide();
        $('#wish-list').hide();
    });

    $('#menu-item-3').click(function() {
        getCommandCodes();
        $('#servant-list').hide();
        $('#material-list').hide();
        $('#command-code-list').show();
        $('#task-list').hide();
        $('#wish-list').hide();
    });

    $('#menu-item-4').click(function() {
        getTasks();
        $('#servant-list').hide();
        $('#material-list').hide();
        $('#command-code-list').hide();
        $('#task-list').show();
        $('#wish-list').hide();
    });

    $('#menu-item-5').click(function() {
        getWishlist();
        $('#servant-list').hide();
        $('#material-list').hide();
        $('#command-code-list').hide();
        $('#task-list').hide();
        $('#wish-list').show();
    });

    function getServants() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servants",
            dataType: 'json',
            success: function (result, status, xhr) {
                content = "";
                content += "<tr>"
                content +=     "<th>ID</th>";
                content +=     "<th>Image</th>";
                content +=     "<th>Servant name</th>";
                content +=     "<th>Skills</th>";
                content +=    " <th>Command Cards</th>";
                content +=     "<th>Action</th>";
                content += "</tr>";
                result.forEach(function(data) {
                    content += "<tr id = \"servant-" + data.servant.id + "\">";
                    content +=    "<td>" + data.servant.id + "</td>";
                    content +=    "<td><img src = \"../" + data.servant.thumbnailUrl + "\" class = \"servant-portrait\"></td>";
                    content +=    "<td>" + data.servant.name + "</td>";
                    content +=    "<td id = \"skill-list\">";
                    content +=      "<img title = \"" + data.skills[0].name + "\" id = \"skill-1\" class = \"skill-icon\" src = \"../" + data.skills[0].imageUrl +"\"></img>";
                    content +=      "<img title = \"" + data.skills[1].name + "\" id = \"skill-2\" class = \"skill-icon\" src = \"../" + data.skills[1].imageUrl +"\"></img>";
                    content +=      "<img title = \"" + data.skills[2].name + "\" id = \"skill-3\" class = \"skill-icon\" src = \"../" + data.skills[2].imageUrl +"\"></img></td>";
                    content +=    "</td>";
                    content +=    "<td id = \"card-list\">"
                    content +=        "<img id = \"card-1\" src = \"../view/assets/img/" + data.commandCards[0].type + ".png\" class = \"card-icon\">";
                    content +=        "<img id = \"card-2\" src = \"../view/assets/img/" + data.commandCards[1].type + ".png\" class = \"card-icon\">";
                    content +=        "<img id = \"card-3\" src = \"../view/assets/img/" + data.commandCards[2].type + ".png\" class = \"card-icon\">";
                    content +=        "<img id = \"card-4\" src = \"../view/assets/img/" + data.commandCards[3].type + ".png\" class = \"card-icon\">";
                    content +=        "<img id = \"card-5\" src = \"../view/assets/img/" + data.commandCards[4].type + ".png\" class = \"card-icon\">";
                    content +=    "</td>";
                    content +=    "<td id = \"actions\">";
                    content +=        "<a href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                    content +=    "</td>";
                    content += "</tr>";
                });
                $("#servant-table").html(content);
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
                content = "";
                content += "<tr>"
                content +=     "<th>ID</th>";
                content +=    " <th>Image</th>";
                content +=     "<th>Material name</th>";
                content +=     "<th>Number held</th>";
                content +=     "<th>Action</th>";
                content += "</tr>";
                result.forEach(function(data) {
                    content += "<tr id = \"material-" + data.id + "\">";
                    content +=    "<td>" + data.id + "</td>";
                    content +=    "<td><img src = \"../" + data.imageUrl + "\" class = \"servant-portrait\"></td>";
                    content +=    "<td>" + data.name + "</td>";
                    content +=    "<td id = \"number\">";
                    content +=      "<a href = \"#/\" title = \"Decrement\"><i class=\"fa-solid fa-caret-down\"></i></a>";
                    content +=      data.number;
                    content +=      "<a href = \"#/\" title = \"Increment\"><i class=\"fa-solid fa-caret-up\"></i></a>";
                    content +=    "</td>";
                    content +=    "<td id = \"actions\">";
                    content +=        "<a href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                    content +=    "</td>";
                    content += "</tr>";
                });
                $("#material-table").html(content);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    function getCommandCodes() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/command-codes",
            dataType: 'json',
            success: function (result, status, xhr) {
                content = "";
                content += "<tr>"
                content +=     "<th>ID</th>";
                content +=    " <th>Image</th>";
                content +=     "<th>Command Code name</th>";
                content +=     "<th>Action</th>";
                content += "</tr>";
                result.forEach(function(data) {
                    content += "<tr id = \"command-code-" + data.id + "\">";
                    content +=    "<td>" + data.id + "</td>";
                    content +=    "<td><img src = \"../view/assets/img/command-codes/" + data.name + ".png\" class = \"servant-portrait\"></td>";
                    content +=    "<td>" + data.name + "</td>";
                    content +=    "<td id = \"actions\">";
                    content +=        "<a href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                    content +=    "</td>";
                    content += "</tr>";
                });
                $("#command-code-table").html(content);
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
                content = "";
                content += "<tr>"
                content +=     "<th>ID</th>";
                content +=    " <th>Image</th>";
                content +=     "<th>Material</th>";
                content +=     "<th>Progress</th>";
                content +=     "<th>Status</th>";
                content +=     "<th>Action</th>";
                content += "</tr>";
                result.forEach(function(data) {
                    content += "<tr id = \"task-" + data.id + "\">";
                    content +=    "<td>" + data.id + "</td>";
                    content +=    "<td><img src = \"../" + data.material.imageUrl + "\" class = \"servant-portrait\"></td>";
                    content +=    "<td>" + data.material.name + "</td>";
                    content +=    "<td>" + data.progress.toLocaleString('en-US') + "/" + data.goal.toLocaleString('en-US') + "</td>";
                    content +=    "<td><span class = \"status-" + data.status.toLowerCase() + "\">" + data.status.replaceAll("_", " ") + "</span></td>";
                    content +=    "<td id = \"actions\">";
                    content +=        "<a href = \"#/\" title = \"Increment\"><i class=\"fa-solid fa-caret-up\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Mark as completed\"><i class=\"fa-solid fa-check\"></i></a>";
                    content +=        "<a href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                    content +=    "</td>";
                    content += "</tr>";
                });
                $("#task-table").html(content);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    function getWishlist() {
         $.ajax({
             type: "GET",
             url: "http://localhost:8080/api/public/wish-items",
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 content += "<tr>"
                 content +=     "<th>ID</th>";
                 content +=     "<th>Image</th>";
                 content +=     "<th>Servant name</th>";
                 content +=     "<th>Status</th>"
                 content +=     "<th>Action</th>";
                 content += "</tr>";
                 result.forEach(function(data) {
                     var status = "NOT_OBTAINED";
                     if (data.servant.isHas == true) {
                        status = "OBTAINED";
                     }
                     content += "<tr id = \"wish-item-" + data.id + "\">";
                     content +=    "<td>" + data.id + "</td>";
                     content +=    "<td><img src = \"../" + data.servant.thumbnailUrl + "\" class = \"servant-portrait\"></td>";
                     content +=    "<td>" + data.servant.name + "</td>";
                     content +=    "<td><span class = \"status-" + status.toLowerCase() + "\">" + status.replaceAll("_", " ") + "</span></td>";
                     content +=    "<td id = \"actions\">";
                     content +=        "<a href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                     content +=        "<a href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                     content +=        "<a href = \"#/\" title = \"Mark as obtained\"><i class=\"fa-solid fa-check\"></i></i></a>";
                     content +=        "<a href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                     content +=    "</td>";
                     content += "</tr>";
                 });
                 $("#wish-table").html(content);
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
         });
     }

});