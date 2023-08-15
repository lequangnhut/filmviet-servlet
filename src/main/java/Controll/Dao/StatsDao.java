package Controll.Dao;

import java.util.List;

import Controll.DTO.VideoLikedInfo;

public interface StatsDao {

	List<VideoLikedInfo> findVideoLikeInfo();
}
