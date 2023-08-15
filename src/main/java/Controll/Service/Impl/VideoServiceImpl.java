package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.VideoDao;
import Controll.Dao.Impl.VideoDaoImpl;
import Controll.Entity.Video;
import Controll.Service.VideoService;

public class VideoServiceImpl implements VideoService {

	private VideoDao dao;

	public VideoServiceImpl() {
		dao = new VideoDaoImpl();
	}

	@Override
	public Video findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Video findByHref(String href) {
		return dao.findByHref(href);
	}

	@Override
	public List<Video> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Video> findAllVideoDelete() {
		return dao.findAllVideoDelete();
	}

	@Override
	public List<Video> findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public List<Video> findAllVideoDelete(int pageNumber, int pageSize) {
		return dao.findAllVideoDelete(pageNumber, pageSize);
	}

	@Override
	public List<Video> findVideoTrending() {
		return dao.findVideoTrending();
	}

	@Override
	public Video create(String title, String href, String poster, String daodien, String dienvien, String theloai,
			String mota, String rawPrice, String description) {
		Video videosHref = findByHref(href);
		if (videosHref == null) {
			videosHref = new Video();
			String cleanPrice = rawPrice.replace(".", "");
			int price = Integer.parseInt(cleanPrice);

			videosHref.setTitle(title);
			videosHref.setHref(href);
			videosHref.setPoster(poster);
			videosHref.setDaodien(daodien);
			videosHref.setDienvien(dienvien);
			videosHref.setTheloai(theloai);
			videosHref.setMota(mota);
			videosHref.setPrice(price);
			videosHref.setDescription(description);
			videosHref.setIsActive(Boolean.TRUE);
			videosHref.setViews(0);
			videosHref.setShares(0);
			videosHref.setAddDate(new Timestamp(System.currentTimeMillis()));
			return dao.create(videosHref);
		}
		return videosHref;
	}

	@Override
	public Video update(String title, String href, String daodien, String dienvien, String theloai, String mota,
			String rawPrice, String description) {
		Video videosHref = findByHref(href);

		int price = 0;
		if (rawPrice.contains(",")) {
			String cleanPrice = rawPrice.replace(",", "");
			price = Integer.parseInt(cleanPrice);
		} else {
			String cleanPrice = rawPrice.replace(".", "");
			price = Integer.parseInt(cleanPrice);
		}

		videosHref.setTitle(title);
		videosHref.setDaodien(daodien);
		videosHref.setDienvien(dienvien);
		videosHref.setTheloai(theloai);
		videosHref.setMota(mota);
		videosHref.setPrice(price);
		videosHref.setDescription(description);
		videosHref.setIsActive(Boolean.TRUE);
		return dao.update(videosHref);
	}

	@Override
	public Video updateDisabled(String title, String href, String daodien, String dienvien, String theloai, String mota,
			String rawPrice, String description) {
		Video videosHref = findByHref(href);

		int price = 0;
		if (rawPrice.contains(",")) {
			String cleanPrice = rawPrice.replace(",", "");
			price = Integer.parseInt(cleanPrice);
		} else {
			String cleanPrice = rawPrice.replace(".", "");
			price = Integer.parseInt(cleanPrice);
		}

		videosHref.setTitle(title);
		videosHref.setDaodien(daodien);
		videosHref.setDienvien(dienvien);
		videosHref.setTheloai(theloai);
		videosHref.setMota(mota);
		videosHref.setPrice(price);
		videosHref.setDescription(description);
		videosHref.setIsActive(Boolean.FALSE);
		return dao.update(videosHref);
	}

	@Override
	public Video delete(String href) {
		Video entity = findByHref(href);
		entity.setIsActive(Boolean.FALSE);
		return dao.update(entity);
	}

	@Override
	public Video RestoreVideo(String href) {
		Video video = findByHref(href);
		video.setIsActive(Boolean.TRUE);
		return dao.update(video);
	}

	@Override
	public Video DeleteVideoRestore(String href) {
		Video video = findByHref(href);
		return dao.delete(video);
	}

}
