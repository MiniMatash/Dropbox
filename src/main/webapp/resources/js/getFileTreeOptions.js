function getFileTreeOptions() {
    $("<div class='options'>").appendTo($("#fileOptions"))
        .append(('<div class="ui-dialog createFolder" hidden><h3>Folder name</h3><input type="text" id="folderName">' +
        '<button onclick="createFolder()">Submit</button> '))
        .append(('<div class="optionElem"><button onclick="createFolderDialog()" title="Create folder"><img src="/images/treeOptions/createFolder.png"></button>'))
        .append(('<div class="ui-dialog uploadFile" hidden><h3>Upload File</h3><input type="file" id="fileName">' +
        '<button onclick="uploadFile()">Submit</button> '))
        .append(('<div class="optionElem"><button onclick="uploadFileDialog()" title="Create folder"><img src="/images/treeOptions/addFile.png"></button>'))

}

function createFolderDialog() {
    $( ".createFolder" ).dialog()
}

function uploadFileDialog() {
    $( ".uploadFile" ).dialog()
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
    jQuery.each(jQuery('#file')[0].files, function(i, file) {
        data.append('file-'+i, file);
    });
    $.ajax({
        url:"/uploadFile",
        method:"POST",
        data:{file:data, currentLocation:window.location.pathname},
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
