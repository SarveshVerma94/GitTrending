package com.sarvesh.trending.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sarvesh.trending.repository.TrendingRepository


class TrendingViewModel @ViewModelInject constructor(
    private val repository: TrendingRepository
) : ViewModel() {

    val characters = repository.getCharacters()
}
