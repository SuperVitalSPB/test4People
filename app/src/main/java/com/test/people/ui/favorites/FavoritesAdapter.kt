package com.test.people.ui.favorites

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.people.db.Favorites
import com.test.people.ui.databinding.ItemRateBinding


class FavoritesAdapter(private val dataList: List<Favorites> = emptyList(),
                       private val actChangedFavorite : (favorites: Favorites) -> Unit = { _ -> }
                  ) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val itemBinding = ItemRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(dataList[position], position, onClickItem)
    }

    override fun getItemCount(): Int = dataList.size

    val onClickItem = OnClickListener {view ->
        if (view == null || view.tag == null)
            return@OnClickListener

        val position = view.getTag() as Int
        val favorite = dataList[position]
        notifyItemRemoved(position)
        actChangedFavorite(favorite)
    }

    class FavoritesViewHolder(private val binding: ItemRateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favorites: Favorites, position: Int, onClickItem: OnClickListener?) {
            with(binding) {
                textValute.text = favorites.name
                textRate.text = favorites.rate.toString()
                imageFavorite.apply {
                    setImageResource(android.R.drawable.star_on)
                    tag = position
                    setOnClickListener(onClickItem)
                }
            }
        }
    }
}