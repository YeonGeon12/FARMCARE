package kopo.poly.mapper;

import kopo.poly.dto.PolygonDataDTO;
import kopo.poly.dto.SolDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPolygonDataMapper {

    //폴리곤 저장
    int insertPolygonData (PolygonDataDTO pDTO) throws Exception;

    //솔루션 저장
    int insertSolData (SolDTO pDTO) throws Exception;

    List<SolDTO> getSolList() throws Exception;

    SolDTO getSolInfo(SolDTO pDTO) throws Exception;

    //기록 삭제
    void deleteSolInfo(SolDTO pDTO) throws Exception;


}
