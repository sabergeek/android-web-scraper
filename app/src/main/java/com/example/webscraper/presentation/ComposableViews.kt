package com.example.webscraper.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.webscraper.data.repository.Failure
import com.example.webscraper.data.repository.Success
import com.example.webscraper.presentation.viewmodels.ScraperViewModel

@Composable
fun LazyColumn(scraperViewModel: ScraperViewModel) {
    when (val response =
        scraperViewModel.scrapeAllWordsAndTheirRepetitionCount.observeAsState().value) {
        is Success -> {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(items = response.data, itemContent = { item ->
                    Text(
                        text = item,
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp, bottom = 6.dp, start = 8.dp, end = 8.dp)
                    )
                })
            }
        }
        is Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.throwable.message ?: "¯\\_(ツ)_/¯ Network made an oopsie..",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}