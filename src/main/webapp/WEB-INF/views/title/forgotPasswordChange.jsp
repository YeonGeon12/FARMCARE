<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.UserDTO" %>
<%
  UserDTO rDTO = (UserDTO) request.getAttribute("rDTO");

  // 비밀번호 재설정 접속 가능한지 체크
  String newPassword = CmmUtil.nvl((String) session.getAttribute("NEW_PASSWORD"));

  String msg ="";


  if ( newPassword.length() == 0) {   // 비정상적인 접근
    msg = "비정상적인 접근입니다. \n비밀번호 재설정 화면에 접근할 수 없습니다.";
  }

%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>비밀번호 변경</title>

  <!-- Custom fonts for this template-->
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link
          href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="/css/sb-admin-2.min.css" rel="stylesheet">
  <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript">

    <%
        //  비정상적인 접근 및 회원정보가 없는 경우 뒤로 가기
        if (msg.length()>0){
        %>
    alert("<%=msg%>")
    history.back();
    <%
    }
  %>

    // HTML로딩이 완료되고, 실행됨
    $(document).ready(function () {

      // 로그인 화면으로 이동
      $("#bthlogin").on("click",function () { // 버튼 클릭했을때, 발생되는 이벤트 생성함(onclick 이벤트와 동일함)
        location.href = "/title/login";
      })
      // 비밀번호 찾기
      $("#bthSearchPassword").on("click",function (event) {

        event.preventDefault(); // 기본 폼 제출을 막음
        let f = document.getElementById("f"); // form 태그

        if (f.password.value === "") {
          alert("새로운 비밀번호를 입력하세요.");
          f.password.focus();
          return false;
        }

        if (f.password2.value === "") {
          alert("검증을 위한 새로운 비밀번호를 입력하세요.");
          f.password2.focus();
          return false;
        }

        if (f.password.value !== f.password2.value) {
          alert("입력한 비밀번호가 일치하지 않습니다.");
          f.password.focus();
          return false;
        }

        f.method = "post"; // 비밀번호 찾기 정보 전송 방식
        f.action = "/title/newPasswordProc" // 비밀번호 찾기 URL

        f.submit(); // 아이디 찾기 정보 전송하기
      })
    })
  </script>

  <style>
    body {
      background: url('/img/non-5.png')no-repeat bottom center/cover; !important;
      position: relative;
      display: flex;
      justify-content: center;
      align-items: center;
      text-align: center;
      margin-top: 70px;
    }


    .card {
      background-color: rgba(255, 255, 255, 0.8) !important; /* 반투명 효과 */
      position: relative;
      z-index: 1; /* 카드가 로고 뒤에 오도록 설정 */
    }

    .logo-container {
      position: absolute;
      left: 50%;
      transform: translateX(-50%);
      width: 90px; /* 로고 크기 */
      height: 90px;
      border-radius: 50%;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      background-color: #28343f; /* 로고 배경 */
      z-index: 2; /* 로고가 카드 위에 오도록 설정 */
      align-items: center;
      justify-content: center;
    }

    .logo-container img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      object-fit: cover;
    }

    .card-body {
      padding-top: 60px; /* 카드 내용이 로고와 겹치지 않도록 패딩 추가 */
    }

    .container {
      max-width: 900px; /* 최대 너비 설정 */
      width: 100%; /* 반응형 너비 설정 */
      padding: 20px;
    }

    .form-control-user {
      height: calc(1.5em + .75rem + 2px); /* 세로 높이 조정 */
      width: calc(70%); /* 가로 길이 조정, 비율 유지 */
      margin: 0 auto; /* 가운데 정렬 */
      justify-content: center; /* 수평 중앙 정렬 */
      align-items: center; /* 수직 중앙 정렬 */
    }

    .btn-user {
      width: calc(30%); /* 버튼의 가로 길이를 입력 필드와 동일하게 조정 */
      height: calc(3.0em + .75rem + 2px); /* 세로 높이 조정 */
      margin: 0 auto; /* 가운데 정렬 */
      display: flex; /* flexbox 사용 */
      justify-content: center; /* 수평 중앙 정렬 */
      align-items: center; /* 수직 중앙 정렬 */
    }

    .form-group {
      margin-bottom: 0.75rem; /* 세로 공간 조정 */
      justify-content: center; /* 수평 중앙 정렬 */
      align-items: center; /* 수직 중앙 정렬 */
    }

  </style>

</head>

<body>

<div class="container">

  <!-- 로고를 카드 외부에 위치 -->
  <div class="logo-container">
    <img src="/img/logo1roatate.png" alt="Logo"> <!-- 로고 이미지 경로 설정 -->
  </div>




      <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
          <!-- Nested Row within Card Body -->

            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>

              <div class="p-5">

                <div class="text-center">
                  <h1 class="h4 text-gray-900 mb-4">비밀번호 재설정</h1>
                </div>
                <form id="f" class="user form-container">
                  <div class="form-group">
                    <input type="password" name="password" id="password" class="form-control form-control-user"
                            placeholder="새로운 비밀번호 입력">
                  </div>
                  <div class="form-group">
                    <input type="password" name="password2" id="password2" class="form-control form-control-user"
                            placeholder="비밀번호 재입력">
                  </div>
                  <button id="bthSearchPassword" class="btn btn-primary btn-user btn-block">
                    비밀번호 변경(초기화)
                  </button>
                </form>
                <hr>
                <div class="text-center">
                  <a class="small" id="bthlogin" style="cursor: pointer">로그인</a>
                </div>
              </div>
            </div>

        </div>
      </div>

    </div>

  </div>

</div>

<!-- Bootstrap core JavaScript-->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/js/sb-admin-2.min.js"></script>

<script>
  // Optional: Add form validation or password reset logic here
</script>

</body>

</html>