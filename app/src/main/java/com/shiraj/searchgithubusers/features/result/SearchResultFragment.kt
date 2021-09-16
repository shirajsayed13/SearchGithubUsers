package com.shiraj.searchgithubusers.features.result

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.shiraj.searchgithubusers.R
import com.shiraj.searchgithubusers.base.BaseFragment
import com.shiraj.searchgithubusers.databinding.FragmentSearchResultBinding
import com.shiraj.searchgithubusers.domain.models.User
import com.shiraj.searchgithubusers.features.result.SearchResultViewModel.Companion.searchKeyword
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class SearchResultFragment :
    BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private val viewModel: SearchResultViewModel by viewModels()

    private val viewAdapter: SearchUserAdapter by lazy(LazyThreadSafetyMode.NONE) { SearchUserAdapter() }

    override fun bind(view: View) = FragmentSearchResultBinding.bind(view)

    private val args: SearchResultFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchKeyword = args.searchKeyword
        setupRecyclerView()
        setupRetryButton()
        setupViewModel()
    }

    private fun setupRetryButton() = with(binding.btnRetry) {
        setOnClickListener { viewAdapter.retry() }
    }

    private fun setupRecyclerView() = with(binding.rvSearchResult) {
        setHasFixedSize(true)
        adapter = viewAdapter.withLoadStateHeaderAndFooter(
            header = SearchUserStateAdapter { viewAdapter.retry() },
            footer = SearchUserStateAdapter { viewAdapter.retry() }
        )
        viewAdapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading &&
                    viewAdapter.itemCount == 0
            showEmptyList(isEmptyList)

            binding.rvSearchResult.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.pbItems.isVisible = loadState.source.refresh is LoadState.Loading
            binding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

    private fun showEmptyList(isEmptyList: Boolean) {
        binding.tvNoSearchResult.isVisible = isEmptyList
    }

    private fun setupViewModel() = with(viewModel) {
        lifecycleScope.launch { result.collect(::handleData) }
    }

    private suspend fun handleData(data: PagingData<User>) {
        binding.pbItems.isVisible = false
        viewAdapter.submitData(data)
    }

}