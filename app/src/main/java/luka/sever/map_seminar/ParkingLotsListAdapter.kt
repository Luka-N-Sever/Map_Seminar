package luka.sever.map_seminar

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
class ParkingLotsListAdapter(private val context: Activity, private val Lots : MutableList<ParkingLot>)
    : ArrayAdapter<ParkingLot>(context, R.layout.custom_list_item, Lots) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list_item, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        val ib = rowView.findViewById(R.id.icon) as ImageView

        titleText.text = Lots[position].name
        subtitleText.text = Lots[position].deets

        return rowView
    }
}