package myapplication90.khloud.example.com.myapplication_demo2;

import io.realm.RealmObject;


public class Movi extends RealmObject{

    private Long id;
    private String poster_path;
    private String overview;
    private String title;
    private String release_date;

    public String getBackimage_path() {
        return backimage_path;
    }

    public void setBackimage_path(String backimage_path) {
        this.backimage_path = backimage_path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


    public Movi(){}



    public Movi(String poster_path, String overview, String release_date, String title, String backdrop_path, double vote_average) {
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.title = title;
        this.backimage_path = backdrop_path;
        this.vote_average = vote_average;
    }
    public Movi(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public Movi(Long id, String poster_path, String title) {
        this.poster_path = poster_path;
        this.title = title;
        this.id = id;
    }




    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTrailername() {
        return trailername;
    }

    public void setTrailername(String trailername) {
        this.trailername = trailername;
    }

    public String getTrailerkey() {
        return trailerkey;
    }

    public void setTrailerkey(String trailerkey) {
        this.trailerkey = trailerkey;
    }

    private String backimage_path;
    private double vote_average;
    private String author;
    private String content;
    private String trailername;
    private String trailerkey;


}
