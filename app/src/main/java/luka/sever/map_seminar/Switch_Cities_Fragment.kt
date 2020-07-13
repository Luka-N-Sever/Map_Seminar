package luka.sever.map_seminar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Switch_Cities_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Switch_Cities_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inf = inflater.inflate(R.layout.fragment_switch__cities_, container, false)
        val lv : ListView = inf.findViewById<ListView>(R.id.CityList)
        val tv : TextView = inf.findViewById(R.id.SCity)
        tv.text = "Select a City to Switch to"

        val db = DataBaseHandler(context = activity?.applicationContext)
        var cities = db.getAllCities()
        val adapter: CityListAdapter? =
            activity?.let { CityListAdapter(it, cities) }
        lv.adapter = adapter
        lv.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            val intent = Intent(this.context,
                SelectedCityActivity::class.java)
            intent.putExtra("SeletedCity", cities[i].name)
            intent.putExtra("UserName", param1)
            startActivity(intent)
        }
        return inf
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Switch_Cities_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Switch_Cities_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
