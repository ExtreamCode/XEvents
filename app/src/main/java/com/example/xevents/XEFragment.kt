package com.example.xevents

import android.os.Bundle
import androidx.fragment.app.Fragment

open class XEFragment(resFragment: Int): Fragment(resFragment) {

    open fun moveFragment(
        fragmnet: XEFragment,
        bundle: Bundle?
    ) {
        moveFragment(fragmnet, bundle)
    }
}