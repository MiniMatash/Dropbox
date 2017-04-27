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
                    .append('<td colspan="4"><a href="' + window.location.href.substring(0, window.location.href.lastIndexOf("/")) + '"><h3 style="display: inline-block;3">...</h3></a>');
            for (var file in files) {
                var tr;
                var link = window.location.href + '/' + files[file].name;
                if (files[file].type == "folder") {
                    tr = $('<tr class="elem" draggable="true" ondragstart="drag(event)" ondragover="allowDrop(event)" ondrop="drop(event)" ondblclick="doubleClickTreeWalker(\'' + link + '\')">').appendTo(table);
                } else {
                    tr = $('<tr class="elem" draggable="true" ondragstart="drag(event)">').appendTo(table);
                }

                $(tr).append('<td class="ui-icon-image"><img class="ui-icon-image" src="/images/files/' + files[file].type + '.png"/>');
                if (files[file].type == "folder") {
                    $(tr).append($('<td style="text-align: center"><a class="path" href="' + window.location.pathname + "/" + files[file].name + '"><h3 class="folderName" style="display: inline-block;">' + files[file].name + '</h3></a>'));
                } else {
                    $(tr).append($('<td style="text-align: center"><h3 class="fileName" onclick="OpenFile(this)">' + files[file].name + '</h3>'));
                }
                $(tr).append($('<td style="text-align: center"><h3 class="modificationDate">' + files[file].modificationDate + '</h3>'));
                $(tr).append($('<td style="text-align: center"><h3 class="authorizationLevel">' + "--" + '</h3>'));
            }
            $(".elem").on("click", function () {
                $(".elem").removeAttr("id");
                $(this).attr("id", "selected");
                if ($("#selected").find(".fileName")[0] != undefined)
                    $('#fileTreeOptions')
                        .empty()
                        .append($('<button class="downloadButton"><a href="/downloadFile' + window.location.pathname + '/'
                            + $("#selected").find(".fileName")[0].innerHTML + '" download="' + $("#selected").find(".fileName")[0].innerHTML
                            + '">Download</a></button>'))
                        .append($('<button id="deleteButton" onclick="deleteElement()">Delete</button>'));
                else
                    $('#fileTreeOptions')
                        .empty()
                        .append($('<button id="deleteButton" onclick="deleteElement()">Delete</button>'))
            });
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    })
}


function drag(ev) {
    ev.dataTransfer.setData("fileName", ev.target.getElementsByClassName("fileName")[0].innerHTML);
    ev.dataTransfer.setDragImage(ev.target.getElementsByClassName("ui-icon-image")[0].firstChild.cloneNode(false), 0, 0);
}

function drop(ev) {
    $.ajax({
        url: "/moveElement",
        method: "POST",
        data: {
            currentPath: window.location.pathname,
            fileName: ev.dataTransfer.getData("fileName"),
            destination: ev.target.innerHTML
        },
        success: function () {
            $('#filesBody').empty();
            $('#fileTreeOptions').empty();
            getFileTree();
        }
    });
}

function allowDrop(ev) {
    ev.preventDefault();
}

