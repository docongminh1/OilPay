package com.example.oilpay;

import java.io.Serializable;

public class Oil implements Serializable{
    private int id;
    private int imageOil;
    private String nameOil;
    private int priceOil;
    private int quantity ;
    private int soLuong;

    public Oil(int id, int imageOil, String nameOil, int priceOil) {
        this.id = id;
        this.imageOil = imageOil;
        this.nameOil = nameOil;
        this.priceOil = priceOil;
    }

    public int getImageOil() {
        return imageOil;
    }

    public void setImageOil(int imageOil) {
        this.imageOil = imageOil;
    }

    public String getNameOil() {
        return nameOil;
    }

    public void setNameOil(String nameOil) {
        this.nameOil = nameOil;
    }

    public int getPriceOil() {
        return priceOil;
    }

    public void setPriceOil(int priceOil) {
        this.priceOil = priceOil;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

}
