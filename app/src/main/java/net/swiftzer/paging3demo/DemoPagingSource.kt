package net.swiftzer.paging3demo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

private const val PER_PAGE = 10
private const val TAG = "DemoPagingSource"

class DemoPagingSource(private val query:String) : PagingSource<Int, String>() {
    override fun getRefreshKey(state: PagingState<Int, String>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        Log.d(TAG, "load() called with query = $query, key = ${params.key}")
        delay(1000)
        val pageNumber = params.key ?: 1
        val start = (pageNumber - 1) * PER_PAGE + 1
        val list = (start until start + PER_PAGE).map { "Item $it (Query $query)" }
        return LoadResult.Page(
            data = list,
            prevKey = null,
            nextKey = if (pageNumber == 10) null else pageNumber + 1,
        )
    }
}
