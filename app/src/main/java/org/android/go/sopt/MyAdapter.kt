package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemMusicBinding

class MyAdapter(context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    private val itemList: List<Music> = listOf(
        Music("Kitsch", "IVE(아이브)"),
        Music("I AM", "IVE(아이브)"),
        Music("꾳", "지수(JISOO)"),
        Music("Ditto", "NewJeans"),
        Music("Hype boy", "NewJeans"),
        Music("OMG", "NewJeans"),
        Music("Love Me Like This", "NMIXX"),
        Music("Teddy Bear", "STAYC(스테이씨)"),
        Music("Like Crazy", "지민"),
        Music("사건의 지평선", "윤하(YOUNHA)"),
        Music("I Don't Think That I Like Her", "Charlie Puth")
    )
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemMusicBinding = ItemMusicBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Music) {
            binding.tvMusicSinger.text = item.music
            binding.tvMusicTitle.text = item.singer
        }
    }

}


data class Music(
    val music: String,
    val singer: String
)








