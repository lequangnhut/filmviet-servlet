package Controll.Service.Impl;

import java.util.List;

import Controll.DTO.VideoLikedInfo;
import Controll.Dao.StatsDao;
import Controll.Dao.Impl.StatsDaoImpl;
import Controll.Service.StatsService;

public class StatsServiceImpl implements StatsService {

    private StatsDao dao;

    public StatsServiceImpl() {
        dao = new StatsDaoImpl();
    }

    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        return dao.findVideoLikeInfo();
    }

}
