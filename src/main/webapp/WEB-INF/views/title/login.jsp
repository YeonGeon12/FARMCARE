<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>로그인</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">

        // HTML로딩이 완료되고, 실행됨
        $(document).ready(function () {

            // 회원가입
            $("#bthUserReg").on("click", function () {  // 버튼 클릭했을때, 발생되는 이벤트 생성함(onclick 이벤트와 동일함)
                location.href = "/title/register";
            })


            // 비밀번호 찾기
            $("#bthPassword").on("click", function () { // 버튼 클릭했을때, 발생되는 이벤트 생성함(onclick 이벤트와 동일함)
                location.href = "/title/forgotPassword";
            })

            // 로그인
            $("#bthLogin").on("click", function () {
                let f = document.getElementById("f"); // form 태그

                if (f.userId.value === "") {
                    alert("아이디를 입력하세요.");
                    f.userId.focus();
                    return;
                }

                if (f.password.value === "") {
                    alert("비밀번호를 입력하세요.");
                    f.password.focus();
                    return;
                }

                let userId = f.userId.value;
                localStorage.setItem("userId", userId); // localStorage에 저장
                // Ajax 호출해서 로그인하기
                $.ajax({
                        url: "/title/loginProc2",
                        type: "post",   // 전송방식은 Post
                        dataType: "json", // 전송 결과는 JSON으로 받기
                        data: $("#f").serialize(), // form 태그 내 input 등 객체를 자동으로 전송할 형태로 변경하기
                        success: function (json) { // /notice/noticeUpdate 호출이 성공했다면..

                            if (json.result === 1) {   // 로그인 성공
                                alert(json.msg);   // 메시지 띄우기
                                location.href = "/title/index"; // 로그인 성공 페이지 이동

                            } else {   // 로그인 실패
                                alert(json.msg); // 메시지 띄우기
                                $("#userId").focus() // 아이디 입력 항목에 마우스 커서 이동
                            }

                        }
                    }
                )
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

    <!-- Outer Row -->
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-12 d-flex justify-content-center align-items-center">
                            <div class="w-100 d-flex flex-column justify-content-center" style="max-width: 90%; padding: 5rem;">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">로그인</h1>
                                </div>
                                <form id="f" class="user">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" id="userId" name="userId" aria-describedby="IDHelp" placeholder="아이디">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user" id="password" name="password" placeholder="비밀번호">
                                    </div>
<%--                                    <div class="form-group">--%>
<%--                                        <div class="custom-control custom-checkbox small">--%>
<%--                                            <input type="checkbox" class="custom-control-input" id="customCheck">--%>
<%--                                            <label class="custom-control-label" for="customCheck">아이디 저장</label>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
                                    <a id="bthLogin" class="btn btn-primary btn-user btn-block" style="cursor: pointer">로그인</a>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" id="bthPassword" style="cursor: pointer">비밀번호 찾기</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" id="bthUserReg" style="cursor: pointer">회원가입</a>
                                </div>
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

<!-- Custo
m scripts for all pages-->
<script src="/js/sb-admin-2.min.js"></script>



</body>

</html>