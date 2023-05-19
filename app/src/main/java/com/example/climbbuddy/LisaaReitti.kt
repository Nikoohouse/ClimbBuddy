package com.example.climbbuddy

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.fragment.findNavController
import com.example.climbbuddy.databinding.LisaaReittiBinding
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LisaaReitti : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: LisaaReittiBinding? = null

    //Muuttujia
    private var yritys = 0
    private val reitinvari = listOf(
        "Valitse reitin väri", "Valkoinen", "Keltainen", "Vihreä", "Sininen", "Violetti",
        "Punainen", "Musta"
    )
    private val otteetvari = listOf(
        "Valitse otteiden väri", "Valkoinen", "Keltainen", "Oranssi", "Vihreä", "Turkoosi",
        "Sininen", "Violetti", "Punainen", "Pinkki", "Musta"
    )

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = LisaaReittiBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Reitin värin alasvetovalikko
        val adapter = activity?.let{
            ArrayAdapter(it, android.R.layout.simple_list_item_activated_1, reitinvari)
        }
        binding.spinnerVarit.adapter=adapter
        binding.spinnerVarit.onItemSelectedListener = this

        // Otteiden värin alasvetovalikko
        val otteetadapter = activity?.let{
            ArrayAdapter(it, android.R.layout.simple_list_item_activated_1, otteetvari)
        }
        binding.spinnerOtteet.adapter=otteetadapter
        binding.spinnerOtteet.onItemSelectedListener = this

        //Tallenna nappi
        binding.btnTallenna.setOnClickListener {
            findNavController().navigate(R.id.action_LisaaReitti_to_ClimbBuddy)
        }

        //Yritykset nappi
        binding.btnYritys.setOnClickListener {
            yritys++
            binding.btnYritys.text = "Yrityksiä: $yritys"

        }

    }

    //Kamera

    //Muutetaan reitin sekä otteiden alasvetovalikkojen taustaväriä valikosta valitun värin mukaan
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
            if (parent != null) {
                when (text) {
                    "Valkoinen", "Valitse reitin väri", "Valitse otteiden väri" -> {
                        parent.background = Color.parseColor("#CCFFFFFF").toDrawable()
                    }
                    "Keltainen" -> {
                        parent.background = Color.parseColor("#CCFFFF00").toDrawable()
                    }
                    "Vihreä" -> {
                        parent.background = Color.parseColor("#CC008000").toDrawable()
                    }
                    "Sininen" -> {
                        parent.background = Color.parseColor("#CC0000FF").toDrawable()
                    }
                    "Violetti" -> {
                        parent.background = Color.parseColor("#CC7D0552").toDrawable()
                    }
                    "Punainen" -> {
                        parent.background = Color.parseColor("#CCFF0000").toDrawable()
                    }
                    "Musta" -> {
                        parent.background = Color.parseColor("#CC000000").toDrawable()
                    }
                    "Oranssi" -> {
                        parent.background = Color.parseColor("#CCFFA500").toDrawable()
                    }
                    "Pinkki" -> {
                        parent.background = Color.parseColor("#CCFFC0CB").toDrawable()
                    }
                    "Turkoosi" -> {
                        parent.background = Color.parseColor("#CC40E0D0").toDrawable()
                    }
                }
            }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

