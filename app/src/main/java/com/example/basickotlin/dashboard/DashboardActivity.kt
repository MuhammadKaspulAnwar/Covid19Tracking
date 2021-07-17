package com.example.basickotlin.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.basickotlin.R
import com.example.basickotlin.model.IndonesiaResponse
import com.example.basickotlin.provinsi.ProvinsiActivity
import com.example.basickotlin.retrofit.ApiService
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style
import kotlinx.android.synthetic.main.activity_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private var mapView: MapView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_dashboard)


        btn_next.setOnClickListener {
            val intent = Intent(this,ProvinsiActivity::class.java)
            startActivity(intent)
            finish()
        }

            showDataIndonesia()
            mapView = findViewById(R.id.mapView)
            mapView!!.onCreate(savedInstanceState)
            mapView!!.getMapAsync {
                it.setStyle(Style.TRAFFIC_NIGHT)

                val location = LatLng(-4.249598664587171, 120.42506537040698)
                val position = CameraPosition.Builder()
                        .target(LatLng(-4.249598664587171, 120.42506537040698))
                        .zoom(3.0)
                        .tilt(10.0)
                        .bearing(5.0)
                        .build()

                it.animateCamera(CameraUpdateFactory.newCameraPosition(position), 5000)
                it.addMarker(MarkerOptions().setPosition(location).title("Indonesia"))

            }


            Glide.with(this)
                    .load("https://firebasestorage.googleapis.com/v0/b/gambartraveling.appspot.com/o/Corona_virus_Awareness_1.png?alt=media&token=550efca3-e8f4-4df7-a4ac-4e2bb0613f11")
                    .placeholder(R.drawable.animation_loader)
                    .into(iv_poster)

        }


    private fun showDataIndonesia() {
        val show = ProggresDialog(this)
        show.showLoading()
            ApiService.instance.getIndonesia().enqueue(object : Callback<ArrayList<IndonesiaResponse>> {
                override fun onResponse(call: Call<ArrayList<IndonesiaResponse>>, response: Response<ArrayList<IndonesiaResponse>>) {
                    show.dismissLoading()
                    if (response.isSuccessful) {
                        val indonesiaResponse = response.body()!![0]
                        tv_sembuh.text = indonesiaResponse.sembuh
                        tv_name.text = indonesiaResponse.name
                        tv_positif.text = indonesiaResponse.positif
                        tv_meninggal.text = indonesiaResponse.meninggal
                    }
                }

                override fun onFailure(call: Call<ArrayList<IndonesiaResponse>>, t: Throwable) {
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_LONG).show()
                    show.showLoading()
                }

            })
        }


}









