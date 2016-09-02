function getFileTreeOptions() {
    $("<div class='options'>").appendTo($("#filesBody"))
        .append(('<div class="ui-dialog createFolder" hidden><h3>Folder name</h3><input type="text" id="folderName">' +
        '<button onclick="createFolder()">Submit</button> '))
        .append(('<div><button onclick="createFolderDialog()" title="Create folder"><img src="/images/treeOptions/createFolder.png"></button>'));
}

function createFolderDialog() {
    $( ".createFolder" ).dialog()
}

function createFolder() {
    var value = JSON.stringify($("#folderName").val());
    $.ajax({
        url:"/createFolder",
        method:"POST",
        data:{folderName:value},
        dataType:"json",
        success: function () {
            $( ".createFolder" ).dialog( "close" );
        }
    })
}