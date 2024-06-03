package model;

import dao.CategoryDAO;
import dao.ReviewDAO;

public class Product {
    private int id;
    private String name;
    private String image;
    private int cid;
    private double price;
    private String description;
    private double weight;
    private String origin;
    private String quality;
    private boolean passed;
    private int quantity;

    public Product(String name, String image, int cid, double price, String description, double weight, String origin, String quality, boolean isPassed, int quantity) {
        this.name = name;
        this.image = image;
        this.cid = cid;
        this.price = price;
        this.description = description;
        this.weight = weight;
        this.origin = origin;
        this.quality = quality;
        this.passed = isPassed;
        this.quantity = quantity;
    }

    public Product() {
    }

    public Product(int id, String name, String image, int cid, double price, String description, double weight, String origin, String quality, boolean passed, int quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.cid = cid;
        this.price = price;
        this.description = description;
        this.weight = weight;
        this.origin = origin;
        this.quality = quality;
        this.passed = passed;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", image=" + image + ", cid=" + cid + ", price=" + price + ", description=" + description + ", weight=" + weight + ", origin=" + origin + ", quality=" + quality + ", passed=" + passed + ", quantity=" + quantity + '}';
    }
    
    public Category getCategory(){
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.findById(cid);
    } 
    
    public int getRating(){
        ReviewDAO reviewDAO = new ReviewDAO();
        return reviewDAO.getRatingByProductId(this.id);
    }
}


