<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>이메일 인증</title>

  <!-- Custom fonts for this template-->
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link
          href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="/css/sb-admin-2.min.css" rel="stylesheet">
  <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript">

    let emailAuthNumber = sessionStorage.getItem('authNumber');  // 저장된 인증번호 가져오기

    $(document).ready(function () {

      $("#userId").val(sessionStorage.getItem('userId'));
      $("#email").val(sessionStorage.getItem('email'));

      $("#bthlogin").on("click",function () { // 버튼 클릭했을때, 발생되는 이벤트 생성함(onclick 이벤트와 동일함)
        location.href = "/title/login";
      })

      //번호 확인후 회원가입
      $("#bthSend").on("click",function (){ // 버튼 클릭했을때, 발생되는 이벤트 생성함(onclick 이벤트와 동일함)
        doSubmit();
      })

      $("#bthResend").on("click",function (){ // 버튼 클릭했을때, 이메일 재발송 이벤트
        doSubmit2();
      })
    });
    const f = document.getElementById("f");
    const enteredAuthNumber = f.authNumber.value;

    function doSubmit2() {
      $.ajax({
        url: "/title/resendEmail2",
        type: "post",   //  전송방식은 Post
        dataType: "json",   // 전송 결과는 JSON으로 받기
        data: {
          userId: sessionStorage.getItem('userId'),
          email: sessionStorage.getItem('email')
        },
        success: function (json) {  // 호출이 성공했다면..
          alert("이메일 인증번호가 재발송 완료되었습니다.");
          sessionStorage.setItem('authNumber', json.authNumber);
        }
      });
    }

    function doSubmit() {
      const f = document.getElementById("f");
      const enteredAuthNumber = f.authNumber.value;

      const savedAuthNumber = sessionStorage.getItem('authNumber');

      if (enteredAuthNumber === "") {
        alert("이메일 인증번호를 입력하세요.");
        f.authNumber.focus();
        return;
      }

      if (enteredAuthNumber !== savedAuthNumber) {
        alert("이메일 인증번호가 일치하지 않습니다.");
        f.authNumber.focus();
        return;

      }

      f.method = "post"; // 비밀번호 찾기 정보 전송 방식
      f.action = "/title/forgotPasswordProc2" // 비밀번호 찾기 URL

      f.submit(); // 비밀번호 찾기 정보 전송하기
    }

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
      max-width: 600px; /* 최대 너비 설정 */
      width: 100%; /* 반응형 너비 설정 */
      padding: 20px;
    }

    .form-control-user {
      height: calc(1.5em + .75rem + 2px); /* 세로 높이 조정 */
      width: calc(100%); /* 가로 길이 조정, 비율 유지 */
      margin: 0 auto; /* 가운데 정렬 */
    }

    .btn-user {
      width: calc(50%); /* 버튼의 가로 길이를 입력 필드와 동일하게 조정 */
      height: calc(3.0em + .75rem + 2px); /* 세로 높이 조정 */
      margin: 0 auto; /* 가운데 정렬 */
      display: flex; /* flexbox 사용 */
      justify-content: center; /* 수평 중앙 정렬 */
      align-items: center; /* 수직 중앙 정렬 */
    }

    .form-group {
      margin-bottom: 0.75rem; /* 세로 공간 조정 */
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
      <div class="row" style="justify-content: center;">
          <div class="p-5" style="margin-top: 10px">
            <div class="text-center">
              <h1 class="h4 text-gray-900 mb-4">이메일 인증</h1>
              <p class="mb-4">메일로 보내드린 인증 코드를 입력하세요.</p>
            </div>
            <form id="f" class="user">
              <!-- Verification Code Input -->
              <div class="form-group">
                <input type="text" name="authNumber" class="form-control form-control-user" id="authNumber"
                        placeholder="전송된 코드를 입력하세요">
                <input type="hidden" name="userId" id="userId" value="">
                <input type="hidden" name="email" id="email" value="">
              </div>
              <!-- Submit Code -->
              <button id="bthSend" type="button" class="btn btn-primary btn-user btn-block">이메일 인증</button>
              <hr>
              <!-- Resend Verification Code -->
              <button id="bthResend" type="button" class="btn btn-secondary btn-user btn-block" onclick="resendCode()">
                인증번호 재발송
              </button>
              <!-- Success Message -->
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

<!-- Bootstrap core JavaScript-->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/js/sb-admin-2.min.js"></script>

<script>
  // Function to show resend confirmation message
  function resendCode() {
    // Simulate resending the code (you would send a request to your server here)
    // Show success message
    document.getElementById('resendMessage').style.display = 'block';

    // Optionally, disable the resend button to prevent multiple clicks
    document.getElementById('1resendButton').disabled = true;

    // After a few seconds, re-enable the resend button if needed
    setTimeout(() => {
      document.getElementById('resendButton').disabled = false;
    }, 5000);  // Re-enable after 5 seconds
  }
</script>

</body>

</html>