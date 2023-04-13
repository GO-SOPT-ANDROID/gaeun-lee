package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemMusicBinding

class MyAdapter(context: Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private val itemList:List<Music> = listOf(Music("아이유","밤편지"),Music("고기","먹고싶다"))
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemMusicBinding = ItemMusicBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(private val binding: ItemMusicBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Music) {
            binding.tvMusicSinger.text = item.name
            binding.tvMusicTitle.text = item.author
        }
    }

}


data class Music(
    // 밑에 이상한 녀석이 있죠? 이는 안드로이드의 Meta Annotation입니다.
    //@DrawableRes val image: Int, // 서버에서 이미지 url이 내려오는 경우 String으로 받아야합니다. (Json 내부에는 URL 타입은 들어갈 수 없음)
    val name: String,
    val author: String
)








