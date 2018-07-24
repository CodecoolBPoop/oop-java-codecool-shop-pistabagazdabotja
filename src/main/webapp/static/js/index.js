$(document).on("click", ".addtocart", function () {
    let item_id = $(this).data("id");
    let params = {
        id: item_id,
    };
    $.post("addtocart", $.param(params), function(response) {
        // response
    });
});