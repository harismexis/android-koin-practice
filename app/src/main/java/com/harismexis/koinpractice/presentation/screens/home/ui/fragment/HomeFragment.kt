package com.harismexis.koinpractice.presentation.screens.home.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.koinpractice.R
import com.harismexis.koinpractice.core.domain.Hero
import com.harismexis.koinpractice.databinding.FragmentHomeBinding
import com.harismexis.koinpractice.framework.util.event.EventObserver
import com.harismexis.koinpractice.framework.util.extensions.showToast
import com.harismexis.koinpractice.framework.util.ui.hideKeyboard
import com.harismexis.koinpractice.presentation.base.BaseFragment
import com.harismexis.koinpractice.presentation.result.HerosResult
import com.harismexis.koinpractice.presentation.screens.home.ui.adapter.HerosAdapter
import com.harismexis.koinpractice.presentation.screens.home.ui.viewholder.HeroViewHolder
import com.harismexis.koinpractice.presentation.screens.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.compat.ViewModelCompat.getViewModel

class HomeFragment : BaseFragment(), HeroViewHolder.HeroClickListener,
    android.widget.SearchView.OnQueryTextListener {

    lateinit var viewModel: HomeViewModel
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: HerosAdapter
    private var uiModels: MutableList<Hero> = mutableListOf()

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onCreateView() {
        viewModel = getViewModel(this, HomeViewModel::class.java)
        setupToolbar()
        setupSwipeToRefresh()
        initialiseRecycler()
        initialiseSearchView()
    }

    private fun setupSwipeToRefresh() {
        binding?.homeSwipeRefresh?.setOnRefreshListener {
            binding?.homeSwipeRefresh?.isRefreshing = true
            viewModel.fetchHeros()
        }
    }

    private fun initialiseRecycler() {
        adapter = HerosAdapter(uiModels, this)
        adapter.setHasStableIds(true)
        binding?.homeList?.layoutManager = LinearLayoutManager(this.context)
        binding?.homeList?.adapter = adapter
    }

    private fun initialiseSearchView() {
        binding?.searchView?.setOnQueryTextListener(this)
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.fetchHeros()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph, binding?.drawerLayout)
        binding?.apply { ->
            toolbar.setupWithNavController(navController, appBarConf)
            toolbar.inflateMenu(R.menu.menu_home)
            // Without listener it's not working, but it should(?)
            // as we call setupWithNavController
            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    else -> false
                }
            }
            navView.setupWithNavController(navController)
        }
    }

    private fun observeLiveData() {
        viewModel.herosResult.observe(viewLifecycleOwner, {
            when (it) {
                is HerosResult.Success -> populate(it.items)
                is HerosResult.Error -> populateError(it.error)
            }
        })

        viewModel.showErrorMessage.observe(viewLifecycleOwner, EventObserver {
            requireContext().showToast(it)
        })
    }

    private fun populate(models: List<Hero>) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
        binding?.homeList?.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

    private fun populateError(error: Exception) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
    }

    override fun onHeroClick(
        item: Hero,
        position: Int
    ) {
        binding?.searchView?.clearFocus()
        val action = HomeFragmentDirections.actionHomeDestToHeroDetailDest(item.id)
        findNavController().navigate(action)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        requireActivity().hideKeyboard()
        viewModel.updateSearchQuery(query)
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }

    override fun getRootView() = binding?.root

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}