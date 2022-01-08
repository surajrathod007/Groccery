package com.surajrathod.groccery.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun ProductDao(): ProductDao



    companion object{

        @Volatile
        private var INSTANCE: ProductDatabase? = null
        fun getDatabase(context: Context): ProductDatabase {

            if (INSTANCE == null) {

                synchronized(this,){

                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDatabase::class.java,
                        "Product_db"
                    ).build()

                }


            }

            return INSTANCE!!
        }

    }






}