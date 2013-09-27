$(document).ready(function(){
    var currentID = null;

    // Close all popover except current.
    $('.popover-link').click(function(){
        $('.popover-link').not(this).popover('hide');
    });

    //
    $('.pop').popover({
        html : true, // Active format HTML.
        content: function() {
            currentID = $(this).attr('id'); // Take the ID of current link.
            $('.rating-input > .glyphicon').css('color','#73DCFF'); // Add color CSS.
            return $('.pop_content[data-container='+currentID+']'); // Return the current content.
        }
    });

    // On close : replace the content to div ".pop_display".
    $('.pop').on('hidden.bs.popover', function () {
        $('.pop_content[data-container='+currentID+']').appendTo('.pop_display[data-container='+currentID+']');
    });

    // Close popover active if click somewhere else.
    $(':not(#anything)').on('click', function (e) {
        $('.popover-link').each(function () {
            if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.pop').has(e.target).length === 0) {
                $(this).popover('hide');
                return;
            }
        });
    });
});