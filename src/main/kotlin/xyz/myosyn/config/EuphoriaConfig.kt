/*
All Rights Reserved

Copyright (c) 2022 Myosyn LLC

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

package xyz.myosyn.config

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Category
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import gg.essential.vigilance.data.SortingBehavior
import java.io.File

object EuphoriaConfig : Vigilant(File("./config/EuphoriaConfig.toml"), "Euphoria"){

    @Property(
        type = PropertyType.TEXT, name = "Last Launched Version",
        description = "The last version that you launched",
        category =  "General", subcategory = "Hidden",
        hidden = true
    )
    var lastLaunchedVersion = ""

    init {
        initialize()
    }

    fun differentVersion(old: String) {

    }
}
