package com.mehmettas.cent.ui.currencybase.currencybaseadapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehmettas.cent.R
import com.mehmettas.cent.data.remote.model.symbol.Currency
import com.mehmettas.cent.ui.main.MainAdapter.MainAdapter
import com.mehmettas.cent.utils.extensions.inflate
import kotlinx.android.synthetic.main.layout_base_item.view.*

class CurrencyBaseAdapter (
    private var items:ArrayList<Currency> = arrayListOf(),
    private var listener: CurrencyBaseAdapter.BaseAdapterListener
): RecyclerView.Adapter<CurrencyBaseAdapter.CurrencyBaseViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyBaseViewHolder {
        return CurrencyBaseViewHolder(parent.inflate(R.layout.layout_base_item))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CurrencyBaseViewHolder, position: Int) {
        holder.bind(getItem(position), listener, position)
    }

    fun addData(list: ArrayList<Currency>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int):Currency = items[position]

    inner class CurrencyBaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            currency: Currency,
            listener: BaseAdapterListener,
            selectedCurrency: Int
        ) = with(itemView) {

            textCurrencySymbol.text = currency.code
            textCurrencyName.text = currency.currencyName

            itemView.setOnClickListener {
                listener.onItemSelected(currency)
            }
        }
    }

    interface BaseAdapterListener{
        fun onItemSelected(currency:Currency)
    }
}