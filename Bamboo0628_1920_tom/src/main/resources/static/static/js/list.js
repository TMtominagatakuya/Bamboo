// ページ読み込み時
$(document).ready(function() {
  // カテゴリー
  var java = "Java";
  var javascript = "Javascript";
  var sql = "SQL";

  // ステータス
  var answered = "解答済";
  var resubmit = "再提出";
  var done = "完了";

  // リストに表示されたカテゴリーの色
  $(".cl-category").each(function() {
    category = $(this).text();
    if (category == java) {
      $(this).addClass("cl-category-java");
    } else if (category == javascript) {
      $(this).addClass("cl-category-javascript");
    } else if (category == sql) {
      $(this).addClass("cl-category-sql");
    } else {
      $(this).addClass("cl-category-other");
    }
  });

  // リストに表示されたステータスの色
  $(".cl-status").each(function() {
    status = $(this).text();
    console.log(status);
    if (status == answered) {
      $(this).addClass("cl-status-answered");
    } else if (status == resubmit) {
      $(this).addClass("cl-status-resubmit");
    } else if (status == done) {
      $(this).addClass("cl-status-done");
    } else {
      $(this).addClass("cl-status-unanswered");
    }
  });

});
