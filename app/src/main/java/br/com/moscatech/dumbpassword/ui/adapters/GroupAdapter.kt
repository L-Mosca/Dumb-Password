package br.com.moscatech.dumbpassword.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import br.com.moscatech.dumbpassword.base.BaseListAdapter
import br.com.moscatech.dumbpassword.base.ViewHolder
import br.com.moscatech.dumbpassword.databinding.AdapterGroupBinding

class GroupAdapter : BaseListAdapter<AdapterGroupBinding, String>(DIFF_UTIL) {

    var onItemClicked: ((String, Int) -> Unit)? = null
    var onItemLongClicked: ((String, View, Int) -> Unit)? = null

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {
            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup) -> AdapterGroupBinding
        get() = { inflater, parent ->
            AdapterGroupBinding.inflate(inflater, parent, false)
        }

    override fun onBindViewHolder(
        holder: ViewHolder<AdapterGroupBinding>,
        data: String,
        position: Int
    ) {
        with(holder.binding) {
            cvGroup.setOnClickListener {
                onItemClicked?.invoke(data, position)
            }

            cvGroup.setOnLongClickListener {
                onItemLongClicked?.invoke(data, this.cvGroup, position)
                true
            }

            tvGroupName.text = data
        }
    }
}