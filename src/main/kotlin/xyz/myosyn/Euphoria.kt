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

package xyz.myosyn

import gg.essential.api.utils.get
import gg.essential.universal.UMinecraft
import gg.essential.universal.utils.MCMinecraft
import net.minecraftforge.fml.common.Mod
import xyz.myosyn.config.EuphoriaConfig

@Mod(
    modid = Euphoria.MODID,
    name = Euphoria.MOD_NAME,
    version = Euphoria.VERSION,
    acceptedMinecraftVersions = "[1.8.9]",
    clientSideOnly = true,
    modLanguage = "kotlin",
    modLanguageAdapter = "gg.essential.api.utils.KotlinAdapter"
)
object Euphoria {

    const val MODID = "Euphoria-BaseMod"
    const val MOD_NAME = "Euphoria"
    const val VERSION = "0.0.1"
    val mc: MCMinecraft
        get() = UMinecraft.getMinecraft()

    val isEnabled
        get() = EuphoriaConfig.isEnabled
}
