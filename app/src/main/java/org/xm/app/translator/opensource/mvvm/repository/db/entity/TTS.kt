package org.xm.app.translator.opensource.mvvm.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TTS() {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
    var content: String = ""
    var tl: String = ""
    var path: String = ""
}