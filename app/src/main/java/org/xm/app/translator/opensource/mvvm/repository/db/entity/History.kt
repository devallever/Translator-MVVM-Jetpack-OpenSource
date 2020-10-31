package org.xm.app.translator.opensource.mvvm.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class History {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    var srcText: String = ""
    var sl: String = "en"
    var tl: String = "en"
    var time: Long = 0
    var liked: Int = 0
    //全路径
    var ttsPath: String = ""
    var result: String = ""

}