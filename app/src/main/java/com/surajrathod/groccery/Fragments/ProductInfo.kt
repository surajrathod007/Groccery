package com.surajrathod.groccery.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.surajrathod.groccery.R
import com.surajrathod.groccery.databinding.FragmentProductInfoBinding
import com.surajrathod.groccery.model.Product


class ProductInfo : Fragment() {

    lateinit var binding: FragmentProductInfoBinding

    val args by navArgs<ProductInfoArgs>()

    lateinit var productOrder : Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_product_info, container, false)

        productOrder = Product(args.product.name,args.product.desc,args.product.price,args.product.img)
        binding = FragmentProductInfoBinding.bind(view)

        binding.apply {

            txtTitle.text = args.product.name
            txtDesc.text = args.product.desc
            txtPrice.text = args.product.price
            productImgF.setImageResource(args.product.img)
        }

        binding.backBtn.setOnClickListener {

            val action: NavDirections = ProductInfoDirections.actionProductInfoToHomeFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.orderBtn.setOnClickListener {
            val action : NavDirections = ProductInfoDirections.actionProductInfoToPlaceOrder(productOrder)
            Navigation.findNavController(it).navigate(action)

        }

        binding.btnViewOrders.setOnClickListener {
            val action : NavDirections = ProductInfoDirections.actionProductInfoToOrdersFragment()
            Navigation.findNavController(it).navigate(action)

        }




        return view
    }






}