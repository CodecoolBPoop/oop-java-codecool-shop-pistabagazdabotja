$(document).on("click", ".addtocart", function () {
    let item_id = $(this).data("id");
    let params = {
        id: item_id,
    };
    $.post("/", $.param(params), function(response) {
        // optional response
    });
});