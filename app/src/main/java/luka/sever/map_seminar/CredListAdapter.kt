package luka.sever.map_seminar

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.*
class CredListAdapter(private val context: Activity, private val Creds : MutableList<Credential>)
    : ArrayAdapter<Credential>(context, R.layout.custom_list_item, Creds) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list_item_for_cred, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        val ib = rowView.findViewById(R.id.icon) as ImageView
        val activatebutton = rowView.findViewById(R.id.activatebutton) as Button

        titleText.text = Creds[position].name
        subtitleText.text = Creds[position].details

        activatebutton.setOnClickListener {
            val intent = Intent(this.context, ActivateCredService::class.java)
            intent.putExtra(EXTRA_PARAM1, Creds[position].name)
            intent.action = ACTION_FOO
            this.context.startService(intent)
        }

        return rowView
    }
}