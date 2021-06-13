package com.harismexis.koinpractice.presentation.screens.herodetail.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.harismexis.koinpractice.R
import com.harismexis.koinpractice.databinding.FragmentHeroDetailBinding
import com.harismexis.koinpractice.databinding.HeroDetailViewBinding
import com.harismexis.koinpractice.datamodel.domain.Hero
import com.harismexis.koinpractice.framework.extensions.populateWithGlide
import com.harismexis.koinpractice.framework.extensions.setTextOrUnknown
import com.harismexis.koinpractice.framework.extensions.showToast
import com.harismexis.koinpractice.presentation.base.BaseFragment
import com.harismexis.koinpractice.presentation.result.HeroDetailResult
import com.harismexis.koinpractice.presentation.screens.herodetail.viewmodel.HeroDetailViewModel
import org.koin.androidx.viewmodel.compat.ViewModelCompat

class HeroDetailFragment : BaseFragment() {

    private var binding: FragmentHeroDetailBinding? = null
    private var detailBinding: HeroDetailViewBinding? = null
    private lateinit var viewModel: HeroDetailViewModel

    companion object {
        private const val ARG_HERO_ID = "actorId"

        fun newInstance(actorId: Int): HeroDetailFragment {
            val args = Bundle()
            args.putInt(ARG_HERO_ID, actorId)
            val fragment = HeroDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView() {
        viewModel = ViewModelCompat.getViewModel(this, HeroDetailViewModel::class.java)
    }

    override fun onViewCreated() {
        setupToolbar()
        observeLiveData()
        fetchHeroDetails()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.toolbar?.setupWithNavController(navController, appBarConf)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        detailBinding = binding?.itemDetailContainer
    }

    override fun getRootView() = binding?.root

    private fun observeLiveData() {
        viewModel.heroDetailResult.observe(viewLifecycleOwner, {
            when (it) {
                is HeroDetailResult.Success -> populate(it.item)
                is HeroDetailResult.Error -> populateError(it.error)
            }
        })
    }

    private fun populateError(error: String) {
        requireContext().showToast(error)
    }

    private fun fetchHeroDetails() {
        val heroId = arguments?.getInt(ARG_HERO_ID)
        heroId?.let {
            viewModel.getHeroById(it)
        }
    }

    private fun populate(hero: Hero) {
        binding?.let {
            it.toolbarTitle.text = hero.name
            it.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
        }
        detailBinding?.let {
            requireContext().populateWithGlide(it.img, hero.image)
            it.txtName.setTextOrUnknown(hero.name)
            it.txtStatus.setTextOrUnknown(hero.status)
            it.txtSpecies.setTextOrUnknown(hero.species)
            it.txtType.setTextOrUnknown(hero.type)
            it.txtGender.setTextOrUnknown(hero.gender)
        }
    }

}