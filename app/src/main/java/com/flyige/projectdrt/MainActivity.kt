package com.flyige.projectdrt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.flyige.projectdrt.constants.DATABASE_NAME
import com.flyige.projectdrt.database.DRTDataBase
import com.flyige.projectdrt.database.DRTDataBase.Companion.database
import com.flyige.projectdrt.databinding.ActivityMainBinding
import com.flyige.projectdrt.fragments.DailyInfoDialogFragment
import com.flyige.projectdrt.listener.DailyInfoFragmentListener
import com.flyige.projectdrt.utils.LogUtil
import com.flyige.projectdrt.viewmodels.MainActivityViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), DailyInfoFragmentListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewmodel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(
            MainActivityViewModel::class.java
        )
    }
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        binding.appBarMain.fabAddDailyInfo.setOnClickListener { view ->
            val dailyInfoDialogFragment = DailyInfoDialogFragment()
            dailyInfoDialogFragment.listener = this
            dailyInfoDialogFragment.show(supportFragmentManager, TAG)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun init() {
        // CHECKPOINT: By:Yige 需要检查数据库是否创建吗？
        database =
            Room.databaseBuilder(applicationContext, DRTDataBase::class.java, DATABASE_NAME).build()
    }

    override fun onResume() {
        super.onResume()
        viewmodel.loadData()
    }

    override fun onResult(result: String) {
        LogUtil.i(TAG, "数据库操作结果：$result")
        viewmodel.loadData()
    }
}