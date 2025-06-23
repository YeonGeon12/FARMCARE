package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.UserDTO;
import kopo.poly.mapper.IUserMapper;
import kopo.poly.service.IMailService;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserMapper userMapper; // 회원 mapper 가져오기

    private final IMailService mailService; // 메일발송 객체 가져오기

    //아이디 중복체크
    @Override
    public UserDTO getUserIDExists(UserDTO pDTO) throws Exception {

        log.info("{}.getUserIdExists Start!", this.getClass().getName());

        // DB 아이디가 존재하는지 SQL 쿼리 실행
        UserDTO rDTO = userMapper.getUserIdExists(pDTO);

        log.info("{}.getUserIdExists End!", this.getClass().getName());

        return rDTO;

        }

    // 비밀번호 찾기용 아이디 체크
    @Override
    public UserDTO getUserIDExists2(UserDTO pDTO) throws Exception {
        log.info("{}.getUserIDExists2 Start!", this.getClass().getName());

        UserDTO rDTO = userMapper.getUserIdExists2(pDTO);

        log.info("rDTO : {}", rDTO);        //아이디 확인

        // 아이디가 중복되면 않는다면 메일 발송
        if (CmmUtil.nvl(rDTO.getExistsYn()).equals("Y")) {

            // 6 자리 랜덤 숫자 생성하기
            int authNumber = ThreadLocalRandom.current().nextInt(100000,999999);

            log.info("authNumber : {}", authNumber);

            // 인증번호 발송 로직
            MailDTO dto = new MailDTO();

            dto.setTitle("이메일 중복 확인 인증번호 발송 메일");
            dto.setContents("인증번호는 "+authNumber+" 입니다.");

            String encryptedEmail = CmmUtil.nvl(rDTO.getEmail()); // rDTO에서 이메일 주소 가져오기
            String decryptedEmail = EncryptUtil.decAES128CBC(encryptedEmail); // 복호화

            log.info("Email to send: {}", decryptedEmail); // 로그에 이메일 주소 출력
            dto.setToMail(decryptedEmail);


            mailService.doSendMail(dto);    // 이메일 발송

            dto = null;

            rDTO.setAuthNumber(authNumber); // 인증번호를 결과값에 넣어주기

        }

        log.info("{}.getUserIDExists2 End!",this.getClass().getName());

        return rDTO;
    }


    @Override   //이메일 중복체크
    public UserDTO getUserEmailExists(UserDTO pDTO) throws Exception {
        log.info("{}.getUserEmailExists Start!", this.getClass().getName());

        // DB 이메일이 존재하는지 SQL 쿼리 실행
        UserDTO rDTO = Optional.ofNullable(userMapper.getUserEmailExists(pDTO)).orElseGet(UserDTO::new);

        log.info("rDTO : {}", rDTO);

        // 이메일 주소가 중복되지 않는다면 메일 발송
        if (CmmUtil.nvl(rDTO.getExistsYn()).equals("N")) {

            // 6 자리 랜덤 숫자 생성하기
            int authNumber = ThreadLocalRandom.current().nextInt(100000,999999);

            log.info("authNumber : {}", authNumber);

            // 인증번호 발송 로직
            MailDTO dto = new MailDTO();

            dto.setTitle("이메일 중복 확인 인증번호 발송 메일");
            dto.setContents("인증번호는 "+authNumber+" 입니다.");
            dto.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

            mailService.doSendMail(dto);    // 이메일 발송

            dto = null;

            rDTO.setAuthNumber(authNumber); // 인증번호를 결과값에 넣어주기

        }

        log.info("{}.getUserEmailExists End!",this.getClass().getName());

        return rDTO;
    }

    // 이메일 재발송
    @Override
    public UserDTO resendEmail(UserDTO pDTO) throws Exception {
        log.info("{}.resendEmail Start!", this.getClass().getName());

        // DB 이메일이 존재하는지 SQL 쿼리 실행
        UserDTO rDTO = Optional.ofNullable(userMapper.getUserEmailExists(pDTO)).orElseGet(UserDTO::new);

        log.info("rDTO : {}", rDTO);

        // 이메일 주소가 중복되지 않는다면 메일 발송
        if (CmmUtil.nvl(rDTO.getExistsYn()).equals("N")) {

            // 6 자리 랜덤 숫자 생성하기
            int authNumber = ThreadLocalRandom.current().nextInt(100000,999999);

            log.info("authNumber : {}", authNumber);

            // 인증번호 발송 로직
            MailDTO dto = new MailDTO();

            dto.setTitle("이메일 중복 확인 인증번호 발송 메일");
            dto.setContents("인증번호는 "+authNumber+" 입니다.");
            dto.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

            mailService.doSendMail(dto);    // 이메일 발송

            dto = null;

            rDTO.setAuthNumber(authNumber); // 인증번호를 결과값에 넣어주기

        }

        log.info("{}.resendEmail End!",this.getClass().getName());

        return rDTO;
    }

    @Override
    public UserDTO resendEmail2(UserDTO pDTO) throws Exception {
        log.info("{}.resendEmail2 Start!", this.getClass().getName());

        // DB 이메일이 존재하는지 SQL 쿼리 실행
        UserDTO rDTO = Optional.ofNullable(userMapper.getUserEmailExists(pDTO)).orElseGet(UserDTO::new);

        log.info("rDTO : {}", rDTO);

        // 이메일 주소가 있으면
        if (CmmUtil.nvl(rDTO.getExistsYn()).equals("Y")) {

            // 6 자리 랜덤 숫자 생성하기
            int authNumber = ThreadLocalRandom.current().nextInt(100000,999999);

            log.info("authNumber : {}", authNumber);

            // 인증번호 발송 로직
            MailDTO dto = new MailDTO();

            dto.setTitle("이메일 중복 확인 인증번호 발송 메일");
            dto.setContents("인증번호는 "+authNumber+" 입니다.");
            dto.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

            mailService.doSendMail(dto);    // 이메일 발송

            dto = null;

            rDTO.setAuthNumber(authNumber); // 인증번호를 결과값에 넣어주기

        }

        log.info("{}.resendEmail2 End!",this.getClass().getName());

        return rDTO;

    }


    //회원가입하기
    @Override
    public int insertUserInfo(UserDTO pDTD) throws Exception {
        log.info("{}.insertUserInfo Start!", this.getClass().getName());

        // 회원가입 성공 : 1, 아이디 중복으로 인한 가입 취소 : 2, 기타 에러 발생 : 0
        int res;

        // 회원가입
        int success = userMapper.insertUserInfo(pDTD);

        // db에 데이터가 등록되었다면(회원가입 성공했다면 ....
        if(success > 0) {
            res = 1;

            /*
            메일 발송 로직 추가 시작!
             */
            MailDTO mDTO = new MailDTO();

            // 회원정보화면에서 입력받은 이메일 변수(아직 암호화되어 넘어오기 때문에 복호화 수행함)
            mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTD.getEmail())));

            mDTO.setTitle("회원가입을 축하드립니다."); // 제목

            // 메일 내용에 가입자 이름넣어서 내용 발송
            mDTO.setContents(CmmUtil.nvl(pDTD.getUserId()) + "님의 회원가입을 진심으로 축하드립니다.");

            // 회원 가입이 성공했기 때문에 메일을 발송함
            mailService.doSendMail(mDTO);

            /*
                메일 발송 로직 추가 끝
             */
        } else {
            res = 0;
        }

        log.info("{}.insertUserInfo End!",this.getClass().getName());

        return res;
    }

    // 비밀번호 변경
    @Override
    public int newPasswordProc(UserDTO pDTO) throws Exception {

        log.info("{}.newPasswordProc Start!",this.getClass().getName());

        // 비밀번호 재설정
        int success = userMapper.updatePassword(pDTO);

        log.info("{}.newPasswordProc End!", this.getClass().getName());

        return success;
    }

    // 이메일 변경
    @Override
    public int newEmailProc(UserDTO pDTO) throws Exception {

        log.info("{}.newEmailProc Start!",this.getClass().getName());

        // 이메일 재설정
        int success = userMapper.updateEmail(pDTO);

        log.info("{}.newEmailProc End!", this.getClass().getName());

        return success;
    }

    // 아이디 비밀번호 찾기 로직
    @Override
    public UserDTO searchUserIdOrPasswordProc2(UserDTO pDTO) throws Exception {

        log.info("{}.searchUserIdOrPasswordProc Start!",this.getClass().getName());

        UserDTO rDTO = userMapper.getUserId(pDTO);

        log.info("{}.searchUserIdOrPasswordProc End!",this.getClass().getName());

        return rDTO;

    }

    // 로그인
    @Override
    public UserDTO getLogin(UserDTO pDTO) throws Exception {

        log.info("{}.getLogin Start!",this.getClass().getName());

        // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
        // userInfoMapper.getUserLoginCheck(pDTO) 함수 실행 결과가 NULL 발생하면, UserInfoDTO 메모리에 올리기
        UserDTO rDTO = Optional.ofNullable(userMapper.getLogin(pDTO)).orElseGet(UserDTO::new);

        /*
            userInfoMapper로 부터 select 쿼리의 결과로 회원아이디를 받아왔다면, 로그인 성공!!

            DTO의 변수에 값이 있는지 확인하기 처리속도 측면에서 가장 좋은 방법은 변수의 길이를 가져오는 것이다.
            따라서 .length() 함수를 통해 회원아이디의 글자수를 가져와 0보다 큰지 비교한다.
            0보다 크다면, 글자가 존재하는 것이기 때문에 값이 존재한다.
         */

        if (!CmmUtil.nvl(rDTO.getUserId()).isEmpty())
        {

            MailDTO mDTO = new MailDTO();

            // 아이디, 패스워드 일치하는지 체크하는 쿼리에서 이메일 값 받아오기(아직 암호화되어 넘어오기 때문에 복호화 수행함)
            mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(rDTO.getEmail())));

            mDTO.setTitle("로그인 알림!");   //제목

            // 메일 내용에 가입자 이름넣어서 내용 발송
            mDTO.setContents(DateUtil.getDateTime("yyyy.MM.dd hh:mm:ss") + "에" +
                    CmmUtil.nvl(rDTO.getUserId()) + "님이 로그인하였습니다.");

            //회원 가입이 성공했기 때문에 메일을 발송함
            mailService.doSendMail(mDTO);
        }
        log.info("{}.getLogin End!",this.getClass().getName());

        return rDTO;
    }

    @Override
    public UserDTO getLoginProfile(UserDTO pDTO) throws Exception {
        log.info("{}.getLoginProfile Start!",this.getClass().getName());

        // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
        // userInfoMapper.getUserLoginCheck(pDTO) 함수 실행 결과가 NULL 발생하면, UserInfoDTO 메모리에 올리기
        UserDTO rDTO = Optional.ofNullable(userMapper.getLogin(pDTO)).orElseGet(UserDTO::new);

        /*
            userInfoMapper로 부터 select 쿼리의 결과로 회원아이디를 받아왔다면, 로그인 성공!!

            DTO의 변수에 값이 있는지 확인하기 처리속도 측면에서 가장 좋은 방법은 변수의 길이를 가져오는 것이다.
            따라서 .length() 함수를 통해 회원아이디의 글자수를 가져와 0보다 큰지 비교한다.
            0보다 크다면, 글자가 존재하는 것이기 때문에 값이 존재한다.
         */

        if (!CmmUtil.nvl(rDTO.getUserId()).isEmpty())
        {

            MailDTO mDTO = new MailDTO();

            // 아이디, 패스워드 일치하는지 체크하는 쿼리에서 이메일 값 받아오기(아직 암호화되어 넘어오기 때문에 복호화 수행함)
            mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(rDTO.getEmail())));


        }
        log.info("{}.getLoginProfile End!",this.getClass().getName());

        return rDTO;
    }

    // 회원탈퇴
    @Override
    public int deleteUserInfo(UserDTO pDTO) throws Exception {

        log.info("{}.deleteUserInfo Start!",this.getClass().getName());

        int result = userMapper.deleteUserInfo(pDTO);

        log.info("{}.deleteUserInfo End!",this.getClass().getName());

        return result;
    }
}
