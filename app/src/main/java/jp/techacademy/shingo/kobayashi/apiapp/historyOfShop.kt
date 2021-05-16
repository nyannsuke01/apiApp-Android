package jp.techacademy.shingo.kobayashi.apiapp

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class historyOfShop: RealmObject() {
    @PrimaryKey
    var id: String = ""
    var imageUrl: String = ""
    var name: String = ""
    var url: String = ""

    companion object {
        fun findAll(): List<historyOfShop> = // クーポンを閲覧したShopを全件取得
            Realm.getDefaultInstance().use { realm ->
                realm.where(historyOfShop::class.java)
                    .findAll().let {
                        realm.copyFromRealm(it)
                    }
            }

        fun findBy(id: String): historyOfShop? = // クーポンを閲覧したShopをidで検索して返す。なければnullで返す
            Realm.getDefaultInstance().use { realm ->
                realm.where(historyOfShop::class.java)
                    .equalTo(historyOfShop::id.name, id)
                    .findFirst()?.let {
                        realm.copyFromRealm(it)
                    }
            }

        fun findByURL(url: String): historyOfShop? = // クーポンを閲覧したShopをidで検索して返す。なければnullで返す
            Realm.getDefaultInstance().use { realm ->
                realm.where(historyOfShop::class.java)
                    .equalTo(historyOfShop::url.name, url)
                    .findFirst()?.let {
                        realm.copyFromRealm(it)
                    }
            }

        fun insert(favoriteShop: historyOfShop) = // 閲覧履歴に追加
            Realm.getDefaultInstance().executeTransaction {
                it.insertOrUpdate(favoriteShop)
            }

        fun delete(id: String) = // idで閲覧履歴から削除する
            Realm.getDefaultInstance().use { realm ->
                realm.where(historyOfShop::class.java)
                    .equalTo(historyOfShop::id.name, id)
                    .findFirst()?.also { deleteShop ->
                        realm.executeTransaction {
                            deleteShop.deleteFromRealm()
                        }
                    }
            }
    }
}