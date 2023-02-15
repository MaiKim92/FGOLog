$(document).ready(function() {
    pageLoad();
    $('#party-list').hide();
    $('#material-list').hide();
    $('#command-code-list').hide();
    $('#task-list').hide();
    $('#wish-list').hide();
    $('#skill-list').hide();
    $('#_remove-skill-button').prop('disabled', true);
    getServants();

    $('#menu-item-1').click(function() {
        getServants();
        $('#servant-list').show();
        $('#party-list').hide();
        $('#material-list').hide();
        $('#command-code-list').hide();
        $('#task-list').hide();
        $('#wish-list').hide();
        $('#skill-list').hide();
    });

    $('#menu-item-2').click(function() {
        getParties();
        $('#servant-list').hide();
        $('#party-list').show();
        $('#material-list').hide();
        $('#command-code-list').hide();
        $('#task-list').hide();
        $('#wish-list').hide();
        $('#skill-list').hide();
    });

    $('#menu-item-3').click(function() {
        getMaterials();
        $('#servant-list').hide();
        $('#party-list').hide();
        $('#material-list').show();
        $('#command-code-list').hide();
        $('#task-list').hide();
        $('#wish-list').hide();
        $('#skill-list').hide();
    });

    $('#menu-item-4').click(function() {
        getCommandCodes();
        $('#servant-list').hide();
        $('#party-list').hide();
        $('#material-list').hide();
        $('#command-code-list').show();
        $('#task-list').hide();
        $('#wish-list').hide();
        $('#skill-list').hide();
    });

    $('#menu-item-5').click(function() {
        getTasks();
        $('#servant-list').hide();
        $('#party-list').hide();
        $('#material-list').hide();
        $('#command-code-list').hide();
        $('#task-list').show();
        $('#wish-list').hide();
        $('#skill-list').hide();
    });

    $('#menu-item-6').click(function() {
        getWishlist();
        $('#servant-list').hide();
        $('#party-list').hide();
        $('#material-list').hide();
        $('#command-code-list').hide();
        $('#task-list').hide();
        $('#wish-list').show();
        $('#skill-list').hide();
    });

    $('#menu-item-7').click(function() {
        getSkills();
        $('#servant-list').hide();
        $('#party-list').hide();
        $('#material-list').hide();
        $('#command-code-list').hide();
        $('#task-list').hide();
        $('#wish-list').hide();
        $('#skill-list').show();
    });

    function getServants() {
        $("#servant-list .pagination-container").remove();
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
                content +=     "<th>Level</th>";
                content +=     "<th>Skills</th>";
                content +=    " <th>Command Cards</th>";
                content +=     "<th>Action</th>";
                content += "</tr>";
                result.forEach(function(data) {
                    content += "<tr id = \"servant-" + data.servant.id + "\" >";
                    content +=    "<td>" + data.servant.id + "</td>";
                    content +=    "<td><img src = \"../" + data.servant.thumbnailUrl + "\" class = \"servant-portrait\"></td>";
                    content +=    "<td>" + data.servant.name + "</td>";
                    content +=    "<td>" + data.servant.level + "</td>";
                    content +=    "<td id = \"servant-skill-list\">";
                    if (data.skills[0] != null) {
                        content +=      "<img title = \"" + data.skills[0].name + "\" id = \"skill-1\" class = \"skill-icon\" src = \"../" + data.skills[0].imageUrl +"\"></img>";
                    }
                    if (data.skills[1] != null) {
                        content +=      "<img title = \"" + data.skills[1].name + "\" id = \"skill-2\" class = \"skill-icon\" src = \"../" + data.skills[1].imageUrl +"\"></img>";
                    }
                    if (data.skills[2] != null) {
                        content +=      "<img title = \"" + data.skills[2].name + "\" id = \"skill-3\" class = \"skill-icon\" src = \"../" + data.skills[2].imageUrl +"\"></img></td>";
                    }
                    content +=    "</td>";
                    content +=    "<td id = \"card-list\">"
                    content +=        "<img id = \"card-1\" src = \"../view/assets/img/" + data.commandCards[0].type + ".png\" class = \"card-icon\">";
                    content +=        "<img id = \"card-2\" src = \"../view/assets/img/" + data.commandCards[1].type + ".png\" class = \"card-icon\">";
                    content +=        "<img id = \"card-3\" src = \"../view/assets/img/" + data.commandCards[2].type + ".png\" class = \"card-icon\">";
                    content +=        "<img id = \"card-4\" src = \"../view/assets/img/" + data.commandCards[3].type + ".png\" class = \"card-icon\">";
                    content +=        "<img id = \"card-5\" src = \"../view/assets/img/" + data.commandCards[4].type + ".png\" class = \"card-icon\">";
                    content +=    "</td>";
                    content +=    "<td id = \"servant-actions-" + data.id + "\">";
                    content +=        "<a data-servant = \"" + data.servant.id + "\" id = \"servant-add-" + data.servant.id + "\" data-toggle=\"modal\" data-target = \"#add-servant-dialog\" data-servant = \"" + data.servant.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a data-servant = \"" + data.servant.id + "\" id = \"servant-edit-" + data.servant.id + "\" data-toggle=\"modal\" data-target = \"#edit-servant-dialog\" data-servant = \"" + data.servant.id + "\"  href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a data-servant = \"" + data.servant.id + "\" id = \"servant-delete-" + data.servant.id + "\" data-toggle=\"modal\" data-target = \"#delete-servant-dialog\" data-servant = \"" + data.servant.id + "\"  href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                    content +=    "</td>";
                    content += "</tr>";
                });
                $("#servant-table").html(content);
                $('#servant-table').paginathing();
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    function getParties() {
        $("#party-list .pagination-container").remove();
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/parties",
            dataType: 'json',
            success: function (result, status, xhr) {
                content = "";
                content += "<tr>"
                content +=     "<th>ID</th>";
                content +=    " <th>Party name</th>";
                content +=     "<th>Servants</th>";
                content +=     "<th>Action</th>";
                content += "</tr>";
                result.forEach(function(data) {
                    content += "<tr id = \"party-" + data.id + "\">";
                    content +=    "<td>" + data.id + "</td>";
                    content +=    "<td>" + data.name + "</td>";
                    content +=    "<td>";
                    var servants = data.servants;
                    servants.forEach(function(ser){
                        content += "<img src = \"../" + ser.servant.thumbnailUrl + "\" class = \"servant-portrait\">";
                    });
                    content +=    "</td>";
                    content +=    "<td id = \"party-actions-" + data.id + "\">";
                    content +=        "<a data-party = \"" + data.id + "\" id = \"party-edit-" + data.id + "\" data-toggle=\"modal\" data-target = \"#add-party-dialog\" data-party = \"" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a data-party = \"" + data.id + "\" id = \"party-edit-" + data.id + "\" data-toggle=\"modal\" data-target = \"#edit-party-dialog\" data-party = \"" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a data-party = \"" + data.id + "\" id = \"party-delete-" + data.id + "\" data-toggle=\"modal\" data-target = \"#delete-party-dialog\" data-party = \"" + data.id + "\" href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                    content +=    "</td>";
                    content += "</tr>";
                });
                $("#party-table").html(content);
                $('#party-table').paginathing();
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    function getMaterials() {
        $("material-list .pagination-container").remove();
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
                    content +=      "<a id = \"material-decrement\" href = \"#/\" title = \"Decrement\"><i class=\"fa-solid fa-caret-down\"></i></a>";
                    content +=      data.number;
                    content +=      "<a id = \"material-increment\" href = \"#/\" title = \"Increment\"><i class=\"fa-solid fa-caret-up\"></i></a>";
                    content +=    "</td>";
                    content +=    "<td id = \"material-actions-" + data.id + "\">";
                    content +=        "<a data-material = \"" + data.id + "\" id = \"material-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a data-material = \"" + data.id + "\" id = \"material-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a data-material = \"" + data.id + "\" id = \"material-delete-" + data.id + "\" href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                    content +=    "</td>";
                    content += "</tr>";
                });
                $("#material-table").html(content);
                $('#material-table').paginathing();
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    function getCommandCodes() {
         $("#command-code-list .pagination-container").remove();
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
                    content +=    "<td id = \"cc-actions-" + data.id + "\">";
                    content +=        "<a data-cc = \"" + data.id + "\" id = \"command-code-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a data-cc = \"" + data.id + "\" id = \"command-code-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a data-cc = \"" + data.id + "\" id = \"command-code-delete-" + data.id + "\" href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                    content +=    "</td>";
                    content += "</tr>";
                });
                $("#command-code-table").html(content);
                $('#command-code-table').paginathing();
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    function getTasks() {
         $("#task-list .pagination-container").remove();
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
                    content +=    "<td id = \"task-actions-" + data.id + "\">";
                    content +=        "<a data-task = \"" + data.id + "\" id = \"task-increment-" + data.id + "\" href = \"#/\" title = \"Increment\"><i class=\"fa-solid fa-caret-up\"></i></a>";
                    content +=        "<a data-task = \"" + data.id + "\" id = \"task-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a data-task = \"" + data.id + "\" id = \"task-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a data-task = \"" + data.id + "\" id = \"task-mark-as-completed-" + data.id + "\" href = \"#/\" title = \"Mark as completed\"><i class=\"fa-solid fa-check\"></i></a>";
                    content +=        "<a data-task = \"" + data.id + "\" id = \"task-delete-" + data.id + "\" href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                    content +=    "</td>";
                    content += "</tr>";
                });
                $("#task-table").html(content);
                $('#task-table').paginathing();
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    }

    function getWishlist() {
         $("#wish-list .pagination-container").remove();
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
                     content +=    "<td id = \"wish-actions-" + data.id + "\">";
                     content +=        "<a data-wish = \"" + data.id + "\" id = \"wish-item-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                     content +=        "<a data-wish = \"" + data.id + "\" id = \"wish-item-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                     content +=        "<a data-wish = \"" + data.id + "\" id = \"wish-item-mark-as-obtained-" + data.id + "\" href = \"#/\" title = \"Mark as obtained\"><i class=\"fa-solid fa-check\"></i></i></a>";
                     content +=        "<a data-wish = \"" + data.id + "\" id = \"wish-item-delete-" + data.id + "\" href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                     content +=    "</td>";
                     content += "</tr>";
                 });
                 $("#wish-table").html(content);
                $('#wish-table').paginathing();
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
         });
     }

    function getSkills() {
         $("#skill-list .pagination-container").remove();
         $.ajax({
             type: "GET",
             url: "http://localhost:8080/api/public/skills",
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 content += "<tr>"
                 content +=     "<th>ID</th>";
                 content +=     "<th>Image</th>";
                 content +=     "<th>Skill name</th>";
                 content +=     "<th>Level</th>";
                 content += "</tr>";
                 result.forEach(function(data) {
                     content += "<tr id = \"skill-" + data.id + "\">";
                     content +=    "<td>" + data.id + "</td>";
                     content +=    "<td><img src = \"../" + data.imageUrl + "\" class = \"servant-portrait\"></td>";
                     content +=    "<td>" + data.name + "</td>";
                     content +=      "<a data-skill = \"" + data.id + "\" id = \"skill-level-decrement-" + data.id + "\" href = \"#/\" title = \"Decrement\"><i class=\"fa-solid fa-caret-down\"></i></a>";
                     content +=      data.level;
                     content +=      "<a data-skill = \"" + data.id + "\" id = \"skill-level-increment-" + data.id + "\" href = \"#/\" title = \"Increment\"><i class=\"fa-solid fa-caret-up\"></i></a>";
                     content +=    "<td id = \"skill-actions-" + data.id + "\">";
                     content +=        "<a data-skill = \"" + data.id + "\" id = \"skill-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                     content +=        "<a data-skill = \"" + data.id + "\" id = \"skill-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                     content +=        "<a data-skill = \"" + data.id + "\" id = \"skill-delete-" + data.id + "\" href = \"#/\" title = \"Delete\"><i class=\"fa-sharp fa-solid fa-trash\"></i></i></a>";
                     content +=    "</td>";
                     content += "</tr>";
                 });
                 $("#skill-table").html(content);
                 $("#skill-table").paginathing({
                    perPage: 100
                 });
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
         });
     }

     // Handle logout event

    function pageLoad() {
        token = sessionStorage.getItem("token");
        if (token == null || token == "") {
            $('#login-menu').show();
            $('#logout-menu').hide();
        } else {
            $('#login-menu').hide();
            $('#logout-menu').show();
        }
    }

    $('#logout-menu-item').on("click", function(event) {
        event.preventDefault();
        sessionStorage.setItem("token", "");
        location.reload();
    });


});