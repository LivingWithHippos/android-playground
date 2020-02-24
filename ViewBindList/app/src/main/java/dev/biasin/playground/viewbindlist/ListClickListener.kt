package dev.biasin.playground.viewbindlist

// this interface must be implemented in fragment/ listener listening to taps on lists and passed to the adapter constructor

interface ListClickListener {
    fun onListClick(response: ListResponse)
}

// this interface is used to return every kind of object implementing it
interface  ListResponse
