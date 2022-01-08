package com.surajrathod.groccery.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.groccery.R
import com.surajrathod.groccery.adapter.RecyclerAdapter
import com.surajrathod.groccery.databinding.FragmentHomeBinding
import com.surajrathod.groccery.model.Product


class HomeFragment : Fragment() {


    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    var price = 15
    var dataList = mutableListOf<Product>()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_home, container, false)

        binding = FragmentHomeBinding.bind(view)

//        binding.textview1.setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_aboutMe)
//        }
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(activity as Context,2)
        recyclerAdapter = RecyclerAdapter(activity as Context)
        recyclerView.adapter = recyclerAdapter



        dataList.add(Product("Blue T Shirt,with kick",getString(R.string.product1),"$14",R.drawable.bluet))
        dataList.add(Product("Hunnky t shirt",getString(R.string.product1),"$${price}",R.drawable.bluet))
        dataList.add(Product("Red t shirt",getString(R.string.product1),"$18",R.drawable.bluet))

        recyclerAdapter.setDataList(dataList)



        return view

    }




}