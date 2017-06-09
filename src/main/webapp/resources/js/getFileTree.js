function getFileTree() {
    $("#tableBody").empty();
    $.ajax({
        url: "/getFileTree",
        method: "GET",
        data: {path: window.location.pathname},
        success: function (files) {
            var table = $("<table style='width:100%;' id='tableBody'>").appendTo($("#filesBody"));
            $(table).append($('<thead><tr><th width="10%"><h3>Image</h3></th><th width="40%"><h3>Name</h3></th>' +
                '<th width="30%"><h3>Changed date</h3></th><th width="20%"><h3>Authorization level</h3></th></tr></thead>'));
            $(table).append($('<tbody>'));
            if (window.location.pathname != "/home")
                $('<tr class="upper" id=" " style="height: 40px;" ondragover="allowDrop(event)" ondrop="drop(event)" ondblclick="doubleClickTreeWalker(\'' +
                    window.location.href.substring(0, window.location.href.lastIndexOf("/")) + '\')">').appendTo(table)
                    .append('<td colspan="4"><a href="' + window.location.href.substring(0, window.location.href.lastIndexOf("/")) + '"><h3>...</h3></a>');
            for (var file in files) {
                var tr;
                var link = window.location.href + '/' + files[file].name;
                if (files[file].type == "folder") {
                    tr = $('<tr class="elem" draggable="true" id="' + files[file].name + '" ondragstart="drag(event)" ondragover="allowDrop(event)" ondrop="drop(event)" ondblclick="doubleClickTreeWalker(\'' + link + '\')">').appendTo(table);
                    displayInfo(tr, files[file]);
                }
            }
            for (var file in files) {
                var tr;
                var link = window.location.href + '/' + files[file].name;
                if (files[file].type != "folder") {
                    tr = $('<tr class="elem" draggable="true" id="' + files[file].name + '" ondragstart="drag(event)" ondragover="allowDrop(event)" ondrop="drop(event)" ondblclick="doubleClickTreeWalker(\'' + link + '\')">').appendTo(table);
                    displayInfo(tr, files[file]);
                }
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


function displayInfo(tr,file){
    $(tr).append('<td class="ui-icon-image"><img class="ui-icon-image" src="/images/files/' + file.type + '.png"/>');
    if (file.type == "folder") {
        $(tr).append($('<td><a class="path" href="' + window.location.pathname + "/" + file.name + '"><h3 class="folderName">' + file.name + '</h3></a>'));
    } else {
        $(tr).append($('<td><h3 class="fileName" onclick="OpenFile(this)">' + file.name + '</h3>'));
    }
    $(tr).append($('<td><h3 class="modificationDate">' + file.modificationDate + '</h3>'));
    $(tr).append($('<td><h3 class="authorizationLevel">' + "--" + '</h3>'));
}

function drag(ev) {
    var fileName = ev.target;
    while(true){
        if((fileName.id!="")&&(fileName.id!=undefined)){
            break;
        }else{
            fileName=fileName.parentElement;
        }
    }
    ev.dataTransfer.setData("fileName", fileName.id);
    ev.dataTransfer.setDragImage(ev.target.getElementsByClassName("ui-icon-image")[0].firstChild.cloneNode(false), 0, 0);
}

function drop(ev) {
    var destination = ev.target;
    while(true){
        if((destination.id!=undefined)&&(destination.id!="")){
            break;
        }else{
            destination=destination.parentElement;
        }
    }
    $.ajax({
        url: "/moveElement",
        method: "POST",
        data: {
            currentPath: window.location.pathname,
            fileName: ev.dataTransfer.getData("fileName"),
            destination: destination.id
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

