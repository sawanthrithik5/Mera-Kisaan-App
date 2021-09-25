package com.example.merakisan.ViewHolder

class Requirements{
    private var CropName: String = ""
    private var LastData: String = ""
    private var Price: String = ""
    private var Quantity: String = ""

    constructor(){

    }
    constructor(CropName: String, LastData: String, Price: String, Quantity: String) {
        this.CropName = CropName
        this.LastData = LastData
        this.Price = Price
        this.Quantity = Quantity
    }

    fun getCropName():String?{
        return CropName
    }
    fun setCropName(CropName: String){
        this.CropName = CropName
    }

    fun getLastData():String?{
        return LastData
    }
    fun setLastData(LastData: String){
        this.LastData = LastData
    }

    fun getPrice():String?{
        return Price
    }
    fun setPrice(Price: String){
        this.Price = Price
    }

    fun getQuantity():String?{
        return Quantity
    }
    fun setQuantity(Quantity: String){
        this.Quantity = Quantity
    }


}