package com.example.hotelmanagementsystem.database.lostitems

import android.app.Application
import androidx.lifecycle.*
import com.example.myapp.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LostFoundMenuViewModel(application: Application): AndroidViewModel(application){

    val allLostItems: LiveData<List<LostItem>>
    private val repository: LostItemsRepository


    init{
        val lostItemDao = AppDataBase.getDatabase(application).getLostItemDao()
        repository = LostItemsRepository(lostItemDao)
        allLostItems = repository.getAllLostItems
    }

    fun insertLostItem(lostItem: LostItem){

        viewModelScope.launch(Dispatchers.IO) {
            repository.insertLostItem(lostItem)
        }
    }

    fun getLostItem(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLostItem(id)
        }
    }

    fun updateLostItem(lostItem: LostItem) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLostItem(lostItem)
        }
    }

    fun deleteLostItem(lostItem: LostItem) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLostItem(lostItem)
        }

    }
}