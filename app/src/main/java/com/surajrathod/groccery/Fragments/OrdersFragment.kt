package com.surajrathod.groccery.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.surajrathod.groccery.Database.ProductDatabase
import com.surajrathod.groccery.R
import com.surajrathod.groccery.adapter.OrdersAdapter
import com.surajrathod.groccery.adapter.RecyclerAdapter
import com.surajrathod.groccery.databinding.FragmentOrdersBinding


class OrdersFragment : Fragment() {


    lateinit var db : ProductDatabase

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: OrdersAdapter

    lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_orders, container, false)

        db = ProductDatabase.getDatabase(requireContext())

        binding = FragmentOrdersBinding.bind(view)
        recyclerView = binding.ordersRv
        recyclerView.layoutManager = LinearLayoutManager(activity as Context)
        recyclerAdapter = OrdersAdapter(activity as Context)
        recyclerView.adapter = recyclerAdapter

        db.ProductDao().getAllProducts().observe(viewLifecycleOwner, Observer { product->

            recyclerAdapter.setDataList(product)

        })




        return view
    }


}