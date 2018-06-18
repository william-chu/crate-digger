package models;

import java.util.Objects;

public class Track {
    private int id;
    private String title;
    private int releaseId;

    public Track(String title, int releaseId) {
        this.title = title;
        this.releaseId = releaseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id &&
                releaseId == track.releaseId &&
                Objects.equals(title, track.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, releaseId);
    }
}
