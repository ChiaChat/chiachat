package org.chiachat.app.user

import com.soywiz.korio.file.VfsFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    val username: String,
    val fingerprint: String,
    val profileImage: VfsFile,
) {
    private val imageData = MutableStateFlow<ByteArray?>(null)

    fun loadImage(): StateFlow<ByteArray?>{
        if(imageData.value == null){
            CoroutineScope(Dispatchers.Default).launch {
                imageData.value = profileImage.readAll()
            }
        }
        return imageData.asStateFlow()
    }
}