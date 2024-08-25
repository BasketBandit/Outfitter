$(document).ready(function(){
    var outfitItemIds = [];
    $(".item").on('click', function(){
        var id = $(this).attr("id");
        var subcategory = $(this).attr("data-subcategory");
        if(!outfitItemIds.includes(id)) {
            $(this).addClass("item-selected");
            outfitItemIds.push(id);
            $("#outfit-item-"+subcategory).append($("#item-image-"+id).clone());
            if(outfitItemIds.length > 0) {
                $("#outfit-create").prop("disabled", false);
            }
        } else {
            outfitItemIds = outfitItemIds.filter(x => x !== id);
            $(this).removeClass("item-selected");
            $("#outfit-item-"+subcategory).find("#item-image-"+id).remove();
            if(outfitItemIds.length == 0) {
                $("#outfit-create").prop("disabled", true);
            }
        }
        $("#itemsInput").val(outfitItemIds);
    });
});