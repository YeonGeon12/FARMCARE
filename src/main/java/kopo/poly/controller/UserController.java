package kopo.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kopo.poly.dto.MsgDTO;
import kopo.poly.dto.UserDTO;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequestMapping(value = "/title")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final IUserService userService;

    @GetMapping(value = "start")
    public String start() {
        log.info("{}.start Start!",this.getClass().getName());
        log.info("{}.start End!",this.getClass().getName());
        return "/title/start";
    }

    @GetMapping(value = "login")
    public String login() {
        log.info("{}.login Start!",this.getClass().getName());
        log.info("{}.login End!",this.getClass().getName());
        return "/title/login";
    }

    // 회원가입 화면 이동
    @GetMapping(value = "register")
    public String register() {
        log.info("{}.register Start!",this.getClass().getName());
        log.info("{}.register End!",this.getClass().getName());
        return "/title/register";
    }

    // 이메일 인증화면 이동 (임시)
    @GetMapping(value = "register2")
    public String register2() {
        log.info("{}.register2 Start!",this.getClass().getName());
        log.info("{}.register2 End!",this.getClass().getName());
        return "title/register2";
    }

    // 비밀번호 변경을 위한 이메일 인증화면 이동 (임시)
    @GetMapping(value = "forgotPassword2")
    public String forgotPassword2() {
        log.info("{}.register2 Start!",this.getClass().getName());
        log.info("{}.register2 End!",this.getClass().getName());
        return "title/forgotPassword2";
    }
    // 회원정보 들어가기전 비밀번호 확인
    @GetMapping(value = "profile1")
    public String profile1() {
        log.info("{}.profile1 Start!",this.getClass().getName());
        log.info("{}.profile1 End!",this.getClass().getName());
        return "title/profile1";
    }

    // 회원정보
    @GetMapping(value = "profile")
    public String profile() {
        log.info("{}.profile Start!",this.getClass().getName());
        log.info("{}.profile End!",this.getClass().getName());
        return "title/profile";
    }

    // 회원정보에서 비밀번호 변경
    @GetMapping(value = "profilePasswordChange")
    public String profilePasswordChange() {
        log.info("{}.profilePasswordChange Start!",this.getClass().getName());
        log.info("{}.profilePasswordChange End!",this.getClass().getName());
        return "title/profilePasswordChange";
    }

    // 회원정보애서 이메일 변경
    @GetMapping(value = "profileEmailChange")
    public String profileEmailChange() {
        log.info("{}.profileEmailChange Start!",this.getClass().getName());
        log.info("{}.profileEmailChange End!",this.getClass().getName());
        return "title/profileEmailChange";
    }
    // 회원정보애서 탈퇴전 한번더 입력
    @GetMapping(value = "profileDelete1")
    public String profileDelete1() {
        log.info("{}.profileDelete1 Start!",this.getClass().getName());
        log.info("{}.profileDelete1 End!",this.getClass().getName());
        return "title/profileDelete1";
    }
    // 회원정보애서 탈퇴 확인
    @GetMapping(value = "profileDelete")
    public String profileDelete() {
        log.info("{}.profileDelete Start!",this.getClass().getName());
        log.info("{}.profileDelete End!",this.getClass().getName());
        return "title/profileDelete";
    }


    // 이메일 재발송
    @ResponseBody
    @PostMapping(value = "resendEmail")
    public UserDTO resendEmail(HttpServletRequest request) throws Exception {

        log.info("{}.resendEmailExists Start!", this.getClass().getName());

        String email = CmmUtil.nvl(request.getParameter("email"));  // 회원아이디

        log.info("email : {}", email);

        UserDTO pDTO = new UserDTO();
        pDTO.setEmail(EncryptUtil.encAES128CBC(email));

        // 입력된 이메일이 중복된 이메일인지 조회
        UserDTO rDTO = Optional.ofNullable(userService.resendEmail(pDTO)).orElseGet(UserDTO::new);

        log.info("{}.resendEmailExists End!", this.getClass().getName());

        return rDTO;
    }

    // 비밀번호 변경을 위한 이메일 재발송
    @ResponseBody
    @PostMapping(value = "resendEmail2")
    public UserDTO resendEmail2(HttpServletRequest request) throws Exception {

        log.info("{}.resendEmail2Exists Start!", this.getClass().getName());

        String email = CmmUtil.nvl(request.getParameter("email"));  // 이메일

        log.info("email : {}", email);

        UserDTO pDTO = new UserDTO();
        pDTO.setEmail(EncryptUtil.encAES128CBC(email)); // 암호화

        // 입력된 이메일이 중복된 이메일인지 조회
        UserDTO rDTO = Optional.ofNullable(userService.resendEmail2(pDTO)).orElseGet(UserDTO::new);

        log.info("{}.resendEmail2Exists End!", this.getClass().getName());

        return rDTO;
    }


    // 아이디 중복체크
    @ResponseBody
    @PostMapping(value = "getUserIDExists")
    public UserDTO getUserIDExists(HttpServletRequest request) throws Exception {

        log.info("{}.getUserIdExists Start!",this.getClass().getName());

        String userId = CmmUtil.nvl(request.getParameter("userId")); // 회원아이디

        log.info("userId : {}", userId);

        UserDTO pDTO = new UserDTO();
        pDTO.setUserId(userId);

        // 회원아이디를 통해 중복된 아이디인지 조회
        UserDTO rDTO = Optional.ofNullable(userService.getUserIDExists(pDTO)).orElseGet(UserDTO::new);

        log.info("{}.getUserIdExists End!", this.getClass().getName());

        return rDTO;
    }

    // 비밀번호 찾기를 위한 아이디 중복체크 2

    @ResponseBody
    @PostMapping(value = "getUserIDExists2")
    public UserDTO getUserIDExists2(HttpServletRequest request) throws Exception {

        log.info("{}.getUserIdExists2 Start!",this.getClass().getName());

        String userId = CmmUtil.nvl(request.getParameter("userId")); // 회원아이디

        log.info("userId : {}", userId);

        UserDTO pDTO = new UserDTO();
        pDTO.setUserId(userId);


        // 회원아이디를 통해 중복된 아이디인지 조회
        UserDTO rDTO = Optional.ofNullable(userService.getUserIDExists2(pDTO)).orElseGet(UserDTO::new);

        String encryptedEmail = CmmUtil.nvl(rDTO.getEmail()); // 암호화된 이메일
        String decryptedEmail = EncryptUtil.decAES128CBC(encryptedEmail); // 복호화
        rDTO.setEmail(decryptedEmail);

        log.info("userId : {}, email : {}", userId, decryptedEmail);
        log.info("{}.getUserIdExists2 End!", this.getClass().getName());

        return rDTO;
    }

    // 이메일 중복 조회
    @ResponseBody
    @PostMapping(value = "getUserEmailExists")
    public UserDTO getUserEmailExists(HttpServletRequest request) throws Exception {

        log.info("{}.getUserEmailExists Start!", this.getClass().getName());

        String email = CmmUtil.nvl(request.getParameter("email"));  // 회원아이디

        log.info("email : {}", email);

        UserDTO pDTO = new UserDTO();
        pDTO.setEmail(EncryptUtil.encAES128CBC(email));

        // 입력된 이메일이 중복된 이메일인지 조회
        UserDTO rDTO = Optional.ofNullable(userService.getUserEmailExists(pDTO)).orElseGet(UserDTO::new);

        log.info("{}.getUserEmailExists End!", this.getClass().getName());

        return rDTO;
    }

    // 로그인 상태에서 Email 변경
    @PostMapping(value = "/updateEmail")
    @ResponseBody
    public Map<String, Object> updateEmail(HttpServletRequest request, HttpSession session) throws Exception {
        log.info("{}.updateEmail Start!", this.getClass().getName());

        // 세션과 요청에서 사용자 ID와 새로운 이메일 가져오기
        String userId = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
        String newEmail = CmmUtil.nvl(request.getParameter("newEmail"));

        log.info("userId : {}", userId);
        log.info("newEmail : {}", newEmail);

        // DTO 생성 및 값 설정
        UserDTO pDTO = new UserDTO();
        pDTO.setUserId(userId);
        pDTO.setEmail(EncryptUtil.encAES128CBC(newEmail)); // 이메일 암호화

        // 이메일 업데이트 실행
        int res = userService.newEmailProc(pDTO);

        // 결과 메시지와 DTO를 담을 Map 생성
        Map<String, Object> result = new HashMap<>();
        result.put("user", pDTO);
        result.put("msg", (res > 0) ? "Email이 성공적으로 업데이트되었습니다." : "Email 업데이트에 실패하였습니다.");

        log.info("{}.updateEmail End!", this.getClass().getName());

        // Map 반환
        return result;
    }

    // 회원가입
    @ResponseBody
    @PostMapping(value = "insertUser")
    public MsgDTO insertUser(HttpServletRequest request) {

        log.info("{}.insertUser start!", this.getClass().getName());

        int res=0;   // 회원가입 결과
        String msg = "";    // 회원가입 결과에 대한 메시지를 전달할 변수
        MsgDTO dto; // 결과 메시지 구조

        // 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserDTO pDTO;

        try {
            /*
                웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작!

                무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
             */
            String userId = CmmUtil.nvl(request.getParameter("userId"));    // 아이디
            String password = CmmUtil.nvl(request.getParameter("password"));    // 비밀번호
            String email = CmmUtil.nvl(request.getParameter("email"));  // 이메일

            /*
                웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 끝!!

                무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
             */

            /*
                반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
                반드시 작성할 것
             */
            log.info("userId : "+ userId);
            log.info("password : "+ password);
            log.info("email : "+ email);
            //log.info("addr1 : "+ addr1);
            //log.info("addr2 : "+ addr2);

            /*
                웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 시작!!

                무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
             */

            //웹
            pDTO = new UserDTO();

            pDTO.setUserId(userId);

            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            pDTO.setPassword(EncryptUtil.encHashSHA256(password));

            // 민감 정보인 이메일은 AES128-CBC로 암호화함
            pDTO.setEmail(EncryptUtil.encAES128CBC(email));

            /*
                웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 끝!!


                무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
             */

            /*
                회원가입
             */
            res = userService.insertUserInfo(pDTO);

            log.info("회원가입 결과(res) : "+ res);

            if (res == 1) {
                msg = "회원가입되었습니다.";
            } else if (res == 2) {
                msg = "이미 가입된 아이디입니다.";
            } else {
                msg = "오류로 인해 회원가입이 실패하였습니다.";
            }
        } catch (Exception e) {
            // 저저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실해하였습니다. : " +e;
            log.info(e.toString());

        } finally {
            // 결과 메시지 전달하기
            dto = new MsgDTO();
            dto.setResult(res);
            dto.setMsg(msg);

            log.info("{}.insertUser End!", this.getClass().getName());
        }

        return dto;
    }

    //비밀번호 찾기 화면
    @GetMapping(value = "forgotPassword" )
    public String forgotPassword(HttpSession session) {
        log.info("{}.forgotPassword Start!",this.getClass().getName());

        session.setAttribute("NEW_PASSWORD","");
        session.removeAttribute("NEW_PASSWORD");

        log.info("{}.forgotPassword End!",this.getClass().getName());

        return "title/forgotPassword";

    }

    // 비밀번호 찾기 로직 수행
    @PostMapping(value = "forgotPasswordProc2")
    public String forgotPassword(HttpServletRequest request, ModelMap model,HttpSession session) throws Exception {
        log.info("{}.forgotPasswordProc2 Start!", this.getClass().getName());

        // 웹에서 받는 정보를 String 변수에 저장
        String userId = CmmUtil.nvl(request.getParameter("userId"));    // 아이디

        // 확인용 로그
        log.info("userId : {}",userId);

        UserDTO pDTO = new UserDTO();
        pDTO.setUserId(userId);

        // 비밀번호 찾기 가능한지 확인하기
        UserDTO rDTO = Optional.ofNullable(userService.searchUserIdOrPasswordProc2(pDTO)).orElseGet(UserDTO::new);

        model.addAttribute("rDTO",rDTO);

        // 비밀번호 재생성하는 화면은 보안을 위해 반드시 NEW_PASSWORD 세션이 존재해야 접속 가능하도록 구현
        // userId 값을 넣은 이유는 비밀번호 재설정하는 newPasswordProc 함수에서 사용하기 위함
        session.setAttribute("NEW_PASSWORD",userId);

        log.info("{}.forgotPasswordProc2 End!",this.getClass().getName());

        return "title/forgotPasswordChange";
    }

    /*
        비밀번호 찾기 로직 수행
        <p>
        아이디, 이름, 이메일 일치하면, 비밀번호 재발급 화면 이동
     */
    @PostMapping(value = "newPasswordProc")
    public String newPasswordProc(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        log.info("{}.user/newPasswordProc Start!", this.getClass().getName());

        String msg; //웹에 보여줄 메시지

        // 정상적인 접근인지 체크
        String newPassword = CmmUtil.nvl((String) session.getAttribute("NEW_PASSWORD"));

        if (!newPassword.isEmpty()) { // 정상 접근

            /*
                웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장!!

                무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
             */

            String password = CmmUtil.nvl(request.getParameter("password")); // 신규 비밀번호

            /*
                반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야한
                반드시 작성할 것
             */
            log.info("password : {}",password);

            /*
                웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기!!

                무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
             */

            UserDTO pDTO = new UserDTO();
            pDTO.setUserId(newPassword);
            pDTO.setPassword(EncryptUtil.encHashSHA256(password));

            userService.newPasswordProc(pDTO);

            // 비밀번호 재생성하는 화면은 보안을 위해 생성한 NEW_PASSWORD 세션 삭제
            session.setAttribute("NEW_PASSWORD","");
            session.removeAttribute("NEW_PASSWORD");

            msg = "비밀번호가 재설정되었습니다,";



        } else {    // 비정상 접근
            msg = "비정상 접근입니다.";

        }

        model.addAttribute("msg", msg);

        log.info("{}.user/newPasswordProc End!", this.getClass().getName());

        return "title/newPasswordResult2";
    }

    @ResponseBody
    @PostMapping(value = "newPasswordProc2")
    public MsgDTO newPasswordProc2(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        log.info("{}.user/newPasswordProc2 Start!", this.getClass().getName());

        int res = 0;
        String msg ="";
        MsgDTO dto;

        UserDTO pDTO;

        // 비밀번호 변경 처리
        try {
            String password = CmmUtil.nvl(request.getParameter("password"));

            // 세션에서 사용자 ID 가져오기 (예: 비밀번호 재설정 대상)
            String userId = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
            log.info("id : {}, password : {}", userId,password);

            pDTO = new UserDTO();
            pDTO.setUserId(userId);

            pDTO.setPassword(EncryptUtil.encHashSHA256(password));

            userService.newPasswordProc(pDTO); // 비밀번호 변경 처리

            res = 1;
            msg = "비밀번호가 재설정되었습니다.";
            log.info("Password reset success for userId: {}", userId);
        } catch (Exception e) {
            res = 2;
            msg = "시스템 문제로 비밀번호가 재설정에 실패했습니다.";
        } finally {

            dto = new MsgDTO();
            dto.setResult(res);
            dto.setMsg(msg);
        }


        log.info("{}.user/newPasswordProc2 End!", this.getClass().getName());

        return dto;  // 결과 페이지 반환
    }

    /*
       로그인 처리 및 결과 알려주는 화면으로 이동
    */
    @ResponseBody
    @PostMapping(value = "loginProc2")
    public MsgDTO loginProc2(HttpServletRequest request, HttpSession session) {

        log.info("{}.loginProc2 Start!",this.getClass().getName());

        int res = 0; // 로그인 처리 결과를 저장할 변수 (로그인 성공 : 1, 아이디 비밀번호 불일치로인한 실패 : 0, 시스템 에러 : 2)
        String msg =""; // 로그인 결과에 대한 메시지를 전달할 변수
        MsgDTO dto; // 결과 메시지 구조

        // 웹 (회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserDTO pDTO;

        try {
            String userId = CmmUtil.nvl(request.getParameter("userId")); // 아이디
            String password = CmmUtil.nvl(request.getParameter("password")); // 비밀번호

            log.info("userId : {} / password : {}", userId, password);

            // 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = new UserDTO();

            pDTO.setUserId(userId);

            // 비밀번호 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            pDTO.setPassword(EncryptUtil.encHashSHA256(password));

            // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 userInfoService 호출하기
            UserDTO rDTO = userService.getLogin(pDTO);

            /*
                로그인을 성공했다면, 회원아이디 정보를 session에 저장함

                세션을 톰켓(was)의 메모리에 존재하며, 웹사이트에 접속한 사람(연결된 객체)마다 메모리에 값을 올린다

                예) 톰켓에 100명의 사용자가 로그인했다면, 사용자 각각 회원아이디를 메모리에 저장하며
                메모리에 저장된 객체의 수는 100개이다.
                따라서 과도한 세션은 톰켓의 메모리 부하를 발생시켜 서버가 다운되는 현상이 있을 수 있기때문에,
                최소한으로 사용하는 것을 권장한다.

                스프링에서 세션을 사용하기 위해서는 함수명의 파라미터에 HttpSession session 존재해야 한다.
                세션은 톰켓의 메모리에 저장되기 때문에 url마다 전달하는게 필요하지 않고,
                그냥 메모리에서 부르면 되기 때문에 jsp, controller에서 쉽게 불러서 쓸 수 있다.
             */
            if (!CmmUtil.nvl(rDTO.getUserId()).isEmpty()) { // 로그인 성공

                res = 1;
                /*
                    세션에 회원가이디 저장하기, 추후 로그인여부를 체크하기 위해 세션에 값이 존재하는지 체크한다.
                    일반적으로 세션에 저장되는 키는 대문자로 입력하며, 앞에 SS를 붙인다.

                    Session 단어에서 SS를 가져온 것이다.
                 */
                msg = "로그인이 성공했습니다.";

                session.setAttribute("SS_USER_ID", userId);
                // session.setAttribute("SS_USER_NAME", CmmUtil.nvl(rDTO.getUserId()));

                session.setAttribute("SS_EMAIL", CmmUtil.nvl(rDTO.getEmail()));
                String encryptedEmail = (String) session.getAttribute("SS_EMAIL");
                String decryptedEmail = EncryptUtil.decAES128CBC(encryptedEmail);
                session.setAttribute("SS_DECRYPTED_EMAIL", decryptedEmail);

                session.setAttribute("SS_REG_DATE",CmmUtil.nvl(rDTO.getRegDt()));


            } else {
                msg = "아이디와 비밀번호가 올바르지 않습니다.";

            }
        } catch (Exception e) {
            //저장이 실패되면 사용자에게 보여줄 메시지
            msg = "시스템 문제로 로그인이 실패했습니다.";
            res = 2;
            log.info(e.toString());

        } finally {
            // 결과 메시지 전달하기
            dto = new MsgDTO();
            dto.setResult(res);
            dto.setMsg(msg);

            log.info("{}.loginProc2 End!",this.getClass().getName());
        }

        return dto;
    }
    /*
        로그인 성공 페이지 이동
     */
    @GetMapping(value = "index")
    public String loginSuccess() {
        log.info("{}.user/loginResult Start!", this.getClass().getName());

        log.info("{}.user/loginResult End!", this.getClass().getName());

        return "title/index";
    }
    @ResponseBody
    @PostMapping(value = "loginProc3")
    public MsgDTO loginProc3(HttpServletRequest request, HttpSession session) {

        log.info("{}.loginProc3 시작!", this.getClass().getName());

        int res = 0; // 로그인 처리 결과를 저장할 변수
        String msg = ""; // 로그인 결과 메시지
        MsgDTO dto; // 결과 메시지 구조

        try {
            // 비밀번호만 받기
            String password = CmmUtil.nvl(request.getParameter("password")); // 비밀번호

            log.info("비밀번호 : {}", password);

            // 기본 사용자 ID 설정 (예: "defaultUser")
            String userId = (String) session.getAttribute("SS_USER_ID"); // 로그인 성공 시 사용할 기본 사용자 ID

            // 비밀번호 해시화
            String hashedPassword = EncryptUtil.encHashSHA256(password);

            // 기본 사용자 ID와 비밀번호가 일치하는지 확인하기 위해 userService 호출
            UserDTO pDTO = new UserDTO();
            pDTO.setUserId(userId);
            pDTO.setPassword(hashedPassword);

            UserDTO rDTO = userService.getLoginProfile(pDTO);

            if (!CmmUtil.nvl(rDTO.getUserId()).isEmpty()) { // 로그인 성공

                res = 1;
                msg = "회원정보 인증 성공했습니다.";

                // 세션에 사용자 정보 저장
            } else {
                msg = "비밀번호가 올바르지 않습니다.";
            }
        } catch (Exception e) {
            // 오류 발생 시 메시지
            msg = "시스템 문제로 로그인이 실패했습니다.";
            res = 2;
            log.info(e.toString());

        } finally {
            // 결과 메시지 전달
            dto = new MsgDTO();
            dto.setResult(res);
            dto.setMsg(msg);

            log.info("{}.loginProc3 종료!", this.getClass().getName());
        }

        return dto;
    }

    // 로그아웃 설정
    @ResponseBody
    @PostMapping(value = "logout")
    public MsgDTO logout(HttpSession session) {
        log.info("{}.logout Start!", this.getClass().getName());

        MsgDTO dto = new MsgDTO();
        int res = 0;
        String msg = "";

        try {
            // 세션 무효화
            session.invalidate();
            res = 1;
            msg = "로그아웃에 성공했습니다.";
        } catch (Exception e) {
            res = 0;
            msg = "시스템 문제로 로그아웃에 실패했습니다.";
            log.error("Error during logout: {}", e.toString());
        } finally {
            dto.setResult(res);
            dto.setMsg(msg);
            log.info("{}.logout End!", this.getClass().getName());
        }

        return dto; // JSON 응답 반환
    }

    // 회원탈퇴
    @ResponseBody
    @PostMapping(value = "deleteUser")
    public MsgDTO deleteUser(HttpServletRequest request, HttpSession session) {

        log.info("{}.deleteUser Start!", this.getClass().getName());

        int res = 0; // 회원탈퇴 처리 결과 (성공: 1, 실패: 0, 시스템 에러: 2)
        String msg = ""; // 회원탈퇴 결과 메시지
        MsgDTO dto = new MsgDTO(); // 결과 메시지 구조체
        UserDTO pDTO = new UserDTO(); // 삭제할 사용자 정보 구조체

        try {
            // 요청에서 사용자 ID 가져오기

            String userId = (String) session.getAttribute("SS_USER_ID");

            log.info("userId : {}", userId);

            // 탈퇴할 사용자 정보 설정
            pDTO.setUserId(userId);

            // 회원탈퇴 처리를 위해 userService 호출
            int result = userService.deleteUserInfo(pDTO);

            if (result >0) { // 회원탈퇴 성공
                res = 1;
                msg = "회원 탈퇴가 완료되었습니다.";
                session.invalidate();
            } else { // 회원 정보가 없는 경우
                msg = "회원 정보가 존재하지 않습니다.";
            }

        } catch (Exception e) {
            // 시스템 문제 발생 시 메시지 설정
            msg = "시스템 문제로 회원 탈퇴에 실패했습니다.";
            res = 2;
            log.error("Error in deleteUser: {}", e.toString());

        } finally {
            // 결과 메시지 설정
            dto.setResult(res);
            dto.setMsg(msg);

            log.info("{}.deleteUser End!", this.getClass().getName());
        }

        return dto;
    }


}
