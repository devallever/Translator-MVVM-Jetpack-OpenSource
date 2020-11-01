//package org.xm.app.translator.opensource.mvvm.repository.db
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import org.xm.app.translator.opensource.mvvm.repository.db.dao.HistoryDao
//import org.xm.app.translator.opensource.mvvm.repository.db.entity.History
//
//@Database(
//    version = 1,
//    entities = [History::class]
//)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun historyDao(): HistoryDao
//
//    companion object {
//        private var mInstance: AppDatabase? = null
//        fun getDatabase(context: Context): AppDatabase {
//            mInstance?.let {
//                return it
//            }
//
//            return Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "TranslatorDatabase"
//            )
//                .build().apply {
//                    mInstance = this
//                }
//        }
//    }
//}