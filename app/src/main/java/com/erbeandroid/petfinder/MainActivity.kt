package com.erbeandroid.petfinder

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.erbeandroid.petfinder.core.common.util.launchAndCollectIn
import com.erbeandroid.petfinder.databinding.ActivityMainBinding
import com.erbeandroid.petfinder.feature.login.phone.PhoneLoginViewModel
import com.erbeandroid.petfinder.feature.login.phone.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.erbeandroid.petfinder.feature.animal.R.id as animal
import com.erbeandroid.petfinder.feature.discussion.R.id as discussion

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectionMonitoring: ConnectionMonitoring

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val mainViewModel: MainViewModel by viewModels()
    private val phoneLoginViewModel: PhoneLoginViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        checkUser()
        observeData()
    }

    private fun checkUser() {
        if (mainViewModel.currentUser() == null) {
            signOut()
        } else {
            setupMain()
        }
    }

    private fun observeData() {
        phoneLoginViewModel.state.launchAndCollectIn(this) { state ->
            if (state == "Success") {
                signIn()
            }
        }

        profileViewModel.state.launchAndCollectIn(this) { state ->
            if (state == "Success") {
                profileViewModel.postUser()
                signIn()
            }
        }

        connectionMonitoring.networkStatus.launchAndCollectIn(this) { state ->
            Log.d("TAG", state.toString())
        }
    }

    private fun signIn() {
        navController.navigate(LoginDirections.actionGlobalNavigationLoginToMain())
        binding.bottomNavigation.isVisible = true
        setupMain()
    }

    private fun signOut() {
        navController.navigate(MainDirections.actionGlobalNavigationMainToLogin())
        binding.bottomNavigation.isVisible = false
    }

    private fun setupMain() {
        binding.bottomNavigation.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                animal.typeFragment,
                discussion.homeFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}