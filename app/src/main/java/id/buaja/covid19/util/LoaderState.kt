/*
 * Copyright 2020 buaja, id.
 * Created By Julsapargi Nursam 3/2/20 2:40 PM
 * Last modified 3/2/20 2:40 PM
 */

package id.buaja.covid19.util

/**
 * Copyright 2020 WOWBid Perintis Nusantara, PT.
 *
 * Created By Julsapargi Nursam 3/2/20
 */

sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}