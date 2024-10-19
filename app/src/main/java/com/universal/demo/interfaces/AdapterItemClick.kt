package com.universal.demo.interfaces

/**
 * @Author: Abdul Rehman
 */

interface AdapterItemClick<T> {
    fun onItemClick(item: T?, position: Int, type: String)
}