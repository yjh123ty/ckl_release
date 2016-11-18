package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.TrackCoordInfo;
import tech.youmu.ckl.app.vo.TrackDetailInfo;
import tech.youmu.ckl.app.vo.TrackMonthInfo;
import tech.youmu.ckl.domain.Track;
import tech.youmu.ckl.domain.TrackCoord;

public interface TrackMapper extends BaseMapper{


    TrackDetailInfo getTrackDetailInfo(@Param("trackId")Long trackId,@Param("userRouteId")Long userRouteId);

    List<TrackMonthInfo> findTrackMonthInfo(Long userId);

    void bacthSaveTrackSuit(@Param("suitNames")List<String> suitNames, @Param("trackId")Long trackId);

    Track getByUserRouteId(Long userRouteId);

    void saveTrack(Track track);

    void bacthSaveTrackCoord(@Param("trackCoords")List<TrackCoord> trackCoords, @Param("trackId")Long trackId);



}