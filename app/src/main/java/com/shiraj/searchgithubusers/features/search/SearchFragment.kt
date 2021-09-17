package com.shiraj.searchgithubusers.features.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.shiraj.searchgithubusers.R
import com.shiraj.searchgithubusers.Validator
import com.shiraj.searchgithubusers.base.BaseFragment
import com.shiraj.searchgithubusers.databinding.FragmentSearchBinding
import com.shiraj.searchgithubusers.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SearchFragment :
    BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun bind(view: View) = FragmentSearchBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            btnSubmit.setOnClickListener {
                val searchKeyword = it.toString().trim() ?: ""
                getSearchResult(searchKeyword)
            }

            edtLogin.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val searchKeyword = binding.edtLogin.text.toString().trim()
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(
                            searchKeyword
                        )
                    )
                    return@OnEditorActionListener true
                }
                false
            })
        }
    }

    private fun getSearchResult(searchKeyword: String) {
        if (Validator.validateInput(searchKeyword)) {
            toast(R.string.enter_search_keyword)
        } else {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(
                    searchKeyword
                )
            )
        }
    }
}