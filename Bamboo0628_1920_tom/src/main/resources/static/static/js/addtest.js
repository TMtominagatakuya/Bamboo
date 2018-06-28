$(document).ready(function() {
  $(".type_id").change(function() {
    $(this).parents(".problem").find(".choiceSpace").toggle();
    $(this).parents(".problem").find(".descriptionSpace").toggle();
  });
});
