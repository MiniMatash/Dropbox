function getFileTreeOptions() {
    $("<div class='options'>").appendTo($("#fileOptions"))
        .append(('<div class="ui-dialog createFolder" hidden><h3>Folder name</h3><input type="text" id="folderName">' +
        '<button onclick="createFolder()">Submit</button> '))
        .append(('<div class="optionElem"><button onclick="createFolderDialog()" title="Create folder">' +
        '<img src="/images/treeOptions/createFolder.png"></button>'))
        .append(('<div class="ui-dialog uploadFile" hidden><form method="POST" enctype="multipart/form-data" action="/uploadFile">' +
        '<h3>Upload file</h3><input type="file" id="file" name="myFile"><input type="hidden" id="pathFile" value="'+window.location.pathname+'">' +
        '<button onclick="uploadFile()">Submit</button></form> '))
        .append(('<div class="optionElem"><button onclick="uploadFileDialog()" title="Upload file"><img src="/images/treeOptions/addFile.png">' +
        '</button>'))

}

function createFolderDialog() {
    $( ".createFolder" ).dialog({
        modal: true
    })
}

function uploadFileDialog() {
    $( ".uploadFile" ).dialog({
        modal: true
    })
}

function createFolder() {
    $.ajax({
        url:"/createFolder",
        method:"POST",
        data:{folderName:$("#folderName").val(), currentLocation:window.location.pathname},
        success: function (result) {
            if(result=="success") {
                $(".createFolder").dialog("close");
                getFileTree();
            }else {
                $('<h3 style="color:red">'+result+'</h3>').appendTo($(".createFolder"));
            }
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
        url:"/uploadFile",
        data:data,
        processData: false,
        contentType: false,
        method:"POST",
        success: function (result) {
            if(result=="success") {
                $(".uploadFile").dialog("close");
                getFileTree();
            }else {
                $('<h3 style="color:red">'+result+'</h3>').appendTo($(".uploadFile"));
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    })

}
