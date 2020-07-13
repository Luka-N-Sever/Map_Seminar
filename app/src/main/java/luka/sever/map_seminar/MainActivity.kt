package luka.sever.map_seminar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email : EditText = findViewById(R.id.editText)
        val password : EditText = findViewById(R.id.editText2)
        val B : Button = findViewById(R.id.button)
        val B2 : Button = findViewById(R.id.SignUpButton)

        B.setOnClickListener {
            if(email.text.isNotEmpty()  && password.text.isNotEmpty())
            {
                //crosscheck database of users
                val db = DataBaseHandler(context = this)
                val us : User = db.getUser(email.text.toString(), password.text.toString())
                if(us.name == email.text.toString() && us.password == password.text.toString())
                {
                    val intent = Intent(this,
                        HomeActivity::class.java)
                    this.finish()
                    intent.putExtra("UserName", us.name)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this, "Please Enter Valid Things", Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(this, "Please Enter Things", Toast.LENGTH_LONG).show()
            }
        }

        B2.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, Create_Account_Fragment.newInstance("1","2"))
                .addToBackStack("SlimShady")
                .commit()
        }
    }
}
