package com.surajrathod.groccery.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductTable")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) var product_id : Int,
    var product_name : String,
    var total_cost : String,
    var quantity : String,
    var date : String

)
