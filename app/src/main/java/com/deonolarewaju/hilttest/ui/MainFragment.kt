package com.deonolarewaju.hilttest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.deonolarewaju.hilttest.R
import com.deonolarewaju.hilttest.repository.MainRepository
import java.util.*

class MainFragment constructor(
    private val someString: String
//    private val someObject: Object,
//    private val mainRepository: MainRepository
) : Fragment(R.layout.fragment_main) {
    private val TAG = "MainFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ${someString}")
    }

}