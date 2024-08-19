package com.example.xevents

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.xevents.Constants.Constants
import com.example.xevents.Views.Fragments.HeaderFragment
import com.example.xevents.Views.Fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private var homeFragmentTransactionId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val bundle = Bundle()
        moveFragment(bundle, HomeFragment())
    }

    fun moveFragment(
        bundle: Bundle,
        targetFragment: XEFragment,
    ): Boolean {
        return this.moveFragment(false, bundle, targetFragment,  true)
    }


    fun moveFragment(
        replaceHdrFragment: Boolean,
        bundle: Bundle,
        targetFragment: XEFragment,
        animate: Boolean
    ): Boolean {
        try {
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
          /*  if (transaction.isAddToBackStackAllowed){
                transaction.addToBackStack()
            }*/
            if(hdrFragment != null){
                transaction.replace(headerResID(), hdrFragment)
            }

            if (animate) {
                transaction.setCustomAnimations(
                    R.anim.in_from_right,
                    R.anim.out_to_left,
                    R.anim.in_from_left,
                    R.anim.out_to_right
                )
            }
            if (!disallowedBackStack && targetFragment.targetFragment == null) {
                targetFragment.setTargetFragment(
                    getPageFragment(),
                    Constants.REQ_PAGE_COMMUNICATOR
                )
            }
            if (commitTransaction(transaction, targetFragment)) {
                //application().setLastPageID(pageFragment.getClass().getSimpleName())
                return true
            }
        } catch (e: Exception) {

        }
        return false
    }

    private fun commitTransaction(
        fragmentTransaction: FragmentTransaction,
        pageFragment: XEFragment
    ): Boolean {
        return try {
            val transactionId = fragmentTransaction.commit()
            if (pageFragment is HomeFragment) {
                homeFragmentTransactionId = transactionId
            }
            /*this.closeDrawers()*/
            true
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            false
        }
    }

    fun headerResID(): Int {
       return try {
            return R.id.headerView;
        } catch (e: NoSuchFieldError) {
            0
        }
    }

    open fun getPageFragment(): XEFragment? {
        val fragment: Fragment? = findFragmentById(dataContainerResID())
        return if (fragment != null) fragment as XEFragment else null
    }

    open fun findFragmentById(fragmentContainerID: Int): Fragment? {
        return this.supportFragmentManager.findFragmentById(fragmentContainerID)
    }

    @IdRes
    protected open fun dataContainerResID(): Int {
        return try {
            R.id.fragmentContainer
        } catch (e: NoSuchFieldError) {
            0
        }
    }
}