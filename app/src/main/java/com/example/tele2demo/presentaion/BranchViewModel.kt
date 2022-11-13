package com.example.tele2demo.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tele2demo.domain.Repository
import com.example.tele2demo.domain.Response
import com.example.tele2demo.domain.ViewState
import com.example.tele2demo.domain.model.City
import kotlinx.coroutines.launch

class BranchViewModel(private val repository: Repository) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<BranchViewState>>()
    val viewState: LiveData<ViewState<BranchViewState>> = _viewState

    fun getCities() {
        viewModelScope.launch {
            when (val result = repository.getCities()) {
                is Response.Success -> {
                    _viewState.postValue(ViewState.Data(BranchViewState.Cities(result.data)))
                }
                is Response.Failure -> {}
            }
        }
    }

    fun getBranches(city: City) {
        viewModelScope.launch {
            when (val result = repository.getBranches(city.id)) {
                is Response.Success -> {
                    _viewState.postValue(ViewState.Data(BranchViewState.Branches(result.data)))
                }
                is Response.Failure -> {}
            }
        }
    }
}