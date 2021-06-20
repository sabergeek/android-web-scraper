package com.example.webscraper.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.webscraper.presentation.viewmodels.ScraperViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ScraperViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn(viewModel)
        }
    }
}
