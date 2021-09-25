package com.example.merakisan.ViewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.merakisan.R
import kotlinx.android.synthetic.main.trending_layout.view.*

public class TrendingViewHolder(
    mContext: Context,
    mTrend: List<Trending>
) : RecyclerView.Adapter<TrendingViewHolder.ViewHolder?>(){
    private val mContext: Context
    private val mTrend: List<Trending>
    init {
        this.mContext = mContext
        this.mTrend = mTrend
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var cropname1: TextView
        var croprate1: TextView
        init {
            cropname1 = itemView.findViewById(R.id.crop_name)
            croprate1 = itemView.findViewById(R.id.crop_rate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.trending_layout, parent, false)
        return TrendingViewHolder.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mTrend.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trend: Trending = mTrend[position]
        holder.cropname1.text = trend.getCropName()
        holder.croprate1.text = trend.getRate()
    }

}