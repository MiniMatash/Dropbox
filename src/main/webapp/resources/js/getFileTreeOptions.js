function getFileTreeOptions() {
    $("<options>").appendTo($("#fileOptions"))
        .append(('<div class="ui-dialog createFolder" hidden><h3>Folder name</h3><input type="text" id="folderName">' +
        '<button onclick="createFolder()">Submit</button> '))
        .append(('<div class="optionElem"><button onclick="openDialog(\'.createFolder\')" title="Create folder">' +
        '<img src="/images/treeOptions/createFolder.png"></button>'))
        .append(('<div class="ui-dialog uploadFile" hidden><form id="uploadFileForm" method="POST" enctype="multipart/form-data" action="/uploadFile">' +
        '<h3>Upload file</h3><input type="file" id="file" name="myFile"><input type="hidden" id="pathFile" value="' + window.location.pathname + '">' +
        '<input class="submit" type="submit" value="Submit"/></form>'))
        .append(('<div class="optionElem"><button onclick="openDialog(\'.uploadFile\')" title="Upload file"><img src="/images/treeOptions/addFile.png">' +
        '</button>'))
        .append(('<div class="deleteElement" hidden>Are you sure you want to delete it?</div>'));
    $('#uploadFileForm').submit(function () {
        uploadFile();
        return false;
    });
}

function openDialog(name) {
    $(name).dialog({
        modal: true
    })
}

function createFolder() {
    $.ajax({
        url: "/createFolder",
        method: "POST",
        data: {
            folderName: $("#folderName").val(),
            currentLocation: window.location.pathname
        },
        success: function (result) {
            reload(".createFolder", result);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    })
}


function uploadFile() {
    var data = new FormData();
    data.append("file", $("#file")[0].files[0]);
    data.append("path", $("#pathFile")[0].value);
    $.ajax({
        url: "/uploadFile",
        data: data,
        processData: false,
        contentType: false,
        method: "POST",
        success: function (result) {
            reload(".uploadFile", result.result);
        },
        error: function () {
            alert("File with name "+$("#file")[0].files[0].name+" already exist");
        }
    })

}

function deleteElement() {
    var path;
    if ($("#selected").find(".path").attr("href") != undefined)
        path = $("#selected").find(".path").attr("href");
    else
        path = window.location.pathname + "/" + $("#selected").find(".fileName")[0].innerHTML;
    $("deleteElement").removeAttr("hidden");
    $(".deleteElement").dialog({
        buttons: [
            {
                text: "Ok",
                click: function () {
                    $.ajax({
                        url: "/deleteElement",
                        method: "POST",
                        data: {currentLocation: path},
                        success: function (result) {
                            reload(".deleteElement", result);
                            $("#deleteButton").hide();
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            reload(".deleteElement", "error");
                        }
                    })
                }
            },
            {
                text: "Cancel",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
}

function reload(dialogClass, result) {
    if (result == "success") {
        $(dialogClass).dialog("close");
        $('#filesBody').empty();
        $('#fileTreeOptions').empty();
        getFileTree();
    } else {
        $('<h3 style="color:red">' + result + '</h3>').appendTo($(dialogClass));
    }
}

function OpenFile(value) {
    window.location = '/downloadFile' + window.location.pathname + '/'
        + value.innerHTML;
}

function doubleClickTreeWalker(a) {
    window.location.href = a;
}

