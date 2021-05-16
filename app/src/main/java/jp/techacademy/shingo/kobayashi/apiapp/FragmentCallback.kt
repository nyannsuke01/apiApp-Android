package jp.techacademy.shingo.kobayashi.apiapp

interface FragmentCallback {
    // Itemを押したときの処理
    fun onClickItem(url: String)
    // Itemを押したときの処理
    fun onClickItem2(shop: Shop)
    // Itemを押したときの処理
    fun onClickItem3(favoriteShop: FavoriteShop)
    // お気に入り追加時の処理
    fun onAddFavorite(shop: Shop)
    // お気に入り削除時の処理
    fun onDeleteFavorite(id: String)
}