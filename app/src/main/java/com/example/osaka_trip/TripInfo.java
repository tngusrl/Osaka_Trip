package com.example.osaka_trip;

import java.io.Serializable;

public class TripInfo implements Serializable {

    int idx;
    String title; // 이름
    String area; // 지역 (오사카, 나라)
    String classify; // 분류 (여행정보(관광지, 음식점, 유명음식, 축제, 쇼핑리스트), 여행 팁)
    String image; // 식당 외부사진
    String image1; // 음식 사진
    String exp; // 설명
    String link; // 구글지도 url
    String favorite; // 즐겨찾기 여부

    public TripInfo(int idx, String title, String area, String classify, String image, String image1, String exp, String link, String favorite) {
        this.idx = idx;
        this.title = title;
        this.area = area;
        this.classify = classify;
        this.image = image;
        this.image1 = image1;
        this.exp = exp;
        this.link = link;
        this.favorite = favorite;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image = image1;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
