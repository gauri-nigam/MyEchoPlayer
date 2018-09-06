package com.internshala.echo.activities

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.internshala.echo.R
import com.internshala.echo.activities.MainActivity.Statified.drawerlayout
import com.internshala.echo.activities.MainActivity.Statified.drawerlayout
import com.internshala.echo.adapters.NavigationDrawerAdapter
import com.internshala.echo.fragments.MainScreen
import com.internshala.echo.fragments.SongsPlayingFragment

class MainActivity : AppCompatActivity(){
    var images_for_navDrawer= intArrayOf(R.drawable.navigation_allsongs,R.drawable.navigation_favorites,R.drawable.navigation_settings,R.drawable.navigation_aboutus)
    var navigationDrawerIconsList:ArrayList<String> = arrayListOf()
    var trackNotificationBuilder: Notification?= null

    object Statified{
        var notificationManager:NotificationManager?= null
        var drawerlayout: DrawerLayout?=null// to close drawer when clicled then linked to navigationDrawerActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar=findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)//defining a toolbar
        setSupportActionBar(toolbar)   //set toolbar to default toolbar
        MainActivity.Statified.drawerlayout= findViewById(R.id.drawer_layout)

        navigationDrawerIconsList.add("All Songs")
        navigationDrawerIconsList.add("Favourites")
        navigationDrawerIconsList.add("Settings")
        navigationDrawerIconsList.add("About Us")

        val toggle= ActionBarDrawerToggle(this@MainActivity,MainActivity.Statified.drawerlayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)//to make obect of action bar, toggle class
        MainActivity.Statified.drawerlayout?.setDrawerListener(toggle)//on clicking icon the drawer appears
        toggle.syncState()        //to animate hambuegericon, from 3 lines to an arrow

        val mainScreenFragment= MainScreen()
        this.supportFragmentManager
                .beginTransaction()
                .add (R.id.details_fragment, mainScreenFragment,"MainScreenFragment")
                .commit()//so all this is executable

        var _navigationAdapter = NavigationDrawerAdapter(navigationDrawerIconsList, images_for_navDrawer, this)
        _navigationAdapter.notifyDataSetChanged()//to that any changes in the list are updated

        var navigation_recycler_view= findViewById<RecyclerView>(R.id.navigation_recycler_view)
        navigation_recycler_view.layoutManager= LinearLayoutManager(this)
        navigation_recycler_view.itemAnimator=DefaultItemAnimator()

        navigation_recycler_view.adapter = _navigationAdapter//to link adapter to recycler view
        navigation_recycler_view.setHasFixedSize(true)//no operation would occur so it optimizes
    //    val intent = Intent(this@MainActivity,MainActivity::class.java)
     //   val pIntent = PendingIntent.getActivity(this@MainActivity,System.currentTimeMillis().toInt(), intent,0)
     //   trackNotificationBuilder= Notification.Builder(this)
     //           .setContentTitle("A track is playing in the Background")
    //            .setSmallIcon(R.drawable.echo_logo)
     //           .setContentIntent(pIntent)
    //            .setOngoing(true)
    //            .setAutoCancel(true)
    //            .build()
     //   Statified.notificationManager-getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    }

//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        setContentView(R.layout.activity_main)//linking it to main
//
//    }

    override fun onStart() {
        super.onStart()
       // try {
        //    Statified.notificationManager?.cancel(1978)
       // }catch (e:Exception){
       //     e.printStackTrace()
       // }
    }

 /*   override fun onStop() {
        super.onStop()
        try {
            if(SongsPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean){
                Statified.notificationManager?.notify(1978,trackNotificationBuilder)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            Statified.notificationManager?.cancel(1978)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }*/

}

