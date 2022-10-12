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
import com.erbeandroid.petfinder.feature.login.util.LoginListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.erbeandroid.petfinder.feature.animal.R.id as animal
import com.erbeandroid.petfinder.feature.discussion.R.id as discussion

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LoginListener {

    @Inject
    lateinit var connectionMonitoring: ConnectionMonitoring

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
    private val navController: NavController by lazy {
        navHostFragment.navController
    }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        connectionMonitoring.networkStatus.launchAndCollectIn(this) { state ->
            Log.d("TAG", if (state) "Connected" else "Disconnected")
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val fragment = destination.displayName.split("/")[1]
            Log.d("TAG", fragment)
        }
    }

    override fun onLoginSuccess() {
        signIn()
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