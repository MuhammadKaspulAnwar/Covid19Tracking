package com.example.basickotlin.provinsi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basickotlin.R
import com.example.basickotlin.model.ProvinsiResponse

class ProvinsiAdapter(private var dataList: ArrayList<ProvinsiResponse>): RecyclerView.Adapter<ProvinsiAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinsiAdapter.ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val infaltedView = layoutInflater.inflate(R.layout.provinsi_item_adapter, parent, false)
        return ViewHolder(infaltedView)
    }

    override fun onBindViewHolder(holder: ProvinsiAdapter.ViewHolder, position: Int) {
       holder.bindItem(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private var city : TextView = view.findViewById(R.id.tv_provinsi)
        private var recovery : TextView = view.findViewById(R.id.tv_recovery)
        private var dead : TextView = view.findViewById(R.id.tv_dead)
        private var positive : TextView = view.findViewById(R.id.tv_positive)
        private var codeProvin : TextView = view.findViewById(R.id.tv_code)
        private var fid : TextView = view.findViewById(R.id.tv_fid)


        fun bindItem(provinsiResponse: ProvinsiResponse) {
            city.text = provinsiResponse.attributes.provinsi
            recovery.text = provinsiResponse.attributes.sembuh.toString()
            dead.text = provinsiResponse.attributes.meninggal.toString()
            positive.text = provinsiResponse.attributes.positif.toString()
            codeProvin.text = provinsiResponse.attributes.kode.toString()
            fid.text = provinsiResponse.attributes.fid.toString()

        }

    }
}