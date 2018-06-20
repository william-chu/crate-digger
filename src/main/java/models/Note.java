package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Note {
    private int id;
    private String content;
    private long postedAt;
    private int releaseId;

    public Note(String content, int releaseId) {
        this.content = content;
        this.releaseId = releaseId;
        this.postedAt = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(long postedAt) {
        this.postedAt = postedAt;
    }

    public String getFormattedCreatedAt(){
        Date date = new Date(postedAt);
        String datePatternToUse = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        return sdf.format(date);
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
                Objects.equals(content, note.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, postedAt, releaseId);
    }
}
