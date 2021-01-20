package com.theusmadev.coronareminder.ui.countrydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.theusmadev.coronareminder.R
import com.theusmadev.coronareminder.databinding.FragmentCountryDetailBinding
import org.koin.android.ext.android.inject

class CountryDetailFragment: Fragment() {

    val viewModel: CountryDetailViewModel by inject()

    lateinit var binding: FragmentCountryDetailBinding
    lateinit var adapter: CoronaAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_detail, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = CoronaAdapter()
        binding.statesList.adapter = adapter

        setupObservers()
        viewModel.getCountryCoronaInfo()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.statesList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}