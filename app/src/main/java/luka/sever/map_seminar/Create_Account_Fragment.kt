    package luka.sever.map_seminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Create_Account_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Create_Account_Fragment : Fragment() {
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
        val inf = inflater.inflate(R.layout.fragment_create__account_, container, false)
        val tv : TextView = inf.findViewById(R.id.CreateEmail)
        val pv : TextView = inf.findViewById(R.id.CreatePass)
        val B : Button = inf.findViewById(R.id.Save)

        B.setOnClickListener {
            val db = DataBaseHandler(context = activity?.applicationContext)
                if(tv.text.isNotEmpty() && pv.text.isNotEmpty())
                    {
                        var NewUser : User = User(tv.text.toString(), pv.text.toString())
                        db.insertuser(NewUser)
                    }
            else{
                    Toast.makeText(
                        activity?.applicationContext,
                        "Cannot have null values!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
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
         * @return A new instance of fragment Create_Account_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Create_Account_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
