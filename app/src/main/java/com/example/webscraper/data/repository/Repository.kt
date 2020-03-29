package com.example.webscraper.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.webscraper.domain.BusinessLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class Repository {

    private val businessLogic = BusinessLogic()

    private val liveDataForTenthCharacter = MutableLiveData<Outcome<String>>()
    private val liveDataForEveryTenthCharacter = MutableLiveData<Outcome<String>>()
    private val liveDataForAllWordsAndTheirRepetitionCount = MutableLiveData<Outcome<List<Pair<String, Int>>>>()

    suspend fun scrapeTenthCharacter(forceNetworkCall: Boolean = false): MutableLiveData<Outcome<String>> =
        withContext(Dispatchers.IO) {
            try {
                if (forceNetworkCall || liveDataForTenthCharacter.value == null){
                    val document = Jsoup.connect(TARGET_URL).get()
                    val result = businessLogic.getTenthCharacter(document.toString())
                    liveDataForTenthCharacter.postValue(Success(result))
                }
            } catch (e: Exception) {
                liveDataForTenthCharacter.postValue(Failure(e))
            }
            return@withContext liveDataForTenthCharacter
        }

    suspend fun scrapeEveryTenthCharacter(forceNetworkCall: Boolean = false): MutableLiveData<Outcome<String>> =
        withContext(Dispatchers.IO) {
            try {
                if (forceNetworkCall || liveDataForEveryTenthCharacter.value == null){
                    val document = Jsoup.connect(TARGET_URL).get()
                    val result = businessLogic.getEveryTenthCharacter(document.toString())
                    liveDataForEveryTenthCharacter.postValue(Success(result))
                }
            } catch (e: Exception) {
                liveDataForEveryTenthCharacter.postValue(
                    Failure(
                        e
                    )
                )
            }
            return@withContext liveDataForEveryTenthCharacter
        }

    suspend fun scrapeAllWordsAndTheirRepetitionCount(forceNetworkCall: Boolean = false): MutableLiveData<Outcome<List<Pair<String, Int>>>> =
        withContext(Dispatchers.IO) {
            try {
                if (forceNetworkCall || liveDataForAllWordsAndTheirRepetitionCount.value == null){
                    val document = Jsoup.connect(TARGET_URL).get()
                    val result = businessLogic.getAllWordsAndRepetitionCount(document.toString())
                    liveDataForAllWordsAndTheirRepetitionCount.postValue(Success(result))
                }
            } catch (e: Exception) {
                liveDataForAllWordsAndTheirRepetitionCount.postValue(
                    Failure(e)
                )
            }
            return@withContext liveDataForAllWordsAndTheirRepetitionCount
        }

    companion object {
        private const val TARGET_URL = "https://en.wikipedia.org/wiki/India"
    }
}