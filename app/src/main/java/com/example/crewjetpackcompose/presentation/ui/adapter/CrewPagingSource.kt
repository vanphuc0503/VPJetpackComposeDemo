package com.example.crewjetpackcompose.presentation.ui.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.crewjetpackcompose.data.model.Crew

class CrewPagingSource(
    private val apiResult: suspend (page: Int) -> List<Crew>,
) : PagingSource<Int, Crew>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Crew> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiResult(page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Crew>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}