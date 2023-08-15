package Controll.Service;

import java.util.List;

import Controll.Entity.Video;

public interface VideoService {

	Video findById(Integer id);

	Video findByHref(String href);

	List<Video> findAll();

	List<Video> findAllVideoDelete();

	List<Video> findByName(String name);

	List<Video> findVideoTrending();

	List<Video> findAll(int pageNumber, int pageSize);

	List<Video> findAllVideoDelete(int pageNumber, int pageSize);

	Video create(String title, String href, String poster, String daodien, String dienvien, String theloai, String mota,
			String price, String description);

	Video update(String title, String href, String daodien, String dienvien, String theloai, String mota, String price,
			String description);

	Video updateDisabled(String title, String href, String daodien, String dienvien, String theloai, String mota,
			String price, String description);

	Video RestoreVideo(String href);

	Video delete(String href);

	Video DeleteVideoRestore(String href);

}
