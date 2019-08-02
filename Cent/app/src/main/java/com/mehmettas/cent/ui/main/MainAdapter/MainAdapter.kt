package com.mehmettas.cent.ui.main.MainAdapter

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mehmettas.cent.R
import com.mehmettas.cent.utils.extensions.inflate
import com.mehmettas.cent.data.remote.model.symbol.Currency
import com.mehmettas.cent.utils.extensions.trimForBothSides
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

            when(selectedCurrency%4)
            {
                0 -> itemView.ellipseCurrency.setImageResource(R.drawable.ellipse_black)
                1 -> itemView.ellipseCurrency.setImageResource(R.drawable.ellipse_blue)
                2 -> itemView.ellipseCurrency.setImageResource(R.drawable.ellipse_orange)
                3 -> itemView.ellipseCurrency.setImageResource(R.drawable.ellipse_purple)
            }

            textCurrencySymbol.text = currency.symbol
            textCurrencyName.text = currency.currencyName
            textCurrencyValue.text = trimForBothSides(currency.rateValue,1,1) // Kotlin Extension Used (String Extension)

            itemView.setOnClickListener {
                listener.onItemSelectedListener(currency)
            }
        }
    }

    interface MainListListener{
        fun onItemSelectedListener(currency:Currency)
    }
}


