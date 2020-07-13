package luka.sever.map_seminar

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var username = intent.getStringExtra("UserName")

        //should be loaded from DB by user
        var Cities = arrayOf(
            "Poreč", "Zadar", "Zagreb", "Dubrovnik",
            "Split", "Ploče", "Rijeka", "Rovinj"
        )

        val adapter: ArrayAdapter<*> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cities)

        val lv : ListView = findViewById(R.id.list_view)
        lv.adapter = adapter

        lv.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            val intent = Intent(this, SelectedCityActivity::class.java)
            intent.putExtra("SeletedCity", adapterView.getItemAtPosition(i).toString())
            intent.putExtra("UserName",username)
            this.finish()
            startActivity(intent)
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
                Toast.makeText(this, "Creds clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_ParkingLots -> {
                Toast.makeText(this, "Lots clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_PurchaseCred -> {
                Toast.makeText(this, "Purchase clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
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
}
