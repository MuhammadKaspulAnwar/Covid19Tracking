package com.example.basickotlin.provinsi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.basickotlin.R
import com.example.basickotlin.dashboard.DashboardActivity
import com.example.basickotlin.dashboard.ProggresDialog
import com.example.basickotlin.model.ProvinsiResponse
import com.example.basickotlin.retrofit.ApiService
import com.example.basickotlin.search.SearchActivity
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style
import kotlinx.android.synthetic.main.activity_provinsi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinsiActivity : AppCompatActivity() {

    private var mapProvinsi : MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_provinsi)


        showDataProvinsi()

        mapProvinsi = findViewById(R.id.map_pronvisi)
        mapProvinsi?.onCreate(savedInstanceState)
        mapProvinsi?.getMapAsync{
            it.setStyle(Style.TRAFFIC_NIGHT)

            val location = LatLng(-1.9679570196714051, 114.8879563571362)
            val position = CameraPosition.Builder()
                    .target(LatLng(-1.9679570196714051, 114.8879563571362))
                    .zoom(4.3)
                    .tilt(20.0)
                    .bearing(10.0)
                    .build()

            it.animateCamera(CameraUpdateFactory.newCameraPosition(position), 5000)
            it.addMarker(MarkerOptions().setPosition(location).title("Data Covid Indonesia"))
        }


        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/gambartraveling.appspot.com/o/VECTEEZY_COVID_TECH_DRIVE_IN_THEATER_ILLUSTRATION_PS0321_generated.jpg?alt=media&token=a7dd20bb-6f05-4171-91ba-1af3dc59515f")
                .placeholder(R.drawable.animation_loader)
                .into(iv_provinsi)


        btn_back.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun showDataProvinsi() {
        val show = ProggresDialog(this)
        show.showLoading()
        rv_provinsi.setHasFixedSize(true)
        rv_provinsi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        ApiService.instance.getProvinsi().enqueue(object :Callback<ArrayList<ProvinsiResponse>>{
            override fun onResponse(call: Call<ArrayList<ProvinsiResponse>>, response: Response<ArrayList<ProvinsiResponse>>) {
                show.dismissLoading()
                val dataList = response.body()
                val setAdapter = dataList?.let { ProvinsiAdapter(it) }

                rv_provinsi.adapter = setAdapter
            }

            override fun onFailure(call: Call<ArrayList<ProvinsiResponse>>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_LONG).show()
                show.showLoading()
            }

        })
    }


}