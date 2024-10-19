package com.universal.demo.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universal.demo.common.Resource
import com.universal.demo.data.model.User
import com.universal.demo.data.remote.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Abdul Rehman
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = _userList

    private val _loadingState = MutableLiveData(false)
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _errorState = MutableLiveData<String>()
    val errorState: LiveData<String>
        get() = _errorState

    /*
     * Get Users from remote
     */
    fun fetchRemoteUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getUsers().collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        delay(2000)
                        _loadingState.postValue(false)
                        _userList.postValue(resource.data!!)
                    }

                    is Resource.Error -> {
                        _loadingState.postValue(false)
                        _errorState.postValue(resource.message!!)
                    }

                    is Resource.Loading -> {
                        _loadingState.postValue(true)
                    }
                }
            }
        }
    }
}