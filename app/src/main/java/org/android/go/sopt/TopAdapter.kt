package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.TopRvTitle
import org.android.go.sopt.databinding.ItemMusicBinding
import org.android.go.sopt.databinding.ItemTopBinding

class TopAdapter(context: Context) : RecyclerView.Adapter<TopAdapter.TopViewHolder>() {

    private val itemList: List<TopRvTitle> = listOf(TopRvTitle("~ 노래리스트 ~"))
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val binding: ItemTopBinding = ItemTopBinding.inflate(inflater, parent, false)
        return TopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    class TopViewHolder(private val binding: ItemTopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: TopRvTitle) {
            binding.tvTitle.text = item.title
        }
    }

}


