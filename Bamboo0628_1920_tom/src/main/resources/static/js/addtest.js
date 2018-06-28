$(document).ready(function() {
  $(".typeId").change(function() {
    $(this).parents(".problem").find(".choiceSpace").toggle();
    $(this).parents(".problem").find(".descriptionSpace").toggle();
  });
});
