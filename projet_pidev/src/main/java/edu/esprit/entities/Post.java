package edu.esprit.entities;


public class Post {

    private int id_post ;
    private Utilisateur utilisateur;
    private PostAudience audience;
    private String date;
    private String caption;
    private String image;
    private int totalReactions;
    private int nbComments;
    private int nbShares;

    public Post() {
        this.totalReactions=0;
         this.nbComments=0;
         this.nbShares=0;
    }


    public Post(PostAudience audience, String caption,String image) {
        this.audience = audience;
        this.caption = caption;
        this.image=image;

    }

    public Post(int id_post , Utilisateur utilisateur, PostAudience audience, String date, String caption, String image, int totalReactions, int nbComments, int nbShares) {
        this.id_post=id_post;
        this.utilisateur = utilisateur;
        this.audience = audience;
        this.date = date;
        this.caption = caption;
        this.image = image;
        this.totalReactions = totalReactions;
        this.nbComments = nbComments;
        this.nbShares = nbShares;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public PostAudience getAudience() {
        return audience;
    }

    public void setAudience(PostAudience audience) {
        this.audience = audience;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTotalReactions() {
        return totalReactions;
    }

    public void setTotalReactions(int totalReactions) {
        this.totalReactions = totalReactions;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    public int getNbShares() {
        return nbShares;
    }

    public void setNbShares(int nbShares) {
        this.nbShares = nbShares;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id_post=" + id_post +
                ", utilisateur=" + utilisateur +
                ", audience=" + audience +
                ", date='" + date + '\'' +
                ", caption='" + caption + '\'' +
                ", image='" + image + '\'' +
                ", totalReactions=" + totalReactions +
                ", nbComments=" + nbComments +
                ", nbShares=" + nbShares +
                '}';
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id_post == post.id_post;
    }
}
