$(document).ready(function () {
    $('.favorite-form').on('submit', function (event) {
        event.preventDefault(); 
        
        const form = $(this);
        const button = form.find('button');
        
        $.ajax({
            url: form.attr('action'),
            type: 'POST',
            data: form.serialize(),
            success: function (response) {
                if (button.hasClass('btn-info')) {
                    button.removeClass('btn-info').addClass('btn-warning');
                    button.text('Remove from Favorites');
                } else if (button.hasClass('btn-warning')) {
                    button.removeClass('btn-warning').addClass('btn-info');
                    button.text('Add to Favorites');
                }
            },
            error: function () {
                alert('Something went wrong!');  
            }
        });
    });
});



window.onscroll = function() {
    const button = document.getElementById("backToTop");
    if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        button.style.display = "block";
    } else {
        button.style.display = "none";
    }
};


document.getElementById("backToTop").onclick = function() {
        document.body.scrollTop = 0; 
        document.documentElement.scrollTop = 0; 
};
