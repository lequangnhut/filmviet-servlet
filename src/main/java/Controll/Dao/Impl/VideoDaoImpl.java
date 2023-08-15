package Controll.Dao.Impl;

import java.util.List;

import Controll.Dao.AbstactDao;
import Controll.Dao.VideoDao;
import Controll.Entity.Video;

public class VideoDaoImpl extends AbstactDao<Video> implements VideoDao {

	@Override
	public Video findById(Integer id) {
		return super.findById(Video.class, id);
	}

	@Override
	public Video findByHref(String href) {
		String sql = "SELECT o FROM Video o WHERE o.href = ?0";
		return super.findOne(Video.class, sql, href);
	}

	@Override
	public List<Video> findAll() {
		return super.findAll(Video.class, true);
	}

	@Override
	public List<Video> findAllVideoDelete() {
		return super.findAllVideoDelete(Video.class, true);
	}

	@Override
	public List<Video> findByName(String name) {
		String sql = "SELECT o FROM Video o WHERE o.title LIKE ?0";
		return super.findMany(Video.class, sql, "%" + name + "%");
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		return super.findAll(Video.class, true, pageNumber, pageSize);
	}

	@Override
	public List<Video> findAllVideoDelete(int pageNumber, int pageSize) {
		return super.findAllVideoDelete(Video.class, true, pageNumber, pageSize);
	}

	@Override
	public List<Video> findVideoTrending() {
		String jpql = "SELECT o FROM Video o WHERE o.isActive = 1 ORDER BY o.addDate DESC";
		return super.findManyMaxResult(Video.class, jpql, 4);
	}

}
