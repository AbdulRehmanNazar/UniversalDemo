package com.universal.demo.data.remote.repository

import android.util.Log
import com.universal.demo.common.Resource
import com.universal.demo.data.remote.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @Author: Abdul Rehman
 */

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUsers() = flow {
        try {
            emit(Resource.Loading(null))
            apiService.getContributors().let { response ->
                if (response.isSuccessful) {
                    val resultData = response.body()
                    if (resultData != null) {
                        emit(Resource.Success(response.body()))
                    } else {
                        emit(Resource.Error("Result was null"))
                        Log.e("ScheduleService", "Result was null")
                    }
                } else {
                    emit(Resource.Error(response.message()))
                    Log.e("ScheduleService", "Error occurred: ${response.message()}")
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)
}
