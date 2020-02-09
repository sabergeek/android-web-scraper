package com.example.webscraper.interactor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webscraper.data.repository.Repository
import com.example.webscraper.utilities.Outcome
import kotlinx.coroutines.launch

class Interactor(private val repo: Repository) : ViewModel() {

    suspend fun scrapeTenthCharacter(): MutableLiveData<Outcome<String>> {
        val liveData = MutableLiveData<Outcome<String>>()
        viewModelScope.launch {
            liveData.postValue(repo.scrapeTenthCharacter().value)
        }
        return liveData
    }

    suspend fun scrapeEveryTenthCharacter(): MutableLiveData<Outcome<String>> {
        val liveData = MutableLiveData<Outcome<String>>()
        viewModelScope.launch {
            liveData.postValue(repo.scrapeEveryTenthCharacter().value)
        }
        return liveData
    }

    suspend fun scrapeAllWordsAndTheirRepetitionCount(): MutableLiveData<Outcome<List<Pair<String, Int>>>> {
        val liveData = MutableLiveData<Outcome<List<Pair<String, Int>>>>()
        viewModelScope.launch {
            liveData.postValue(repo.scrapeAllWordsAndTheirRepetitionCount().value)
        }
        return liveData
    }
}
