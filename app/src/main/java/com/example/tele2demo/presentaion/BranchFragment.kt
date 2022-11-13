package com.example.tele2demo.presentaion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tele2demo.databinding.FragmentSalonBinding
import com.example.tele2demo.domain.ViewState
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class BranchFragment : Fragment() {

    private val viewModel: BranchViewModel by viewModels()
    private var _binding: FragmentSalonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSalonBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.enterBtn.setOnClickListener {
            openScanner()
        }
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Data -> onState(it.data)
                is ViewState.Error -> TODO()
                is ViewState.Loading -> TODO()
            }
        }
        binding.chooseCity.setOnClickListener {
            viewModel.getCities()
        }
        binding.chooseBranch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //viewModel.getBranches()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onState(state: BranchViewState) {
        when (state) {
            is BranchViewState.Branches -> {
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    state.branches.map { it.name }
                )
                binding.chooseBranch.adapter = adapter
            }
            is BranchViewState.Cities -> {
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    state.cities.map { it.name }
                )
                binding.chooseCity.adapter = adapter
            }
        }
    }

    private fun openScanner() {
        val barcodeLauncher = registerForActivityResult(
            ScanContract()
        ) { result: ScanIntentResult ->
            val action =
                BarcodeFragmentDirections.actionBarcodeFragmentToConfirmDeviceFragment(result.contents)
            findNavController().navigate(action)
        }
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.EAN_13)
        options.setPrompt("POWERED BY DIGITAL")
        options.setCameraId(0)
        options.setOrientationLocked(false)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        barcodeLauncher.launch(options)
    }
}