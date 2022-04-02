package com.example.androidtrivia

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.androidtrivia.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {
    private var _binding : FragmentGameWonBinding? = null
    private val binding get() = _binding!!
     private var q : Int = 0
    private var c = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            q = it.getInt("numQuestions")
            c = it.getInt("numCorrect")
        }

        var args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }
        args?.numCorrect
        args?.numQuestions
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_won,container,false)

        Toast.makeText(context,"you answered $c out of $q questions",Toast.LENGTH_LONG).show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextMatchButton.setOnClickListener {
            findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu,menu)
        if(getShareIntent().resolveActivity(requireActivity().packageManager)==null){
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.share -> {
                shareSuccess()
                return true
            }
            else  -> super.onOptionsItemSelected(item)
        }
    }

    private fun getShareIntent():Intent{
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_success_text,c,q))
        return intent
    }

    private fun shareSuccess() = startActivity(getShareIntent())

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}