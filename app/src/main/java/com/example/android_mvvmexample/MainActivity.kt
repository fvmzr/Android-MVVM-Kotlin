package com.example.android_mvvmexample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_mvvmexample.network.Character
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.characterLiveData.observe(this, { state ->
            proccessCharacterResponse(state)


        })
    }

    private fun proccessCharacterResponse(state: ScreenState<List<Character>?>) {
        val pb = findViewById<ProgressBar>(R.id.progrssBar)

        when (state) {
            is ScreenState.Loading -> {
                pb.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                pb.visibility = View.GONE
                if (state.data != null) {
                    val adapter = MainAdapter(state.data)
                    val recyclerView = findViewById<RecyclerView>(R.id.charactersRV)
                    recyclerView?.layoutManager = StaggeredGridLayoutManager(
                        2, StaggeredGridLayoutManager.VERTICAL
                    )

                    recyclerView?.adapter = adapter
                }
            }

            is ScreenState.Error -> {
                pb.visibility = View.GONE
                val view = pb.rootView
                Snackbar.make(view, state.message!!, Snackbar.LENGTH_LONG).show()

            }
        }
    }
}