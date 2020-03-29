package com.example.webscraper.domain

class BusinessLogic {

    fun getTenthCharacter(target: String): String {
        return if (target.length > 10) {
            target[10].toString()
        } else {
            "string too short"
        }
    }

    fun getEveryTenthCharacter(target: String): String {
        return target.filterIndexed { index, _ ->
            index > 0 && index % 10 == 0
        }
    }

    fun getAllWordsAndRepetitionCount(target: String): List<Pair<String, Int>> {
        return if (target.length > 10) {
            target.split("\\s+".toRegex()).toList().groupingBy { it }.eachCount()
                .filter { it.value > 0 }.toList()
        } else {
            listOf(Pair("string too short", 0))
        }
    }
}