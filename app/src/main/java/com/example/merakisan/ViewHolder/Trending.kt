package com.example.merakisan.ViewHolder

class Trending{
    private var CropName: String = ""
    private var Rate: String = ""

    constructor(){

    }

    constructor(CropName: String, Rate: String) {
        this.CropName = CropName
        this.Rate = Rate
    }

    fun getCropName():String?{
        return CropName
    }
    fun setCropName(CropName: String){
        this.CropName = CropName
    }

    fun getRate(): String?{
        return Rate
    }
    fun setRate(Rate: String){
        this.Rate = Rate
    }

}