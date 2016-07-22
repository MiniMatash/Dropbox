function getFileTree(){
    $.ajax({
        URL: "/getFileTree",
        method: "GET",
        success: function (files) {
            var table = $("<table id='tableBody'>").appendTo($("#filesBody"));
            $(table).append($('<tr><th width="20%">Name</th><th width="15%">Changed date</th><th width="10%">Authorization level</th></tr>'));
            for(var file in files) {
                $("<tr>").appendTo(table)
                    .append(('<td>'+file.name+''))
                    .append(('<td>'+file.dateTime+''))
                    .append('<td>'+"--"+'');
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    })
}