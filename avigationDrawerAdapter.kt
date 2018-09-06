package com.internshala.echo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.internshala.echo.R
import com.internshala.echo.activities.MainActivity
import com.internshala.echo.fragments.AboutUsFragment
import com.internshala.echo.fragments.FavouriteFragment
import com.internshala.echo.fragments.MainScreen
import com.internshala.echo.fragments.SettingsFragments

/**
 * Created by Gauri Nigam on 12/23/2017.
 */
class NavigationDrawerAdapter(_contentList: ArrayList<String>,_getImages:IntArray,_context:Context):RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>()
{
    var contentList: ArrayList<String>?=null
    var getImages: IntArray?=null
    var mContext: Context?= null

    init {
        this.contentList=_contentList
        this.getImages= _getImages
        this.mContext=_context
    }


    override fun onBindViewHolder(holder: NavViewHolder?, position: Int) {
        holder?.icon_GET?.setBackgroundResource(getImages?.get(position) as Int)
        holder?.text_GET?.text = contentList?.get(position)
        holder?.contentHolder?.setOnClickListener({
            if(position==0)
            {
                val mainScreenFragment =MainScreen()
                (mContext as MainActivity).supportFragmentManager//cause SFM is made in main activity.kt
                        .beginTransaction()
                        .replace(R.id.details_fragment, mainScreenFragment)
                        .commit()
            }
            else if (position==1)
            {
                val favouriteFragment =FavouriteFragment()
                (mContext as MainActivity).supportFragmentManager//cause SFM is made in main activity.kt
                        .beginTransaction()
                        .replace(R.id.details_fragment, favouriteFragment)
                        .commit()
            }
            else if(position==2)
            {
                val settingsFragment =SettingsFragments()
                (mContext as MainActivity).supportFragmentManager//cause SFM is made in main activity.kt
                        .beginTransaction()
                        .replace(R.id.details_fragment, settingsFragment)
                        .commit()
            }
            else
            {
                val aboutUsFragment =AboutUsFragment()
                (mContext as MainActivity).supportFragmentManager//cause SFM is made in main activity.kt
                        .beginTransaction()
                        .replace(R.id.details_fragment, aboutUsFragment)
                        .commit()
            }
            MainActivity.Statified.drawerlayout?.closeDrawers()// to close drawer when clicked

        })

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NavViewHolder {
        var itemView= LayoutInflater.from(parent?.context).inflate(R.layout.row_custom_navigationdrawer, parent, false)
        val returnThis = NavViewHolder(itemView)
        return returnThis
        //val itemView=LayoutInflater.from(parent!!.context)
        //       .inflate(R.layout.row_custom_navigationdrawer,parent,false)
       // return NavViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return contentList?.size as Int
    }

    /*inner*/ class NavViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)//constructor which contains an itemView object of view type and ViewHolder would contain a parameter
    {
        var icon_GET: ImageView?=null
        var text_GET: TextView?=null
        var contentHolder:RelativeLayout?=null

        init {
            icon_GET=itemView?.findViewById(R.id.icon_navdrawer)
            text_GET=itemView?.findViewById(R.id.text_navdrawer)
            contentHolder=itemView?.findViewById(R.id.navdrawer_item_content_holder)
        }


    }
}