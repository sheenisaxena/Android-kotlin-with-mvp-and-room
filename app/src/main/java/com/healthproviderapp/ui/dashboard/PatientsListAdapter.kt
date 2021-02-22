package com.healthproviderapp.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healthproviderapp.R
import com.healthproviderapp.roomdatabase.entity.User
import kotlinx.android.synthetic.main.item_patient_list.view.*


class PatientsListAdapter(
        val context: Context,
        var mDataList: MutableList<User>,
        private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient_list, parent, false)

        return MyVieHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mDataList[position]

        holder.itemView.txtUserName.text = item.name

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    interface OnItemClickListener {
        fun onItemClick(pos: Int)
    }

    class MyVieHolder(val mView: View) : RecyclerView.ViewHolder(mView)

    class MyProgressHolder(val mView: View) : RecyclerView.ViewHolder(mView)
}
