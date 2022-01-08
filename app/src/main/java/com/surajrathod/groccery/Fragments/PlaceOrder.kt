package com.surajrathod.groccery.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.surajrathod.groccery.Database.ProductDatabase
import com.surajrathod.groccery.Database.ProductEntity
import com.surajrathod.groccery.R
import com.surajrathod.groccery.databinding.FragmentPlaceOrderBinding
import com.surajrathod.groccery.model.Product
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class PlaceOrder : Fragment() {


    val args1 by navArgs<PlaceOrderArgs>()
    var mulCost = 1
    lateinit var db: ProductDatabase

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())
    //val db = Room.databaseBuilder(activity as Context,ProductDatabase::class.java,"product_db").build()


    //var costInt = Integer.parseInt(costTotal.toString())
    lateinit var binding: FragmentPlaceOrderBinding
    //  lateinit var productdetail : Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_place_order, container, false)

        db = ProductDatabase.getDatabase(requireContext())

        binding = FragmentPlaceOrderBinding.bind(view)


        val size = args1.productData.price?.length

        var subPrice = args1.productData.price

        var costTotal = subPrice?.subSequence(1, size!!)

        var intCost: Int = Integer.parseInt(costTotal as String)
        var intCostForMinus: Int = Integer.parseInt(costTotal)
        val currentPrice: Int = Integer.parseInt(costTotal)


        binding.apply {

            txtPriceOrder.text = txtPriceOrder.text.toString() + args1.productData.price
            txtTitleOrder.text = txtTitleOrder.text.toString() + args1.productData.name
            productImgOrder.setImageResource(args1.productData.img)
            txtPriceOrder.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.Price_color
                )
            )
            cost.text = "$$costTotal"

        }


        binding.btnPlus.setOnClickListener {

            mulCost++
            intCost += currentPrice
            intCostForMinus += intCostForMinus

            intCost.toString()
            binding.cost.text = "$$intCost"
            binding.counter.text = "Product Quantity : $mulCost "
            //intCost  = Integer.parseInt(costTotal as String)


        }

        binding.btnMinus.setOnClickListener {
            --mulCost

            if (mulCost < 1) {
                Toast.makeText(requireContext(), "Can not less than 1", Toast.LENGTH_SHORT).show()
            } else {
                binding.counter.text = "Product Quantity : $mulCost "
            }


            intCost -= currentPrice
            if (intCost < 1) {
                Toast.makeText(requireContext(), "Can not less than 1", Toast.LENGTH_SHORT).show()
            } else {
                intCost.toString()
                binding.cost.text = "$$intCost"
            }
            //intCost = intCostForMinus

        }


        binding.btnPlaceOrder2.setOnClickListener {

            GlobalScope.launch {
                db.ProductDao().insert(
                    ProductEntity(
                        0,
                        "Product Name : ${args1.productData.name!!}",
                        "Total cost : ${binding.cost.text.toString()}",
                        "Quantity : $mulCost", "Purchase Date : $currentDate"
                    )
                )
            }

            Toast.makeText(requireContext(), "Order Succesfull !", Toast.LENGTH_SHORT).show()
        }






        return view
    }


}