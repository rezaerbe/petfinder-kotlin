package com.erbeandroid.petfinder

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.erbeandroid.petfinder.core.common.base.BaseFragment
import com.erbeandroid.petfinder.core.common.extension.launchAndCollectIn
import com.erbeandroid.petfinder.databinding.ActivityMainBinding
import com.erbeandroid.petfinder.feature.login.util.LoginListener
import dagger.hilt.android.AndroidEntryPoint
import com.erbeandroid.petfinder.feature.animal.R.id as animal
import com.erbeandroid.petfinder.feature.component.R.id as component
import com.erbeandroid.petfinder.feature.discussion.R.id as discussion
import com.erbeandroid.petfinder.feature.task.R.id as task

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LoginListener {

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
        mainViewModel.connectionStatus.launchAndCollectIn(this) { state ->
            val currentFragment =
                navHostFragment.childFragmentManager.primaryNavigationFragment
            if (currentFragment is BaseFragment<*>) {
                if (state) currentFragment.onConnect() else currentFragment.onDisconnect()
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation?.isVisible =
                destination.id == component.listFragment ||
                        destination.id == animal.typeFragment ||
                        destination.id == discussion.discussionFragment ||
                        destination.id == task.taskFragment
        }
    }

    override fun onLoginSuccess() {
        signIn()
        setupMain()
    }

    private fun signIn() {
        navController.navigate(LoginDirections.actionGlobalNavigationLoginToMain())
        binding.bottomNavigation?.isVisible = true
    }

    private fun signOut() {
        navController.navigate(MainDirections.actionGlobalNavigationMainToLogin())
        binding.bottomNavigation?.isVisible = false
    }

    private fun setupMain() {
        binding.bottomNavigation?.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                component.listFragment,
                animal.typeFragment,
                discussion.discussionFragment,
                task.taskFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}