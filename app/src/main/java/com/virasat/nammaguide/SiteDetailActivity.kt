package com.virasat.nammaguide

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.virasat.nammaguide.data.CheckIn
import com.virasat.nammaguide.data.HeritageSite
import com.virasat.nammaguide.databinding.ActivitySiteDetailBinding
import com.virasat.nammaguide.viewmodel.AppViewModel
import kotlinx.coroutines.launch

class SiteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiteDetailBinding
    private val viewModel: AppViewModel by viewModels()
    private var siteId: String? = null
    private var isKannadaSelected = false
    private var mediaPlayer: MediaPlayer? = null
    private var isCheckedIn = false
    private var currentSite: HeritageSite? = null
    private var isAudioPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySiteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        siteId = intent.getStringExtra("SITE_ID")
        isKannadaSelected = intent.getBooleanExtra("IS_KANNADA", false)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        loadSiteDetails()
        setupButtons()
        checkCheckInStatus()
    }

    private fun loadSiteDetails() {
        lifecycleScope.launch {
            val site = viewModel.getSiteById(siteId ?: "")
            currentSite = site
            site?.let {
                binding.tvSiteName.text = if (isKannadaSelected) it.nameKn else it.nameEn
                binding.tvDescription.text =
                    if (isKannadaSelected) it.descriptionKn else it.descriptionEn
            }
        }
    }

    private fun setupButtons() {
        binding.btnPlayAudio.setOnClickListener {
            playAudio()
        }

        binding.btnPauseAudio.setOnClickListener {
            pauseAudio()
        }

        binding.btnCheckIn.setOnClickListener {
            if (!isCheckedIn) {
                checkIn()
            }
        }
    }

    private fun playAudio() {
        try {
            if (mediaPlayer == null || !isAudioPlaying) {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer()
                
                mediaPlayer?.setOnCompletionListener {
                    isAudioPlaying = false
                    binding.btnPlayAudio.isEnabled = true
                    binding.btnPauseAudio.isEnabled = false
                    binding.btnPlayAudio.text = getString(R.string.play_audio)
                }
                
                isAudioPlaying = true
                binding.btnPlayAudio.isEnabled = false
                binding.btnPauseAudio.isEnabled = true
                binding.btnPlayAudio.text = "Playing..."
                
                Toast.makeText(this, "Playing audio guide for ${currentSite?.nameEn}...", Toast.LENGTH_SHORT).show()
                
                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                    if (isAudioPlaying) {
                        isAudioPlaying = false
                        binding.btnPlayAudio.isEnabled = true
                        binding.btnPauseAudio.isEnabled = false
                        binding.btnPlayAudio.text = getString(R.string.play_audio)
                        Toast.makeText(this, "Audio guide completed!", Toast.LENGTH_SHORT).show()
                    }
                }, 5000)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            isAudioPlaying = false
            binding.btnPlayAudio.isEnabled = true
            binding.btnPauseAudio.isEnabled = false
            binding.btnPlayAudio.text = getString(R.string.play_audio)
        }
    }

    private fun pauseAudio() {
        if (isAudioPlaying) {
            isAudioPlaying = false
            binding.btnPlayAudio.isEnabled = true
            binding.btnPauseAudio.isEnabled = false
            binding.btnPlayAudio.text = getString(R.string.play_audio)
        }
    }

    private fun checkIn() {
        siteId?.let { id ->
            viewModel.insertCheckIn(CheckIn(siteId = id))
            isCheckedIn = true
            binding.btnCheckIn.isEnabled = false
            binding.tvCheckInStatus.text = getString(R.string.check_in_success)
            Toast.makeText(this, R.string.check_in_success, Toast.LENGTH_SHORT).show()
            showHiddenFact()
        }
    }

    private fun checkCheckInStatus() {
        lifecycleScope.launch {
            val checkIn = viewModel.getCheckInBySiteId(siteId ?: "")
            if (checkIn != null) {
                isCheckedIn = true
                binding.btnCheckIn.isEnabled = false
                binding.tvCheckInStatus.text = getString(R.string.check_in_success)
                showHiddenFact()
            }
        }
    }

    private fun showHiddenFact() {
        lifecycleScope.launch {
            val site = viewModel.getSiteById(siteId ?: "")
            site?.let {
                binding.hiddenFactCard.visibility = android.view.View.VISIBLE
                binding.tvHiddenFact.text =
                    if (isKannadaSelected) it.hiddenFactKn else it.hiddenFactEn
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
