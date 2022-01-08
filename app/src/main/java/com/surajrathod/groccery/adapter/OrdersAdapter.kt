package com.surajrathod.groccery.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.groccery.Database.ProductDatabase
import com.surajrathod.groccery.Database.ProductEntity
import com.surajrathod.groccery.R
import com.surajrathod.groccery.model.Product
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class OrdersAdapter(var context : Context) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>(){




    var dataList = emptyList<ProductEntity>()

    internal fun setDataList(dataList : List<ProductEntity>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        var name : TextView
        var cost : TextView
        var quant : TextView
        var date : TextView
        var card : CardView

        init {
            name = itemView.findViewById(R.id.PrName)
            cost = itemView.findViewById(R.id.PrCost)
            quant = itemView.findViewById(R.id.PrQuantity)
            date = itemView.findViewById(R.id.PrDate)
            card = itemView.findViewById(R.id.orderCard)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.orders_container,parent,false)
        return OrdersAdapter.ViewHolder(view)
    }



    override fun getItemCount()= dataList.size

    override fun onBindViewHolder(holder: OrdersAdapter.ViewHolder, position: Int) {



        var data = dataList[position]
        holder.name.text = data.product_name
        holder.quant.text = data.quantity
        holder.cost.text = data.total_cost
        holder.date.text = data.date

        holder.card.setOnLongClickListener {

            var db = ProductDatabase.getDatabase(it.context)
            GlobalScope.launch {
                db.ProductDao().delete(data)
            }
            Toast.makeText(it.context,"Order Deleted",Toast.LENGTH_SHORT).show()

            true
        }


    }


}