package com.erbeandroid.petfinder.feature.component.list

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.erbeandroid.petfinder.feature.component.R
import com.erbeandroid.petfinder.feature.component.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        registerForContextMenu(binding.menuComponent.textMenu)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterForContextMenu(binding.menuComponent.textMenu)
        _binding = null
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.option_menu, menu)
    }

    override fun onContextItemSelected(menuItem: MenuItem): Boolean {
        val name = menuItem.title
        Log.d("TAG", "onMenuItemClick: $name")
        return when (menuItem.itemId) {
            R.id.option_1 -> {
                // Respond to menu item 1 click.
                true
            }
            R.id.option_2 -> {
                // Respond to menu item 2 click.
                true
            }
            R.id.option_3 -> {
                // Respond to menu item 3 click.
                true
            }
            else -> super.onContextItemSelected(menuItem)
        }
    }
}