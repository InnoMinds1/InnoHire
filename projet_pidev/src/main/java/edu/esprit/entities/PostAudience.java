package edu.esprit.entities;

public enum PostAudience {
    PUBLIC(0,"PUBLIC","/img/ic_public.png"),
    FRIENDS(1,"FRIENDS","/img/ic_friend.png");

    private int id;
    private String name;
    private String imgSrc;

    PostAudience(int id, String name, String imgSrc) {
        this.id = id;
        this.name = name;
        this.imgSrc = imgSrc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
