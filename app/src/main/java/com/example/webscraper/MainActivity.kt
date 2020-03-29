package com.example.webscraper

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.webscraper.data.repository.Repository
import com.example.webscraper.interactor.Interactor
import com.example.webscraper.data.repository.Failure
import com.example.webscraper.data.repository.Success
import com.example.webscraper.utilities.longToast
import com.list.rados.fast_list.bind
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_content.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var interactor: Interactor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        interactor = ViewModelProvider(this, InteractorViewModelFactory()).get(Interactor::class.java)

        loadData()
    }

    private fun loadData() {
        getTenthCharacter()

        getEveryTenthCharacter()

        getAllWordsAndTheirRepetitionCount()
    }

    private fun getTenthCharacter() {
        CoroutineScope(Dispatchers.Main).launch {
            interactor.scrapeTenthCharacter().observe(this@MainActivity, Observer { outcome ->
                when (outcome) {
                    is Success -> {
                        txt_first.text = SpannableStringBuilder().bold { append(outcome.data) }
                        txt_first.append("\n__________________________________________________")
                    }
                    is Failure -> {
                        longToast(outcome.throwable.localizedMessage ?: "")
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
                        txt_second.text = SpannableStringBuilder().bold { append(outcome.data) }
                        txt_second.append("\n__________________________________________________")
                    }
                    is Failure -> {
                        longToast(outcome.throwable.localizedMessage ?: "")
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
                            recyclerView.bind(outcome.data, R.layout.layout_content) {
                                txt_content.text = SpannableStringBuilder().bold {
                                    append(it.first).append(" : ").append(it.second.toString())
                                        .append(getString(R.string.repetition))
                                }
                            }
                        }
                        is Failure -> {
                            longToast(outcome.throwable.localizedMessage ?: "")
                        }
                    }
                })
        }
    }

    internal class InteractorViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Interactor(Repository()) as T
        }
    }
}
