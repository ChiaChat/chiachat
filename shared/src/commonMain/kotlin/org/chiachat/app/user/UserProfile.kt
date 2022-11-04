package org.chiachat.app.user

import com.soywiz.korio.file.VfsFile

data class UserProfile(
    val username: String,
    val fingerprint: String,
    val profileImage: VfsFile,
)
