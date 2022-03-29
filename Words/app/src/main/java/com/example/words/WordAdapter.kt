package com.example.words

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

const val SEARCH_PREFIX = "https://www.google.com/search?q="
class WordAdapter(val letterId:String,context:Context) : RecyclerView.Adapter<WordAdapter.WordViewHolder>(){

    private val filteredWords: List<String>
    init {

        val words = context.resources.getStringArray(R.array.words).toList()

        filteredWords = words.filter { it.startsWith(letterId,ignoreCase = true) }
            .shuffled()
            .take(5)
            .sorted()
    }
    class WordViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val button: Button = view.findViewById(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return WordViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = filteredWords[position]
        // Needed to call startActivity
        val context = holder.view.context

        // Set the text of the WordViewHolder
        holder.button.text = word

        holder.button.setOnClickListener {
            val queryUrl:Uri = Uri.parse("$SEARCH_PREFIX define $word")
            val intent = Intent(Intent.ACTION_VIEW,queryUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return filteredWords.size
    }
}