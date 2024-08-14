package com.example.xevents

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xevents.Views.Fragments.HeaderFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }

    open fun moveFragment(
        replaceHdrFragment: Boolean,
        bundle: Bundle,
        targetFragment: XEFragment
    ){
        val fn = this.supportFragmentManager
        val transaction = fn.beginTransaction()
        val disallowedBackStack = false
        if (disallowedBackStack) {
             transaction.disallowAddToBackStack()
        }
        val hdrFragment: XEFragment? =if (replaceHdrFragment) HeaderFragment() else null
        if(bundle != null){
            targetFragment.arguments = bundle
            if (hdrFragment != null){
                hdrFragment.arguments = bundle
            }
        }
        if (transaction.isAddToBackStackAllowed){
            /*transaction.addToBackStack()*/
        }
        if(hdrFragment != null){
            /*transaction.replace()*/
        }
    }
}