function getFileTree() {
    $.ajax({
        url: "/getFileTree",
        method: "GET",
        data:{path:window.location.pathname},
        success: function (files) {
            var table = $("<table id='tableBody'>").appendTo($("#filesBody"));
            $(table).append($('<tr><th width="10%"></th><th width="20%">Name</th><th width="15%">Changed date</th><th width="10%">Authorization level</th></tr>'));
            if(window.location.pathname!="/home")
                $("<tr>").appendTo(table)
                    .append('<span><a href="'+window.location.href.substring(0,window.location.href.lastIndexOf("/"))+'">...</a></span>');
            for (var file in files) {
                $("<tr>").appendTo(table)
                    .append('<td><img src="/images/files/'+files[file].type+'.png">')
                    .append('<td style="text-align: center">' + files[file].name + '')
                    .append('<td>' + files[file].modificationDate + '')
                    .append('<td>' + "--" + '');
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    })
}