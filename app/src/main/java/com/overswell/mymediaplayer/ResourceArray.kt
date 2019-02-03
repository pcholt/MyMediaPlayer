package com.overswell.mymediaplayer

import androidx.annotation.IdRes
import androidx.annotation.RawRes

data class ResourceArray(
    val resources: Array<ResourceEntry>
)

data class ResourceEntry(
    @RawRes val audioResource: Int,
    val labelButton: String
)