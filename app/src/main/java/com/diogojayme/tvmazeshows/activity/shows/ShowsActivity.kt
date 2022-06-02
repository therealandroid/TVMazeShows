package com.diogojayme.tvmazeshows.activity.shows

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.diogojayme.tvmazeshows.common.viewbinding.viewBinding
import com.diogojayme.tvmazeshows.databinding.ActivityShowsBinding
import com.diogojayme.tvmazeshows.model.view.Show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowsActivity : AppCompatActivity() {

    private val viewModel: ShowsViewModel by viewModels()
    private val binding: ActivityShowsBinding by viewBinding(ActivityShowsBinding::inflate)

    private val showsAdapter = ShowsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setupObservers()
    }

    private fun setupView() = with(binding) {
        setSupportActionBar(binding.showsToolbar)
        showsRecycler.adapter = showsAdapter
        showsRecycler.layoutManager =
            GridLayoutManager(this@ShowsActivity, 2) // I opted in grid instead list
    }

    private fun setupObservers() {
        viewModel.rendererObserver.subscribe {
            when (it.contentType) {
                is ShowsViewState.ContentType.ListShows -> renderShows(it.contentType.shows)
                else -> {
                    //TODO: display a cool error view
                }
            }
        }

        viewModel.firstSearch()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderShows(shows: List<Show>) {
        showsAdapter.addShows(shows)
        showsAdapter.notifyDataSetChanged() // for simplicity I'm using notifyDataSetChanged, but for performance it's worth using DIFFUtil
    }

}