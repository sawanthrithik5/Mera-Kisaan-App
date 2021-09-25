package com.example.merakisan.ViewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.merakisan.R
public class RequirementViewHolder(
    mContext: Context,
    mReq: List<Requirements>
): RecyclerView.Adapter<RequirementViewHolder.ViewHolder?>(){

    private val mContext: Context
    private val mReq: List<Requirements>
    init {
        this.mContext = mContext
        this.mReq = mReq
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        var cropname: TextView
        var croplstdate: TextView
        var cropquantity: TextView
        var cropprice: TextView
        init {
            cropname = itemView.findViewById(R.id.rcropName)
            croplstdate = itemView.findViewById(R.id.rcroplastdate)
            cropquantity = itemView.findViewById(R.id.rcropQuantity)
            cropprice = itemView.findViewById(R.id.rcropPrice)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.requir_layout, parent, false)
        return RequirementViewHolder.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mReq.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val req: Requirements = mReq[position]
        holder.cropname.text = req.getCropName()
        holder.croplstdate.text = req.getLastData()
        holder.cropquantity.text = "Quantity: " + req.getQuantity()
        holder.cropprice.text = "Price: " + req.getPrice()
    }

}