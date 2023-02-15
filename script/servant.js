$(document).ready(function(){

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
            window.location.href = "dashboard.html";
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
            window.location.href = "dashboard.html";
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
                window.location.href = "dashboard.html";
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
                window.location.href = "dashboard.html";
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
});