package Controll.Dao.Impl;

import java.util.ArrayList;
import java.util.List;

import Controll.DTO.VideoLikedInfo;
import Controll.Dao.AbstactDao;
import Controll.Dao.StatsDao;

public class StatsDaoImpl extends AbstactDao<Object[]> implements StatsDao {

	@Override
	public List<VideoLikedInfo> findVideoLikeInfo() {
		String sql = "SELECT v.title, v.href, v.daodien, v.isActive, SUM(CAST(h.isLiked AS INT)) AS totalLike FROM dbo.video v"
				+ " JOIN dbo.history h ON h.videoId = v.id WHERE v.isActive = 1 GROUP BY v.title, v.href, v.daodien, v.isActive"
				+ " ORDER BY SUM(CAST(h.isLiked AS INT)) DESC";
		List<Object[]> objects = super.findManyByNativeQuery(sql);
		List<VideoLikedInfo> result = new ArrayList<>();

		objects.forEach(object -> {
			VideoLikedInfo likeInfo = setDataVideoLikedInfo(object);
			result.add(likeInfo);
		});
		return result;
	}

	private VideoLikedInfo setDataVideoLikedInfo(Object[] object) {
		VideoLikedInfo likeInfo = new VideoLikedInfo();
		likeInfo.setTitle((String) object[0]);
		likeInfo.setHref((String) object[1]);
		likeInfo.setDaodien((String) object[2]);
		likeInfo.setTotalLike(object[4] == null ? 0 : (Integer) object[4]);
		likeInfo.setIsActive((Boolean) object[3]);
		return likeInfo;
	}

}
