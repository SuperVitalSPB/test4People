package com.test.people.ui.sort

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.test.people.model.Valute
import com.test.people.ui.databinding.ItemRateBinding


class RatesSortAdapter(private val dataList: List<Valute> = emptyList()
    ) : RecyclerView.Adapter<RatesSortAdapter.SortsViewHolder>() {

    constructor(dataList: List<Valute> = emptyList(),
                typeSort: TypeSort) : this (dataList) {
        dataSort(typeSort)
    }

    lateinit var typeSort: TypeSort

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortsViewHolder {
        val itemBinding = ItemRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SortsViewHolder(itemBinding)
    }

    fun dataSort(typeSort: TypeSort) {
        this.typeSort = typeSort
        (dataList as ArrayList<Valute>).sortWith(Comparator { lhs, rhs ->
            // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
            (when (typeSort.typeField) {
                TypeField.tfByName -> if (lhs.name > rhs.name) -1 else 1
                TypeField.tfByValue -> if (lhs.rate > rhs.rate) -1 else 1
                else -> 0
            }) * if (typeSort.direction == DirectionSort.tfSortDown) 1 else -1
        })
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SortsViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }

    override fun getItemCount(): Int = dataList.size

    class SortsViewHolder(private val binding: ItemRateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(valute: Valute, position: Int) {
            with(binding) {
                textValute.text = valute.name
                textRate.text = valute.rate.toString()
                imageFavorite.isVisible = false
            }
        }
    }

    data class TypeSort (val typeField: TypeField, val direction: DirectionSort)
}