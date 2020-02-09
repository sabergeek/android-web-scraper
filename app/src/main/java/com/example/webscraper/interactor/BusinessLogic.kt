package com.example.webscraper.interactor

class BusinessLogic {

    fun getTenthCharacter(target: String): String {
        return target[10].toString()
    }

    fun getEveryTenthCharacter(target: String): String {
        return target.filterIndexed { index, _ ->
            index > 0 && index % 10 == 0
        }
    }

    fun getAllWordsAndRepetitionCount(target: String): List<Pair<String, Int>> {

        return target.split("\\s+".toRegex()).toList().groupingBy { it }.eachCount().filter { it.value > 0 }.toList()
    }
}