package com.example.paging3demo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paging3demo.R
import com.example.paging3demo.modelClass.Search
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.CharacterData
import java.util.ArrayList

class RecyclerviewAdapter : PagingDataAdapter<Search, RecyclerviewAdapter.MyViewHolder>(DiffUtilCallBack()) {


    //var dataAll: List<Search> = ArrayList<Search>()


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return MyViewHolder(itemView)
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val emailTv = view.findViewById<TextView>(R.id.email_tv)
        val firstName = view.findViewById<TextView>(R.id.firstname_tv)
        val circularIv = view.findViewById<CircleImageView>(R.id.circulariv)

        fun bind(data: Search) {
            firstName.text = data.Title
            emailTv.text = data.Year

            Glide
                .with(circularIv)
                .load(data.Poster)
                .centerCrop()
                .placeholder(R.drawable.loader)
                .into(circularIv);

        }
    }

    //check content of each row = DiffUtil

    class DiffUtilCallBack : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.Title == newItem.Title
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.Title == newItem.Title
                    && oldItem.Type == newItem.Year
        }

    }

}