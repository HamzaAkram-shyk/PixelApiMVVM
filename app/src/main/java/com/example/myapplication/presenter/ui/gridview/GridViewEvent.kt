package com.example.myapplication.presenter.ui.gridview

import com.example.myapplication.domain.model.PixelResponse

sealed class GridViewEvent {
    class CollectList(val list: List<PixelResponse.Hit>) : GridViewEvent()
    class Failure(val message: String, error: Any? = null) : GridViewEvent()
}
