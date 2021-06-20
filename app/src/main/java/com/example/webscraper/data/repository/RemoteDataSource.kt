package com.example.webscraper.data.repository

import com.example.webscraper.domain.BusinessLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class RemoteDataSource {

    private val businessLogic by lazy {
        BusinessLogic()
    }

    suspend fun scrape(): Outcome<List<Pair<String, Int>>> {
        return withContext(Dispatchers.IO) {
            try {
                val document = withContext(Dispatchers.IO) {
                    Jsoup.connect(TARGET_URL).get()
                }
                val result = businessLogic.getAllWordsAndRepetitionCount(document.toString())
                Success(result)
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }

    companion object {
        private const val TARGET_URL = "https://en.wikipedia.org/wiki/India"
    }
}