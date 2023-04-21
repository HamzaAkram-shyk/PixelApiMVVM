package com.example.myapplication.presenter.ui.gridview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.util.Response
import com.example.myapplication.data.util.fromJson
import com.example.myapplication.domain.PixelFetchUC
import com.example.myapplication.domain.model.PixelResponse
import com.example.myapplication.domain.model.RequestModel
import com.squareup.moshi.Moshi
import com.util.OneTimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GirdViewViewModel @Inject constructor(
    private val moshi: Moshi,
    private val pixelUseCase: PixelFetchUC
) : ViewModel() {
    val loader = MutableLiveData(false)
    private val stateEvents = MutableLiveData<OneTimeEvent<GridViewEvent>>()
    val uiStateEvents: LiveData<OneTimeEvent<GridViewEvent>> = stateEvents
    private val request = RequestModel(
        query = "kitten",
        imageType = "photo"
    )

    fun getPixelData() {
        loader.value = true
        viewModelScope.launch {
            pixelUseCase(request)
                .catch { exp ->
                    loader.value = false
                    Log.e("Data", "${exp.message}")
                    stateEvents.value = OneTimeEvent(GridViewEvent.Failure(exp.message.toString()))

                }.collect {
                    when (it) {
                        is Response.Success -> {
                            val response = moshi.fromJson<PixelResponse>(it.responseBody)
                            loader.value = false
                            stateEvents.value =
                                OneTimeEvent(GridViewEvent.CollectList(response?.hits!!))
                        }
                        is Response.Exception -> {
                            Log.e("Data", "${it.e}")
                            loader.value = false

                            stateEvents.value =
                                OneTimeEvent(GridViewEvent.Failure(it.e.message.toString()))
                        }
                        is Response.Failure -> {
                            Log.e("Data", "${it.errorCode} ${it.errorBody}")
                            loader.value = false

                            stateEvents.value = OneTimeEvent(
                                GridViewEvent.Failure(
                                    it.errorCode.toString(),
                                    it.errorBody
                                )
                            )
                        }

                    }

                }

        }
    }

}