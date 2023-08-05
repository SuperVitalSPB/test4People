package com.test.people.ui.popular

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.people.model.Valute
import com.test.people.ui.databinding.ItemRateBinding


class RatesAdapter(private val dataList: List<Valute> = emptyList(),
                   private val actChangedFavorite : (valute: Valute) -> Unit = { _ -> }
                  ) : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesAdapter.RatesViewHolder {
        val itemBinding = ItemRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(dataList[position], position, onClickItem)
    }

    override fun getItemCount(): Int = dataList.size

    val onClickItem = OnClickListener {view ->
        if (view == null || view.tag == null)
            return@OnClickListener

        val position = view.getTag() as Int
        val valute = dataList[position]
        valute.isFavorite = !valute.isFavorite
        notifyItemChanged(position)
        actChangedFavorite(valute)
    }

    class RatesViewHolder(private val binding: ItemRateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(valute: Valute, position: Int, onClickItem: OnClickListener?) {
            with(binding) {
                textValute.text = valute.name
                textRate.text = valute.rate.toString()
                imageFavorite.apply {
                    setImageResource(
                        if (valute.isFavorite)
                            android.R.drawable.star_on
                        else
                            android.R.drawable.star_off
                    )
                    tag = position
                    setOnClickListener(onClickItem)
                }
            }
        }
    }
}