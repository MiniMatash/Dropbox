function getFileTree() {
    $("#tableBody").empty();
    $.ajax({
        url: "/getFileTree",
        method: "GET",
        data: {path: window.location.pathname},
        success: function (files) {
            var table = $("<table id='tableBody'>").appendTo($("#filesBody"));
            $(table).append($('<tr><th width="10%"></th><th width="20%">Name</th><th width="15%">Changed date</th><th width="10%">Authorization level</th></tr>'));
            if (window.location.pathname != "/home")
                $('<tr class="upper" style="text-align:center; height: 40px;" ondblclick="doubleClickTreeWalker(\'' +
                    window.location.href.substring(0, window.location.href.lastIndexOf("/")) + '\')">').appendTo(table)
                    .append('<td colspan="4"><span><a href="' + window.location.href.substring(0, window.location.href.lastIndexOf("/")) + '"><h3>...</h3></a></span>');
            for (var file in files) {
                var tr;
                var link = window.location.href + '/' + files[file].name;
                if (files[file].type == "folder") {
                    tr = $('<tr class="elem" ondblclick="doubleClickTreeWalker(\'' + link + '\')">').appendTo(table);
                } else {
                    tr = $('<tr class="elem">').appendTo(table);
                }
                $(tr).append('<td><img src="/images/files/' + files[file].type + '.png">');
                if (files[file].type == "folder") {
                    $(tr).append($('<td style="text-align: center"><a id="path" href="' + window.location.pathname + "/" + files[file].name + '"><h3>' + files[file].name + '</h3></a>'));
                } else {
                    $(tr).append($('<td style="text-align: center"><h3 id="fileName">' + files[file].name + '</h3>'));
                }
                $(tr).append($('<td style="text-align: center"><h3>' + files[file].modificationDate + '</h3>'));
                $(tr).append($('<td style="text-align: center"><h3>' + "--" + '</h3>'));
            }
            $(".elem,.upper").on("click", function () {
                ;
                $(".elem,.upper").removeAttr("id");
                $(this).attr("id", "selected");
                $('#fileTreeOptions').empty()
                    .append($('<button id="deleteButton"onclick="deleteElement()"><div class="deleteElement">Delete</div></button>'))
            });
            $(".upper").on("click", function () {
                $(".upper").removeAttr("id");
                $(this).attr("id", "selected");
            });
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    })
}

function doubleClickTreeWalker(a) {
    window.location.href = a;
}


function deleteElement() {
    var path;
    if ($("#selected").find("#path").attr("href") != undefined)
        path = $("#selected").find("#path").attr("href");
    else
        path = window.location.pathname+"/"+$("#selected").find("#fileName")[0].innerHTML;
    $(".deleteElement").dialog({
        buttons: [
            {
                text: "Ok",
                click: function() {
                    $.ajax({
                        url: "/deleteElement",
                        method: "POST",
                        data: {currentLocation: path},
                        success: function (result) {
                            if (result == "success") {
                                $("#deleteButton").hide;
                                $(".deleteElement").dialog("close");
                                getFileTree();
                            }
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            alert(xhr.status);
                            alert(thrownError);
                        }
                    })
                }
            },
            {
                text:"Cancel",
                click: function() {
                    $( this ).dialog( "close" );
                }
            }
        ]
    });

}