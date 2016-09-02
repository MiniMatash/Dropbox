function logout(){
    $.ajax({
        url:"/logout",
        method:"GET",
        success:function () {
            window.location="/";
        }
    })
}