function calculateTotal() {
    let amounts = [];
    $(".amount").each(function () {
        amounts.push(Number($(this).html()));
    });

    let prices = [];
    $(".price").each(function () {
        prices.push(Number($(this).html()));
    });

    let total = 0;
    for (let i = 0; i < amounts.length; i++) total += amounts[i] * prices[i];
    $(".total").text(String(total));
}

$(document).ready(function () {
    calculateTotal();
});

$(document).on("click", ".plus", function () {
    let item_id = $(this).data("id");
    let params = {
        action: "plus",
        id: item_id,
    };
    $.post("/cart", $.param(params), function(response) {
        // optional response
    });

    let amount = Number($(this).siblings('.amount').text()) + 1;
    let price = Number($(this).parent().siblings('.name-price').children('.price').text());

    $(this).siblings('.amount').text(String(amount));
    $(this).siblings('.subtotal').text(String(amount * price));
    calculateTotal();
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

    let amount = Number($(this).siblings('.amount').text());
    let price = Number($(this).parent().siblings('.name-price').children('.price').text());

    if (amount > 1) amount--;
    $(this).siblings('.amount').text(String(amount));
    $(this).siblings('.subtotal').text(String(amount * price));
    calculateTotal();
});

$(document).on("click", ".remove", function (event) {
    event.preventDefault();
    let item_id = $(this).data("id");
    let params = {
        action: "remove",
        id: item_id,
    };
    $.post("/cart", $.param(params), function(response) {
        // optional response
    });
    $(this).closest(".product").remove();
    calculateTotal();
});



