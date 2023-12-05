package com.example.matchmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matchmate.databinding.ActivityMainBinding
import com.example.matchmate.repository.NetworkResult
import com.example.matchmate.repository.ProfileRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main launching activity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

   @Inject lateinit var profileRepository: ProfileRepository
   private lateinit var displayProfileAdapter: DisplayProfileAdapter

   private lateinit var viewModel: ProfileViewModel
   private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setUpViewModel()
    }

    private fun setupRecyclerView() {
        displayProfileAdapter = DisplayProfileAdapter(applicationContext) {
                profileId, isLiked ->
            if(isLiked) {
                Toast.makeText(this, "Accepted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Declined", Toast.LENGTH_SHORT).show()
            }
            viewModel.profileLiked(profileId, isLiked)

        }
        binding.recylerviewProfiles.apply {
            adapter = displayProfileAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(
            this,
            ProfileViewModelFactory(profileRepository)
        )[ProfileViewModel::class.java]

        viewModel.networkResult.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                is NetworkResult.Success -> {
                    binding.progressbar.visibility = View.GONE
                    binding.recylerviewProfiles.visibility = View.VISIBLE
                }

                is NetworkResult.Error -> {
                    binding.progressbar.visibility = View.GONE
                    binding.errorScreen.root.visibility = View.VISIBLE
                }
            }

            viewModel.profileData.observe(this) { list ->
                displayProfileAdapter.submitList(list)
            }
        }
    }
}