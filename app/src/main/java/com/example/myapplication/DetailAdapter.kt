package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FilmDetailRowBinding
import com.squareup.picasso.Picasso

class DetailAdapter(private var films: MutableList<Result>, private var listener: FilmDetailClickListener): RecyclerView.Adapter<DetailAdapter.FilmDetailRowHolder>(){
    interface FilmDetailClickListener{
        fun onClick(item: Result)
    }
    private lateinit var binding: FilmDetailRowBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailAdapter.FilmDetailRowHolder {
        binding = FilmDetailRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FilmDetailRowHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmDetailRowHolder, position: Int) {
        val place = films[position]
        holder.bind(place,listener)
    }

    override fun getItemCount(): Int {
        return films.size
    }
    class FilmDetailRowHolder(private val binding: FilmDetailRowBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(objet : Result, listener :FilmDetailClickListener){
            val stringImageConcat : String = "https://image.tmdb.org/t/p/w500".plus(objet.backdropPath) ?: ""
                if(!stringImageConcat.isNullOrEmpty()){

                Picasso.get()
                    .load(stringImageConcat)
                    .into(binding.imageSimilar)
            }


            itemView.setOnClickListener{
                listener.onClick(objet)
            }
        }


    }

}