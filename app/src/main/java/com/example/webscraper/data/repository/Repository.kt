package com.example.webscraper.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.webscraper.interactor.BusinessLogic
import com.example.webscraper.utilities.Failure
import com.example.webscraper.utilities.Outcome
import com.example.webscraper.utilities.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class Repository {

    private val businessLogic = BusinessLogic()

    suspend fun scrapeTenthCharacter(): MutableLiveData<Outcome<String>> {
        val liveData = MutableLiveData<Outcome<String>>()

        withContext(Dispatchers.IO) {
            try {
                val document = Jsoup.connect(TARGET_URL).get()
                val result = businessLogic.getTenthCharacter(document.toString())
                liveData.postValue(Success(result))
            } catch (e: Exception) {
                liveData.postValue(Failure(e))
            }
        }
        return liveData
    }

    suspend fun scrapeEveryTenthCharacter(): MutableLiveData<Outcome<String>> {

        val liveData = MutableLiveData<Outcome<String>>()

        withContext(Dispatchers.IO) {
            try {
                val document = Jsoup.connect(TARGET_URL).get()
                val result = businessLogic.getEveryTenthCharacter(document.toString())
                liveData.postValue(Success(result))

            } catch (e: Exception) {
                liveData.postValue(Failure(e))
            }
        }
        return liveData
    }

    suspend fun scrapeAllWordsAndTheirRepetitionCount(): MutableLiveData<Outcome<Map<String, Int>>> {

        val liveData = MutableLiveData<Outcome<Map<String, Int>>>()

        withContext(Dispatchers.IO) {
            try {
                val document = Jsoup.connect(TARGET_URL).get()
                val result = businessLogic.getAllWordsAndRepetitionCount(document.toString())
                liveData.postValue(Success(result))
            } catch (e: Exception) {
                liveData.postValue(Failure(e))
            }
        }
        return liveData
    }

    companion object {
        private const val TARGET_URL =
            "https://truecaller.blog/2018/01/22/life-as-an-android-engineer/"
    }
}