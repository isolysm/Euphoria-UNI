package xyz.myosyn.config

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object UpdateChecker {

    fun checkUpdate() {
        CoroutineScope(Dispatchers.IO + CoroutineName("Euphoria-Updater")).launch {
            val latestRelease = webUtil.fetchJSON("https://api.github.com/repos/ulypse/")
        }
    }
}
