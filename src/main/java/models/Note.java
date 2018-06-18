package models;

import java.util.Objects;

public class Note {
    private int id;
    private String conent;
    private long postedAt;
    private int releaseId;

    public Note(String conent, int releaseId) {
        this.conent = conent;
        this.releaseId = releaseId;
        this.postedAt = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public long getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(long postedAt) {
        this.postedAt = postedAt;
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
        Note note = (Note) o;
        return id == note.id &&
                postedAt == note.postedAt &&
                releaseId == note.releaseId &&
                Objects.equals(conent, note.conent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, conent, postedAt, releaseId);
    }
}
