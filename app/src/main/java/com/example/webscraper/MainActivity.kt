package com.example.webscraper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.webscraper.data.repository.Repository
import com.example.webscraper.interactor.Interactor
import com.example.webscraper.utilities.Failure
import com.example.webscraper.utilities.Success
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val interactor = Interactor(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_request.setOnClickListener {

            getTenthCharacter()

            getEveryTenthCharacter()

            getAllWordsAndTheirRepetitionCount()
        }
    }

    private fun getTenthCharacter() {
        CoroutineScope(Dispatchers.Main).launch {
            interactor.scrapeTenthCharacter().observe(this@MainActivity, Observer { outcome ->
                when (outcome) {
                    is Success -> {
                        txt_first.text = getString(R.string.tenth_character).plus(outcome.data)
                    }
                    is Failure -> {
                        Snackbar.make(
                            root,
                            outcome.throwable.localizedMessage,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
    }

    private fun getEveryTenthCharacter() {
        CoroutineScope(Dispatchers.Main).launch {
            interactor.scrapeEveryTenthCharacter().observe(this@MainActivity, Observer { outcome ->
                when (outcome) {
                    is Success -> {
                        txt_second.text =
                            getString(R.string.every_tenth_character).plus(outcome.data)
                    }
                    is Failure -> {
                        Snackbar.make(
                            root,
                            outcome.throwable.localizedMessage,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
    }

    private fun getAllWordsAndTheirRepetitionCount() {
        CoroutineScope(Dispatchers.Main).launch {
            interactor.scrapeAllWordsAndTheirRepetitionCount()
                .observe(this@MainActivity, Observer { outcome ->
                    when (outcome) {
                        is Success -> {

                            txt_third.text =
                                getString(R.string.all_words_and_their_repetition_count)

                            outcome.data.entries.map {
                                txt_third.append(it.key + " : " + it.value + getString(R.string.repetition))
                            }
                        }
                        is Failure -> {
                            Snackbar.make(
                                root,
                                outcome.throwable.localizedMessage,
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
        }
    }
}
