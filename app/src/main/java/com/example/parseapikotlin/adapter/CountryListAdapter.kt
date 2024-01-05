package com.example.parseapikotlin.adapter


import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parseapikotlin.R
import com.example.parseapikotlin.data.CountryModel

class CountryListAdapter(val activity: Activity): RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {

    private var countryList: List<CountryModel>? = null



    fun setCountryList(countryList: List<CountryModel>?) {
        this.countryList = countryList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list_row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListAdapter.MyViewHolder, position: Int) {
        holder.bind(countryList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        if(countryList == null)return 0
        else return countryList?.size!!
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val flagImage: ImageView = view.findViewById(R.id.flagImage)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvCapital: TextView = view.findViewById(R.id.tvCapital)
        private val tvRegion: TextView = view.findViewById(R.id.tvRegion)

        fun bind(data: CountryModel, activity: Activity) {
            // Set name, capital, and region
            tvName.text = data.name?.common ?: ""
            tvCapital.text = "Capital: " + (data.capital?.firstOrNull() ?: "")
            tvRegion.text = "Region: " + (data.region ?: "")

            // Load flag image URL
            val flagUrl: String? = data.flags?.png

            // Use Glide or any image-loading library to load the image
            if (!flagUrl.isNullOrEmpty()) {
                Glide.with(activity)
                    .load(flagUrl)
                    .into(flagImage)
            }
        }
    }

}