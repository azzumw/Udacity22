package com.example.shoeinventory.ui

import android.graphics.Color
import android.graphics.Color.rgb
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoeinventory.R
import com.example.shoeinventory.databinding.FragmentShoeListBinding
import com.example.shoeinventory.models.Shoe
import com.example.shoeinventory.viewModels.ShoeViewModel
import com.example.shoeinventory.viewModels.UserViewModel


class ShoeListFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.shoeListFrag = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nav = findNavController()
        userViewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                shoeViewModel.shoesList.observe(viewLifecycleOwner) {
                    //invoke add Data/view
                    if (it.size > 0) {
                        binding.NoShoeText.isVisible = false
                        binding.shoeImage.isVisible = false
                        Toast.makeText(context, "${it.size}", Toast.LENGTH_SHORT).show()
                        createView(it)
                    } else {
                        binding.NoShoeText.isVisible = true
                        binding.shoeImage.isVisible = true
                    }
                }
            } else {
                nav.navigate(R.id.loginFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                //set loggedin state as false
                userViewModel.logout()
                //navigate to login screen
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun navigateToDetailScreen() {
        findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
    }

    private fun createView(list: MutableList<Shoe>) {
        val layout = binding.shoelistLinearLayout
        layout.setBackgroundColor(rgb(222, 218, 217))

        for (i in 0 until list.size) {
            Log.e("FOR:", "$i")
            layout.addView(createCardView(createTextView(list[i].name)))
            layout.addView(createDivider())
        }
    }

    private fun createDivider(): View {
        val v = View(context)
        v.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        v.layoutParams.height = resources.getDimensionPixelSize(R.dimen.view_height)
        v.setBackgroundColor(Color.LTGRAY)
        return v
    }

    private fun createTextView(name: String): TextView {

        return TextView(context).apply {
            text = name
            textSize = 24.0f
            setPadding(32, 16, 32, 0)
            textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START
            layoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
        }
    }

    private fun createCardView(textView: TextView): CardView {

        val c = CardView(requireContext())
        c.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        c.layoutParams.height = resources.getDimensionPixelSize(R.dimen.text_view_height)
        c.radius = 10.0f
        c.cardElevation = 2.0f
        c.setPadding(resources.getDimensionPixelSize(R.dimen.fab_margin))
        c.addView(textView)

        return c
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}