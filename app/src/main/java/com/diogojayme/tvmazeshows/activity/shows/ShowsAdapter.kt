package com.diogojayme.tvmazeshows.activity.shows

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.diogojayme.tvmazeshows.R
import com.diogojayme.tvmazeshows.activity.detail.ShowDetailActivity
import com.diogojayme.tvmazeshows.databinding.ItemShowBinding
import com.diogojayme.tvmazeshows.model.view.Show

const val SHOW_BUNDLE_KEY = "data"

class ShowsAdapter : RecyclerView.Adapter<ShowsViewHolder>() {

    private val showsList: MutableList<Show> = mutableListOf()

    fun addShows(shows: List<Show>) {
        showsList.addAll(shows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShowsViewHolder(
        ItemShowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        holder.bind(showsList[position])
    }

    override fun getItemCount() = showsList.size

}

class ShowsViewHolder(private val binding: ItemShowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(show: Show) = with(binding) {
        itemShowContent.setOnClickListener {
            // because i'm not using fragments, I will opt-out for navigation
            it.context.startActivity(
                Intent(it.context, ShowDetailActivity::class.java).apply {
                    putExtra(SHOW_BUNDLE_KEY, show)
                }
            )
        }

        showImage.load(show.image?.medium) {
            crossfade(true)
            placeholder(R.drawable.movie_placeholder)
        }

        showName.text = show.name
        //just for simplicity and because we just need to know if show ended, I'll just check if there is an end date and set the visibility
        showStatus.visibility = if (show.ended != null) View.VISIBLE else View.GONE
    }
}
