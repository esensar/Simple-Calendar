package com.simplemobiletools.calendar.pro.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import com.simplemobiletools.calendar.pro.R

class SelectTimeZoneActivity : SimpleActivity() {
    private var mIsSearchOpen = false
    private var mSearchMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_time_zone)
        title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_select_time_zone, menu)
        setupSearch(menu)
        updateMenuItemColors(menu)
        return true
    }

    private fun setupSearch(menu: Menu) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        mSearchMenuItem = menu.findItem(R.id.search)
        (mSearchMenuItem!!.actionView as SearchView).apply {
            queryHint = getString(R.string.enter_a_country)
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isSubmitButtonEnabled = false
            isIconified = false
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String) = false

                override fun onQueryTextChange(newText: String): Boolean {
                    if (mIsSearchOpen) {
                        searchQueryChanged(newText)
                    }
                    return true
                }
            })
        }

        mSearchMenuItem!!.expandActionView()
        MenuItemCompat.setOnActionExpandListener(mSearchMenuItem, object : MenuItemCompat.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                mIsSearchOpen = true
                searchQueryChanged("")
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                mIsSearchOpen = false
                finish()
                return true
            }
        })
    }

    private fun searchQueryChanged(text: String) {

    }
}
