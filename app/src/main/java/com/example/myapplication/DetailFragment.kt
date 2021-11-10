package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailFragment : Fragment() {



    private lateinit var binding : FragmentDetailBinding
    private val service by lazy { FilmServiceImpl() }
    private val args : DetailFragmentArgs by navArgs()
    private lateinit var adapter: DetailAdapter
    private var films = mutableListOf<Result>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appelData()
        setupView()
    }
    private fun setupView(){
        val imageUrl = "https://image.tmdb.org/t/p/w500".plus(args.film.backdropPath)
        if(!imageUrl.isNullOrEmpty()){

            Picasso.get()
                .load(imageUrl)
                .into(binding.headerImage)
            Picasso.get()
                .load(imageUrl)
                .into(binding.affiche)

        }
        binding.descriptif.text = args.film.overview
        binding.title.text = args.film.title

    }
    private fun appelData(){
        CoroutineScope(Dispatchers.IO).launch {


            val response = service.getTrend(
                FilmServiceImpl.mediaType,
                FilmServiceImpl.timWindow,
                FilmServiceImpl.APIToken
            )


            withContext(Dispatchers.Main){
                if (response != null) {
                    if (response.isSuccessful){

                        response.body()?.results?.forEach {
                            films.add(it)
                        }
                        setupRecyclerView()
                        Log.d("RESPONSE","SUCCESSFUL")



                    }else{
                        Log.d("coroutine","response NOT successful")
                    }
                }

            }
        }
    }
    private fun setupRecyclerView(){
        adapter = DetailAdapter(films, object : DetailAdapter.FilmDetailClickListener{

            override fun onClick(item: Result) {

            }

        })
        binding?.recyclerSimilar?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding?.recyclerSimilar?.adapter = adapter
        adapter?.notifyDataSetChanged()
        binding?.recyclerSimilar?.isVisible = true
    }

}