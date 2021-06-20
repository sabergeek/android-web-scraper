package com.example.webscraper.presentation.viewmodels

import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.lifecycle.*
import com.example.webscraper.data.repository.Failure
import com.example.webscraper.data.repository.Outcome
import com.example.webscraper.data.repository.RemoteDataSource
import com.example.webscraper.data.repository.Success
import kotlinx.coroutines.launch

class ScraperViewModel : ViewModel() {

    private val repo by lazy {
        RemoteDataSource()
    }

    val scrapeAllWordsAndTheirRepetitionCount: LiveData<Outcome<List<String>>> by lazy {
        viewModelScope.launch {
            val response = repo.scrape().let { outcome ->
                when (outcome) {
                    is Success -> {
                        Success(outcome.data.map { formatResponse(it) })
                    }
                    is Failure -> {
                        Failure(outcome.throwable)
                    }
                }
            }

            _scrapeAllWordsAndTheirRepetitionCount.postValue(response)
        }
        _scrapeAllWordsAndTheirRepetitionCount.distinctUntilChanged()
    }

    private fun formatResponse(data: Pair<String, Int>): String {
        return SpannableStringBuilder().bold {
            append(data.first).append(" : ").append(data.second.toString())
                .append(" repetition \n\n")
        }.toString()
    }

    private val _scrapeAllWordsAndTheirRepetitionCount by lazy {
        MutableLiveData<Outcome<List<String>>>()
    }
}
