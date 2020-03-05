package dev.biasin.playground.viewbindlist

import MultiListItem
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import dev.biasin.playground.viewbindlist.databinding.SecondListItemBinding

abstract class GenericAdapter(
    items: List<MultiListItem>,
    private val clickListener: (ListResponse) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: List<MultiListItem> = items

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = itemList[position].type
}

abstract class GenericHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var _binding: ViewBinding? = binding
    private val binding get() = _binding!!

    abstract fun bind(item: MultiListItem, clickListener: (ListResponse) -> Unit)

    fun onDestroy(){
        _binding = null
    }
}

class SecondGenericHolder(tBinding: SecondListItemBinding): GenericHolder(tBinding){

    private val binding: SecondListItemBinding = tBinding

    override fun bind(item: MultiListItem, clickListener: (ListResponse) -> Unit) {
        binding.cvFirstItem.setOnClickListener { clickListener(
            IntResponse(
                item.type
            )
        ) }
    }

    data class IntResponse(val response: Int):
        ListResponse
}

class MyAdapter(
    items: List<MultiListItem>,
    clickListener: (ListResponse) -> Unit
) : GenericAdapter(items, clickListener){
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}