package com.mehmettas.cent.ui.main.MainAdapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehmettas.cent.R
import com.mehmettas.cent.utils.extensions.inflate
import com.mehmettas.cent.data.remote.model.symbol.Currency
import com.mehmettas.cent.utils.AppConstants
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

            when(selectedCurrency%AppConstants.DRAWEBLE_AMOUNT)
            {
                0 -> {
                    itemView.ellipseCurrency.setImageResource(R.drawable.ellipse_black)
                    currency.currencyDraweble = AppConstants.BLACK
                }
                1 -> {
                    itemView.ellipseCurrency.setImageResource(R.drawable.ellipse_blue)
                    currency.currencyDraweble = AppConstants.BLUE
                }
                2 -> {
                    itemView.ellipseCurrency.setImageResource(R.drawable.ellipse_orange)
                    currency.currencyDraweble = AppConstants.ORANGE
                }
                3 -> {
                    itemView.ellipseCurrency.setImageResource(R.drawable.ellipse_purple)
                    currency.currencyDraweble = AppConstants.PURPLE
                }
            }

            textCurrencySymbol.text = currency.symbol
            textCurrencyName.text = currency.currencyName
            textCurrencyValue.text = "%.3f".format(trimForBothSides(currency.rateValue,1,1).toDouble())  // Kotlin Extension Used (String Extension)

            var rateValue:Double =  trimForBothSides(currency.rateValue,1,1).toDouble()
            var previousDayValue:Double = trimForBothSides(currency.previousDayValue,1,1).toDouble()
            var upAndDownPercent = configureUpDownText(rateValue,previousDayValue)
            if (upAndDownPercent.toDouble()<0)
            {
                textCurrencyUpAndDown.setTextColor(resources.getColor(R.color.decreasing_color))
                textCurrencyUpAndDown.text = "- ${upAndDownPercent.toDouble()*-1}%"
            }
            else
            {
                textCurrencyUpAndDown.setTextColor(resources.getColor(R.color.rising_color))
                textCurrencyUpAndDown.text = "+ ${upAndDownPercent.toDouble()}%"
            }

            currency.percentDifferenceValue = upAndDownPercent

            itemView.setOnClickListener {
                listener.onItemSelectedListener(currency)
            }
        }
    }

    private fun configureUpDownText(rateValue:Double,previousDayValue:Double): String
    {
        var percentDifference = ((rateValue - previousDayValue)/rateValue)*100
        return (Math.round(percentDifference * 10.0) / 10.0).toString()
    }

    interface MainListListener{
        fun onItemSelectedListener(currency:Currency)
    }
}


