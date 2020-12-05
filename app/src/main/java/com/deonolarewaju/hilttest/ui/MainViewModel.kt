package com.deonolarewaju.hilttest.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.deonolarewaju.hilttest.model.BlogModel
import com.deonolarewaju.hilttest.repository.MainRepository
import com.deonolarewaju.hilttest.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle): ViewModel(){

//    create datastate object,
    private val _dataState: MutableLiveData<DataState<List<BlogModel>>> = MutableLiveData()

//    getter function for datastate
    val dataState: LiveData<DataState<List<BlogModel>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {

                is MainStateEvent.GetBlogEvents -> {
                    mainRepository.getBlog()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

            }
        }
    }


}

//Used to describe all the different events you can start off
sealed class MainStateEvent {

    object GetBlogEvents: MainStateEvent()

    object None: MainStateEvent()
}