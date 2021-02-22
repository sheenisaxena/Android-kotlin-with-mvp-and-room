package com.healthproviderapp.ui.dashboard

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetPatientsModel(
    val id: Int,
    val name: String
):Parcelable
