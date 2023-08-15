package Controll.DTO;

public class VideoLikedInfo {

    private String title;
    private String daodien;
    private String href;
    private Boolean isActive;
    private int totalLike;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDaodien() {
        return daodien;
    }

    public void setDaodien(String daodien) {
        this.daodien = daodien;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

}
