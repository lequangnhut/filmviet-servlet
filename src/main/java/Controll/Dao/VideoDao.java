package Controll.Dao;

import java.util.List;

import Controll.Entity.Video;

public interface VideoDao {

	Video findById(Integer id);

	Video findByHref(String href);

	List<Video> findAll();

	List<Video> findAllVideoDelete();

	List<Video> findByName(String name);

	List<Video> findAll(int pageNumber, int pageSize);

	List<Video> findAllVideoDelete(int pageNumber, int pageSize);

	List<Video> findVideoTrending();

	Video create(Video entity);

	Video update(Video entity);

	Video delete(Video entity);

}
