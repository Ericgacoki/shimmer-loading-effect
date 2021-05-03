package com.ericg.shimmerloading

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ericg.shimmerloading.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startShimmerLoading()
        binding.btnRefresh.setOnClickListener {
            startShimmerLoading()
        }
    }

    private fun startShimmerLoading() {
        binding.mainLayout.visibility = GONE
        binding.shimmerLayout.apply {
            visibility = VISIBLE
            startShimmer()
        }

        CoroutineScope(Main).launch(Main) {
            delay(2500)
            stopShimmerLoading()
        }
    }

    // Normally this should happen after a network request 😎
    private fun stopShimmerLoading() {
        binding.shimmerLayout.apply {
            clearAnimation()
            stopShimmer()
            visibility = GONE
        }
        binding.mainLayout.visibility = VISIBLE
    }

}