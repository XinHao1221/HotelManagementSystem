package com.example.hotelmanagementsystem.database.lostitems;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface LostItemDao {
    @Insert
    suspend fun insertLostItem(lostItem: LostItem)

    @Update
    suspend fun updateLostItem(lostItem: LostItem)

    @Delete
    suspend fun deleteLostItem(lostItem: LostItem)

    @Query("SELECT * FROM lost_item_table WHERE lostItemId = :key")
    fun getLostItem(key: Long): LostItem?;

    @Query("SELECT * FROM lost_item_table ORDER BY lostItemId ASC")
    fun getAllLostItems(): LiveData<List<LostItem>>;
}

