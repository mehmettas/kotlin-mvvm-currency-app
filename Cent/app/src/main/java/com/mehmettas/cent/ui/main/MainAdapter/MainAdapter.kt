package com.mehmettas.cent.ui.main.MainAdapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehmettas.cent.R
import com.mehmettas.cent.utils.inflate
import java.util.*
import com.mehmettas.cent.data.remote.model.symbol.Currency
import kotlinx.android.synthetic.main.layout_item_currency.view.*
import kotlin.collections.ArrayList

class MainAdapter(
    private var items: ArrayList<Currency> = arrayListOf(),
    private var listener: MainListListener
): RecyclerView.Adapter<MainAdapter.MainViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent.inflate(R.layout.layout_item_currency))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), listener, position)
    }

    fun addData(list: ArrayList<Currency>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int):Currency = items[position]

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            currency: Currency,
            listener: MainListListener,
            selectedCurrency: Int
        ) = with(itemView) {

            textCurrencySymbol.text = currency.symbol
            textCurrencyName.text = currency.currencyName
            textCurrencyValue.text = currency.rateValue

            itemView.setOnClickListener {
                listener.onItemSelectedListener(currency)
            }
        }
    }

    interface MainListListener{
        fun onItemSelectedListener(currency:Currency)
    }
}


