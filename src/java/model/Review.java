
package model;

public class Review {
    private int id;
    private int productId;
    private int userId;
    private int reviewText;
    private int rating;

    public Review() {
    }

    public Review(int id, int productId, int userId, int reviewText, int rating) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReviewText() {
        return reviewText;
    }

    public void setReviewText(int reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", productId=" + productId + ", userId=" + userId + ", reviewText=" + reviewText + ", rating=" + rating + '}';
    }
    
}
