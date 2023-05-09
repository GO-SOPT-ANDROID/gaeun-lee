package org.android.go.sopt

import org.android.go.sopt.R
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.android.go.sopt.data.*
import org.android.go.sopt.databinding.ItemBottomBinding
import org.android.go.sopt.databinding.ItemMusicBinding
import org.android.go.sopt.databinding.ItemTopBinding


class MultiViewAdapter(context: Context) :
    ListAdapter<MultiData, ViewHolder>(diffUtil) {

    init {
        setHasStableIds(true)
    }


    private val inflater by lazy { LayoutInflater.from(context) }
    private lateinit var selectionTracker: SelectionTracker<Long>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
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

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return when (currentList[position].itemType) {
            0 -> MULTI_TYPE1
            1 -> MULTI_TYPE2
            2 -> MULTI_TYPE3
            else -> MULTI_TYPE2

        }


    }


    fun setSelectionTracker(selectionTracker: SelectionTracker<Long>) {

        this.selectionTracker = selectionTracker
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MULTI_TYPE1 -> {
                (holder as TopRvTitleViewHolder).onBind(currentList[position])
                holder.setIsRecyclable(false)
            }
            MULTI_TYPE2 -> {
                (holder as MusicListViewHolder).onBind(currentList[position], position)
                holder.setIsRecyclable(false)
            }
            MULTI_TYPE3 -> {
                (holder as BottomSponsorViewHolder).onBind(currentList[position])
                holder.setIsRecyclable(false)
            }
        }

    }

    class TopRvTitleViewHolder(private val binding: ItemTopBinding) :
        ViewHolder(binding.root) {
        fun onBind(item: MultiData) {
            val dataObject = item.dataObject as DataObject.TopRvTitle
            binding.tvTitle.text = dataObject.title
        }
    }

    inner class MusicListViewHolder(private val binding: ItemMusicBinding) :
        ViewHolder(binding.root) {

        fun onBind(item: MultiData, itemPosition: Int) {

            val dataObject = item.dataObject as DataObject.Music
            binding.tvMusicTitle.text = dataObject.music
            binding.tvMusicSinger.text = dataObject.singer

            if (selectionTracker != null && selectionTracker.isSelected(absoluteAdapterPosition.toLong())) {
                binding.chkSelect.setImageResource(R.drawable.ic_home)
            } else {
                binding.chkSelect.setImageResource(R.drawable.ic_image)
            }


        }

        fun getItemDetails(viewHolder: RecyclerView.ViewHolder?): ItemDetailsLookup.ItemDetails<Long> {
            return object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getSelectionKey(): Long? {
                    return itemId
                }

                override fun getPosition(): Int {
                    if (viewHolder == null) {
                        return RecyclerView.NO_POSITION
                    }
                    return viewHolder.bindingAdapterPosition
                }

                override fun inSelectionHotspot(e: MotionEvent): Boolean {
                    return true
                }
            }
        }
    }

    class BottomSponsorViewHolder(private val binding: ItemBottomBinding) :
        ViewHolder(binding.root) {
        fun onBind(item: MultiData) {
            val dataObject = item.dataObject as DataObject.BottomSponsor
            binding.tvMusicEnd.text = dataObject.sponsor
        }

    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MultiData>() {
            override fun areItemsTheSame(oldItem: MultiData, newItem: MultiData): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MultiData, newItem: MultiData): Boolean {
                return oldItem == newItem
            }

        }
    }

    class MyItemDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        @Nullable
        override fun getItemDetails(@NonNull motionEvent: MotionEvent): ItemDetails<Long>? {
            val view = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
            if (view != null && recyclerView.getChildViewHolder(view) is MultiViewAdapter.MusicListViewHolder) {
                val viewHolder =
                    recyclerView.getChildViewHolder(view) as MultiViewAdapter.MusicListViewHolder
                return viewHolder.getItemDetails(viewHolder)
            }
            return null
        }
    }


}
