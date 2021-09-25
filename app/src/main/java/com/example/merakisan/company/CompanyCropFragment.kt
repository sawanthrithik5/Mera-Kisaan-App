package com.example.merakisan.company

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.merakisan.R
import com.example.merakisan.ViewHolder.CropViewHolder
import com.example.merakisan.ViewHolder.Crops
import com.example.merakisan.ViewHolder.RequirementViewHolder
import com.example.merakisan.ViewHolder.Requirements
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
 * Use the [CompanyCropFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CompanyCropFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var reqviewholder: RequirementViewHolder? = null
    private var mReq: List<Requirements>? = null
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_company_crop, container, false)

        recyclerView = view.findViewById(R.id.reqList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onStart() {
        super.onStart()
        mReq = ArrayList()
        val ref = FirebaseDatabase.getInstance().reference.child("Requirement")
        ref!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for(datasnapshot in snapshot.children){
                    val req = datasnapshot.getValue(Requirements::class.java)
                    (mReq as ArrayList<Requirements>).add(req!!)
                }
                reqviewholder = RequirementViewHolder(context!!, (mReq as ArrayList<Requirements>))
                recyclerView.adapter = reqviewholder
            }

        })
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CompanyCropFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}