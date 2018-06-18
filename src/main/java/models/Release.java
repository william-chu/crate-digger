package models;

import java.util.Date;
import java.util.Objects;

public class Release {
    private int id;
    private String title;
    private String label;
    private String labelNumber;
    private int mediaCondition;
    private String sleeveType;
    private int sleeveCondition;
    private String seller;
    private String mediaType;
    private int price;
    private String datePurchased;
    private boolean isInCollection;
    private  String imageUrl;

    public Release(String title, String label, String labelNumber, int mediaCondition, String sleeveType, int sleeveCondition, String seller, String mediaType, int price, String datePurchased, boolean isInCollection, String imageUrl) {
        this.title = title;
        this.label = label;
        this.labelNumber = labelNumber;
        this.mediaCondition = mediaCondition;
        this.sleeveType = sleeveType;
        this.sleeveCondition = sleeveCondition;
        this.seller = seller;
        this.mediaType = mediaType;
        this.price = price;
        this.datePurchased = datePurchased;
        this.isInCollection = isInCollection;
        this.imageUrl = imageUrl;
    }

//    public boolean getIsInCollection() {
//        return isInCollection;
//    }

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelNumber() {
        return labelNumber;
    }

    public void setLabelNumber(String labelNumber) {
        this.labelNumber = labelNumber;
    }

    public int getMediaCondition() {
        return mediaCondition;
    }

    public void setMediaCondition(int mediaCondition) {
        this.mediaCondition = mediaCondition;
    }

    public String getSleeveType() {
        return sleeveType;
    }

    public void setSleeveType(String sleeveType) {
        this.sleeveType = sleeveType;
    }

    public int getSleeveCondition() {
        return sleeveCondition;
    }

    public void setSleeveCondition(int sleeveCOndition) {
        this.sleeveCondition = sleeveCOndition;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public boolean isInCollection() {
        return isInCollection;
    }

    public void setInCollection(boolean inCollection) {
        isInCollection = inCollection;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Release release = (Release) o;
        return id == release.id &&
                mediaCondition == release.mediaCondition &&
                sleeveCondition == release.sleeveCondition &&
                price == release.price &&
                isInCollection == release.isInCollection &&
                Objects.equals(title, release.title) &&
                Objects.equals(label, release.label) &&
                Objects.equals(labelNumber, release.labelNumber) &&
                Objects.equals(sleeveType, release.sleeveType) &&
                Objects.equals(seller, release.seller) &&
                Objects.equals(mediaType, release.mediaType) &&
                Objects.equals(datePurchased, release.datePurchased) &&
                Objects.equals(imageUrl, release.imageUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, label, labelNumber, mediaCondition, sleeveType, sleeveCondition, seller, mediaType, price, datePurchased, isInCollection, imageUrl);
    }
}
