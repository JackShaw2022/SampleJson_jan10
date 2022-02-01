package com.example.samplejson_jan10.viewmodel

import androidx.lifecycle.*
import com.example.samplejson_jan10.model.repository.SampleJsonRepository
import com.example.samplejson_jan10.utils.Resource
import com.example.samplejson_jan10.utils.SelectedData
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class SampleJsonViewModel(
    private val sampleJsonRepository: SampleJsonRepository
) : ViewModel() {

    private var _data: MutableLiveData<Resource<List<Any>>> = MutableLiveData()
    val data: LiveData<Resource<List<Any>>> = _data

    init {
        getUsers()
    }

    var selectedDataType = SelectedData.USERS
        set(value) {
            when (value) {
                SelectedData.USERS -> getUsers()
            }
            field = value
        }

    private fun getUsers() {
        _data.value = Resource.Loading
        viewModelScope.launch {
            val response = sampleJsonRepository.getUsers()
            _data.postValue(response)
        }
    }

    class Factory(
        private val sampleJsonRepository: SampleJsonRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SampleJsonViewModel::class.java)) {
                return SampleJsonViewModel(sampleJsonRepository) as T
            } else {
                throw RuntimeException("Could not create instance of TodoViewModel")
            }
        }
    }
}