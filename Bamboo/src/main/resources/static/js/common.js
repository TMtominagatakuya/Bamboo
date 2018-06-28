// ページ読み込み時
$(document).ready(function() {

  // グローバルナビ　アクティブ
  // ページurlを"/"で区切った時の文字列を取得
  // var url = location.pathname.split("/")[1];
  // var url2 = location.pathname.split("/")[2];
  var url = location.pathname;

  var navList1 = $("#active-nav1 a");
  var navList2 = $("#active-nav2 a");
  var navList3 = $("#active-nav3 a");

  var test = "test";
  var testlist = "testlist";
  var addtest = ~url.indexOf("addtest");
  console.log(addtest);

  var user = "user";
  var userslist = "userslist";
  var adduser = ~url.indexOf("adduser");

  // 問題管理
  navList1.each(function(){
    // var href = $(this).attr("href").split("/")[1];
    var href = $(this).attr("href");

    if (~url.indexOf(href)) {
      $("#collapse1").addClass("show");
      $(this).addClass("active-sidebar-subbtn");
    } else {
      if (~url.indexOf(test) && ~href.indexOf(testlist) && !addtest) {
        $("#collapse1").addClass("show");
        $(this).addClass("active-sidebar-subbtn");
      }
    }
  });

  // 入社前課題
  navList2.each(function(){
    // var href = $(this).attr("href").split("/")[1];
    // var href2 = $(this).attr("href").split("/")[2];
    var href = $(this).attr("href");

    if (~url.indexOf(href)) {
      $("#collapse2").addClass("show");
      $(this).addClass("active-sidebar-subbtn");
    }
  });

  // ユーザ管理
  navList3.each(function(){
    // var href = $(this).attr("href").split("/")[1];
    var href = $(this).attr("href");

    if (~url.indexOf(href)) {
      $("#collapse1").addClass("show");
      $(this).addClass("active-sidebar-subbtn");
    } else {
      if (~url.indexOf(user) && ~href.indexOf(userslist) && !adduser) {
        $("#collapse1").addClass("show");
        $(this).addClass("active-sidebar-subbtn");
      }
    }
  });
});
