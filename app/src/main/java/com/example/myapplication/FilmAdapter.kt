package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FilmRowBinding
import com.squareup.picasso.Picasso


class FilmAdapter (private var films: MutableList<Result>, private var listener: FilmClickListener): RecyclerView.Adapter<FilmAdapter.FilmRowHolder>(){
    interface FilmClickListener{
        fun onClick(item: Result)
    }
    private lateinit var binding: FilmRowBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmAdapter.FilmRowHolder {
        binding = FilmRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FilmRowHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmAdapter.FilmRowHolder, position: Int) {
        val place = films[position]
        holder.bind(place,listener)
    }

    override fun getItemCount(): Int {
        return films.size
    }
    class FilmRowHolder(private val binding: FilmRowBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(objet : Result, listener : FilmAdapter.FilmClickListener){
           val stringImageConcat : String = "https://image.tmdb.org/t/p/w500".plus(objet.backdropPath) ?: ""

            binding.title.text = objet.originalTitle
            //binding.score.text = objet.voteAverage.toString()
            binding.date.text = objet.releaseDate


            if(!stringImageConcat.isNullOrEmpty()){

                Picasso.get()
                    .load(stringImageConcat)
                    .into(binding.filmAvatar)
            }

            itemView.setOnClickListener{
                listener.onClick(objet)
            }
        }


    }

}