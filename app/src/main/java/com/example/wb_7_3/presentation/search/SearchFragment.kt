package com.example.wb_7_3.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.wb_7_3.appComponent
import com.example.wb_7_3.databinding.FragmentSearchBinding
import com.example.wb_7_3.utils.Resource
import javax.inject.Inject


class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null

    private val vm: SearchViewModel by viewModels {
        viewModelFactory
    }

    private var loadingPermission: Boolean = true

    private var enableToTouchLike: Boolean = true

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        binding?.buttonDislike?.setOnClickListener {
            getCatWithObserver()
        }
        binding?.buttonLike?.setOnClickListener {
            if(enableToTouchLike) {
                addCatToFavorite()
                vm.setEnableTouchLike(false)
                getCatWithObserver()
            }
        }
    }

    private fun setupObservers() {
        vm.currentCat.observe(viewLifecycleOwner, Observer {
            Log.e("URL", it.url)
            binding?.catImageImageView?.setImageURI(it.url)
            vm.setEnableTouchLike(true)
        })

        vm.loadingPermission.observe(viewLifecycleOwner, Observer {
            loadingPermission = it
        })

        vm.enableToTouchLike.observe(viewLifecycleOwner, Observer{
            enableToTouchLike = it
        })

        if (loadingPermission) {
            getCatWithObserver()
            vm.setLoadingPermissionFalse()
        }
    }

    private fun addCatToFavorite() {
        vm.postCat().observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    if (resource.data == true) {
                        Toast.makeText(context, "Cat added to favorite", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Can't add cat to favorite", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Error -> {
                    resource.message?.let { Log.e("Error", it) }
//                    Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
            }
        })
    }

    private fun getCatWithObserver() {
        vm.getCat().observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    binding?.catImageImageView?.visibility = View.VISIBLE
                    resource.data?.let { vm.setCurrentCat(it) }
                }
                is Resource.Error -> {
                    resource.message?.let { Log.e("Error", it) }
                    Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                    binding?.searchProgressBar?.visibility = View.GONE
                    binding?.catImageImageView?.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding?.searchProgressBar?.visibility = View.VISIBLE
                    binding?.catImageImageView?.visibility = View.GONE
                }
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


}