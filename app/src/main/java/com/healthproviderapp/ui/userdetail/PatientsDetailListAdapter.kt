package com.healthproviderapp.ui.userdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healthproviderapp.R
import com.healthproviderapp.roomdatabase.entity.User
import com.healthproviderapp.roomdatabase.entity.UserHealthRecords
import kotlinx.android.synthetic.main.item_patient_list.view.*
import kotlinx.android.synthetic.main.item_patientdetail_list.view.*


class PatientsDetailListAdapter(
        val context: Context,
        var mDataList: MutableList<UserHealthRecords>,
        private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patientdetail_list, parent, false)

        return MyVieHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mDataList[position]

        holder.itemView.txtReason.text = item.visitingreason
        holder.itemView.txtDiagnosis.text = item.diagnosis
        holder.itemView.txtSymptoms.text = item.symptoms
        holder.itemView.txtPrescription.text = item.prescription


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
