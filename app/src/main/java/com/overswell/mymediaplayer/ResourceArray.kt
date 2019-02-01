package com.overswell.mymediaplayer

data class ResourceArray(
    val resources: Array<Int>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceArray

        if (!resources.contentEquals(other.resources)) return false

        return true
    }

    override fun hashCode(): Int {
        return resources.contentHashCode()
    }
}
