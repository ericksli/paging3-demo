package net.swiftzer.paging3demo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val TAG = "DemoViewModel"

@HiltViewModel
class DemoViewModel @Inject constructor(

) : ViewModel() {

    /**
     * Fake query param to be submitted to our fake backend
     */
    private val query = MutableStateFlow("A")

    /**
     * Background color switch
     */
    val backgroundSwitch = MutableStateFlow(false)

    // just use flatMapLatest and no combine
//    val pagingData: Flow<PagingData<ListItem>> = query.flatMapLatest { q ->
//        Log.d(TAG, "query.flatMapLatest() called with: q = $q")
//        Pager(PagingConfig(pageSize = 20)) { DemoPagingSource(q) }.flow
//            .map { pagingData ->
//                Log.d(TAG, "map() called with: pagingData = $pagingData")
//                pagingData.map { title ->
//                    ListItem(title, false)
//                }
//            }
//    }.cachedIn(viewModelScope)


    // flatMapLatest the query & combine the background color toggle
//    val pagingData: Flow<PagingData<ListItem>> = query.flatMapLatest { q ->
//        combine(
//            Pager(PagingConfig(pageSize = 20)) { DemoPagingSource(q) }.flow.cachedIn(viewModelScope),
//            backgroundSwitch,
//        ) { pagingData, backgroundSwitch ->
//            Log.d(
//                TAG,
//                "called with: pagingData = $pagingData, backgroundSwitch = $backgroundSwitch"
//            )
//            pagingData.map { title ->
//                ListItem(title, backgroundSwitch)
//            }
//        }
//    }.cachedIn(viewModelScope)


    // flatMapLatest the query, combine the background color toggle & clear list when changing query
    val pagingData: Flow<PagingData<ListItem>> = query.flatMapLatest { q ->
        Log.d(TAG, "query.flatMapLatest() called with: q = $q")
        flow {
            emit(flowOf(PagingData.empty()))
            emit(
                combine(
                    Pager(PagingConfig(pageSize = 20)) { DemoPagingSource(q) }.flow
                        .cachedIn(viewModelScope),
                    backgroundSwitch,
                ) { pagingData, backgroundSwitch ->
                    Log.d(
                        TAG,
                        "called with: pagingData = $pagingData, backgroundSwitch = $backgroundSwitch"
                    )
                    pagingData.map { title ->
                        ListItem(title, backgroundSwitch)
                    }
                }
            )
        }.flattenConcat()
    }.cachedIn(viewModelScope)


    fun updateQuery(query: String) {
        this.query.value = query
    }
}
