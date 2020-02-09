package com.example.webscraper

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.lifecycle.Observer
import com.example.webscraper.data.repository.Repository
import com.example.webscraper.interactor.Interactor
import com.example.webscraper.utilities.Failure
import com.example.webscraper.utilities.Success
import com.google.android.material.snackbar.Snackbar
import com.list.rados.fast_list.bind
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_content.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val interactor = Interactor(Repository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_request.setOnClickListener {

            btn_request.text = getString(R.string.retry)

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
                        txt_first.text = SpannableStringBuilder().bold { append(outcome.data)}
                        txt_first.append("\n__________________________________________________")
                    }
                    is Failure -> {
                        Snackbar.make(root, outcome.throwable.localizedMessage, Snackbar.LENGTH_SHORT).show()
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
                        txt_second.text = SpannableStringBuilder().bold { append(outcome.data)}
                        txt_second.append("\n__________________________________________________")
                    }
                    is Failure -> {
                        Snackbar.make(root, outcome.throwable.localizedMessage, Snackbar.LENGTH_SHORT).show()
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
                            recyclerView.bind(outcome.data, R.layout.layout_content){
                                txt_content.text = SpannableStringBuilder().bold { append(it.first).append(" : ").append(it.second.toString()).append(getString(R.string.repetition)) }
                            }
                        }
                        is Failure -> {
                            Snackbar.make(root, outcome.throwable.localizedMessage, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }
}
