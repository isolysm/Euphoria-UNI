package xyz.myosyn.config.commands

import gg.essential.api.EssentialAPI
import gg.essential.api.commands.Command
import gg.essential.api.commands.DefaultHandler
import xyz.myosyn.config.EuphoriaConfig

object EuphoriaCommand : Command("euphoria") {

    override val commandAliases =  setOf(Alias("guicoremod"))

    @DefaultHandler
    fun execCommand() {
        EssentialAPI.getGuiUtil().openScreen(EuphoriaConfig.gui)
    }
}

