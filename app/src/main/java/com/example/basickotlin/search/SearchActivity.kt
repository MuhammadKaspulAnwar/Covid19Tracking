package com.example.basickotlin.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basickotlin.R
import com.example.basickotlin.dashboard.ProggresDialog
import com.example.basickotlin.model.ProvinsiResponse
import com.example.basickotlin.provinsi.ProvinsiActivity
import com.example.basickotlin.provinsi.ProvinsiAdapter
import com.example.basickotlin.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private var provinsiAdapter: ProvinsiAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        showDataSearch()
        rv_search1.layoutManager = LinearLayoutManager(this)
        rv_search2.layoutManager = LinearLayoutManager(this)


        back_search.setOnClickListener {
            val intent = Intent(this, ProvinsiActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showDataSearch() {
        val show = ProggresDialog(this)
        show.showLoading()
        ApiService.instance.getProvinsi().enqueue(object: Callback<ArrayList<ProvinsiResponse>>{
            override fun onResponse(call: Call<ArrayList<ProvinsiResponse>>, response: Response<ArrayList<ProvinsiResponse>>) {
                show.dismissLoading()
                val dataList = response.body()
                provinsiAdapter = dataList?.let { ProvinsiAdapter(it) }
                rv_search1.adapter = provinsiAdapter

                searchView.imeOptions = EditorInfo.IME_ACTION_SEARCH
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean = false

                    override fun onQueryTextChange(action: String?): Boolean {
                        if (action != null) {

                            if (action.isEmpty()) {
                                rv_search1.visibility = View.VISIBLE
                                rv_search2.visibility = View.GONE

                            }else if(action.length > 2) {
                                val filter = dataList?.filter { it.attributes.provinsi.contains("$action", true) }
                                provinsiAdapter = ProvinsiAdapter(filter as ArrayList<ProvinsiResponse>)

                                if (action.isNotEmpty()) {
                                    rv_search2.visibility = View.VISIBLE
                                    rv_search2.adapter = provinsiAdapter
                                    rv_search1.visibility = View.INVISIBLE
                                }else {
                                    rv_search1.visibility = View.VISIBLE
                                    rv_search2.visibility = View.GONE
                                }

                            }
                        }

                        return false
                    }

                })
            }

            override fun onFailure(call: Call<ArrayList<ProvinsiResponse>>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_LONG).show()
                show.showLoading()
            }

        })
    }

}