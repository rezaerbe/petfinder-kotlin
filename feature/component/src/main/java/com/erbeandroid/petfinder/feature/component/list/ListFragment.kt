package com.erbeandroid.petfinder.feature.component.list

import androidx.fragment.app.FragmentTransaction
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.feature.component.databinding.FragmentListBinding
import com.erbeandroid.petfinder.feature.component.list.util.CustomDialogFragment

class ListFragment :
    BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    override fun initInteraction() {
        binding.dialogComponent.initFullDialog {
            showFullDialog()
        }
    }

    override fun initObservation() = Unit

    private fun showFullDialog() {
        val customDialogFragment = CustomDialogFragment()

        // The device is using a large layout, so show the fragment as a dialog
        // customDialogFragment.show(parentFragmentManager, "dialog")

        // The device is smaller, so show the fragment fullscreen
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        // For a little polish, specify a transition animation
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        // To make it fullscreen, use the 'content' root view as the container
        // for the fragment, which is always the root view for the activity
        transaction
            .add(android.R.id.content, customDialogFragment)
            .addToBackStack(null)
            .commit()
    }
}