package com.test.people.ui.popular

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.people.model.Valute
import com.test.people.ui.databinding.ItemRateBinding


class RatesAdapter(private val dataList: List<Valute> = emptyList(),
                   val actSelectRow: OnClickListener? = null) : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesAdapter.RatesViewHolder {
        val itemBinding = ItemRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(dataList[position], actSelectRow)
    }

    override fun getItemCount(): Int = dataList.size

    class RatesViewHolder(private val binding: ItemRateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(valute: Valute, onClickItem: OnClickListener?) {
            with(binding) {
                textValute.text = valute.name
                textRate.text = valute.rate.toString()
                containerMain.setOnClickListener(onClickItem)
            }
        }
    }
}


/*
    val onClickItem = OnClickListener {view ->
        if (view == null || view.tag == null)
            return@OnClickListener

        getSelected()?.selected = false

        val position = view.getTag() as Int
        val card: Card = dataList[position]
        card.selected = !card.selected
        notifyDataSetChanged()
        actSelectColumn.call()
    } // onClickItem
*/

   /* fun getSelected(): Card? {
        var result: Card? = null
        rx.Observable.from(dataList)
            .filter({card -> return@filter card.selected})
            .subscribe { card -> result = card }
        return result
    }*/
