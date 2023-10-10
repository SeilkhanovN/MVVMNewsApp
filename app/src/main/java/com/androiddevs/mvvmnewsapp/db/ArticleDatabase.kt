package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androiddevs.mvvmnewsapp.models.Article


@Database(entities = [Article::class], version = 1)
@TypeConverters(Converter::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao() : ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context) : ArticleDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java,
                        "article_db.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

//    @Volatile
//    private var instance : ArticleDatabase? = null
//    private val LOCK = Any()
//
//    operator fun invoke() = instance ?: synchronized(LOCK) {
//        instance ?: createDatabase(context.aplicationContext).also { instance = it }
//    }
//
//    private fun createDatabase(context: Context) {
//        Room.databaseBuilder(
//            context,
//            ArticleDatabase::class.java,
//            "article_db.db"
//        ).build()
//    }

}