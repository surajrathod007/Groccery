package com.surajrathod.groccery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.surajrathod.groccery.Fragments.HomeFragment
import com.surajrathod.groccery.Fragments.HomeFragmentDirections
import com.surajrathod.groccery.Fragments.ProductInfo
import com.surajrathod.groccery.R
import com.surajrathod.groccery.model.Product

class RecyclerAdapter(var context : Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var dataList = emptyList<Product>()

    internal fun setDataList(dataList : List<Product>){
        this.dataList = dataList
        notifyDataSetChanged()
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var image : ImageView
        var price : TextView
        var title : TextView
        var container : MaterialCardView

        init {

            image = itemView.findViewById(R.id.productImg)
            price = itemView.findViewById(R.id.txtPrice)
            title = itemView.findViewById(R.id.productName)
            container = itemView.findViewById(R.id.product_item_container)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {


        var data = dataList[position]


        holder.title.text = data.name
        holder.price.text = data.price
        holder.image.setImageResource(data.img)

        holder.container.setOnClickListener {view->
            val action : NavDirections = HomeFragmentDirections.actionHomeFragmentToProductInfo(data)


            Navigation.findNavController(view).navigate(action)

        }


    }

    override fun getItemCount() = dataList.size
}