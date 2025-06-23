<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String userId = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>회원 탈퇴</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">



        $(document).ready(function () {

            $("#bthSend").on("click", function () {
                doSubmit();
            });
            function doSubmit() {
                const f = document.getElementById("f");

                $.ajax({
                    url: "/title/deleteUser",
                    type: "post",
                    dataType: "json",
                    data: $("#f").serialize(),  // 세션에서 가져온 사용자 ID
                    success: function (json) {
                        if (json.result === 1) {   // 회원탈퇴 성공
                            alert(json.msg);   // 메시지 띄우기
                            location.href = "/title/login"; // 회원정보 페이지 이동

                        } else {   // 실패
                            alert(json.msg); // 메시지 띄우기
                            $("#bthSend").focus() // 항목에 마우스 커서 이동
                        }
                    }
                });
            }});

    </script>

    <style>
        body {
            background: url('/img/non-5.png') no-repeat bottom center/cover !important;
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            margin-top: 70px;
        }

        .card {
            background-color: rgba(255, 255, 255, 0.8) !important;
            position: relative;
            z-index: 1;
        }

        .logo-container {
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            width: 90px;
            height: 90px;
            border-radius: 50%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #28343f;
            z-index: 2;
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
            padding-top: 60px;

        }

        .container {
            max-width: 900px;
            width: 150%;
            padding: 20px;
        }

        .form-control-user {
            height: calc(1.5em + .75rem + 2px);
            width: calc(100%);
            margin: 0 auto;
            text-align: center;
            align-content: center;
        }

        .btn-user {
            width: calc(50%);
            height: calc(3.0em + .75rem + 2px);
            margin: 0 auto;
            display: flex;
            justify-content: center;
            align-items: center;
        }



        .form-container {
            width: 100%;

        }

    </style>
</head>
<body>
<div class="container">

    <div class="logo-container">
        <img src="/img/logo1roatate.png" alt="Logo">
    </div>

    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-12 d-flex justify-content-center align-items-center">
                            <div class="w-100 d-flex flex-column justify-content-center" style="max-width: 90%; padding: 5rem;">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">회원탈퇴</h1>
                                        <br>
                                        <p>탈퇴 시,&nbsp;<span>${SS_USER_ID}</span>&nbsp;님의 정보가 삭제됩니다.</p>
                                    </div>
                                    <hr>
                                    <form class="user form-container" id="f">
                                            <button id="bthSend" type="button" class="btn btn-primary btn-user btn-block">
                                                회원탈퇴
                                            </button>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>

<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<script src="/js/sb-admin-2.min.js"></script>

</body>
</html>