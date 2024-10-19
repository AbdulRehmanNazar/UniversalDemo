package com.universal.demo.utils

import android.view.View


/**
 * @Author: Abdul Rehman
 */


fun View.showViewVisible() {
    this.visibility = View.VISIBLE
}

fun View.showViewInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.showViewGone() {
    this.visibility = View.GONE
}