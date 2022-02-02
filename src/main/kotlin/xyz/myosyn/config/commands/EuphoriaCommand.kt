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

