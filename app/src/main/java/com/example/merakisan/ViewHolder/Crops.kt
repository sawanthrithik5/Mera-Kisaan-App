package com.example.merakisan.ViewHolder

class Crops {
    private var ProductName: String = ""
    private var Description: String = ""
    private var Price: String = ""
    private var Quantity: String = ""
    private var ProductImage: String = ""

    constructor(){

    }
    constructor(
        ProductName: String,
        Description: String,
        Price: String,
        Quantity: String,
        ProductImage: String
    ) {
        this.ProductName = ProductName
        this.Description = Description
        this.Price = Price
        this.Quantity = Quantity
        this.ProductImage = ProductImage
    }
    fun getProductName(): String?{
        return ProductName
    }
    fun setProductName(ProductName: String){
        this.ProductName = ProductName
    }
    fun getDescription(): String?{
        return Description
    }
    fun setDescription(Description: String){
        this.Description = Description
    }
    fun getPrice(): String?{
        return Price
    }
    fun setPrice(Price: String){
        this.Price = Price
    }
    fun getQuantity(): String?{
        return Quantity
    }
    fun setQuantity(Quantity: String){
        this.Quantity = Quantity
    }
    fun getProductImage(): String?{
        return ProductImage
    }
    fun setProductImage(ProductImage: String){
        this.ProductImage = ProductImage
    }
}
