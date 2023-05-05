package org.android.go.sopt

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.*
import org.android.go.sopt.databinding.ItemBottomBinding
import org.android.go.sopt.databinding.ItemMusicBinding
import org.android.go.sopt.databinding.ItemTopBinding


class MultiViewAdapter(context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var itemList = mutableListOf<MultiData>()

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            MULTI_TYPE1 -> {
                val binding: ItemTopBinding = ItemTopBinding.inflate(inflater, parent, false)
                return TopRvTitleViewHolder(binding)
            }
            MULTI_TYPE2 -> {
                val binding: ItemMusicBinding = ItemMusicBinding.inflate(inflater, parent, false)
                return MusicListViewHolder(binding)
            }
            MULTI_TYPE3 -> {
                val binding: ItemBottomBinding = ItemBottomBinding.inflate(inflater, parent, false)
                return BottomSponsorViewHolder(binding)
            }
            else -> {
                val binding: ItemMusicBinding = ItemMusicBinding.inflate(inflater, parent, false)
                return MusicListViewHolder(binding)
            }



        }
    }
    override fun getItemViewType(position: Int): Int {
        return itemList[position].itemType
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val log = itemList[position].itemType
        when(itemList[position].itemType) {
            MULTI_TYPE1 -> {
                (holder as TopRvTitleViewHolder).onBind(itemList[position])
                holder.setIsRecyclable(false)
            }
            MULTI_TYPE2 -> {
                (holder as MusicListViewHolder).onBind(itemList[position])
                holder.setIsRecyclable(false)
            }
            MULTI_TYPE3 -> {
                (holder as BottomSponsorViewHolder).onBind(itemList[position])
                holder.setIsRecyclable(false)
            }
        }

    }

    class TopRvTitleViewHolder(private val binding: ItemTopBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(item: MultiData){
            val dataObject = item.dataObject as DataObject.TopRvTitle
            binding.tvTitle.text=dataObject.title
        }
    }
    class MusicListViewHolder(private val binding: ItemMusicBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(item: MultiData){
            val dataObject = item.dataObject as DataObject.Music
            binding.tvMusicTitle.text=dataObject.music
            binding.tvMusicSinger.text=dataObject.singer

        }
    }
    class BottomSponsorViewHolder(private val binding: ItemBottomBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(item: MultiData){
            val dataObject = item.dataObject as DataObject.BottomSponsor
            binding.tvMusicEnd.text=dataObject.sponsor
        }
    }
    fun setItemList(newItemList:MutableList<MultiData>){
      itemList =  newItemList
    }








}
