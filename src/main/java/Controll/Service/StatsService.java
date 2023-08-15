package Controll.Service;

import java.util.List;

import Controll.DTO.VideoLikedInfo;

public interface StatsService {
	List<VideoLikedInfo> findVideoLikedInfo();
}
