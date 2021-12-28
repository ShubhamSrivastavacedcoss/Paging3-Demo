package com.example.paging3demo.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3demo.api.QuoteApi
import com.example.paging3demo.modelClass.Search

class SearchPagingSource(val quoteApi: QuoteApi) : PagingSource<Int, Search>() {
    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        return try {
            val page = params.key ?: FIRST_PAGE
            val response = quoteApi.getQuotes("1d094e25","fast","movie", page)
           // val response = api.fetchRepos(username, page, params.loadSize)
            LoadResult.Page(
                data = response.Search,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (response.Search.isEmpty()) null else page + 1
            )


      /*      val nextPage : Int = params.key?: FIRST_PAGE
            val response = quoteApi.getQuotes("","","", nextPage)
            val nextPageNumber: Int? = null

            if (response.body()! = null){

            }*/

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}