function getFileTreeOptions() {
    $("<div class='options'>").appendTo($("#fileOptions"))
        .append(('<div class="ui-dialog createFolder" hidden><h3>Folder name</h3><input type="text" id="folderName">' +
        '<button onclick="createFolder()">Submit</button> '))
        .append(('<div><button onclick="createFolderDialog()" title="Create folder"><img src="/images/treeOptions/createFolder.png"></button>'));
}

function createFolderDialog() {
    $( ".createFolder" ).dialog()
}

function createFolder() {
/*
    var value = JSON.stringify($("#folderName").val());
*/
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