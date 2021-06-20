package com.example.webscraper.domain

class BusinessLogic {
    /**
     * This scrapes the entire blob of text and considers all letters separated by white-spaces as words.
     * Returns a list of Pairs, each mapping to a word and how many times they're repeated.
     */
    fun getAllWordsAndRepetitionCount(target: String): List<Pair<String, Int>> {
        return if (target.length > 10) {
            target.split("\\s+".toRegex()).toList().groupingBy { it }.eachCount()
                .filter { it.value > 0 }.toList()
        } else {
            listOf(Pair("string too short", 0))
        }
    }
}