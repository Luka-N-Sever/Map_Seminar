package luka.sever.map_seminar

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main_for_city.*


class SelectedCityActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object { val CHANNEL_ID = "servs" }

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    var city : String = ""
    var UserName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_city)

        UserName = intent.getStringExtra("UserName")
        val tv : TextView = findViewById(R.id.SelectedCity)
        val iv : ImageView = findViewById(R.id.City)
        tv.text = intent.getStringExtra("SeletedCity")

        city = tv.text.toString()

        when(city)
        {
            "Dubrovnik" -> iv.setImageResource(R.drawable.dubrovnik)
            "Ploce" -> iv.setImageResource(R.drawable.ploce)
            "Poreč" -> iv.setImageResource(R.drawable.porec)
            "Pula" -> iv.setImageResource(R.drawable.pula)
            "Rijeka" -> iv.setImageResource(R.drawable.rijeka)
            "Rovinj" -> iv.setImageResource(R.drawable.rovinj)
            "Split" -> iv.setImageResource(R.drawable.split)
            "Zadar" -> iv.setImageResource(R.drawable.zadar)
            "Zagreb" -> iv.setImageResource(R.drawable.zagreb)
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_MyCreds -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.drawer_layout, MyCreds.newInstance(city,"2"))
                    .addToBackStack("SlimShady")
                    .commit()
            }
            R.id.nav_ParkingLots -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.drawer_layout, Parking_Lots_Fragment.newInstance(city,"2"))
                    .addToBackStack("SlimShady")
                    .commit()
            }
            R.id.nav_PurchaseCred -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.drawer_layout, Purchase_Cred_Fragment.newInstance(city,"2"))
                    .addToBackStack("SlimShady")
                    .commit()
            }
            R.id.nav_SwitchCity -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.drawer_layout, Switch_Cities_Fragment.newInstance(UserName,"2"))
                    .addToBackStack("SlimShady")
                    .commit()
            }
            R.id.nav_update -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.drawer_layout, Update_Account_Fragment.newInstance(UserName,"2"))
                    .addToBackStack("SlimShady")
                    .commit()
            }
            R.id.nav_logout -> {
                val intent = Intent(this,
                    MainActivity::class.java)
                startActivity(intent)
            }
    }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
}
    fun SetCity(city : String)
    {
        //set the city
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}
