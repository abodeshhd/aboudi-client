package aboudi.client;

import aboudi.client.module.Module;
import aboudi.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import java.util.LinkedHashMap;
import java.util.Map;

public class AboudiClient implements ClientModInitializer {
    public static ModuleManager modules;
    private final Map<KeyBinding, Module> binds = new LinkedHashMap<>();

    @Override
    public void onInitializeClient() {
        modules = new ModuleManager();
        int[] keys = { GLFW.GLFW_KEY_R, GLFW.GLFW_KEY_G, GLFW.GLFW_KEY_K }; // Sprint=R, Fullbright=G, FPS Boost=K
        int i = 0;
        KeyBinding.Category cat = KeyBinding.Category.create(Identifier.of("aboudiclient", "main"));
        for (Module m : modules.getModules()) {
            KeyBinding kb = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aboudiclient." + m.getName().toLowerCase().replace(" ", "_"),
                InputUtil.Type.KEYSYM, keys[i++], cat));
            binds.put(kb, m);
        }
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            binds.forEach((kb, m) -> {
                while (kb.wasPressed()) {
                    m.toggle();
                    if (mc.player != null)
                        mc.player.sendMessage(Text.literal("[ABN] " + m.getName() + (m.isEnabled() ? " ON" : " OFF")), true);
                }
            });
            modules.tick();
        });
    }
}
