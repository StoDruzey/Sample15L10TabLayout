package com.example.sample15l10tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sample15l10tablayout.databinding.FragmentTabBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IndexOutOfBoundsException

class TabFragment : Fragment() {
    private var _binding: FragmentTabBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTabBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewPager.adapter = FragmentTabAdapter(this@TabFragment)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = position.toString()
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}

class FragmentTabAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return if (position in 0..2) {
            XFragment()
        } else {
            throw IndexOutOfBoundsException("Wrong position $position")
        }
    }
}