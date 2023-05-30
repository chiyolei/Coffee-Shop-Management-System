/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CoffeeShopManagementSystem;

import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class menuTable {
    
    
    private String productid;
    private String productname;
    private ImageView productImage;
    private int productPrice;

    public menuTable() {
    }

    public menuTable(String productid, String productname, ImageView productImage, int productPrice) {
        this.productid = productid;
        this.productname = productname;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }
    
    

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public ImageView getProductImage() {
        return productImage;
    }

    public void setProductImage(ImageView productImage) {
        this.productImage = productImage;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    
    
}
