// ページ読み込み時
$(document).ready(function() {

  // グローバルナビ　アクティブ
  // ページurlを"/"で区切った時の文字列を取得
  var url = location.pathname.split("/")[1];
  var param = location.search;
  var navList = $("#active-nav a");
  var page =

  // 問題管理
  navList.each(function(){
    // aタグに設定したurlで"/"で区切った１番目の文字列を取得
    var href = $(this).attr("href").split("/")[1];
    console.log(href); //add
    console.log(url); //
    console.log(param); //null

    // 変数hrefと変数urlの中身が一致していれば、aタグにクラスactiveをつける
    if (href == url) {
      $("#collapse1").addClass("show");
      $(this).addClass("active-sidebar-subbtn");
      console.log(href);
    } else {
      if (href == "" && param != null) {
        $("#collapse1").addClass("show");
        $(this).addClass("active-sidebar-subbtn");
      }
    }
  });
});