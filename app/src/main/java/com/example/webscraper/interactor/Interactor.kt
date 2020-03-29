package com.example.webscraper.interactor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.webscraper.data.repository.Outcome
import com.example.webscraper.data.repository.Repository

class Interactor(private val repo: Repository) : ViewModel() {

    private val liveDataScrapeTenthCharacter = MutableLiveData<Outcome<String>>()
    private val liveDataScrapeEveryTenthCharacter = MutableLiveData<Outcome<String>>()
    private val liveDataScrapeAllWordsAndTheirRepetitionCount = MutableLiveData<Outcome<List<Pair<String, Int>>>>()

    suspend fun scrapeTenthCharacter(refresh: Boolean = false): MutableLiveData<Outcome<String>> {
        liveDataScrapeTenthCharacter.postValue(repo.scrapeTenthCharacter(refresh).value)
        return liveDataScrapeTenthCharacter
    }

    suspend fun scrapeEveryTenthCharacter(refresh: Boolean = false): MutableLiveData<Outcome<String>> {
        liveDataScrapeEveryTenthCharacter.postValue(repo.scrapeEveryTenthCharacter().value)
        return liveDataScrapeEveryTenthCharacter
    }

    suspend fun scrapeAllWordsAndTheirRepetitionCount(refresh: Boolean = false): MutableLiveData<Outcome<List<Pair<String, Int>>>> {
        liveDataScrapeAllWordsAndTheirRepetitionCount.postValue(repo.scrapeAllWordsAndTheirRepetitionCount().value)
        return liveDataScrapeAllWordsAndTheirRepetitionCount
    }
}
