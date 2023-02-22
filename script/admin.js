$(document).ready(function() {
    var BASE_URL = "http://localhost:8080/api/";

    var allServants = "";
    var allCEs = "";

    pageLoad();
    $('#party-list').hide();
    $('#material-list').hide();
    $('#command-code-list').hide();
    $('#task-list').hide();
    $('#wish-list').hide();
    $('#skill-list').hide();
    $('#craft-essence-list').hide();
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
        $('#craft-essence-list').hide();
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
        $('#craft-essence-list').hide();
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
        $('#craft-essence-list').hide();
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
        $('#craft-essence-list').hide();
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
        $('#craft-essence-list').hide();
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
        $('#craft-essence-list').hide();
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
        $('#craft-essence-list').hide();
    });

    $('#menu-item-8').click(function() {
        getCraftEssences();
        $('#servant-list').hide();
        $('#party-list').hide();
        $('#material-list').hide();
        $('#command-code-list').hide();
        $('#task-list').hide();
        $('#wish-list').hide();
        $('#skill-list').hide();
        $('#craft-essence-list').show();
    });

    function getServants() {
        $("#servant-list .pagination-container").remove();
        $("#servant-table").html("");
        $.ajax({
            type: "GET",
            url: BASE_URL + "public/servants",
            dataType: 'json',
            success: function (result, status, xhr) {
                content = "";
                content += "<tr>"
                content +=     "<th>ID</th>";
                content +=     "<th>Image</th>";
                content +=     "<th>Servant name</th>";
                content +=     "<th>NP Level</th>";
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
                    content +=    "<td>" + data.servant.npLevel + "</td>";
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
            url: BASE_URL + "public/parties",
            dataType: 'json',
            success: function (result, status, xhr) {
                content = "";
                content += "<tr>"
                content +=     "<th>ID</th>";
                content +=    " <th>Party name</th>";
                content +=     "<th>Servants</th>";
                content +=     "<th>Mystic Code</th>";
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
                    content +=    "<td><img src = \"../view/assets/img/mystic-codes/" + data.mysticCode.name + ".png\" title = \"" + data.mysticCode.name + "\" class = \"mc-image\"></td>";
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
            url: BASE_URL + "public/materials",
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
                    content +=        "<a data-toggle=\"modal\" data-target = \"#add-material-dialog\" id = \"material-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
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
            url: BASE_URL + "public/command-codes",
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
            url: BASE_URL + "public/tasks",
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
                    content +=        "<a data-toggle = \"modal\" data-target = \"#add-task-dialog\" id = \"task-edit-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                    content +=        "<a data-toggle = \"modal\" data-target = \"#add-task-dialog\" data-task = \"" + data.id + "\" data-material = \"" + data.material.id + "\" id = \"task-edit-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
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
             url: BASE_URL + "public/wish-items",
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
                     if (data.servant.npLevel > 0) {
                        status = "OBTAINED";
                     }
                     content += "<tr id = \"wish-item-" + data.id + "\">";
                     content +=    "<td>" + data.id + "</td>";
                     content +=    "<td><img src = \"../" + data.servant.thumbnailUrl + "\" class = \"servant-portrait\"></td>";
                     content +=    "<td>" + data.servant.name + "</td>";
                     content +=    "<td><span class = \"status-" + status.toLowerCase() + "\">" + status.replaceAll("_", " ") + "</span></td>";
                     content +=    "<td id = \"wish-actions-" + data.id + "\">";
                     content +=        "<a data-toggle = \"modal\" data-target = \"#add-wish-dialog\" id = \"wishlist-edit-add-item-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                     content +=        "<a data-toggle = \"modal\" data-target = \"#add-wish-dialog\" data-wish = \"" + data.id + "\" data-servant = \"" + data.servant.id + "\" id = \"wishlist-edit-edit-item-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                     if (status == "NOT_OBTAINED") {
                        content +=     "<a data-wish = \"" + data.id + "\" id = \"wish-item-mark-as-obtained-" + data.id + "\" href = \"#/\" title = \"Mark as obtained\"><i class=\"fa-solid fa-check\"></i></a>";
                     } else {
                        content +=     "<a data-wish = \"" + data.id + "\" id = \"wish-item-mark-as-not-obtained-" + data.id + "\" href = \"#/\" title = \"Mark as not obtained\"><i class=\"fa-solid fa-list-check\"></i></a>";
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
             url: BASE_URL + "public/skills",
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

     function getCraftEssences() {
             $("#craft-essence-list .pagination-container").remove();
             $("#craft-essence-table").html("");
             $.ajax({
                 type: "GET",
                 url: BASE_URL + "public/craft-essences",
                 dataType: 'json',
                 success: function (result, status, xhr) {
                     content = "";
                     content += "<tr>"
                     content +=     "<th>ID</th>";
                     content +=    " <th>Image</th>";
                     content +=     "<th>Craft Essence name</th>";
                     content +=     "<th>Action</th>";
                     content += "</tr>";
                     result.forEach(function(data) {
                         content += "<tr id = \"command-code-" + data.id + "\">";
                         content +=    "<td>" + data.id + "</td>";
                         content +=    "<td><img src = \"../view/assets/img/craft-essences/thumb/" + data.name + ".png\" class = \"servant-portrait\"></td>";
                         content +=    "<td>" + data.name + "</td>";
                         content +=    "<td id = \"ce-actions-" + data.id + "\">";
                         content +=        "<a data-toggle = \"modal\" data-target = \"#add-ce-dialog\" data-ce = \"" + data.id + "\" id = \"craft-essence-add-" + data.id + "\" href = \"#/\" title = \"Add\"><i class=\"fa-sharp fa-solid fa-plus\"></i></a>";
                         content +=        "<a data-toggle = \"modal\" data-target = \"#edit-ce-dialog\" data-ce = \"" + data.id + "\" id = \"craft-essence-edit-" + data.id + "\" href = \"#/\" title = \"Edit\"><i class=\"fa-solid fa-pen-to-square\"></i></a>";
                         content +=    "</td>";
                         content += "</tr>";
                     });
                     $("#craft-essence-table").html(content);
                     $('#craft-essence-table').paginathing();
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
        loadServantsForPartyEdit();
    }

    function loadServantsForPartyEdit() {
        $.ajax({
             type: "GET",
             url: BASE_URL + "public/servants/",
             dataType: 'json',
             success: function (result, status, xhr) {
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        }).then(function(servantResult) {
            allServants = servantResult;
        });
        $.ajax({
             type: "GET",
             url: BASE_URL + "public/craft-essences/",
             dataType: 'json',
             success: function (result, status, xhr) {
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        }).then(function(craftEssenceResult) {
            allCEs = craftEssenceResult;
        });
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

    $('#add-servant-np-level').on('change', function() {
        if ($(this).val() > 0) {
            $('#add-servant-level').prop('disabled', false);
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
        if ($('#add-servant-np-level').val() > 0) {
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
            npLevel: Math.min(Math.max($("#add-servant-np-level").val(), 0), 5),
            level: level,
            skills: skills,
            commandCards: cards,
            servantClass: $("#add-servant-class").find(':selected').val()
        };
        $.ajax({
             type: "POST",
             url: BASE_URL + "admin/servants",
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
        var url = BASE_URL + "public/skills-without-servant";
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
             url: BASE_URL + "public/skills-without-servant",
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
             url: BASE_URL + "public/servant-skills/" + $('#edit-servant-dialog').data('servant'),
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
            url: BASE_URL + "public/servants/" + servantId,
            dataType: 'json',
            success: function (result, status, xhr) {
                $('#edit-servant-name').val(result.servant.name);
                $('#edit-servant-image-url').val(result.servant.imageUrl);
                $('#edit-servant-thumbnail-url').val(result.servant.thumbnailUrl);
                $('#edit-servant-np-level').val(result.servant.npLevel);
                $('#edit-servant-level').attr('disabled', !result.servant.npLevel > 0);
                $('#edit-servant-level').val(result.servant.level);
                $('#edit-servant-class').val(result.servant.servantClass);
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

    $('#edit-servant-np-level').on('change', function() {
        if ($(this).val() > 0) {
            $('#edit-servant-level').prop('disabled', false);
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
        if ($('#edit-servant-np-level').val() > 0) {
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
            npLevel: Math.min(Math.max($("#edit-servant-np-level").val(), 0), 5),
            level: level,
            skills: skills,
            commandCards: cards,
            servantClass: $("#edit-servant-class").find(':selected').val()
        };
        $.ajax({
             type: "PATCH",
             url: BASE_URL + "admin/servants/" + $('#edit-servant-dialog').data('servant'),
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
        loadServantsForPartyEdit();
        var url = BASE_URL + "public/mystic-codes";
        $.ajax({
             type: "GET",
             url: url,
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 result.forEach(function(data){
                    content += "<option value = \""+ data.id + "\">";
                    content += data.name;
                    content += "</option>";
                 });
                 $('#add-mystic-code').html(content);
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
    });

    $('#add-party-member-button').on("click", function() {
        var content = "";
        var members = $('#add-party-member-main').children().length;
        if (members > 5) {
            return;
        }
        $('#remove-party-member-' + members).hide();
        content += "<div id = \"add-add-party-member-" + (members + 1) + "\" class = \"row\">";
        content += "<select id = \"party-servant-select-" + (members + 1) +"\" class = \"col-5\">";
        allServants.forEach(function(data) {
            content += "<option value = \""+ data.servant.id + "\">";
            content += data.servant.name;
            content += "</option>";
        });
        content +=      "</select>";
        content += "<select id = \"party-ce-select-" + (members + 1) +"\" class = \"col-5\">";
        allCEs.forEach(function(ce) {
            content += "<option value = \""+ ce.id + "\">";
            content += ce.name;
            content += "</option>";
        });
        content +=      "</select>";
        content += "<div class = \"col-2 center\"><a href = \"#/\" data-member = \"" + (members + 1) +"\" id = \"remove-party-member-" + (members + 1) + "\"><i class=\"fa-solid fa-trash\"></i></a></div>";
        content += "</div>";
        $('#add-party-member-main').append(content);
        $('#party-ce-select-' + (members + 1)).trigger('change');
        $('#party-servant-select-' + (members + 1)).trigger('change');
    });

    $('#add-party-form').on('click', '[id^=remove-party-member-]', function() {
        var elementToRemove = '#add-party-member-' + $(this).data('member');
        $(elementToRemove).remove();
        if ($(this).data('member') > 1) {
            $('#remove-party-member-' + ($(this).data('member') - 1)).show();
        }
    });

    $('#add-party-accept').on("click", function(event) {
        event.preventDefault();
        // Validate the party details
        if (!/\S/.test($('#add-party-name').val())) {
            $('#add-party-name').notify("Party name is invalid", {
                position: "right"
            });
            return;
        }
        if ($('#add-party-member-main').children().length <= 0) {
            $('#add-party-member-main').notify("Party must have a servant", {
                position: "right"
            });
            return;
        }
        saveParty();
    });

    $('#add-party-member-main').on('change', '[id^=party-servant-select-]', function(){
        $(this).parent().data('servant', $(this).find(':selected').val());
    });

    $('#add-party-member-main').on('change', '[id^=party-ce-select-]', function(){
        $(this).parent().data('ce', $(this).find(':selected').val());
    });

    function saveParty() {
        var servants = [];
        $('[id^=add-add-party-member-]').each(function() {
            console.log($(this).data('servant'));
            console.log($(this).data('ce'));
            var servant = {
                servantId: $(this).data('servant'),
                craftEssenceId: $(this).data('ce')
            }
            servants.push(servant);
        });
        var data = {
            name: $('#add-party-name').val(),
            mysticCodeId: $('#add-mystic-code').find(':selected').val(),
            partyMembers: servants
        };
        console.log(JSON.stringify(data));
        $.ajax({
             type: "POST",
             url: BASE_URL + "admin/parties",
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
                getParties();
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
        $.ajax({
             type: "GET",
             url: BASE_URL + "public/mystic-codes",
             dataType: 'json',
             success: function (result, status, xhr) {
                 content = "";
                 result.forEach(function(data){
                    content += "<option value = \""+ data.id + "\">";
                    content += data.name;
                    content += "</option>";
                 });
                 $('#edit-mystic-code').html(content);
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
        var data = "";
        var servantData = "";
        var ceData = "";
        $.ajax({
             type: "GET",
             url: BASE_URL + "public/parties/" + $(this).data('party'),
             dataType: 'json',
             success: function (result, status, xhr) {
                 $('#edit-party-name').val(result.name);
                 var i = 0;
                 $('#edit-party-member-main').html("");
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        }).then(function(result) {
            $.ajax({
                 type: "GET",
                 url: BASE_URL + "public/servants/",
                 dataType: 'json',
                 success: function (result, status, xhr) {
                 },
                 error: function (xhr, status, error) {
                      console.log(error);
                 }
            }).then(function(servantResult) {
                allServants = servantResult;
                $.ajax({
                     type: "GET",
                     url: BASE_URL + "public/craft-essences/",
                     dataType: 'json',
                     success: function (result, status, xhr) {
                     },
                     error: function (xhr, status, error) {
                          console.log(error);
                     }
                }).then(function(craftEssenceResult) {
                    allCEs = craftEssenceResult;
                    $('#edit-party-dialog').data('party', result.id);
                    $('#edit-party-name').val(result.name);
                    var servants = result.servants;
                    var i = 0;
                    servants.forEach(function(servant) {
                        var servantSelect = "";
                        var ceSelect = "";
                        $('#edit-remove-party-member-' + i).hide();
                        servantSelect += "<div id = \"edit-add-party-member-" + (i + 1) + "\" class = \"row\">";
                        servantSelect += "<select id = \"edit-party-servant-select-" + (i + 1) +"\" class = \"col-5\">";
                        servantResult.forEach(function(servantChoice) {
                            servantSelect += "<option value = \""+ servantChoice.servant.id + "\">";
                            servantSelect += servantChoice.servant.name;
                            servantSelect += "</option>";
                        });
                        servantSelect +=      "</select>";
                        ceSelect += "<select id = \"edit-party-ce-select-" + (i + 1) +"\" class = \"col-5\">";
                        craftEssenceResult.forEach(function(ceChoice) {
                            ceSelect += "<option value = \""+ ceChoice.id + "\">";
                            ceSelect += ceChoice.name;
                            ceSelect += "</option>";
                        });
                        ceSelect +=      "</select>";
                        var content = servantSelect + ceSelect;
                        content += "<div class = \"col-2 center\"><a href = \"#/\" data-member = \"" + (i + 1) +"\" id = \"remove-party-member-" + (i + 1) + "\"><i class=\"fa-solid fa-trash\"></i></a></div>";
                        content += "</div>";
                        $('#edit-party-member-main').append(content);
                        console.log(result);
                        $('#edit-party-servant-select-' + (i + 1) + '>option:eq(' + (servant.servant.id - 1) + ')').attr('selected', true);
                        $('#edit-party-servant-select-' + (i + 1)).trigger('change');
                        if (servant.craftEssence != null) {
                            $('#edit-party-ce-select-' + (i + 1) + '>option:eq(' + (servant.craftEssence.id - 1) + ')').attr('selected', true);
                        }
                        $('#edit-party-ce-select-' + (i + 1)).trigger('change');
                        i++;
                    });
                });
            });
        });
    });

    $('#edit-party-member-main').on('change', '[id^=edit-party-servant-select-]', function(){
        $(this).parent().data('servant', $(this).find(':selected').val());
    });

    $('#edit-party-member-main').on('change', '[id^=edit-party-ce-select-]', function(){
        $(this).parent().data('ce', $(this).find(':selected').val());
    });

    $('#edit-party-member-button').on("click", function() {
        var content = "";
        var members = $('#edit-party-member-main').children().length;
        if (members > 5) {
            return;
        }
        $('#edit-remove-party-member-' + members).hide();
        content += "<div id = \"edit-add-party-member-" + (members + 1) + "\" class = \"row\">";
        content += "<select id = \"edit-party-servant-select-" + (members + 1) +"\" class = \"col-5\">";
        allServants.forEach(function(data) {
            content += "<option value = \""+ data.servant.id + "\">";
            content += data.servant.name;
            content += "</option>";
        });
        content +=      "</select>";
        content += "<select id = \"edit-party-ce-select-" + (members + 1) +"\" class = \"col-5\">";
        allCEs.forEach(function(ce) {
            content += "<option value = \""+ ce.id + "\">";
            content += ce.name;
            content += "</option>";
        });
        content +=      "</select>";
        content += "<div class = \"col-2 center\"><a href = \"#/\" data-member = \"" + (members + 1) +"\" id = \"edit-remove-party-member-" + (members + 1) + "\"><i class=\"fa-solid fa-trash\"></i></a></div>";
        content += "</div>";
        $('#edit-party-member-main').append(content);
        $('#edit-party-ce-select-' + (members + 1)).trigger('change');
        $('#edit-party-servant-select-' + (members + 1)).trigger('change');
    });

    $('#edit-party-form').on('click', '[id^=edit-remove-party-member-]', function() {
        var elementToRemove = '#edit-add-party-member-' + $(this).data('member');
        $(elementToRemove).remove();
        if ($(this).data('member') > 1) {
            $('#edit-remove-party-member-' + ($(this).data('member') - 1)).show();
        }
    });

    $('#edit-party-accept').on("click", function(event) {
        event.preventDefault();
        // Validate the party details
        if (!/\S/.test($('#edit-party-name').val())) {
            $('#edit-party-name').notify("Party name is invalid", {
                position: "right"
            });
            return;
        }
        if ($('#edit-party-member-main').children().length <= 0) {
            $('#edit-party-member-main').notify("Party must have a servant", {
                position: "right"
            });
            return;
        }
        updateParty();
    });

    $('#edit-party-member-main').on('change', '[id^=edit-party-servant-select-]', function(){
        $(this).parent().data('servant', $(this).find(':selected').val());
    });

    $('#edit-party-member-main').on('change', '[id^=edit-party-ce-select-]', function(){
        $(this).parent().data('ce', $(this).find(':selected').val());
    });

    function updateParty() {
        var servants = [];
        $('[id^=edit-add-party-member-]').each(function() {
            console.log($(this).data('servant'));
            console.log($(this).data('ce'));
            var servant = {
                servantId: parseInt($(this).data('servant')),
                craftEssenceId: parseInt($(this).data('ce'))
            }
            servants.push(servant);
        });
        var data = {
            id: $('#edit-party-dialog').data('party'),
            name: $('#edit-party-name').val(),
            mysticCodeId: $('#edit-mystic-code').find(':selected').val(),
            partyMembers: servants
        };
        console.log(JSON.stringify(data));
        $.ajax({
             type: "PATCH",
             url: BASE_URL + "admin/parties/" + $('#edit-party-dialog').data('party'),
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
                $.notify("Party created successfully", "success");
                getParties();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
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
             url: BASE_URL + "admin/materials",
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
            url: BASE_URL + "public/materials/" + materialId,
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
        updateMaterial();
        $('#edit-material-dialog #edit-material-cancel').click();
        $.notify("Material saved successfully","success");
    });

    function updateMaterial() {
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
             url: BASE_URL + "admin/materials/" + materialId,
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
             url: BASE_URL + "admin/materials/decrement/" + materialId,
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
             url: BASE_URL + "admin/materials/increment/" + materialId,
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

    // Handle the add command code dialog
    $('#add-cc-accept').on('click', function() {
        event.preventDefault();
        // Validate the material details
        if (!/\S/.test($('#add-cc-name').val())) {
            $('#add-cc-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        saveCommandCode();
        $('#add-cc-dialog #add-cc-cancel').click();
        $.notify("Command Code saved successfully","success");
    });

    function saveCommandCode() {
        var data = {
            name: $('#add-cc-name').val()
        }
        $.ajax({
             type: "POST",
             url: BASE_URL + "admin/command-codes",
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
                 getCommandCodes();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    // Handle the edit command code dialog
    $('#command-code-list').on("click", '[id^=command-code-edit-]', function(){
        $('#edit-cc-dialog').data('cc', $(this).data('cc'));
        $.ajax({
             type: "GET",
             url: BASE_URL + "public/command-codes/" + $(this).data('cc'),
             dataType: 'json',
             success: function (result, status, xhr) {
                 $('#edit-cc-name').val(result.name);
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
    });
    $('#edit-cc-accept').on('click', function() {
        event.preventDefault();
        // Validate the material details
        if (!/\S/.test($('#edit-cc-name').val())) {
            $('#edit-cc-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        updateCommandCodes();
        $('#edit-cc-dialog #edit-cc-cancel').click();
        $.notify("Command Code saved successfully","success");
    });

    function updateCommandCodes() {
        var ccid = $('#edit-cc-dialog').data('cc');
        var data = {
            id: ccid,
            name: $('#edit-cc-name').val(),
            imageUrl: $('#edit-cc-image-url').val()
        }
        $.ajax({
             type: "PATCH",
             url: BASE_URL + "admin/command-codes/" + ccid,
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
                 getCommandCodes();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    // Handle the add and edit wish item dialog
    $('#wish-list').on('click', '[id^=wishlist-edit-]', function(event) {
        var servantId = $(this).data('servant');
        $('#add-wish-dialog').data('wish', $(this).data('wish'));
        if (servantId != null) {
            $('#add-wish-dialog').data('servant', servantId);
        }
        $.ajax({
            type: "GET",
            url: BASE_URL + "public/servants",
            dataType: 'json',
            success: function (result, status, xhr) {
                content = "";
                result.forEach(function(data) {
                    content += "<option value = \"" + data.servant.id + "\">" + data.servant.name + "</option>";
                });
                $('#add-wish-name').html(content);
                $('#add-wish-image').attr('src', '../' + result[0].servant.thumbnailUrl);
                $('#add-wish-name').val(servantId).trigger('change');
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

    $('#add-wish-name').on('change', function() {
        if ($('#add-wish-name').val() != null) {
            $.ajax({
                type: "GET",
                url: BASE_URL + "public/servants/" + $('#add-wish-name').val(),
                dataType: 'json',
                success: function (result, status, xhr) {
                    content = "";
                    $('#add-wish-image').attr('src', '../' + result.servant.thumbnailUrl);
                },
                error: function (xhr, status, error) {
                     console.log(error);
                }
            });
        }
    });

    $('#add-wish-accept').on('click', function() {
        event.preventDefault();
        if($('#add-wish-dialog').data('wish') == null) {
            saveWishList();
        } else {
            updateWishList();
        }
        $('#add-wish-dialog #add-wish-cancel').click();
        $.notify("Servant saved to wishlist successfully","success");
    });

    function saveWishList() {
        var data = {
            servant:
            {
                id: $('#add-wish-name').val()
            }
        }
        $.ajax({
             type: "POST",
             url: BASE_URL + "admin/wish-items",
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
                 getWishlist();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    function updateWishList() {
        $.ajax({
             type: "PUT",
             url: BASE_URL + "admin/wish-items/" + $('#add-wish-dialog').data('wish') + "/" + $('#add-wish-name').val(),
             dataType: 'json',
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                 getWishlist();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    // Handle the mark as obtained and not obtained button
    $('#wish-list').on('click', '[id^=wish-item-mark-as-]', function() {
        $.ajax({
             type: "PUT",
             url: BASE_URL + "admin/wish-items/toggle-obtained/" + $(this).data('wish'),
             dataType: 'json',
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                 getWishlist();
                 $.notify("Wishlist updated successfully","success");
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    });

    // Handle the add skill dialog
    $('#add-skill-accept').on('click', function() {
        event.preventDefault();
        // Validate the material details
        if (!/\S/.test($('#add-skill-name').val())) {
            $('#add-skill-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        if (!/\S/.test($('#add-skill-image-url').val())) {
            $('#add-skill-image-url').notify("Image URL is invalid", {
                position: "right"
            });
            return;
        }
        saveSkill();
        $('#add-skill-dialog #add-skill-cancel').click();
        $.notify("Skill saved successfully","success");
    });

    function saveSkill() {
        var data = {
            name: $('#add-skill-name').val(),
            imageUrl: $('#add-skill-image-url').val(),
        }
        $.ajax({
             type: "POST",
             url: BASE_URL + "admin/skills",
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
                getSkills();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    // Handle the edit material dialog
    $('#skill-list').on("click", '[id^=skill-edit-]', function(){
        $('#edit-skill-dialog').data('skill', $(this).data('skill'));
    });
    $('#edit-skill-dialog').on("show.bs.modal", function() {
        var skillId = $(this).data('skill');
        $.ajax({
            type: "GET",
            url: BASE_URL + "public/skills/" + skillId,
            dataType: 'json',
            success: function (result, status, xhr) {
                $('#edit-skill-name').val(result.name);
                $('#edit-skill-image-url').val(result.imageUrl);
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });
    });

    $('#edit-skill-accept').on('click', function() {
        event.preventDefault();
        // Validate the material details
        if (!/\S/.test($('#edit-skill-name').val())) {
            $('#edit-skill-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        if (!/\S/.test($('#edit-skill-image-url').val())) {
            $('#edit-skill-image-url').notify("Image URL is invalid", {
                position: "right"
            });
            return;
        }
        updateSkill();
        $('#edit-skill-dialog #edit-skill-cancel').click();
        $.notify("Skill saved successfully","success");
    });

    function updateSkill() {
        var skillId = $('#edit-skill-dialog').data('skill');
        var data = {
            id: skillId,
            name: $('#edit-skill-name').val(),
            imageUrl: $('#edit-skill-image-url').val()
        }
        $.ajax({
             type: "PATCH",
             url: BASE_URL + "admin/skills/" + skillId,
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
                 getSkills();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    // Handle the add and edit task dialog
    $('#task-list').on('click', '[id^=task-edit-]', function(event) {
        $('#add-task-progress').val(0);
        $('#add-task-goal').val(0);
        var materialId = $(this).data('material');
        $('#add-task-dialog').data('task', $(this).data('task'));
        if (materialId != null) {
            $('#add-task-dialog').data('material', materialId);
        }
        $.ajax({
            type: "GET",
            url: BASE_URL + "public/materials",
            dataType: 'json',
            success: function (result, status, xhr) {
                content = "";
                result.forEach(function(data) {
                    content += "<option value = \"" + data.id + "\">" + data.name + "</option>";
                });
                $('#add-task-name').html(content);
                if (materialId != null) {
                    $('#add-task-name').val(materialId);
                }
            },
            error: function (xhr, status, error) {
                 console.log(error);
            }
        });

        if (materialId != null) {
            $.ajax({
                type: "GET",
                url: BASE_URL + "public/tasks/" + $(this).data('task'),
                dataType: 'json',
                success: function (result, status, xhr) {
                    $('#add-task-progress').val(result.progress);
                    $('#add-task-goal').val(result.goal);
                },
                error: function (xh4, status, error) {
                    console.log(error);
                }
            });
        }
    });

    $('#add-task-accept').on('click', function() {
        event.preventDefault();
        if($('#add-task-dialog').data('material') == null) {
            saveTask();
        } else {
            updateTask();
        }
        $('#add-task-dialog #add-task-cancel').click();
        $.notify("Task saved successfully","success");
    });

    function saveTask() {
        var data = {
            material:
            {
                id: $('#add-task-name').val()
            },
            progress: $('#add-task-progress').val(),
            goal: $('#add-task-goal').val(),
            status: 'IN_PROGRESS'
        }
        $.ajax({
             type: "POST",
             url: BASE_URL + "admin/tasks",
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
                 getTasks();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    function updateTask() {
        var data = {
            id: $('#add-task-dialog').data('task'),
            status: 'IN_PROGRESS',
            material:
            {
                id: $('#add-task-name').val()
            },
            progress: $('#add-task-progress').val(),
            goal: $('#add-task-goal').val()
        }
        $.ajax({
             type: "PUT",
             url: BASE_URL + "admin/tasks/" + $('#add-task-dialog').data('task'),
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
                 getTasks();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    // Handle the mark as completed and in progress button
    $('#task-list').on('click', '[id^=task-mark-as-]', function() {
        $.ajax({
             type: "PUT",
             url: BASE_URL + "admin/tasks/toggle-completed/" + $(this).data('task'),
             dataType: 'json',
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                 getTasks();
                 $.notify("Task updated successfully","success");
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    });

    // Handle the increment button
    $('#task-list').on('click', '[id^=task-increment-]', function() {
        $.ajax({
             type: "PATCH",
             url: BASE_URL + "admin/tasks/increment/" + $(this).data('task'),
             dataType: 'json',
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('Authorization', sessionStorage.getItem("token"));
             },
             headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
             success: function (result, status, xhr) {
                 getTasks();
                 $.notify("Task updated successfully","success");
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    });

    // Handle the add craft essence dialog
    $('#add-ce-accept').on('click', function() {
        event.preventDefault();
        // Validate the material details
        if (!/\S/.test($('#add-ce-name').val())) {
            $('#add-ce-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        saveCraftEssence();
        $('#add-ce-dialog #add-ce-cancel').click();
        $.notify("Craft Essence saved successfully","success");
    });

    function saveCraftEssence() {
        var data = {
            name: $('#add-ce-name').val()
        }
        $.ajax({
             type: "POST",
             url: BASE_URL + "admin/craft-essences",
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
                 getCraftEssences();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

    // Handle the edit craft essence dialog
    $('#craft-essence-list').on("click", '[id^=craft-essence-edit-]', function(){
        $('#edit-ce-dialog').data('ce', $(this).data('ce'));
        $.ajax({
             type: "GET",
             url: BASE_URL + "public/craft-essences/" + $(this).data('ce'),
             dataType: 'json',
             success: function (result, status, xhr) {
                 $('#edit-ce-name').val(result.name);
             },
             error: function (xhr, status, error) {
                  console.log(error);
             }
        });
    });
    $('#edit-ce-accept').on('click', function() {
        event.preventDefault();
        // Validate the material details
        if (!/\S/.test($('#edit-ce-name').val())) {
            $('#edit-ce-name').notify("Name is invalid", {
                position: "right"
            });
            return;
        }
        updateCraftEssence();
        $('#edit-ce-dialog #edit-ce-cancel').click();
        $.notify("Craft Essence saved successfully","success");
    });

    function updateCraftEssence() {
        var ccid = $('#edit-ce-dialog').data('ce');
        var data = {
            id: ccid,
            name: $('#edit-ce-name').val(),
            imageUrl: $('#edit-cc-image-url').val()
        }
        $.ajax({
             type: "PATCH",
             url: BASE_URL + "admin/craft-essences/" + ccid,
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
                 getCraftEssences();
             },
             error: function (xhr, status, error) {
                 console.log(error);
             },
         });
    }

});