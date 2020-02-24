package dev.biasin.playground.viewbindlist

import MultiListItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import dev.biasin.playground.viewbindlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MultiViewBindingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this is just the activity layout
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupList()
    }

    fun setupList(){
        // creating our list of MultiListItem
        val items: MutableList<MultiListItem> = mutableListOf()
        // adding some items
        items.add(
            PoetAndQuote(
                Pair(
                    "Dante Alighieri",
                    "Fatti non foste a viver come bruti ma per seguir virtute e canoscenza"
                )
            )
        )
        items.add(
            PoetAndQuote(
                Pair(
                    "Cecco Angiolieri",
                    "S’i’ fosse foco, arderei ’l mondo; s’i’ fosse vento, lo tempesterei; "
                )
            )
        )
        items.add(
            PoetAndQuote(
                Pair("Giovanni Boccaccio", "Viva amore, e muoia soldo.")
            )
        )
        items.add(Poet("Alessandro da Sant'Elpidio"))
        items.add(Poet("Bosone da Gubbio"))
        items.add(Poet("Aimaro Monaco dei Corbizzi"))
        // shuffling for different positioned items
        items.shuffle()

        layoutManager = LinearLayoutManager(this)
        adapter =
            MultiViewBindingAdapter(
                items
            ) { name: String ->
                poetClicked(name)
            }

        binding.rvList.layoutManager = layoutManager
        binding.rvList.adapter = adapter

    }

    private fun poetClicked(name: String) {
        Log.d("MainActivity", "Clicked author $name")
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

data class PoetAndQuote(val nameAndQuote: Pair<String,String>, override val type: Int = TYPE_PAIR): MultiListItem
data class Poet(val name: String, override val type: Int = TYPE_SINGLE): MultiListItem
