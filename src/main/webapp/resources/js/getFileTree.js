function getFileTree() {
    $.ajax({
        url: "/getFileTree",
        method: "GET",
        data: {path: window.location.pathname},
        success: function (files) {
            var table = $("<table id='tableBody'>").appendTo($("#filesBody"));
            $(table).append($('<tr><th width="10%"></th><th width="20%">Name</th><th width="15%">Changed date</th><th width="10%">Authorization level</th></tr>'));
            if (window.location.pathname != "/home")
                $("<tr>").appendTo(table)
                    .append('<span><a href="' + window.location.href.substring(0, window.location.href.lastIndexOf("/")) + '">...</a></span>');
            for (var file in files) {
                var tr = $("<tr>").appendTo(table)
                $(tr).append('<td><img src="/images/files/' + files[file].type + '.png">');
                if (files[file].type == "folder") {
                    $(tr).append($('<td style="text-align: center"><a href="' + window.location.pathname + "/" + files[file].name + '">' + files[file].name + '</a>'));
                } else {
                    $(tr).append($('<td style="text-align: center">' + files[file].name + ''));
                }
                $(tr).append($('<td>' + files[file].modificationDate + ''));
                $(tr).append($('<td>' + "--" + ''));
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    })
}