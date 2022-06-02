package com.diogojayme.tvmazeshows.activity.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.diogojayme.tvmazeshows.R
import com.diogojayme.tvmazeshows.activity.shows.SHOW_BUNDLE_KEY
import com.diogojayme.tvmazeshows.common.viewbinding.viewBinding
import com.diogojayme.tvmazeshows.databinding.ActivityDetailBinding
import com.diogojayme.tvmazeshows.model.view.Show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding(ActivityDetailBinding::inflate)
    private val viewModel: ShowsDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActionBar()
        setupView()
        setupObservers()
    }

    private fun extraData(): Show = intent.getSerializableExtra(SHOW_BUNDLE_KEY) as Show

    private fun setupActionBar() {
        setSupportActionBar(binding.detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
    }

    private fun setupObservers() {
        viewModel.rendererObserver.subscribe {
            when (it.contentType) {
                is ShowDetailsViewState.ContentType.LoadAliases -> updateAlias(it.contentType.aliases)
                ShowDetailsViewState.ContentType.EmptyAliases -> updateEmptyAliases()
            }
        }
    }

    private fun setupView() = with(binding) {
        val show = extraData()
        viewModel.loadAlias(show.id)
        showName.text = show.name

        show.summary?.let {
            showSummary.text = it
            showSummary.isVisible = true
        }

        show.rating.average?.let {
            showRating.text = it.toString()
        }

        showImageDetail.load(show.image?.original) {
            placeholder(R.drawable.movie_placeholder)
        }
    }

    private fun updateAlias(aliases: String) = with(binding) {
        showAliases.text = aliases
    }

    private fun updateEmptyAliases() = with(binding) {
        showAliases.text = getString(R.string.alias_not_found)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}