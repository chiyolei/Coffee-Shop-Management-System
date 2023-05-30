/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CoffeeShopManagementSystem;

/**
 *
 * @author ASUS
 */
public class salesInfo {
    
    private String date;
    private String orderNo;
    private int amount;

    public salesInfo() {
    }

    public salesInfo(String date, String orderNo, int amount) {
        this.date = date;
        this.orderNo = orderNo;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    
}
