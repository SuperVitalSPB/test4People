package com.test.people.ui.popular

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.people.model.Valute
import com.test.people.ui.R

class RatesAdapter(private val dataList: List<Valute> = emptyList(),
                   actSelectColumn: OnClickListener? = null) : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RatesViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val item = dataList[position]
        // holder.bind(item, position, onClickItem)
    }

    override fun getItemCount(): Int = dataList.size

    val onClickItem = OnClickListener {view ->
/*
        if (view == null || view.tag == null)
            return@OnClickListener

        getSelected()?.selected = false

        val position = view.getTag() as Int
        val card: Card = dataList[position]
        card.selected = !card.selected
        notifyDataSetChanged()
        actSelectColumn.call()
*/
    } // onClickItem

   /* fun getSelected(): Card? {
        var result: Card? = null
        rx.Observable.from(dataList)
            .filter({card -> return@filter card.selected})
            .subscribe { card -> result = card }
        return result
    }*/

    class RatesViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_rate, parent, false)) {

/*
        var container_main: LinearLayout? = null
        var number: TextView? = null

        init {
            container_main = itemView.findViewById(R.id.container_main)
            number = itemView.findViewById(R.id.number)
        }

        fun bind(card: Card, position: Int, onClickItem: View.OnClickListener?) {
            container_main?.background = card.getSelectedBackgroud(container_main?.context)
            number?.text = card.getNumberStar(number?.context)
            number?.setTextColor(number?.context?.resources!!.getColor(if (card.selected)
                R.color.Opti24_button_primary
            else R.color.Gray))
            container_main?.tag = position
            container_main?.setOnClickListener(onClickItem)
        }
*/

    }

} // class Adapter