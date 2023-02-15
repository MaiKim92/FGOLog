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
        $("#servant-table").html("");
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
                    content +=        "<a id = \"servant-add-" + data.servant.id + "\" data-toggle=\"modal\" data-target = \"#add-servant-dialog\" data-servant = \"" + data.servant.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a id = \"servant-edit-" + data.servant.id + "\" data-toggle=\"modal\" data-target = \"#edit-servant-dialog\" data-servant = \"" + data.servant.id + "\"  href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    content +=        "<a id = \"command-card-edit-" + data.servant.id + "\" data-toggle=\"modal\" data-target = \"#edit-command-card-dialog\" data-servant = \"" + data.servant.id + "\"  href = \"#/\" title = \"Edit Command Cards\"><i class=\"fa-solid fa-terminal\"></i></a>";
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
        $("#party-table").html("");
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
        $("#material-list .pagination-container").remove();
        $("#material-table").html("");
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
                    content +=      "<a id = \"material-decrement-" + data.id + "\" data-material = \"" + data.id + "\" href = \"#/\" title = \"Decrement\"><i class=\"fa-solid fa-caret-down\"></i></a>";
                    content +=      data.number;
                    content +=      "<a id = \"material-increment-" + data.id + "\" data-material = \"" + data.id + "\" href = \"#/\" title = \"Increment\"><i class=\"fa-solid fa-caret-up\"></i></a>";
                    content +=    "</td>";
                    content +=    "<td id = \"material-actions-" + data.id + "\">";
                    content +=        "<a data-toggle=\"modal\" data-target = \"#add-material-dialog\" data-material = \"" + data.id + "\" id = \"material-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a data-toggle=\"modal\" data-target = \"#edit-material-dialog\" data-material = \"" + data.id + "\" id = \"material-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
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
        $("#command-code-table").html("");
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
                    content +=        "<a data-toggle = \"modal\" data-target = \"#add-cc-dialog\" data-cc = \"" + data.id + "\" id = \"command-code-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a data-toggle = \"modal\" data-target = \"#edit-cc-dialog\" data-cc = \"" + data.id + "\" id = \"command-code-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
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
        $("#task-table").html("");
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
                    content +=        "<a data-toggle = \"modal\" data-target = \"#add-task-dialog\" data-task = \"" + data.id + "\" id = \"task-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a data-toggle = \"modal\" data-target = \"#edit-task-dialog\" data-task = \"" + data.id + "\" id = \"task-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                    if (data.status.toLowerCase() == "in_progress") {
                        content +=        "<a data-task = \"" + data.id + "\" id = \"task-mark-as-completed-" + data.id + "\" href = \"#/\" title = \"Mark as completed\"><i class=\"fa-solid fa-check\"></i></a>";
                    } else {
                        content +=        "<a data-task = \"" + data.id + "\" id = \"task-mark-as-in-progress-" + data.id + "\" href = \"#/\" title = \"Mark as in progress\"><i class=\"fa-solid fa-bars-progress\"></i></a>";
                    }
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
         $("#wish-table").html("");
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
                     content +=        "<a data-toggle = \"modal\" data-target = \"#add-wish-dialog\" data-wish = \"" + data.id + "\" id = \"wish-item-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                     content +=        "<a data-toggle = \"modal\" data-target = \"#edit-wish-dialog\" data-wish = \"" + data.id + "\" id = \"wish-item-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                     if (status == "NOT_OBTAINED") {
                        content +=     "<a data-wish = \"" + data.id + "\" id = \"wish-item-mark-as-obtained-" + data.id + "\" href = \"#/\" title = \"Mark as obtained\"><i class=\"fa-solid fa-check\"></i></i></a>";
                     }
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
         $("#skill-table").html("");
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
                     content +=        "<a data-toggle = \"modal\" data-target = \"#add-skill-dialog\" data-skill = \"" + data.id + "\" id = \"skill-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                     content +=        "<a data-toggle = \"modal\" data-target = \"#edit-skill-dialog\" data-skill = \"" + data.id + "\" id = \"skill-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
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


    // Handle the add servant dialog
    $('#add-servant-dialog').on("show.bs.modal", function() {
        getAllSkillForServantSkills($('#add-servant-skill'));
    });

    $('#_add-skill-button').on("click", function(event) {
        event.preventDefault();
        $('#add-servant-skill option:selected').remove().appendTo('#add-servant-skill-2');
        $('#_remove-skill-button').prop('disabled', false);
        if ($('#add-servant-skill-2').children().length >= 3) {
            $('#_add-skill-button').prop('disabled', true);
        }
    });

    $('#_remove-skill-button').on("click", function(event) {
        event.preventDefault();
        $('#add-servant-skill-2 option:selected').remove().appendTo('#add-servant-skill');
        $('#_add-skill-button').prop('disabled', false);
        if ($('#add-servant-skill-2').children().length <= 0) {
            $('#_remove-skill-button').prop('disabled', true);
        }
    });

    $('#add-servant-accept').on("click", function(event) {
        event.preventDefault();
        // Validate the servant details
        if (!/\S/.test($('#add-servant-name').val())) {
            $('#add-servant-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        if (!/\S/.test($('#add-servant-image-url').val())) {
            $('#add-servant-image-url').notify("Image URL is invalid", {
                position: "right"
            });
            return;
        }
        if (!/\S/.test($('#add-servant-thumbnail-url').val())) {
            $('#add-servant-thumbnail-url').notify("Thumbnail URL is invalid", {
                position: "right"
            });
            return;
        }
        if ($('#add-servant-skill-2').children().length < 3) {
            var accept = confirm("A Servant must have 3 skills. Are you sure to continue?");
            if (!accept) {
                return;
            }
        }
        var saved = saveServant();
        if (saved) {
            $('#add-servant-dialog #add-servant-cancel').click();
            $.notify("Servant created successfully", "success");
        }
    });

    $('#add-servant-is-has-true').on('change', function() {
        if ($(this).is(':checked')) {
            $('#add-servant-level').prop('disabled', false);
        }
    });

    $('#add-servant-is-has-false').on('change', function() {
        if ($(this).is(':checked')) {
            $('#add-servant-level').prop('disabled', true);
        }
    });

    function saveServant() {
        var cards = $('#add-servant-cards').val().split("");
        var success = false;
        if (cards.length != 5) {
            $('#add-servant-cards').notify("A Servant must have five Command Cards", {
                position: "right"
            });
            return false;
        }
        cards.every(function(card) {
            if (card != 'A' && card != 'B' && card != 'Q') {
                $('#add-servant-cards').notify("Command Cards are invalid! Accepted values are: Q, A, B.", {
                    position: "right"
                });
                success = false;
                return false;
            }
            success = true;
            return true;
        });
        if (!success) {
            return false;
        }
        var level = 0;
        if ($('#add-servant-is-has-true').is(":checked")) {
            level = Math.min($("#add-servant-level").val(), 120);
        } else {
            level = 0;
        }
        var skills = [];
        $('#add-servant-skill-2 option').each(function() {
            skills.push(parseInt($(this).val()));
        });
        var data = {
            name: $('#add-servant-name').val(),
            imageUrl: $('#add-servant-image-url').val(),
            thumbnailUrl: $('#add-servant-thumbnail-url').val(),
            isHas: $("#add-servant-is-has-true").is(":checked"),
            level: level,
            skills: skills,
            commandCards: cards
        };
        $.ajax({
             type: "POST",
             url: "http://localhost:8080/api/admin/servants",
             dataType: 'json',
             data: JSON.stringify(data),
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                getServants();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
        return true;
    }

    function getAllSkillForServantSkills(selector) {
        var url = "http://localhost:8080/api/public/skills-without-servant";
        $.ajax({
             type: "GET",
             url: url,
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 result.forEach(function(data) {
                    content += "<option value = \"" + data.id + "\">" + data.name + "</option>";
                 });
                 selector.html(content);
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
    }
    function getAllSkillForEditServantSkills() {
        $.ajax({
             type: "GET",
             url: "http://localhost:8080/api/public/skills-without-servant",
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 result.forEach(function(data) {
                    content += "<option value = \"" + data.id + "\">" + data.name + "</option>";
                 });
                 $('#edit-servant-skill').html(content);
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
        $.ajax({
             type: "GET",
             url: "http://localhost:8080/api/public/servant-skills/" + $('#edit-servant-dialog').data('servant'),
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 result.forEach(function(data) {
                    content += "<option value = \"" + data.id + "\">" + data.name + "</option>";
                 });
                 $('#edit-servant-skill-2').html(content);
                 if ($('#edit-servant-skill-2').children().length >= 3) {
                     $('#_edit-add-skill-button').prop('disabled', true);
                 }
                 if ($('#edit-servant-skill-2').children().length <= 0) {
                     $('#_edit-remove-skill-button').prop('disabled', true);
                 }
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
    }

    // Handle the edit servant dialog
    $('#servant-list').on("click", '[id^=servant-edit-]', function(){
        $('#edit-servant-dialog').data('servant', $(this).data('servant'));
    });

    $('#_edit-add-skill-button').on("click", function(event) {
        event.preventDefault();
        $('#edit-servant-skill option:selected').remove().appendTo('#edit-servant-skill-2');
        $('#_edit-remove-skill-button').prop('disabled', false);
        if ($('#edit-servant-skill-2').children().length >= 3) {
            $('#_edit-add-skill-button').prop('disabled', true);
        }
    });

    $('#_edit-remove-skill-button').on("click", function(event) {
        event.preventDefault();
        $('#edit-servant-skill-2 option:selected').remove().appendTo('#edit-servant-skill');
        $('#_edit-add-skill-button').prop('disabled', false);
        if ($('#edit-servant-skill-2').children().length <= 0) {
            $('#_edit-remove-skill-button').prop('disabled', true);
        }
    });
    $('#edit-servant-dialog').on("show.bs.modal", function() {
        var servantId = $(this).data('servant');
        getAllSkillForEditServantSkills();
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/servants/" + servantId,
            dataType: 'json',
            success: function (result, status, xhr) {
                $('#edit-servant-name').val(result.servant.name);
                $('#edit-servant-image-url').val(result.servant.imageUrl);
                $('#edit-servant-thumbnail-url').val(result.servant.thumbnailUrl);
                $('#edit-servant-is-has-true').attr('checked', result.servant.isHas);
                $('#edit-servant-level').attr('disabled', !result.servant.isHas);
                $('#edit-servant-level').val(result.servant.level);
                var commandCards = result.commandCards;
                var cards = ""
                commandCards.every(function(card) {
                    if (card.type == 'QUICK') {
                        cards += "Q";
                        return true;
                    }
                    if (card.type == 'ARTS') {
                        cards += "A";
                        return true;
                    }
                    cards += "B";
                    return true;
                });
                $('#edit-servant-cards').val(cards);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

    $('#edit-servant-accept').on("click", function(event) {
        event.preventDefault();
        // Validate the servant details
        if (!/\S/.test($('#edit-servant-name').val())) {
            $('#edit-servant-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        if (!/\S/.test($('#edit-servant-image-url').val())) {
            $('#edit-servant-image-url').notify("Image URL is invalid", {
                position: "right"
            });
            return;
        }
        if (!/\S/.test($('#edit-servant-thumbnail-url').val())) {
            $('#edit-servant-thumbnail-url').notify("Thumbnail URL is invalid", {
                position: "right"
            });
            return;
        }
        var saved = updateServant($('#edit-servant-dialog').data("servant"));
        if (saved) {
            $('#edit-servant-dialog #edit-servant-cancel').click();
            $.notify("Servant saved successfully","success");
        }
    });

    $('#edit-servant-is-has-true').on('change', function() {
        if ($(this).is(':checked')) {
            $('#edit-servant-level').prop('disabled', false);
        }
    });

    $('#edit-servant-is-has-false').on('change', function() {
        if ($(this).is(':checked')) {
            $('#edit-servant-level').prop('disabled', true);
        }
    });

    function updateServant(servantId) {
        var cards = $('#edit-servant-cards').val().split("");
        var success = false;
        if (cards.length != 5) {
            $('#edit-servant-cards').notify("A Servant must have five Command Cards", {
                position: "right"
            });
            return false;
        }
        cards.every(function(card) {
            if (card != 'A' && card != 'B' && card != 'Q') {
                $('#edit-servant-cards').notify("Command Cards are invalid! Accepted values are: Q, A, B.", {
                    position: "right"
                });
                success = false;
                return false;
            }
            success = true;
            return true;
        });
        if (!success) {
            return false;
        }
        var level = 0;
        console.log(cards);
        if ($('#edit-servant-is-has-true').is(":checked")) {
            level = Math.min($("#edit-servant-level").val(), 120);
        } else {
            level = 0;
        }
        var skills = [];
        $('#edit-servant-skill-2 option').each(function() {
            skills.push(parseInt($(this).val()));
        });
        var data = {
            name: $('#edit-servant-name').val(),
            imageUrl: $('#edit-servant-image-url').val(),
            thumbnailUrl: $('#edit-servant-thumbnail-url').val(),
            isHas: $("#edit-servant-is-has-true").is(":checked"),
            level: level,
            skills: skills,
            commandCards: cards
        };
        $.ajax({
             type: "PATCH",
             url: "http://localhost:8080/api/admin/servants/" + $('#edit-servant-dialog').data('servant'),
             dataType: 'json',
             data: JSON.stringify(data),
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                getServants();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
        return true;
    }

    // Handle the add party dialog
    $('#add-party-dialog').on("show.bs.modal", function() {
        getAllServantsForPartyMembers();
    });

    $('#_add-member-button').on("click", function(event) {
        event.preventDefault();
        $('#add-party-member option:selected').remove().appendTo('#add-party-member-2');
        $('#_remove-member-button').prop('disabled', false);
        if ($('#add-party-member-2').children().length >= 6) {
            $('#_add-member-button').prop('disabled', true);
        }
    });

    $('#_remove-member-button').on("click", function(event) {
        event.preventDefault();
        $('#add-party-member-2 option:selected').remove().appendTo('#add-party-member');
        $('#_add-member-button').prop('disabled', false);
        if ($('#add-party-member-2').children().length <= 0) {
            $('#_remove-member-button').prop('disabled', true);
        }
    });

    function getAllServantsForPartyMembers() {
        var url = "http://localhost:8080/api/public/servants";
        $.ajax({
             type: "GET",
             url: url,
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 result.forEach(function(data) {
                    content += "<option value = \"" + data.servant.id + "\">" + data.servant.name + "</option>";
                 });
                 $('#add-party-member').html(content);
                 if ($('#add-party-member-2').children().length >= 6) {
                     $('#_add-member-button').prop('disabled', true);
                 }
                 if ($('#add-party-member-2').children().length <= 0) {
                     $('#_remove-member-button').prop('disabled', true);
                 }
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
    }

    $('#add-party-accept').on("click", function(event) {
        event.preventDefault();
        // Validate the party details
        if (!/\S/.test($('#add-party-name').val())) {
            $('#add-party-name').notify("Party name is invalid", {
                position: "right"
            });
            return;
        }
        if ($('#add-party-member-2').children().length <= 0) {
            $('#add-party-member').notify("Party must have a servant", {
                position: "right"
            });
            return;
        }
        saveParty();
    });

    function saveParty() {
        var servants = [];
        $('#add-party-member-2 option').each(function() {
            servants.push(parseInt($(this).val()));
        });
        var data = {
            name: $('#add-party-name').val(),
            servantIds: servants
        };
        console.log(JSON.stringify(data));
        $.ajax({
             type: "POST",
             url: "http://localhost:8080/api/admin/parties",
             dataType: 'json',
             data: JSON.stringify(data),
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                $('#add-party-dialog #add-party-cancel').click();
                $.notify("Party created successfully", "success");
                getServants();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
        });
    }

    // Handle the party edit dialog
    $('#party-list').on("click", '[id^=party-edit-]', function(){
        $('#edit-party-dialog').data('party', $(this).data('party'));
    });

    $('#edit-party-dialog').on("show.bs.modal", function() {
        getAllServantsForEditPartyMembers();
    });

    $('#_edit-add-member-button').on("click", function(event) {
        event.preventDefault();
        $('#edit-party-member option:selected').remove().appendTo('#edit-party-member-2');
        $('#_edit-remove-member-button').prop('disabled', false);
        if ($('#edit-party-member-2').children().length >= 6) {
            $('#_edit-add-member-button').prop('disabled', true);
        }
    });

    $('#_edit-remove-member-button').on("click", function(event) {
        event.preventDefault();
        $('#edit-party-member-2 option:selected').remove().appendTo('#edit-party-member');
        $('#_edit-add-member-button').prop('disabled', false);
        if ($('#edit-party-member-2').children().length <= 0) {
            $('#_edit-remove-member-button').prop('disabled', true);
        }
    });

    function getAllServantsForPartyMembers() {
        $.ajax({
             type: "GET",
             url: "http://localhost:8080/api/public/servants",
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 result.forEach(function(data) {
                    content += "<option value = \"" + data.servant.id + "\">" + data.servant.name + "</option>";
                 });
                 $('#add-party-member').html(content);
                 if ($('#edit-party-member-2').children().length >= 6) {
                     $('#_edit-add-member-button').prop('disabled', true);
                 }
                 if ($('#edit-party-member-2').children().length <= 0) {
                     $('#_edit-remove-member-button').prop('disabled', true);
                 }
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
    }

    $('#edit-party-accept').on("click", function(event) {
        event.preventDefault();
        // Validate the party details
        if (!/\S/.test($('#edit-party-name').val())) {
            $('#edit-party-name').notify("Party name is invalid", {
                position: "right"
            });
            return;
        }
        if ($('#edit-party-member-2').children().length <= 0) {
            $('#edit-party-member').notify("Party must have a servant", {
                position: "right"
            });
            return;
        }
        updateParty();
    });

    function updateParty(partyId) {
        var servants = [];
        $('#edit-party-member-2 option').each(function() {
            servants.push(parseInt($(this).val()));
        });
        var data = {
            name: $('#edit-party-name').val(),
            servantIds: servants
        };
        var partyId = $('#edit-party-dialog').data('party');
        $.ajax({
             type: "PATCH",
             url: "http://localhost:8080/api/admin/parties/" + partyId,
             dataType: 'json',
             data: JSON.stringify(data),
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                $('#edit-party-dialog #edit-party-cancel').click();
                $.notify("Party updated successfully", "success");
                getParties();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
        });
    }

    function getAllServantsForEditPartyMembers() {
        var partyId = $('#edit-party-dialog').data('party');
        $.ajax({
             type: "GET",
             url: "http://localhost:8080/api/public/parties/" + partyId,
             dataType: 'json',
             success: function (result, status, xhr) {
                 $('#edit-party-name').val(result.name);
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
        $.ajax({
             type: "GET",
             url: "http://localhost:8080/api/public/servants-exclude-party/" + $('#edit-party-dialog').data('party'),
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 result.forEach(function(data) {
                    content += "<option value = \"" + data.id + "\">" + data.name + "</option>";
                 });
                 $('#edit-party-member').html(content);
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
        $.ajax({
             type: "GET",
             url: "http://localhost:8080/api/public/servants-in-party/" + $('#edit-party-dialog').data('party'),
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 result.forEach(function(data) {
                    content += "<option value = \"" + data.id + "\">" + data.name + "</option>";
                 });
                 $('#edit-party-member-2').html(content);
                 if ($('#edit-party-member-2').children().length >= 6) {
                     $('#_edit-add-member-button').prop('disabled', true);
                 }
                 if ($('#edit-party-member-2').children().length <= 0) {
                     $('#_edit-remove-member-button').prop('disabled', true);
                 }
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
    }


    // Handle the add material dialog
    $('#add-material-accept').on('click', function() {
        event.preventDefault();
        // Validate the material details
        if (!/\S/.test($('#add-material-name').val())) {
            $('#add-material-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        if (!/\S/.test($('#add-material-image-url').val())) {
            $('#add-material-image-url').notify("Image URL is invalid", {
                position: "right"
            });
            return;
        }
        saveMaterial();
        $('#add-material-dialog #add-material-cancel').click();
        $.notify("Material saved successfully","success");
    });

    function saveMaterial() {
        var number = 0;
        if ($('#add-material-number').val() != null && $('#add-material-number').val() > 0 && !isNaN($('#add-material-number').val())) {
            number = parseInt($('#add-material-number').val());
        }
        var data = {
            name: $('#add-material-name').val(),
            imageUrl: $('#add-material-image-url').val(),
            number: number
        }
        $.ajax({
             type: "POST",
             url: "http://localhost:8080/api/admin/materials",
             dataType: 'json',
             data: JSON.stringify(data),
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                getMaterials();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    // Handle the edit material dialog
    $('#material-list').on("click", '[id^=material-edit-]', function(){
        $('#edit-material-dialog').data('material', $(this).data('material'));
    });
    $('#edit-material-dialog').on("show.bs.modal", function() {
        var materialId = $(this).data('material');
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/public/materials/" + materialId,
            dataType: 'json',
            success: function (result, status, xhr) {
                $('#edit-material-name').val(result.name);
                $('#edit-material-image-url').val(result.imageUrl);
                $('#edit-material-number').val(result.number);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

    $('#edit-material-accept').on('click', function() {
        event.preventDefault();
        // Validate the material details
        if (!/\S/.test($('#edit-material-name').val())) {
            $('#edit-material-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        if (!/\S/.test($('#edit-material-image-url').val())) {
            $('#edit-material-image-url').notify("Image URL is invalid", {
                position: "right"
            });
            return;
        }
        saveMaterial();
        $('#edit-material-dialog #edit-material-cancel').click();
        $.notify("Material saved successfully","success");
    });

    function saveMaterial() {
        var materialId = $('#edit-material-dialog').data('material');
        var number = 0;
        if ($('#edit-material-number').val() != null && $('#edit-material-number').val() > 0 && !isNaN($('#edit-material-number').val())) {
            number = parseInt($('#edit-material-number').val());
        }
        var data = {
            id: materialId,
            name: $('#edit-material-name').val(),
            imageUrl: $('#edit-material-image-url').val(),
            number: number
        }
        $.ajax({
             type: "PATCH",
             url: "http://localhost:8080/api/admin/materials/" + materialId,
             dataType: 'json',
             data: JSON.stringify(data),
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                 getMaterials();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    // Handle the increment and decrement buttons
    $('#material-list').on('click', '[id^=material-decrement-]', function(event) {
        var materialId = $(this).data('material');
        $.ajax({
             type: "PATCH",
             url: "http://localhost:8080/api/admin/materials/decrement/" + materialId,
             dataType: 'json',
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                getMaterials();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    });

    $('#material-list').on('click', '[id^=material-increment-]', function(event) {
        var materialId = $(this).data('material');
        $.ajax({
             type: "PATCH",
             url: "http://localhost:8080/api/admin/materials/increment/" + materialId,
             dataType: 'json',
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                getMaterials();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    });
});