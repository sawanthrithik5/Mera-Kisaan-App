package com.example.merakisan.ViewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.merakisan.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crop_layout.view.*

public class CropViewHolder(
    mContext: Context,
    mCrops: List<Crops>
) : RecyclerView.Adapter<CropViewHolder.ViewHolder?>() {

    private val mContext: Context
    private val mCrops: List<Crops>
    init {
        this.mContext = mContext
        this.mCrops = mCrops
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var cropname: TextView
        var cropdescription: TextView
        var cropquantity: TextView
        var cropprice: TextView
        var cropimage: ImageView
        init {
            cropname = itemView.findViewById(R.id.cropName)
            cropdescription = itemView.findViewById(R.id.cropDescription)
            cropprice = itemView.findViewById(R.id.cropPrice)
            cropquantity = itemView.findViewById(R.id.cropQuantity)
            cropimage = itemView.findViewById(R.id.cropImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.crop_layout, parent, false)
        return CropViewHolder.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCrops.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crop: Crops = mCrops[position]
        holder.cropname.text = crop.getProductName()
        holder.cropdescription.text = crop.getDescription()
        holder.cropquantity.text = "Quantity: " + crop.getQuantity()
        holder.cropprice.text = "Price: " + crop.getPrice()
        Picasso.get().load(crop.getProductImage()).fit().into(holder.cropimage)
    }
}