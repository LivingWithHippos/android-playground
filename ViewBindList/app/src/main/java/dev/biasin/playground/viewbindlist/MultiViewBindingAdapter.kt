package dev.biasin.playground.viewbindlist

import MultiListItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.biasin.playground.viewbindlist.databinding.FirstListItemBinding
import dev.biasin.playground.viewbindlist.databinding.SecondListItemBinding

class MultiViewBindingAdapter(
    items: List<MultiListItem>,
    private val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var itemList: List<MultiListItem> = items

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = itemList[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        when (viewType) {
            TYPE_PAIR -> {
                val pairBinding =
                    FirstListItemBinding.inflate(layoutInflater, parent, false)
                return FirstHolder(
                    pairBinding
                )
            }
            TYPE_SINGLE -> {
                val singleBinding =
                    SecondListItemBinding.inflate(layoutInflater, parent, false)
                return SecondHolder(
                    singleBinding
                )
            }
            else -> throw NoSuchListItemType(
                "No correspondent binding found for viewType $viewType"
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        if (holder is FirstHolder && item is PoetAndQuote)
            holder.bind(item, clickListener)
        if (holder is SecondHolder && item is Poet)
            holder.bind(item, clickListener)
    }

}

//todo: interface for these two?
class FirstHolder(fBinding: FirstListItemBinding) :
    RecyclerView.ViewHolder(fBinding.root) {

    private lateinit var content: Pair<String, String>

    private val binding: FirstListItemBinding = fBinding

    fun bind(item: MultiListItem, _clickListener: (String) -> Unit) {

        with(item as PoetAndQuote) {
            content = this.nameAndQuote
        }.also {
            binding.tvName.text = content.first
            binding.tvDescription.text = content.second
            binding.cvFirstItem.setOnClickListener { _clickListener(content.first) }
        }
    }
}

class SecondHolder(sBinding: SecondListItemBinding) :
    RecyclerView.ViewHolder(sBinding.root) {

    private lateinit var content: String

    private val binding: SecondListItemBinding = sBinding

    fun bind(item: MultiListItem, _clickListener: (String) -> Unit) {

        with(item as Poet) {
            content = this.name
        }.also {
            binding.tvName.text = content
            binding.cvFirstItem.setOnClickListener { _clickListener(content) }
        }
    }


}

const val TYPE_PAIR = R.layout.first_list_item
const val TYPE_SINGLE = R.layout.second_list_item

class NoSuchListItemType(message: String) : RuntimeException(message)