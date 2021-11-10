package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.FilmServiceImpl.Companion.APIToken
import com.example.myapplication.FilmServiceImpl.Companion.language
import com.example.myapplication.databinding.FragmentRechercheBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RechercheFragment : Fragment() {

private  var binding : FragmentRechercheBinding?= null
    private val service by lazy { FilmServiceImpl() }
    private var films = mutableListOf<Result>()
    private lateinit var adapter: FilmAdapter
    val query = "a"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRechercheBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appelData()
        setupRecyclerView()
    }
private fun appelData(){
    CoroutineScope(Dispatchers.IO).launch {


        val response = service.getMovies(language,query, APIToken)


        withContext(Dispatchers.Main){
            if (response != null) {
                if (response.isSuccessful){

                    response.body()?.results?.forEach {
                        films.add(it)
                    }
                    setupRecyclerView()
                    Log.d("RESPONSE","SUCCESSFUL")
                    binding?.recycler?.isVisible = true


                }else{
                    Log.d("coroutine","response NOT successful")
                }
            }

        }
    }

}
    private fun setupRecyclerView(){
        adapter = FilmAdapter(films, object : FilmAdapter.FilmClickListener{

            override fun onClick(item: Result) {
                val directions = RechercheFragmentDirections.actionRechercheFragment2ToDetailFragment(item)
                findNavController().navigate(directions)
                Log.d("ONDETAIL", "navigate")
            }

        })
        binding?.recycler?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.recycler?.adapter = adapter
        adapter?.notifyDataSetChanged()
        binding?.recycler?.isVisible = true
    }
}