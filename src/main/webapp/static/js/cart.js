$(document).on("click", ".plus", function () {
    let item_id = $(this).data("id");
    let params = {
        action: "plus",
        id: item_id,
    };
    $.post("/cart", $.param(params), function(response) {
        // optional response
    });
});

$(document).on("click", ".minus", function () {
    let item_id = $(this).data("id");
    let params = {
        action: "minus",
        id: item_id,
    };
    $.post("/cart", $.param(params), function(response) {
        // optional response
    });
});

$(document).on("click", ".remove", function () {
    let item_id = $(this).data("id");
    let params = {
        action: "remove",
        id: item_id,
    };
    $.post("/cart", $.param(params), function(response) {
        // optional response
    });
});



