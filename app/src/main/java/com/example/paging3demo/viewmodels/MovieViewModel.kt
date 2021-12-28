package com.example.paging3demo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*

import com.example.paging3demo.repository.SearchPagingSource
import com.example.paging3demo.api.QuoteApi
import com.example.paging3demo.api.RetrofitHelper
import com.example.paging3demo.modelClass.Search
import kotlinx.coroutines.flow.Flow

class MovieViewModel:ViewModel() {

    lateinit var quoteApi: QuoteApi

    init {
        quoteApi = RetrofitHelper.getInstance().create(QuoteApi::class.java)
    }

    fun getListData(): Flow<PagingData<Search>> {
        return Pager(config = PagingConfig(pageSize = 8, maxSize = 200),
            pagingSourceFactory= { SearchPagingSource(quoteApi) }).flow.cachedIn(viewModelScope)

    }
}