package org.android.go.sopt


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemBottomBinding

class BottomAdapter(context: Context) : RecyclerView.Adapter<BottomAdapter.BottomViewHolder>() {

    private val itemList: List<Desc> = listOf(Desc("주식회사 멜론"), Desc("후원사 솝트"))
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomViewHolder {
        val binding: ItemBottomBinding = ItemBottomBinding.inflate(inflater, parent, false)
        return BottomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BottomViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class BottomViewHolder(private val binding: ItemBottomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Desc) {
            binding.tvMusicEnd.text = item.desc
        }
    }

}


data class Desc(

    val desc: String,

    )
