package com.flyige.projectdrt

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
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
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), DailyInfoFragmentListener {

    lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val TAG: String = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBase()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        binding.appBarMain.fabAddDailyInfo.setOnClickListener { view ->
//            showDailyInfoDialog()
            // TODO: 直接显示composable的话UI有问题，有啥好办法跳过 fragment 这一层
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

    fun initDataBase() {
        // CHECKPOINT: By:Yige 需要检查数据库是否创建吗？
        database =
            Room.databaseBuilder(applicationContext, DRTDataBase::class.java, DATABASE_NAME)
                .build()
    }

    override fun onResult(result: String) {
        LogUtil.d("fyg","爷收到了: $result")

    }
}