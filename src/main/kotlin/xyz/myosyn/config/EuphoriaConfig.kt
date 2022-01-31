package xyz.myosyn.config

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Category
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import gg.essential.vigilance.data.SortingBehavior
import java.awt.Color
import java.io.File

object EuphoriaConfig : Vigilant(File("./config/EuphoriaConfig.toml"), "Euphoria"){

    @Property(
        type = PropertyType.TEXT, name = "Last Launched Version",
        description = "The last version that you launched"
    )
}
