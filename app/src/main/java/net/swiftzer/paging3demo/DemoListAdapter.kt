package net.swiftzer.paging3demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.swiftzer.paging3demo.databinding.ListItemBinding

class DemoListAdapter(
    private val lifecycleOwner: LifecycleOwner,
) : PagingDataAdapter<ListItem, DemoListHolder>(DemoListDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return DemoListHolder(lifecycleOwner, binding)
    }

    override fun onBindViewHolder(holder: DemoListHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DemoListHolder(
    lifecycleOwner: LifecycleOwner,
    private val binding: ListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.lifecycleOwner = lifecycleOwner
    }

    fun bind(item: ListItem?) {
        binding.item = item
    }
}

object DemoListDiffUtil : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

}
