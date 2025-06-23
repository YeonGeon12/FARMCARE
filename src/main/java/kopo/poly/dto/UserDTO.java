package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserDTO {

    private String userId;

    private String email;

    private String password;

    private String regId;
    private String regDt;
    private String chgId;
    private String chgDt;

    private String existsYn;    //중복가입 방지

    private int authNumber; //인증번호

    private String redirectUrl;

}
