package com.example.tele2demo.presentaion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tele2demo.R
import com.example.tele2demo.databinding.FragmentBarcodeBinding
import com.example.tele2demo.databinding.FragmentConfirmDeviceBinding


class ConfirmDeviceFragment : Fragment() {

    private val args: ConfirmDeviceFragmentArgs by navArgs()
    private var _binding: FragmentConfirmDeviceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmDeviceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmDeviceIdEditText.setText(args.deviceId)
        binding.confirmDeviceBtn.setOnClickListener {
            findNavController().navigate(R.id.action_confirmDeviceFragment_to_mainFragment)
        }
    }
}