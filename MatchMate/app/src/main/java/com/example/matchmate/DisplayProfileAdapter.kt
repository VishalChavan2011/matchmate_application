package com.example.matchmate


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchmate.databinding.ProfileBinding
import com.example.matchmate.model.ProfileData
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Adapter to display the list of profile.
 * @property context instance of [ApplicationContext].
 * @property onButtonClick the function to respond of the button click event.
 */
class DisplayProfileAdapter(
    @ApplicationContext val context: Context,
    private val onButtonClick: ((id: Int, isLiked: Boolean) -> Unit)
) : ListAdapter<ProfileData, DisplayProfileAdapter.ProfileViewHolder>(ProfileDiffUtil) {

    class ProfileViewHolder(private val binding: ProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            profileData: ProfileData,
            context: Context,
            onButtonClick: (id: Int, isLiked: Boolean) -> Unit
        ) {
            with(binding) {
                val name = "${profileData.firstName} ${profileData.lastName}"
                textviewName.text = name
                textviewAge.text = profileData.age.toString()
                textviewLocation.text = profileData.location
                if(profileData.profileStatusDecided) {
                    buttonLayout.visibility = View.GONE
                    textviewProfileStatus.apply {
                        text =  if(profileData.isProfileLiked) "Accepted" else "Declined"
                        visibility = View.VISIBLE
                        background = if(profileData.isProfileLiked)
                            AppCompatResources.getDrawable(context, R.drawable.like_rounded_corner)
                        else
                            AppCompatResources.getDrawable(context, R.drawable.dislike_rounded_corner)
                    }
                } else {
                    buttonLayout.visibility = View.VISIBLE
                    textviewProfileStatus.visibility = View.GONE
                }
                imageButtonLike.setOnClickListener {
                    onButtonClick(profileData.profileId, true)
                }
                imageButtonDislike.setOnClickListener {
                    onButtonClick(profileData.profileId, false)
                }
                Glide.with(context)
                    .load(profileData.image)
                    .placeholder(R.drawable.placeholder_image)
                    .into(imageviewProfileimage)
            }
        }
    }
    object ProfileDiffUtil : DiffUtil.ItemCallback<ProfileData>() {
        override fun areItemsTheSame(oldItem: ProfileData, newItem: ProfileData) = oldItem.profileId == newItem.profileId

        override fun areContentsTheSame(oldItem: ProfileData, newItem: ProfileData) = oldItem == newItem
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
            val binding = ProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProfileViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
            holder.bindData(getItem(position), context, onButtonClick)
        }
    }