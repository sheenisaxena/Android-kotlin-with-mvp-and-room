package com.healthproviderapp.ui.visitingreason

import com.healthproviderapp.base.LifecycleAwarePresenter
import com.healthproviderapp.utils.SharedPref

class VisitingReasonPresenter(
        val mView: VisitingReasonContract.VisitingReasonView,
        val sharedPreference: SharedPref
) : LifecycleAwarePresenter<VisitingReasonContract.VisitingReasonView>(mView),
    VisitingReasonContract.VisitingReasonInterface {

}