package org.android.go.sopt

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
import com.bumptech.glide.Glide
import org.android.go.sopt.data.*
import org.android.go.sopt.databinding.ItemBottomBinding
import org.android.go.sopt.databinding.ItemTopBinding
import org.android.go.sopt.databinding.ItemUsersBinding
import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto

class MultiViewAdapter(context: Context) :
    ListAdapter<ResponseListUsersDto.Data, ViewHolder>(diffUtil) {

    init { // selectionTracker를 위한 설정
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
                val binding: ItemUsersBinding = ItemUsersBinding.inflate(inflater, parent, false)
                return UsersListViewHolder(binding)
            }
            MULTI_TYPE3 -> {
                val binding: ItemBottomBinding = ItemBottomBinding.inflate(inflater, parent, false)
                return BottomSponsorViewHolder(binding)
            }
            else -> {
                val binding: ItemUsersBinding = ItemUsersBinding.inflate(inflater, parent, false)
                return UsersListViewHolder(binding)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> MULTI_TYPE1
            currentList.size + 1 -> MULTI_TYPE3
            else -> MULTI_TYPE2
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + 2 // UserList + Top + Bottom 이므로 +2를 해준다
    }

    fun setSelectionTracker(selectionTracker: SelectionTracker<Long>) {
        this.selectionTracker = selectionTracker
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MULTI_TYPE1 -> {
                (holder as TopRvTitleViewHolder).onBind()
                holder.setIsRecyclable(false)
            }
            MULTI_TYPE2 -> {
                (holder as UsersListViewHolder).onBind(currentList[position - 1])
                holder.setIsRecyclable(false)
            }
            MULTI_TYPE3 -> {
                (holder as BottomSponsorViewHolder).onBind()
                holder.setIsRecyclable(false)
            }
        }
    }

    class TopRvTitleViewHolder(private val binding: ItemTopBinding) : ViewHolder(binding.root) {
        fun onBind() {
            binding.tvTitle.text = "USER LIST"
        }
    }

    inner class UsersListViewHolder(private val binding: ItemUsersBinding) :
        ViewHolder(binding.root) {

        fun onBind(item: ResponseListUsersDto.Data) {
            binding.tvUserName.text = item.first_name + item.last_name
            binding.tvUserEmail.text = item.email
            Glide.with(binding.root).load(item.avatar).into(binding.ivAvatar)

            if (selectionTracker != null && selectionTracker.isSelected(absoluteAdapterPosition.toLong())) {
                binding.ivSelect.setImageResource(R.drawable.ic_home)
            } else {
                binding.ivSelect.setImageResource(R.drawable.ic_image)
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
        fun onBind() {
            binding.tvMusicEnd.text = "후원사 SOPT"
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseListUsersDto.Data>() {
            override fun areItemsTheSame(
                oldItem: ResponseListUsersDto.Data,
                newItem: ResponseListUsersDto.Data,
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: ResponseListUsersDto.Data,
                newItem: ResponseListUsersDto.Data,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MyItemDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        @Nullable
        override fun getItemDetails(@NonNull motionEvent: MotionEvent): ItemDetails<Long>? {
            val view = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
            if (view != null && recyclerView.getChildViewHolder(view) is MultiViewAdapter.UsersListViewHolder) {
                val viewHolder =
                    recyclerView.getChildViewHolder(view) as MultiViewAdapter.UsersListViewHolder
                return viewHolder.getItemDetails(viewHolder)
            }
            return null
        }
    }
}
