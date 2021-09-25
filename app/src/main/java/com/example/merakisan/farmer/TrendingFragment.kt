package com.example.merakisan.farmer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.merakisan.R
import com.example.merakisan.ViewHolder.RequirementViewHolder
import com.example.merakisan.ViewHolder.Requirements
import com.example.merakisan.ViewHolder.Trending
import com.example.merakisan.ViewHolder.TrendingViewHolder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TrendingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrendingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var treviewholder1: TrendingViewHolder? = null
    private var mTrend: List<Trending>? = null
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onStart() {
        super.onStart()
        mTrend = ArrayList()
        val ref = FirebaseDatabase.getInstance().reference.child("Trending")
        ref!!.orderByChild("Rate").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for(datasnapshot in snapshot.children){
                    val req = datasnapshot.getValue(Trending::class.java)
                    (mTrend as ArrayList<Trending>).add(req!!)
                }
                treviewholder1 = TrendingViewHolder(context!!, (mTrend as ArrayList<Trending>))
                recyclerView.adapter = treviewholder1
            }

        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_trending, container, false)
        recyclerView = view.findViewById(R.id.trendinglist)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TrendingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TrendingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}