package com.example.hotelmanagementsystem.database.lostitems

import androidx.lifecycle.LiveData

class LostItemsRepository(private val lostItemDao: LostItemDao) {
    val getAllLostItems: LiveData<List<LostItem>> = lostItemDao.getAllLostItems()

    suspend fun insertLostItem(lostItem: LostItem) {
        lostItemDao.insertLostItem(lostItem)
    }

    suspend fun updateLostItem(lostItem: LostItem) {
        lostItemDao.updateLostItem(lostItem)
    }

    suspend fun deleteLostItem(lostItem: LostItem) {
        lostItemDao.deleteLostItem(lostItem)
    }

    fun getLostItem(key: Long): LostItem? {
        return lostItemDao.getLostItem(key)
    }
}