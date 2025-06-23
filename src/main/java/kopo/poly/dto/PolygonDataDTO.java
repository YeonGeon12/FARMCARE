package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class PolygonDataDTO {
    private int id;
    private String polygonName;


    private String coordinates; // JSON 형식으로 좌표 저장
    private String createdAt; // 생성 일자
    private String centroid; //중심점
    private String pnu;  //pnu코드
}
