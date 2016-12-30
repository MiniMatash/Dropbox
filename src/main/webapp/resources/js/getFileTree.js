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
                    tr = $('<tr class="elem" ondblclick="doubleClickTreeWalker(\'' + link + '\')">').appendTo(table);
                } else {
                    tr = $('<tr class="elem">').appendTo(table);
                }

                $(tr).append('<td><img src="/images/files/' + files[file].type + '.png">');
                if (files[file].type == "folder") {
                    $(tr).append($('<td style="text-align: center"><a id="path" href="' + window.location.pathname + "/" + files[file].name + '"><h3 style="display: inline-block;">' + files[file].name + '</h3></a>'));
                } else {
                    $(tr).append($('<td style="text-align: center"><h3 id="fileName">' + files[file].name + '</h3>'));
                }
                $(tr).append($('<td style="text-align: center"><h3>' + files[file].modificationDate + '</h3>'));
                $(tr).append($('<td style="text-align: center"><h3>' + "--" + '</h3>'));
            }
            $(".elem").on("click", function () {
                $(".elem").removeAttr("id");
                $(this).attr("id", "selected");
                $('#fileTreeOptions').empty()
                    .append($('<button id="deleteButton" onclick="deleteElement()">Delete</button>'))
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
