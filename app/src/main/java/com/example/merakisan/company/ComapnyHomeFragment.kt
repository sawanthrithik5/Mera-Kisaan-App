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
 * Use the [ComapnyHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComapnyHomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var cropAdapter1: CropViewHolder? = null
    private var mCrops1: List<Crops>? = null
    lateinit var recyclerView1: RecyclerView

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
        var view: View =  inflater.inflate(R.layout.fragment_comapny_home, container, false)

        recyclerView1 = view.findViewById(R.id.cropList)
        recyclerView1.setHasFixedSize(true)
        recyclerView1.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onStart() {
        super.onStart()
        mCrops1 = ArrayList()
        val ref = FirebaseDatabase.getInstance().reference.child("Crop")
        ref!!.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for(datasnapshot in snapshot.children){
                    val crop = datasnapshot.getValue(Crops::class.java)
                    (mCrops1 as ArrayList<Crops>).add(crop!!)
                }
                cropAdapter1 = CropViewHolder(context!!, (mCrops1 as ArrayList<Crops>))
                recyclerView1.adapter = cropAdapter1
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ComapnyHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ComapnyHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}