package com.barreto.playgroundtmdb

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.barreto.playgroundtmdb.repository.NetworkDataSource
import com.barreto.playgroundtmdb.services.WebApiAccess
import com.barreto.playgroundtmdb.ui.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val dataSource = NetworkDataSource(WebApiAccess.moviesApi)
                return MainViewModel(dataSource) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}