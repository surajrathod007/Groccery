package com.surajrathod.groccery.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Product(
    var name: String? = null,
    var desc: String? = null,
    var price: String?,
    var img: Int
) : Parcelable

