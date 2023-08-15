package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.ShareDao;
import Controll.Dao.Impl.ShareDaoImpl;
import Controll.Entity.Share;
import Controll.Entity.User;
import Controll.Entity.Video;
import Controll.Service.ShareService;

public class ShareServiceImpl implements ShareService {

	private ShareDao dao;

	public ShareServiceImpl() {
		dao = new ShareDaoImpl();
	}

	@Override
	public List<Share> findByUser(String email) {
		return dao.findByUser(email);
	}

	@Override
	public Share findByUserIdAndVideoId(Integer userId, Integer videoId) {
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public Share create(User user, Video video, String email) {
		Share existShare = findByUserIdAndVideoId(user.getId(), video.getId());
		if (existShare == null) {
			existShare = new Share();
			existShare.setUser(user);
			existShare.setVideo(video);
			existShare.setEmail(email);
			existShare.setShareDate(new Timestamp(System.currentTimeMillis()));
			dao.create(existShare);
		}
		return existShare;
	}

}
