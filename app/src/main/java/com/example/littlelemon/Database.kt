package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.serialization.SerialName

@Entity
data class MenuItem(
    @PrimaryKey val id: Int,
    @ColumnInfo(name ="title") val title: String,
    @ColumnInfo(name ="description") val description: String,
    @ColumnInfo(name ="price") val price: Int,
    @ColumnInfo(name ="image") val image: String,
    @ColumnInfo(name ="category") val category: String
)

@Dao
interface MenuDao {
    @Query("SELECT * FROM MenuItem")
    fun getAll(): LiveData<List<MenuItem>>

    @Query("SELECT (SELECT COUNT(*) FROM MenuItem) == 0")
    fun isEmpty(): Boolean

    @Insert
    fun add(menuItem: MenuItem)
}

@Database(entities = [MenuItem::class], version = 1)
abstract class AppDatabas: RoomDatabase(){
    abstract fun menuDao(): MenuDao
}
