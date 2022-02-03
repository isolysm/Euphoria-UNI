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

package xyz.myosyn.mixins;

import gg.essential.api.EssentialAPI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.transformer.Config;

@Mixin(GuiIngameMenu.class)
public abstract class MixinGuiIngameMenu extends GuiScreen {
    @Unique
    private GuiButton configButton;

    @Inject(method = "initGui", at = @At("TAIL"))
    private void addGuiButtons(CallbackInfo ci) {
        if (Config.showConfigOnEscape) {
            this.buttonList.add(configButton = new GuiButton(this.height - 100, 100, 100, "Euphoria"))
        }
    }

    @Inject(method = "actionPerformed", at = @At("TAIL"))
    private void onButtonPress(GuiButton button, CallbackInfo ci) {
        if (button == configButton) {
            EssentialAPI.getGuiUtil().openScreen(Config.INSTANCE.gui());
        }
    }
}
