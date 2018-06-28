// ページ読み込み時
$(document).ready(function() {

  // グローバルナビ　アクティブ
  // ページurlを"/"で区切った時の文字列を取得
  var url = location.pathname.split("/")[1];
  var url2 = location.pathname.split("/")[2];
  var navList1 = $("#active-nav1 a");
  var navList2 = $("#active-nav2 a");
  var navList3 = $("#active-nav3 a");
  var page = "page";
  var edittest = "EditTest";
  var deletetest = "DeleteTest";
  var category = "category"
  var user = "User";
  var result = $.isNumeric(url);

  // 問題管理
  navList1.each(function(){
    var href = $(this).attr("href").split("/")[1];

    if (href == url && href != "") {
      $("#collapse1").addClass("show");
      $(this).addClass("active-sidebar-subbtn");
      console.log(href);
    } else if ((!url.indexOf(page) && href == "") || (!url.indexOf(edittest) && href == "") || (!url.indexOf(deletetest) && href == "")) {
      $("#collapse1").addClass("show");
      $(this).addClass("active-sidebar-subbtn");
    } else {
      if (href == url || href == "" && result == true) {
        $("#collapse1").addClass("show");
        $(this).addClass("active-sidebar-subbtn");
      }
    }
  });

  // 入社前課題
  navList2.each(function(){
    var href = $(this).attr("href").split("/")[1];
    var href2 = $(this).attr("href").split("/")[2];

    if (href == url && href2 == url2) {
      $("#collapse2").addClass("show");
      $(this).addClass("active-sidebar-subbtn");
    } else {
      if (href == url && href2 == null && url2.indexOf(category)) {
        $("#collapse2").addClass("show");
        $(this).addClass("active-sidebar-subbtn");
      }
    }
  });

  // ユーザ管理
  navList3.each(function(){
    var href = $(this).attr("href").split("/")[1];

    if (href == url) {
      $("#collapse3").addClass("show");
      $(this).addClass("active-sidebar-subbtn");
    } else {
      if (!url.indexOf(user) && href == "UsersList" && url != "UserAdd") {
        $("#collapse3").addClass("show");
        $(this).addClass("active-sidebar-subbtn");
      }
    }
  });
});
