package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class MailDTO {

    String toMail;  // 받는 사람
    String title;   // 보내는 메일 제목
    String contents;    // 보내는 메일 내용

    String seq; // 번호
    String fromMail;    // 보내는 사람
    String sandDt; // 날짜

}
