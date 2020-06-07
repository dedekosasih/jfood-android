package com.example.jfood_android;



public class Food
{
    // instance variables - replace the example below with your own

    private int id;
    private String name;
    private int price;
    private Seller seller;
    private String category;



    public Food(int id, String name, int price, String category, Seller seller)
    {
        this.id=id;
        this.name=name;
        this.price=price;
        this.category=category;
        this.seller=seller;
    }

    public Food(int id, String name, int price, String category) {
    }

    /**
     * Ini adalah method untuk mendapatkan id dengan tipe data integer
     *
     * @return id akan mengembalikan id
     */
    public int getId()
    {
        // initialise instance variables
        return id;
    }
    /**
     * Ini adalah method untuk mendapatkan nama
     *
     * @return name akan mengembalikan name
     */
    public String getName()
    {
        // initialise instance variables
        return name;
    }
    /**
     * Ini adalah method untuk mendapatkan keterangan harga
     *
     * @return price akan mengembalikan price
     */
    public int getPrice()
    {
        // initialise instance variables
        return price;
    }
    /**
     * Ini adalah method untuk mendapatkan seller
     *
     * @return seller akan mengembalikan seller
     */
    public Seller getSeller()
    {
        // initialise instance variables
        return seller;
    }
    /**
     * Ini adalah method untuk mendapatkan category
     *
     * @return category akan mengembalikan category
     */
    public String getCategory()
    {
        // initialise instance variables
        return category;
    }
    /**
     * Ini adalah setter untuk id
     *
     * @param id untuk set id
     */
    public void setId(int id)
    {
        // initialise instance variables
        this.id=id;
    }
    /**
     * Ini adalah setter untuk name
     *
     * @param name untuk set name
     */
    public void setName(String name)
    {
        // initialise instance variables
        this.name=name;
    }
    /**
     * Ini adalah setter untuk price
     *
     * @param price untuk set price
     */
    public void setPrice(int price)
    {
        // initialise instance variables
        this.price=price;
    }
    /**
     * Ini adalah setter untuk category
     *
     * @param category untuk set category
     */
    public void setCategory(String category)
    {
        // initialise instance variables
        this.category=category;
    }

    public void setSeller(Seller seller)
    {
        // initialise instance variables
        this.seller=seller;
    }


}
