package com.virasat.nammaguide

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.virasat.nammaguide.adapter.HeritageSiteAdapter
import com.virasat.nammaguide.databinding.ActivityMainBinding
import com.virasat.nammaguide.viewmodel.AppViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AppViewModel by viewModels()
    private lateinit var adapter: HeritageSiteAdapter
    private var isKannadaSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HeritageSiteAdapter { site ->
            val intent = Intent(this, SiteDetailActivity::class.java).apply {
                putExtra("SITE_ID", site.id)
                putExtra("IS_KANNADA", isKannadaSelected)
            }
            startActivity(intent)
        }
        
        binding.rvSites.layoutManager = LinearLayoutManager(this)
        binding.rvSites.adapter = adapter

        binding.btnEnglish.setOnClickListener {
            isKannadaSelected = false
            adapter.setLanguage(false)
        }

        binding.btnKannada.setOnClickListener {
            isKannadaSelected = true
            adapter.setLanguage(true)
        }

        binding.fabScanQr.setOnClickListener {
            val intent = Intent(this, QrScannerActivity::class.java).apply {
                putExtra("IS_KANNADA", isKannadaSelected)
            }
            startActivity(intent)
        }

        viewModel.allSites.observe(this) { sites ->
            if (sites.isNullOrEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.tvEmpty.text = "Loading sites..."
            } else {
                binding.tvEmpty.visibility = View.GONE
                adapter.submitList(sites)
            }
        }
    }
}
