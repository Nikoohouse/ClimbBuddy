package com.example.climbbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.climbbuddy.databinding.AloitusruutuBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ClimbBuddy : Fragment() {

    private var _binding: AloitusruutuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AloitusruutuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAloita.setOnClickListener {
            findNavController().navigate(R.id.action_ClimbBuddy_to_Aloitus)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}